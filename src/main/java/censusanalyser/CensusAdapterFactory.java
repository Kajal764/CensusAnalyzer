package censusanalyser;

public class CensusAdapterFactory {
        public static <E> void getCensusData(CensusAnalyser.Country country, String... csvFilePath) throws CensusAnalyserException {
           /* if (country.equals(CensusAnalyser.Country.INDIA)) {
                return new CensusAdapter().loadCensusData(IndiaCensusCSV.class,csvFilePath);
            } else if (country.equals(CensusAnalyser.Country.US)) {
                return new CensusAdapter().combiningCensusData(USCensusCSV.class,csvFilePath);
            } else {
                throw new CensusAnalyserException("INCORRECT_COUNTRY", CensusAnalyserException.ExceptionType.INCORRECT_COUNTRY);
            }
        }*/
    }
}
