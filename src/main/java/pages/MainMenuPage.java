package pages;

import utils.Locators;
import widgets.Button;
import widgets.InventoryItemWidget;
import widgets.Sidebar;
import widgets.Table;

public class MainMenuPage {

    public Table table = new Table(Locators.MainPage.TABLE);
    public Button menuBtn = new Button(Locators.MainPage.MENU_BTN);
    public Button cardBtn = new Button(Locators.MainPage.CARD_BTN);
    public Sidebar sidebar = new Sidebar(Locators.MainPage.SIDEBAR_LIST);
    public InventoryItemWidget inventoryItem = new InventoryItemWidget(Locators.MainPage.TABLE);
}
