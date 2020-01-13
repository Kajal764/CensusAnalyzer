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
    List<CensusDAO> censusList =null;
    List<CSVStateDAO> CSVStateList=null;

    public CensusAnalyser() {
        this.censusList = new ArrayList<CensusDAO>();
        this.CSVStateList=new ArrayList<CSVStateDAO>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        return this.loadCensusData(csvFilePath,IndiaCensusCSV.class);

    }

    public int loadUsCensusData(String csvFilePath) throws CensusAnalyserException {
        return this.loadCensusData(csvFilePath,UsCensusCsv.class);
    }

    private <E> int loadCensusData(String csvFilePath, Class<E> CensusCSVClass) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<E> csvFileList = csvBuilder.getCSVFileList(reader, CensusCSVClass);
            int i=0;
            if(CensusCSVClass.getName().equals("censusanalyser.IndiaCensusCSV")) {
                csvFileList.stream().filter(CensusData -> censusList.add(new CensusDAO((IndiaCensusCSV) CensusData))).collect(Collectors.toList());
            }
            else if(CensusCSVClass.getName().equals("censusanalyser.UsCensusCsv")) {
                csvFileList.stream().filter(CensusData -> censusList.add(new CensusDAO((UsCensusCsv) CensusData))).collect(Collectors.toList());

            }
            System.out.println(censusList.size());
            return censusList.size();
        }catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }

    }


    public int loadIndiastateDataCode(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<CSVStates> csvFileList = csvBuilder.getCSVFileList(reader, CSVStates.class);
            csvFileList.stream().filter(CensusData -> CSVStateList.add(new CSVStateDAO( CensusData))).collect(Collectors.toList());
            return CSVStateList.size();
        }catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }


    public String sortingIndiaCensusData() throws IOException, CSVBuilderException, JSONException, CensusAnalyserException {
        if(censusList == null || censusList.size()==0)
        {
            throw new CensusAnalyserException("NO Census Data",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List <CensusDAO> sortedList = censusList.stream().sorted(Comparator.comparing(CensusDAO::getState)).collect(Collectors.toList());
        String sortList = new Gson().toJson(sortedList);
        System.out.println(sortList);
        return sortList;
    }

    public String sortingIndiaStateCodeCSV() throws CensusAnalyserException {

        if(CSVStateList == null || CSVStateList.size()==0)
        {
            throw new CensusAnalyserException("NO Census Data",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List <CSVStateDAO> sortedList = CSVStateList.stream().sorted(Comparator.comparing(CSVStateDAO::getStateCode)).collect(Collectors.toList());
        String sortList = new Gson().toJson(sortedList);
        System.out.println(sortList);
        return sortList;
    }

    public String sortIndiaCensusPopulationWise() throws CensusAnalyserException {
        if(censusList == null || censusList.size()==0)
        {
            throw new CensusAnalyserException("NO Census Data",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List <CensusDAO> sortedList = censusList.stream().sorted(Comparator.comparing(CensusDAO::getPopulation).reversed()).collect(Collectors.toList());
        String sortList = new Gson().toJson(sortedList);
        System.out.println(sortList);
        return sortList;
    }

    public String sortIndiaCensusDensityWise() throws CensusAnalyserException {
        if(censusList == null || censusList.size()==0)
        {
            throw new CensusAnalyserException("NO Census Data",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List <CensusDAO> sortedList = censusList.stream().sorted(Comparator.comparing(CensusDAO::getDensityPerSqKm).reversed()).collect(Collectors.toList());
        String sortList = new Gson().toJson(sortedList);
        System.out.println(sortList);
        return sortList;
    }

    public String sortIndiaCensusAreaWise() throws CensusAnalyserException {
        if(censusList == null || censusList.size()==0)
        {
            throw new CensusAnalyserException("NO Census Data",
                    CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA);
        }
        List <CensusDAO> sortedList = censusList.stream().sorted(Comparator.comparing(CensusDAO::getAreaInSqKm).reversed()).collect(Collectors.toList());
        String sortList = new Gson().toJson(sortedList);
        System.out.println(sortList);
        return sortList;
    }




}