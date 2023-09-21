package widgets;

import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.openqa.selenium.By;
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
}
