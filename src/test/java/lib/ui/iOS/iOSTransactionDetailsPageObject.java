package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.TransactionDetailsPageObject;

public class iOSTransactionDetailsPageObject extends TransactionDetailsPageObject {
    static {
        TRANSACTION_AMOUNT = "id:com.crassula.demo:id/labelAmount";
        TRANSACTION_ICON = "id:com.crassula.demo:id/imageIcon";
        TRANSACTION_PAYMENT = "id:com.crassula.demo:id/labelPayment";
        TRANSACTION_DATE = "id:com.crassula.demo:id/labelDetails";
        TRANSACTION_TYPE_TITLE = "xpath://XCUIElementTypeStaticText[@name='Type']";
        TRANSACTION_TYPE_VALUE = "xpath://XCUIElementTypeStaticText[@name='Exchange']";
        TRANSACTION_STATUS_TITLE = "xpath://XCUIElementTypeStaticText[@name='Status']";
        TRANSACTION_STATUS_VALUE = "xpath://XCUIElementTypeStaticText[contains(@name, 'Pending')]";
        TRANSACTION_DESCRIPTION_TITLE = "xpath://XCUIElementTypeStaticText[contains(@name, 'Exchange')]";
        TRANSACTION_CREATED_TITLE = "xpath://XCUIElementTypeStaticText[contains(@name, '2025')]";
        TRANSACTION_PAYMENT_TO_TITLE = "xpath://XCUIElementTypeStaticText[contains(@name, 'TO')]";
        TRANSACTION_ACCOUNT_NUMBER_TITLE = "xpath://XCUIElementTypeStaticText[@name='8497 9796 400']";
        TRANSACTION_ID_TITLE = "id:Transaction ID";
        BACK_BUTTON = "id:XCUIElementTypeButton";
    }

    public iOSTransactionDetailsPageObject(AppiumDriver driver) {
        super(driver);
    }
}