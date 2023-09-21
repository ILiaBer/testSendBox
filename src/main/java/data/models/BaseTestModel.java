package data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
public class BaseTestModel<T extends DataModel> {

    @Setter
    @Getter
    private String testName;

    @Setter
    @Getter
    private T baseModel;
}
