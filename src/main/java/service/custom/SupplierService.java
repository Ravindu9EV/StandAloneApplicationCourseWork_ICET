package service.custom;

import javafx.collections.ObservableList;
import model.Employee;
import model.Supplier;
import service.SuperService;

public interface SupplierService extends SuperService {
    boolean add(Supplier supplier);
    boolean edit(Supplier supplier);
    boolean delete(String id);
    Supplier search(String id);
    ObservableList<Supplier> getAll();

    String getID();
    ObservableList<String> getIDs();
}
