package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageObject extends MainPageObject
{
    protected static String
            SIGN_UP_BUTTON,
            LOGIN_BUTTON,
            LOGIN_TITLE,
            EMAIL_LABEL,
            PASSWORD_LABEL,
            FORGOT_PASSWORD_BUTTON,
            PASSWORD_TOGGLE_BUTTON,
            CONTINUE_BUTTON,
            USERNAME_FIELD,
            PASSWORD_FIELD,
            PASSCODE_TITLE,
            PASSCODE_BUTTON_1;

    public LoginPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void waitForLoginButton()
    {
        waitForElementPresent(
                LOGIN_BUTTON,
                "Cannot find Login button",
                10
        );
    }

    public String getLoginButtonText()
    {
        WebElement loginButton = waitForElementPresent(
                LOGIN_BUTTON,
                "Cannot find Login button",
                10
        );
        return loginButton.getText();
    }

    public void verifyLoginButtonExists()
    {
        waitForLoginButton();
    }

    public void verifyLoginButtonText(String expectedText)
    {
        String actualText = getLoginButtonText();
        assertEquals(
                expectedText,
                actualText,
                "Login button text does not match expected value"
        );
    }

    public void clickLoginButton()
    {
        waitForElementPresent(
                SIGN_UP_BUTTON,
                "Cannot find Sign up button",
                10
        );

        waitForElementAndClick(
                LOGIN_BUTTON,
                "Cannot find login button",
                10
        );
    }

    public void verifyLoginPageElements()
    {
        waitForElementPresent(LOGIN_TITLE, "Cannot find login title", 10);
        waitForElementPresent(EMAIL_LABEL, "Cannot find Email label", 10);
        waitForElementPresent(PASSWORD_LABEL, "Cannot find Password label", 10);
        waitForElementPresent(PASSWORD_TOGGLE_BUTTON, "Cannot find Password toggle button", 10);
        waitForElementPresent(FORGOT_PASSWORD_BUTTON, "Cannot find Forgot Password button", 10);
        waitForElementPresent(CONTINUE_BUTTON, "Cannot find Continue button", 10);
    }

    public void enterEmail(String email)
    {
        this.waitForElementAndClick(EMAIL_LABEL, "Cannot find and click email label", 5);

        try { Thread.sleep(2000); } catch (InterruptedException _) {}

        this.waitForElementAndSendKeys(USERNAME_FIELD, email, "Cannot find email input field", 5);
    }

    public void enterPassword(String password)
    {
        this.waitForElementAndClick(PASSWORD_LABEL, "Cannot find and click password label", 5);

        try { Thread.sleep(2000); } catch (InterruptedException _) {}

        this.waitForElementAndSendKeys(PASSWORD_FIELD, password, "Cannot find password input field", 5);
    }

    public void performLogin(String email, String password)
    {
        enterEmail(email);
        enterPassword(password);
        clickContinueButton();
    }

    public void clickContinueButton()
    {
        waitForElementAndClick(
                CONTINUE_BUTTON,
                "Cannot find Continue button",
                10
        );
    }

    public void createPasscode()
    {
        this.waitForElementPresent(PASSCODE_TITLE, "Cannot find passcode creation screen", 10);
        
        for (int i = 0; i < 4; i++) {
            this.waitForElementAndClick(PASSCODE_BUTTON_1, "Cannot find button '1'", 5);
        }
        
        try { Thread.sleep(2000); } catch (InterruptedException _) {}
        
        for (int i = 0; i < 4; i++) {
            this.waitForElementAndClick(PASSCODE_BUTTON_1, "Cannot find button '1'", 5);
        }
    }
}