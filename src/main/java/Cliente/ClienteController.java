/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class ClienteController {
    private ClienteModel clienteModel;

    public ClienteController() {
        this.clienteModel = new ClienteModel();
    }

    public boolean addCliente(ClienteDto dto) {
        try {
            return clienteModel.addCliente(dto);
        } catch (Exception ex) {
            System.out.println("Error en ClienteController.addCliente: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean updateCliente(ClienteDto dto) {
        try {
            return clienteModel.updateCliente(dto);
        } catch (Exception ex) {
            System.out.println("Error en ClienteController.updateCliente: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteCliente(int id) {
        try {
            return clienteModel.deleteCliente(id);
        } catch (Exception ex) {
            System.out.println("Error en ClienteController.deleteCliente: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public ClienteDto getCliente(int id) {
        try {
            return clienteModel.getCliente(id);
        } catch (Exception ex) {
            System.out.println("Error en ClienteController.getCliente: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public List<ClienteDto> getAllClientes() {
        try {
            return clienteModel.getAllClientes();
        } catch (Exception ex) {
            System.out.println("Error en ClienteController.getAllClientes: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public boolean existsCliente(int id) {
        try {
            return clienteModel.existsCliente(id);
        } catch (Exception ex) {
            System.out.println("Error en ClienteController.existsCliente: " + ex.getLocalizedMessage());
            return false;
        }
    }
}
