package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV<I> {

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public int areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public int densityPerSqKm;

    public IndiaCensusCSV(String state, double population, int densityPerSqKm, int areaInSqKm) {
        this.state=state;
        this.population = (int) population;
        this.densityPerSqKm = densityPerSqKm;
        this.areaInSqKm = areaInSqKm;
    }

    public IndiaCensusCSV() {
    }

//    public String getState() {
//        return state;
//    }

    @Override
    public String toString() {
        return "IndiaCensusCSV{" +
                "State='" + state + '\'' +
                ", Population='" + population + '\'' +
                ", AreaInSqKm='" + areaInSqKm + '\'' +
                ", DensityPerSqKm='" + densityPerSqKm + '\'' +
                '}';
    }
}
