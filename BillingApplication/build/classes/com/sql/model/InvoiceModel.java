/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sql.model;

/**
 *
 * @author arjun.n
 */
public class InvoiceModel {
    int id;
    String type;
    String userId;
    String customerId;
    String day;
    String month;
    String year;
    float total;
    float tax_cgst;
    float tax_sgst;
    float tax_igst;
    String shop_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getTax_cgst() {
        return tax_cgst;
    }

    public void setTax_cgst(float tax_cgst) {
        this.tax_cgst = tax_cgst;
    }

    public float getTax_sgst() {
        return tax_sgst;
    }

    public void setTax_sgst(float tax_sgst) {
        this.tax_sgst = tax_sgst;
    }

    public float getTax_igst() {
        return tax_igst;
    }

    public void setTax_igst(float tax_igst) {
        this.tax_igst = tax_igst;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    @Override
    public String toString() {
        return "InvoiceModel{" + "id=" + id + ", type=" + type + ", userId=" + userId + ", customerId=" + customerId + ", day=" + day + ", month=" + month + ", year=" + year + ", total=" + total + ", tax_cgst=" + tax_cgst + ", tax_sgst=" + tax_sgst + ", tax_igst=" + tax_igst + ", shop_name=" + shop_name + '}';
    }
    
}
