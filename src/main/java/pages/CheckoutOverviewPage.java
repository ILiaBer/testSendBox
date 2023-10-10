package pages;

import utils.Locators;
import widgets.Button;
import widgets.InventoryItemWidget;
import widgets.Label;

public class CheckoutOverviewPage {

    public InventoryItemWidget cardItem = new InventoryItemWidget(Locators.CheckoutOverviewPage.TABLE);
    public Label total = new Label(Locators.CheckoutOverviewPage.TOTAL);
    public Label tax = new Label(Locators.CheckoutOverviewPage.TAX);
    public Button finish = new Button(Locators.CheckoutOverviewPage.FINISH);
    public Label orderComplete = new Label(Locators.CheckoutOverviewPage.ORDER_COMPLETE);
}
