/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class UsersController {
    private UsersModel usersModel;
    
    public UsersController() {
        this.usersModel = new UsersModel();
    }

    public boolean addUsuario(UsersDto userDto) {
        try {
            return usersModel.addUsuario(userDto);
        } catch (Exception ex) {
            System.out.println("Error en UsuarioController.addUsuario: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean updateUsuario(UsersDto userDto) {
        try {
            return usersModel.updateUsuario(userDto);
        } catch (Exception ex) {
            System.out.println("Error en UsuarioController.updateUsuario: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteUsuario(int userId) {
        try {
            return usersModel.deleteUsuario(userId);
        } catch (Exception ex) {
            System.out.println("Error en UsuarioController.deleteUsuario: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public UsersDto getUsuario(int userId) {
        try {
            return usersModel.getUsuario(userId);
        } catch (Exception ex) {
            System.out.println("Error en UsuarioController.getUsuario: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public List<UsersDto> getAllUsuarios() {
        try {
            return usersModel.getAllUsuarios();
        } catch (Exception ex) {
            System.out.println("Error en UsuarioController.getAllUsuarios: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public UsersDto login(String email, String contrasenia) throws Exception {

        UsersDto usuario = usersModel.getUsuarioByEmail(email);
        
        if (usuario == null) {
            throw new Exception("El correo ingresado no existe en el sistema.");
        }
        
        if (!usuario.getContrasenia().equals(contrasenia)) {
            throw new Exception("Contraseña incorrecta.");
        }
        
        return usuario;
    }

    public boolean existsUsuario(int userId) {
        try {
            return usersModel.existsUsuario(userId);
        } catch (Exception ex) {
            System.out.println("Error en UsuarioController.existsUsuario: " + ex.getLocalizedMessage());
            return false;
        }
    }
}
