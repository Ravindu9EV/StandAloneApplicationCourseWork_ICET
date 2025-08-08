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
public class ItemEntity {
    @Id
    private String id;
    private String name;
    private Integer quantity;
    private Double price;
    private Double discount;
    private String brand;
    @ManyToOne
    @JoinColumn(name="supplier_id",nullable = false)
    private SupplierEntity supplierEntity;

    @ManyToOne
    @JoinColumn(name = "size_id",nullable = false)
    private SizeEntity sizeEntity;




//    @ManyToOne
//    @JoinColumn(name = "stock_id",nullable = false)
//    private StockEntity stockEntity;


}
