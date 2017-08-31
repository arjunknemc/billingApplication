package com.sql; 

import com.constants.Constants;
import java.io.FileReader;
import java.sql.*;
import org.h2.tools.RunScript;
import org.h2.tools.Server;

/**
 *
 * 
 */
public class DBConnection {
   
    private Connection myConnection;
    
    /** Creates a new instance of MyDBConnection */
    public DBConnection() {
        init();
        //createStatement();
    }

    private void init(){
    
       try{
        
        //Class.forName("com.mysql.jdbc.Driver");
        //myConnection=DriverManager.getConnection(
        //       "jdbc:mysql://localhost/billing_application","root", "password"
        //        );
            Class.forName("org.h2.Driver");
            Server server = Server.createTcpServer().start();
            myConnection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/billing_application","sa","");
            if(!createStatement()){
                RunScript.execute(myConnection, new FileReader(Constants.sqlScript));
                RunScript.execute(myConnection, new FileReader(Constants.insertScript));
            }
            
            
        }
        catch(Exception e){
            System.out.println("Failed to get connection");
            e.printStackTrace();
        }
    }
    
    public boolean createStatement(){
        boolean result = false;
        String sql = "select * from user";
        try{
             PreparedStatement query  = myConnection.prepareStatement(sql);
              result = query.execute();
        }catch(SQLException e){
            System.out.println("com.sql.DBConnection.createStatement()" );
            e.printStackTrace();
        }
       return result;
    }
    
    
    public Connection getMyConnection(){
        return myConnection;
    }
    
    
    public void close(ResultSet rs){
        
        if(rs !=null){
            try{
               rs.close();
            }
            catch(Exception e){}
        
        }
    }
    
     public void close(java.sql.Statement stmt){
        
        if(stmt !=null){
            try{
               stmt.close();
            }
            catch(Exception e){}
        
        }
    }
     
  public void destroy(){
  
    if(myConnection !=null){
    
         try{
               myConnection.close();
            }
            catch(Exception e){}
        
        
    }
  }
    
}
