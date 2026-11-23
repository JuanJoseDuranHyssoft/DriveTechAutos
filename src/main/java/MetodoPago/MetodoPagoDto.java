/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MetodoPago;

/**
 *
 * @author Juan Jose
 */
public class MetodoPagoDto {
    private int idMetodoPago;
    private String descripcion;

    public MetodoPagoDto() {
    }

    public MetodoPagoDto(int idMetodoPago, String descripcion) {
        this.idMetodoPago = idMetodoPago;
        this.descripcion = descripcion;
    }

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
