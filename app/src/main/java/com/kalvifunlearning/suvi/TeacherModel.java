package com.kalvifunlearning.suvi;

public class TeacherModel {
    String name,email, mobile,accountType, isVerifiedTeacher;

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

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getIsVerifiedTeacher() {
        return isVerifiedTeacher;
    }

    public void setIsVerifiedTeacher(String isVerified) {
        this.isVerifiedTeacher = isVerified;
    }

    public TeacherModel(String name, String email, String mobile, String accountType, String isVerifiedTeacher) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.accountType = accountType;
        this.isVerifiedTeacher = isVerifiedTeacher;
    }
}
