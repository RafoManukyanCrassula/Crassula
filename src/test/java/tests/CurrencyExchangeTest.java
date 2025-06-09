package tests;

import lib.CoreTestCase;
import lib.ui.DashboardPageObject;
import lib.ui.LoginPageObject;
import lib.ui.factories.CurrencyExchangePageObjectFactory;
import lib.ui.factories.DashboardPageObjectFactory;
import lib.ui.factories.LoginPageObjectFactory;
import lib.ui.CurrencyExchangePageObject;
import org.junit.jupiter.api.Test;

public class CurrencyExchangeTest extends CoreTestCase
{
    @Test
    public void testCurrencyExchange()
    {
        // 1. Выполняем логин
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

        // 2. Проверяем успешный логин и переход на дашборд
        DashboardPageObject dashboardPage = DashboardPageObjectFactory.get(driver);
        dashboardPage.waitForDashboardToLoad();
        dashboardPage.checkTransactionsTextPresent();

        // 3. Переходим к обмену валют
        CurrencyExchangePageObject exchangePage = CurrencyExchangePageObjectFactory.get(driver);
        
        // Проверяем наличие кнопки Exchange и кликаем на неё
        exchangePage.verifyExchangeButtonExists();
        exchangePage.clickExchangeButton();
        
        // 4. Проверяем элементы страницы обмена
        exchangePage.verifyExchangePageTitle();
        exchangePage.verifyExchangeRateInfoExists();
        
        // 5. Проверяем первое поле (отправитель)
        exchangePage.verifyFirstAmountFieldExists();
        exchangePage.verifyFirstCurrencyIconExists();
        String fromCurrency = exchangePage.getFirstCurrencyButtonText();
        exchangePage.verifyFirstCurrencyButtonExists(fromCurrency);
        
        // Открываем выбор валют для первого поля
        exchangePage.clickFirstCurrencyButton(fromCurrency);
        exchangePage.verifyCurrencySelectionElements();
        exchangePage.closeCurrencySelection();
        
        // Проверяем баланс первого поля
        exchangePage.verifyFirstFieldBalance();
        exchangePage.verifyMaxButtonExists();
        
        // 6. Проверяем второе поле (получатель)
        exchangePage.verifySecondAmountFieldExists();
        exchangePage.verifySecondCurrencyIconExists();
        String toCurrency = exchangePage.getSecondCurrencyButtonText();
        exchangePage.verifySecondCurrencyButtonExists(toCurrency);
        
        // Открываем выбор валют для второго поля
        exchangePage.clickSecondCurrencyButton(toCurrency);
        exchangePage.verifySecondCurrencySelectionElements(toCurrency);
        exchangePage.closeSecondCurrencySelection();
        
        // Проверяем баланс второго поля
        exchangePage.verifySecondFieldBalance();
        
        // 7-11. Проверяем информацию о комиссии и аккаунте
        exchangePage.verifyFeeInfoExists();
        exchangePage.verifyAccountToLabelExists();
        exchangePage.verifyAccountSelectionField();
        exchangePage.verifyAccountName();
        exchangePage.verifyAccountAmount();
        
        // 12-16. Работаем с выбором аккаунта
        exchangePage.clickAccountSelection();
        exchangePage.verifyAccountSelectionModal();
        String accountName = exchangePage.getAccountName();
        exchangePage.selectAccount(accountName);
        
        // 17-18. Проверяем дополнительные кнопки
        exchangePage.verifyExchangeButtonInForm();
        exchangePage.verifyAccountButton();
        
        // 19-22. Вводим сумму и проверяем символы
        exchangePage.clickFirstAmountField();
        exchangePage.enterAmount("1");
        exchangePage.verifyMinusSymbolInFirstField();
        exchangePage.verifyPlusSymbolInSecondField();
        
        // 23-25. Проверяем детали комиссии
        exchangePage.clickFeeInfo();
        exchangePage.verifyFeeDetailsModal();
        exchangePage.closeFeeDetailsModal();
        
        // 26. Переходим к подтверждению
        exchangePage.clickExchangeButtonInForm();
        
        // 27. Проверяем страницу подтверждения
        exchangePage.verifyConfirmExchangeTitle();
        exchangePage.verifyConfirmationDetails();
        
        // 28. Подтверждаем обмен
        exchangePage.clickConfirmExchange();
        
        // 29-32. Проверяем страницу успеха
        exchangePage.verifySuccessTitle();
        exchangePage.verifySuccessMessage();
        exchangePage.verifyExchangeAmount();
        exchangePage.verifyTransactionDetailsTitle();
        
        // 33. Проверяем детали транзакции
        exchangePage.clickTransactionDetails();
        exchangePage.verifyTransactionDetailsContent();
        
        // 34-35. Возвращаемся на дашборд
        exchangePage.clickBackButton();
        exchangePage.clickBackToHome();
        
        // 36. Проверяем транзакцию на дашборде
        dashboardPage.verifyTransactionOnDashboard();
    }
}