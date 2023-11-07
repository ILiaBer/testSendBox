package utils;

import com.google.gson.annotations.SerializedName;

public class TestResult {

    @SerializedName("status")
    private String testPassed;

    public String isTestPassed() {
        return testPassed;
    }

    @SerializedName("fullName")
    private String fullName;

    public String fullName() {
        return fullName;
    }
}
