package censusanalyser;

import com.brideglabz.CSVBuilderException;
import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static censusanalyser.CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INDIA_CENSUS_FILE_PATH_WRONG = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/IndiaStateCensusData";
    private static final String INDIA_CENSUS_FILE_DATA_Del = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/IndiaCensusDataForDelimete.csv";
    private static final String CSV_HEADER_INCORRECT = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/CensusFileIncorrectHeader.csv";
    private static final String NULL_CSV_FIlE = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/NullFile.csv";

    //---->
    private static final String INDIA_STATECODE_CSV_FILE_PATH = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_STATECODE_CSV_FILE_PATH = "/home/admin1/Desktop/CensusAnalyser/src/main/resources/IndiaStateCode.csv";
    private static final String INDIA_STATECODE_FILE_PATH_WRONG = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/IndiaStateCode";
    private static final String INDIA_STATECODE_FILE_DATA = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/StateCode4.csv";
    private static final String INDIA_STATECODE_CSV_HEADER_INCORRECT = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/CSVCode5.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        int numOfRecords = 0;
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
            System.out.println(numOfRecords);
        } catch (CensusAnalyserException e) {
            //System.out.println(numOfRecords);
            e.printStackTrace();
        }


    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CENSUS_FILE_PROBLEM, e.type);
        }
    }


    @Test
    public void whenGivenCsvFileCorrectButTypeIncorrect_ShouldThrowCustomExce() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_FILE_PATH_WRONG);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CENSUS_FILE_PROBLEM, e.type);
        }

    }

    @Test
    public void whenGivenCSVFileInCorrectDelimeter_ShouldThrowCustomeException() {
        int numOfRecords = 0;
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_FILE_DATA_Del);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
            System.out.println(numOfRecords);
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA, e.type);
        }

    }

    @Test
    public void WhenGivenCSVFileHeaderIncorrect_ShouldThrowCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(CSV_HEADER_INCORRECT);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA, e.type);
        }
    }

// ----->
    @Test
    public void givenIndianStateCodeCSVFileReturnsCorrectRecords() {
        int numOfRecords = 0;
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            numOfRecords = censusAnalyser.loadIndiaCensusDataCode(INDIA_STATECODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
            System.out.println(numOfRecords);
        } catch (CensusAnalyserException e) {
            System.out.println(numOfRecords);
        }
    }


    @Test
    public void givenIndiaStatecodeData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusDataCode(WRONG_STATECODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CENSUS_FILE_PROBLEM, e.type);
        }
    }


    @Test
    public void whenGivenCsvStateCodeFileCorrectButTypeIncorrect_ShouldThrowCustomExce() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusDataCode(INDIA_STATECODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void whenGivenCSVStateCodeFileInCorrectDelimeter_ShouldThrowCustomeException() {
        int numOfRecords = 0;
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_STATECODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {
            System.out.println(numOfRecords);
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA, e.type);
        }
    }

    @Test
    public void WhenGivenCSVStateCodeFileHeaderIncorrect_ShouldThrowCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_STATECODE_CSV_HEADER_INCORRECT);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA, e.type);
        }
    }

    //------->SortedFunctionTestcase
    @Test
    public void WhenReturnSortedListFirstEntry_ShouldReturnTrue() throws CensusAnalyserException, IOException, CSVBuilderException, JSONException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortingIndiaCensusData();
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals(true, indiaCensusCSV[0].state.contains("Andhra Pradesh"));
    }

    @Test
    public void WhenReturnSortedListLastEntry_ShouldReturnTrue() throws CensusAnalyserException, IOException, CSVBuilderException, JSONException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortingIndiaCensusData();
        IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals(true, indiaCensusCSV[28].state.contains("West Bengal"));
    }

    @Test
    public void whenLoadClassDataIsNull_ShouldThowCustomException() throws JSONException, IOException, CSVBuilderException, CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(NULL_CSV_FIlE);
            String censusCSVList = censusAnalyser.sortingIndiaCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
            Assert.assertEquals(true, indiaCensusCSV[0].state.contains("Andhra Pradesh"));
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void WhenReturnSortedCSVStateListFirstEntry_ShouldReturnTrue() throws CensusAnalyserException, IOException, CSVBuilderException, JSONException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusDataCode(INDIA_STATECODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortingIndiaStateCodeCSV();
        CSVStates[] indiaStateCSV = new Gson().fromJson(censusCSVList, CSVStates[].class);
        System.out.println(indiaStateCSV[0]);
        Assert.assertEquals(true, indiaStateCSV[0].StateCode.contains("AD"));
    }

    @Test
    public void WhenReturnSortedCSVStateListLastEntry_ShouldReturnTrue() throws CensusAnalyserException, IOException, CSVBuilderException, JSONException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusDataCode(INDIA_STATECODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortingIndiaStateCodeCSV();
        CSVStates[] indiaStateCSV = new Gson().fromJson(censusCSVList, CSVStates[].class);
        System.out.println(indiaStateCSV[36]);
        Assert.assertEquals(true, indiaStateCSV[36].StateCode.contains("WB"));
    }

    @Test
    public void WhenReturnSortedDataBasedOnPopulationFirstEntry_ShouldReturnTrue() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortIndiaCensusPopulationWise();
        IndiaCensusCSV[] indiaStateCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        System.out.println(indiaStateCSV[0]);
        Assert.assertEquals(199812341,indiaStateCSV[0].population);
    }

    @Test
    public void WhenReturnSortedDataBasedOnPopulationLastEntry_ShouldReturnTrue() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortIndiaCensusPopulationWise();
        IndiaCensusCSV[] indiaStateCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        System.out.println(indiaStateCSV[28]);
        Assert.assertEquals(607688,indiaStateCSV[28].population);
    }

    @Test
    public void whenReturnSortedDataBasedOnDensity_ShouldReturnTrue() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortIndiaCensusDensityWise();
        IndiaCensusCSV[] indiaStateCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals(50,indiaStateCSV[28].densityPerSqKm);
        Assert.assertEquals(1102,indiaStateCSV[0].densityPerSqKm);

    }

    @Test
    public void whenReturnSortedDataBasedOnArea_ShouldReturnTrue() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortIndiaCensusAreaWise();
        IndiaCensusCSV[] indiaStateCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals(342239,indiaStateCSV[0].areaInSqKm);
        Assert.assertEquals(3702,indiaStateCSV[28].areaInSqKm);

    }









}
