package utils;

import pages.MainMenuPage;
import pages.RegistrationPage;
import steps.RegistrationPageSteps;

import static com.codeborne.selenide.Selenide.page;

public class BaseRouter {

    public MainMenuPage mainMenuPage() {return page(MainMenuPage.class);}
    public RegistrationPage registrationPage() {return page(RegistrationPage.class);}

    public RegistrationPageSteps registrationPageSteps() {return page(RegistrationPageSteps.class);}
}
