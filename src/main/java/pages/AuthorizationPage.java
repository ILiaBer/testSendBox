package pages;

import utils.Locators;
import widgets.Button;
import widgets.LineEdit;

public class AuthorizationPage {

    public LineEdit userLogin = new LineEdit(Locators.AuthorizationPage.USER_LOGIN);
    public LineEdit password = new LineEdit(Locators.AuthorizationPage.PASSWORD);
    public Button login = new Button(Locators.AuthorizationPage.LOGIN);
}
