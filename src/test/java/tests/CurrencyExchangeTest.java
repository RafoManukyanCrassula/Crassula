package tests;

import lib.CoreTestCase;
import lib.ui.DashboardPageObject;
import lib.ui.LoginPageObject;
import lib.ui.factories.CurrencyExchangePageObjectFactory;
import lib.ui.factories.DashboardPageObjectFactory;
import lib.ui.factories.LoginPageObjectFactory;
import lib.ui.CurrencyExchangePageObject;
import lib.ui.TransactionDetailsPageObject;
import org.junit.jupiter.api.Test;

public class CurrencyExchangeTest extends CoreTestCase
{
    @Test
    public void testCurrencyExchange()
    {
        LoginPageObject loginPage = LoginPageObjectFactory.get(driver);
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        loginPage.verifyLoginButtonExists();
        loginPage.clickLoginButton();
        loginPage.performLogin("client@crassula.io", "Qwerty123");
        loginPage.createPasscode();
        DashboardPageObject dashboardPage = DashboardPageObjectFactory.get(driver);
        dashboardPage.waitForDashboardToLoad();
        dashboardPage.checkTransactionsTextPresent();

        CurrencyExchangePageObject exchangePage = CurrencyExchangePageObjectFactory.get(driver);
        exchangePage.verifyExchangeButtonExists();
        exchangePage.clickExchangeButton();
        exchangePage.verifyExchangePageTitle();
        exchangePage.verifyExchangeRateInfoExists();
        exchangePage.verifyFirstAmountFieldExists();
        exchangePage.verifyFirstCurrencyIconExists();
        String fromCurrency = exchangePage.getFirstCurrencyButtonText();
        exchangePage.verifyFirstCurrencyButtonExists(fromCurrency);
        exchangePage.clickFirstCurrencyButton(fromCurrency);
        exchangePage.verifyCurrencySelectionElements();
        exchangePage.closeCurrencySelection();
        exchangePage.verifyFirstFieldBalance();
        exchangePage.verifyMaxButtonExists();
        exchangePage.clickMaxButtonAndClearAmount();
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
        exchangePage.clickAccountSelection();
        exchangePage.verifyAccountSelectionModal();
        String accountName = exchangePage.getAccountName();
        String accountNumber = exchangePage.getAccountNumberFromModal();
        exchangePage.selectAccount(accountName);
        exchangePage.verifyExchangeButtonInForm();
        exchangePage.verifyAccountButton();
        exchangePage.clickFirstAmountField();
        exchangePage.enterAmount("1");
        exchangePage.verifyMinusSymbolInFirstField();
        exchangePage.verifyPlusSymbolInSecondField();
        exchangePage.clickFeeInfo();
        exchangePage.verifyFeeDetailsModal("1");
        exchangePage.closeFeeDetailsModal();
        String exchangeRate = exchangePage.getExchangeRate();
        exchangePage.clickExchangeButtonInForm();
        exchangePage.verifyConfirmExchangeTitle();
        exchangeRate = exchangePage.getExchangeRate();
        exchangePage.verifyConfirmationDetails("1", accountNumber, exchangeRate);
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
        dashboardPage.verifyTransactionOnDashboard();
    }
}