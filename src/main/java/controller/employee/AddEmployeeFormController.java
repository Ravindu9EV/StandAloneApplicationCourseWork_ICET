package controller.employee;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import service.ServiceFactory;
import service.custom.EmployeeService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeFormController implements Initializable {

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
    private TableColumn<?, ?> colPassword;
    @FXML
    private TableView<Employee> tblEmployees;

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

    @FXML
    private JFXTextField txtPassword;

    private final EmployeeService service= ServiceFactory.getInstance().getService(ServiceType.EMPLOYEE);
    @FXML
    void btnAddEmpOnAction(ActionEvent event) {
        if(service.addEmployee(getEmployee())){
            new Alert(Alert.AlertType.INFORMATION,"Employee Added Successfully...").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Unsuccessfull!!!").show();
        }

    }

    private Employee getEmployee(){
        if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty() || txtLocation.getText().isEmpty() || txtContact.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All Fields required to fill").show();
            return null;
        } else {
           return new Employee(txtId.getText(),txtName.getText(),txtLocation.getText(),txtEmail.getText(),txtPassword.getText(),txtContact.getText());
        }
    }
    @FXML
    void btnDeleteEmpOnAction(ActionEvent event) {
        if(service.deleteEmployee(txtId.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Employee Deleted Successfully...").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Unsuccessfull!!!\nPlease input correct ID!!!").show();
        }
    }

    @FXML
    void btnEditEmpOnAction(ActionEvent event) {
            Employee employee=getEmployee();
            if(employee!=null){

                if(service.editEmployee(employee)){
    loadTable();
                }else new Alert(Alert.AlertType.ERROR,"Not Updated!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something missing!").show();
            }
    }

    @FXML
    void btnSearchEmpOnAction(ActionEvent event) {
        Employee employee=service.search(txtId.getText());
        if(employee!=null){
            setTextFields(employee);
        }else{
            new Alert(Alert.AlertType.ERROR,"Emplyee doesn't exist!\nPlease check the ID..").show();
        }
    }

    //--------set text fields ------
    private void setTextFields(Employee employee){
        txtId.setText(employee.getId());
        txtContact.setText(employee.getContact());
        txtLocation.setText(employee.getLocation());
        txtPassword.setText(employee.getPassword());
        txtEmail.setText(employee.getEmail());
        txtName.setText(employee.getName());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtId.setText(service.getID());
        txtId.setDisable(true);
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        loadTable();
        tblEmployees.getSelectionModel().selectedItemProperty().addListener((observableValue, employee, t1) -> {
            if(t1!=null){
                setTextFields(t1);
            }
        });

    }

    //-----load employee table-----
    private void loadTable(){
        tblEmployees.setItems(service.getAll());
    }
}
