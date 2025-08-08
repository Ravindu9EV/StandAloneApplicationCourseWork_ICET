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
public class SizeEntity {
    @Id
    private String id;
    private String name;

    @OneToMany(mappedBy = "sizeEntity")
    private Set<ItemEntity> itemEntitySet;


}
