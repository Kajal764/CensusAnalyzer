package censusanalyser;

import com.brideglabz.CSVBuilderException;
import com.google.gson.Gson;
import org.json.JSONException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {

    Map<String, CensusDAO> censusMap =null;
    Map<String,CSVStateDAO> CSVStateList=null;

    public CensusAnalyser() {
        this.censusMap = new TreeMap<>();
        this.CSVStateList=new TreeMap<>();
    }

    public int loadIndiaCensusData(String... csvFilePath) throws CensusAnalyserException {
        censusMap = new CensusLoader().loadCensusData(IndiaCensusCSV.class,csvFilePath);
        return censusMap.size();
    }

    public int loadUsCensusData(String... csvFilePath) throws CensusAnalyserException {
        censusMap=new CensusLoader().loadCensusData(UsCensusCsv.class,csvFilePath);
        return censusMap.size();
    }


    public String sortingIndiaCensusData() throws IOException, CSVBuilderException, JSONException, CensusAnalyserException {
        if(censusMap == null || censusMap.size()==0)
        {
            throw new CensusAnalyserException("NO Census Data",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List <CensusDAO> sorttedList = censusMap.values().stream().collect(Collectors.toList());
        List <CensusDAO> sortedList = sorttedList.stream().sorted(Comparator.comparing(CensusDAO::getState)).collect(Collectors.toList());
        System.out.println(sortedList);
        String sortList=new Gson().toJson(sortedList);
        System.out.println(sortList);
        return sortList;
    }

    public String sortingIndiaStateCodeCSV() throws CensusAnalyserException {

        if(censusMap == null || censusMap.size()==0)
        {
            throw new CensusAnalyserException("NO Census Data",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List <CensusDAO> sorttedList =  censusMap.values().stream().collect(Collectors.toList());
        List <CensusDAO> sortedList = sorttedList.stream().sorted(Comparator.comparing(CensusDAO::getStateCode)).collect(Collectors.toList());
        String sortList = new Gson().toJson(sortedList);
        System.out.println(sortList);
        return sortList;
    }


    public String sortIndiaCensusPopulationWise() throws CensusAnalyserException {
        if(censusMap == null || censusMap.size()==0)
        {
            throw new CensusAnalyserException("NO Census Data",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }

        List <CensusDAO> sorttedList = censusMap.values().stream().collect(Collectors.toList());
        List <CensusDAO> sortedList = sorttedList.stream().sorted(Comparator.comparing(CensusDAO::getPopulation).reversed()).collect(Collectors.toList());
        System.out.println(sortedList);
        String sortList=new Gson().toJson(sortedList);
        System.out.println(sortList);
        return sortList;


    }

    public String sortIndiaCensusDensityWise() throws CensusAnalyserException {
        if(censusMap == null || censusMap.size()==0)
        {
            throw new CensusAnalyserException("NO Census Data",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List <CensusDAO> sorttedList = censusMap.values().stream().collect(Collectors.toList());
        List <CensusDAO> sortedList = sorttedList.stream().sorted(Comparator.comparing(CensusDAO::getDensityPerSqKm).reversed()).collect(Collectors.toList());
        System.out.println(sortedList);
        String sortList=new Gson().toJson(sortedList);
        System.out.println(sortList);
        return sortList;
    }

    public String sortIndiaCensusAreaWise() throws CensusAnalyserException {
        if(censusMap == null || censusMap.size()==0)
        {
            throw new CensusAnalyserException("NO Census Data",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List <CensusDAO> sorttedList = censusMap.values().stream().collect(Collectors.toList());
        List <CensusDAO> sortedList = sorttedList.stream().sorted(Comparator.comparing(CensusDAO::getAreaInSqKm).reversed()).collect(Collectors.toList());
        String sortList=new Gson().toJson(sortedList);
        System.out.println(sortList);
        return sortList;
    }
}