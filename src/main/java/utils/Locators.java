package utils;

import data.enums.MenuItems;
import org.openqa.selenium.By;

public interface Locators {

    interface SideBarMenu {
    }

    interface AuthorizationPage {
        By USER_LOGIN = By.cssSelector("#user-name");
        By PASSWORD = By.cssSelector("#password");
        By LOGIN = By.cssSelector("#login-button");
    }

    static By getDivByText(String attribute) {
        return By.xpath("//div[contains(text(),'" + attribute + "')]");
    }
}
