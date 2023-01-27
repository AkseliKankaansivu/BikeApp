/**
 * 
 */
package dataReader;

import java.sql.*;

/**
 * @author aksel
 * @version Jan 27, 2023
 * A CSV reader for the dataReader backend software. Fetches data from CSV type files
 */
public class CSVReader {

    /**
     * @param args not used
     */
        public static void main( String args[] ) {
            connect();
            createNewDatabase("testi.db");
            
            createNewTable();
         }
        
       private static void createNewTable() {
            String url = "jdbc:sqlite:C:/sqlite/JTP.db"; 
            String sql = "CREATE TABLE BIKESTATIONS" + 
                    "(fid int NOT NULL UNIQUE," +
                    "id int PRIMARY KEY NOT NULL," +
                    "nimi text NOT NULL," +
                    "namn text NOT NULL," +
                    "name text NOT NULL," +
                    "osoite text NOT NULL," +
                    "adress text NOT NULL," +
                    "kaupunki text NOT NULL," +
                    "stad text NOT NULL," +
                    "operaattor text NOT NULL," +
                    "kapasiteet int NOT NULL," +
                    "x real NOT NULL," +
                    "y real NOT NULL);";
            try{  
                Connection conn = DriverManager.getConnection(url);  
                Statement stmt = conn.createStatement();  
                stmt.execute(sql);  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
    }

    private void insert(String nimi, int raha) {
            String url = "jdbc:sqlite:C:/sqlite/JTP.db"; 
            String sql = "INSERT INTO kayttaja(nimi, raha) VALUES(?,?)";  
            try{ 
                Connection conn = DriverManager.getConnection(url);  
                PreparedStatement pstmt = conn.prepareStatement(sql);  
                pstmt.setString(1, nimi);  
                pstmt.setInt(2, raha);  
                pstmt.executeUpdate();  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
    }

    /**
     *  yhdistetään sql tietokantaan
     */
    public static void connect() {  
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
}
