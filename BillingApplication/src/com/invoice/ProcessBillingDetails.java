/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invoice;

import com.billingDetails.AddBillingDetails;
import com.pojo.Bill;
import com.pojo.BillDetails;
import com.sql.InvoiceDAO;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author arjun.n
 */
public class ProcessBillingDetails {
    
    public ProcessBillingDetails(){
        
    }
    
    public Bill process(JTable tableItems, String type){
       
        float totalAmt=0;
        float totalCgst=0;
        float totalSgst=0;
        float totalIgst=0;
        Bill bill = new Bill();
        ArrayList<BillDetails> processDetails = new ArrayList<BillDetails>();
        
        for(int i=0 ; i < tableItems.getRowCount(); i++){
            BillDetails d = new BillDetails();
            
            d.setDescription(tableItems.getValueAt(i, 1).toString());
            d.setQty(Double.parseDouble(tableItems.getValueAt(i, 2).toString()));
            d.setRate(Double.parseDouble(tableItems.getValueAt(i, 3).toString()));
            
            totalAmt+=Double.parseDouble(tableItems.getValueAt(i, 4).toString());
            d.setAmt(Double.parseDouble(tableItems.getValueAt(i, 4).toString())); 
            
            totalCgst+=Double.parseDouble(tableItems.getValueAt(i, 5).toString());
            d.setCgst(Double.parseDouble(tableItems.getValueAt(i, 5).toString()));
            
            totalSgst+=Double.parseDouble(tableItems.getValueAt(i, 6).toString());
            d.setSgst(Double.parseDouble(tableItems.getValueAt(i, 6).toString()));
            
            double igst = Double.parseDouble(tableItems.getValueAt(i, 7).toString());
            if(igst != 0){
                totalIgst+=Double.parseDouble(tableItems.getValueAt(i, 7).toString());
                d.setIgst(Double.parseDouble(tableItems.getValueAt(i, 7).toString()));
            }
            
            d.setTaxPercentage(Double.parseDouble(tableItems.getValueAt(i, 8).toString()));
            d.setItemCode(tableItems.getValueAt(i, 9).toString());
            processDetails.add(d);
        }
        
        InvoiceDAO billNumber = new InvoiceDAO();
        bill.setInvoiceId(billNumber.getInvoiceId(type));
        bill.setDetails(processDetails);
        bill.setTotalAmt(totalAmt);
        bill.setTotalCgst(totalCgst);
        bill.setTotalSgst(totalSgst);
        bill.setTotalIgst(totalIgst);
        bill.setType(type);
        return bill;
    }
    
}
