package widgets;

import com.codeborne.selenide.CollectionCondition;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import utils.BaseRouter;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
@AllArgsConstructor
public class Image extends BaseRouter{

        private By locator;

    public BaseRouter checkAttachmentInList(POJO.models.Comment comment, File attachment) {
        attachmentsCollectionInComment(comment)
                .filterBy(text(attachment.getName()))
                .shouldHave(CollectionCondition.sizeGreaterThan(0));
        return this;
    }
    }
