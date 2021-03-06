package censusanalyser;

import com.brideglabz.CSVBuilderException;
import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static censusanalyser.CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM;

public class CensusAnalyserTest  {

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
    private static final String US_CENSUS_DATA = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/USCensusData.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        int numOfRecords = 0;
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            numOfRecords = censusAnalyser.loadCensusData( INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
            System.out.println(numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData( WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CENSUS_FILE_PROBLEM, e.type);
        }
    }


    @Test
    public void whenGivenCsvFileCorrectButTypeIncorrect_ShouldThrowCustomExce() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData( INDIA_CENSUS_FILE_PATH_WRONG);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void whenGivenCSVFileInCorrectDelimeter_ShouldThrowCustomeException() {
        int numOfRecords = 0;
        try
        {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            numOfRecords=censusAnalyser.loadCensusData(INDIA_CENSUS_FILE_DATA_Del);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e)
        {
            System.out.println(numOfRecords);
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA, e.type);
        }
    }

    @Test
    public void WhenGivenCSVFileHeaderIncorrect_ShouldThrowCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CSV_HEADER_INCORRECT);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA, e.type);
        }
    }

    // ----->
    @Test
    public void givenIndianCSVFileReturnsCommonCorrectRecords() {
        int numOfRecords = 0;
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            numOfRecords = censusAnalyser.loadCensusData( INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
            System.out.println(numOfRecords);
        } catch (CensusAnalyserException e) {
            System.out.println(numOfRecords);
        }
    }



    @Test
    public void givenIndiaStatecodeData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_STATECODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CENSUS_FILE_PROBLEM, e.type);
        }
    }


    @Test
    public void whenGivenCsvStateCodeFileCorrectButTypeIncorrect_ShouldThrowCustomExce() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INDIA_STATECODE_FILE_PATH_WRONG );
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void whenGivenCSVStateCodeFileInCorrectDelimeter_ShouldThrowCustomeException() {
        int numOfRecords = 0;
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            numOfRecords=censusAnalyser.loadCensusData(INDIA_STATECODE_FILE_DATA);
            Assert.assertEquals(37, numOfRecords);
            System.out.println(numOfRecords);
        } catch (CensusAnalyserException e) {
            System.out.println(numOfRecords);
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA, e.type);
        }
    }


    //------->SortedFunctionTestcase


    @Test
    public void WhenReturnSortedCSVStateListFirstEntry_ShouldReturnTrue() throws CensusAnalyserException, IOException, CSVBuilderException, JSONException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortingIndiaStateCodeCSV();
        CensusDAO[] indiaStateCSV = new Gson().fromJson(censusCSVList, CensusDAO[].class);
        System.out.println(indiaStateCSV[0]);
       Assert.assertEquals(true,indiaStateCSV[0].state.contains("Andhra Pradesh"));
    }

    @Test
    public void WhenReturnSortedCSVStateListLastEntry_ShouldReturnTrue() throws CensusAnalyserException, IOException, CSVBuilderException, JSONException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortingIndiaStateCodeCSV();
        CensusDAO[] indiaStateCSV = new Gson().fromJson(censusCSVList, CensusDAO[].class);
        System.out.println(indiaStateCSV[28]);
        Assert.assertEquals(true, indiaStateCSV[28].state.contains("West Bengal"));
    }

    @Test
    public void WhenReturnSortedDataBasedOnStateFirstEntry_ShouldReturnTrue() throws CensusAnalyserException, JSONException, IOException, CSVBuilderException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortingIndiaStateCodeCSV();
        IndiaCensusCSV[] indiaStateCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        System.out.println(indiaStateCSV[0]);
        Assert.assertEquals(true, indiaStateCSV[0].state.contains("Andhra Pradesh"));
    }


    @Test
    public void WhenReturnSortedDataBasedOnPopulationFirstEntry_ShouldReturnTrue() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortIndiaCensusPopulationWise();
        IndiaCensusCSV[] indiaStateCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        System.out.println(indiaStateCSV[0]);
        Assert.assertEquals(199812341,indiaStateCSV[0].population);
    }

    @Test
    public void WhenReturnSortedDataBasedOnPopulationLastEntry_ShouldReturnTrue() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortIndiaCensusPopulationWise();
        IndiaCensusCSV[] indiaStateCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        System.out.println(indiaStateCSV[28]);
        Assert.assertEquals(607688,indiaStateCSV[28].population);
    }

    @Test
    public void whenReturnSortedDataBasedOnDensity_ShouldReturnTrue() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortIndiaCensusDensityWise();
        IndiaCensusCSV[] indiaStateCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals(50,indiaStateCSV[28].densityPerSqKm);
        Assert.assertEquals(1102,indiaStateCSV[0].densityPerSqKm);

    }

    @Test
    public void whenReturnSortedDataBasedOnArea_ShouldReturnTrue() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
        String censusCSVList = censusAnalyser.sortIndiaCensusAreaWise();
        IndiaCensusCSV[] indiaStateCSV = new Gson().fromJson(censusCSVList, IndiaCensusCSV[].class);
        Assert.assertEquals(342239,indiaStateCSV[0].areaInSqKm);
        Assert.assertEquals(3702,indiaStateCSV[28].areaInSqKm);

    }


    @Test
    public void givenUSCensusCSVFileReturnsCorrectRecords() {
        int numOfRecords = 0;
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            numOfRecords = censusAnalyser.loadCensusData(US_CENSUS_DATA);;
            Assert.assertEquals(51, numOfRecords);
            System.out.println(numOfRecords);
        } catch (CensusAnalyserException e) {
            System.out.println(numOfRecords);
            e.printStackTrace();
        }


    }


}
