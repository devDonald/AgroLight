package smtagro.donald.com.models;

/**
 * Created by OBEYA on 4/2/2017.
 */

public class FarmersModel {
    private String _id;
    private String name;
    private String gender;
    private String age;
    private String marital_status;
    private String farm_size;
    private String household_size;
    private String phone_number;
    private String avg_income_non_farming;
    private String dry_season_business;
    private String avg_income_farming;
    private String distance_to_market;
    private String produce_storage_capacity;
    private String cooperative_membership;
    private String center_number;
    private String center_name;
    private String personal_serial_number;
    private String major_crops;
    private String major_livestock;
    private String inputs_and_quantities;
    private String bvn;
    private String passport;
    private String Transaction_id;
    private String Status;
    private String Center_num;
    private String unique_farmer_num;
    private String barcode;
    private String sync;


    public FarmersModel(String name, String gender, String age, String marital_status, String farm_size, String household_size, String phone_number, String avg_income_non_farming, String dry_season_business, String avg_income_farming, String distance_to_market, String produce_storage_capacity, String cooperative_membership, String center_number, String center_name, String personal_serial_number, String major_crops, String major_livestock, String inputs_and_quantities) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.marital_status = marital_status;
        this.farm_size = farm_size;
        this.household_size = household_size;
        this.phone_number = phone_number;
        this.avg_income_non_farming = avg_income_non_farming;
        this.dry_season_business = dry_season_business;
        this.avg_income_farming = avg_income_farming;
        this.distance_to_market = distance_to_market;
        this.produce_storage_capacity = produce_storage_capacity;
        this.cooperative_membership = cooperative_membership;
        this.center_number = center_number;
        this.center_name = center_name;
        this.personal_serial_number = personal_serial_number;
        this.major_crops = major_crops;
        this.major_livestock = major_livestock;
        this.inputs_and_quantities = inputs_and_quantities;
    }


    public FarmersModel() {

    }

    public String getID() {
        return _id;
    }

    public void setID(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDry_season_business() {
        return dry_season_business;
    }

    public void setDry_season_business(String dry_season_business) {
        this.dry_season_business = dry_season_business;
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

    public String getPersonal_serial_number() {
        return personal_serial_number;
    }

    public void setPersonal_serial_number(String personal_serial_number) {
        this.personal_serial_number = personal_serial_number;
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

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getTransaction_id() {
        return Transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        Transaction_id = transaction_id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCenter_num() {
        return Center_num;
    }

    public void setCenter_num(String center_num) {
        Center_num = center_num;
    }

    public String getUnique_farmer_num() {
        return unique_farmer_num;
    }

    public void setUnique_farmer_num(String unique_farmer_num) {
        this.unique_farmer_num = unique_farmer_num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getSync() {
        return sync;
    }

    public void setSync(String sync) {
        this.sync = sync;
    }

//    public String getRegistration_id() {
//        return registration_id;
//    }
//
//    public void setRegistration_id(String registration_id) {
//        this.registration_id = registration_id;
//    }
}
