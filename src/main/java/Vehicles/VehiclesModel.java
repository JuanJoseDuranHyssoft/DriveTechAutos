/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehicles;
import DBManager.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class VehiclesModel {

    private ConnectionDB con;
    private ResultSet rs = null;

    public VehiclesModel() {
    }

    public boolean addVehicle(VehiclesDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("INSERT INTO Vehicles (vin, brand, model, year, price, status, idCategory) VALUES (");
            query.append("'").append(dto.getVin()).append("', ");
            query.append("'").append(dto.getBrand()).append("', ");
            query.append("'").append(dto.getModel()).append("', ");
            query.append(dto.getYear()).append(", ");
            query.append(dto.getPrice()).append(", ");
            query.append("'").append(dto.getStatus()).append("', ");
            query.append(dto.getIdCategory()).append(")");

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in VehiclesModel.addVehicle: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean updateVehicle(VehiclesDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("UPDATE Vehicles SET ");
            query.append("vin = '").append(dto.getVin()).append("', ");
            query.append("brand = '").append(dto.getBrand()).append("', ");
            query.append("model = '").append(dto.getModel()).append("', ");
            query.append("year = ").append(dto.getYear()).append(", ");
            query.append("price = ").append(dto.getPrice()).append(", ");
            query.append("status = '").append(dto.getStatus()).append("', ");
            query.append("idCategory = ").append(dto.getIdCategory()).append(" ");
            query.append("WHERE idVehicle = ").append(dto.getIdVehicle());

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in VehiclesModel.updateVehicle: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean deleteVehicle(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("DELETE FROM Vehicles WHERE idVehicle = ");
            query.append(id);

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in VehiclesModel.deleteVehicle: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public VehiclesDto getVehicle(int id) {
        VehiclesDto dto = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM Vehicles WHERE idVehicle = ");
            query.append(id);

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                dto = new VehiclesDto();
                dto.setIdVehicle(rs.getInt("idVehicle"));
                dto.setVin(rs.getString("vin"));
                dto.setBrand(rs.getString("brand"));
                dto.setModel(rs.getString("model"));
                dto.setYear(rs.getInt("year"));
                dto.setPrice(rs.getDouble("price"));
                dto.setStatus(rs.getString("status"));
                dto.setIdCategory(rs.getInt("idCategory"));
            }
        } catch (SQLException ex) {
            System.out.println("Error in VehiclesModel.getVehicle: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return dto;
    }

    public List<VehiclesDto> getAllVehicles() {
        List<VehiclesDto> lista = new ArrayList<>();
        try {
            con = new ConnectionDB();
            String query = "SELECT * FROM Vehicles";
            rs = con.find(query);

            while (rs != null && rs.next()) {
                VehiclesDto dto = new VehiclesDto();
                dto.setIdVehicle(rs.getInt("idVehicle"));
                dto.setVin(rs.getString("vin"));
                dto.setBrand(rs.getString("brand"));
                dto.setModel(rs.getString("model"));
                dto.setYear(rs.getInt("year"));
                dto.setPrice(rs.getDouble("price"));
                dto.setStatus(rs.getString("status"));
                dto.setIdCategory(rs.getInt("idCategory"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            System.out.println("Error in VehiclesModel.getAllVehicles: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return lista;
    }

    public boolean existsVehicle(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT 1 FROM Vehicles WHERE idVehicle = ");
            query.append(id);

            rs = con.find(query.toString());
            return rs != null && rs.next();
        } catch (SQLException ex) {
            System.out.println("Error in VehiclesModel.existsVehicle: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }
}
