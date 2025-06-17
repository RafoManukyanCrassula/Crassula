package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidCurrencyExchangePageObject;
import lib.ui.CurrencyExchangePageObject;
import lib.ui.iOS.iOSCurrencyExchangePageObject;

public class CurrencyExchangePageObjectFactory {

    public static CurrencyExchangePageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidCurrencyExchangePageObject(driver);
        } else {
            return new iOSCurrencyExchangePageObject(driver);
        }
    }
}