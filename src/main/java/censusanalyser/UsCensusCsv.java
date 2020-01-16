package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class UsCensusCsv {


    @CsvBindByName(column = "State",required = true)
    public String state;

    @CsvBindByName(column = "Population",required = true)
    public int population;

    @CsvBindByName(column = "TotalArea" ,required= true)
    public double areaInSqKm;

    @CsvBindByName(column = "PopulationDensity",required = true)
    public double densityPerSqKm;

    @CsvBindByName(column ="StateId")
    public String stateCode;

    public UsCensusCsv(String state, String stateCode, double population, double areaInSqKm, double densityPerSqKm) {
        this.state =state;
        this.stateCode = stateCode;
        this.population = (int) population;
        this.areaInSqKm = areaInSqKm;
        this.densityPerSqKm = densityPerSqKm;

    }

    public UsCensusCsv() {
    }

    @Override
    public String toString() {
        return "UsCensusCsv{" +
                "state='" + state + '\'' +
                ", population=" + population +
                ", areaInSqKm=" + areaInSqKm +
                ", densityPerSqKm=" + densityPerSqKm +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}
