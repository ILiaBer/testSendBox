package data.dataClasses;

import data.enums.UserLogins;
import data.models.BaseTestModel;
import data.models.User;
import io.github.sskorol.core.DataSupplier;

import java.util.stream.Stream;

public class Users {
    public static final String defaultPass = "secret_sauce";

    public static User standardUser = User.builder().login(UserLogins.STANDARD_USER.getName()).password(defaultPass).build();
    public static User problemUser = User.builder().login(UserLogins.PROBLEM_USER.getName()).password(defaultPass).build();
    public static User performanceGlitchUser = User.builder().login(UserLogins.PERFORMANCE_GLITCH_USER.getName()).password(defaultPass).build();
    public static User lockedOutUser = User.builder().login(UserLogins.LOCKED_OUT_USER.getName()).password(defaultPass).build();

    public BaseTestModel<User> generateUserWithTestInfo(String testName, User user) {
        return new BaseTestModel<>(testName, user);
    }

    public static User generateUser(String login, String pass) {
        return User.builder()
                .login(login)
                .password(pass)
                .build();
    }

    @DataSupplier(name = "positiveAuthorization")
    public Stream<BaseTestModel<User>> positiveAuthorization() {
        return Stream.of(
                generateUserWithTestInfo("Standard user can be authorize", generateUser(UserLogins.STANDARD_USER.getName(), defaultPass)),
                generateUserWithTestInfo("Problem user can be authorize", generateUser(UserLogins.PROBLEM_USER.getName(), defaultPass)),
                generateUserWithTestInfo("Performance glitch user can be authorize", generateUser(UserLogins.PERFORMANCE_GLITCH_USER.getName(), defaultPass)));
    }
}
