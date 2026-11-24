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

    public boolean addUser(UsersDto userDto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("INSERT INTO Users (nameUser, password, role, email) VALUES ('");
            query.append(userDto.getNameUser()).append("', '");
            query.append(userDto.getPassword()).append("', '");
            query.append(userDto.getRole()).append("', '");
            query.append(userDto.getEmail()).append("')");

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in UsersModel.addUser: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean updateUser(UsersDto userDto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("UPDATE Users SET ");
            query.append("nameUser = '").append(userDto.getNameUser()).append("', ");
            query.append("password = '").append(userDto.getPassword()).append("', ");
            query.append("role = '").append(userDto.getRole()).append("', ");
            query.append("email = '").append(userDto.getEmail()).append("' ");
            query.append("WHERE idUser = ").append(userDto.getIdUser());

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in UsersModel.updateUser: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean deleteUser(int userId) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("DELETE FROM Users WHERE idUser = ");
            query.append(userId);

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in UsersModel.deleteUser: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public UsersDto getUser(int userId) {
        UsersDto userDto = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM Users WHERE idUser = ");
            query.append(userId);

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                userDto = new UsersDto();
                userDto.setIdUser(rs.getInt("idUser"));
                userDto.setNameUser(rs.getString("nameUser"));
                userDto.setPassword(rs.getString("password"));
                userDto.setRole(rs.getString("role"));
                userDto.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            System.out.println("Error in UsersModel.getUser: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return userDto;
    }

    public List<UsersDto> getAllUsers() {
        List<UsersDto> listUsersDto = new ArrayList<>();
        try {
            con = new ConnectionDB();
            String query = "SELECT * FROM Users";
            rs = con.find(query);

            while (rs != null && rs.next()) {
                UsersDto user = new UsersDto();
                user.setIdUser(rs.getInt("idUser"));
                user.setNameUser(rs.getString("nameUser"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("email"));
                listUsersDto.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("Error in UsersModel.getAllUsers: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return listUsersDto;
    }

    public UsersDto login(String email, String password) {
        UsersDto user = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM Users WHERE email = '");
            query.append(email).append("' AND password = '").append(password).append("'");

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                user = new UsersDto();
                user.setIdUser(rs.getInt("idUser"));
                user.setNameUser(rs.getString("nameUser"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            System.out.println("Error in UsersModel.login: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return user;
    }

    public boolean existsUser(int userId) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT 1 FROM Users WHERE idUser = ");
            query.append(userId);

            rs = con.find(query.toString());
            return rs != null && rs.next();
        } catch (SQLException ex) {
            System.out.println("Error in UsersModel.existsUser: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }
    
    public UsersDto getUserByEmail(String email) {
        UsersDto user = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM Users WHERE email = '");
            query.append(email).append("'");

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                user = new UsersDto();
                user.setIdUser(rs.getInt("idUser"));
                user.setNameUser(rs.getString("nameUser"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            System.out.println("Error in UsersModel.getUserByEmail: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return user;
    }

}
