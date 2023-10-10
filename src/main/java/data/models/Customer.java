package data.models;

import com.github.javafaker.Faker;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer extends DataModel {

    private String firstName;
    private String lastName;
    private String postalCode;

    public static Customer generateCustomer() {
        Faker faker = new Faker();
        return Customer.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .postalCode(String.valueOf(faker.number().randomNumber()))
                .build();
    }
}
