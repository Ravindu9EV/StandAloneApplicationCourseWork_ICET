package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CustomerEntity {
    @Id
    private String id;
    private String name;
    private String location;
    private String email;
    private String password;
    private String contact;

    @ManyToOne
    @JoinColumn(name="employee_id",nullable = false)
    private EmployeeEntity employeeEntity;
    private Timestamp timestamp;
}
