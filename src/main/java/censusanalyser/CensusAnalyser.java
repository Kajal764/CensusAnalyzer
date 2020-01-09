package censusanalyser;


import com.brideglabz.CSVBuilderException;
import com.brideglabz.CSVBuilderFactory;
import com.brideglabz.ICSVBuilder;
import com.google.gson.Gson;
import org.json.JSONException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
           List<IndiaCensusCSV> censusCSVList=csvBuilder.getCSVFileList(reader,IndiaCensusCSV.class);
            return censusCSVList.size();
          //  return this.getCount(censusCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }

    }

    public int loadIndiaCensusDataCode(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            /*Iterator<CSVStates> censusCSVIterator = csvBuilder.getCSVIterator(reader, CSVStates.class);
            return this.getCount(censusCSVIterator);*/
            List<CSVStates> censusCSVList=csvBuilder.getCSVFileList(reader,CSVStates.class);
            return censusCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }

    }


    private <E> int getCount(Iterator<E> censusCSVIterator) {
        Iterable<E> csvIterator = () -> censusCSVIterator;
        int numOfEnteries = (int) StreamSupport.stream(csvIterator.spliterator(), true).count();
        System.out.println(numOfEnteries);
        return numOfEnteries;

    }

    private String sortIndiaCensusData(Iterator<IndiaCensusCSV> censusCSVIterator) throws JSONException {
        ArrayList<IndiaCensusCSV> list = new ArrayList();
        while (censusCSVIterator.hasNext()) {
            list.add(censusCSVIterator.next());
        }
        List <IndiaCensusCSV> sortedList = list.stream().sorted(Comparator.comparing(IndiaCensusCSV::getState)).collect(Collectors.toList());
        String sortList = new Gson().toJson(sortedList);

        return sortList;
    }

    public String sortingIndiaCensusData(String csvFilePath) throws IOException, CSVBuilderException, JSONException {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSVIterator(reader, IndiaCensusCSV.class);
            return String.valueOf(this.sortIndiaCensusData(censusCSVIterator));
    }

}