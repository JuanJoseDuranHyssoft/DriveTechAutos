/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Categories;

/**
 *
 * @author Juan Jose
 */
public class CategoriesDto {
    private int idCategory;
    private String nameCategory;

    public CategoriesDto() {
    }

    public CategoriesDto(int idCategory, String nameCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNombreCategory() {
        return nameCategory;
    }

    public void setNombreCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
