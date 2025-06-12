package lib.ui.Android;

import lib.ui.TransactionDetailsPageObject;
import io.appium.java_client.AppiumDriver;

public class AndroidTransactionDetailsPageObject extends TransactionDetailsPageObject
{
    static {
        TRANSACTION_AMOUNT = "id:com.crassula.demo:id/labelAmount";
        TRANSACTION_ICON = "id:com.crassula.demo:id/imageIcon";
        TRANSACTION_PAYMENT = "id:com.crassula.demo:id/labelPayment";
        TRANSACTION_DATE = "id:com.crassula.demo:id/labelDetails";
        TRANSACTION_TYPE_TITLE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelTitle' and @text='Type']";
        TRANSACTION_TYPE_VALUE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue' and @text='Exchange']";
        TRANSACTION_STATUS_TITLE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelTitle' and @text='Status']";
        TRANSACTION_STATUS_VALUE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue' and @text='Pending']";
        TRANSACTION_DESCRIPTION_TITLE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelTitle' and @text='Description']";
        TRANSACTION_CREATED_TITLE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelTitle' and @text='Created']";
        TRANSACTION_PAYMENT_TO_TITLE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelTitle' and @text='Payment to']";
        TRANSACTION_ACCOUNT_NUMBER_TITLE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelTitle' and @text='Account number']";
        TRANSACTION_ID_TITLE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelTitle' and @text='Transaction ID']";
        BACK_BUTTON = "id:com.crassula.demo:id/buttonBack";
    }

    public AndroidTransactionDetailsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}