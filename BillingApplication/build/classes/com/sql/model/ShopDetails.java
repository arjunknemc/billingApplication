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
public class ShopDetails {
    
    String name;
    String gst_no;
    String gst_state_code;
    String address;
    String locality;
    String state;
    String pincode;
    String phone_no;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGst_no() {
        return gst_no;
    }

    public void setGst_no(String gst_no) {
        this.gst_no = gst_no;
    }

    public String getGst_state_code() {
        return gst_state_code;
    }

    public void setGst_state_code(String gst_state_code) {
        this.gst_state_code = gst_state_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    
    @Override
    public String toString() {
        return "ShopDetails{" + "name=" + name + ", gst_no=" + gst_no + ", gst_state_code=" + gst_state_code + ", address=" + address + ", locality=" + locality + ", state=" + state + ", pincode=" + pincode + '}';
    }

    
    
    
    
}
