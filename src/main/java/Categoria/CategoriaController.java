/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Categoria;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class CategoriaController {
    private CategoriaModel categoriaModel;

    public CategoriaController() {
        this.categoriaModel = new CategoriaModel();
    }

    public boolean addCategoria(CategoriaDto dto) {
        try {
            return categoriaModel.addCategoria(dto);
        } catch (Exception ex) {
            System.out.println("Error en CategoriaController.addCategoria: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean updateCategoria(CategoriaDto dto) {
        try {
            return categoriaModel.updateCategoria(dto);
        } catch (Exception ex) {
            System.out.println("Error en CategoriaController.updateCategoria: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteCategoria(int id) {
        try {
            return categoriaModel.deleteCategoria(id);
        } catch (Exception ex) {
            System.out.println("Error en CategoriaController.deleteCategoria: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public CategoriaDto getCategoria(int id) {
        try {
            return categoriaModel.getCategoria(id);
        } catch (Exception ex) {
            System.out.println("Error en CategoriaController.getCategoria: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public List<CategoriaDto> getAllCategorias() {
        try {
            return categoriaModel.getAllCategorias();
        } catch (Exception ex) {
            System.out.println("Error en CategoriaController.getAllCategorias: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public boolean existsCategoria(int id) {
        try {
            return categoriaModel.existsCategoria(id);
        } catch (Exception ex) {
            System.out.println("Error en CategoriaController.existsCategoria: " + ex.getLocalizedMessage());
            return false;
        }
    }
}
