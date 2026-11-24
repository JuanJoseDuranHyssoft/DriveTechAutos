/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MetodoPago;
import DBManager.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class MetodoPagoModel {

    private ConnectionDB con;
    private ResultSet rs = null;

    public MetodoPagoModel() {
    }

    public boolean addMetodoPago(MetodoPagoDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("INSERT INTO MetodoPago (descripcion) VALUES (");
            query.append("'").append(dto.getDescripcion()).append("')");

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en MetodoPagoModel.addMetodoPago: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean updateMetodoPago(MetodoPagoDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("UPDATE MetodoPago SET ");
            query.append("descripcion = '").append(dto.getDescripcion()).append("' ");
            query.append("WHERE idMetodoPago = ").append(dto.getIdMetodoPago());

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en MetodoPagoModel.updateMetodoPago: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean deleteMetodoPago(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("DELETE FROM MetodoPago WHERE idMetodoPago = ");
            query.append(id);

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en MetodoPagoModel.deleteMetodoPago: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public MetodoPagoDto getMetodoPago(int id) {
        MetodoPagoDto dto = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM MetodoPago WHERE idMetodoPago = ");
            query.append(id);

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                dto = new MetodoPagoDto();
                dto.setIdMetodoPago(rs.getInt("idMetodoPago"));
                dto.setDescripcion(rs.getString("descripcion"));
            }
        } catch (SQLException ex) {
            System.out.println("Error en MetodoPagoModel.getMetodoPago: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return dto;
    }

    public List<MetodoPagoDto> getAllMetodosPago() {
        List<MetodoPagoDto> lista = new ArrayList<>();
        try {
            con = new ConnectionDB();
            String query = "SELECT * FROM MetodoPago";
            rs = con.find(query);

            while (rs != null && rs.next()) {
                MetodoPagoDto dto = new MetodoPagoDto();
                dto.setIdMetodoPago(rs.getInt("idMetodoPago"));
                dto.setDescripcion(rs.getString("descripcion"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            System.out.println("Error en MetodoPagoModel.getAllMetodosPago: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return lista;
    }

    public boolean existsMetodoPago(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT 1 FROM MetodoPago WHERE idMetodoPago = ");
            query.append(id);

            rs = con.find(query.toString());
            return rs != null && rs.next();
        } catch (SQLException ex) {
            System.out.println("Error en MetodoPagoModel.existsMetodoPago: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }
}
