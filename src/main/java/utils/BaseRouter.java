package utils;

import pages.AuthorizationPage;
import pages.CardPage;
import pages.CheckoutOverviewPage;
import pages.MainMenuPage;
import steps.InventorySteps;
import steps.MainPageSteps;

import static com.codeborne.selenide.Selenide.page;

public class BaseRouter {

    public MainMenuPage mainMenuPage() {return page(MainMenuPage.class);}
    public AuthorizationPage authorizationPage() {return page(AuthorizationPage.class);}
    public CardPage cardPage() {return page(CardPage.class);}
    public CheckoutOverviewPage checkoutOverviewPage() {return page(CheckoutOverviewPage.class);}


    public static MainPageSteps mainPageSteps() {return page(MainPageSteps.class);}
    public static InventorySteps inventorySteps() {return page(InventorySteps.class);}
}
