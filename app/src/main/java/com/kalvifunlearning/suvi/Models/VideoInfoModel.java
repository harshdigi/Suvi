package com.kalvifunlearning.suvi.Models;

public class VideoInfoModel {
    String videoName, videoLink, description, board, language, subject, standard, category, section, open, createdBy;

    public VideoInfoModel(String videoName, String videoLink, String description, String board, String language, String subject, String standard, String category, String section, String open, String createdBy) {
        this.videoName = videoName;
        this.videoLink = videoLink;
        this.description = description;
        this.board = board;
        this.language = language;
        this.subject = subject;
        this.standard = standard;
        this.category = category;
        this.section = section;
        this.open = open;
        this.createdBy = createdBy;
    }

    public VideoInfoModel() {
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
