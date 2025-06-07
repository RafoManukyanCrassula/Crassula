package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.LoginPageObject;

public class AndroidLoginPageObject extends LoginPageObject
{
    static {
        LOGIN_BUTTON = "id:com.crassula.demo:id/buttonLogin";
        LOGIN_TITLE = "id:com.crassula.demo:id/labelTitle";
        EMAIL_LABEL = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/textTitle' and @text='Email']";
        PASSWORD_LABEL = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/textTitle' and @text='Password']";
        FORGOT_PASSWORD_BUTTON = "id:com.crassula.demo:id/buttonForgotPassword";
        PASSWORD_TOGGLE_BUTTON = "id:com.crassula.demo:id/buttonPassword";
        CONTINUE_BUTTON = "id:com.crassula.demo:id/buttonSignIn";
        USERNAME_FIELD = "xpath:(//android.view.ViewGroup[@resource-id='com.crassula.demo:id/mainLayout'])[1]//android.widget.EditText";
        PASSWORD_FIELD = "xpath:(//android.view.ViewGroup[@resource-id='com.crassula.demo:id/mainLayout'])[2]//android.widget.EditText";
        PASSCODE_TITLE = "xpath://android.widget.TextView[contains(@text, 'Create a passcode') and contains(@text, 'for your account')]";
        PASSCODE_BUTTON_1 = "xpath://android.widget.Button[@text='1']";
    }

    public AndroidLoginPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}