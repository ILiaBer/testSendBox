package pages;

import utils.Locators;
import widgets.LineEdit;

public class RegistrationPage {

    public LineEdit name = new LineEdit(Locators.RegistrationPage.NAME);
    public LineEdit email = new LineEdit(Locators.RegistrationPage.EMAIL);
    public LineEdit currentAddress = new LineEdit(Locators.RegistrationPage.CURRENT_ADDRESS);
    public LineEdit permanentAddress = new LineEdit(Locators.RegistrationPage.PERMANENT_ADDRESS);
}
