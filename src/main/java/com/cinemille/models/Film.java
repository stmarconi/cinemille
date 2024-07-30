package com.cinemille.models;

import java.time.LocalDate;

public class Film {
    private Integer id;
    private String title;
    private String description;
    private Integer duration;
    private LocalDate startDate;
    private Integer weeksNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getWeeksNumber() {
        return weeksNumber;
    }

    public void setWeeksNumber(Integer weeksNumber) {
        this.weeksNumber = weeksNumber;
    }
}
