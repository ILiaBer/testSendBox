import data.dataClasses.Users;
import data.models.BaseTestModel;
import data.models.User;
import org.testng.annotations.Test;
import utils.BaseTest;

public class AuthorizationTests extends BaseTest {

    @Test(description = "Member can be registered", dataProviderClass = Users.class, dataProvider = "positiveAuthorization")
    void checkMemberCanBeRegistered(BaseTestModel<User> user){
        baseRouter
                .authorizationPage().userLogin.fill(user.getBaseModel().getLogin())
                .authorizationPage().password.fill(user.getBaseModel().getPassword())
                .authorizationPage().login.click()
                .mainMenuPage().table.visible();
    }
}