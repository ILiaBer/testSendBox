package utils;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class TestResultsBot extends TelegramLongPollingBot {

//    public void sendAllureReport(String chatId) {
//        String allureReportPath = "build/allure-results";
//        String zipFilePath ="build/reports/allure-report.zip";
//        try {
//            ZipUtil.zipDirectory(new File(allureReportPath), new File(zipFilePath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Отправляем ZIP-архив в Telegram
//        SendDocument sendDocument = new SendDocument();
//        sendDocument.setChatId(chatId);
//        sendDocument.setDocument(new InputFile(new File(zipFilePath)));
//
//        try {
//            execute(sendDocument);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }

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


    @SneakyThrows
    @AfterClass
    public void sendAllureReport(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        String message = getBriefInfo();
        message = new String(message.getBytes(), "UTF-8");
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
        List<String> list = new ArrayList();
        String failedTestsString = new String();

        if (!reportDirectory.exists() || !reportDirectory.isDirectory()) {
            return "Отчет Allure не найден.";
        }
        int passedTests = 0;
        int failedTests = 0;

        File[] testResultFiles = reportDirectory.listFiles((dir, name) -> name.endsWith("-result.json"));
        if (testResultFiles != null) {
            for (File resultFile : testResultFiles) {
                try {
                    String jsonContent = Files.readString(resultFile.toPath());
                    TestResult testResult = new Gson().fromJson(jsonContent, TestResult.class);
                    if (testResult.isTestPassed().equals("passed")) {
                        passedTests++;
                    } else {
                        failedTests++;
                        list.add(testResult.fullName());
                        failedTestsString = "Упавшие тесты:\n";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Set<String> uniqueSet = new HashSet<>(list);
        List<String> resultList = new ArrayList<>(uniqueSet);
        for (int i = 0; i < resultList.size(); i++) {
            resultList.set(i, resultList.get(i));
        }
        String str = failedTestsString + String.join("\n", resultList);


        double rate = Math.round(((double) passedTests / (passedTests + failedTests) * 100) * 10) / 10.0;
        return "Passed tests : " + passedTests + "\nFailed tests: " + failedTests + "\nTest Pass Rate - " + rate + "%" +
                "\n" + str;
    }
}