package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import lib.Platform;
import lib.ui.factories.LoginPageObjectFactory;
import lib.ui.LoginPageObject;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import lib.ui.factories.DashboardPageObjectFactory;
import io.cucumber.java.en.And;

public class LoginSteps {
    private LoginPageObject loginPage;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() throws Exception {
        Platform.getInstance();
        loginPage = LoginPageObjectFactory.get(Platform.getDriver());
        loginPage.verifyLoginButtonExists();
    }

    @When("the user enters valid credentials")
    public void theUserEntersValidCredentials() {
        loginPage.clickLoginButton();
        loginPage.performLogin("client@crassula.io", "Qwerty123");
    }
    
    @When("the user enters an invalid email")
    public void theUserEntersAnInvalidEmail() {
        loginPage.clickLoginButton();
        loginPage.enterInvalidEmail("invalid_email");
    }

    @When("the user clicks on the login button")
    public void theUserClicksOnTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @When("leaves email and password empty")
    public void leavesEmailAndPasswordEmpty() {
        loginPage.clickEmailField();
        loginPage.clickPasswordField();
    }

    @When("the user enters invalid email and password")
    public void theUserEntersInvalidEmailAndPassword() {
        loginPage.clickLoginButton();
        loginPage.enterEmail("random@mail.ru");
        loginPage.enterPassword("random");
    }

    @When("clicks the login button")
    public void clicksTheLoginButton() {
        loginPage.clickContinueButton();
    }

    @Then("the user is redirected to the dashboard")
    public void theUserIsRedirectedToTheDashboard() throws Exception {
        Platform.getInstance();
        Assertions.assertNotNull(
                DashboardPageObjectFactory.get(Platform.getDriver()),
            "Dashboard not loaded"
        );
    }

    @Then("the error message {string} is displayed")
    public void theErrorMessageIsDisplayed(String expectedError) {
        loginPage.verifyValidationErrorMustBePresent(expectedError);
    }

    @Then("the alert {string} is displayed")
    public void theAlertIsDisplayed(String expectedAlertMessage) {
        assertEquals(
            expectedAlertMessage,
            loginPage.getAlertMessage(),
            "Alert message does not match"
        );
    }

    @Then("the user closes the alert")
    public void theUserClosesTheAlert() throws Exception {
        if (Platform.getInstance().isAndroid()) {
            loginPage.clickAlertOkButton();
        }
    }

    @And("clicks on the login button")
    public void clicksOnTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @And("clicks on the continue button")
    public void clicksOnTheContinueButton() {
        loginPage.clickContinueButton();
    }
}