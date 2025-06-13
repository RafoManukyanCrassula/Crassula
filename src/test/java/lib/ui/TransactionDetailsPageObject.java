package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.factories.TransactionDetailsPageObjectFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Arrays;
import java.util.List;

abstract public class TransactionDetailsPageObject extends MainPageObject {
    protected static String
            TRANSACTION_AMOUNT,
            TRANSACTION_ICON,
            TRANSACTION_PAYMENT,
            TRANSACTION_DATE,
            TRANSACTION_TYPE_TITLE,
            TRANSACTION_TYPE_VALUE,
            TRANSACTION_STATUS_TITLE,
            TRANSACTION_STATUS_VALUE,
            TRANSACTION_DESCRIPTION_TITLE,
            TRANSACTION_CREATED_TITLE,
            TRANSACTION_PAYMENT_TO_TITLE,
            TRANSACTION_ACCOUNT_NUMBER_TITLE,
            TRANSACTION_ID_TITLE,
            BACK_BUTTON;

    public TransactionDetailsPageObject(RemoteWebDriver driver) {
        super((AppiumDriver) driver);
    }

    public void verifyTransactionDetailsContent() {
        this.waitForElementPresent(TRANSACTION_AMOUNT, "Transaction amount not found", 10);
        this.waitForElementPresent(TRANSACTION_ICON, "Transaction icon not found", 10);
        this.waitForElementPresent(TRANSACTION_PAYMENT, "Transaction payment info not found", 10);
        this.waitForElementPresent(TRANSACTION_DATE, "Transaction date not found", 10);
        this.waitForElementPresent(TRANSACTION_TYPE_TITLE, "Type title not found", 10);
        this.waitForElementPresent(TRANSACTION_TYPE_VALUE, "Exchange value not found", 10);
        this.waitForElementPresent(TRANSACTION_STATUS_TITLE, "Status title not found", 10);
        this.waitForElementPresent(
                TRANSACTION_STATUS_VALUE,
                "Transaction status not valid or not found",
                10
        );
        String status = this.waitForElementAndGetAttribute(
                TRANSACTION_STATUS_VALUE,
                "text",
                "Can't get text of transaction status",
                10
        );
        List<String> validStatuses = Arrays.asList("Pending", "Draft", "Processing", "Rejected", "Cancelled", "Failed", "Completed");
        if (!validStatuses.contains(status)) {
            throw new AssertionError("Transaction status is not valid. Found: " + status + ". Valid statuses: " + validStatuses);
        }
        this.waitForElementPresent(TRANSACTION_DESCRIPTION_TITLE, "Transaction description title not valid or not found", 10);
        this.waitForElementPresent(TRANSACTION_CREATED_TITLE, "Transaction creation time not valid or not found", 10);
        this.waitForElementPresent(TRANSACTION_PAYMENT_TO_TITLE, "Transaction payment to title not valid or not found", 10);
        this.waitForElementPresent(TRANSACTION_ACCOUNT_NUMBER_TITLE, "Transaction account number not valid or not found", 10);
        this.waitForElementPresent(TRANSACTION_ID_TITLE, "Transaction ID not valid or not found", 10);
    }

    public void clickBackButton() {
        this.waitForElementAndClick(BACK_BUTTON, "Back button not found", 10);
    }

    public static TransactionDetailsPageObject get(RemoteWebDriver driver) {
        return TransactionDetailsPageObjectFactory.get(driver);
    }
}