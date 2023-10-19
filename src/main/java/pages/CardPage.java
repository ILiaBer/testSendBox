package pages;

import utils.Locators;
import widgets.Button;
import widgets.InventoryItemWidget;
import widgets.Label;
import widgets.LineEdit;

public class CardPage {

    public InventoryItemWidget cardItem = new InventoryItemWidget(Locators.CartPage.TABLE);
    public Button checkout = new Button(Locators.CartPage.CHECKOUT);
    public LineEdit firstName = new LineEdit(Locators.CartPage.FIRST_NAME);
    public LineEdit lastName = new LineEdit(Locators.CartPage.LAST_NAME);
    public LineEdit postalCode = new LineEdit(Locators.CartPage.POSTAL_CODE);
    public Button continueBtn = new Button(Locators.CartPage.CONTINUE);
    public Label error = new Label(Locators.CartPage.ERROR);
}
