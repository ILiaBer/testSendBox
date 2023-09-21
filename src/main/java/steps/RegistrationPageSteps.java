package steps;

import data.models.RegistrationMember;

public class RegistrationPageSteps {

//    public RegistrationPageSteps addNewMember(RegistrationMember member){
//        member.getIBase().fillingData(member);
//        return this;
//    }

    public RegistrationPageSteps removeMember() {
        DeleteAll.putOnDeleting(() -> {
//            baseRouter.mainPage().firmware.click();
//            deleteByDescription(firmware);
        });
        return this;
    }
}
