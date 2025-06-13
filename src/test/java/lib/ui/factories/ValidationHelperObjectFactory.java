package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidValidationHelperObject;
import lib.ui.ValidationHelperObject;
import lib.ui.iOS.iOSValidationHelperObject;

public class ValidationHelperObjectFactory
{
    public static ValidationHelperObject get(AppiumDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidValidationHelperObject(driver);
        } else {
            return new iOSValidationHelperObject(driver);
        }
    }
}