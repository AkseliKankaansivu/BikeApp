package dataReader;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author aksel
 * @version Jan 30, 2023
 * Creates 
 */
public class DBcreator {

    /**
     * @param args not used
     */
        public static void main( String args[] ) {
            connect();
            createNewDatabase("testi.db"); 
            createNewTable();
            DBcreator db = new DBcreator();
            db.insert();
            db.selectAll();
         }
        
       private static void createNewTable() {
            String url = "jdbc:sqlite:C:/sqlite/JTP.db"; 
            String sql = "CREATE TABLE IF NOT EXISTS bikestations (\n" + 
                    "fid int NOT NULL UNIQUE, \n" +
                    "id int PRIMARY KEY NOT NULL, \n" +
                    "nimi text NOT NULL, \n" +
                    "namn text NOT NULL, \n" +
                    "name text NOT NULL, \n" +
                    "osoite text NOT NULL, \n" +
                    "adress text NOT NULL, \n" +
                    "kaupunki text NOT NULL, \n" +
                    "stad text NOT NULL, \n" +
                    "operaattor text NOT NULL, \n" +
                    "kapasiteet int NOT NULL, \n" +
                    "x real NOT NULL, \n" +
                    "y real NOT NULL);";
            try {  
                Connection conn = DriverManager.getConnection(url);  
                Statement stmt = conn.createStatement();  
                stmt.execute(sql);  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
    }
       /**
        * Inserts variables into database 
        */
    private void insert() {
            String url = "jdbc:sqlite:C:/sqlite/JTP.db"; 
            String sql = "INSERT INTO bikestations(fid, id, nimi, namn, name, osoite, adress, kaupunki, stad, operaattor, kapasiteet, x, y) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";  
            try{ 
                Connection conn = DriverManager.getConnection(url);  
                PreparedStatement pstmt = conn.prepareStatement(sql);  
                pstmt.setInt(1, 1);  
                pstmt.setInt(2, 800);
                pstmt.setString(3, "Helsinki");
                pstmt.setString(4, "Helsingfors");
                pstmt.setString(5, "Helsinki");
                pstmt.setString(6, "Katu 10000");
                pstmt.setString(7, "Street 10000");
                pstmt.setString(8, "Helsinki");
                pstmt.setString(9, "Helsingfors");
                pstmt.setString(10, "Telia");
                pstmt.setInt(11, 10);
                pstmt.setDouble(12, 2.5);
                pstmt.setDouble(13, 3.5);
                pstmt.executeUpdate();  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
    }

    /**
     *  yhdistetään sql tietokantaan
     */
    public static void connect() {  
           @SuppressWarnings("resource")
        Connection conn = null;  
           try {  
               // db parameters  
               String url = "jdbc:sqlite:C:/sqlite/JTP.db";  
               // create a connection to the database  
               conn = DriverManager.getConnection(url);  
                 
               System.out.println("Connection to SQLite has been established.");  
                 
           } catch (SQLException e) {  
               System.out.println(e.getMessage());  
           } finally {  
               try {  
                   if (conn != null) {  
                       conn.close();  
                   }  
               } catch (SQLException ex) {  
                   System.out.println(ex.getMessage());  
               }  
           }  
         }
    /**
     * Create a database
     * @param fileName db name
     */
    public static void createNewDatabase(String fileName) {  
        
        String url = "jdbc:sqlite:C:/sqlite/" + fileName;  

        try {  
            Connection conn = DriverManager.getConnection(url);  
            if (conn != null) {  
                DatabaseMetaData meta = conn.getMetaData();  
                System.out.println("The driver name is " + meta.getDriverName());  
                System.out.println("A new database has been created.");  
            }  

        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
       }
    /**
     * select all rows in the warehouses table
     */
    public void selectAll() { 
            String url = "jdbc:sqlite:C:/sqlite/JTP.db"; 
            String sql = "SELECT * FROM bikestations";  
              
            try {  
                Connection conn = DriverManager.getConnection(url);  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("nimi") + "\t" +
                                   rs.getString("osoite"));
            }
            conn.close();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
}
