package censusanalyser;

import com.brideglabz.CSVBuilderException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static censusanalyser.CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INDIA_CENSUS_FILE_PATH_WRONG = "./src/test/resources/IndiaStateCensusData";
    private static final String INDIA_CENSUS_FILE_DATA = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/IndiaCensusData.csv";
    private static final String CSV_HEADER_INCORRECT = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/CensusFileIncorrectHeader.csv";

    //---->
    private static final String INDIA_STATECODE_CSV_FILE_PATH = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_STATECODE_CSV_FILE_PATH = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/IndiaStateCode.csv";
    private static final String INDIA_STATECODE_FILE_PATH_WRONG = "/home/admin1/Desktop/CensusAnalyser/src/test/resources/IndiaStateCode";
    private static final String INDIA_STATECODE_FILE_DATA="/home/admin1/Desktop/CensusAnalyser/src/test/resources/StateCode4.csv";
    private static final String INDIA_STATECODE_CSV_HEADER_INCORRECT="/home/admin1/Desktop/CensusAnalyser/src/test/resources/CSVCode5.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
            System.out.println(numOfRecords);
        } catch (CensusAnalyserException e) {
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
            numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_FILE_DATA);
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
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusDataCode(INDIA_STATECODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
            System.out.println(numOfRecords);
        } catch (CensusAnalyserException e) {
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

   @Test
    public void WhenGivenSortedListFirstEntry_ShouldReturnTrue() throws CensusAnalyserException, IOException, CSVBuilderException {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int firstIndex=1;
            String s = censusAnalyser.sortingIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH,firstIndex);
            String s1 = "IndiaCensusCSV{state='Andhra Pradesh', population=49386799, areaInSqKm=162968, densityPerSqKm=303}";
            Assert.assertEquals(s1, s);

    }
    @Test
    public void WhenGivenSortedListLastEntry_ShouldReturnTrue() throws CensusAnalyserException, IOException, CSVBuilderException {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        int lastIndex=2;
        String s = censusAnalyser.sortingIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH,lastIndex);
        String s1 = "IndiaCensusCSV{state='West Bengal', population=91347736, areaInSqKm=88752, densityPerSqKm=1029}";
        Assert.assertEquals(s1,s);

    }

}