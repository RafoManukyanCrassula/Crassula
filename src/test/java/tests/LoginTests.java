package tests;

import lib.CoreTestCase;
import lib.ui.DashboardPageObject;
import lib.ui.factories.LoginPageObjectFactory;
import lib.ui.factories.DashboardPageObjectFactory;
import lib.ui.LoginPageObject;
import org.junit.jupiter.api.Test;

public class LoginTests extends CoreTestCase {
    
    private static final String VALID_EMAIL = "client@crassula.io";
    private static final String VALID_PASSWORD = "Qwerty123";
    private static final String INVALID_EMAIL = "random@mail.ru";
    private static final String INVALID_PASSWORD = "random";
    private static final String INCOMPLETE_EMAIL = "random";
    
    @Test
    public void testLogin() {
        LoginPageObject loginPage = LoginPageObjectFactory.get(driver);

        loginPage.verifyLoginButtonExists();
        loginPage.verifyLoginButtonText("Log in");
        loginPage.clickLoginButton();
        loginPage.verifyLoginPageElements();
        loginPage.performLogin(VALID_EMAIL, VALID_PASSWORD);
        loginPage.createPasscode();

        DashboardPageObject dashboardPage = DashboardPageObjectFactory.get(driver);
        dashboardPage.waitForDashboardToLoad();
        dashboardPage.checkTransactionsTextPresent();
    }

    @Test
    public void testBlankFieldsValidation() {
        LoginPageObject loginPage = LoginPageObjectFactory.get(driver);

        loginPage.clickLoginButton();
        loginPage.clickEmailField();
        loginPage.clickPasswordField();
        loginPage.verifyValidationErrorMustBePresent("The value should not be blank");
        loginPage.clickEmailField();
        loginPage.verifyValidationErrorMustBePresent("The value should not be blank");
    }

    @Test
    public void testInvalidEmailValidation() {
        LoginPageObject loginPage = LoginPageObjectFactory.get(driver);

        loginPage.clickLoginButton();
        loginPage.enterInvalidEmail(INCOMPLETE_EMAIL);
        loginPage.verifyValidationErrorMustBePresent("Email address is not valid");
    }

    @Test
    public void testInvalidCredentialsAlert() {
        LoginPageObject loginPage = LoginPageObjectFactory.get(driver);

        loginPage.clickLoginButton();
        loginPage.enterEmail(INVALID_EMAIL);
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickContinueButton();
        loginPage.verifyWrongCredentialsAlert();
        loginPage.clickAlertOkButton();
    }
}