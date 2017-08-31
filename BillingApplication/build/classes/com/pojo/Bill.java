/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author arjun.n
 */
public class Bill {
    int invoiceId;
    double totalAmt;
    double totalCgst;
    double totalSgst;
    double totalIgst;
    ArrayList<BillDetails> details;
    int day;
    int month;
    int year;
    String type;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public double getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(double totalAmt) {
        this.totalAmt = totalAmt;
    }

    public double getTotalCgst() {
        return totalCgst;
    }

    public void setTotalCgst(double totalCgst) {
        this.totalCgst = totalCgst;
    }

    public double getTotalIgst() {
        return totalIgst;
    }

    public void setTotalIgst(double totalIgst) {
        this.totalIgst = totalIgst;
    }

    public ArrayList<BillDetails> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<BillDetails> details) {
        this.details = details;
    }

    public double getTotalSgst() {
        return totalSgst;
    }

    public void setTotalSgst(double totalSgst) {
        this.totalSgst = totalSgst;
    }

    public int getDay() {
       return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
       return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Bill{" + "invoiceId=" + invoiceId + ", totalAmt=" + totalAmt + ", totalCgst=" + totalCgst + ", totalSgst=" + totalSgst + ", totalIgst=" + totalIgst + ", details=" + details + ", day=" + day + ", month=" + month + ", year=" + year + ", type=" + type + '}';
    }

   
    
    
}
