package it.danieltrosko.lsauto.model;


public class CarAcceptance {

    private Long carId;
    private String mark;
    private String model;
    private String engineDesignation;
    private String engineCode;
    private String year;
    private String plateNumber;
    private String chassisNumber;
    private String meterReading;

    //repair
    private String scopeOfWork;
    private String faultsReportedByCustomer;
    private String estimatedRepairPrice;
    private String status;
    private String dataOfPickup;


    //user
    private String email;
    private String firstName;
    private String surname;
    private String phoneNumber;

    //address
    private Long addressId;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String postCode;
    private String city;

    public CarAcceptance() {
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngineDesignation() {
        return engineDesignation;
    }

    public void setEngineDesignation(String engineDesignation) {
        this.engineDesignation = engineDesignation;
    }

    public String getEngineCode() {
        return engineCode;
    }

    public void setEngineCode(String engineCode) {
        this.engineCode = engineCode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(String meterReading) {
        this.meterReading = meterReading;
    }

    public String getScopeOfWork() {
        return scopeOfWork;
    }

    public void setScopeOfWork(String scopeOfWork) {
        this.scopeOfWork = scopeOfWork;
    }

    public String getFaultsReportedByCustomer() {
        return faultsReportedByCustomer;
    }

    public void setFaultsReportedByCustomer(String faultsReportedByCustomer) {
        this.faultsReportedByCustomer = faultsReportedByCustomer;
    }

    public String getEstimatedRepairPrice() {
        return estimatedRepairPrice;
    }

    public void setEstimatedRepairPrice(String estimatedRepairPrice) {
        this.estimatedRepairPrice = estimatedRepairPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataOfPickup() {
        return dataOfPickup;
    }

    public void setDataOfPickup(String dataOfPickup) {
        this.dataOfPickup = dataOfPickup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
