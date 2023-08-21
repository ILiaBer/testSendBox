package widgets;

import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.openqa.selenium.By;
import utils.BaseRouter;

import static com.codeborne.selenide.Selenide.$;

@CommonsLog
public class MenuItem extends BaseRouter {
    private By locator;

    public MenuItem(By locator){
        this.locator=locator;
    }

    public BaseRouter click() {
        $(locator).scrollTo();
        $(locator).click();
        return this;
    }
}
