package steps;

import data.models.Customer;
import data.models.InventoryItem;
import utils.BaseTest;

import java.util.List;

public class InventorySteps extends BaseTest {

    public InventorySteps proceedToPaymentWithInventory(InventoryItem item, Customer customer) {
        baseRouter.mainMenuPage().inventoryItem.addItemToCart(item);
        proceedToPayment(customer);
        return this;
    }

    public InventorySteps proceedToPaymentWithInventory(List<InventoryItem> list, Customer customer) {
        list.forEach(item -> baseRouter.mainMenuPage().inventoryItem.addItemToCart(item));
        proceedToPayment(customer);
        return this;
    }

    public InventorySteps proceedToPayment(Customer customer) {
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
