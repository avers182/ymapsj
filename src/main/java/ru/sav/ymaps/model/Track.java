package ru.sav.ymaps.model;

import java.util.Date;

public class Track {
    private Long id;
    private Float distance;
    private Date startTime;
    private Date endTime;
    private String startTimeS;
    private String endTimeS;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTimeS() {
        return startTimeS;
    }

    public void setStartTimeS(String startTimeS) {
        this.startTimeS = startTimeS;
    }

    public String getEndTimeS() {
        return endTimeS;
    }

    public void setEndTimeS(String endTimeS) {
        this.endTimeS = endTimeS;
    }
}
