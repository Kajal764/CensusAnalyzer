package censusanalyser;

public class UsCensusDAO {

    public String State;
    public int Population;
    public int HousingUnits;
    public double TotalArea;
    public double WaterArea;
    public double LandArea;
    public double PopulationDensity;
    public double HousingDensity;
    public String StateId;

    public UsCensusDAO(UsCensusCsv usCensusCsv) {
        StateId=usCensusCsv.stateId;
        State=usCensusCsv.state;
        Population=usCensusCsv.population;
        HousingUnits=usCensusCsv.housingunits;
        TotalArea=usCensusCsv.totalarea;
        WaterArea=usCensusCsv.waterarea;
        LandArea=usCensusCsv.landarea;
        PopulationDensity=usCensusCsv.populationDensity;
        HousingDensity=usCensusCsv.housingDensity;


    }
}
