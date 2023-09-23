package data.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InventoryItem extends DataModel {

    private String login;
    private String password;
}
