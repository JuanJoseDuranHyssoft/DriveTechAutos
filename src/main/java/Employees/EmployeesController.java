/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Employees;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class EmployeesController {
    private EmployeesModel employeesModel;

    public EmployeesController() {
        this.employeesModel = new EmployeesModel();
    }

    public boolean addEmployee(EmployeesDto dto) {
        try {
            return employeesModel.addEmployee(dto);
        } catch (Exception ex) {
            System.out.println("Error in EmployeesController.addEmployee: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean updateEmployee(EmployeesDto dto) {
        try {
            return employeesModel.updateEmployee(dto);
        } catch (Exception ex) {
            System.out.println("Error in EmployeesController.updateEmployee: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteEmployee(int id) {
        try {
            return employeesModel.deleteEmployee(id);
        } catch (Exception ex) {
            System.out.println("Error in EmployeesController.deleteEmployee: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public EmployeesDto getEmployee(int id) {
        try {
            return employeesModel.getEmployee(id);
        } catch (Exception ex) {
            System.out.println("Error in EmployeesController.getEmployee: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public List<EmployeesDto> getAllEmployees() {
        try {
            return employeesModel.getAllEmployees();
        } catch (Exception ex) {
            System.out.println("Error in EmployeesController.getAllEmployees: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public boolean existsEmployee(int id) {
        try {
            return employeesModel.existsEmployee(id);
        } catch (Exception ex) {
            System.out.println("Error in EmployeesController.existsEmployee: " + ex.getLocalizedMessage());
            return false;
        }
    }
}
