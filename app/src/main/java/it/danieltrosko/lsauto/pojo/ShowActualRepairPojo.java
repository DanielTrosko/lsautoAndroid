package it.danieltrosko.lsauto.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowActualRepairPojo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("dateOfAdmission")
    @Expose
    private String dateOfAdmission;
    @SerializedName("estimatedRepairPrice")
    @Expose
    private String estimatedRepairPrice;
    @SerializedName("mark")
    @Expose
    private String mark;
    @SerializedName("model")
    @Expose
    private String model;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(String dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public String getEstimatedRepairPrice() {
        return estimatedRepairPrice;
    }

    public void setEstimatedRepairPrice(String estimatedRepairPrice) {
        this.estimatedRepairPrice = estimatedRepairPrice;
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
}
