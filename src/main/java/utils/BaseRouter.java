package utils;

import pages.AuthorizationPage;
import pages.MainMenuPage;
import steps.MainPageSteps;
import steps.RegistrationPageSteps;

import static com.codeborne.selenide.Selenide.page;

public class BaseRouter {

    public MainMenuPage mainMenuPage() {return page(MainMenuPage.class);}
    public AuthorizationPage authorizationPage() {return page(AuthorizationPage.class);}

    public RegistrationPageSteps registrationPageSteps() {return page(RegistrationPageSteps.class);}
    public static MainPageSteps mainPageSteps() {return page(MainPageSteps.class);}
}
