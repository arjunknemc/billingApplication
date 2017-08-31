/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sql;

import com.sql.model.InventoryModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author arjun.n
 */
public class InventoryDAO {
    
    public InventoryModel getDetailsFromItemNo(String itemCode, String shopName){
        DBConnection conn = new DBConnection();
        Connection c = conn.getMyConnection();
        InventoryModel eachRow = null;
        try{
             String sql = "SELECT * from inventory where item_no=? and location=?";
             PreparedStatement query = c.prepareStatement(sql);
             query.setString(1, itemCode);
             query.setString(2, shopName);
             ResultSet response = query.executeQuery();
             while(response.next()){
                 eachRow = new InventoryModel();
                 eachRow.setItem_no(response.getString(1));
                 eachRow.setDetails(response.getString(2));
                 eachRow.setRegister_date(response.getTime(3));
                 eachRow.setQuantity(response.getInt(4));
                 eachRow.setCompany(response.getString(5));
                 eachRow.setCostPrice(response.getDouble(6));
                 eachRow.setSalePrice(response.getDouble(7));
                 eachRow.setTax(response.getDouble(8));
                 eachRow.setHsnID(response.getInt(9));
                 eachRow.setItemNotes(response.getString(10));
                 eachRow.setLocation(response.getString(11));
             }
        }catch(Exception e){
            System.out.println("com.sql.UserDAO.getDetailsFromItemNo() : " +e.getMessage());
        }
       
        return eachRow;
    }
    
    public InventoryModel searchOnItemNumber(String itemCode){
        DBConnection conn = new DBConnection();
        Connection c = conn.getMyConnection();
        InventoryModel eachRow = null;
        try{
             String sql = "SELECT * from inventory where item_no=?";
             PreparedStatement query = c.prepareStatement(sql);
             query.setString(1, itemCode);
        
             ResultSet response = query.executeQuery();
             while(response.next()){
                 eachRow = new InventoryModel();
                 eachRow.setItem_no(response.getString(1));
                 eachRow.setDetails(response.getString(2));
                 eachRow.setRegister_date(response.getTime(3));
                 eachRow.setQuantity(response.getInt(4));
                 eachRow.setCompany(response.getString(5));
                 eachRow.setCostPrice(response.getDouble(6));
                 eachRow.setSalePrice(response.getDouble(7));
                 eachRow.setTax(response.getDouble(8));
                 eachRow.setHsnID(response.getInt(9));
                 eachRow.setItemNotes(response.getString(10));
                 eachRow.setLocation(response.getString(11));
             }
        }catch(Exception e){
            System.out.println("com.sql.UserDAO.getDetailsFromItemNo() : " +e.getMessage());
        }
       
        return eachRow;
    }
    
    public boolean updateQuantity(String itemCode, double quantity){
        DBConnection conn = new DBConnection();
        Connection c = conn.getMyConnection();
        try{
            String sql = "update inventory set quantity = ? where item_no = ?";
            PreparedStatement query = c.prepareStatement(sql);
            query.setDouble(1, quantity);
            query.setString(2, itemCode);
            query.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("com.sql.UserDAO.updateQuantity() : " +e.getMessage());
            return false;
        }
    }
    
    public boolean updateInventory(InventoryModel model){
        DBConnection conn = new DBConnection();
        Connection c = conn.getMyConnection();
        try{
            String sql = "update inventory set details = ? , quantity = ? , hsnID = ? , tax = ? , company = ? where item_no = ?";
            PreparedStatement query = c.prepareStatement(sql);
            query.setString(1, model.getDetails());
            query.setDouble(2, model.getQuantity());
            query.setInt(3, model.getHsnID());
            query.setDouble(4, model.getTax());
            query.setString(5, model.getCompany());
            query.setString(6, model.getItem_no());
            query.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("com.sql.UserDAO.updateInventory() : " +e.getMessage());
            return false;
        }
    }
    
    public boolean insertIntoInventory(InventoryModel model){
        DBConnection conn = new DBConnection();
        Connection c = conn.getMyConnection();
        try{
            String sql = "insert into inventory(item_no,details,register_date,quantity,company,costPrice,salePrice,tax,hsnID,itemNotes,location)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement query = c.prepareStatement(sql);
            query.setString(1, model.getItem_no());
            query.setString(2, model.getDetails());
            query.setDate(3, getCurrentDatetime());
            query.setDouble(4, model.getQuantity());
            query.setString(5, model.getCompany());
            query.setDouble(6, model.getCostPrice());
            query.setDouble(7, model.getSalePrice());
            query.setDouble(8, model.getTax());
            query.setInt(9, model.getHsnID());
            query.setString(10, model.getItemNotes());
            query.setString(11, model.getLocation());
            
            query.executeUpdate();
            return true;
            
        }catch(Exception e){
            return false;
        }

    }
    
    public java.sql.Date getCurrentDatetime() {
        Date today = new Date();
        return new java.sql.Date(today.getTime());
    }
    
    public ArrayList<InventoryModel> getAllDetails(){
         DBConnection conn = new DBConnection();
         Connection c = conn.getMyConnection();
         ArrayList<InventoryModel> details = new ArrayList<InventoryModel>();
         try{
             String sql = "select * from inventory";
             PreparedStatement query = c.prepareStatement(sql);
             ResultSet response = query.executeQuery();
             while(response.next()){
                 InventoryModel eachRow = new InventoryModel();
                 eachRow.setItem_no(response.getString(1));
                 eachRow.setDetails(response.getString(2));
                 eachRow.setRegister_date(response.getTime(3));
                 eachRow.setQuantity(response.getInt(4));
                 eachRow.setCompany(response.getString(5));
                 eachRow.setCostPrice(response.getDouble(6));
                 eachRow.setSalePrice(response.getDouble(7));
                 eachRow.setTax(response.getDouble(8));
                 eachRow.setHsnID(response.getInt(9));
                 eachRow.setItemNotes(response.getString(10));
                 eachRow.setLocation(response.getString(11));
                 
                 details.add(eachRow);
             }
         }catch(Exception e){
             
         }
         return details;
    }
    
    public boolean removeRowFromInventory(String item_no , String location){
        DBConnection conn = new DBConnection();
         Connection c = conn.getMyConnection();
         try{
             String sql = "delete from inventory where item_no=? and location=?";
             PreparedStatement query = c.prepareStatement(sql);
             query.setString(1, item_no);
             query.setString(2, location);
             query.executeUpdate();
             
         }catch(Exception e){
             return false;
         }
         return true;
    }
}
