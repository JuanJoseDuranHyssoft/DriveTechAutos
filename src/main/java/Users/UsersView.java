/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Juan Jose
 */
public class UsersView {
    private final UsersController usersController;
    private final Scanner scanner;

    public UsersView() {
        this.usersController = new UsersController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 7) {
            System.out.println("\n=== MENÚ DE GESTIÓN DE USUARIOS ===");
            System.out.println("1. Iniciar Sesión (Login)");
            System.out.println("2. Agregar Nuevo Usuario");
            System.out.println("3. Listar Todos los Usuarios");
            System.out.println("4. Buscar Usuario por ID");
            System.out.println("5. Actualizar Usuario");
            System.out.println("6. Eliminar Usuario");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1: login(); break;
                    case 2: agregarUsuario(); break;
                    case 3: listarUsuarios(); break;
                    case 4: buscarUsuario(); break;
                    case 5: actualizarUsuario(); break;
                    case 6: eliminarUsuario(); break;
                    case 7: System.out.println("Saliendo del sistema..."); break;
                    default: System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingresa un número válido.");
            }
        }
    }

    private void login() {
        System.out.println("\n--- INICIO DE SESIÓN ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();

        try {
            UsersDto usuario = usersController.login(email, contrasenia);
            System.out.println("✅ ¡Login exitoso! Bienvenido, " + usuario.getNameUser() + " (" + usuario.getRole() + ")");
            
        } catch (Exception e) {
            System.out.println("❌ Error de inicio de sesión: " + e.getMessage()); 
        }
    }

    private void agregarUsuario() {
        System.out.println("\n--- AGREGAR USUARIO ---");
        System.out.print("Nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();
        System.out.print("Rol (Ej: Administrador, Vendedor): ");
        String rol = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        // El ID no se envía porque SQLite lo genera automáticamente (AUTOINCREMENT)
        UsersDto nuevoUsuario = new UsersDto(0, nombre, contrasenia, rol, email);

        if (usersController.addUser(nuevoUsuario)) {
            System.out.println("✅ Usuario agregado exitosamente.");
        } else {
            System.out.println("❌ Error al agregar el usuario.");
        }
    }

    private void listarUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        List<UsersDto> usuarios = usersController.getAllUsers();

        if (usuarios != null && !usuarios.isEmpty()) {
            for (UsersDto u : usuarios) {
                System.out.println("ID: " + u.getIdUser() + " | Nombre: " + u.getNameUser() + 
                                   " | Rol: " + u.getRole() + " | Email: " + u.getEmail());
            }
        } else {
            System.out.println("No hay usuarios registrados en la base de datos.");
        }
    }

    private void buscarUsuario() {
        System.out.print("\nIngrese el ID del usuario a buscar: ");
        int id = Integer.parseInt(scanner.nextLine());

        UsersDto u = usersController.getUser(id);
        if (u != null) {
            System.out.println("✅ Usuario encontrado:");
            System.out.println("Nombre: " + u.getNameUser() + " | Rol: " + u.getRole() + " | Email: " + u.getEmail());
        } else {
            System.out.println("❌ No se encontró ningún usuario con el ID " + id);
        }
    }

    private void actualizarUsuario() {
        System.out.print("\nIngrese el ID del usuario a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (usersController.existsUser(id)) {
            System.out.print("Nuevo nombre de usuario: ");
            String nombre = scanner.nextLine();
            System.out.print("Nueva contraseña: ");
            String contrasenia = scanner.nextLine();
            System.out.print("Nuevo rol: ");
            String rol = scanner.nextLine();
            System.out.print("Nuevo email: ");
            String email = scanner.nextLine();

            UsersDto usuarioActualizado = new UsersDto(id, nombre, contrasenia, rol, email);

            if (usersController.updateUser(usuarioActualizado)) {
                System.out.println("✅ Usuario actualizado exitosamente.");
            } else {
                System.out.println("❌ Error al actualizar el usuario.");
            }
        } else {
            System.out.println("❌ El usuario con ID " + id + " no existe.");
        }
    }

    private void eliminarUsuario() {
        System.out.print("\nIngrese el ID del usuario a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (usersController.deleteUser(id)) {
            System.out.println("✅ Usuario eliminado exitosamente.");
        } else {
            System.out.println("❌ Error al eliminar el usuario (o el usuario no existe).");
        }
    }
}
