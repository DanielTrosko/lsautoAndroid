package it.danieltrosko.lsauto.model;

import com.google.gson.annotations.SerializedName;

public class Car {
    private String mark;
    private String model;
    private String engineDesignation;
    private String year;
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

    @Override
    public String toString() {
        return "Car{" +
                "mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", engineDesignation='" + engineDesignation + '\'' +
                ", year='" + year + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                '}';
    }
}
