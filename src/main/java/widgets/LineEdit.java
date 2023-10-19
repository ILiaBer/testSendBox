package widgets;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import utils.BaseRouter;

import static com.codeborne.selenide.Selenide.$;

public class LineEdit extends BaseRouter {

    public LineEdit(By locator) {
        this.locator = locator;
    }

    private By locator;

    public BaseRouter fill(String value) {
        $(locator).sendKeys(value);
        return this;
    }

    public BaseRouter clear() {
        $(locator).clear();
        return this;
    }

    public BaseRouter clearAll() {
        $(locator).shouldBe(Condition.exist);
        Actions action = new Actions(WebDriverRunner.getWebDriver());
        action.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0061')).perform();
        $(locator).sendKeys(Keys.BACK_SPACE);
        return this;
    }
}
