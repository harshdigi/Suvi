package com.kalvifunlearning.suvi.Models;

import android.net.Uri;

public class StudentModel {
    String name, email, mobile, city, board, standard , language , accountType,uid;
    Uri imageUrl ;



    public StudentModel(String name, String email, String mobile, String city, String board, String standard, String language, String accountType, Uri imageUrl, String uid) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.city = city;
        this.board = board;
        this.standard = standard;
        this.language = language;
        this.accountType = accountType;
        this.uid= uid;
        this.imageUrl = imageUrl;
    }
    public StudentModel(String name, String email, String mobile, String city, String board, String standard, String language, String accountType, Uri imageUrl) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.city = city;
        this.board = board;
        this.standard = standard;
        this.language = language;
        this.accountType = accountType;
        this.uid= uid;
        this.imageUrl = imageUrl;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Uri getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
