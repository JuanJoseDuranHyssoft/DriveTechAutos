/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Customers;
import DBManager.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class CustomersModel {

    private ConnectionDB con;
    private ResultSet rs = null;

    public CustomersModel() {
    }

    public boolean addCustomer(CustomersDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("INSERT INTO Customers (name, lastName, phone, email, address) VALUES (");
            query.append("'").append(dto.getName()).append("', ");
            query.append("'").append(dto.getLastName()).append("', ");
            query.append("'").append(dto.getPhone()).append("', ");
            query.append("'").append(dto.getEmail()).append("', ");
            query.append("'").append(dto.getAddress()).append("')");

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in CustomersModel.addCustomer: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean updateCustomer(CustomersDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("UPDATE Customers SET ");
            query.append("name = '").append(dto.getName()).append("', ");
            query.append("lastName = '").append(dto.getLastName()).append("', ");
            query.append("phone = '").append(dto.getPhone()).append("', ");
            query.append("email = '").append(dto.getEmail()).append("', ");
            query.append("address = '").append(dto.getAddress()).append("' ");
            query.append("WHERE idCustomer = ").append(dto.getIdCustomer());

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in CustomersModel.updateCustomer: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean deleteCustomer(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("DELETE FROM Customers WHERE idCustomer = ");
            query.append(id);

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in CustomersModel.deleteCustomer: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public CustomersDto getCustomer(int id) {
        CustomersDto dto = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM Customers WHERE idCustomer = ");
            query.append(id);

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                dto = new CustomersDto();
                dto.setIdCustomer(rs.getInt("idCustomer"));
                dto.setName(rs.getString("name"));
                dto.setLastName(rs.getString("lastName"));
                dto.setPhone(rs.getString("phone"));
                dto.setEmail(rs.getString("email"));
                dto.setAddress(rs.getString("address"));
            }
        } catch (SQLException ex) {
            System.out.println("Error in CustomersModel.getCustomer: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return dto;
    }

    public List<CustomersDto> getAllCustomers() {
        List<CustomersDto> lista = new ArrayList<>();
        try {
            con = new ConnectionDB();
            String query = "SELECT * FROM Customers";
            rs = con.find(query);

            while (rs != null && rs.next()) {
                CustomersDto dto = new CustomersDto();
                dto.setIdCustomer(rs.getInt("idCustomer"));
                dto.setName(rs.getString("name"));
                dto.setLastName(rs.getString("lastName"));
                dto.setPhone(rs.getString("phone"));
                dto.setEmail(rs.getString("email"));
                dto.setAddress(rs.getString("address"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            System.out.println("Error in CustomersModel.getAllCustomers: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return lista;
    }

    public boolean existsCustomer(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT 1 FROM Customers WHERE idCustomer = ");
            query.append(id);

            rs = con.find(query.toString());
            return rs != null && rs.next();
        } catch (SQLException ex) {
            System.out.println("Error in CustomersModel.existsCustomer: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }
}
