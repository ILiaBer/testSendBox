package data.models;

import data.IBase;
import org.testng.Assert;

public class JournalMemberToFill implements IBase {

    @Override
    public void fillingData(RegistrationMember member) {
        throw new AssertionError("There is not Journal member");
    }

    @Override
    public void fillingData(JournalMember member) {

    }
}
