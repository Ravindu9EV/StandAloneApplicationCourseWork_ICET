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

public class StockEntity {
    @EmbeddedId
    private String stockID;


    private int stockCount;
//
//    @OneToMany(mappedBy = "stockEntity")
//    private Set<ItemEntity> itemEntities;


}
