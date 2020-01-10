package censusanalyser;

public class IndiaCensusDAO {
    public String state;
    public int population;
    public int areaInSqKm;
    public int densityPerSqKm;

    public String getState() {
        return state;
    }

    public int getPopulation() {
        return population;
    }

    public int getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV)
    {
        state=indiaCensusCSV.state;
        population=indiaCensusCSV.population;
        areaInSqKm=indiaCensusCSV.population;
        densityPerSqKm=indiaCensusCSV.densityPerSqKm;
    }

}
