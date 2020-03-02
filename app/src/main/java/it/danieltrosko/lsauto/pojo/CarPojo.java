package it.danieltrosko.lsauto.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarPojo {

    @SerializedName("mark")
    @Expose
    private String mark;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("engineDesignation")
    @Expose
    private String engineDesignation;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("plateNumber")
    @Expose
    private String plateNumber;

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
}
