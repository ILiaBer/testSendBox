package steps;

public class WebTablesSteps {

    public WebTablesSteps addNewMember(){
        removeMember();
//fill
        return this;
    }

    public WebTablesSteps removeMember() {
        DeleteAll.putOnDeleting(() -> {
//            baseRouter.mainPage().firmware.click();
//            deleteByDescription(firmware);
        });
        return this;
    }
}
