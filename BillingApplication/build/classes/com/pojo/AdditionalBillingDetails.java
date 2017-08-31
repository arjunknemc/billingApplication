/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pojo;

import java.time.LocalDateTime;

/**
 *
 * @author arjun.n
 */
public class AdditionalBillingDetails {
    String billType;
    int day;
    int month;
    int year;

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        LocalDateTime now = LocalDateTime.now();
        return now.getYear();
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    
}
