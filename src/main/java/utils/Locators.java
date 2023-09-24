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

        By INVENTORY_ITEM = By.cssSelector(".inventory_item");
        By HEADER = By.xpath("//div[contains(@class, 'inventory_item_name')]");
        By DESCRIPTION = By.xpath("//div[contains(@class, 'inventory_item_description')]//div[contains(@class, 'inventory_item_desc')]");
        By PRICE = By.xpath("//div[contains(@class, 'inventory_item_price')]");
        By ADD_TO_CART = By.xpath("//button[contains(text(), 'Add to cart')]");
        By REMOVE_FROM_CART = By.xpath("//button[contains(text(), 'Remove')]");




    }

//    interface MainPage {
//
//    }

    static By getDivByText(String attribute) {
        return By.xpath("//div[contains(text(),'" + attribute + "')]");
    }
}
