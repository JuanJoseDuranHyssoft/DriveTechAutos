/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sales;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class SalesController {
    private SalesModel salesModel;

    public SalesController() {
        this.salesModel = new SalesModel();
    }

    public boolean addSale(SalesDto dto) {
        try {
            return salesModel.addSale(dto);
        } catch (Exception ex) {
            System.out.println("Error in SalesController.addSale: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean updateSale(SalesDto dto) {
        try {
            return salesModel.updateSale(dto);
        } catch (Exception ex) {
            System.out.println("Error in SalesController.updateSale: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteSale(int id) {
        try {
            return salesModel.deleteSale(id);
        } catch (Exception ex) {
            System.out.println("Error in SalesController.deleteSale: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public SalesDto getSale(int id) {
        try {
            return salesModel.getSale(id);
        } catch (Exception ex) {
            System.out.println("Error in SalesController.getSale: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public List<SalesDto> getAllSales() {
        try {
            return salesModel.getAllSales();
        } catch (Exception ex) {
            System.out.println("Error in SalesController.getAllSales: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public boolean existsSale(int id) {
        try {
            return salesModel.existsSale(id);
        } catch (Exception ex) {
            System.out.println("Error in SalesController.existsSale: " + ex.getLocalizedMessage());
            return false;
        }
    }
}
