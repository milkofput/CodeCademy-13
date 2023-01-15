package com.groep13.codecademy.database;
import java.sql.*;

/**
 * DB beheert de databaseverbinding en de uitvoer van query's in de database.
 */
public class DB {
    private static String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    
    //Voert de parameter uit als query in de database, en retourneert het resultaat daarvan als ResultSet.
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
    
    //Voert de parameter uit als query in de database, en retourneert of de query succesvol was of niet als boolean.
    public static boolean exec(String SQL) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            stmt = con.createStatement();
            stmt.execute(SQL); 
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    } 
}
