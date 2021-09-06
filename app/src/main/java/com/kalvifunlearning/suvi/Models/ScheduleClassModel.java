package com.kalvifunlearning.suvi.Models;

public class ScheduleClassModel {
    String classId,topicName, timing, date, day, meetingLink, description;

    public ScheduleClassModel(String classId, String topicName, String timing, String date, String day, String meetingLink, String description) {
        this.classId = classId;
        this.topicName = topicName;
        this.timing = timing;
        this.date = date;
        this.day = day;
        this.meetingLink = meetingLink;
        this.description = description;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
