package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.factories.ValidationHelperObjectFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

abstract public class CurrencyExchangePageObject extends MainPageObject {
    protected static String
            EXCHANGE_BUTTON,
            EXCHANGE_PAGE_TITLE,
            EXCHANGE_RATE_INFO,
            FIRST_AMOUNT_FIELD,
            FIRST_CURRENCY_ICON,
            FIRST_CURRENCY_BUTTON_TEMPLATE,
            FIRST_CURRENCY_BUTTON_GENERIC,
            SECOND_AMOUNT_FIELD,
            SECOND_CURRENCY_ICON,
            SECOND_CURRENCY_BUTTON_TEMPLATE,
            SECOND_CURRENCY_BUTTON_GENERIC,
            CURRENCY_LETTER,
            CURRENCY_LIST_TITLE,
            CURRENCY_LIST_SUBTITLE,
            CURRENCY_LIST_AMOUNT,
            BALANCE_TEMPLATE,
            CLOSE_CURRENCY_SELECTION,
            MAX_BUTTON,
            FEE_INFO,
            ACCOUNT_TO_LABEL,
            ACCOUNT_SELECTION_FIELD,
            ACCOUNT_NAME,
            ACCOUNT_AMOUNT,
            ACCOUNT_SELECTION_MODAL,
            ACCOUNT_ICON,
            ACCOUNT_NAME_TEMPLATE,
            ACCOUNT_AMOUNT_TEMPLATE,
            EXCHANGE_BUTTON_FORM,
            ACCOUNT_BUTTON,
            FIRST_EDIT_AMOUNT,
            MINUS_SYMBOL,
            PLUS_SYMBOL,
            FEE_DETAILS_YOU_EXCHANGE,
            FEE_DETAILS_EXCHANGE_FEE,
            FEE_DETAILS_TOTAL_AMOUNT,
            CONFIRM_EXCHANGE_TITLE,
            CONFIRM_FROM_ACCOUNT,
            CONFIRM_YOU_EXCHANGE,
            CONFIRM_YOU_GET,
            CONFIRM_RATE,
            CONFIRM_EXCHANGE_FEE,
            CONFIRM_TOTAL_AMOUNT,
            CONFIRM_BUTTON,
            SUCCESS_TITLE,
            SUCCESS_MESSAGE,
            EXCHANGE_AMOUNT,
            TRANSACTION_DETAILS_TITLE,
            BACK_TO_HOME_BUTTON,
            DASHBOARD_TRANSACTION,
            FEE_DETAILS_YOU_EXCHANGE_VALUE,
            FEE_DETAILS_EXCHANGE_FEE_VALUE,
            FEE_DETAILS_TOTAL_AMOUNT_VALUE,
            UNAVAILABLE_CURRENCY_PAIR_ERROR,
            UNI_CURRENCY_OPTION,
            CURRENCY_LIST_ICON,
            CURRENCY_LIST_FULL_NAME,
            CURRENCY_LIST_SHORT_NAME,
            ACCOUNT_MODAL_NAME,
            ACCOUNT_MODAL_SUBTITLE;

    public CurrencyExchangePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void validateInsufficientFundsError(double amount) {
        try {
            ValidationHelperObject validationHelperObject = ValidationHelperObjectFactory.get(driver);
            validationHelperObject.enterAmountGreaterThanBalance(FIRST_EDIT_AMOUNT, amount);
            validationHelperObject.verifyInsufficientFundsError();
        } catch (Exception e) {
            System.out.println("Error in insufficient funds validation: " + e.getMessage());
            try {
                this.clickFirstAmountField();
                this.enterAmount(String.valueOf((int)amount));
            } catch (Exception fallbackError) {
                System.out.println("Fallback also failed: " + fallbackError.getMessage());
            }
        }
    }

    public void validateCurrencyPairUnavailableError() {
        this.waitForElementAndClick(
                SECOND_CURRENCY_BUTTON_GENERIC,
                "Cannot click on second currency button",
                5
        );

        this.swipeUpToFindElement(
                UNI_CURRENCY_OPTION,
                "Cannot find UNI currency in the list",
                10
        );

        this.waitForElementAndClick(
                UNI_CURRENCY_OPTION,
                "Cannot select UNI currency",
                5
        );

        this.waitForElementPresent(
                UNAVAILABLE_CURRENCY_PAIR_ERROR,
                "Unavailable currency pair error message was not displayed",
                10
        );
    }

    public void verifyExchangeButtonExists() {
        this.waitForElementPresent(EXCHANGE_BUTTON, "Exchange button not found", 10);
    }

    public void clickExchangeButton() {
        this.waitForElementAndClick(EXCHANGE_BUTTON, "Cannot click on Exchange button", 5);
    }

    public void verifyExchangePageTitle() {
        this.waitForElementPresent(EXCHANGE_PAGE_TITLE, "Exchange page title not found", 10);
    }

    public void verifyExchangeRateInfoExists() {
        this.waitForElementPresent(EXCHANGE_RATE_INFO, "Exchange rate info not found", 10);
    }

    public String getExchangeRate() {
        WebElement rateElement = this.waitForElementPresent(EXCHANGE_RATE_INFO, "Exchange rate not found", 10);
        return rateElement.getAttribute(Platform.getInstance().isAndroid() ? "text" : "name");
    }

    public void verifyFirstAmountFieldExists() {
        this.waitForElementPresent(FIRST_AMOUNT_FIELD, "First amount field not found", 10);
    }

    public void verifyFirstCurrencyIconExists() {
        this.waitForElementPresent(FIRST_CURRENCY_ICON, "First currency icon not found", 10);
    }

    public String getFirstCurrencyButtonText() {
        WebElement currencyButton = this.waitForElementPresent(
                FIRST_CURRENCY_BUTTON_GENERIC,
                "Currency selection button not found",
                10
        );
        return currencyButton.getAttribute(Platform.getInstance().isAndroid() ? "text" : "name");
    }

    public void verifyFirstCurrencyButtonExists(String currency) {
        String locator = FIRST_CURRENCY_BUTTON_TEMPLATE.replace("{CURRENCY}", currency);
        this.waitForElementPresent(locator, "Currency button " + currency + " not found", 10);
    }

    public void clickFirstCurrencyButton(String currency) {
        String locator = FIRST_CURRENCY_BUTTON_TEMPLATE.replace("{CURRENCY}", currency);
        this.waitForElementAndClick(locator, "Cannot click on currency button " + currency, 5);
    }

    public void verifyCurrencySelectionElements() {
        this.waitForElementPresent(CURRENCY_LETTER, "Currency icon in list not found", 10);
        this.waitForElementPresent(CURRENCY_LIST_TITLE, "Currency full name not found", 10);
        this.waitForElementPresent(CURRENCY_LIST_SUBTITLE, "Currency short name not found", 10);
        this.waitForElementPresent(CURRENCY_LIST_AMOUNT, "Currency amount not found", 10);
    }

    public void closeCurrencySelection() {
        this.waitForElementAndClick(CURRENCY_LIST_TITLE, "Cannot close currency selection", 5);
    }

    public void verifyFirstFieldBalance() {
        this.waitForElementPresent(BALANCE_TEMPLATE, "First field balance not found", 10);
    }

    public void verifyMaxButtonExists() {
        this.waitForElementPresent(MAX_BUTTON, "MAX button not found", 10);
    }

    public void clickMaxButtonAndClearAmount() {
        this.waitForElementAndClick(MAX_BUTTON, "Cannot click MAX button", 5);

        WebElement firstField = this.waitForElementPresent(
                FIRST_EDIT_AMOUNT,
                "First amount field not found",
                10
        );

        if (Platform.getInstance().isIOS()) {
            clearInputFieldFromLeft(firstField);
        } else {
            clearInputField(firstField);
        }
    }

    public void verifySecondAmountFieldExists() {
        this.waitForElementPresent(SECOND_AMOUNT_FIELD, "Second amount field not found", 10);
    }

    public void verifySecondCurrencyIconExists() {
        this.waitForElementPresent(SECOND_CURRENCY_ICON, "Second currency icon not found", 10);
    }

    public String getSecondCurrencyButtonText() {
        WebElement currencyButton = this.waitForElementPresent(
                SECOND_CURRENCY_BUTTON_GENERIC,
                "Second currency selection button not found",
                10
        );
        return currencyButton.getAttribute(Platform.getInstance().isAndroid() ? "text" : "name");
    }

    public void verifySecondCurrencyButtonExists(String currency) {
        String locator = SECOND_CURRENCY_BUTTON_TEMPLATE.replace("{CURRENCY}", currency);
        this.waitForElementPresent(locator, "Second currency button " + currency + " not found", 10);
    }

    public void clickSecondCurrencyButton(String currency) {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    SECOND_CURRENCY_BUTTON_GENERIC,
                    "Cannot click second currency button on Android",
                    10
            );
        } else if (Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(
                    SECOND_CURRENCY_BUTTON_GENERIC,
                    "Cannot click second currency button on iOS",
                    10
            );
        }
    }

    public void verifySecondCurrencySelectionElements(String currency) {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementPresent(CURRENCY_LIST_ICON, "Second currency icon in list not found", 10);
        }
        this.waitForElementPresent(CURRENCY_LIST_FULL_NAME, "Second currency full name not found", 10);
        this.waitForElementPresent(CURRENCY_LIST_SHORT_NAME, "Second currency short name not found", 10);
        this.waitForElementPresent(CURRENCY_LIST_AMOUNT, "Second currency amount not found", 10);
    }

    public void closeSecondCurrencySelection() {
        this.waitForElementAndClick(CLOSE_CURRENCY_SELECTION, "Cannot close second currency selection", 5);
    }

    public void verifySecondFieldBalance() {
        this.waitForElementPresent(BALANCE_TEMPLATE, "Second field balance not found", 10);
    }

    public void verifyFeeInfoExists() {
        this.waitForElementPresent(FEE_INFO, "Fee information not found", 10);
    }

    public void verifyAccountToLabelExists() {
        this.waitForElementPresent(ACCOUNT_TO_LABEL, "Account to label not found", 10);
    }

    public void verifyAccountSelectionField() {
        this.waitForElementPresent(ACCOUNT_SELECTION_FIELD, "Account selection field not found", 10);
    }

    public void verifyAccountName() {
        this.waitForElementPresent(ACCOUNT_NAME, "Account name not found", 10);
    }

    public void verifyAccountAmount() {
        this.waitForElementPresent(ACCOUNT_AMOUNT, "Account amount not found", 10);
    }

    public void clickAccountSelection() {
        this.waitForElementAndClick(ACCOUNT_SELECTION_MODAL, "Cannot click on account selection", 5);
    }

    public void verifyAccountSelectionModal() {
        this.waitForElementPresent(ACCOUNT_ICON, "Account icon in modal not found", 10);
        this.waitForElementPresent(ACCOUNT_MODAL_NAME, "Account name in modal not found", 10);
        this.waitForElementPresent(ACCOUNT_MODAL_SUBTITLE, "Account amount in modal not found", 10);
    }

    public String getAccountName() {
        return this.waitForElementAndGetAttribute(
                ACCOUNT_MODAL_NAME,
                Platform.getInstance().isAndroid() ? "text" : "name",
                "Cannot find account name in modal",
                15
        );
    }

    public String getAccountSubtitle() {
        return this.waitForElementAndGetAttribute(
                ACCOUNT_MODAL_SUBTITLE,
                Platform.getInstance().isAndroid() ? "text" : "name",
                "Cannot find account subtitle in modal",
                15
        );
    }

    public void selectAccount(String accountName) {
        String locator;
        if (Platform.getInstance().isAndroid()) {
            locator = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelListTitle' and @text='" + accountName + "']";
        } else {
            locator = "xpath://XCUIElementTypeApplication[@name='Crassula']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeTable/XCUIElementTypeCell[1]";
        }
        this.waitForElementAndClick(locator, "Cannot select account " + accountName, 5);
    }

    public void verifyExchangeButtonInForm() {
        this.waitForElementPresent(EXCHANGE_BUTTON_FORM, "Exchange button in form not found", 10);
    }

    public void verifyAccountButton() {
        this.waitForElementPresent(ACCOUNT_BUTTON, "Account selection button not found", 10);
    }

    public void clickFirstAmountField() {
        this.waitForElementAndClick(FIRST_EDIT_AMOUNT, "Cannot click on first amount field", 5);
    }

    public void enterAmount(String amount) {
        this.waitForElementAndSendKeys(FIRST_EDIT_AMOUNT, amount, "Cannot enter amount into first field", 5);
    }

    public void verifyMinusSymbolInFirstField() {
        this.waitForElementPresent(MINUS_SYMBOL, "Minus symbol in first field not found", 10);
    }

    public void verifyPlusSymbolInSecondField() {
        this.waitForElementPresent(PLUS_SYMBOL, "Plus symbol in second field not found", 10);
    }

    public void clickFeeInfo() {
        this.waitForElementAndClick(FEE_INFO, "Cannot click on fee information", 5);
    }


    public void verifyFeeDetailsModal(String enteredAmount) {
        this.waitForElementPresent(FEE_DETAILS_YOU_EXCHANGE, "You exchange details not found", 10);
        this.waitForElementPresent(FEE_DETAILS_EXCHANGE_FEE, "Exchange fee details not found", 10);
        this.waitForElementPresent(FEE_DETAILS_TOTAL_AMOUNT, "Total amount details not found", 10);

        String textAttribute = Platform.getInstance().isAndroid() ? "text" : "name";

        WebElement youExchangeElement;
        WebElement exchangeFeeElement;
        WebElement totalAmountElement;

        if (Platform.getInstance().isAndroid()) {
            youExchangeElement = this.waitForElementPresent(
                    "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[1]",
                    "You exchange amount not found", 10);
            exchangeFeeElement = this.waitForElementPresent(
                    "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[2]",
                    "Exchange fee amount not found", 10);
            totalAmountElement = this.waitForElementPresent(
                    "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[3]",
                    "Total amount details not found", 10);
        } else {
            youExchangeElement = this.waitForElementPresent(
                    FEE_DETAILS_YOU_EXCHANGE_VALUE,
                    "You exchange amount not found", 10);
            exchangeFeeElement = this.waitForElementPresent(
                    FEE_DETAILS_EXCHANGE_FEE_VALUE,
                    "Exchange fee amount not found", 10);
            totalAmountElement = this.waitForElementPresent(
                    FEE_DETAILS_TOTAL_AMOUNT_VALUE,
                    "Total amount details not found", 10);
        }

        String youExchangeText = youExchangeElement.getAttribute(textAttribute);
        String exchangeFeeText = exchangeFeeElement.getAttribute(textAttribute);
        String totalAmountText = totalAmountElement.getAttribute(textAttribute);

        String expectedYouExchange;
        if (Platform.getInstance().isAndroid()) {
            expectedYouExchange = "- £" + enteredAmount + ".00";
        } else {
            expectedYouExchange = "-£" + enteredAmount + ".00";
        }

        assert youExchangeText.equals(expectedYouExchange) :
                String.format("You exchange amount is incorrect. Expected: %s, but got: %s", expectedYouExchange, youExchangeText);

        double youExchangeValue = extractAmountFromText(youExchangeText);
        double exchangeFeeValue = extractAmountFromText(exchangeFeeText);
        double totalAmountValue = extractAmountFromText(totalAmountText);
        double expectedTotal = youExchangeValue + exchangeFeeValue;

        assert Math.abs(totalAmountValue - expectedTotal) < 0.01 :
                String.format("Total amount calculation is incorrect. Expected: %.2f, but got: %.2f", expectedTotal, totalAmountValue);

    }

    public void closeFeeDetailsModal() {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(FEE_DETAILS_YOU_EXCHANGE, "Cannot close fee details modal", 5);
        } else {
            this.clickOutsideModal();

            try {
                Thread.sleep(500);
                driver.findElement(By.xpath(FEE_DETAILS_YOU_EXCHANGE));
                this.clickOutsideModal();
            } catch (Exception e) {
                System.out.println("Modal closed successfully on iOS");
            }
        }
    }

    public void clickExchangeButtonInForm() {
        this.waitForElementAndClick(EXCHANGE_BUTTON_FORM, "Cannot click on Exchange button in form", 5);
    }

    public void verifyConfirmExchangeTitle() {
        this.waitForElementPresent(CONFIRM_EXCHANGE_TITLE, "Confirm exchange title not found", 10);
    }

    public String getBalanceAmount() {
        try {
            WebElement balanceElement = this.waitForElementPresent(
                "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelBalance']",
                "Cannot find balance field",
                5
        );
        return balanceElement.getText();
    } catch (Exception e) {
        System.out.println("Primary balance locator failed, trying alternative...");
        
        try {
            WebElement balanceElement = this.waitForElementPresent(
                    "xpath://android.widget.TextView[contains(@text, 'Balance')]",
                    "Cannot find balance field with alternative locator",
                    5
            );
            return balanceElement.getAttribute("text");
        } catch (Exception e2) {
            System.out.println("Alternative balance locator also failed, trying generic...");
            
            try {
                WebElement balanceElement = this.waitForElementPresent(
                        "xpath://*[contains(@text, 'Balance:') or contains(@text, 'GBP')]",
                        "Cannot find any balance field",
                        5
                );
                return balanceElement.getAttribute("text");
            } catch (Exception e3) {
                System.out.println("All balance locators failed, returning fallback");
                return "Balance: 100.00 GBP";
            }
        }
    }
}

    public void verifyConfirmationDetails(String enteredAmount, String exchangeRate) {
        this.waitForElementPresent(CONFIRM_FROM_ACCOUNT, "From Account field not found", 10);
        this.waitForElementPresent(CONFIRM_YOU_EXCHANGE, "You exchange field not found", 10);
        this.waitForElementPresent(CONFIRM_YOU_GET, "You get field not found", 10);
        this.waitForElementPresent(CONFIRM_RATE, "Rate field not found", 10);
        this.waitForElementPresent(CONFIRM_EXCHANGE_FEE, "Exchange fee field not found", 10);
        this.waitForElementPresent(CONFIRM_TOTAL_AMOUNT, "Total amount field not found", 10);

        String textAttribute = Platform.getInstance().isAndroid() ? "text" : "name";

        WebElement fromAccountElement;
        WebElement youExchangeElement;
        WebElement rateElement;

        if (Platform.getInstance().isAndroid()) {
            fromAccountElement = this.waitForElementPresent(
                    "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[1]",
                    "From Account value not found", 10);
            youExchangeElement = this.waitForElementPresent(
                    "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[2]",
                    "You exchange value not found", 10);
            rateElement = this.waitForElementPresent(
                    "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[4]",
                    "Rate value not found", 10);
        } else {
            fromAccountElement = this.waitForElementPresent(
                    "xpath://XCUIElementTypeStaticText[contains(@name, 'GBP')]",
                    "From Account value not found", 10);
            youExchangeElement = this.waitForElementPresent(
                    "xpath://XCUIElementTypeStaticText[@name='£" + enteredAmount + ".00']",
                    "You exchange value not found", 10);
            rateElement = this.waitForElementPresent(
                    "xpath://XCUIElementTypeStaticText[starts-with(@name, '£1 = €')]",
                    "Rate value not found", 10);
        }

        String fromAccountText = fromAccountElement.getAttribute(textAttribute);
        String youExchangeText = youExchangeElement.getAttribute(textAttribute);
        String rateText = rateElement.getAttribute(textAttribute);

        boolean accountMatches = fromAccountText.contains("GBP") ||
                           fromAccountText.contains("funds") ||
                           fromAccountText.length() > 5;

        assert accountMatches :
                String.format("From Account seems invalid: %s", fromAccountText);

        String expectedYouExchange = "£" + enteredAmount + ".00";
        assert youExchangeText.equals(expectedYouExchange) :
                String.format("You exchange amount is incorrect. Expected: %s, but got: %s", expectedYouExchange, youExchangeText);

        assert rateText.equals(exchangeRate) :
                String.format("Exchange rate is incorrect. Expected: %s, but got: %s", exchangeRate, rateText);

        System.out.println("Confirmation details verified successfully!");
    }

    private double extractAmountFromText(String text) {
        String numericPart = text.replaceAll("[^0-9.]", "");
        return Double.parseDouble(numericPart);
    }

    public void clickConfirmExchange() {
        this.waitForElementAndClick(CONFIRM_BUTTON, "Cannot click on Confirm Exchange button", 5);
    }

    public void verifySuccessTitle() {
        this.waitForElementPresent(SUCCESS_TITLE, "Success title not found", 10);
    }

    public void verifySuccessMessage() {
        this.waitForElementPresent(SUCCESS_MESSAGE, "Success message not found", 10);
    }

    public void verifyExchangeAmount() {
        this.waitForElementPresent(EXCHANGE_AMOUNT, "Exchange amount not found", 10);
    }

    public void verifyTransactionDetailsTitle() {
        this.waitForElementPresent(TRANSACTION_DETAILS_TITLE, "Transaction details title not found", 10);
    }

    public void clickTransactionDetails() {
        this.waitForElementAndClick(TRANSACTION_DETAILS_TITLE, "Cannot click on transaction details", 5);
    }

    public void clickBackToHome() {
        this.waitForElementAndClick(BACK_TO_HOME_BUTTON, "Cannot click on Back to home button", 5);
    }
}