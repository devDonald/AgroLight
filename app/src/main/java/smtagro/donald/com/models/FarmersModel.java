package smtagro.donald.com.models;


public class FarmersModel {
    private String names;
    private String gender;
    private String age,lga;
    private String marital_status;
    private String farm_size;
    private String household_size;
    private String phone_number;
    private String avg_income_non_farming;
    private String avg_income_farming;
    private String distance_to_market;
    private String produce_storage_capacity;
    private String cooperative_membership;
    private String center_number;
    private String center_name;
    private String major_crops;
    private String major_livestock;
    private String inputs_and_quantities;
    private String bvn,agentName;
    private String FIN;
    private String farmerImage,farmImage;

    public FarmersModel() {

    }

    public FarmersModel(String names, String gender, String age, String lga,
                        String marital_status, String farm_size, String household_size,
                        String phone_number, String avg_income_non_farming,
                        String avg_income_farming, String distance_to_market,
                        String produce_storage_capacity, String cooperative_membership,
                        String center_number, String center_name, String major_crops,
                        String major_livestock, String inputs_and_quantities, String bvn,
                        String agentName, String FIN, String farmerImage, String farmImage) {
        this.names = names;
        this.gender = gender;
        this.age = age;
        this.lga = lga;
        this.marital_status = marital_status;
        this.farm_size = farm_size;
        this.household_size = household_size;
        this.phone_number = phone_number;
        this.avg_income_non_farming = avg_income_non_farming;
        this.avg_income_farming = avg_income_farming;
        this.distance_to_market = distance_to_market;
        this.produce_storage_capacity = produce_storage_capacity;
        this.cooperative_membership = cooperative_membership;
        this.center_number = center_number;
        this.center_name = center_name;
        this.major_crops = major_crops;
        this.major_livestock = major_livestock;
        this.inputs_and_quantities = inputs_and_quantities;
        this.bvn = bvn;
        this.agentName = agentName;
        this.FIN = FIN;
        this.farmerImage = farmerImage;
        this.farmImage = farmImage;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getFarm_size() {
        return farm_size;
    }

    public void setFarm_size(String farm_size) {
        this.farm_size = farm_size;
    }

    public String getHousehold_size() {
        return household_size;
    }

    public void setHousehold_size(String household_size) {
        this.household_size = household_size;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAvg_income_non_farming() {
        return avg_income_non_farming;
    }

    public void setAvg_income_non_farming(String avg_income_non_farming) {
        this.avg_income_non_farming = avg_income_non_farming;
    }

    public String getAvg_income_farming() {
        return avg_income_farming;
    }

    public void setAvg_income_farming(String avg_income_farming) {
        this.avg_income_farming = avg_income_farming;
    }

    public String getDistance_to_market() {
        return distance_to_market;
    }

    public void setDistance_to_market(String distance_to_market) {
        this.distance_to_market = distance_to_market;
    }

    public String getProduce_storage_capacity() {
        return produce_storage_capacity;
    }

    public void setProduce_storage_capacity(String produce_storage_capacity) {
        this.produce_storage_capacity = produce_storage_capacity;
    }

    public String getCooperative_membership() {
        return cooperative_membership;
    }

    public void setCooperative_membership(String cooperative_membership) {
        this.cooperative_membership = cooperative_membership;
    }

    public String getCenter_number() {
        return center_number;
    }

    public void setCenter_number(String center_number) {
        this.center_number = center_number;
    }

    public String getCenter_name() {
        return center_name;
    }

    public void setCenter_name(String center_name) {
        this.center_name = center_name;
    }

    public String getMajor_crops() {
        return major_crops;
    }

    public void setMajor_crops(String major_crops) {
        this.major_crops = major_crops;
    }

    public String getMajor_livestock() {
        return major_livestock;
    }

    public void setMajor_livestock(String major_livestock) {
        this.major_livestock = major_livestock;
    }

    public String getInputs_and_quantities() {
        return inputs_and_quantities;
    }

    public void setInputs_and_quantities(String inputs_and_quantities) {
        this.inputs_and_quantities = inputs_and_quantities;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getFIN() {
        return FIN;
    }

    public void setFIN(String FIN) {
        this.FIN = FIN;
    }

    public String getFarmerImage() {
        return farmerImage;
    }

    public void setFarmerImage(String farmerImage) {
        this.farmerImage = farmerImage;
    }

    public String getFarmImage() {
        return farmImage;
    }

    public void setFarmImage(String farmImage) {
        this.farmImage = farmImage;
    }
}
