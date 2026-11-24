/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Sales;

import Customers.CustomersController;
import Customers.CustomersDto;
import Customers.OdialogCustomers;
import Employees.EmployeesController;
import Employees.EmployeesDto;
import Employees.OdialogEmployees;
import PaymentMethods.OdialogPaymentMethods;
import PaymentMethods.PaymentMethodsController;
import PaymentMethods.PaymentMethodsDto;
import Vehicles.OdialogVehicles;
import Vehicles.VehiclesController;
import Vehicles.VehiclesDto;
import drivetech.mainView.DriveTechAutosMain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Jose
 */
public class VentasView extends javax.swing.JInternalFrame {

    private final SalesController salesController = new SalesController();
    private final VehiclesController vehiclesController = new VehiclesController();
    private final CustomersController customersController = new CustomersController();
    private final EmployeesController employeesController = new EmployeesController();
    private final PaymentMethodsController paymentMethodsController = new PaymentMethodsController();
    private OdialogVehicles odialogVehicle;
    private OdialogPaymentMethods odialogPaymentMethods;
    private OdialogCustomers odialogCustomers;
    private OdialogEmployees odialogEmployees;
    private DefaultTableModel tableModel;

    private final Map<Integer, VehiclesDto> vehiclesById = new HashMap<>();
    private final Map<Integer, CustomersDto> customersById = new HashMap<>();
    private final Map<Integer, EmployeesDto> employeesById = new HashMap<>();
    private final Map<Integer, PaymentMethodsDto> paymentMethodsById = new HashMap<>();

    private List<SalesDto> sales = new ArrayList<>();

    private int selectedSaleId = 0;
    private int selectedVehicleId = 0;
    private int selectedCustomerId = 0;
    private int selectedEmployeeId = 0;
    private int selectedPaymentMethodId = 0;

    /**
     * Creates new form VentasView
     */
    public VentasView() {
        initComponents();
        configureTable();
        loadSales();
    }

    private void configureTable() {
        tableModel = new DefaultTableModel(
                new String[]{"ID Venta", "Fecha Venta", "Precio Final", "Vehículo", "Cliente", "Empleado", "Método Pago"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        salesTable.setModel(tableModel);
        salesTable.removeColumn(salesTable.getColumnModel().getColumn(0));
    }

    public void loadSales() {
        loadLookups();
        tableModel.setRowCount(0);

        sales = salesController.getAllSales();
        if (sales == null) {
            sales = new ArrayList<>();
        }

        for (SalesDto s : sales) {
            tableModel.addRow(new Object[]{
                s.getIdSale(),
                s.getSaleDate(),
                s.getFinalPrice(),
                vehicleLabel(vehiclesById.get(s.getIdVehicle())),
                customerLabel(customersById.get(s.getIdCustomer())),
                employeeLabel(employeesById.get(s.getIdEmployee())),
                paymentMethodLabel(paymentMethodsById.get(s.getIdPaymentMethod()))
            });
        }
    }

    private void loadLookups() {
        vehiclesById.clear();
        customersById.clear();
        employeesById.clear();
        paymentMethodsById.clear();

        List<VehiclesDto> vehicles = vehiclesController.getAllVehicles();
        if (vehicles != null) {
            for (VehiclesDto v : vehicles) {
                vehiclesById.put(v.getIdVehicle(), v);
            }
        }
        List<CustomersDto> customers = customersController.getAllCustomers();
        if (customers != null) {
            for (CustomersDto c : customers) {
                customersById.put(c.getIdCustomer(), c);
            }
        }
        List<EmployeesDto> employees = employeesController.getAllEmployees();
        if (employees != null) {
            for (EmployeesDto e : employees) {
                employeesById.put(e.getIdEmployee(), e);
            }
        }
        List<PaymentMethodsDto> paymentMethods = paymentMethodsController.getAllPaymentMethods();
        if (paymentMethods != null) {
            for (PaymentMethodsDto p : paymentMethods) {
                paymentMethodsById.put(p.getIdPaymentMethod(), p);
            }
        }
    }

    private String vehicleLabel(VehiclesDto v) {
        return v == null ? "" : v.getBrand() + " " + v.getModel();
    }

    private String customerLabel(CustomersDto c) {
        return c == null ? "" : c.getName() + " " + c.getLastName();
    }

    private String employeeLabel(EmployeesDto e) {
        return e == null ? "" : e.getName() + " " + e.getLastName();
    }

    private String paymentMethodLabel(PaymentMethodsDto p) {
        return p == null ? "" : p.getDescription();
    }

    private void fillInputsFromSelectedRow() {
        int viewRow = salesTable.getSelectedRow();
        if (viewRow < 0) {
            return;
        }
        SalesDto s = sales.get(salesTable.convertRowIndexToModel(viewRow));

        selectedSaleId = s.getIdSale();
        selectedVehicleId = s.getIdVehicle();
        selectedCustomerId = s.getIdCustomer();
        selectedEmployeeId = s.getIdEmployee();
        selectedPaymentMethodId = s.getIdPaymentMethod();

        inputSaleDate.setText(s.getSaleDate());
        inputPrice.setText(java.math.BigDecimal.valueOf(s.getFinalPrice()).toPlainString());
        inputOdialogVehicle.setText(vehicleLabel(vehiclesById.get(selectedVehicleId)));
        inputOdialogClient.setText(customerLabel(customersById.get(selectedCustomerId)));
        inputOdialogEmploye.setText(employeeLabel(employeesById.get(selectedEmployeeId)));
        inputOdialogPaymentMethods.setText(paymentMethodLabel(paymentMethodsById.get(selectedPaymentMethodId)));
    }

    private void clearForm() {
        inputSaleDate.setText("");
        inputPrice.setText("");
        inputOdialogVehicle.setText("");
        inputOdialogClient.setText("");
        inputOdialogEmploye.setText("");
        inputOdialogPaymentMethods.setText("");

        selectedSaleId = 0;
        selectedVehicleId = 0;
        selectedCustomerId = 0;
        selectedEmployeeId = 0;
        selectedPaymentMethodId = 0;

        salesTable.clearSelection();
    }

    private void createSale() {
        double price;
        try {
            price = Double.parseDouble(inputPrice.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio final debe ser un número.");
            return;
        }
        if (inputSaleDate.getText().trim().isEmpty() || selectedVehicleId == 0
                || selectedCustomerId == 0 || selectedEmployeeId == 0 || selectedPaymentMethodId == 0) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.");
            return;
        }

        SalesDto dto = new SalesDto();
        dto.setSaleDate(inputSaleDate.getText().trim());
        dto.setFinalPrice(price);
        dto.setIdVehicle(selectedVehicleId);
        dto.setIdCustomer(selectedCustomerId);
        dto.setIdEmployee(selectedEmployeeId);
        dto.setIdPaymentMethod(selectedPaymentMethodId);

        if (salesController.addSale(dto)) {
            loadSales();
            clearForm();
            JOptionPane.showMessageDialog(this, "Venta creada correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo crear la venta.");
        }
    }

    private void updateSelectedSale() {
        if (selectedSaleId == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una venta de la tabla para editar.");
            return;
        }
        double price;
        try {
            price = Double.parseDouble(inputPrice.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio final debe ser un número.");
            return;
        }
        if (inputSaleDate.getText().trim().isEmpty() || selectedVehicleId == 0
                || selectedCustomerId == 0 || selectedEmployeeId == 0 || selectedPaymentMethodId == 0) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.");
            return;
        }

        SalesDto dto = new SalesDto();
        dto.setIdSale(selectedSaleId);
        dto.setSaleDate(inputSaleDate.getText().trim());
        dto.setFinalPrice(price);
        dto.setIdVehicle(selectedVehicleId);
        dto.setIdCustomer(selectedCustomerId);
        dto.setIdEmployee(selectedEmployeeId);
        dto.setIdPaymentMethod(selectedPaymentMethodId);

        if (salesController.updateSale(dto)) {
            loadSales();
            clearForm();
            JOptionPane.showMessageDialog(this, "Venta actualizada correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar la venta.");
        }
    }

    private void deleteSelectedSale() {
        if (selectedSaleId == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila de la tabla para borrar");
            return;
        }
        int option = JOptionPane.showConfirmDialog(this,
                "¿Seguro que desea borrar la venta seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (option != JOptionPane.YES_OPTION) {
            return;
        }
        if (salesController.deleteSale(selectedSaleId)) {
            loadSales();
            clearForm();
            JOptionPane.showMessageDialog(this, "Venta borrada correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo borrar la venta.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        inputSaleDate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        inputPrice = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        salesTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        inputOdialogVehicle = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        inputOdialogClient = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        inputOdialogPaymentMethods = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        inputOdialogEmploye = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnClean = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://i.ibb.co/rfpNCdNG/logo-Autos.png")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());

        jLabel2.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 242, 242));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Registro y Visualizacion de Ventas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel4.setText("Fecha Venta");

        jLabel5.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel5.setText("Precio Final");

        salesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        salesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(salesTable);

        jLabel6.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel6.setText("Vehiculo");

        inputOdialogVehicle.setEditable(false);
        inputOdialogVehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputOdialogVehicleMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel7.setText("Cliente");

        inputOdialogClient.setEditable(false);
        inputOdialogClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputOdialogClientMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel8.setText("Metodo de Pago");

        inputOdialogPaymentMethods.setEditable(false);
        inputOdialogPaymentMethods.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputOdialogPaymentMethodsMouseClicked(evt);
            }
        });
        inputOdialogPaymentMethods.addActionListener(this::inputOdialogPaymentMethodsActionPerformed);

        jLabel9.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel9.setText("Empleado");

        inputOdialogEmploye.setEditable(false);
        inputOdialogEmploye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputOdialogEmployeMouseClicked(evt);
            }
        });
        inputOdialogEmploye.addActionListener(this::inputOdialogEmployeActionPerformed);

        btnCreate.setBackground(new java.awt.Color(255, 102, 0));
        btnCreate.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        btnCreate.setText("Crear");
        btnCreate.addActionListener(this::btnCreateActionPerformed);

        btnClean.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        btnClean.setText("Limpiar");
        btnClean.addActionListener(this::btnCleanActionPerformed);

        btnDelete.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        btnDelete.setText("Borrar");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btEdit.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        btEdit.setText("Editar");
        btEdit.setToolTipText("");
        btEdit.addActionListener(this::btEditActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(inputSaleDate, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(inputPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inputOdialogEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inputOdialogVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inputOdialogClient, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(inputOdialogPaymentMethods, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClean, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(inputOdialogVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(inputOdialogClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(inputOdialogPaymentMethods, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inputOdialogEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(inputSaleDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(inputPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnCreate)
                                    .addComponent(btEdit))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnClean))
                            .addComponent(btnDelete))))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputOdialogVehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputOdialogVehicleMouseClicked
        DriveTechAutosMain window = (DriveTechAutosMain) SwingUtilities.getWindowAncestor(this);
        
        odialogVehicle = new OdialogVehicles(window, true);
        odialogVehicle.setLocationRelativeTo(window);
        odialogVehicle.setVisible(true);

        VehiclesDto selected = odialogVehicle.getSelectedVehicle();
        if (selected != null) {
            selectedVehicleId = selected.getIdVehicle();
            vehiclesById.put(selectedVehicleId, selected);
            inputOdialogVehicle.setText(vehicleLabel(selected));
        }
    }//GEN-LAST:event_inputOdialogVehicleMouseClicked

    private void inputOdialogPaymentMethodsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputOdialogPaymentMethodsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputOdialogPaymentMethodsActionPerformed

    private void inputOdialogPaymentMethodsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputOdialogPaymentMethodsMouseClicked
        DriveTechAutosMain window = (DriveTechAutosMain) SwingUtilities.getWindowAncestor(this);
        
        odialogPaymentMethods = new OdialogPaymentMethods(window, true);
        odialogPaymentMethods.setLocationRelativeTo(window);
        odialogPaymentMethods.setVisible(true);

        PaymentMethodsDto selected = odialogPaymentMethods.getSelectedPaymentMethod();
        if (selected != null) {
            selectedPaymentMethodId = selected.getIdPaymentMethod();
            paymentMethodsById.put(selectedPaymentMethodId, selected);
            inputOdialogPaymentMethods.setText(paymentMethodLabel(selected));
        }
    }//GEN-LAST:event_inputOdialogPaymentMethodsMouseClicked

    private void inputOdialogClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputOdialogClientMouseClicked
        DriveTechAutosMain window = (DriveTechAutosMain) SwingUtilities.getWindowAncestor(this);
        
        odialogCustomers = new OdialogCustomers(window, true);
        odialogCustomers.setLocationRelativeTo(window);
        odialogCustomers.setVisible(true);

        CustomersDto selected = odialogCustomers.getSelectedCustomer();
        if (selected != null) {
            selectedCustomerId = selected.getIdCustomer();
            customersById.put(selectedCustomerId, selected);
            inputOdialogClient.setText(customerLabel(selected));
        }
    }//GEN-LAST:event_inputOdialogClientMouseClicked

    private void inputOdialogEmployeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputOdialogEmployeMouseClicked
        DriveTechAutosMain window = (DriveTechAutosMain) SwingUtilities.getWindowAncestor(this);
        
        odialogEmployees = new OdialogEmployees(window, true);
        odialogEmployees.setLocationRelativeTo(window);
        odialogEmployees.setVisible(true);

        EmployeesDto selected = odialogEmployees.getSelectedEmployee();
        if (selected != null) {
            selectedEmployeeId = selected.getIdEmployee();
            employeesById.put(selectedEmployeeId, selected);
            inputOdialogEmploye.setText(employeeLabel(selected));
        }
    }//GEN-LAST:event_inputOdialogEmployeMouseClicked

    private void salesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salesTableMouseClicked
        fillInputsFromSelectedRow();
    }//GEN-LAST:event_salesTableMouseClicked

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        updateSelectedSale();
    }//GEN-LAST:event_btEditActionPerformed

    private void btnCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanActionPerformed
        clearForm();
    }//GEN-LAST:event_btnCleanActionPerformed

    private void inputOdialogEmployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputOdialogEmployeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputOdialogEmployeActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteSelectedSale();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        createSale();
    }//GEN-LAST:event_btnCreateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JTextField inputOdialogClient;
    private javax.swing.JTextField inputOdialogEmploye;
    private javax.swing.JTextField inputOdialogPaymentMethods;
    private javax.swing.JTextField inputOdialogVehicle;
    private javax.swing.JTextField inputPrice;
    private javax.swing.JTextField inputSaleDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable salesTable;
    // End of variables declaration//GEN-END:variables
}
