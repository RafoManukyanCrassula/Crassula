package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.TransactionDetailsPageObject;

public class iOSTransactionDetailsPageObject extends TransactionDetailsPageObject {
    static {
    TRANSACTION_AMOUNT = "xpath://XCUIElementTypeStaticText[starts-with(@name, '-') or starts-with(@name, '+')]";
    TRANSACTION_ICON = "xpath://XCUIElementTypeImage";
    TRANSACTION_PAYMENT = "xpath://XCUIElementTypeStaticText[string-length(@name) > 5]";
    TRANSACTION_DATE = "xpath://XCUIElementTypeStaticText[contains(@name, '2025') or contains(@name, 'AM') or contains(@name, 'PM')]";
    TRANSACTION_TYPE_TITLE = "xpath://XCUIElementTypeStaticText[@name='Type']";
    TRANSACTION_TYPE_VALUE = "xpath://XCUIElementTypeStaticText[@name='Exchange']";
    TRANSACTION_STATUS_TITLE = "xpath://XCUIElementTypeStaticText[@name='Status']";
    TRANSACTION_STATUS_VALUE = "xpath://XCUIElementTypeStaticText[contains(@name, 'Pending') or contains(@name, 'Completed') or contains(@name, 'Processing')]";
    TRANSACTION_DESCRIPTION_TITLE = "xpath://XCUIElementTypeStaticText[@name='Description']";
    TRANSACTION_CREATED_TITLE = "xpath://XCUIElementTypeStaticText[contains(@name, 'Exchange') and contains(@name, 'rate')]";
    TRANSACTION_PAYMENT_TO_TITLE = "xpath://XCUIElementTypeStaticText[contains(@name, 'Payment') or contains(@name, 'TO')]";
    TRANSACTION_ACCOUNT_NUMBER_TITLE = "xpath://XCUIElementTypeStaticText[contains(@name, 'Account')]";
    TRANSACTION_ID_TITLE = "xpath://XCUIElementTypeStaticText[string-length(@name) > 5 and string-length(@name) < 20]";
    BACK_BUTTON = "xpath://XCUIElementTypeButton";
}

    public iOSTransactionDetailsPageObject(AppiumDriver driver) {
        super(driver);
    }
}