package widgets;

import com.codeborne.selenide.Condition;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import utils.BaseRouter;

import static com.codeborne.selenide.Selenide.$;

@AllArgsConstructor
public class Label extends BaseRouter {
    private By locator;

    public BaseRouter visible(){
        $(locator).shouldBe(Condition.visible);
        return this;
    }

    public BaseRouter checkText(String text){
        $(locator).shouldBe(Condition.text(text));
        return this;
    }
}
