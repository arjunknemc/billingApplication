/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invoice;

import com.pojo.Bill;
import com.pojo.BillDetails;
import com.sql.model.ShopDetails;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import com.constants.Constants;

/**
 *
 * @author arjun.n
 */
public class CreateSimpleWorkingHtml {
    
    String customerName;
    String customerDetails;
    String additionalDetails;
    //String filePath = "C://BillingSoftware//Invoice//";
    //String filePath = Constants.filePath;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(String customerDetails) {
        this.customerDetails = customerDetails;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }
    
    public String head = "<head>\n" +
"		<meta charset=\"utf-8\">\n" +
"		<title>Invoice</title>\n" +
"		<link rel=\"stylesheet\" href=\"style.css\">\n" +
"	</head> ";
    
    public String create(ShopDetails details , Bill bill){
       // String fileName = bill.getInvoiceId()+".html";
        String file = getFilePath(bill, details);
        createFile(file);
        writeToFile(file , details, bill);
        
        return file;
    }
    
    public String getFilePath(Bill bill, ShopDetails details){
        String fileName = bill.getType()+"-"+details.getName()+"-"+bill.getInvoiceId()+".html";
        return Constants.filePath+fileName;
    }
    
    public String constructShopTable(ShopDetails details){
        String shopDetails = "<header>\n" +
"			<h1>Tax Invoice</h1>\n" +
"                       <address>\n" +
"                       <shopname>\n"+
"				<p>"+details.getName() +"</p>\n" +
"                       </shopname>\n"+
"				<p>"+details.getAddress()+"</p>\n" +
"				<p>"+details.getLocality()+"-" +details.getPincode()+"</p>\n" +
"				<p>"+details.getState()+"</p>\n" +
"                       <shopDetails>\n" +
"                               <p>StateCode: " +details.getGst_state_code()+"</p>\n"+
"				<p>GST: "+details.getGst_no()+"</p>\n" +
"                               <p>Ph NO: "+details.getPhone_no()+"</p>\n"+
"			</shopDetails>\n"+
"			</address>\n" +
"                       <span><img alt=\"\" src=\"logo.jpg\"><input type=\"file\" accept=\"image/*\"></span>\n"+
"		</header>";
        
        return shopDetails;
    }
    
    public String constructCustomerAndInvoiceNo(ShopDetails details , Bill bill){
        String date = bill.getDay()+"/"+bill.getMonth()+"/"+bill.getYear();
        String response = "<h1>Recipient</h1>\n" +
"			<address>\n" +
"				<p>Customer Details</p>\n" +
"				<p>"+getCustomerName()+"<br>"+getCustomerDetails()+"</p>\n" +
"			</address>\n" +
"			<table class=\"meta\">\n" +
"				<tr>\n" +
"					<th><span>Invoice #</span></th>\n" +
"					<td><span>"+bill.getInvoiceId()+"</span></td>\n" +
"				</tr>\n" +
"				<tr>\n" +
"					<th><span>Date</span></th>\n" +
"					<td><span>"+date+"</span></td>\n" +
"				</tr>\n" +
"			</table>";
        
        return response;
    }
    
    public String constuctBillDetailsHeader(){
        String response = "<thead>\n" +
"					<tr>\n" +
"                                               <th><span>SI</span></th>\n"+
//"						<th><span>Item</span></th>\n" +
"						<th><span>Description</span></th>\n" +
"						<th><span>Rate</span></th>\n" +
"						<th><span>Quantity</span></th>\n" +
"						<th><span>Price</span></th>\n" +
"					</tr>\n" +
"				</thead>";
        
        return response;
    }
    
    public String constructDescriptionTable(Bill bill){
        String details = "<tbody>";
        for(int i=0; i < bill.getDetails().size() ; i++){
           int si = i+1;
           BillDetails d = bill.getDetails().get(i);
           double rate = roundOff(d.getRate());
           double qty = d.getQty();
           double individualRate = rate/qty;
           String newRow = "<tr>\n" +
"                               <td><span>"+si+"</span></td>\n" +
//"                               <td><span>"+getAdditionalDetails()+"</span></td>\n"+          
"				<td><span>"+d.getDescription()+"<br>"+getAdditionalDetails() +"</span></td>\n" +
"				<td><span data-prefix>Rs</span><span >"+individualRate+"</span></td>\n" +
"                               <td><span>"+d.getQty()+"</span></td>\n" +
"				<td><span data-prefix>Rs</span><span>"+rate+"</span></td>\n" +
"			    </tr>\n" ;
           details = details + newRow;
        }
        details = details + "</tbody>";
        return details;
    }
    
    public String constructDetailsTable(Bill bill){
        String response;
        response = "<table class=\"inventory\">"+constuctBillDetailsHeader()
                +constructDescriptionTable(bill)+"</table>";
        
        return response;
    }
    
     public String constructTotalAmtTable(Bill bill){
        double tax =  bill.getTotalCgst();
        System.out.println("TAX : " +tax);
        tax = roundOff(tax);
          System.out.println("TAX AFTER ROUNDOFF: " +tax);
        double taxPercentage = bill.getDetails().get(0).getTaxPercentage();
        String response = "<table class=\"balance\">\n" +
"				<tr>\n" +
"					<th><span>Total-CGST:"+taxPercentage/2+"%"+"</span></th>\n" +
"					<td><span data-prefix>Rs</span><span>"+roundOff(bill.getTotalCgst())+"</span></td>\n" +
"				</tr>\n" +
"				<tr>\n" +
"					<th><span>Total-SGCT:"+taxPercentage/2+"%"+"</span></th>\n" +
"					<td><span data-prefix>Rs</span><span>"+roundOff(bill.getTotalSgst())+"</span></td>\n" +
"				</tr>\n" +
"				<tr>\n" +
"					<th><span>Total</span></th>\n" +
"					<td><span data-prefix>Rs</span><span>"+bill.getTotalAmt() +"</span></td>\n" +
"				</tr>\n" +
"			</table>";
        
        return response;
    }
    
    public String signatureAndFooter(){
        String signature = "<signature>\n" +
"			<h1>Signature</h1>\n" +
"		</signature>";
        
        String additionalNote = "<aside>\n" +
"			<h1><span>Additional Notes</span></h1>\n" +
"			<div>\n" +
"				<p>Goods Once Sold Cannot be Exchanged or Returned.</p>\n" +
"			</div>\n" +
"		</aside>";
        
        return signature+additionalNote;
        
    }
    
    public String constructHtmlBody(ShopDetails details, Bill bill){
        String response;
        response = "<html>"+head+"<body><div class=\"body-container\">"+constructShopTable(details)
                +"<article>"+constructCustomerAndInvoiceNo(details,bill)+constructDetailsTable(bill)
                +constructTotalAmtTable(bill)+"</article>"+signatureAndFooter()
                +"</div></body></html>";
        
        return response;
    }
    
    public boolean createFile(String filePath){
        boolean status = false;
        try{
            File file = new File(filePath);
            if(file.createNewFile()){
                System.out.println("com.invoice.CreateSimpleWorkingHtml.createFile()");
                status = true;
            }
        }catch(IOException e){
            status = false;
        }
        return status;
    }
    
    public boolean writeToFile(String filePath, ShopDetails details , Bill bill){
        BufferedWriter bw = null;
        FileWriter fw = null;
        boolean status = false;
        try{
            fw = new FileWriter(filePath);
            bw = new BufferedWriter(fw);
            bw.write(constructHtmlBody(details, bill));
            status = true;
            bw.close();
            fw.close();
        }catch(IOException e){
            
        }
        return status;
    }
    
    public static double roundOff(double value){
        String taxInString = Double.toString(value);
        int index = taxInString.indexOf(".");
        if(index!=-1){
            String[] split = taxInString.split("\\.");
            if(split.length > 1 && split[1].length() < 4){
                return value;
            }
        }
        double finalValue = Math.floor(value * 1e2) / 1e2;
        return finalValue;
    }
}
