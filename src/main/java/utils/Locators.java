package utils;

import data.enums.MenuItems;
import org.openqa.selenium.By;

public interface Locators {

    interface AuthorizationPage {
        By USER_LOGIN = By.cssSelector("#user-name");
        By PASSWORD = By.cssSelector("#password");
        By LOGIN = By.cssSelector("#login-button");
        By CONTAINER_LABEL = By.cssSelector("#login_button_container");
    }

    interface MainPage {
        By TABLE = By.cssSelector("#inventory_container");
        By MENU_BTN = By.cssSelector("#react-burger-menu-btn");
        By SIDEBAR_LIST = By.xpath("//nav[contains(@class, bm-item-list)]");
        By LOGOUT = By.cssSelector("#logout_sidebar_link");
        By RESET_DATA = By.cssSelector("#reset_sidebar_link");
        By CARD_BTN = By.cssSelector("#shopping_cart_container");

        By HEADER = By.xpath(".//div[contains(@class, 'inventory_item_name')]");
    }

    interface CartPage {
        By TABLE = By.cssSelector("#cart_contents_container");
        By CHECKOUT = By.cssSelector("#checkout");
        By FIRST_NAME = By.cssSelector("#first-name");
        By LAST_NAME = By.cssSelector("#last-name");
        By POSTAL_CODE = By.cssSelector("#postal-code");
        By CONTINUE = By.cssSelector("#continue");
    }

    interface CheckoutOverviewPage {
        By TABLE = By.xpath("//div[contains(@class,'cart_list')]");
        By TOTAL = By.xpath("//div[contains(@class,'summary_total_label')]");
        By TAX = By.xpath("//div[contains(@class,'summary_tax_label')]");

    }



    static By getDivByText(String attribute) {
        return By.xpath("//div[contains(text(),'" + attribute + "')]");
    }
}
