/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Categories;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class CategoriesController {
    private CategoriesModel categoriesModel;

    public CategoriesController() {
        this.categoriesModel = new CategoriesModel();
    }

    public boolean addCategory(CategoriesDto dto) {
        try {
            return categoriesModel.addCategory(dto);
        } catch (Exception ex) {
            System.out.println("Error in CategoriesController.addCategory: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean updateCategory(CategoriesDto dto) {
        try {
            return categoriesModel.updateCategory(dto);
        } catch (Exception ex) {
            System.out.println("Error in CategoriesController.updateCategory: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteCategory(int id) {
        try {
            return categoriesModel.deleteCategory(id);
        } catch (Exception ex) {
            System.out.println("Error in CategoriesController.deleteCategory: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public CategoriesDto getCategory(int id) {
        try {
            return categoriesModel.getCategory(id);
        } catch (Exception ex) {
            System.out.println("Error in CategoriesController.getCategory: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public List<CategoriesDto> getAllCategories() {
        try {
            return categoriesModel.getAllCategories();
        } catch (Exception ex) {
            System.out.println("Error in CategoriesController.getAllCategories: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public boolean existsCategory(int id) {
        try {
            return categoriesModel.existsCategory(id);
        } catch (Exception ex) {
            System.out.println("Error in CategoriesController.existsCategory: " + ex.getLocalizedMessage());
            return false;
        }
    }
}
