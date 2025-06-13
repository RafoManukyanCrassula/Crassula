package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ValidationHelperObject;

public class AndroidValidationHelperObject extends ValidationHelperObject
{
    static {
        INSUFFICIENT_FUNDS_ERROR = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelBalance' and @text='Account has insufficient funds.']";
        VALIDATION_ERROR_CONTAINER = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelBalance']";
        GENERAL_ERROR_MESSAGE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelBalance' and @text='{ERROR_TEXT}']";
    }

    public AndroidValidationHelperObject(AppiumDriver driver)
    {
        super(driver);
    }
}