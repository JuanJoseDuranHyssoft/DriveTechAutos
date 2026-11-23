/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Empleado;
import DBManager.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class EmpleadoModel {

    private ConnectionDB con;
    private ResultSet rs = null;

    public EmpleadoModel() {
    }

    public boolean addEmpleado(EmpleadoDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("INSERT INTO Empleado (nombre, apellido, cargo, telefono) VALUES (");
            query.append("'").append(dto.getNombre()).append("', ");
            query.append("'").append(dto.getApellido()).append("', ");
            query.append("'").append(dto.getCargo()).append("', ");
            query.append("'").append(dto.getTelefono()).append("')");

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en EmpleadoModel.addEmpleado: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean updateEmpleado(EmpleadoDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("UPDATE Empleado SET ");
            query.append("nombre = '").append(dto.getNombre()).append("', ");
            query.append("apellido = '").append(dto.getApellido()).append("', ");
            query.append("cargo = '").append(dto.getCargo()).append("', ");
            query.append("telefono = '").append(dto.getTelefono()).append("' ");
            query.append("WHERE idEmpleado = ").append(dto.getIdEmpleado());

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en EmpleadoModel.updateEmpleado: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean deleteEmpleado(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("DELETE FROM Empleado WHERE idEmpleado = ");
            query.append(id);

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en EmpleadoModel.deleteEmpleado: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public EmpleadoDto getEmpleado(int id) {
        EmpleadoDto dto = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM Empleado WHERE idEmpleado = ");
            query.append(id);

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                dto = new EmpleadoDto();
                dto.setIdEmpleado(rs.getInt("idEmpleado"));
                dto.setNombre(rs.getString("nombre"));
                dto.setApellido(rs.getString("apellido"));
                dto.setCargo(rs.getString("cargo"));
                dto.setTelefono(rs.getString("telefono"));
            }
        } catch (SQLException ex) {
            System.out.println("Error en EmpleadoModel.getEmpleado: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return dto;
    }

    public List<EmpleadoDto> getAllEmpleados() {
        List<EmpleadoDto> lista = new ArrayList<>();
        try {
            con = new ConnectionDB();
            String query = "SELECT * FROM Empleado";
            rs = con.find(query);

            while (rs != null && rs.next()) {
                EmpleadoDto dto = new EmpleadoDto();
                dto.setIdEmpleado(rs.getInt("idEmpleado"));
                dto.setNombre(rs.getString("nombre"));
                dto.setApellido(rs.getString("apellido"));
                dto.setCargo(rs.getString("cargo"));
                dto.setTelefono(rs.getString("telefono"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            System.out.println("Error en EmpleadoModel.getAllEmpleados: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return lista;
    }

    public boolean existsEmpleado(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT 1 FROM Empleado WHERE idEmpleado = ");
            query.append(id);

            rs = con.find(query.toString());
            return rs != null && rs.next();
        } catch (SQLException ex) {
            System.out.println("Error en EmpleadoModel.existsEmpleado: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }
}
