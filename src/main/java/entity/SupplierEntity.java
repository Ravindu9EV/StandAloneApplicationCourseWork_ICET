package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SupplierEntity {
    @Id
    private String id;
    private String name;
    private String location;
    private String email;

    private String contact;

    @ManyToOne
    @JoinColumn(name = "employee_id",nullable = false)
    private EmployeeEntity employeeEntity;

    @OneToMany(mappedBy = "supplierEntity")
    private Set<ItemEntity> itemEntities;

}
