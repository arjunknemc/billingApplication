/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author arjun.n
 */
public class UserDAO {

    public UserDAO() {
    }
    
    public boolean verify(String username, String password){
        DBConnection conn = new DBConnection();
        Connection c = conn.getMyConnection();
        try{
             String sql = "SELECT id from user where username=? and password=?";
             PreparedStatement query = c.prepareStatement(sql);
             query.setString(1, username);
             query.setString(2, password);
            
             ResultSet set = query.executeQuery();
             while(set.next()){
                 return true;
             }
        }catch(Exception e){
            System.out.println("com.sql.UserDAO.verify() : " +e.getMessage());
        }
       
        return false;
    }
}
