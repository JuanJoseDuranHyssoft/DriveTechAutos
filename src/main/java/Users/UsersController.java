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

    public boolean addUser(UsersDto userDto) {
        try {
            return usersModel.addUser(userDto);
        } catch (Exception ex) {
            System.out.println("Error in UsersController.addUser: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean updateUser(UsersDto userDto) {
        try {
            return usersModel.updateUser(userDto);
        } catch (Exception ex) {
            System.out.println("Error in UsersController.updateUser: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteUser(int userId) {
        try {
            return usersModel.deleteUser(userId);
        } catch (Exception ex) {
            System.out.println("Error in UsersController.deleteUser: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public UsersDto getUser(int userId) {
        try {
            return usersModel.getUser(userId);
        } catch (Exception ex) {
            System.out.println("Error in UsersController.getUser: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public List<UsersDto> getAllUsers() {
        try {
            return usersModel.getAllUsers();
        } catch (Exception ex) {
            System.out.println("Error in UsersController.getAllUsers: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public UsersDto login(String email, String password) throws Exception {

        UsersDto user = usersModel.getUserByEmail(email);
        
        if (user == null) {
            throw new Exception("El correo ingresado no existe en el sistema.");
        }
        
        if (!user.getPassword().equals(password)) {
            throw new Exception("Contraseña incorrecta.");
        }
        
        return user;
    }

    public boolean existsUser(int userId) {
        try {
            return usersModel.existsUser(userId);
        } catch (Exception ex) {
            System.out.println("Error in UsersController.existsUser: " + ex.getLocalizedMessage());
            return false;
        }
    }
}
