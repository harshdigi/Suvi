package com.kalvifunlearning.suvi.Models;

import java.util.List;

public class BatchModel {
    String batchId,batchName,batchStandard, batchBoard, batchLanguage,batchDescription, batchTeacher;
    List<String> batchStudentsList;
    int totalStudents;

    public BatchModel(String batchId, String batchName, String batchStandard, String batchBoard, String batchLanguage, String batchDescription, String batchTeacher, List<String> batchStudentsList, int totalStudents) {
        this.batchId = batchId;
        this.batchName = batchName;
        this.batchStandard = batchStandard;
        this.batchBoard = batchBoard;
        this.batchLanguage = batchLanguage;
        this.batchDescription = batchDescription;
        this.batchTeacher = batchTeacher;
        this.batchStudentsList = batchStudentsList;
        this.totalStudents = totalStudents;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchStandard() {
        return batchStandard;
    }

    public void setBatchStandard(String batchStandard) {
        this.batchStandard = batchStandard;
    }

    public String getBatchBoard() {
        return batchBoard;
    }

    public void setBatchBoard(String batchBoard) {
        this.batchBoard = batchBoard;
    }

    public String getBatchLanguage() {
        return batchLanguage;
    }

    public void setBatchLanguage(String batchLanguage) {
        this.batchLanguage = batchLanguage;
    }

    public String getBatchDescription() {
        return batchDescription;
    }

    public void setBatchDescription(String batchDescription) {
        this.batchDescription = batchDescription;
    }

    public String getBatchTeacher() {
        return batchTeacher;
    }

    public void setBatchTeacher(String batchTeacher) {
        this.batchTeacher = batchTeacher;
    }

    public List<String> getBatchStudentsList() {
        return batchStudentsList;
    }

    public void setBatchStudentsList(List<String> batchStudentsList) {
        this.batchStudentsList = batchStudentsList;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }
}
