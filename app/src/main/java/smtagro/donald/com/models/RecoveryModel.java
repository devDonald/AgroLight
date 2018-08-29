package smtagro.donald.com.models;

public class RecoveryModel {
    private String FIN,agentID,dateOfHervest,noOfHectares,offTakeDate;
    private String offTakeCenter,reportDate,farmerName,typeOfCrop,agentName;

    public RecoveryModel() {
    }

    public RecoveryModel(String FIN, String agentID, String dateOfHervest,
                         String noOfHectares, String offTakeDate, String offTakeCenter,
                         String reportDate,
                         String farmerName, String typeOfCrop, String agentName) {
        this.FIN = FIN;
        this.agentID = agentID;
        this.dateOfHervest = dateOfHervest;
        this.noOfHectares = noOfHectares;
        this.offTakeDate = offTakeDate;
        this.offTakeCenter = offTakeCenter;
        this.reportDate = reportDate;
        this.farmerName = farmerName;
        this.typeOfCrop = typeOfCrop;
        this.agentName = agentName;
    }

    public String getFIN() {
        return FIN;
    }

    public void setFIN(String FIN) {
        this.FIN = FIN;
    }

    public String getAgentID() {
        return agentID;
    }

    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }

    public String getDateOfHervest() {
        return dateOfHervest;
    }

    public void setDateOfHervest(String dateOfHervest) {
        this.dateOfHervest = dateOfHervest;
    }

    public String getNoOfHectares() {
        return noOfHectares;
    }

    public void setNoOfHectares(String noOfHectares) {
        this.noOfHectares = noOfHectares;
    }

    public String getOffTakeDate() {
        return offTakeDate;
    }

    public void setOffTakeDate(String offTakeDate) {
        this.offTakeDate = offTakeDate;
    }

    public String getOffTakeCenter() {
        return offTakeCenter;
    }

    public void setOffTakeCenter(String offTakeCenter) {
        this.offTakeCenter = offTakeCenter;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getTypeOfCrop() {
        return typeOfCrop;
    }

    public void setTypeOfCrop(String typeOfCrop) {
        this.typeOfCrop = typeOfCrop;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
}
