package utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.common.collect.Lists;
import data.models.User;
import data.models.api.AllureResponse;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import steps.DeleteAll;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.codeborne.selenide.Selenide.open;
import static data.dataClasses.Users.standardUser;
import static utils.ApiTools.getAllureInfo;
import static utils.Tools.getAllureUrl;

public class BaseTest extends BaseRouter {

    public static final ThreadLocal<ITestResult> testResult = new ThreadLocal<>();
    public static ConcurrentHashMap<ITestResult, List<Runnable>> finishMap = new ConcurrentHashMap<>();

    public final static String allureProjectId = "my-project-id";  //Also in send_results.ps1

    private final static String resources = "src/main/resources/";
    public final static String pathToSaucePullover = resources + "SaucePullover.jpg";
    public final static String pathToSauceBackpack = resources + "SauceBackpack.jpg";
    public final static String pathToRedTatt = resources + "RedTatt.jpg";
    public final static String pathToRedOnesie = resources + "RedOnesie.jpg";
    public final static String pathToBoltShirt = resources + "BoltShirt.jpg";
    public final static String pathToBikeLight = resources + "BikeLight.jpg";

    @SneakyThrows
    @AfterSuite
    public static void sendNotification() {
        String chatId = "-1001800988927";
        if (TestProperties.isNotificationEnabled()) {
            TestResultsBot bot = new TestResultsBot();
            String[] cmd = {"powershell.exe", "./send_results.ps1"};
            Runtime.getRuntime().exec(cmd);
            try {
                if (TestProperties.isAllureEnabled()) {
                    pending(5000);                                //Otherwise, new data does not have
                    AllureResponse response = createAllureProjectIfNotExist();
                    bot.sendAllureReport(chatId, getAllureUrl(response.getData().getProject().getReports()));
                } else {
                    bot.sendAllureReport(chatId);
                }
            } catch (NullPointerException e) {
                Reporter.log("Something went wrong with allureProjectId" + e.getMessage());
            }
        }
    }

    private static AllureResponse createAllureProjectIfNotExist() {
        AllureResponse response = getAllureInfo();
        if (response.getMetaData().getMessage().equals("project_id '" + allureProjectId + "' not found")) {
            ApiTools.createProject();
        }
        return response;
    }

    @SneakyThrows
    @BeforeClass
    public static void cleanResults() {
        File allureResults = new File("build/allure-results");
        if (allureResults.exists()) {
            FileUtils.deleteDirectory(allureResults);
        }
        if (TestProperties.isAllureEnabled()) {
            ApiTools.cleanResults();
        }
    }@BeforeMethod
    protected void setUp(ITestResult result) {
        SelenideLogger.addListener("allure", new AllureSelenide());
        testResult.set(result);
        finishMap.put(result, new ArrayList<>());
        Configuration.browserSize = "1920x1070";

        ChromeOptions chromeOptions = createChromeOptions();

        if (shouldRunLocally()) {
            System.setProperty("webdriver.chrome.driver", "src/main/java/utils/chromedriver.exe");
            Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        } else {
            Configuration.remote = "http://localhost:4444";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            Configuration.browserCapabilities = capabilities;
        }

        open("https://www.saucedemo.com/");
    }

    private ChromeOptions createChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-extensions");
        return chromeOptions;
    }

    private static boolean shouldRunLocally() {
        TestProperties testProperties = new TestProperties();
        return testProperties.isLocalRun();
    }

    @Step("Login")
    protected void login(User user) {
        authorizationPage().userLogin.fill(user.getLogin());
        authorizationPage().password.fill(user.getPassword());
        authorizationPage().login.click();
        mainMenuPage().table.visible();
    }

    @Step("Reset data after test")
    protected void resetDataAfterTest() {
        DeleteAll.putOnDeleting(() -> {
            mainMenuPage().menuBtn.click();
            mainMenuPage().sidebar.resetData();
        });
    }

    @Step("Login")
    protected void login() {
        login(standardUser);
    }

    @AfterMethod
    protected static void finishMethod() {
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
