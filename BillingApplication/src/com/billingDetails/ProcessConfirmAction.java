/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingDetails;

import com.invoice.ProcessBillingDetails;
import com.pojo.Bill;
import com.sql.ShopDAO;
import com.sql.model.ShopDetails;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;

/**
 *
 * @author arjun.n
 */
public class ProcessConfirmAction {
    
    public ArrayList<ShopDetails> getShopDetails(){
        ShopDAO d = new ShopDAO();
        ArrayList<ShopDetails> shopDetails = d.getShopDetails();
        return shopDetails;
    }
    
    public String confirmAction(JRootPane rootPane, ArrayList<ShopDetails> shopDetails){
        
        URL iconURL = getClass().getResource("/com/sql/choose.png");
        // iconURL is null when not found
        ImageIcon icon = new ImageIcon(iconURL);
        String[] address = new String[10];
        for(int i=0; i < shopDetails.size() ; i++){
            address[i] = shopDetails.get(i).getName();
        }
       
        String s = (String)JOptionPane.showInputDialog(
                    rootPane,
                    "Choose the Addrress:\n"
                    + "For Billing",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    address,
                    address[0]);

        //If a string was returned, say so.
        if ((s != null) && (s.length() > 0)) {
           return s;
        }else{
            return "Default";
        }
    }
}
