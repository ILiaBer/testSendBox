package widgets;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.internal.util.IOUtils;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import utils.BaseRouter;
import utils.Locators;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@AllArgsConstructor
public class InventoryItem extends BaseRouter {
    private By locator;

    private byte[] returnBytesByReference(File file) {
        byte[] bytes;
        try {
            InputStream in = new FileInputStream(file);
            bytes = IOUtils.toByteArray(in);
        } catch (IOException e) {
            bytes = null;
        }
        return bytes;
    }

    public BaseRouter checkHeader(String header) {
        $$(Locators.MainPage.HEADER).filterBy(Condition.text(header)).first().shouldBe(Condition.exist);
        return this;
    }

    public BaseRouter checkDescriptionByHeader(String header, String description) {
        $$(Locators.MainPage.HEADER).filterBy(Condition.text(header)).first().$("/../../../div")
                .$(Locators.MainPage.DESCRIPTION).shouldBe(Condition.text(description));
        return this;
    }

    public BaseRouter addItemToCart(String header) {
        $$(Locators.MainPage.HEADER).filterBy(Condition.text(header)).first().$("/../../../div")
                .$(Locators.MainPage.ADD_TO_CART).click();
        return this;
    }

    public BaseRouter removeItem(String header) {
        $$(Locators.MainPage.HEADER).filterBy(Condition.text(header)).first().$("/../../../div")
                .$(Locators.MainPage.REMOVE_FROM_CART).click();
        return this;
    }

    public BaseRouter checkPriceByHeader(String header, String price) {
        $$(Locators.MainPage.HEADER).filterBy(Condition.text(header)).first().$("/../../../div")
                .$(Locators.MainPage.PRICE).shouldBe(Condition.text(price));
        return this;
    }

    public boolean isEqualWith(String header, String comparedImagePath) {
        String jsessionid = WebDriverRunner.getWebDriver().manage().getCookieNamed("JSESSIONID").getValue();
        File file = new File(comparedImagePath);
        String src = $("//div[contains(@class, 'inventory_item_name') and contains(text(), '" +
                header + "')]/../../../..//div[contains(@class, 'inventory_item_img')]//img").getAttribute("src");
        byte[] image1 = RestAssured.given().cookie(new Cookie.Builder("JSESSIONID", jsessionid).build())
                .when()
                .get(src)
                .asByteArray();
        byte[] image2 = returnBytesByReference(file);
        return Arrays.equals(image1, image2);
    }
}
