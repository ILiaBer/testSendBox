package widgets;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import utils.BaseRouter;

import static com.codeborne.selenide.Selenide.$;

public class MenuItem extends BaseRouter {
    private By locator;

    public MenuItem(By locator){
        this.locator=locator;
    }

    public BaseRouter visible() {
        $(locator).shouldBe(Condition.visible);
        return this;
    }
}
