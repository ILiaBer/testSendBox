package data.models;

import lombok.Data;

import java.io.File;
import java.util.List;

@Data
public class CustomTestsResults {

    int passedTests;
    int failedTests;
    int skippedTest;

}
