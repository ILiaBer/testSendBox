package data.models;

import lombok.Data;

@Data
public class CustomTestsResults {

    int passedTests;
    int failedTests;
    int skippedTest;

}
