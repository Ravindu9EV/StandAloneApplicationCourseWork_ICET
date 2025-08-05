package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AdminEntity {
    @Id
    private String id;
    private String name;
    private String location;
    private String email;
    private String password;
    private String contact;
}
