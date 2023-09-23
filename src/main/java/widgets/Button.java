package widgets;


import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import utils.BaseRouter;

import static com.codeborne.selenide.Selenide.$;

@AllArgsConstructor
public class Button extends BaseRouter {
    private By locator;

    public BaseRouter click(){
        $(locator).scrollTo();
        $(locator).click();
        return this;
    }
}
