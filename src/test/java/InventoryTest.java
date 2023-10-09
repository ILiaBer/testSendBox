import com.github.javafaker.Faker;
import data.dataClasses.InventoryItems;
import data.models.InventoryItem;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.Tools;

public class InventoryTest extends BaseTest {

    @Test(description = "Item can be added to card")
    void itemCanBeAddedToCard() {
        resetDataAfterTest();
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
        Faker faker = new Faker();
        InventoryItem item = (InventoryItem) Tools.getRandomClassObj(InventoryItems.class);
        Arrange(this::login);
        Act(() -> {
            baseRouter
                    .mainMenuPage().inventoryItem.addItemToCart(item)
                    .mainMenuPage().cardBtn.click()
                    .cardPage().checkout.click()
                    .cardPage().firstName.fill(faker.name().firstName())
                    .cardPage().lastName.fill(faker.name().lastName())
                    .cardPage().postalCode.fill(String.valueOf(faker.number().randomNumber()))
                    .cardPage().continueBtn.click()
                    .checkoutOverviewPage().total.checkText(Tools.calculateTotal(item.getPrice()).toString())
                    .checkoutOverviewPage().tax.checkText(Tools.calculateTax(item.getPrice()).toString());


        });
    }
}
