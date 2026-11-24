/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Venta;
import DBManager.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class VentaModel {

    private ConnectionDB con;
    private ResultSet rs = null;

    public VentaModel() {
    }

    public boolean addVenta(VentaDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("INSERT INTO Venta (fechaVenta, precioFinal, idVehiculo, idCliente, idEmpleado, idMetodoPago) VALUES (");
            query.append("'").append(dto.getFechaVenta()).append("', ");
            query.append(dto.getPrecioFinal()).append(", ");
            query.append(dto.getIdVehiculo()).append(", ");
            query.append(dto.getIdCliente()).append(", ");
            query.append(dto.getIdEmpleado()).append(", ");
            query.append(dto.getIdMetodoPago()).append(")");

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en VentaModel.addVenta: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean updateVenta(VentaDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("UPDATE Venta SET ");
            query.append("fechaVenta = '").append(dto.getFechaVenta()).append("', ");
            query.append("precioFinal = ").append(dto.getPrecioFinal()).append(", ");
            query.append("idVehiculo = ").append(dto.getIdVehiculo()).append(", ");
            query.append("idCliente = ").append(dto.getIdCliente()).append(", ");
            query.append("idEmpleado = ").append(dto.getIdEmpleado()).append(", ");
            query.append("idMetodoPago = ").append(dto.getIdMetodoPago()).append(" ");
            query.append("WHERE idVenta = ").append(dto.getIdVenta());

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en VentaModel.updateVenta: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean deleteVenta(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("DELETE FROM Venta WHERE idVenta = ");
            query.append(id);

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en VentaModel.deleteVenta: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public VentaDto getVenta(int id) {
        VentaDto dto = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM Venta WHERE idVenta = ");
            query.append(id);

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                dto = new VentaDto();
                dto.setIdVenta(rs.getInt("idVenta"));
                dto.setFechaVenta(rs.getString("fechaVenta"));
                dto.setPrecioFinal(rs.getDouble("precioFinal"));
                dto.setIdVehiculo(rs.getInt("idVehiculo"));
                dto.setIdCliente(rs.getInt("idCliente"));
                dto.setIdEmpleado(rs.getInt("idEmpleado"));
                dto.setIdMetodoPago(rs.getInt("idMetodoPago"));
            }
        } catch (SQLException ex) {
            System.out.println("Error en VentaModel.getVenta: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return dto;
    }

    public List<VentaDto> getAllVentas() {
        List<VentaDto> lista = new ArrayList<>();
        try {
            con = new ConnectionDB();
            String query = "SELECT * FROM Venta";
            rs = con.find(query);

            while (rs != null && rs.next()) {
                VentaDto dto = new VentaDto();
                dto.setIdVenta(rs.getInt("idVenta"));
                dto.setFechaVenta(rs.getString("fechaVenta"));
                dto.setPrecioFinal(rs.getDouble("precioFinal"));
                dto.setIdVehiculo(rs.getInt("idVehiculo"));
                dto.setIdCliente(rs.getInt("idCliente"));
                dto.setIdEmpleado(rs.getInt("idEmpleado"));
                dto.setIdMetodoPago(rs.getInt("idMetodoPago"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            System.out.println("Error en VentaModel.getAllVentas: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return lista;
    }

    public boolean existsVenta(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT 1 FROM Venta WHERE idVenta = ");
            query.append(id);

            rs = con.find(query.toString());
            return rs != null && rs.next();
        } catch (SQLException ex) {
            System.out.println("Error en VentaModel.existsVenta: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }
}
