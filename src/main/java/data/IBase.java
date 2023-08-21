package data;

import data.models.JournalMember;
import data.models.RegistrationMember;

public interface IBase {
     void fillingData(RegistrationMember member);
     void fillingData(JournalMember member);
}
