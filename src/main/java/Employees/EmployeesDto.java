/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Employees;

/**
 *
 * @author Juan Jose
 */
public class EmployeesDto {
    private int idEmployee;
    private String name;
    private String lastName;
    private String position;
    private String phone;

    public EmployeesDto() {
    }

    public EmployeesDto(int idEmployee, String name, String lastName, String position, String phone) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.lastName = lastName;
        this.position = position;
        this.phone = phone;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
