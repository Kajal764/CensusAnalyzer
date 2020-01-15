package censusanalyser;

import com.brideglabz.CSVBuilderException;
import com.brideglabz.CSVBuilderFactory;
import com.brideglabz.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter {

    Map<String, CensusDAO> censusMap=new TreeMap<>();

    public <E> Map<String, CensusDAO> loadCensusData(Class<E> CensusCSVClass, String... csvFilePath) throws CensusAnalyserException {

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0])))
        {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<E> csvFileList = csvBuilder.getCSVFileList(reader, CensusCSVClass);
            if(CensusCSVClass.getName().equals("censusanalyser.IndiaCensusCSV")) {
                StreamSupport.stream(csvFileList.spliterator(),false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(CensusCSV ->censusMap.put(CensusCSV.state,new CensusDAO(CensusCSV)));
            }
            if(CensusCSVClass.getName().equals("censusanalyser.UsCensusCsv")) {
                StreamSupport.stream(csvFileList.spliterator(),false)
                        .map(UsCensusCsv.class::cast)
                        .forEach(CensusCSV ->censusMap.put(CensusCSV.state,new CensusDAO(CensusCSV)));
            }

            if(csvFilePath.length==1) return censusMap;
            this.loadIndiastateDataCode(csvFilePath[1],censusMap);
            return censusMap;
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

    public <E> Map<String, CensusDAO> loadIndiastateDataCode(String csvFilePath, Map<String,CensusDAO> censusStatemap) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<CSVStates> csvFileList = csvBuilder.getCSVFileList(reader, CSVStates.class);

            StreamSupport.stream(csvFileList.spliterator(),false)
                    .filter(CSVStateList->censusMap.get(CSVStateList.StateName)!=null)
                    .forEach(CensusData ->censusMap.get(CensusData.StateName).stateCode=CensusData.StateCode);
            return censusMap;
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



}
