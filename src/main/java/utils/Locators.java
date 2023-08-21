package utils;

import data.enums.MenuItems;
import org.openqa.selenium.By;

public interface Locators {

    interface SideBarMenu {
        By ELEMENTS = getDivByText(MenuItems.ELEMENTS.getName());
        By FORMS = getDivByText(MenuItems.FORMS.getName());
        By ALERTS_FRAME_WINDOWS = getDivByText(MenuItems.ALERTS_FRAME_WINDOWS.getName());
        By WIDGETS = getDivByText(MenuItems.WIDGETS.name);
        By INTERACTIONS = getDivByText(MenuItems.INTERACTIONS.getName());
        By BOOK_STORE_APPLICATION = getDivByText(MenuItems.BOOK_STORE_APPLICATION.getName());
    }

    interface RegistrationPage {
        By NAME = By.cssSelector("#userName");
        By EMAIL = By.cssSelector("#userEmail");
        By CURRENT_ADDRESS = By.cssSelector("#currentAddress");
        By PERMANENT_ADDRESS = By.cssSelector("#permanentAddress");
    }

    static By getDivByText(String attribute) {
        return By.xpath("//div[contains(text(),'" + attribute + "')]");
    }
}
