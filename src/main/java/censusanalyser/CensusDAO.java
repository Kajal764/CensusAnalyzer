package censusanalyser;

public class CensusDAO {

    public String state;
    public String stateCode;
    public double population;
    public double areaInSqKm;
    public double densityPerSqKm;



    public String getState() {
        return state;
    }

    public double getPopulation() {
        return population;
    }

    public double getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public double getAreaInSqKm() {
        return areaInSqKm;
    }

    public CensusDAO(IndiaCensusCSV indiaCensusCSV)
    {
        state=indiaCensusCSV.state;
        population=indiaCensusCSV.population;
        areaInSqKm=indiaCensusCSV.areaInSqKm;
        densityPerSqKm=indiaCensusCSV.densityPerSqKm;
    }

    public CensusDAO(UsCensusCsv usCensusCsv) {

        state=usCensusCsv.state;
        stateCode=usCensusCsv.stateId;
        population=usCensusCsv.population;
        areaInSqKm=usCensusCsv.areaInSqKm;
        densityPerSqKm=usCensusCsv.densityPerSqKm;


    }
}
