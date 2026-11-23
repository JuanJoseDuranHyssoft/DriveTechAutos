/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MetodoPago;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class MetodoPagoController {
    private MetodoPagoModel metodoPagoModel;

    public MetodoPagoController() {
        this.metodoPagoModel = new MetodoPagoModel();
    }

    public boolean addMetodoPago(MetodoPagoDto dto) {
        try {
            return metodoPagoModel.addMetodoPago(dto);
        } catch (Exception ex) {
            System.out.println("Error en MetodoPagoController.addMetodoPago: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean updateMetodoPago(MetodoPagoDto dto) {
        try {
            return metodoPagoModel.updateMetodoPago(dto);
        } catch (Exception ex) {
            System.out.println("Error en MetodoPagoController.updateMetodoPago: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteMetodoPago(int id) {
        try {
            return metodoPagoModel.deleteMetodoPago(id);
        } catch (Exception ex) {
            System.out.println("Error en MetodoPagoController.deleteMetodoPago: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public MetodoPagoDto getMetodoPago(int id) {
        try {
            return metodoPagoModel.getMetodoPago(id);
        } catch (Exception ex) {
            System.out.println("Error en MetodoPagoController.getMetodoPago: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public List<MetodoPagoDto> getAllMetodosPago() {
        try {
            return metodoPagoModel.getAllMetodosPago();
        } catch (Exception ex) {
            System.out.println("Error en MetodoPagoController.getAllMetodosPago: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public boolean existsMetodoPago(int id) {
        try {
            return metodoPagoModel.existsMetodoPago(id);
        } catch (Exception ex) {
            System.out.println("Error en MetodoPagoController.existsMetodoPago: " + ex.getLocalizedMessage());
            return false;
        }
    }
}
