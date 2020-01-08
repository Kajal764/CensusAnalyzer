package censusanalyser;

import com.brideglabz.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public class CensusAnalyser {
    ArrayList list = new ArrayList();

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSVIterator(reader, IndiaCensusCSV.class);
            return this.getCount(censusCSVIterator);

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
            Iterator<CSVStates> censusCSVIterator = csvBuilder.getCSVIterator(reader, CSVStates.class);
            return this.getCount(censusCSVIterator);
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
        return numOfEnteries;

    }


    private String sortIndiaCensusData(Iterator<IndiaCensusCSV> censusCSVIterator, int firstIndex) {
        while (censusCSVIterator.hasNext()) {
            String s = (censusCSVIterator.next()).toString();
            list.add(s); }
            String objlist = bubbleSort(list,firstIndex);
            return objlist;
    }

    private String bubbleSort(ArrayList list, int firstIndex) {
        for (int i = 0; i < list.size(); i++)
        { String element1 = (String) list.get(i);
            for (int j = 0; j < list.size() - 1; j++) {
                String element2 = (String) list.get(j);
                if (element1.compareTo(element2) < 0) {
                    String temp = (String) list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }

        System.out.println(list);
        if(firstIndex==1)
            return (String) list.get(0);
        return (String) list.get(list.size() - 1);
    }

    public String sortingIndiaCensusData(String csvFilePath, int firstIndex) throws IOException, CSVBuilderException {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSVIterator(reader, IndiaCensusCSV.class);
            return this.sortIndiaCensusData(censusCSVIterator,firstIndex);
    }

}