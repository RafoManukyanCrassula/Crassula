package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.CurrencyExchangePageObject;

public class iOSCurrencyExchangePageObject extends CurrencyExchangePageObject {
    static {
        EXCHANGE_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Exchange']";
        EXCHANGE_PAGE_TITLE = "xpath://XCUIElementTypeStaticText[@name='Exchange']";
        EXCHANGE_RATE_INFO = "xpath://XCUIElementTypeStaticText[starts-with(@name, '£1 = €')]";
        FIRST_AMOUNT_FIELD = "xpath:(//XCUIElementTypeTextField)[1]";
        FIRST_CURRENCY_ICON = "xpath:(//XCUIElementTypeImage)[1]";
        FIRST_CURRENCY_BUTTON_TEMPLATE = "xpath://XCUIElementTypeButton[@name='{CURRENCY}']";
        FIRST_CURRENCY_BUTTON_GENERIC = "xpath:(//XCUIElementTypeButton)[1]";
        SECOND_AMOUNT_FIELD = "xpath:(//XCUIElementTypeTextField)[2]";
        SECOND_CURRENCY_ICON = "xpath:(//XCUIElementTypeImage)[2]";
        SECOND_CURRENCY_BUTTON_TEMPLATE = "xpath://XCUIElementTypeButton[@name='{CURRENCY}']";
        SECOND_CURRENCY_BUTTON_GENERIC = "xpath://XCUIElementTypeButton[@name='EUR']";
        CURRENCY_LETTER = "xpath://XCUIElementTypeStaticText";
        CURRENCY_LIST_TITLE = "xpath://XCUIElementTypeStaticText";
        CURRENCY_LIST_SUBTITLE = "xpath://XCUIElementTypeStaticText";
        BALANCE_TEMPLATE = "xpath://XCUIElementTypeStaticText[starts-with(@name, 'Balance:')]";
        MAX_BUTTON = "xpath://XCUIElementTypeStaticText[@name='MAX']";
        FEE_INFO = "xpath://XCUIElementTypeStaticText[contains(@name, 'No fees') or starts-with(@name, 'Fee: ')]";
        ACCOUNT_TO_LABEL = "xpath://XCUIElementTypeStaticText";
        ACCOUNT_SELECTION_FIELD = "xpath://XCUIElementTypeButton";
        ACCOUNT_NAME = "xpath://XCUIElementTypeStaticText";
        ACCOUNT_AMOUNT = "xpath://XCUIElementTypeStaticText";
        ACCOUNT_SELECTION_MODAL = "xpath://XCUIElementTypeStaticText[@name='0 funds account']";
        ACCOUNT_ICON = "xpath:(//XCUIElementTypeImage)[1]";
        ACCOUNT_NAME_TEMPLATE = "xpath://XCUIElementTypeStaticText[@name='{ACCOUNT_NAME}']";
        ACCOUNT_AMOUNT_TEMPLATE = "xpath://XCUIElementTypeStaticText[@name='{ACCOUNT_AMOUNT}']";
        EXCHANGE_BUTTON_FORM = "xpath://XCUIElementTypeButton[@name='Exchange']";
        ACCOUNT_BUTTON = "xpath://XCUIElementTypeButton[@name='Account']";
        FIRST_EDIT_AMOUNT = "xpath:(//XCUIElementTypeTextField)[1]";
        MINUS_SYMBOL = "id:-";
        PLUS_SYMBOL = "id:+";
        FEE_DETAILS_YOU_EXCHANGE = "xpath://XCUIElementTypeStaticText[@name='You exchange']";
        FEE_DETAILS_EXCHANGE_FEE = "xpath://XCUIElementTypeStaticText[@name='Exchange fee']";
        FEE_DETAILS_TOTAL_AMOUNT = "xpath://XCUIElementTypeStaticText[@name='Total amount']";
        FEE_DETAILS_YOU_EXCHANGE_VALUE = "xpath:(//XCUIElementTypeStaticText[starts-with(@name, '-£')])[1]";
        FEE_DETAILS_EXCHANGE_FEE_VALUE = "xpath:(//XCUIElementTypeStaticText[starts-with(@name, '-£')])[2]";
        FEE_DETAILS_TOTAL_AMOUNT_VALUE = "xpath:(//XCUIElementTypeStaticText[starts-with(@name, '-£')])[3]";
        CONFIRM_EXCHANGE_TITLE = "xpath://XCUIElementTypeStaticText[@name='Confirm exchange']";
        CONFIRM_FROM_ACCOUNT = "xpath://XCUIElementTypeStaticText[@name='From Account']";
        CONFIRM_YOU_EXCHANGE = "xpath://XCUIElementTypeStaticText[@name='You exchange']";
        CONFIRM_YOU_GET = "xpath://XCUIElementTypeStaticText[@name='You get']";
        CONFIRM_RATE = "xpath://XCUIElementTypeStaticText[@name='Rate']";
        CONFIRM_EXCHANGE_FEE = "xpath://XCUIElementTypeStaticText[@name='Exchange fee']";
        CONFIRM_TOTAL_AMOUNT = "xpath://XCUIElementTypeStaticText[@name='Total amount']";
        CONFIRM_BUTTON = "xpath://XCUIElementTypeButton[@name='Confirm exchange']";
        SUCCESS_TITLE = "xpath://XCUIElementTypeStaticText[@name='Success!']";
        SUCCESS_MESSAGE = "xpath://XCUIElementTypeStaticText";
        EXCHANGE_AMOUNT = "xpath://XCUIElementTypeStaticText";
        TRANSACTION_DETAILS_TITLE = "xpath://XCUIElementTypeStaticText[@name='Transaction details']";
        BACK_TO_HOME_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Back to home']";
        DASHBOARD_TRANSACTION = "xpath://XCUIElementTypeStaticText[@name='- £1.00']";
        UNAVAILABLE_CURRENCY_PAIR_ERROR = "xpath://XCUIElementTypeStaticText[@name='The exchange of these currency pairs GBP-UNI is currently unavailable']";
        UNI_CURRENCY_OPTION = "xpath://XCUIElementTypeStaticText[@name='UNI']";
        CURRENCY_LIST_FULL_NAME = "xpath://XCUIElementTypeStaticText[@name='Euro']";
        CURRENCY_LIST_SHORT_NAME = "xpath:(//XCUIElementTypeStaticText[@name='EUR'])[2]";
        CURRENCY_LIST_AMOUNT = "xpath://XCUIElementTypeStaticText[starts-with(@name, '€')]";
        CLOSE_CURRENCY_SELECTION = "xpath://XCUIElementTypeApplication[@name='Crassula']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeTable/XCUIElementTypeCell[11]/XCUIElementTypeOther[1]/XCUIElementTypeOther";
        ACCOUNT_MODAL_NAME = "xpath://XCUIElementTypeStaticText[contains(@name, 'account')]";
        ACCOUNT_MODAL_SUBTITLE = "xpath:(//XCUIElementTypeStaticText)[3]";
    }

    public iOSCurrencyExchangePageObject(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public void verifyFirstFieldBalance() {
        String balanceLocator = "xpath://XCUIElementTypeStaticText[starts-with(@name, 'Balance:')]";
        this.waitForElementPresent(
                balanceLocator,
                "Account balance not found on iOS",
                20
        );
    }

    @Override
    public String getSecondCurrencyButtonText() {
        return this.waitForElementAndGetAttribute(
                SECOND_CURRENCY_BUTTON_GENERIC,
                "name",
                "Cannot get currency button text",
                15
        );
    }
}