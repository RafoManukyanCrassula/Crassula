package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.factories.ValidationHelperObjectFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.By;
import java.time.Duration;
import java.util.Arrays;

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
        ValidationHelperObject validationHelperObject = ValidationHelperObjectFactory.get(driver);
        validationHelperObject.enterAmountGreaterThanBalance(FIRST_EDIT_AMOUNT, amount);
        validationHelperObject.verifyInsufficientFundsError();
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

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

    private void clearInputField(WebElement field) {
        field.click();
        int length = field.getText().length();
        for (int i = 0; i < length; i++) {
            field.sendKeys("\b");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    public String getAccountNumberFromModal() {
        return this.waitForElementAndGetAttribute(
                "xpath://XCUIElementTypeStaticText[contains(@name, 'account')]",
                Platform.getInstance().isAndroid() ? "text" : "name",
                "Cannot find account number in modal",
                15
        );
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
        this.waitForElementAndSendKeys(FIRST_EDIT_AMOUNT, amount, "Cannot enter amount", 5);
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

    public void clickOutsideModal() {
        if (Platform.getInstance().isIOS()) {
            try {
                Dimension size = driver.manage().window().getSize();
                int centerX = size.width / 2;
                int centerY = size.height / 2;

                org.openqa.selenium.interactions.PointerInput finger = new org.openqa.selenium.interactions.PointerInput(org.openqa.selenium.interactions.PointerInput.Kind.TOUCH, "finger");
                org.openqa.selenium.interactions.Sequence tap = new org.openqa.selenium.interactions.Sequence(finger, 1);

                tap.addAction(finger.createPointerMove(Duration.ofMillis(0), org.openqa.selenium.interactions.PointerInput.Origin.viewport(), centerX, centerY));
                tap.addAction(finger.createPointerDown(org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));
                tap.addAction(finger.createPointerUp(org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));

                driver.perform(Arrays.asList(tap));
                Thread.sleep(1000);

            } catch (Exception e) {
                System.out.println("Failed to close modal with center tap: " + e.getMessage());

                try {
                    Dimension size = driver.manage().window().getSize();
                    int centerX = size.width / 2;
                    int centerY = size.height / 2;

                    org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
                    actions.moveToLocation(centerX, centerY).click().perform();
                    Thread.sleep(1000);

                } catch (Exception ex) {
                    System.out.println("Alternative center click also failed: " + ex.getMessage());
                }
            }
        }
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
        WebElement balanceElement = this.waitForElementPresent(
                "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelBalance']",
                "Cannot find balance field",
                10
        );
        return balanceElement.getText();
    }

    public void verifyConfirmationDetails(String enteredAmount, String accountNumber, String exchangeRate) {
        this.waitForElementPresent(CONFIRM_FROM_ACCOUNT, "From Account field not found", 10);
        this.waitForElementPresent(CONFIRM_YOU_EXCHANGE, "You exchange field not found", 10);
        this.waitForElementPresent(CONFIRM_YOU_GET, "You get field not found", 10);
        this.waitForElementPresent(CONFIRM_RATE, "Rate field not found", 10);
        this.waitForElementPresent(CONFIRM_EXCHANGE_FEE, "Exchange fee field not found", 10);
        this.waitForElementPresent(CONFIRM_TOTAL_AMOUNT, "Total amount field not found", 10);

        String textAttribute = Platform.getInstance().isAndroid() ? "text" : "name";

        WebElement fromAccountElement = this.waitForElementPresent(
                "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[1]",
                "From Account value not found", 10);
        String fromAccountText = fromAccountElement.getAttribute(textAttribute);

        WebElement youExchangeElement = this.waitForElementPresent(
                "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[2]",
                "You exchange value not found", 10);
        String youExchangeText = youExchangeElement.getAttribute(textAttribute);

        WebElement youGetElement = this.waitForElementPresent(
                "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[3]",
                "You get value not found", 10);
        youGetElement.getAttribute(textAttribute);

        WebElement rateElement = this.waitForElementPresent(
                "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[4]",
                "Rate value not found", 10);
        String rateText = rateElement.getAttribute(textAttribute);

        WebElement exchangeFeeElement = this.waitForElementPresent(
                "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[5]",
                "Exchange fee value not found", 10);
        String exchangeFeeText = exchangeFeeElement.getAttribute(textAttribute);

        WebElement totalAmountElement = this.waitForElementPresent(
                "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[6]",
                "Total amount value not found", 10);
        String totalAmountText = totalAmountElement.getAttribute(textAttribute);

        boolean accountMatches = fromAccountText.contains(accountNumber) ||
                fromAccountText.equals(accountNumber + " (GBP)") ||
                fromAccountText.equals(accountNumber);
        assert accountMatches :
                String.format("From Account is incorrect. Expected to contain: %s, but got: %s", accountNumber, fromAccountText);

        String expectedYouExchange = "£" + enteredAmount + ".00";
        assert youExchangeText.equals(expectedYouExchange) :
                String.format("You exchange amount is incorrect. Expected: %s, but got: %s", expectedYouExchange, youExchangeText);

        assert rateText.equals(exchangeRate) :
                String.format("Exchange rate is incorrect. Expected: %s, but got: %s", exchangeRate, rateText);

        double youExchangeValue = extractAmountFromText(youExchangeText);
        double exchangeFeeValue = extractAmountFromText(exchangeFeeText);
        double totalAmountValue = extractAmountFromText(totalAmountText);
        double expectedTotal = youExchangeValue + exchangeFeeValue;

        assert Math.abs(totalAmountValue - expectedTotal) < 0.01 :
                String.format("Total amount calculation is incorrect. Expected: %.2f, but got: %.2f", expectedTotal, totalAmountValue);

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

    private void clearInputFieldFromLeft(WebElement field) {
        field.click();

        String textByGetText = field.getText();
        String textByValue = field.getAttribute("value");
        String textByLabel = field.getAttribute("label");

        System.out.println("Text by getText(): " + textByGetText);
        System.out.println("Text by value attribute: " + textByValue);
        System.out.println("Text by label attribute: " + textByLabel);

        String actualText = textByValue != null && !textByValue.isEmpty() ? textByValue : textByGetText;
        if (actualText == null || actualText.isEmpty()) {
            actualText = textByLabel;
        }

        if (actualText == null || actualText.isEmpty()) {
            System.out.println("Field is already empty.");
            return;
        }

        System.out.println("Text to clear: " + actualText);

        try {
            JavascriptExecutor js = driver;
            js.executeScript("arguments[0].value = '';", field);

            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", field);
            js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", field);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String checkText = field.getAttribute("value");
            if (checkText == null) checkText = field.getText();

            if (checkText == null || checkText.isEmpty()) {
                System.out.println("Successfully cleared using JavaScript");
                return;
            }
        } catch (Exception e) {
            System.out.println("Method 1 (JavaScript) failed: " + e.getMessage());
        }

        try {
            field.click();
            field.sendKeys(Keys.chord(Keys.COMMAND, "a"));
            field.sendKeys(Keys.DELETE);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String checkText = field.getAttribute("value");
            if (checkText == null) checkText = field.getText();

            if (checkText == null || checkText.isEmpty()) {
                System.out.println("Successfully cleared using select all + delete");
                return;
            }
        } catch (Exception e) {
            System.out.println("Method 2 (Select All + Delete) failed: " + e.getMessage());
        }

        try {
            field.clear();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String checkText = field.getAttribute("value");
            if (checkText == null) checkText = field.getText();

            if (checkText == null || checkText.isEmpty()) {
                System.out.println("Successfully cleared using WebDriver clear()");
                return;
            }
        } catch (Exception e) {
            System.out.println("Method 3 (WebDriver clear) failed: " + e.getMessage());
        }

        try {
            field.click();
            field.sendKeys(Keys.chord(Keys.COMMAND, Keys.RIGHT));

            int length = actualText.length();
            for (int i = 0; i < length; i++) {
                field.sendKeys(Keys.BACK_SPACE);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            String checkText = field.getAttribute("value");
            if (checkText == null) checkText = field.getText();

            if (checkText == null || checkText.isEmpty()) {
                System.out.println("Successfully cleared using backspace from end");
                return;
            }
        } catch (Exception e) {
            System.out.println("Method 4 (Backspace from end) failed: " + e.getMessage());
        }

        String finalTextByValue = field.getAttribute("value");
        String finalTextByGetText = field.getText();
        String finalTextByLabel = field.getAttribute("label");

        System.out.println("Final check - value: " + finalTextByValue);
        System.out.println("Final check - getText: " + finalTextByGetText);
        System.out.println("Final check - label: " + finalTextByLabel);

        String finalText = finalTextByValue != null && !finalTextByValue.isEmpty() ? finalTextByValue : finalTextByGetText;
        if (finalText == null || finalText.isEmpty()) {
            finalText = finalTextByLabel;
        }

        assert (finalText == null || finalText.isEmpty()) : "The input field is not empty after clearing. Final value: " + finalText;
    }
}