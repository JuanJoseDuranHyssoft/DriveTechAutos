/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaymentMethods;

/**
 *
 * @author Juan Jose
 */
public class PaymentMethodsDto {
    private int idPaymentMethod;
    private String description;

    public PaymentMethodsDto() {
    }

    public PaymentMethodsDto(int idPaymentMethod, String description) {
        this.idPaymentMethod = idPaymentMethod;
        this.description = description;
    }

    public int getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(int idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
