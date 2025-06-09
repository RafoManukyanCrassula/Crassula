package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.CurrencyExchangePageObject;

public class iOSCurrencyExchangePageObject extends CurrencyExchangePageObject {
    static {
        EXCHANGE_BUTTON = "com.crassula.demo:id/buttonExchange";
        EXCHANGE_PAGE_TITLE = "//XCUIElementTypeStaticText[@name='Exchange']";
        EXCHANGE_RATE_INFO = "com.crassula.demo:id/labelInfoExchange";
        FIRST_AMOUNT_FIELD = "(//XCUIElementTypeOther[@name='com.crassula.demo:id/viewAmount'])[1]";
        FIRST_CURRENCY_ICON = "(//XCUIElementTypeImage[@name='com.crassula.demo:id/imageCurrency'])[1]";
        FIRST_CURRENCY_BUTTON_TEMPLATE = "//XCUIElementTypeButton[@name='com.crassula.demo:id/buttonCurrency' and @label='{CURRENCY}']";
        SECOND_AMOUNT_FIELD = "(//XCUIElementTypeOther[@name='com.crassula.demo:id/viewAmount'])[2]";
        SECOND_CURRENCY_ICON = "(//XCUIElementTypeImage[@name='com.crassula.demo:id/imageCurrency'])[2]";
        SECOND_CURRENCY_BUTTON_TEMPLATE = "//XCUIElementTypeButton[@name='com.crassula.demo:id/buttonCurrency' and @label='{CURRENCY}']";
        CURRENCY_LETTER = "com.crassula.demo:id/currencyLetter";
        CURRENCY_LIST_TITLE = "com.crassula.demo:id/labelListTitle";
        CURRENCY_LIST_SUBTITLE = "com.crassula.demo:id/labelListSubTitle";
        CURRENCY_LIST_AMOUNT = "com.crassula.demo:id/labelListAmount";
        BALANCE_TEMPLATE = "//XCUIElementTypeStaticText[@name='com.crassula.demo:id/labelBalance' and contains(@label, 'Balance:')]";
        MAX_BUTTON = "com.crassula.demo:id/labelMax";
        FEE_INFO = "com.crassula.demo:id/labelInfoFee";
        ACCOUNT_TO_LABEL = "com.crassula.demo:id/labelAccountTo";
        ACCOUNT_SELECTION_FIELD = "com.crassula.demo:id/layoutViewHolder";
        ACCOUNT_NAME = "com.crassula.demo:id/labelListTitle";
        ACCOUNT_AMOUNT = "com.crassula.demo:id/labelListSubTitle";
        ACCOUNT_SELECTION_MODAL = "(//XCUIElementTypeOther[@name='com.crassula.demo:id/layoutHolder'])[1]/XCUIElementTypeOther[2]";
        ACCOUNT_ICON = "(//XCUIElementTypeImage[@name='com.crassula.demo:id/imageIcon'])[1]";
        ACCOUNT_NAME_TEMPLATE = "//XCUIElementTypeStaticText[@name='com.crassula.demo:id/labelListTitle' and @label='{ACCOUNT_NAME}']";
        ACCOUNT_AMOUNT_TEMPLATE = "//XCUIElementTypeStaticText[@name='com.crassula.demo:id/labelListSubTitle' and @label='{ACCOUNT_AMOUNT}']";
        EXCHANGE_BUTTON_FORM = "com.crassula.demo:id/buttonExchange";
        ACCOUNT_BUTTON = "com.crassula.demo:id/buttonAccount";
        FIRST_EDIT_AMOUNT = "(//XCUIElementTypeTextField[@name='com.crassula.demo:id/editAmount'])[1]";
        MINUS_SYMBOL = "//XCUIElementTypeStaticText[@name='com.crassula.demo:id/textinput_prefix_text' and @label='-']";
        PLUS_SYMBOL = "//XCUIElementTypeStaticText[@name='com.crassula.demo:id/textinput_prefix_text' and @label='+']";
        FEE_DETAILS_YOU_EXCHANGE = "//XCUIElementTypeStaticText[@name='com.crassula.demo:id/labelListTitle' and @label='You exchange']";
        FEE_DETAILS_EXCHANGE_FEE = "//XCUIElementTypeStaticText[@name='com.crassula.demo:id/labelListTitle' and @label='Exchange fee']";
        FEE_DETAILS_TOTAL_AMOUNT = "//XCUIElementTypeStaticText[@name='com.crassula.demo:id/labelListTitle' and @label='Total amount']";
        CONFIRM_EXCHANGE_TITLE = "com.crassula.demo:id/labelTopBarTitle";
        CONFIRM_FROM_ACCOUNT = "//XCUIElementTypeStaticText[@name='com.crassula.demo:id/labelTitle' and @label='From Account']";
        CONFIRM_YOU_EXCHANGE = "//XCUIElementTypeStaticText[@name='com.crassula.demo:id/labelTitle' and @label='You exchange']";
        CONFIRM_YOU_GET = "//XCUIElementTypeStaticText[@name='com.crassula.demo:id/labelTitle' and @label='You get']";
        CONFIRM_RATE = "//XCUIElementTypeStaticText[@name='com.crassula.demo:id/labelTitle' and @label='Rate']";
        CONFIRM_EXCHANGE_FEE = "//XCUIElementTypeStaticText[@name='com.crassula.demo:id/labelTitle' and @label='Exchange fee']";
        CONFIRM_TOTAL_AMOUNT = "//XCUIElementTypeStaticText[@name='com.crassula.demo:id/labelTitle' and @label='Total amount']";
        CONFIRM_BUTTON = "com.crassula.demo:id/buttonConfirm";
        SUCCESS_TITLE = "//XCUIElementTypeStaticText[@name='com.crassula.demo:id/labelTitle' and @label='Success!']";
        SUCCESS_MESSAGE = "com.crassula.demo:id/labelText";
        EXCHANGE_AMOUNT = "com.crassula.demo:id/labelAmount";
        TRANSACTION_DETAILS_TITLE = "//XCUIElementTypeStaticText[@name='com.crassula.demo:id/labelTitle' and @label='Transaction details']";
    }

    public iOSCurrencyExchangePageObject(AppiumDriver driver) {
        super(driver);
    }
}