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

public class CensusAnalyser {
    List<IndiaCensusCSV> censusCSVList=null;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            censusCSVList=csvBuilder.getCSVFileList(reader,IndiaCensusCSV.class);
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

    public int loadIndiaCensusDataCode(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
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

    private String sortIndiaCensusData(List<IndiaCensusCSV> list) throws JSONException {
        /*ArrayList<IndiaCensusCSV> list = new ArrayList();
        while (censusCSVIterator.hasNext()) {
            list.add(censusCSVIterator.next());
        }*/
        List <IndiaCensusCSV> sortedList = list.stream().sorted(Comparator.comparing(IndiaCensusCSV::getState)).collect(Collectors.toList());
        String sortList = new Gson().toJson(sortedList);
        return sortList;
    }

    public String sortingIndiaCensusData() throws IOException, CSVBuilderException, JSONException, CensusAnalyserException {
       if(censusCSVList == null || censusCSVList.size()==0)
       {
           throw new CensusAnalyserException("NO Census Data",
                   CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
       }


        return String.valueOf(this.sortIndiaCensusData(censusCSVList));
    }

}