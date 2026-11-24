/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Employees;
import DBManager.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class EmployeesModel {

    private ConnectionDB con;
    private ResultSet rs = null;

    public EmployeesModel() {
    }

    public boolean addEmployee(EmployeesDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("INSERT INTO Employees (name, lastName, position, phone) VALUES (");
            query.append("'").append(dto.getName()).append("', ");
            query.append("'").append(dto.getLastName()).append("', ");
            query.append("'").append(dto.getPosition()).append("', ");
            query.append("'").append(dto.getPhone()).append("')");

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in EmployeesModel.addEmployee: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean updateEmployee(EmployeesDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("UPDATE Employees SET ");
            query.append("name = '").append(dto.getName()).append("', ");
            query.append("lastName = '").append(dto.getLastName()).append("', ");
            query.append("position = '").append(dto.getPosition()).append("', ");
            query.append("phone = '").append(dto.getPhone()).append("' ");
            query.append("WHERE idEmployee = ").append(dto.getIdEmployee());

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in EmployeesModel.updateEmployee: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean deleteEmployee(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("DELETE FROM Employees WHERE idEmployee = ");
            query.append(id);

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in EmployeesModel.deleteEmployee: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public EmployeesDto getEmployee(int id) {
        EmployeesDto dto = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM Employees WHERE idEmployee = ");
            query.append(id);

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                dto = new EmployeesDto();
                dto.setIdEmployee(rs.getInt("idEmployee"));
                dto.setName(rs.getString("name"));
                dto.setLastName(rs.getString("lastName"));
                dto.setPosition(rs.getString("position"));
                dto.setPhone(rs.getString("phone"));
            }
        } catch (SQLException ex) {
            System.out.println("Error in EmployeesModel.getEmployee: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return dto;
    }

    public List<EmployeesDto> getAllEmployees() {
        List<EmployeesDto> lista = new ArrayList<>();
        try {
            con = new ConnectionDB();
            String query = "SELECT * FROM Employees";
            rs = con.find(query);

            while (rs != null && rs.next()) {
                EmployeesDto dto = new EmployeesDto();
                dto.setIdEmployee(rs.getInt("idEmployee"));
                dto.setName(rs.getString("name"));
                dto.setLastName(rs.getString("lastName"));
                dto.setPosition(rs.getString("position"));
                dto.setPhone(rs.getString("phone"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            System.out.println("Error in EmployeesModel.getAllEmployees: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return lista;
    }

    public boolean existsEmployee(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT 1 FROM Employees WHERE idEmployee = ");
            query.append(id);

            rs = con.find(query.toString());
            return rs != null && rs.next();
        } catch (SQLException ex) {
            System.out.println("Error in EmployeesModel.existsEmployee: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }
}
