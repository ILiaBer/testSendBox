package widgets;

import org.openqa.selenium.By;
import utils.BaseRouter;

import static com.codeborne.selenide.Selenide.$;

public class Table extends BaseRouter {
    private By locator;

    public Table(By locator) {
        this.locator = locator;
    }

    public BaseRouter click() {
        $(locator).scrollTo();
        $(locator).click();
        return this;
    }
}
