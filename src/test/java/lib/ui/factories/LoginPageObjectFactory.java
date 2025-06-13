package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidLoginPageObject;
import lib.ui.LoginPageObject;
import lib.ui.iOS.iOSLoginPageObject;

public class LoginPageObjectFactory {
    public static LoginPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidLoginPageObject(driver);
        } else {
            return new iOSLoginPageObject(driver);
        }
    }
}