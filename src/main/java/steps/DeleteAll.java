package steps;

import utils.TestBase;

import java.util.List;

public class DeleteAll {

    public static void putOnDeleting(Runnable block) {
        List<Runnable> listForTransfer = TestBase.finishMap.get(TestBase.testResult.get());
        listForTransfer.add(block);
    }
}
