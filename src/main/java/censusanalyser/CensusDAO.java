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

   /* public IndiaCensusCsv getIndiaCensusCsv(){
        return new IndiaCensusCSV(state,population,densityPerSqKm,areaInSqKm);
    }
public Object getCensusDTO(CensusAnalyser.Country country)
{
    if(country.equals(CensusAnalyser.Country.US))

}*/

    public String getStateCode() {
        return stateCode;
    }

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



}
