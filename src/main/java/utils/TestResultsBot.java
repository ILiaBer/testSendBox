package utils;

import com.google.gson.Gson;
import data.models.CustomTestsResults;
import data.models.DataForCustomResults;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

import static data.models.DataForCustomResults.generateDefaultDataForCustomResults;

public class TestResultsBot extends TelegramLongPollingBot {

    @SneakyThrows
    @AfterClass
    public void sendAllureReport(String chatId, String allureLink) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        String message = getBriefInfo() + "\n\n[Allure report link](" + allureLink + ")";
        System.out.println(message);
        message = new String(message.getBytes(), StandardCharsets.UTF_8);
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @AfterClass
    public void sendAllureReport(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        String message = getBriefInfo();
        System.out.println(message);
        message = new String(message.getBytes(), StandardCharsets.UTF_8);
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    private String getBriefInfo() {
        File reportDirectory = new File("build/allure-results");

        if (!reportDirectory.exists() || !reportDirectory.isDirectory()) {
            return "Отчет Allure не найден.";
        }
        DataForCustomResults dataForResults = generateDefaultDataForCustomResults();
        CustomTestsResults myTestsResults = getResults(dataForResults, reportDirectory);
        return generateMessage(dataForResults, myTestsResults);
    }

    private static String generateMessage(DataForCustomResults dataForResults, CustomTestsResults myTestsResults) {
        String stringOfSkippedTests = dataForResults.getInParticularSkipped() +
                generateStringWithoutDuplicates(dataForResults.getListOfSkippedTest());
        String stringOfFailedTests = dataForResults.getInParticularFailed()
                + generateStringWithoutDuplicates(dataForResults.getListOfFailedTest());
        double rate = Math.round(((double) myTestsResults.getPassedTests() / (myTestsResults.getPassedTests() +
                myTestsResults.getFailedTests()) * 100) * 10) / 10.0;

        if (myTestsResults.getSkippedTest() != 0 && myTestsResults.getPassedTests() == 0) {
            return "An error occurred while running the tests. Check the Stack Trace";
        } else {
            return "Test Pass Rate - " + rate + "%\nPassed tests : " + myTestsResults.getPassedTests() +
                    "\nFailed tests: " + myTestsResults.getFailedTests() + stringOfFailedTests + stringOfSkippedTests;
        }
    }

    private static CustomTestsResults getResults(DataForCustomResults data, File reportDirectory) {
        CustomTestsResults customTestsResults = new CustomTestsResults();
        int passedTests = 0;
        int failedTests = 0;
        int skippedTest = 0;
        File[] testResultFiles = reportDirectory.listFiles((dir, name) -> name.endsWith("-result.json"));
        for (File resultFile : Objects.requireNonNull(testResultFiles)) {
            try {
                String jsonContent = Files.readString(resultFile.toPath());
                TestResult testResult = new Gson().fromJson(jsonContent, TestResult.class);
                if (testResult.isTestPassed().equals("passed")) {
                    passedTests++;
                }
                if (testResult.isTestPassed().equals("skipped")) {
                    data.getListOfSkippedTest().add(testResult.fullName());
                    skippedTest++;
                    String skippedTestLabel = "\nSkipped tests: " + skippedTest;
                    data.setInParticularSkipped(skippedTestLabel + "\nIn particular:\n");
                }
                if (testResult.isTestPassed().equals("failed")) {
                    failedTests++;
                    data.getListOfFailedTest().add(testResult.fullName());
                    data.setInParticularFailed("\nIn particular:\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        customTestsResults.setPassedTests(passedTests);
        customTestsResults.setFailedTests(failedTests);
        customTestsResults.setSkippedTest(skippedTest);
        return customTestsResults;
    }


    private static String generateStringWithoutDuplicates(List<String> list) {
        Set<String> uniqueSet = new HashSet<>(list);
        List<String> resultList = new ArrayList<>(uniqueSet);
        for (int i = 0; i < resultList.size(); i++) {
            resultList.set(i, list.get(i));
        }
        return String.join("\n", resultList);
    }

    @Override
    public void onUpdateReceived(Update update) {
    }

    @Override
    public String getBotUsername() {
        return "ililozTestResults_bot";
    }

    @Override
    public String getBotToken() {
        return "6497299015:AAGJROT1rYBk1WqymLmCFcU4ix9YVRlOj1E";
    }
}