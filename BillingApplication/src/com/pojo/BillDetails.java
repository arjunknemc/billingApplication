/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pojo;

/**
 *
 * @author arjun.n
 */
public class BillDetails {
    String description;
    double qty;
    double rate;
    double amt;
    double cgst;
    double sgst;
    double igst;
    double taxPercentage;
    String itemCode;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public double getCgst() {
        return cgst;
    }

    public void setCgst(double cgst) {
        this.cgst = cgst;
    }

    public double getSgst() {
        return sgst;
    }

    public void setSgst(double sgst) {
        this.sgst = sgst;
    }

    public double getIgst() {
        return igst;
    }

    public void setIgst(double igst) {
        this.igst = igst;
    }

    public double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    
    @Override
    public String toString() {
        return "BillDetails{" + "description=" + description + ", qty=" + qty + ", rate=" + rate + ", amt=" + amt + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst + ", taxPercentage=" + taxPercentage + '}';
    }

   
    
}
