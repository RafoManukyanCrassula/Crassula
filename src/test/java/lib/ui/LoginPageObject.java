package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageObject extends MainPageObject {
    protected static String
            LOGIN_BUTTON,
            SIGN_UP_BUTTON,
            LOGIN_TITLE,
            EMAIL_LABEL,
            PASSWORD_LABEL,
            FORGOT_PASSWORD_BUTTON,
            PASSWORD_TOGGLE_BUTTON,
            CONTINUE_BUTTON,
            USERNAME_FIELD,
            PASSWORD_FIELD,
            PASSCODE_TITLE,
            PASSCODE_BUTTON_1,
            VALIDATION_ERROR,
            ALERT_TITLE,
            ALERT_MESSAGE,
            ALERT_OK_BUTTON;

    public LoginPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void verifyLoginButtonExists() {
        waitForElementPresent(
                LOGIN_BUTTON,
                "Cannot find Login button",
                10
        );
    }

    public String getLoginButtonText() {
        WebElement loginButton = waitForElementPresent(
                LOGIN_BUTTON,
                "Cannot find Login button",
                10
        );
        return loginButton.getText();
    }

    public void verifyLoginButtonText(String expectedText) {
        String actualText = getLoginButtonText();
        assertEquals(
                expectedText,
                actualText,
                "Login button text does not match expected value"
        );
    }

    public void clickLoginButton() {
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

    public void verifyLoginPageElements() {
        waitForElementPresent(LOGIN_TITLE, "Cannot find login title", 10);
        waitForElementPresent(EMAIL_LABEL, "Cannot find Email label", 10);
        waitForElementPresent(PASSWORD_LABEL, "Cannot find Password label", 10);
        waitForElementPresent(PASSWORD_TOGGLE_BUTTON, "Cannot find Password toggle button", 10);
        waitForElementPresent(FORGOT_PASSWORD_BUTTON, "Cannot find Forgot Password button", 10);
        waitForElementPresent(CONTINUE_BUTTON, "Cannot find Continue button", 10);
    }

    public void enterEmail(String email) {
        this.waitForElementAndClick(EMAIL_LABEL, "Cannot find and click email label", 5);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException _) {
        }
        this.waitForElementAndSendKeys(USERNAME_FIELD, email, "Cannot find email input field", 5);
    }

    public void enterPassword(String password) {
        this.waitForElementAndClick(PASSWORD_LABEL, "Cannot find and click password label", 5);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException _) {
        }
        this.waitForElementAndSendKeys(PASSWORD_FIELD, password, "Cannot find password input field", 5);
    }

    public void performLogin(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickContinueButton();
    }

    public void clickContinueButton() {
        waitForElementAndClick(
                CONTINUE_BUTTON,
                "Cannot find Continue button",
                10
        );
    }

    public void createPasscode() {
        this.waitForElementPresent(PASSCODE_TITLE, "Cannot find passcode creation screen", 10);

        for (int i = 0; i < 4; i++) {
            this.waitForElementAndClick(PASSCODE_BUTTON_1, "Cannot find button '1'", 5);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException _) {
        }

        for (int i = 0; i < 4; i++) {
            this.waitForElementAndClick(PASSCODE_BUTTON_1, "Cannot find button '1'", 5);
        }
    }

    public void clickEmailField() {
        waitForElementAndClick(
                EMAIL_LABEL,
                "Cannot find email label",
                10
        );
    }

    public void clickPasswordField() {
        waitForElementAndClick(
                PASSWORD_LABEL,
                "Cannot find password label",
                10
        );
    }

    public boolean isValidationErrorPresent() {
        try {
            waitForElementPresent(VALIDATION_ERROR, "Validation error not found", 3);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getValidationErrorText() {
        WebElement errorElement = waitForElementPresent(
                VALIDATION_ERROR,
                "Cannot find validation error message",
                10
        );
        return errorElement.getText();
    }

    public void verifyValidationError(String expectedError) {
        String actualError = getValidationErrorText();
        assertEquals(
                expectedError,
                actualError,
                "Validation error text does not match expected value"
        );
    }

    public void enterInvalidEmail(String invalidEmail) {
        this.waitForElementAndClick(EMAIL_LABEL, "Cannot find email label", 5);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException _) {
        }
        this.waitForElementAndSendKeys(USERNAME_FIELD, invalidEmail, "Cannot find email input field", 5);
        clickPasswordField();
    }

    public String getAlertTitle() {
        WebElement alertTitle = waitForElementPresent(
                ALERT_TITLE,
                "Cannot find alert title",
                10
        );
        return alertTitle.getText();
    }

    public String getAlertMessage() {
        WebElement alertMessage = waitForElementPresent(
                ALERT_MESSAGE,
                "Cannot find alert message",
                10
        );
        return alertMessage.getText();
    }

    public void verifyWrongCredentialsAlert() {
        String actualTitle = getAlertTitle();
        assertEquals(
                "Wrong credentials",
                actualTitle,
                "Alert title does not match expected value"
        );

        String actualMessage = getAlertMessage();
        assertEquals(
                "The email or password you entered is incorrect",
                actualMessage,
                "Alert message does not match expected value"
        );
    }

    public void clickAlertOkButton() {
        waitForElementAndClick(
                ALERT_OK_BUTTON,
                "Cannot find OK button in alert",
                10
        );
    }

    public void verifyValidationErrorMustBePresent(String expectedError) {
        if (!isValidationErrorPresent()) {
            throw new AssertionError("Validation error MUST be present but was not found! Expected: " + expectedError);
        }
        verifyValidationError(expectedError);
    }
}