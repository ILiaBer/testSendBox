package data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserLogins {

    STANDARD_USER("standard_user"),
    LOCKED_OUT_USER("locked_out_user"),
    PROBLEM_USER("problem_user12"),
    PERFORMANCE_GLITCH_USER("performance_glitch_user12");

    @Getter
    private String name;
}
