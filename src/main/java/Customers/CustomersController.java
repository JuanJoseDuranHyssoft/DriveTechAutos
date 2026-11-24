/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Customers;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class CustomersController {
    private CustomersModel customersModel;

    public CustomersController() {
        this.customersModel = new CustomersModel();
    }

    public boolean addCustomer(CustomersDto dto) {
        try {
            return customersModel.addCustomer(dto);
        } catch (Exception ex) {
            System.out.println("Error in CustomersController.addCustomer: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean updateCustomer(CustomersDto dto) {
        try {
            return customersModel.updateCustomer(dto);
        } catch (Exception ex) {
            System.out.println("Error in CustomersController.updateCustomer: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteCustomer(int id) {
        try {
            return customersModel.deleteCustomer(id);
        } catch (Exception ex) {
            System.out.println("Error in CustomersController.deleteCustomer: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public CustomersDto getCustomer(int id) {
        try {
            return customersModel.getCustomer(id);
        } catch (Exception ex) {
            System.out.println("Error in CustomersController.getCustomer: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public List<CustomersDto> getAllCustomers() {
        try {
            return customersModel.getAllCustomers();
        } catch (Exception ex) {
            System.out.println("Error in CustomersController.getAllCustomers: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public boolean existsCustomer(int id) {
        try {
            return customersModel.existsCustomer(id);
        } catch (Exception ex) {
            System.out.println("Error in CustomersController.existsCustomer: " + ex.getLocalizedMessage());
            return false;
        }
    }
}
