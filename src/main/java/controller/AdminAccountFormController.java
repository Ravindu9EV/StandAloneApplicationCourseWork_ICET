package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Admin;
import repository.DaoFactory;
import service.ServiceFactory;
import service.custom.impl.AdminServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminAccountFormController implements Initializable {

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtLocation;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPassword;

    private String id="A001";

    static Stage stage=null;
    private final AdminServiceImpl service=ServiceFactory.getInstance().getService(ServiceType.ADMIN);
    @FXML
    void btnEditOnAction(ActionEvent event) {
        if(txtName.getText().isEmpty()||txtLocation.getText().isEmpty()||txtContact.getText().isEmpty()||txtEmail.getText().isEmpty()||txtPassword.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"All fields Required to fill!!").show();
        }else{
            if(service.edit(new Admin(id,txtName.getText(),txtLocation.getText(),txtEmail.getText(),txtPassword.getText(),txtContact.getText()))){
                new Alert(Alert.AlertType.INFORMATION,"Successfully Updated..").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Update Not Success!!!").show();
            }
        }

    }
    @FXML
    void btnCloseOnAction(ActionEvent event) {

        stage.close();

    }

    //------load text fields-----
    private void loadTextFields(Admin admin){

        System.out.println(admin);
        if(admin==null||admin.getName().isEmpty()||admin.getContact().isEmpty()||admin.getEmail().isEmpty()||admin.getPassword().isEmpty()||admin.getLocation().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Something missing!").show();
        }else {
            txtContact.setText(admin.getContact());
            txtEmail.setText(admin.getEmail());
            txtName.setText(admin.getName());
            txtPassword.setText(admin.getPassword());
            txtLocation.setText(admin.getLocation());
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTextFields(service.findById(id));
    }
}
