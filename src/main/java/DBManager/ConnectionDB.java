package DBManager;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {

    private String url = "src/main/java/DBManager/";
    private String driver = "jdbc:sqlite";
    private String db = "DriveTech.db";
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public ConnectionDB() {
        String address = driver + ":" + url + db;
        try {
            con = DriverManager.getConnection(address);
            if (con != null) {
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("DataBase connected: " + meta.getDriverName());
            }
        } catch (SQLException ex) {
            System.out.println("Connection error: " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return con;
    }

    public ResultSet find(String query) {
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            System.out.println("Error en ConnectionDB.find: " + ex.getMessage());
        }
        return null;
    }

    public int executeUpdate(String query) {
        int result = 0;
        try {
            stmt = con.createStatement();
            result = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("Error en ConnectionDB.executeUpdate: " + ex.getMessage());
        }
        return result;
    }

    public void closeConnection() {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar conexión: " + ex.getMessage());
        }
    }
}