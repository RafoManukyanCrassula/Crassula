package tests;

import lib.CoreTestCase;
import lib.ui.DashboardPageObject;
import lib.ui.factories.LoginPageObjectFactory;
import lib.ui.factories.DashboardPageObjectFactory;
import lib.ui.LoginPageObject;
import org.junit.jupiter.api.Test;

public class LoginTests extends CoreTestCase
{
    @Test
    public void testLogin()
    {
        LoginPageObject loginPage = LoginPageObjectFactory.get(driver);
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
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
}