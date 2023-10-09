package data.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InventoryItem extends DataModel {

    private String header;
    private String description;
    private Double price;
    private String imgPath;
}
