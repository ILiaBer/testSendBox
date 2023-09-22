import data.dataClasses.Users;
import data.enums.UserLogins;
import data.models.BaseTestModel;
import data.models.User;
import org.testng.annotations.Test;
import utils.BaseTest;

import static data.dataClasses.Users.generateUser;
import static data.dataClasses.Users.standardUser;
import static utils.Tools.getRandomEnumExceptOne;

public class AuthorizationTests extends BaseTest {

    @Test(description = "User can be registered", dataProviderClass = Users.class, dataProvider = "positiveAuthorization")
    void userCanBeRegistered(BaseTestModel<User> user) {
        Act(() -> {
            baseRouter
                    .authorizationPage().userLogin.fill(user.getBaseModel().getLogin())
                    .authorizationPage().password.fill(user.getBaseModel().getPassword())
                    .authorizationPage().login.click();
        });
        Assert(() -> {
            baseRouter.mainMenuPage().table.visible();
        });
    }

    @Test(description = "Locked user cant be registered")
    void lockedUserCantBeRegistered() {
        User user = generateUser(UserLogins.LOCKED_OUT_USER.getName(), Users.defaultPass);
        Act(() -> {
        baseRouter
                .authorizationPage().userLogin.fill(user.getLogin())
                .authorizationPage().password.fill(user.getPassword())
                .authorizationPage().login.click();
        });
        baseRouter
                .mainMenuPage().table.notVisible();
    }

    @Test(description = "Logout user")
    void logoutUser() {
        UserLogins login = getRandomEnumExceptOne(UserLogins.LOCKED_OUT_USER);
        login(generateUser(login.getName(), Users.defaultPass));
    }
}