package smtagro.donald.com.models;

public class FarmingModel {

    private String FIN,agentID,dateOfPlanting,expectedHarvestDate,dateOfFirstFertApplication;
    private String farmerName,typeOfCrop,agentName,dateOfNextFertApplication,inputAndQuantities;
    private String farmDanger;

    public FarmingModel() {
    }

    public FarmingModel(String FIN, String agentID, String dateOfPlanting,
                        String expectedHarvestDate, String dateOfFirstFertApplication,
                        String farmerName, String typeOfCrop, String agentName,
                        String dateOfNextFertApplication, String inputAndQuantities,
                        String farmDanger) {
        this.FIN = FIN;
        this.agentID = agentID;
        this.dateOfPlanting = dateOfPlanting;
        this.expectedHarvestDate = expectedHarvestDate;
        this.dateOfFirstFertApplication = dateOfFirstFertApplication;
        this.farmerName = farmerName;
        this.typeOfCrop = typeOfCrop;
        this.agentName = agentName;
        this.dateOfNextFertApplication = dateOfNextFertApplication;
        this.inputAndQuantities = inputAndQuantities;
        this.farmDanger = farmDanger;
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

    public String getDateOfPlanting() {
        return dateOfPlanting;
    }

    public void setDateOfPlanting(String dateOfPlanting) {
        this.dateOfPlanting = dateOfPlanting;
    }

    public String getExpectedHarvestDate() {
        return expectedHarvestDate;
    }

    public void setExpectedHarvestDate(String expectedHarvestDate) {
        this.expectedHarvestDate = expectedHarvestDate;
    }

    public String getDateOfFirstFertApplication() {
        return dateOfFirstFertApplication;
    }

    public void setDateOfFirstFertApplication(String dateOfFirstFertApplication) {
        this.dateOfFirstFertApplication = dateOfFirstFertApplication;
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

    public String getDateOfNextFertApplication() {
        return dateOfNextFertApplication;
    }

    public void setDateOfNextFertApplication(String dateOfNextFertApplication) {
        this.dateOfNextFertApplication = dateOfNextFertApplication;
    }

    public String getInputAndQuantities() {
        return inputAndQuantities;
    }

    public void setInputAndQuantities(String inputAndQuantities) {
        this.inputAndQuantities = inputAndQuantities;
    }

    public String getFarmDanger() {
        return farmDanger;
    }

    public void setFarmDanger(String farmDanger) {
        this.farmDanger = farmDanger;
    }
}
