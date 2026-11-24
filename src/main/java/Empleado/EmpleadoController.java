/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Empleado;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class EmpleadoController {
    private EmpleadoModel empleadoModel;

    public EmpleadoController() {
        this.empleadoModel = new EmpleadoModel();
    }

    public boolean addEmpleado(EmpleadoDto dto) {
        try {
            return empleadoModel.addEmpleado(dto);
        } catch (Exception ex) {
            System.out.println("Error en EmpleadoController.addEmpleado: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean updateEmpleado(EmpleadoDto dto) {
        try {
            return empleadoModel.updateEmpleado(dto);
        } catch (Exception ex) {
            System.out.println("Error en EmpleadoController.updateEmpleado: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteEmpleado(int id) {
        try {
            return empleadoModel.deleteEmpleado(id);
        } catch (Exception ex) {
            System.out.println("Error en EmpleadoController.deleteEmpleado: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public EmpleadoDto getEmpleado(int id) {
        try {
            return empleadoModel.getEmpleado(id);
        } catch (Exception ex) {
            System.out.println("Error en EmpleadoController.getEmpleado: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public List<EmpleadoDto> getAllEmpleados() {
        try {
            return empleadoModel.getAllEmpleados();
        } catch (Exception ex) {
            System.out.println("Error en EmpleadoController.getAllEmpleados: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public boolean existsEmpleado(int id) {
        try {
            return empleadoModel.existsEmpleado(id);
        } catch (Exception ex) {
            System.out.println("Error en EmpleadoController.existsEmpleado: " + ex.getLocalizedMessage());
            return false;
        }
    }
}
