package service.custom;

import javafx.collections.ObservableList;
import model.Customer;
import model.Employee;
import service.SuperService;

public interface CustomerService extends SuperService {
    boolean add(Customer customer);
    boolean edit(Customer customer);
    boolean delete(String id);
    Customer search(String id);
    ObservableList<Customer> getAll();
    String getID();
}
