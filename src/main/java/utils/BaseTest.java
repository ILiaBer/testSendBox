package utils;

import com.codeborne.selenide.Configuration;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.MainMenuPage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    public static final ThreadLocal<ITestResult> testResult = new ThreadLocal<>();
    public static ConcurrentHashMap<ITestResult, List<Runnable>> finishMap = new ConcurrentHashMap<>();


    public static BaseRouter baseRouter;

    public BaseTest() {
        baseRouter = new BaseRouter();
    }

    @BeforeMethod
    protected void setUp(ITestResult result) {
        testResult.set(result);
        finishMap.put(result, new ArrayList<>());
        Configuration.browserSize = "1920x1070";
        System.setProperty("webdriver.chrome.driver", "src/main/java/utils/chromedriver.exe");
        open("https://www.saucedemo.com/");
    }

    @AfterMethod(alwaysRun = true)
    protected void finishMethod(){
        for (Runnable runnable : Lists.reverse(finishMap.get(testResult.get())))
            runnable.run();
        finishMap.remove(testResult.get());
        testResult.remove();
    }
}
