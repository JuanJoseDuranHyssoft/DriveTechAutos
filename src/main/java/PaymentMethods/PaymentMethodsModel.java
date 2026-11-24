/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaymentMethods;
import DBManager.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class PaymentMethodsModel {

    private ConnectionDB con;
    private ResultSet rs = null;

    public PaymentMethodsModel() {
    }

    public boolean addPaymentMethod(PaymentMethodsDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("INSERT INTO PaymentMethods (description) VALUES (");
            query.append("'").append(dto.getDescription()).append("')");

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in PaymentMethodsModel.addPaymentMethod: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean updatePaymentMethod(PaymentMethodsDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("UPDATE PaymentMethods SET ");
            query.append("description = '").append(dto.getDescription()).append("' ");
            query.append("WHERE idPaymentMethod = ").append(dto.getIdPaymentMethod());

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in PaymentMethodsModel.updatePaymentMethod: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean deletePaymentMethod(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("DELETE FROM PaymentMethods WHERE idPaymentMethod = ");
            query.append(id);

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in PaymentMethodsModel.deletePaymentMethod: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public PaymentMethodsDto getPaymentMethod(int id) {
        PaymentMethodsDto dto = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM PaymentMethods WHERE idPaymentMethod = ");
            query.append(id);

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                dto = new PaymentMethodsDto();
                dto.setIdPaymentMethod(rs.getInt("idPaymentMethod"));
                dto.setDescription(rs.getString("description"));
            }
        } catch (SQLException ex) {
            System.out.println("Error in PaymentMethodsModel.getPaymentMethod: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return dto;
    }

    public List<PaymentMethodsDto> getAllPaymentMethods() {
        List<PaymentMethodsDto> lista = new ArrayList<>();
        try {
            con = new ConnectionDB();
            String query = "SELECT * FROM PaymentMethods";
            rs = con.find(query);

            while (rs != null && rs.next()) {
                PaymentMethodsDto dto = new PaymentMethodsDto();
                dto.setIdPaymentMethod(rs.getInt("idPaymentMethod"));
                dto.setDescription(rs.getString("description"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            System.out.println("Error in PaymentMethodsModel.getAllPaymentMethods: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return lista;
    }

    public boolean existsPaymentMethod(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT 1 FROM PaymentMethods WHERE idPaymentMethod = ");
            query.append(id);

            rs = con.find(query.toString());
            return rs != null && rs.next();
        } catch (SQLException ex) {
            System.out.println("Error in PaymentMethodsModel.existsPaymentMethod: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }
}
