/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sql;

import com.pojo.Bill;
import com.pojo.BillDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author arjun.n
 */
public class InvoiceDAO {

    public boolean insertInvoice(Bill bill) {
        DBConnection conn = new DBConnection();
        Connection c = conn.getMyConnection();
       
        try {
           int invoiceId =  bill.getInvoiceId();
           String sql = "insert into invoice(idInvoice,Type,userid,customerid,"
                   + "DAY,MONTH,YEAR,total,tax_cgst,tax_sgst,tasx_igst)"
                   + "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
           PreparedStatement query = c.prepareStatement(sql);
           query.setInt(1, invoiceId);
           query.setString(2, "VALID");
           query.setString(3, "admin");
           query.setString(4, "customer");
           query.setString(5, String.valueOf(bill.getDay()));
           query.setString(6, String.valueOf(bill.getMonth()));
           query.setString(7, String.valueOf(bill.getYear()));
           query.setDouble(8,  bill.getTotalAmt());
           query.setDouble(9, bill.getTotalCgst());
           query.setDouble(10, bill.getTotalSgst());
           query.setDouble(11, bill.getTotalIgst());
           int result = query.executeUpdate();
           
           ArrayList<BillDetails> billEachRow = bill.getDetails();
           for(int i=0; i< billEachRow.size(); i++){
                 insertIntoInvoiceLine(billEachRow.get(i), invoiceId);
            }
            updateInvoiceId(invoiceId,bill.getType());
            return true;      
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("com.sql.UserDAO.verify() : " + e.getMessage());
        }

        return false;
    }
    
    
    public boolean insertIntoInvoiceLine(BillDetails details, int invoiceID){
        DBConnection conn = new DBConnection();
        Connection c = conn.getMyConnection();
        try{
            String sql = "insert into invoice_line(idInvoice,amount,rate,quantity,"
                    + "description,cgst,sgst,igst,idHsN)"
                    + "VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement query = c.prepareStatement(sql);
            query.setInt(1, invoiceID);
            query.setDouble(2, details.getAmt());
            query.setDouble(3, details.getRate());
            query.setDouble(4, details.getQty());
            query.setString(5, details.getDescription());
            query.setDouble(6, details.getCgst());
            query.setDouble(7, details.getSgst());
            query.setDouble(8, details.getIgst());
            query.setInt(9, 0);
            int result = query.executeUpdate();
            if(result > 0){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private int getRandomNumber() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(100);
        return randomInt;
    }

    public int getInvoiceId(String type) {
        DBConnection conn = new DBConnection();
        Connection c = conn.getMyConnection();

        //int invoiceId = getRandomNumber();
        try {
            String sql = "select * from invoice_number where purpose=?";
            PreparedStatement query = c.prepareStatement(sql);
            query.setString(1, type);
            ResultSet set = query.executeQuery();
            while(set.next()){
                int invoiceId = set.getInt(1);
                return invoiceId+1;
            }
        } catch (Exception e) {
               e.printStackTrace();
        }
    
        return 0;
    }
    
    public boolean updateInvoiceId(int number,String type){
        DBConnection conn = new DBConnection();
        Connection c = conn.getMyConnection();
        boolean status = false;
        try{
            String sql = "update invoice_number set id=? where purpose=?";
            PreparedStatement query = c.prepareStatement(sql);
            query.setInt(1, number);
            query.setString(2, type);
            
            int result = query.executeUpdate();
            if(result > 0){
                status=true;
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("updateInvoiceId " +e.getLocalizedMessage());
        }
        return status;
    }
}
