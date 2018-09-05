package smtagro.donald.com.models;


public class FarmersModel {
    private String names;
    private String gender;
    private String age,lga,state;
    private String marital_status;
    private String farm_size;
    private String household_size;
    private String phone_number;
    private String avg_income_non_farming;
    private String avg_income_farming;
    private String distance_to_market, bank_name;
    private String account_name, account_number,training_attended,training_type;
    private String cooperative_membership,cooperative_location,cooperative_chairman;
    private String federal_ward;
    private String village,highestQualification,modeOfIdentification,identificationImage;
    private String farmLocation,farmer_existance,major_crops;
    private String major_livestock;
    private String bvn,agentName;
    private String FIN;
    private String farmerImage;
    private Double longitude,latitude;

    public FarmersModel() {

    }

    public FarmersModel(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public FarmersModel(String names, String gender, String age, String lga, String state,
                        String marital_status, String farm_size, String household_size,
                        String phone_number, String avg_income_non_farming,
                        String avg_income_farming, String distance_to_market, String bank_name,
                        String account_name, String account_number, String training_attended,
                        String training_type, String cooperative_membership,
                        String cooperative_location, String cooperative_chairman,
                        String federal_ward, String village, String highestQualification,
                        String modeOfIdentification, String identificationImage,
                        String farmLocation, String farmer_existance, String major_crops,
                        String major_livestock, String bvn, String agentName, String FIN,
                        String farmerImage) {
        this.names = names;
        this.gender = gender;
        this.age = age;
        this.lga = lga;
        this.state = state;
        this.marital_status = marital_status;
        this.farm_size = farm_size;
        this.household_size = household_size;
        this.phone_number = phone_number;
        this.avg_income_non_farming = avg_income_non_farming;
        this.avg_income_farming = avg_income_farming;
        this.distance_to_market = distance_to_market;
        this.bank_name = bank_name;
        this.account_name = account_name;
        this.account_number = account_number;
        this.training_attended = training_attended;
        this.training_type = training_type;
        this.cooperative_membership = cooperative_membership;
        this.cooperative_location = cooperative_location;
        this.cooperative_chairman = cooperative_chairman;
        this.federal_ward = federal_ward;
        this.village = village;
        this.highestQualification = highestQualification;
        this.modeOfIdentification = modeOfIdentification;
        this.identificationImage = identificationImage;
        this.farmLocation = farmLocation;
        this.farmer_existance = farmer_existance;
        this.major_crops = major_crops;
        this.major_livestock = major_livestock;
        this.bvn = bvn;
        this.agentName = agentName;
        this.FIN = FIN;
        this.farmerImage = farmerImage;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getTraining_attended() {
        return training_attended;
    }

    public void setTraining_attended(String training_attended) {
        this.training_attended = training_attended;
    }

    public String getTraining_type() {
        return training_type;
    }

    public void setTraining_type(String training_type) {
        this.training_type = training_type;
    }

    public String getCooperative_membership() {
        return cooperative_membership;
    }

    public void setCooperative_membership(String cooperative_membership) {
        this.cooperative_membership = cooperative_membership;
    }

    public String getCooperative_location() {
        return cooperative_location;
    }

    public void setCooperative_location(String cooperative_location) {
        this.cooperative_location = cooperative_location;
    }

    public String getCooperative_chairman() {
        return cooperative_chairman;
    }

    public void setCooperative_chairman(String cooperative_chairman) {
        this.cooperative_chairman = cooperative_chairman;
    }

    public String getFederal_ward() {
        return federal_ward;
    }

    public void setFederal_ward(String federal_ward) {
        this.federal_ward = federal_ward;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getHighestQualification() {
        return highestQualification;
    }

    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }

    public String getModeOfIdentification() {
        return modeOfIdentification;
    }

    public void setModeOfIdentification(String modeOfIdentification) {
        this.modeOfIdentification = modeOfIdentification;
    }

    public String getIdentificationImage() {
        return identificationImage;
    }

    public void setIdentificationImage(String identificationImage) {
        this.identificationImage = identificationImage;
    }

    public String getFarmLocation() {
        return farmLocation;
    }

    public void setFarmLocation(String farmLocation) {
        this.farmLocation = farmLocation;
    }

    public String getFarmer_existance() {
        return farmer_existance;
    }

    public void setFarmer_existance(String farmer_existance) {
        this.farmer_existance = farmer_existance;
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
