package widgets;

import com.codeborne.selenide.Condition;
import data.models.InventoryItem;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import utils.BaseRouter;
import utils.Locators;
import utils.Tools;

import java.io.File;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;

@AllArgsConstructor
public class InventoryItemWidget extends BaseRouter {
    private By locator;

    public BaseRouter checkHeader(String header) {
        $(locator).$$(Locators.MainPage.HEADER).filterBy(Condition.text(header)).first().shouldBe(Condition.exist);
        return this;
    }

    public BaseRouter checkHeader(String header, SoftAssert softAssert) {
        softAssert.assertTrue($(locator).$$(Locators.MainPage.HEADER).filterBy(Condition.text(header)).first()
                .is(Condition.exist));
        return this;
    }

    public BaseRouter checkHeader(InventoryItem item) {
        checkHeader(item.getHeader());
        return this;
    }

    public BaseRouter checkHeader(InventoryItem item, SoftAssert softAssert) {
        checkHeader(item.getHeader(), softAssert);
        return this;
    }

    public BaseRouter checkDescriptionByHeader(String header, String description) {
        $(locator).$x(".//div[contains(@class, 'inventory_item_name') and contains(text(), '" + header + "')]/../../div")
                .shouldHave(Condition.text(description));
        return this;
    }

    public BaseRouter checkDescriptionByHeader(String header, String description, SoftAssert softAssert) {
        softAssert.assertTrue($(locator).$x(".//div[contains(@class, 'inventory_item_name') and contains(text(), '"
                + header + "')]/../../div").is(Condition.text(description)));
        return this;
    }

    public BaseRouter checkDescriptionByHeader(InventoryItem item) {
        checkDescriptionByHeader(item.getHeader(), item.getDescription());
        return this;
    }

    public BaseRouter checkDescriptionByHeader(InventoryItem item, SoftAssert softAssert) {
        checkDescriptionByHeader(item.getHeader(), item.getDescription(), softAssert);
        return this;
    }

    public BaseRouter addItemToCart(String header) {
        $(locator).$x(".//div[contains(@class, 'inventory_item_name') and contains(text(), '" + header + "')]" +
                "//following::button[contains(@name,'add-to-cart')]").click();
        return this;
    }

    public BaseRouter addItemToCart(InventoryItem item) {
        addItemToCart(item.getHeader());
        return this;
    }

    public BaseRouter removeItem(String header) {
        $(locator).$x(".//div[contains(@class, 'inventory_item_name') and contains(text(), '" + header + "')]" +
                "//following::button[contains(@name,'remove')]").click();
        return this;
    }

    public BaseRouter removeItem(InventoryItem item) {
        removeItem(item.getHeader());
        return this;
    }

    public BaseRouter checkPriceByHeader(String header, Double price) {
        $(locator).$x(".//div[contains(@class, 'inventory_item_name') and contains(text(), '" + header + "')]" +
                "/../../..//div[contains(@class, 'inventory_item_price')]").shouldHave(Condition.text("$" + price.toString()));
        return this;
    }

    public BaseRouter checkPriceByHeader(String header, Double price, SoftAssert softAssert) {
        softAssert.assertTrue($(locator).$x(".//div[contains(@class, 'inventory_item_name') and contains(text(), '" + header + "')]" +
                "/../../..//div[contains(@class, 'inventory_item_price')]").is(Condition.text("$" + price.toString())));
        return this;
    }

    public BaseRouter checkPriceByHeader(InventoryItem item) {
        checkPriceByHeader(item.getHeader(), item.getPrice());
        return this;
    }

    public BaseRouter checkPriceByHeader(InventoryItem item, SoftAssert softAssert) {
        checkPriceByHeader(item.getHeader(), item.getPrice(), softAssert);
        return this;
    }

    @SneakyThrows
    public void checkAttachment(String header, String comparedImagePath) {
        String imgUrl = $(locator).$x(".//div[contains(@class, 'inventory_item_name') and contains(text(), '"
                + header + "')]" + "/../../../..//img").getAttribute("src");
        URL url = new URL(imgUrl);
        File file = new File(comparedImagePath);
        Tools.compareImages(file, url);
    }

    @SneakyThrows
    public void checkAttachment(String header, String comparedImagePath, SoftAssert softAssert) {
        String imgUrl = $(locator).$x(".//div[contains(@class, 'inventory_item_name') and contains(text(), '"
                + header + "')]" + "/../../../..//img").getAttribute("src");
        URL url = new URL(imgUrl);
        File file = new File(comparedImagePath);
        softAssert.assertTrue(Tools.compareImages(file, url));
    }

    @SneakyThrows
    public void checkAttachment(InventoryItem item, SoftAssert softAssert) {
        checkAttachment(item.getHeader(), item.getImgPath(), softAssert);
    }

    @SneakyThrows
    public void checkAttachment(InventoryItem item) {
        checkAttachment(item.getHeader(), item.getImgPath());
    }
}
