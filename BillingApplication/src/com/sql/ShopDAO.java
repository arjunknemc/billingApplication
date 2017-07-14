/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sql;

import com.sql.model.ShopDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author arjun.n
 */
public class ShopDAO {
    
    public ArrayList<ShopDetails> getShopDetails(){
        ArrayList<ShopDetails> details = new ArrayList<ShopDetails>();
        DBConnection conn = new DBConnection();
        Connection c = conn.getMyConnection();
        try{
             String sql = "SELECT * from shop_details";
             PreparedStatement query = c.prepareStatement(sql);
             
             ResultSet set = query.executeQuery();
             while(set.next()){
                 ShopDetails d = new ShopDetails();
                  d.setName(set.getString(2));
                  d.setGst_no(set.getString(3));
                  d.setGst_state_code(set.getString(4));
                  d.setAddress(set.getString(5));
                  d.setLocality(set.getString(6));
                  d.setState(set.getString(7));
                  d.setPincode(set.getString(8));
                  details.add(d);
             }
        }catch(Exception e){
            System.out.println("com.sql.UserDAO.verify() : " +e.getMessage());
        }
       
        return details;
    }
}
