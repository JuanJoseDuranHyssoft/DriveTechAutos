/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculo;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class VehiculoController {
    private VehiculoModel vehiculoModel;

    public VehiculoController() {
        this.vehiculoModel = new VehiculoModel();
    }

    public boolean addVehiculo(VehiculoDto dto) {
        try {
            return vehiculoModel.addVehiculo(dto);
        } catch (Exception ex) {
            System.out.println("Error en VehiculoController.addVehiculo: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean updateVehiculo(VehiculoDto dto) {
        try {
            return vehiculoModel.updateVehiculo(dto);
        } catch (Exception ex) {
            System.out.println("Error en VehiculoController.updateVehiculo: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteVehiculo(int id) {
        try {
            return vehiculoModel.deleteVehiculo(id);
        } catch (Exception ex) {
            System.out.println("Error en VehiculoController.deleteVehiculo: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public VehiculoDto getVehiculo(int id) {
        try {
            return vehiculoModel.getVehiculo(id);
        } catch (Exception ex) {
            System.out.println("Error en VehiculoController.getVehiculo: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public List<VehiculoDto> getAllVehiculos() {
        try {
            return vehiculoModel.getAllVehiculos();
        } catch (Exception ex) {
            System.out.println("Error en VehiculoController.getAllVehiculos: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public boolean existsVehiculo(int id) {
        try {
            return vehiculoModel.existsVehiculo(id);
        } catch (Exception ex) {
            System.out.println("Error en VehiculoController.existsVehiculo: " + ex.getLocalizedMessage());
            return false;
        }
    }
}
