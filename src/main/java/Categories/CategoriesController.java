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
    private CategoriesModel categoriaModel;

    public CategoriesController() {
        this.categoriaModel = new CategoriesModel();
    }

    public boolean addCategoria(CategoriesDto dto) {
        try {
            return categoriaModel.addCategory(dto);
        } catch (Exception ex) {
            System.out.println("Error en CategoriaController.addCategoria: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean updateCategoria(CategoriesDto dto) {
        try {
            return categoriaModel.updateCategory(dto);
        } catch (Exception ex) {
            System.out.println("Error en CategoriaController.updateCategoria: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteCategoria(int id) {
        try {
            return categoriaModel.deleteCategory(id);
        } catch (Exception ex) {
            System.out.println("Error en CategoriaController.deleteCategoria: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public CategoriesDto getCategoria(int id) {
        try {
            return categoriaModel.getCategory(id);
        } catch (Exception ex) {
            System.out.println("Error en CategoriaController.getCategoria: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public List<CategoriesDto> getAllCategorias() {
        try {
            return categoriaModel.getAllCategories();
        } catch (Exception ex) {
            System.out.println("Error en CategoriaController.getAllCategorias: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public boolean existsCategoria(int id) {
        try {
            return categoriaModel.existsCategory(id);
        } catch (Exception ex) {
            System.out.println("Error en CategoriaController.existsCategoria: " + ex.getLocalizedMessage());
            return false;
        }
    }
}
