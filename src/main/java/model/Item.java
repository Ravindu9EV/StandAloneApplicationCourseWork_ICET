package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Item {
    private String id;
    private String name;
    private Integer quantity;
    private Double price;
    private Double discount;
    private String brand;
    private String supplierID;
    private String sizeID;
}
