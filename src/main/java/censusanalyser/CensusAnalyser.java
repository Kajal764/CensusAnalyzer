package censusanalyser;

import com.brideglabz.CSVBuilderException;
import com.google.gson.Gson;
import org.json.JSONException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {

    Map<String, CensusDAO> censusMap =null;
    public enum Country {
        INDIA,US
    }
    private Country country;

    public CensusAnalyser(Country country) {
        this.country = country;
    }

    public CensusAnalyser() {
        this.censusMap = new TreeMap<>();
    }

    public int loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        censusMap = CensusAdapterFactory.getCensusData(country,csvFilePath);
        return censusMap.size();
    }


    public String sortingIndiaCensusData() throws IOException, CSVBuilderException, JSONException, CensusAnalyserException {
        if(censusMap == null || censusMap.size()==0)
        {
            throw new CensusAnalyserException("NO Census Data",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List sorttedList=censusMap.values().stream().sorted(Comparator.comparing(censusData->censusData.stateCode))
        .map(censusDao->censusDao.getCensusDTO(country)).collect(Collectors.toList());
        System.out.println(sorttedList);
        String sortList=new Gson().toJson(sorttedList);
        System.out.println(sortList);
        return sortList;
    }


    public String sortingIndiaStateCodeCSV() throws CensusAnalyserException {

        if(censusMap == null || censusMap.size()==0)
        {
            throw new CensusAnalyserException("NO Census Data",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }

        List sorttedList=censusMap.values().stream().sorted(Comparator.comparing(censusData->censusData.state))
                .map(censusDao->censusDao.getCensusDTO(country)).collect(Collectors.toList());
        String sortList = new Gson().toJson(sorttedList);
        System.out.println(sortList);
        return sortList;
    }

    public String sortIndiaCensusPopulationWise() throws CensusAnalyserException {
        if(censusMap == null || censusMap.size()==0)
        {
            throw new CensusAnalyserException("NO Census Data",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }

        List sorttedList=censusMap.values().stream().sorted(Comparator.comparing(censusData->censusData.population))
                .map(censusDao->censusDao.getCensusDTO(country)).collect(Collectors.toList());
        Collections.reverse(sorttedList);
        String sortList=new Gson().toJson(sorttedList);
        System.out.println(sortList);
        return sortList;
    }

    public String sortIndiaCensusDensityWise() throws CensusAnalyserException {
        if(censusMap == null || censusMap.size()==0)
        {
            throw new CensusAnalyserException("NO Census Data",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List sorttedList=censusMap.values().stream().sorted(Comparator.comparing(censusData->censusData.densityPerSqKm))
                .map(censusDao->censusDao.getCensusDTO(country)).collect(Collectors.toList());
        Collections.reverse(sorttedList);

        String sortList=new Gson().toJson(sorttedList);
        System.out.println(sortList);
        return sortList;
    }

    public String sortIndiaCensusAreaWise() throws CensusAnalyserException {
        if(censusMap == null || censusMap.size()==0)
        {
            throw new CensusAnalyserException("NO Census Data",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List sorttedList=censusMap.values().stream().sorted(Comparator.comparing(censusData->censusData.areaInSqKm))
                .map(censusDao->censusDao.getCensusDTO(country)).collect(Collectors.toList());
        Collections.reverse(sorttedList);

        String sortList=new Gson().toJson(sorttedList);
        System.out.println(sortList);
        return sortList;
    }
}