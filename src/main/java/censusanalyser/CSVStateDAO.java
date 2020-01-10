package censusanalyser;


public class CSVStateDAO {
    private int TIN;
    private String StateCode;
    private int SrNo;
    private String StateName;

    public String getStateCode() {
        return StateCode;
    }

    public CSVStateDAO(CSVStates csvStates) {
        SrNo=csvStates.SrNo;
        StateName=csvStates.StateName;
        TIN=csvStates.TIN;
        StateCode=csvStates.StateCode;
    }
}