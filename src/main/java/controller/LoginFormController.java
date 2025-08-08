package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.EmployeeService;
import service.custom.impl.AdminServiceImpl;
import util.ServiceType;

import java.io.IOException;

public class LoginFormController {
    @FXML
    private RadioButton radioBtnAdmin;
    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;
    private final Stage stage=new Stage();
    @FXML
    void btnLoginOnAction(ActionEvent event)  {

            try {

                //check for admin login
                if (radioBtnAdmin.isSelected()) {
                    AdminServiceImpl service = ServiceFactory.getInstance().getService(ServiceType.ADMIN);
                    boolean b = service.login(txtEmail.getText(), txtPassword.getText());
                    System.out.println(b);
                    if (b) {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/admin_dashboard.fxml"))));
                        stage.show();
                    }
               } else {
                    //check for employee login
                    EmployeeService service = ServiceFactory.getInstance().getService(ServiceType.EMPLOYEE);
                    if (service.login(txtEmail.getText(), txtPassword.getText())) {
                        try {
                            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/employee_dashboard.fxml"))));
                            stage.show();

                            service.getAll().forEach(employee -> {
                                if (employee.getEmail().equals(txtEmail.getText())){
                                    AddSupplierFormController.empID=employee.getId();

                                }
                            });
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }

          }catch (Exception e){
                System.out.println(e.getMessage());

                new Alert(Alert.AlertType.ERROR,"Something missing!\n"+e.getMessage()).show();
            }
        System.out.println(txtEmail.getText()+"\n"+txtPassword.getText());
    }

}
