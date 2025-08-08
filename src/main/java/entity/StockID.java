package entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Data
@ToString
public class StockID implements Serializable {
    private String itemID;
    private String sizeID;

     public StockID(String itemID,String sizeID){
        this.itemID=itemID;
        this.sizeID=sizeID;
    }

    @Override
    public boolean equals(Object stockID){
        return stockID  instanceof StockID? ((StockID) stockID).itemID==this.itemID && ((StockID)stockID).sizeID==this.sizeID:false;
    }

    @Override
    public int hashCode(){
        return this.sizeID.hashCode()+this.itemID.hashCode();
    }

}
