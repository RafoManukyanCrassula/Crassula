package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.DashboardPageObject;

public class iOSDashboardPageObject extends DashboardPageObject {
    static {
        TRANSACTIONS_TEXT = "id:Transactions";
    }

    public iOSDashboardPageObject(AppiumDriver driver) {
        super(driver);
    }
}