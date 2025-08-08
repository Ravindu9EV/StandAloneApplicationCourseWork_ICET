package controller;

import com.jfoenix.controls.JFXTextField;
import controller.employee.AddEmployeeFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Supplier;
import service.ServiceFactory;
import service.custom.SupplierService;
import service.custom.impl.SupplierServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class AddSupplierFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Supplier> tblSuppliers;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtLocation;

    @FXML
    private JFXTextField txtName;
    public static String empID;
    SupplierService service= ServiceFactory.getInstance().getService(ServiceType.SUPPLIER);
    @FXML
    void btnAddSupOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteSupOnAction(ActionEvent event) {

    }

    @FXML
    void btnEditSupOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchSupOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colID.setCellValueFactory(new PropertyValueFactory<>("name"));
        colID.setCellValueFactory(new PropertyValueFactory<>("email"));
        colID.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colID.setCellValueFactory(new PropertyValueFactory<>("location"));
        loadSupplierTable();
        txtId.setText(service.getID());
        tblSuppliers.getSelectionModel().selectedItemProperty().addListener((observableValue, supplier, t1) -> {
            if(t1!=null){
                setTextFileds(t1);
            }
        });
    }

    private void setTextFileds(Supplier t1) {
        txtId.setText(t1.getId());
        txtContact.setText(t1.getContact());
        txtName.setText(t1.getName());
        txtEmail.setText(t1.getEmail());
        txtLocation.setText(t1.getLocation());
    }

    private void loadSupplierTable() {
        tblSuppliers.setItems(service.getAll());
    }

   //--------get supplier----
    private Supplier supplier(){
        if(txtId.getText().isEmpty()||txtName.getText().isEmpty()||txtEmail.getText().isEmpty()||txtContact.getText().isEmpty()||txtLocation.getText().isEmpty()){
            return null;
        }
        return empID.isEmpty()?null: new Supplier(txtId.getText(),txtName.getText(),txtLocation.getText(),txtEmail.getText(),txtContact.getText(),empID);
    }
}
