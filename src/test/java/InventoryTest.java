import data.dataClasses.InventoryItems;
import data.models.Customer;
import data.models.InventoryItem;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BaseTest;
import utils.Tools;

import java.util.ArrayList;
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
            inventorySteps().proceedToPaymentWithInventory(item, customer);
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
            inventorySteps().proceedToPaymentWithInventory(item, customer);
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
        List list = new ArrayList();
        InventoryItem firstItem = (InventoryItem) Tools.getRandomClassObj(InventoryItems.class);
        list.add(firstItem);
        InventoryItem secondItem = (InventoryItem) Tools.getRandomClassObjExceptList(InventoryItems.class, list);
        list.add(secondItem);
        InventoryItem thirdItem = (InventoryItem) Tools.getRandomClassObjExceptList(InventoryItems.class, list);
        list.add(thirdItem);
        Arrange(this::login);
        Act(() -> {
            inventorySteps().proceedToPaymentWithInventory(list, customer);
        });
        Assert(() -> {
            baseRouter
                    .checkoutOverviewPage().tax.checkText(Tools.calculateTax(List.of(
                            firstItem.getPrice(), secondItem.getPrice(), thirdItem.getPrice())).toString());
        });
    }

    @Test(description = "Total on one product")
    void totalOnOneProduct() {
        Cleanup(this::resetDataAfterTest);
        Customer customer = generateCustomer();
        InventoryItem item = (InventoryItem) Tools.getRandomClassObj(InventoryItems.class);
        Arrange(this::login);
        Act(() -> {
            inventorySteps().proceedToPaymentWithInventory(item, customer);
        });
        Assert(() -> {
            baseRouter
                    .checkoutOverviewPage().total.checkText(Tools.calculateTotal(item.getPrice()).toString());
        });
    }

    @Test(description = "Total on multiple product")
    void totalOnMultipleProduct() {
        Cleanup(this::resetDataAfterTest);
        Customer customer = generateCustomer();
        List list = new ArrayList();
        InventoryItem firstItem = (InventoryItem) Tools.getRandomClassObj(InventoryItems.class);
        list.add(firstItem);
        InventoryItem secondItem = (InventoryItem) Tools.getRandomClassObjExceptList(InventoryItems.class, list);
        list.add(secondItem);
        InventoryItem thirdItem = (InventoryItem) Tools.getRandomClassObjExceptList(InventoryItems.class, list);
        list.add(thirdItem);
        Arrange(this::login);
        Act(() -> {
            inventorySteps().proceedToPaymentWithInventory(list, customer);
        });
        Assert(() -> {
            baseRouter
                    .checkoutOverviewPage().total.checkText(Tools.calculateTotal(List.of(
                            firstItem.getPrice(), secondItem.getPrice(), thirdItem.getPrice())).toString());
        });
    }

    @Test(description = "Required fields for delivery information")
    void requiredFieldsForDeliveryInformation() {
        Cleanup(this::resetDataAfterTest);
        Arrange(this::login);
        Customer customer = generateCustomer();
        SoftAssert softAssert = new SoftAssert();
        baseRouter
                .mainMenuPage().cardBtn.click()
                .cardPage().checkout.click();
        Act(() -> {
          baseRouter
                  .cardPage().firstName.fill(customer.getFirstName())
                  .cardPage().lastName.fill(customer.getLastName())
                  .cardPage().continueBtn.click();
            softAssert.assertTrue(baseRouter.cardPage().error.isVisible());
          baseRouter
                  .cardPage().postalCode.fill(customer.getPostalCode())
                  .cardPage().lastName.clearAll()
                  .cardPage().continueBtn.click();
            softAssert.assertTrue(baseRouter.cardPage().error.isVisible());
            baseRouter
                    .cardPage().lastName.fill(customer.getLastName())
                    .cardPage().firstName.clearAll()
                    .cardPage().continueBtn.click();
            softAssert.assertTrue(baseRouter.cardPage().error.isVisible());
        });
        Assert(softAssert::assertAll);
    }
}
