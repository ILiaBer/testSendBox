package utils;

import com.codeborne.selenide.Configuration;
import com.google.common.collect.Lists;
import data.models.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.codeborne.selenide.Selenide.open;
import static data.dataClasses.Users.standardUser;

public class BaseTest extends BaseRouter{

    public static final ThreadLocal<ITestResult> testResult = new ThreadLocal<>();
    public static ConcurrentHashMap<ITestResult, List<Runnable>> finishMap = new ConcurrentHashMap<>();


    public static BaseRouter baseRouter;

    public BaseTest() {
        baseRouter = new BaseRouter();
    }

    @Step("Login")
    protected void login(User user) {
        baseRouter
                .authorizationPage().userLogin.fill(user.getLogin())
                .authorizationPage().password.fill(user.getPassword())
                .authorizationPage().login.click()
                .mainMenuPage().table.visible();
    }

    @Step("Login")
    protected void login() {
        login(standardUser);
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
    protected void finishMethod() {
        for (Runnable runnable : Lists.reverse(finishMap.get(testResult.get())))
            runnable.run();
        finishMap.remove(testResult.get());
        testResult.remove();
    }

    @SneakyThrows
    @Step("Pending count milliseconds")
    public static void pending(Integer countMilliseconds) {
        Thread.sleep(countMilliseconds);
    }

    public void Arrange(Allure.ThrowableRunnableVoid runnableVoid) {
        Allure.step("Arrange", runnableVoid);
    }

    public void Act(Allure.ThrowableRunnableVoid runnableVoid) {
        Allure.step("Act", runnableVoid);
    }

    public void Assert(Allure.ThrowableRunnableVoid runnableVoid) {
        Allure.step("Assert", runnableVoid);
    }

    public void Cleanup(Allure.ThrowableRunnableVoid runnableVoid) {
        Allure.step("Cleanup", runnableVoid);
    }
}
