/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Categories;
import DBManager.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class CategoriesModel {

    private ConnectionDB con;
    private ResultSet rs = null;

    public CategoriesModel() {
    }

    public boolean addCategory(CategoriesDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("INSERT INTO Categories (nameCategory) VALUES (");
            query.append("'").append(dto.getNameCategory()).append("')");

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in CategoriesModel.addCategory: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean updateCategory(CategoriesDto dto) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("UPDATE Categories SET ");
            query.append("nameCategory = '").append(dto.getNameCategory()).append("' ");
            query.append("WHERE idCategory = ").append(dto.getIdCategory());

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in CategoriesModel.updateCategory: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public boolean deleteCategory(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("DELETE FROM Categories WHERE idCategory = ");
            query.append(id);

            return con.executeUpdate(query.toString()) > 0;
        } catch (Exception ex) {
            System.out.println("Error in CategoriesModel.deleteCategory: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }

    public CategoriesDto getCategory(int id) {
        CategoriesDto dto = null;
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT * FROM Categories WHERE idCategory = ");
            query.append(id);

            rs = con.find(query.toString());

            if (rs != null && rs.next()) {
                dto = new CategoriesDto();
                dto.setIdCategory(rs.getInt("idCategory"));
                dto.setNameCategory(rs.getString("nameCategory"));
            }
        } catch (SQLException ex) {
            System.out.println("Error in CategoriesModel.getCategory: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return dto;
    }

    public List<CategoriesDto> getAllCategories() {
        List<CategoriesDto> lista = new ArrayList<>();
        try {
            con = new ConnectionDB();
            String query = "SELECT * FROM Categories";
            rs = con.find(query);

            while (rs != null && rs.next()) {
                CategoriesDto dto = new CategoriesDto();
                dto.setIdCategory(rs.getInt("idCategory"));
                dto.setNameCategory(rs.getString("nameCategory"));
                lista.add(dto);
            }
        } catch (SQLException ex) {
            System.out.println("Error in CategoriesModel.getAllCategories: " + ex.getLocalizedMessage());
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
        return lista;
    }

    public boolean existsCategory(int id) {
        try {
            con = new ConnectionDB();
            StringBuilder query = new StringBuilder("SELECT 1 FROM Categories WHERE idCategory = ");
            query.append(id);

            rs = con.find(query.toString());
            return rs != null && rs.next();
        } catch (SQLException ex) {
            System.out.println("Error in CategoriesModel.existsCategory: " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (con != null) {
                con.closeConnection();
            }
        }
    }
}
