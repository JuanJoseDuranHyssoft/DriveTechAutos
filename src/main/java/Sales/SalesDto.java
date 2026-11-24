/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sales;

/**
 *
 * @author Juan Jose
 */
public class SalesDto {
    private int idSale;
    private String saleDate;
    private double finalPrice;
    private int idVehicle;
    private int idCustomer;
    private int idEmployee;
    private int idPaymentMethod;

    public SalesDto() {
    }

    public SalesDto(int idSale, String saleDate, double finalPrice, int idVehicle, int idCustomer, int idEmployee, int idPaymentMethod) {
        this.idSale = idSale;
        this.saleDate = saleDate;
        this.finalPrice = finalPrice;
        this.idVehicle = idVehicle;
        this.idCustomer = idCustomer;
        this.idEmployee = idEmployee;
        this.idPaymentMethod = idPaymentMethod;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(int idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }
}
