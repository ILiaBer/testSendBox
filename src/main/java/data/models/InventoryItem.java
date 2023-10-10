package data.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InventoryItem extends DataModel {

    public String header;
    public String description;
    public Double price;
    public String imgPath;
}
