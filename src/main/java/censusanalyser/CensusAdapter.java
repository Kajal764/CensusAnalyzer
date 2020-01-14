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
import java.util.stream.StreamSupport;

public abstract class CensusAdapter {

    public abstract <E> Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException;


}
