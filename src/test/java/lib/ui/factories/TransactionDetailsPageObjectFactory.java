package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.ui.TransactionDetailsPageObject;
import lib.ui.Android.AndroidTransactionDetailsPageObject;
import lib.ui.iOS.iOSTransactionDetailsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TransactionDetailsPageObjectFactory
{
    public static TransactionDetailsPageObject get(RemoteWebDriver driver)
    {
        if(driver.getClass().toString().contains("AndroidDriver")) {
            return new AndroidTransactionDetailsPageObject((AppiumDriver) driver);
        } else {
            return new iOSTransactionDetailsPageObject((AppiumDriver) driver);
        }
    }
}