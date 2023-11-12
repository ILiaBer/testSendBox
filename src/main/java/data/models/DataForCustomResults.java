package data.models;

import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class DataForCustomResults {
    List<String> listOfFailedTest;
    List<String> listOfSkippedTest;
    String inParticularFailed;
    String inParticularSkipped;
    File reportDirectory;

    public static DataForCustomResults generateDefaultDataForCustomResults() {
        return DataForCustomResults.builder()
                .listOfFailedTest(new ArrayList())
                .listOfSkippedTest(new ArrayList())
                .inParticularFailed(new String())
                .inParticularSkipped(new String())
                .build();
    }
}
