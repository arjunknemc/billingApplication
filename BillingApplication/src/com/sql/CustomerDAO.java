/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sql;

import com.sql.model.CustomerModel;
import com.sql.model.ShopDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author arjun.n
 */
public class CustomerDAO {
   
    public ArrayList<CustomerModel> getAllCustomerData(){
        ArrayList<CustomerModel> customerDetails = new ArrayList<CustomerModel>();
        DBConnection conn = new DBConnection();
        Connection c = conn.getMyConnection();
        try{
            String sql = "select * from customer";
            PreparedStatement query = c.prepareStatement(sql);
            ResultSet response = query.executeQuery();
            while(response.next()){
                CustomerModel customer = new CustomerModel();
                customer.setName(response.getString(2));
                customer.setGst(response.getString(3));
                customer.setAddress(response.getString(4));
                customer.setContact_no(response.getString(5));
                customerDetails.add(customer);
            }
        }catch(Exception e){
            System.out.println("com.sql.CustomerDAO.getAllCustomerData()" +e.getMessage());
        }
        
        return customerDetails;
    }
    
    
    public boolean insertCustomerDetails(CustomerModel model){     
        DBConnection conn = new DBConnection();
        Connection c = conn.getMyConnection();
        try{
             String sql = "insert into customer(name,gst,address,contact_no) values (?,?,?,?)";
             PreparedStatement query = c.prepareStatement(sql);
             query.setString(1, model.getName());
             query.setString(2, model.getGst());
             query.setString(3, model.getAddress());
             query.setString(4, model.getContact_no());
             boolean status = query.execute();
             return status;
        }catch(Exception e){
            System.out.println("com.sql.CustomerDAO.insertCustomerDetails()" +e.getMessage());
        }
       
        return false;
    }
}
