package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Admin {
    private String id;
    private String name;
    private String location;
    private String email;
    private String password;
    private String contact;
}
