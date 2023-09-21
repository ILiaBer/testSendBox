package data.models;

import data.IBase;

//import static utils.BaseRouter.baseRouter;


public class RegistrationMemberToFill implements IBase {


    @Override
    public void fillingData(RegistrationMember member) {
//        baseRouter
//                .registrationPage().name.fill(member.getName())
//                .registrationPage().email.fill(member.getEmail())
//                .registrationPage().currentAddress.fill(member.getCurrentAddress())
//                .registrationPage().permanentAddress.fill(member.getPermanentAddress());
    }

    @Override
    public void fillingData(JournalMember member) {
        throw new AssertionError("There is not Registration member");
    }
}
