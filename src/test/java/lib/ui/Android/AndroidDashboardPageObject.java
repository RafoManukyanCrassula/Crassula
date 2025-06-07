package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.DashboardPageObject;

public class AndroidDashboardPageObject extends DashboardPageObject {

    static {
        TRANSACTIONS_TEXT = "xpath://android.widget.TextView[@text='Transactions']";
    }

    public AndroidDashboardPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
