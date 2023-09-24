package pages;

import utils.Locators;
import widgets.Button;
import widgets.InventoryItem;
import widgets.Sidebar;
import widgets.Table;

public class MainMenuPage {

    public Table table = new Table(Locators.MainPage.TABLE);
    public Button menuBtn = new Button(Locators.MainPage.MENU_BTN);
    public Sidebar sidebar = new Sidebar(Locators.MainPage.SIDEBAR_LIST);
    public InventoryItem inventoryItem = new InventoryItem(Locators.MainPage.INVENTORY_ITEM);
}
