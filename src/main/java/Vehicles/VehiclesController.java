/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehicles;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class VehiclesController {
    private VehiclesModel vehiclesModel;

    public VehiclesController() {
        this.vehiclesModel = new VehiclesModel();
    }

    public boolean addVehicle(VehiclesDto dto) {
        try {
            return vehiclesModel.addVehicle(dto);
        } catch (Exception ex) {
            System.out.println("Error in VehiclesController.addVehicle: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean updateVehicle(VehiclesDto dto) {
        try {
            return vehiclesModel.updateVehicle(dto);
        } catch (Exception ex) {
            System.out.println("Error in VehiclesController.updateVehicle: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteVehicle(int id) {
        try {
            return vehiclesModel.deleteVehicle(id);
        } catch (Exception ex) {
            System.out.println("Error in VehiclesController.deleteVehicle: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public VehiclesDto getVehicle(int id) {
        try {
            return vehiclesModel.getVehicle(id);
        } catch (Exception ex) {
            System.out.println("Error in VehiclesController.getVehicle: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public List<VehiclesDto> getAllVehicles() {
        try {
            return vehiclesModel.getAllVehicles();
        } catch (Exception ex) {
            System.out.println("Error in VehiclesController.getAllVehicles: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public boolean existsVehicle(int id) {
        try {
            return vehiclesModel.existsVehicle(id);
        } catch (Exception ex) {
            System.out.println("Error in VehiclesController.existsVehicle: " + ex.getLocalizedMessage());
            return false;
        }
    }
}
