/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PaymentMethods;
import java.util.List;

/**
 *
 * @author Juan Jose
 */
public class PaymentMethodsController {
    private PaymentMethodsModel paymentMethodsModel;

    public PaymentMethodsController() {
        this.paymentMethodsModel = new PaymentMethodsModel();
    }

    public boolean addPaymentMethod(PaymentMethodsDto dto) {
        try {
            return paymentMethodsModel.addPaymentMethod(dto);
        } catch (Exception ex) {
            System.out.println("Error in PaymentMethodsController.addPaymentMethod: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean updatePaymentMethod(PaymentMethodsDto dto) {
        try {
            return paymentMethodsModel.updatePaymentMethod(dto);
        } catch (Exception ex) {
            System.out.println("Error in PaymentMethodsController.updatePaymentMethod: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public boolean deletePaymentMethod(int id) {
        try {
            return paymentMethodsModel.deletePaymentMethod(id);
        } catch (Exception ex) {
            System.out.println("Error in PaymentMethodsController.deletePaymentMethod: " + ex.getLocalizedMessage());
            return false;
        }
    }

    public PaymentMethodsDto getPaymentMethod(int id) {
        try {
            return paymentMethodsModel.getPaymentMethod(id);
        } catch (Exception ex) {
            System.out.println("Error in PaymentMethodsController.getPaymentMethod: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public List<PaymentMethodsDto> getAllPaymentMethods() {
        try {
            return paymentMethodsModel.getAllPaymentMethods();
        } catch (Exception ex) {
            System.out.println("Error in PaymentMethodsController.getAllPaymentMethods: " + ex.getLocalizedMessage());
            return null;
        }
    }

    public boolean existsPaymentMethod(int id) {
        try {
            return paymentMethodsModel.existsPaymentMethod(id);
        } catch (Exception ex) {
            System.out.println("Error in PaymentMethodsController.existsPaymentMethod: " + ex.getLocalizedMessage());
            return false;
        }
    }
}
