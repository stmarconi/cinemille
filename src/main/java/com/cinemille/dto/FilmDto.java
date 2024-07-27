package com.cinemille.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class FilmDto {
    private String title;
    private String description;
    private Integer duration;
    private Map<Integer, LocalDateTime> screenings;

    public FilmDto(String title, String description,Integer duration, Map<Integer,LocalDateTime> dates) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.screenings = dates;
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

    public Map<Integer, LocalDateTime> getScreenings() {
        return screenings;
    }

    public void setScreenings(Map<Integer, LocalDateTime> screenings) {
        this.screenings = screenings;
    }
}
