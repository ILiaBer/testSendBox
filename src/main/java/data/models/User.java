package data.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User extends DataModel {
    private String login;
    private String password;
}
