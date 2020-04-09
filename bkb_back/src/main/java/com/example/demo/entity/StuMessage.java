package com.example.demo.entity;

public class StuMessage {
    private String stuNumber;
    private String stuName;
    private String stuSex;

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public void setStuName(String stuName) { this.stuName = stuName; }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public String getStuName() {
        return stuName;
    }

    public String getStuSex() {
        return stuSex;
    }
}
