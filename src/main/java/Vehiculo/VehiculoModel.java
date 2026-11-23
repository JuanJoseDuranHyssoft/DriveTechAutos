/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculo;
import DBManager.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class VehiculoModel {

    private ConnectionDB con;
    private ResultSet rs = null;

    public VehiculoModel() {
    }

    public boolean addVehiculo(VehiculoDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("INSERT INTO Vehiculo (vin, marca, modelo, anio, precio, estado, idCategoria) VALUES (");
            query.append("'").append(dto.getVin()).append("', ");
            query.append("'").append(dto.getMarca()).append("', ");
            query.append("'").append(dto.getModelo()).append("', ");
            query.append(dto.getAnio()).append(", ");
            query.append(dto.getPrecio()).append(", ");
            query.append("'").append(dto.getEstado()).append("', ");
            query.append(dto.getIdCategoria()).append(")");

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en VehiculoModel.addVehiculo: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean updateVehiculo(VehiculoDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("UPDATE Vehiculo SET ");
            query.append("vin = '").append(dto.getVin()).append("', ");
            query.append("marca = '").append(dto.getMarca()).append("', ");
            query.append("modelo = '").append(dto.getModelo()).append("', ");
            query.append("anio = ").append(dto.getAnio()).append(", ");
            query.append("precio = ").append(dto.getPrecio()).append(", ");
            query.append("estado = '").append(dto.getEstado()).append("', ");
            query.append("idCategoria = ").append(dto.getIdCategoria()).append(" ");
            query.append("WHERE idVehiculo = ").append(dto.getIdVehiculo());

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en VehiculoModel.updateVehiculo: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean deleteVehiculo(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("DELETE FROM Vehiculo WHERE idVehiculo = ");
            query.append(id);

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en VehiculoModel.deleteVehiculo: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public VehiculoDto getVehiculo(int id) {
        VehiculoDto dto = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM Vehiculo WHERE idVehiculo = ");
            query.append(id);

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                dto = new VehiculoDto();
                dto.setIdVehiculo(rs.getInt("idVehiculo"));
                dto.setVin(rs.getString("vin"));
                dto.setMarca(rs.getString("marca"));
                dto.setModelo(rs.getString("modelo"));
                dto.setAnio(rs.getInt("anio"));
                dto.setPrecio(rs.getDouble("precio"));
                dto.setEstado(rs.getString("estado"));
                dto.setIdCategoria(rs.getInt("idCategoria"));
            }
        } catch (SQLException ex) {
            System.out.println("Error en VehiculoModel.getVehiculo: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return dto;
    }

    public List<VehiculoDto> getAllVehiculos() {
        List<VehiculoDto> lista = new ArrayList<>();
        try {
            con = new ConnectionDB();
            String query = "SELECT * FROM Vehiculo";
            rs = con.find(query);

            while (rs != null && rs.next()) {
                VehiculoDto dto = new VehiculoDto();
                dto.setIdVehiculo(rs.getInt("idVehiculo"));
                dto.setVin(rs.getString("vin"));
                dto.setMarca(rs.getString("marca"));
                dto.setModelo(rs.getString("modelo"));
                dto.setAnio(rs.getInt("anio"));
                dto.setPrecio(rs.getDouble("precio"));
                dto.setEstado(rs.getString("estado"));
                dto.setIdCategoria(rs.getInt("idCategoria"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            System.out.println("Error en VehiculoModel.getAllVehiculos: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return lista;
    }

    public boolean existsVehiculo(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT 1 FROM Vehiculo WHERE idVehiculo = ");
            query.append(id);

            rs = con.find(query.toString());
            return rs != null && rs.next();
        } catch (SQLException ex) {
            System.out.println("Error en VehiculoModel.existsVehiculo: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }
}
