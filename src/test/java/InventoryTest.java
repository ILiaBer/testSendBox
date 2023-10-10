import data.dataClasses.InventoryItems;
import data.models.Customer;
import data.models.InventoryItem;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static data.models.Customer.generateCustomer;

public class InventoryTest extends BaseTest {

    @Test(description = "Item can be added to card")
    void itemCanBeAddedToCard() {
        Cleanup(this::resetDataAfterTest);
        InventoryItem item = (InventoryItem) Tools.getRandomClassObj(InventoryItems.class);
        Arrange(this::login);
        Act(() -> {
            baseRouter
                    .mainMenuPage().inventoryItem.addItemToCart(item);
        });
        Assert(() -> {
            baseRouter
                    .mainMenuPage().cardBtn.click()
                    .cardPage().cardItem.checkHeader(item)
                    .cardPage().cardItem.checkDescriptionByHeader(item)
                    .cardPage().cardItem.checkPriceByHeader(item);
        });
    }

    @Test(description = "Item has info")
    void itemHasInfo() {
        InventoryItem item = (InventoryItem) Tools.getRandomClassObj(InventoryItems.class);
        Arrange(this::login);
        Assert(() -> {
            baseRouter
                    .mainMenuPage().inventoryItem.checkHeader(item)
                    .mainMenuPage().inventoryItem.checkDescriptionByHeader(item)
                    .mainMenuPage().inventoryItem.checkPriceByHeader(item)
                    .mainMenuPage().inventoryItem.checkAttachment(item);
        });
    }

    @Test(description = "Item can be ordered")
    void itemCanBeOrdered() {
        Customer customer = generateCustomer();
        InventoryItem item = (InventoryItem) Tools.getRandomClassObj(InventoryItems.class);
        Arrange(this::login);
        Act(() -> {
            inventorySteps().proceedToPayment(item, customer);
            baseRouter.checkoutOverviewPage().finish.click();
        });
        Assert(() -> {
            baseRouter
                    .checkoutOverviewPage().orderComplete.visible();
        });
    }

    @Test(description = "Tax on one product")
    void taxOnOneProduct() {
        Cleanup(this::resetDataAfterTest);
        Customer customer = generateCustomer();
        InventoryItem item = (InventoryItem) Tools.getRandomClassObj(InventoryItems.class);
        Arrange(this::login);
        Act(() -> {
            inventorySteps().proceedToPayment(item, customer);
        });
        Assert(() -> {
            baseRouter
                    .checkoutOverviewPage().tax.checkText(Tools.calculateTax(item.getPrice()).toString());
        });
    }

    @Test(description = "Tax on multiple product")
    void taxOnMultipleProduct() {
        Cleanup(this::resetDataAfterTest);
        Customer customer = generateCustomer();
        InventoryItem firstItem = (InventoryItem) Tools.getRandomClassObj(InventoryItems.class);
        InventoryItem secondItem = (InventoryItem) Tools.getRandomClassObjExceptList(InventoryItems.class, List.of(firstItem));
        InventoryItem thirdItem = (InventoryItem) Tools.getRandomClassObjExceptList(InventoryItems.class, List.of(firstItem, secondItem));



        Arrange(this::login);
        Act(() -> {
            inventorySteps().proceedToPayment(List.of(firstItem,secondItem, thirdItem), customer);
        });
        Assert(() -> {
//            baseRouter
//                    .checkoutOverviewPage().tax.checkText(Tools.calculateTax(item.getPrice()).toString());
        });
    }

    @Test(description = "Total on one product")
    void totalOnOneProduct() {
        Cleanup(this::resetDataAfterTest);
        Customer customer = generateCustomer();
        InventoryItem item = (InventoryItem) Tools.getRandomClassObj(InventoryItems.class);
        Arrange(this::login);
        Act(() -> {
            inventorySteps().proceedToPayment(item, customer);
        });
        Assert(() -> {
            baseRouter
                    .checkoutOverviewPage().total.checkText(Tools.calculateTotal(item.getPrice()).toString());
        });
    }

}
