package censusanalyser;

public class CensusDAO {

    public String state;
    public String stateCode;
    public double population;
    public double areaInSqKm;
    public double densityPerSqKm;

    public CensusDAO(IndiaCensusCSV indiaCensusCSV)
    {
        state=indiaCensusCSV.state;
        population=indiaCensusCSV.population;
        areaInSqKm=indiaCensusCSV.areaInSqKm;
        densityPerSqKm=indiaCensusCSV.densityPerSqKm;
    }

    public CensusDAO(UsCensusCsv usCensusCsv) {

        state=usCensusCsv.state;
        stateCode =usCensusCsv.stateCode;
        population=usCensusCsv.population;
        areaInSqKm=usCensusCsv.areaInSqKm;
        densityPerSqKm=usCensusCsv.densityPerSqKm;

    }

    public Object getCensusDTO(CensusAnalyser.Country country)
    {
    if(country.equals(CensusAnalyser.Country.US))
        return new UsCensusCsv(state,stateCode,population,areaInSqKm,densityPerSqKm);
    return new IndiaCensusCSV(state,population,(int)densityPerSqKm,(int)areaInSqKm);

    }



}
