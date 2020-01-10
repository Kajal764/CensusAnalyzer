package censusanalyser;


public class CSVStateDAO {
    private int tin;
    private String StateCode;
    private int srNo;
    private String stateName;


    public String getStateCode() {
        return StateCode;
    }

    public CSVStateDAO(CSVStates csvStates) {
        srNo=csvStates.SrNo;
        stateName=csvStates.StateName;
        tin=csvStates.TIN;
        StateCode=csvStates.StateCode;
    }
}