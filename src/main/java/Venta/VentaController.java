/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Venta;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class VentaController {
    private VentaModel ventaModel;

    public VentaController() {
        this.ventaModel = new VentaModel();
    }

    public boolean addVenta(VentaDto dto) {
        try {
            return ventaModel.addVenta(dto);
        } catch (Exception ex) {
            System.out.println("Error en VentaController.addVenta: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean updateVenta(VentaDto dto) {
        try {
            return ventaModel.updateVenta(dto);
        } catch (Exception ex) {
            System.out.println("Error en VentaController.updateVenta: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteVenta(int id) {
        try {
            return ventaModel.deleteVenta(id);
        } catch (Exception ex) {
            System.out.println("Error en VentaController.deleteVenta: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public VentaDto getVenta(int id) {
        try {
            return ventaModel.getVenta(id);
        } catch (Exception ex) {
            System.out.println("Error en VentaController.getVenta: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public List<VentaDto> getAllVentas() {
        try {
            return ventaModel.getAllVentas();
        } catch (Exception ex) {
            System.out.println("Error en VentaController.getAllVentas: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public boolean existsVenta(int id) {
        try {
            return ventaModel.existsVenta(id);
        } catch (Exception ex) {
            System.out.println("Error en VentaController.existsVenta: " + ex.getLocalizedMessage());
            return false;
        }
    }
}
