package utils;

import pages.MainMenuPage;

import static com.codeborne.selenide.Selenide.page;

public class BaseRouter {

    public MainMenuPage mainMenuPage() {return page(MainMenuPage.class);}
}
