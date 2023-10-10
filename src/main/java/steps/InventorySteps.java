package steps;

import data.models.Customer;
import data.models.InventoryItem;
import utils.BaseTest;

import java.util.List;

public class InventorySteps extends BaseTest {

    public InventorySteps proceedToPayment(InventoryItem item, Customer customer) {
        baseRouter
                .mainMenuPage().inventoryItem.addItemToCart(item)
                .mainMenuPage().cardBtn.click()
                .cardPage().checkout.click()
                .cardPage().firstName.fill(customer.getFirstName())
                .cardPage().lastName.fill(customer.getLastName())
                .cardPage().postalCode.fill(customer.getPostalCode())
                .cardPage().continueBtn.click();
        return this;
    }

    public InventorySteps proceedToPayment(List<InventoryItem> list, Customer customer) {
        list.forEach(item -> baseRouter.mainMenuPage().inventoryItem.addItemToCart(item));
        baseRouter
                .mainMenuPage().cardBtn.click()
                .cardPage().checkout.click()
                .cardPage().firstName.fill(customer.getFirstName())
                .cardPage().lastName.fill(customer.getLastName())
                .cardPage().postalCode.fill(customer.getPostalCode())
                .cardPage().continueBtn.click();
        return this;
    }
}
