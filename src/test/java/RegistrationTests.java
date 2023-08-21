import org.testng.annotations.Test;
import utils.BaseTest;

import static data.models.RegistrationMember.generateNewRegMember;

//import static data.models.RegistrationMember.generateNewRegMember;

public class RegistrationTests extends BaseTest {

    @Test(description = "Member can be registered")
    void checkMemberCanBeRegistered(){
        baseRouter.registrationPageSteps().addNewMember(generateNewRegMember());
    }

}
