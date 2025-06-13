package tests;

import lib.CoreTestCase;
import lib.ui.DashboardPageObject;
import lib.ui.factories.LoginPageObjectFactory;
import lib.ui.factories.DashboardPageObjectFactory;
import lib.ui.LoginPageObject;
import org.junit.jupiter.api.Test;

public class LoginTests extends CoreTestCase {
    @Test
    public void testLogin() {
        LoginPageObject loginPage = LoginPageObjectFactory.get(driver);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException
                e) {
            e.printStackTrace();
        }

        loginPage.verifyLoginButtonExists();
        loginPage.verifyLoginButtonText("Log in");
        loginPage.clickLoginButton();
        loginPage.verifyLoginPageElements();
        loginPage.performLogin("client@crassula.io", "Qwerty123");
        loginPage.createPasscode();

        DashboardPageObject dashboardPage = DashboardPageObjectFactory.get(driver);
        dashboardPage.waitForDashboardToLoad();
        dashboardPage.checkTransactionsTextPresent();
    }

    @Test
    public void testBlankFieldsValidation() {
        LoginPageObject loginPage = LoginPageObjectFactory.get(driver);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        loginPage.clickLoginButton();
        loginPage.clickEmailField();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException _) {
        }
        loginPage.clickPasswordField();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException _) {
        }
        loginPage.verifyValidationErrorMustBePresent("The value should not be blank");
        loginPage.clickEmailField();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException _) {
        }
        loginPage.verifyValidationErrorMustBePresent("The value should not be blank");
    }

    @Test
    public void testInvalidEmailValidation() {
        LoginPageObject loginPage = LoginPageObjectFactory.get(driver);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        loginPage.clickLoginButton();
        String longEmail = "random";
        loginPage.enterInvalidEmail(longEmail);
        loginPage.verifyValidationErrorMustBePresent("Email address is not valid");
    }

    @Test
    public void testInvalidCredentialsAlert() {
        LoginPageObject loginPage = LoginPageObjectFactory.get(driver);

        loginPage.clickLoginButton();
        loginPage.enterEmail("random@mail.ru");
        loginPage.enterPassword("random");
        loginPage.clickContinueButton();
        loginPage.verifyWrongCredentialsAlert();
        loginPage.clickAlertOkButton();
    }
}