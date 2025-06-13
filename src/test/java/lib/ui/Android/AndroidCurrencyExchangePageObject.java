package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.CurrencyExchangePageObject;

public class AndroidCurrencyExchangePageObject extends CurrencyExchangePageObject {
    static {
        EXCHANGE_BUTTON = "id:com.crassula.demo:id/buttonExchange";
        EXCHANGE_PAGE_TITLE = "xpath://android.widget.TextView[@text='Exchange']";
        EXCHANGE_RATE_INFO = "xpath://android.widget.TextView[contains(@text, '£') and contains(@text, '€')]";
        FIRST_AMOUNT_FIELD = "xpath:(//android.view.ViewGroup[@resource-id='com.crassula.demo:id/viewAmount'])[1]";
        FIRST_CURRENCY_ICON = "xpath:(//android.widget.ImageView[@resource-id='com.crassula.demo:id/imageCurrency'])[1]";
        FIRST_CURRENCY_BUTTON_TEMPLATE = "xpath://android.widget.Button[@resource-id='com.crassula.demo:id/buttonCurrency' and @text='{CURRENCY}']";
        FIRST_CURRENCY_BUTTON_GENERIC = "xpath://android.widget.Button[@resource-id='com.crassula.demo:id/buttonCurrency']";
        SECOND_AMOUNT_FIELD = "xpath:(//android.view.ViewGroup[@resource-id='com.crassula.demo:id/viewAmount'])[2]";
        SECOND_CURRENCY_ICON = "xpath:(//android.widget.ImageView[@resource-id='com.crassula.demo:id/imageCurrency'])[2]";
        SECOND_CURRENCY_BUTTON_TEMPLATE = "xpath://android.widget.Button[@resource-id='com.crassula.demo:id/buttonCurrency' and @text='{CURRENCY}']";
        SECOND_CURRENCY_BUTTON_GENERIC = "xpath:(//android.widget.Button[@resource-id='com.crassula.demo:id/buttonCurrency'])[2]";
        CURRENCY_LETTER = "id:com.crassula.demo:id/currencyLetter";
        CURRENCY_LIST_TITLE = "id:com.crassula.demo:id/labelListTitle";
        CURRENCY_LIST_SUBTITLE = "id:com.crassula.demo:id/labelListSubTitle";
        CURRENCY_LIST_AMOUNT = "id:com.crassula.demo:id/labelListAmount";
        BALANCE_TEMPLATE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelBalance' and contains(@text, 'Balance:')]";
        MAX_BUTTON = "id:com.crassula.demo:id/labelMax";
        FEE_INFO = "id:com.crassula.demo:id/labelInfoFee";
        ACCOUNT_TO_LABEL = "id:com.crassula.demo:id/labelAccountTo";
        ACCOUNT_SELECTION_FIELD = "id:com.crassula.demo:id/layoutViewHolder";
        ACCOUNT_NAME = "id:com.crassula.demo:id/labelListTitle";
        ACCOUNT_AMOUNT = "id:com.crassula.demo:id/labelListSubTitle";
        ACCOUNT_SELECTION_MODAL = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelListTitle' and @text='0 funds account']";
        ACCOUNT_ICON = "xpath:(//android.widget.ImageView[@resource-id='com.crassula.demo:id/imageIcon'])[1]";
        ACCOUNT_NAME_TEMPLATE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelListTitle' and @text='{ACCOUNT_NAME}']";
        ACCOUNT_AMOUNT_TEMPLATE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelListSubTitle' and @text='{ACCOUNT_AMOUNT}']";
        EXCHANGE_BUTTON_FORM = "id:com.crassula.demo:id/buttonExchange";
        ACCOUNT_BUTTON = "id:com.crassula.demo:id/buttonAccount";
        FIRST_EDIT_AMOUNT = "xpath:(//android.widget.EditText[@resource-id='com.crassula.demo:id/editAmount'])[1]";
        MINUS_SYMBOL = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/textinput_prefix_text' and @text='-']";
        PLUS_SYMBOL = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/textinput_prefix_text' and @text='+']";
        FEE_DETAILS_YOU_EXCHANGE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelListTitle' and @text='You exchange']";
        FEE_DETAILS_EXCHANGE_FEE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelListTitle' and @text='Exchange fee']";
        FEE_DETAILS_TOTAL_AMOUNT = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelListTitle' and @text='Total amount']";
        CONFIRM_EXCHANGE_TITLE = "id:com.crassula.demo:id/labelTopBarTitle";
        CONFIRM_FROM_ACCOUNT = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelTitle' and @text='From Account']";
        CONFIRM_YOU_EXCHANGE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelTitle' and @text='You exchange']";
        CONFIRM_YOU_GET = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelTitle' and @text='You get']";
        CONFIRM_RATE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelTitle' and @text='Rate']";
        CONFIRM_EXCHANGE_FEE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelTitle' and @text='Exchange fee']";
        CONFIRM_TOTAL_AMOUNT = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelTitle' and @text='Total amount']";
        CONFIRM_BUTTON = "id:com.crassula.demo:id/buttonConfirm";
        SUCCESS_TITLE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelTitle' and @text='Success!']";
        SUCCESS_MESSAGE = "id:com.crassula.demo:id/labelText";
        EXCHANGE_AMOUNT = "id:com.crassula.demo:id/labelAmount";
        TRANSACTION_DETAILS_TITLE = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelTitle' and @text='Transaction details']";
        BACK_TO_HOME_BUTTON = "id:com.crassula.demo:id/buttonContinue";
        DASHBOARD_TRANSACTION = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelAmount' and @text='- £1.00']";
        FEE_DETAILS_YOU_EXCHANGE_VALUE = "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[1]";
        FEE_DETAILS_EXCHANGE_FEE_VALUE = "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[2]";
        FEE_DETAILS_TOTAL_AMOUNT_VALUE = "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[3]";
        UNAVAILABLE_CURRENCY_PAIR_ERROR = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelBalance' and @text='The exchange of these currency pairs GBP-UNI is currently unavailable']";
        UNI_CURRENCY_OPTION = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelListSubTitle' and @text='UNI']";
    }

    public AndroidCurrencyExchangePageObject(AppiumDriver driver) {
        super(driver);
    }
}