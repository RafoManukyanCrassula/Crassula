package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.DashboardPageObject;
import lib.ui.LoginPageObject;
import lib.ui.factories.CurrencyExchangePageObjectFactory;
import lib.ui.factories.DashboardPageObjectFactory;
import lib.ui.factories.LoginPageObjectFactory;
import lib.ui.CurrencyExchangePageObject;
import lib.ui.TransactionDetailsPageObject;
import org.junit.jupiter.api.Test;

public class CurrencyExchangeTest extends CoreTestCase {
    
    private static final String TEST_EMAIL = "client@crassula.io";
    private static final String TEST_PASSWORD = "Qwerty123";
    
    @Test
    public void testCurrencyExchange() {
        performLoginAndNavigateToExchange();
    
        verifyAndConfigureExchangeFields();
    
        String accountName = fillExchangeForm();
    
        executeExchangeAndVerifyResult(accountName);
    }

    private void performLoginAndNavigateToExchange() {
        LoginPageObject loginPage = LoginPageObjectFactory.get(driver);
    
        loginPage.verifyLoginButtonExists();
        loginPage.clickLoginButton();
        loginPage.performLogin(TEST_EMAIL, TEST_PASSWORD);
        loginPage.createPasscode();
    
        DashboardPageObject dashboardPage = DashboardPageObjectFactory.get(driver);
        dashboardPage.waitForDashboardToLoad();
        dashboardPage.checkTransactionsTextPresent();
    }

    private void verifyAndConfigureExchangeFields() {
        CurrencyExchangePageObject exchangePage = CurrencyExchangePageObjectFactory.get(driver);

        exchangePage.verifyExchangeButtonExists();
        exchangePage.clickExchangeButton();
        exchangePage.verifyExchangePageTitle();
        exchangePage.verifyExchangeRateInfoExists();
        exchangePage.verifyFirstAmountFieldExists();
        exchangePage.verifyFirstCurrencyIconExists();

        if (Platform.getInstance().isAndroid()) {
            String fromCurrency = exchangePage.getFirstCurrencyButtonText();
            exchangePage.verifyFirstCurrencyButtonExists(fromCurrency);
            exchangePage.clickFirstCurrencyButton(fromCurrency);
            exchangePage.verifyCurrencySelectionElements();
            exchangePage.closeCurrencySelection();
        }

        exchangePage.verifyFirstFieldBalance();
        exchangePage.verifyMaxButtonExists();
        exchangePage.verifySecondAmountFieldExists();
        exchangePage.verifySecondCurrencyIconExists();

        String toCurrency = exchangePage.getSecondCurrencyButtonText();
        exchangePage.verifySecondCurrencyButtonExists(toCurrency);
        exchangePage.clickSecondCurrencyButton(toCurrency);
        exchangePage.verifySecondCurrencySelectionElements(toCurrency);
        exchangePage.closeSecondCurrencySelection();
        exchangePage.verifySecondFieldBalance();

        exchangePage.verifyFeeInfoExists();
        exchangePage.verifyAccountToLabelExists();
        exchangePage.verifyAccountSelectionField();
        exchangePage.verifyAccountName();
        exchangePage.verifyAccountAmount();
    }

    private String fillExchangeForm() {
        CurrencyExchangePageObject exchangePage = CurrencyExchangePageObjectFactory.get(driver);

        exchangePage.clickAccountSelection();
        exchangePage.verifyAccountSelectionModal();
        String accountName = exchangePage.getAccountName();
        exchangePage.selectAccount(accountName);
        exchangePage.verifyExchangeButtonInForm();
        exchangePage.verifyAccountButton();

        exchangePage.clickMaxButtonAndClearAmount();
        exchangePage.clickFirstAmountField();
        exchangePage.enterAmount("1");
        exchangePage.verifyMinusSymbolInFirstField();
        exchangePage.verifyPlusSymbolInSecondField();

        exchangePage.clickFeeInfo();
        exchangePage.verifyFeeDetailsModal("1");
        exchangePage.closeFeeDetailsModal();
        
        return accountName;
    }

    private void executeExchangeAndVerifyResult(String accountName) {
        CurrencyExchangePageObject exchangePage = CurrencyExchangePageObjectFactory.get(driver);

        exchangePage.clickExchangeButtonInForm();
        exchangePage.verifyConfirmExchangeTitle();
        String exchangeRate = exchangePage.getExchangeRate();
        
        exchangePage.verifyConfirmationDetails("1", exchangeRate);
        exchangePage.clickConfirmExchange();
        exchangePage.verifySuccessTitle();
        exchangePage.verifySuccessMessage();
        exchangePage.verifyExchangeAmount();
        exchangePage.verifyTransactionDetailsTitle();

        exchangePage.clickTransactionDetails();
        TransactionDetailsPageObject transactionDetailsPage = TransactionDetailsPageObject.get(driver);
        transactionDetailsPage.verifyTransactionDetailsContent();
        transactionDetailsPage.clickBackButton();

        exchangePage.clickBackToHome();
        DashboardPageObject dashboardPage = DashboardPageObjectFactory.get(driver);
        dashboardPage.verifyTransactionOnDashboard();
    }

    @Test
    public void testInsufficientFundsValidation() {
        LoginPageObject loginPage = LoginPageObjectFactory.get(driver);

        loginPage.verifyLoginButtonExists();
        loginPage.clickLoginButton();
        loginPage.performLogin(TEST_EMAIL, TEST_PASSWORD);
        loginPage.createPasscode();
        DashboardPageObject dashboardPage = DashboardPageObjectFactory.get(driver);
        dashboardPage.waitForDashboardToLoad();
        dashboardPage.checkTransactionsTextPresent();

        CurrencyExchangePageObject exchangePage = CurrencyExchangePageObjectFactory.get(driver);
        exchangePage.clickExchangeButton();

        double greaterAmount = getGreaterAmount(exchangePage);
        exchangePage.validateInsufficientFundsError(greaterAmount);

        System.out.println("Validation for insufficient funds passed.");
    }

    private static double getGreaterAmount(CurrencyExchangePageObject exchangePage) {
        String balanceText = exchangePage.getBalanceAmount();
        String numericBalanceText = balanceText
                .replace("Balance:", "")
                .replace("GBP", "")
                .trim();

        double currentBalance;
        try {
            currentBalance = Double.parseDouble(numericBalanceText);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Failed to parse balance: " + balanceText, e);
        }

        return currentBalance + 10.0;
    }

    @Test
    public void testUnavailableCurrencyPairValidation() {
        LoginPageObject loginPage = LoginPageObjectFactory.get(driver);

        loginPage.verifyLoginButtonExists();
        loginPage.clickLoginButton();
        loginPage.performLogin(TEST_EMAIL, TEST_PASSWORD);
        loginPage.createPasscode();
        DashboardPageObject dashboardPage = DashboardPageObjectFactory.get(driver);
        dashboardPage.waitForDashboardToLoad();
        dashboardPage.checkTransactionsTextPresent();

        CurrencyExchangePageObject exchangePage = CurrencyExchangePageObjectFactory.get(driver);
        exchangePage.clickExchangeButton();
        exchangePage.validateCurrencyPairUnavailableError();

        System.out.println("Validation for unavailable currency pair passed.");
    }
}