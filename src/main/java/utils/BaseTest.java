package utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.common.collect.Lists;
import data.models.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import steps.DeleteAll;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.codeborne.selenide.Selenide.open;
import static data.dataClasses.Users.standardUser;

public class BaseTest extends BaseRouter {

    public static final ThreadLocal<ITestResult> testResult = new ThreadLocal<>();
    public static ConcurrentHashMap<ITestResult, List<Runnable>> finishMap = new ConcurrentHashMap<>();


    public static BaseRouter baseRouter;

    public RemoteWebDriver driver;

    public final static String pathToSaucePullover = "src/main/resources/SaucePullover.jpg";
    public final static String pathToSauceBackpack = "src/main/resources/SauceBackpack.jpg";
    public final static String pathToRedTatt = "src/main/resources/RedTatt.jpg";
    public final static String pathToRedOnesie = "src/main/resources/RedOnesie.jpg";
    public final static String pathToBoltShirt = "src/main/resources/BoltShirt.jpg";
    public final static String pathToBikeLight = "src/main/resources/BikeLight.jpg";

    public BaseTest() {
        baseRouter = new BaseRouter();
    }

    @BeforeMethod
    protected void setUp(ITestResult result) throws MalformedURLException {
        SelenideLogger.addListener("allure", new AllureSelenide());
        testResult.set(result);
        finishMap.put(result, new ArrayList<>());
        Configuration.browserSize = "1920x1070";
        if (shouldRunLocally()) {
            System.setProperty("webdriver.chrome.driver", "src/main/java/utils/chromedriver.exe");
        } else {
            Configuration.remote = "http://localhost:4444/wd/hub";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            Configuration.browserCapabilities = capabilities;
        }
        open("https://www.saucedemo.com/");
    }

    private boolean shouldRunLocally() {
        TestProperties testProperties = new TestProperties();
        return testProperties.isLocalRun();
    }

    @Step("Login")
    protected void login(User user) {
        baseRouter
                .authorizationPage().userLogin.fill(user.getLogin())
                .authorizationPage().password.fill(user.getPassword())
                .authorizationPage().login.click()
                .mainMenuPage().table.visible();
    }

    @Step("Reset data after test")
    protected void resetDataAfterTest() {
        DeleteAll.putOnDeleting(() -> {
            baseRouter
                    .mainMenuPage().menuBtn.click()
                    .mainMenuPage().sidebar.resetData();
        });
    }

    @Step("Login")
    protected void login() {
        login(standardUser);
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
