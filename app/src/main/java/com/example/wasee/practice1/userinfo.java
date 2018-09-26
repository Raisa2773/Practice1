package com.example.wasee.practice1;

public class userinfo {
    String name,mobileNo,email,password,gender,dob;

    public userinfo() {
    }

    public userinfo(String name, String mobileNo, String email, String password,String gender,String dob) {
        this.name = name;
        this.mobileNo = mobileNo;
        this.email = email;
        this.password = password;
        this.gender=gender;
        this.dob=dob;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
