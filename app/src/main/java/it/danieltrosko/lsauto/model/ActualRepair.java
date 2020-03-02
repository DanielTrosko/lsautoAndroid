package it.danieltrosko.lsauto.model;


public class ActualRepair {
    private Long id;
    private String dateOfAdmission;
    private String estimatedRepairPrice;
    private String mark;
    private String model;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
