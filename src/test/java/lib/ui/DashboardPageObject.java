package lib.ui;

import io.appium.java_client.AppiumDriver;

import static lib.ui.CurrencyExchangePageObject.DASHBOARD_TRANSACTION;

public class DashboardPageObject extends MainPageObject
{
    protected static String
        TRANSACTIONS_TEXT;

    public DashboardPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void waitForDashboardToLoad()
    {
        this.waitForElementPresent(TRANSACTIONS_TEXT, "Dashboard did not load - Transactions text not found", 10);
    }

    public void checkTransactionsTextPresent()
    {
        this.waitForElementPresent(TRANSACTIONS_TEXT, "Transactions text is not present on dashboard", 5);
    }

// Добавить этот метод в существующий класс DashboardPageObject

    public void verifyTransactionOnDashboard()
    {
        this.waitForElementPresent(
                DASHBOARD_TRANSACTION,
                "Транзакция не найдена на дашборде",
                10
        );
    }
}