package censusanalyser;

import com.brideglabz.CSVBuilderException;
import com.brideglabz.CSVBuilderFactory;
import com.brideglabz.ICSVBuilder;

import java.awt.*;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CensusLoader {
    List<CensusDAO> censusList=new ArrayList<>();

    public <E> List<CensusDAO> loadCensusData(String csvFilePath, Class<E> CensusCSVClass) throws CensusAnalyserException {

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<E> csvFileList = csvBuilder.getCSVFileList(reader, CensusCSVClass);
            if(CensusCSVClass.getName().equals("censusanalyser.IndiaCensusCSV")) {
                csvFileList.stream().filter(CensusData -> censusList.add(new CensusDAO((IndiaCensusCSV) CensusData))).collect(Collectors.toList());
            }
            else if(CensusCSVClass.getName().equals("censusanalyser.UsCensusCsv")) {
                csvFileList.stream().filter(CensusData -> censusList.add(new CensusDAO((UsCensusCsv) CensusData))).collect(Collectors.toList());

            }
            System.out.println(censusList.size());
            return censusList;
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
