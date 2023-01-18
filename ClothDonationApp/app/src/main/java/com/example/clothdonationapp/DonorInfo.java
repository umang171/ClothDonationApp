package com.example.clothdonationapp;

public class DonorInfo {
    public String fname,lname,email,phone,address,gender,clothType,clothSize;

    @Override
    public String toString() {
        return "DonorInfo{" +
                "Name='" + fname + '\'' +
                "\nemail='" + email + '\'' +
                "\nphone='" + phone + '\'' +
                "\naddress='" + address + '\'' +
                "\ngender='" + gender + '\'' +
                "\nclothType='" + clothType + '\'' +
                "\nclothSize='" + clothSize + '\'' +
                '}';
    }
}
