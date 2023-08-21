package utils;

import data.MenuItems;
import org.openqa.selenium.By;

public interface Locators {

    interface SideBarMenu{

        By ELEMENTS = getDivByText(MenuItems.ELEMENTS.name);
        By FORMS = getDivByText(MenuItems.FORMS.name);
        By ALERTS_FRAME_WINDOWS = getDivByText(MenuItems.ALERTS_FRAME_WINDOWS.name);
        By WIDGETS = getDivByText(MenuItems.WIDGETS.name);
        By INTERACTIONS = getDivByText(MenuItems.INTERACTIONS.name);
        By BOOK_STORE_APPLICATION = getDivByText(MenuItems.BOOK_STORE_APPLICATION.name);


        static By getDivByText(String attribute) {
            return By.xpath("//div[contains(text(),'" + attribute +"')]");
        }
    }
}
