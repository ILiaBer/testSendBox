package steps;

import utils.BaseTest;

import java.util.List;

public class DeleteAll {

    public static void putOnDeleting(Runnable block) {
        List<Runnable> listForTransfer = BaseTest.finishMap.get(BaseTest.testResult.get());
        listForTransfer.add(block);
    }
}
