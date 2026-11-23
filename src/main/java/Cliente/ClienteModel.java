/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;
import DBManager.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class ClienteModel {

    private ConnectionDB con;
    private ResultSet rs = null;

    public ClienteModel() {
    }

    public boolean addCliente(ClienteDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("INSERT INTO Cliente (nombre, apellido, telefono, email, direccion) VALUES (");
            query.append("'").append(dto.getNombre()).append("', ");
            query.append("'").append(dto.getApellido()).append("', ");
            query.append("'").append(dto.getTelefono()).append("', ");
            query.append("'").append(dto.getEmail()).append("', ");
            query.append("'").append(dto.getDireccion()).append("')");

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en ClienteModel.addCliente: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean updateCliente(ClienteDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("UPDATE Cliente SET ");
            query.append("nombre = '").append(dto.getNombre()).append("', ");
            query.append("apellido = '").append(dto.getApellido()).append("', ");
            query.append("telefono = '").append(dto.getTelefono()).append("', ");
            query.append("email = '").append(dto.getEmail()).append("', ");
            query.append("direccion = '").append(dto.getDireccion()).append("' ");
            query.append("WHERE idCliente = ").append(dto.getIdCliente());

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en ClienteModel.updateCliente: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean deleteCliente(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("DELETE FROM Cliente WHERE idCliente = ");
            query.append(id);

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en ClienteModel.deleteCliente: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public ClienteDto getCliente(int id) {
        ClienteDto dto = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM Cliente WHERE idCliente = ");
            query.append(id);

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                dto = new ClienteDto();
                dto.setIdCliente(rs.getInt("idCliente"));
                dto.setNombre(rs.getString("nombre"));
                dto.setApellido(rs.getString("apellido"));
                dto.setTelefono(rs.getString("telefono"));
                dto.setEmail(rs.getString("email"));
                dto.setDireccion(rs.getString("direccion"));
            }
        } catch (SQLException ex) {
            System.out.println("Error en ClienteModel.getCliente: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return dto;
    }

    public List<ClienteDto> getAllClientes() {
        List<ClienteDto> lista = new ArrayList<>();
        try {
            con = new ConnectionDB();
            String query = "SELECT * FROM Cliente";
            rs = con.find(query);

            while (rs != null && rs.next()) {
                ClienteDto dto = new ClienteDto();
                dto.setIdCliente(rs.getInt("idCliente"));
                dto.setNombre(rs.getString("nombre"));
                dto.setApellido(rs.getString("apellido"));
                dto.setTelefono(rs.getString("telefono"));
                dto.setEmail(rs.getString("email"));
                dto.setDireccion(rs.getString("direccion"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            System.out.println("Error en ClienteModel.getAllClientes: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return lista;
    }

    public boolean existsCliente(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT 1 FROM Cliente WHERE idCliente = ");
            query.append(id);

            rs = con.find(query.toString());
            return rs != null && rs.next();
        } catch (SQLException ex) {
            System.out.println("Error en ClienteModel.existsCliente: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }
}
