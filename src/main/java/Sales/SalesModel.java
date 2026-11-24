/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sales;
import DBManager.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class SalesModel {

    private ConnectionDB con;
    private ResultSet rs = null;

    public SalesModel() {
    }

    public boolean addSale(SalesDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("INSERT INTO Sales (saleDate, finalPrice, idVehicle, idCustomer, idEmployee, idPaymentMethod) VALUES (");
            query.append("'").append(dto.getSaleDate()).append("', ");
            query.append(dto.getFinalPrice()).append(", ");
            query.append(dto.getIdVehicle()).append(", ");
            query.append(dto.getIdCustomer()).append(", ");
            query.append(dto.getIdEmployee()).append(", ");
            query.append(dto.getIdPaymentMethod()).append(")");

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in SalesModel.addSale: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean updateSale(SalesDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("UPDATE Sales SET ");
            query.append("saleDate = '").append(dto.getSaleDate()).append("', ");
            query.append("finalPrice = ").append(dto.getFinalPrice()).append(", ");
            query.append("idVehicle = ").append(dto.getIdVehicle()).append(", ");
            query.append("idCustomer = ").append(dto.getIdCustomer()).append(", ");
            query.append("idEmployee = ").append(dto.getIdEmployee()).append(", ");
            query.append("idPaymentMethod = ").append(dto.getIdPaymentMethod()).append(" ");
            query.append("WHERE idSale = ").append(dto.getIdSale());

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in SalesModel.updateSale: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean deleteSale(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("DELETE FROM Sales WHERE idSale = ");
            query.append(id);

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in SalesModel.deleteSale: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public SalesDto getSale(int id) {
        SalesDto dto = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM Sales WHERE idSale = ");
            query.append(id);

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                dto = new SalesDto();
                dto.setIdSale(rs.getInt("idSale"));
                dto.setSaleDate(rs.getString("saleDate"));
                dto.setFinalPrice(rs.getDouble("finalPrice"));
                dto.setIdVehicle(rs.getInt("idVehicle"));
                dto.setIdCustomer(rs.getInt("idCustomer"));
                dto.setIdEmployee(rs.getInt("idEmployee"));
                dto.setIdPaymentMethod(rs.getInt("idPaymentMethod"));
            }
        } catch (SQLException ex) {
            System.out.println("Error in SalesModel.getSale: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return dto;
    }

    public List<SalesDto> getAllSales() {
        List<SalesDto> lista = new ArrayList<>();
        try {
            con = new ConnectionDB();
            String query = "SELECT * FROM Sales";
            rs = con.find(query);

            while (rs != null && rs.next()) {
                SalesDto dto = new SalesDto();
                dto.setIdSale(rs.getInt("idSale"));
                dto.setSaleDate(rs.getString("saleDate"));
                dto.setFinalPrice(rs.getDouble("finalPrice"));
                dto.setIdVehicle(rs.getInt("idVehicle"));
                dto.setIdCustomer(rs.getInt("idCustomer"));
                dto.setIdEmployee(rs.getInt("idEmployee"));
                dto.setIdPaymentMethod(rs.getInt("idPaymentMethod"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            System.out.println("Error in SalesModel.getAllSales: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return lista;
    }

    public boolean existsSale(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT 1 FROM Sales WHERE idSale = ");
            query.append(id);

            rs = con.find(query.toString());
            return rs != null && rs.next();
        } catch (SQLException ex) {
            System.out.println("Error in SalesModel.existsSale: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }
}
