package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardControler {

    private final Stage stage=new Stage();
    @FXML
    void btnEmployeeFormOnAction(ActionEvent event) {

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/add_employee_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnViewSalesFormOnAction(ActionEvent event) {

    }
    @FXML
    void btnEditAcountOnAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/admin_acount_form.fxml"))));
            stage.show();
            AdminAccountFormController.stage=this.stage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
