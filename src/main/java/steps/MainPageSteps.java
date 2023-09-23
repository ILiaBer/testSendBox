package steps;

import utils.BaseTest;

public class MainPageSteps extends BaseTest {

    public MainPageSteps logout() {
        baseRouter
                .mainMenuPage().menuBtn.click()
                .mainMenuPage().sidebar.logout();
        return this;
    }
}
