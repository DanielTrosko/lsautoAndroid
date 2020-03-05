package it.danieltrosko.lsauto.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarPojo {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("mark")
    @Expose
    private String mark;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("engineDesignation")
    @Expose
    private String engineDesignation;
    @SerializedName("engineCode")
    @Expose
    private String engineCode;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("plateNumber")
    @Expose
    private String plateNumber;
    @SerializedName("chassisNumber")
    @Expose
    private String chassisNumber;
    @SerializedName("meterReading")
    @Expose
    private String meterReading;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
