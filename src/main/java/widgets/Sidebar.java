package widgets;

import com.codeborne.selenide.Condition;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import utils.BaseRouter;
import utils.Locators;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

@AllArgsConstructor
public class Sidebar extends BaseRouter {
    private By locator;

    public BaseRouter resetData() {
        $(locator).shouldBe(Condition.visible, Duration.ofSeconds(5));
        $(locator).$(Locators.MainPage.RESET_DATA).click();
        return this;
    }

    public BaseRouter logout() {
        $(locator).shouldBe(Condition.visible, Duration.ofSeconds(5));
        $(locator).$(Locators.MainPage.LOGOUT).click();
        return this;
    }
}
