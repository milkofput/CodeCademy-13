/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecademy13.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author milko
 */
public class DB {
    private static String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Bibliotheek;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    
    public static ResultSet exec(String SQL) {
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
        /*finally {
        if (rs != null) try { rs.close(); } catch(Exception e) {}
        if (stmt != null) try { stmt.close(); } catch(Exception e) {}
        if (con != null) try { con.close(); } catch(Exception e) {}
        }*/
        return null;
    }    
}
