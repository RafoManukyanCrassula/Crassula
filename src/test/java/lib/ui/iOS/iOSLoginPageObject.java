package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.LoginPageObject;

public class iOSLoginPageObject extends LoginPageObject {
    static {
        SIGN_UP_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Sign up']";
        LOGIN_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Log in']";
        LOGIN_TITLE = "id:Log in to Crassula";
        EMAIL_LABEL = "id:Email";
        PASSWORD_LABEL = "id:Password";
        USERNAME_FIELD = "xpath://XCUIElementTypeStaticText[@name='Email']";
        PASSWORD_FIELD = "xpath://XCUIElementTypeStaticText[@name='Password']";
        CONTINUE_BUTTON = "xpath://XCUIElementTypeButton[@name='Continue']";
        FORGOT_PASSWORD_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Forgot Password?']";
        PASSWORD_TOGGLE_BUTTON = "id:ic visibility off";
        PASSCODE_TITLE = "id:Create a passcode for your account";
        PASSCODE_BUTTON_1 = "xpath://XCUIElementTypeButton[@name='1']";
        VALIDATION_ERROR = "xpath://XCUIElementTypeStaticText[contains(@name,'not be blank') or contains(@name,'not valid')]";
        ALERT_TITLE = "xpath://XCUIElementTypeStaticText[@name='Wrong credentials']";
        ALERT_MESSAGE = "id:The email or password you entered is incorrect";
        ALERT_OK_BUTTON = "id:OK";
    }

    public iOSLoginPageObject(AppiumDriver driver) {
        super(driver);
    }
}