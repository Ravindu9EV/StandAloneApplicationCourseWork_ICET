package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Supplier;
import service.ServiceFactory;
import service.custom.ItemService;
import service.custom.SupplierService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class AddItemFormController implements Initializable {

    @FXML
    private ComboBox<?> cmbSize;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colStock;

    @FXML
    private TableView<?> tblEmployees;

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
    @FXML
    private ComboBox<?> cmbSupplierID;

    ItemService service=ServiceFactory.getInstance().getService(ServiceType.ITEM);
    @FXML
    void btnAddItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnEditItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchItemOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        txtId.setText(service.getID());
       // cmbSupplierID.setItems();
    }


}
