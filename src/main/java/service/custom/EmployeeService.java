package service.custom;

import javafx.collections.ObservableList;
import model.Employee;
import service.SuperService;

public interface EmployeeService extends SuperService {

    boolean addEmployee(Employee employee);
    boolean editEmployee(Employee employee);
    boolean deleteEmployee(String id);
    Employee search(String id);
    ObservableList<Employee> getAll();
    boolean login(String email,String password);
    String getID();
}
