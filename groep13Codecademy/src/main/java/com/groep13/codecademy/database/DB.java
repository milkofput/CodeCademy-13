/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.database;
import java.sql.*;

/**
 *
 * @author milko
 */
public class DB {
    private static String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    
    public static ResultSet execWithRS(String SQL) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);          
            return rs;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }   
    
    public static void exec(String SQL) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            stmt = con.createStatement();
            stmt.execute(SQL);          
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    } 
}
