package com.example.demo.entity;

public class Rank {
    private String schoolId;
    private String schoolName;
    private Integer labelTwo;
    private Integer labelNine;
    private Integer labelSelf;
    private Integer average;
    private String schoolCity;

    public String getSchoolCity() {
        return schoolCity;
    }

    public void setSchoolCity(String schoolCity) {
        this.schoolCity = schoolCity;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getLabelTwo() {
        return labelTwo;
    }

    public void setLabelTwo(Integer labelTwo) {
        this.labelTwo = labelTwo;
    }

    public Integer getLabelNine() {
        return labelNine;
    }

    public void setLabelNine(Integer labelNine) {
        this.labelNine = labelNine;
    }

    public Integer getLabelSelf() {
        return labelSelf;
    }

    public void setLabelSelf(Integer labelSelf) {
        this.labelSelf = labelSelf;
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }
}
