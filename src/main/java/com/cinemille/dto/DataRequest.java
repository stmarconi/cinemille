package com.cinemille.dto;

public class DataRequest {

    private String startDate;
    private String endDate;

    public DataRequest() { }

    public String getStartDate(){
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
