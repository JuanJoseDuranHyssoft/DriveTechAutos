/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Venta;

/**
 *
 * @author Juan Jose
 */
public class VentaDto {
    private int idVenta;
    private String fechaVenta;
    private double precioFinal;
    private int idVehiculo;
    private int idCliente;
    private int idEmpleado;
    private int idMetodoPago;

    public VentaDto() {
    }

    public VentaDto(int idVenta, String fechaVenta, double precioFinal, int idVehiculo, int idCliente, int idEmpleado, int idMetodoPago) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.precioFinal = precioFinal;
        this.idVehiculo = idVehiculo;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.idMetodoPago = idMetodoPago;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }
}
