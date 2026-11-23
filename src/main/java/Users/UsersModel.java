/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;
import DBManager.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class UsersModel {
    
    private ConnectionDB con;
    private ResultSet rs = null;

    public UsersModel() {
    }

    public boolean addUsuario(UsersDto userDto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("INSERT INTO Usuario (nombreUsuario, contrasenia, rol, email) VALUES ('");
            query.append(userDto.getNombreUsuario()).append("', '");
            query.append(userDto.getContrasenia()).append("', '");
            query.append(userDto.getRol()).append("', '");
            query.append(userDto.getEmail()).append("')");

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en UsuarioModel.addUsuario: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean updateUsuario(UsersDto userDto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("UPDATE Usuario SET ");
            query.append("nombreUsuario = '").append(userDto.getNombreUsuario()).append("', ");
            query.append("contrasenia = '").append(userDto.getContrasenia()).append("', ");
            query.append("rol = '").append(userDto.getRol()).append("', ");
            query.append("email = '").append(userDto.getEmail()).append("' ");
            query.append("WHERE idUsuario = ").append(userDto.getIdUsuario());

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en UsuarioModel.updateUsuario: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean deleteUsuario(int userId) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("DELETE FROM Usuario WHERE idUsuario = ");
            query.append(userId);

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error en UsuarioModel.deleteUsuario: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public UsersDto getUsuario(int userId) {
        UsersDto userDto = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM Usuario WHERE idUsuario = ");
            query.append(userId);

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                userDto = new UsersDto();
                userDto.setIdUsuario(rs.getInt("idUsuario"));
                userDto.setNombreUsuario(rs.getString("nombreUsuario"));
                userDto.setContrasenia(rs.getString("contrasenia"));
                userDto.setRol(rs.getString("rol"));
                userDto.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            System.out.println("Error en UsuarioModel.getUsuario: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return userDto;
    }

    public List<UsersDto> getAllUsuarios() {
        List<UsersDto> listUsersDto = new ArrayList<>();
        try {
            con = new ConnectionDB();
            String query = "SELECT * FROM Usuario";
            rs = con.find(query);

            while (rs != null && rs.next()) {
                UsersDto user = new UsersDto();
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setNombreUsuario(rs.getString("nombreUsuario"));
                user.setContrasenia(rs.getString("contrasenia"));
                user.setRol(rs.getString("rol"));
                user.setEmail(rs.getString("email"));
                listUsersDto.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("Error en UsuarioModel.getAllUsuarios: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return listUsersDto;
    }

    public UsersDto login(String email, String contrasenia) {
        UsersDto user = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM Usuario WHERE email = '");
            query.append(email).append("' AND contrasenia = '").append(contrasenia).append("'");

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                user = new UsersDto();
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setNombreUsuario(rs.getString("nombreUsuario"));
                user.setContrasenia(rs.getString("contrasenia"));
                user.setRol(rs.getString("rol"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            System.out.println("Error en UsuarioModel.login: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return user;
    }

    public boolean existsUsuario(int userId) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT 1 FROM Usuario WHERE idUsuario = ");
            query.append(userId);

            rs = con.find(query.toString());
            return rs != null && rs.next();
        } catch (SQLException ex) {
            System.out.println("Error en UsuarioModel.existsUsuario: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }
    
    public UsersDto getUsuarioByEmail(String email) {
        UsersDto user = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM Usuario WHERE email = '");
            query.append(email).append("'");

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                user = new UsersDto();
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setNombreUsuario(rs.getString("nombreUsuario"));
                user.setContrasenia(rs.getString("contrasenia"));
                user.setRol(rs.getString("rol"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            System.out.println("Error en UsuarioModel.getUsuarioByEmail: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return user;
    }

}
