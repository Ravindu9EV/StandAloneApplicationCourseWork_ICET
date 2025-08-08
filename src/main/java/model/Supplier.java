package model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Supplier {
    private String id;
    private String name;
    private String location;
    private String email;

    private String contact;
    private String employeeID;
}
