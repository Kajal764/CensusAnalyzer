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
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<IndiaCensusCSV> csvFileList = csvBuilder.getCSVFileList(reader, IndiaCensusCSV.class);
            int i=0;
            while (i<csvFileList.size())
            {
                this.censusList.add(new CensusDAO(csvFileList.get(i)));
                i++;
            }
            return censusList.size();
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
            List<CSVStates> csvFileList = csvBuilder.getCSVFileList(reader, CSVStates.class);
            int i=0;
            while (i<csvFileList.size())
            {
                this.CSVStateList.add(new CSVStateDAO(csvFileList.get(i)));
                i++;
            }
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


        public int loadUsCensusData(String csvFilePath) throws CensusAnalyserException {
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                List<UsCensusCsv> csvFileList = csvBuilder.getCSVFileList(reader, UsCensusCsv.class);
                int i=0;
                while (i<csvFileList.size())
                {
                    this.censusList.add(new CensusDAO(csvFileList.get(i)));
                    i++;
                }
                return censusList.size();
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

}