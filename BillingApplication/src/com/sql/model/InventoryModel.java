/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sql.model;

import java.util.Date;

/**
 *
 * @author arjun.n
 */
public class InventoryModel {
    String item_no;
    String details;
    Date register_date;
    double quantity;
    String company;
    double costPrice;
    double salePrice;
    double tax;
    int hsnID;
    String itemNotes;
    String location;

    public String getItem_no() {
        return item_no;
    }

    public void setItem_no(String item_no) {
        this.item_no = item_no;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public int getHsnID() {
        return hsnID;
    }

    public void setHsnID(int hsnID) {
        this.hsnID = hsnID;
    }

    public String getItemNotes() {
        return itemNotes;
    }

    public void setItemNotes(String itemNotes) {
        this.itemNotes = itemNotes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "InventoryModel{" + "item_no=" + item_no + ", details=" + details + ", register_date=" + register_date + ", quantity=" + quantity + ", company=" + company + ", costPrice=" + costPrice + ", salePrice=" + salePrice + ", tax=" + tax + ", hsnID=" + hsnID + ", itemNotes=" + itemNotes + ", location=" + location + '}';
    }
    
    
}
