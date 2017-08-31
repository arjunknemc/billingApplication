/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sql;

import com.constants.Constants;
import com.sql.model.InvoiceModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author arjun.n
 */
public class ReportGenerationDAO {
    
    public ArrayList<InvoiceModel> fetchMonthlyReport(String month, String year, String shopName){
        DBConnection conn = new DBConnection();
        Connection c = conn.getMyConnection();
        ArrayList<InvoiceModel> reportDetails = new ArrayList<InvoiceModel>();
        try{
           String sql = "select * from invoice where month=? and year=? and shop_name=? and type=?";
           PreparedStatement query = c.prepareStatement(sql);
           query.setString(1, month);
           query.setString(2, year);
           query.setString(3, shopName);
           query.setString(4, Constants.invoice_VALID);
          
            ResultSet result =  query.executeQuery();
            while(result.next()){
                InvoiceModel model =  new InvoiceModel();
                model.setId(result.getInt(1));
                model.setTax_cgst(result.getFloat(9));
                model.setTax_sgst(result.getFloat(10));
                model.setTax_igst(result.getFloat(11));
                model.setTotal(result.getFloat(8));
                model.setShop_name(result.getString(12));
                reportDetails.add(model);
            }
           
        }catch(Exception e){
            e.printStackTrace();
        }
        return reportDetails;
        
    }
}
