package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class UsCensusCsv {

    @CsvBindByName(column = "StateId",required = true)
    public String stateId;

    @CsvBindByName(column = "State",required = true)
    public String state;

    @CsvBindByName(column = "Population",required = true)
    public int population;

    @CsvBindByName(column = "HousingUnits",required = true)
    public int housingunits;

    @CsvBindByName(column = "TotalArea" ,required= true)
    public double totalarea;

    @CsvBindByName(column = "WaterArea",required = true)
    public double waterarea;

    @CsvBindByName(column = "LandArea",required = true)
    public double landarea;

    @CsvBindByName(column = "PopulationDensity",required = true)
    public double populationDensity;

    @CsvBindByName(column = "HousingDensity",required = true)
    public double housingDensity;


    @Override
    public String toString() {
        return "UsCensusCsv{" +
                "stateId='" + stateId + '\'' +
                ", state='" + state + '\'' +
                ", population=" + population +
                ", housingunits=" + housingunits +
                ", totalarea=" + totalarea +
                ", waterarea=" + waterarea +
                ", landarea=" + landarea +
                ", populationDensity=" + populationDensity +
                ", housingDensity=" + housingDensity +
                '}';
    }
}
