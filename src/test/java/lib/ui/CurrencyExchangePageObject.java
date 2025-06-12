package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

abstract public class CurrencyExchangePageObject extends MainPageObject
{
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
            FEE_DETAILS_TOTAL_AMOUNT_VALUE;

    public CurrencyExchangePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void verifyExchangeButtonExists()
    {
        this.waitForElementPresent(EXCHANGE_BUTTON, "Exchange button not found", 10);
    }

    public void clickExchangeButton()
    {
        this.waitForElementAndClick(EXCHANGE_BUTTON, "Cannot click on Exchange button", 5);
    }

    public void verifyExchangePageTitle()
    {
        this.waitForElementPresent(EXCHANGE_PAGE_TITLE, "Exchange page title not found", 10);
    }

    public void verifyExchangeRateInfoExists()
    {
        this.waitForElementPresent(EXCHANGE_RATE_INFO, "Exchange rate info not found", 10);
    }

    public String getExchangeRate()
    {
    WebElement rateElement = this.waitForElementPresent(EXCHANGE_RATE_INFO, "Exchange rate not found", 10);
    return rateElement.getAttribute("text");
    }

    public void verifyFirstAmountFieldExists()
    {
        this.waitForElementPresent(FIRST_AMOUNT_FIELD, "First amount field not found", 10);
    }

    public void verifyFirstCurrencyIconExists()
    {
        this.waitForElementPresent(FIRST_CURRENCY_ICON, "First currency icon not found", 10);
    }

    public String getFirstCurrencyButtonText()
    {
        WebElement currencyButton = this.waitForElementPresent(
                FIRST_CURRENCY_BUTTON_GENERIC,
                "Currency selection button not found",
                10
        );
        return currencyButton.getAttribute("text");
    }

    public void verifyFirstCurrencyButtonExists(String currency)
    {
        String locator = FIRST_CURRENCY_BUTTON_TEMPLATE.replace("{CURRENCY}", currency);
        this.waitForElementPresent(locator, "Currency button " + currency + " not found", 10);
    }

    public void clickFirstCurrencyButton(String currency)
    {
        String locator = FIRST_CURRENCY_BUTTON_TEMPLATE.replace("{CURRENCY}", currency);
        this.waitForElementAndClick(locator, "Cannot click on currency button " + currency, 5);
    }

    public void verifyCurrencySelectionElements()
    {
        this.waitForElementPresent(CURRENCY_LETTER, "Currency icon in list not found", 10);
        this.waitForElementPresent(CURRENCY_LIST_TITLE, "Currency full name not found", 10);
        this.waitForElementPresent(CURRENCY_LIST_SUBTITLE, "Currency short name not found", 10);
        this.waitForElementPresent(CURRENCY_LIST_AMOUNT, "Currency amount not found", 10);
    }

    public void closeCurrencySelection()
    {
        this.waitForElementAndClick(CURRENCY_LIST_TITLE, "Cannot close currency selection", 5);
    }

    public void verifyFirstFieldBalance()
    {
        this.waitForElementPresent(BALANCE_TEMPLATE, "First field balance not found", 10);
    }

    public void verifyMaxButtonExists()
    {
        this.waitForElementPresent(MAX_BUTTON, "MAX button not found", 10);
    }

    public void clickMaxButtonAndClearAmount()
    {
        this.waitForElementAndClick(MAX_BUTTON, "Cannot click MAX button", 5);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement balanceElement = this.waitForElementPresent(
                BALANCE_TEMPLATE,
                "Account balance not found",
                10
        );
        String balanceText = balanceElement.getText();
        String balanceAmount = balanceText.substring(balanceText.indexOf(": ") + 2, balanceText.lastIndexOf(" "));
        balanceAmount = balanceAmount.replace(",", "");

        WebElement firstField = this.waitForElementPresent(
                FIRST_EDIT_AMOUNT,
                "First amount field not found",
                10
        );
        String inputAmount = firstField.getText();

        assert inputAmount != null && !inputAmount.isEmpty() : "Input field is empty after clicking MAX";
        assert inputAmount.equals(balanceAmount) :
                "Amount in input field (" + inputAmount + ") does not match account balance (" + balanceAmount + ")";

        firstField.click();
        int length = firstField.getText().length();
        for (int i = 0; i < length; i++) {
            firstField.sendKeys("\b");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void verifySecondAmountFieldExists()
    {
        this.waitForElementPresent(SECOND_AMOUNT_FIELD, "Second amount field not found", 10);
    }

    public void verifySecondCurrencyIconExists()
    {
        this.waitForElementPresent(SECOND_CURRENCY_ICON, "Second currency icon not found", 10);
    }

    public String getSecondCurrencyButtonText()
    {
        WebElement currencyButton = this.waitForElementPresent(
                SECOND_CURRENCY_BUTTON_GENERIC,
                "Second currency selection button not found",
                10
        );
        return currencyButton.getAttribute("text");
    }

    public void verifySecondCurrencyButtonExists(String currency)
    {
        String locator = SECOND_CURRENCY_BUTTON_TEMPLATE.replace("{CURRENCY}", currency);
        this.waitForElementPresent(locator, "Second currency button " + currency + " not found", 10);
    }

    public void clickSecondCurrencyButton(String currency)
    {
        String locator = SECOND_CURRENCY_BUTTON_TEMPLATE.replace("{CURRENCY}", currency);
        this.waitForElementAndClick(locator, "Cannot click on second currency button " + currency, 5);
    }

    public void verifySecondCurrencySelectionElements(String currency)
    {
        this.waitForElementPresent("xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/currencyLetter'])[1]", "Second currency icon in list not found", 10);
        this.waitForElementPresent("xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelListTitle']", "Second currency full name not found", 10);
        this.waitForElementPresent("xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelListSubTitle']", "Second currency short name not found", 10);
        this.waitForElementPresent("xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelListAmount']", "Second currency amount not found", 10);
    }

    public void closeSecondCurrencySelection()
    {
        this.waitForElementAndClick("xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/currencyLetter'])[1]", "Cannot close second currency selection", 5);
    }

    public void verifySecondFieldBalance()
    {
        this.waitForElementPresent(BALANCE_TEMPLATE, "Second field balance not found", 10);
    }

    public void verifyFeeInfoExists()
    {
        this.waitForElementPresent(FEE_INFO, "Fee information not found", 10);
    }

    public void verifyAccountToLabelExists()
    {
        this.waitForElementPresent(ACCOUNT_TO_LABEL, "Account to label not found", 10);
    }

    public void verifyAccountSelectionField()
    {
        this.waitForElementPresent(ACCOUNT_SELECTION_FIELD, "Account selection field not found", 10);
    }

    public void verifyAccountName()
    {
        this.waitForElementPresent(ACCOUNT_NAME, "Account name not found", 10);
    }

    public String getSelectedAccountNumber()
    {
        WebElement accountElement = this.waitForElementPresent(ACCOUNT_NAME, "Account name not found", 10);
        String accountText = accountElement.getAttribute("text");
        String[] parts = accountText.split(" ");
        return parts[0];
    }

    public String getAccountNumberFromModal()
    {
         WebElement accountElement = this.waitForElementPresent("xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelListTitle']", "Account name in modal not found", 10
    );
        String accountText = accountElement.getAttribute("text");
        return accountText.split(" ")[0];
    }

    public void verifyAccountAmount()
    {
        this.waitForElementPresent(ACCOUNT_AMOUNT, "Account amount not found", 10);
    }

    public void clickAccountSelection()
    {
        this.waitForElementAndClick(ACCOUNT_SELECTION_MODAL, "Cannot click on account selection", 5);
    }

    public void verifyAccountSelectionModal()
    {
        this.waitForElementPresent(ACCOUNT_ICON, "Account icon in modal not found", 10);
        this.waitForElementPresent("xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelListTitle']", "Account name in modal not found", 10);
        this.waitForElementPresent("xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelListSubTitle']", "Account amount in modal not found", 10);
    }

    public String getAccountName()
    {
        WebElement accountElement = this.waitForElementPresent(
                "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelListTitle']",
                "Account name not found",
                10
        );
        return accountElement.getAttribute("text");
    }

    public void selectAccount(String accountName)
    {
        String locator = "xpath://android.widget.TextView[@resource-id='com.crassula.demo:id/labelListTitle' and @text='" + accountName + "']";
        this.waitForElementAndClick(locator, "Cannot select account " + accountName, 5);
    }

    public void verifyExchangeButtonInForm()
    {
        this.waitForElementPresent(EXCHANGE_BUTTON_FORM, "Exchange button in form not found", 10);
    }

    public void verifyAccountButton()
    {
        this.waitForElementPresent(ACCOUNT_BUTTON, "Account selection button not found", 10);
    }

    public void clickFirstAmountField()
    {
        this.waitForElementAndClick(FIRST_EDIT_AMOUNT, "Cannot click on first amount field", 5);
    }

    public void enterAmount(String amount)
    {
        this.waitForElementAndSendKeys(FIRST_EDIT_AMOUNT, amount, "Cannot enter amount", 5);
    }

    public void verifyMinusSymbolInFirstField()
    {
        this.waitForElementPresent(MINUS_SYMBOL, "Minus symbol in first field not found", 10);
    }

    public void verifyPlusSymbolInSecondField()
    {
        this.waitForElementPresent(PLUS_SYMBOL, "Plus symbol in second field not found", 10);
    }

    public void clickFeeInfo()
    {
        this.waitForElementAndClick(FEE_INFO, "Cannot click on fee information", 5);
    }

    public void verifyFeeDetailsModal(String enteredAmount)
    {
        this.waitForElementPresent(FEE_DETAILS_YOU_EXCHANGE, "You exchange details not found", 10);
        this.waitForElementPresent(FEE_DETAILS_EXCHANGE_FEE, "Exchange fee details not found", 10);
        this.waitForElementPresent(FEE_DETAILS_TOTAL_AMOUNT, "Total amount details not found", 10);
        
        WebElement youExchangeElement = this.waitForElementPresent(
            "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[1]", 
            "You exchange amount not found", 10);
        String youExchangeText = youExchangeElement.getAttribute("text");
        
        WebElement exchangeFeeElement = this.waitForElementPresent(
            "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[2]", 
            "Exchange fee amount not found", 10);
        String exchangeFeeText = exchangeFeeElement.getAttribute("text");
        
        WebElement totalAmountElement = this.waitForElementPresent(
            "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[3]", 
            "Total amount not found", 10);
        String totalAmountText = totalAmountElement.getAttribute("text");
        
        String expectedYouExchange = "- £" + enteredAmount + ".00";
        assert youExchangeText.equals(expectedYouExchange) : 
            String.format("You exchange amount is incorrect. Expected: %s, but got: %s", expectedYouExchange, youExchangeText);
        
        double youExchangeValue = extractAmountFromText(youExchangeText);
        double exchangeFeeValue = extractAmountFromText(exchangeFeeText);
        double totalAmountValue = extractAmountFromText(totalAmountText);
        double expectedTotal = youExchangeValue + exchangeFeeValue;
        
        assert Math.abs(totalAmountValue - expectedTotal) < 0.01 :
            String.format("Total amount calculation is incorrect. Expected: %.2f, but got: %.2f", expectedTotal, totalAmountValue);
        
        System.out.println("Fee details verified successfully:");
        System.out.println("You exchange: " + youExchangeText);
        System.out.println("Exchange fee: " + exchangeFeeText);
        System.out.println("Total amount: " + totalAmountText);
    }

    public void closeFeeDetailsModal()
    {
        this.waitForElementAndClick(FEE_DETAILS_YOU_EXCHANGE, "Cannot close fee details modal", 5);
    }

    public void clickExchangeButtonInForm()
    {
        this.waitForElementAndClick(EXCHANGE_BUTTON_FORM, "Cannot click on Exchange button in form", 5);
    }

    public void verifyConfirmExchangeTitle()
    {
        this.waitForElementPresent(CONFIRM_EXCHANGE_TITLE, "Confirm exchange title not found", 10);
    }

public void verifyConfirmationDetails(String enteredAmount, String accountNumber, String exchangeRate)
{
    this.waitForElementPresent(CONFIRM_FROM_ACCOUNT, "From Account field not found", 10);
    this.waitForElementPresent(CONFIRM_YOU_EXCHANGE, "You exchange field not found", 10);
    this.waitForElementPresent(CONFIRM_YOU_GET, "You get field not found", 10);
    this.waitForElementPresent(CONFIRM_RATE, "Rate field not found", 10);
    this.waitForElementPresent(CONFIRM_EXCHANGE_FEE, "Exchange fee field not found", 10);
    this.waitForElementPresent(CONFIRM_TOTAL_AMOUNT, "Total amount field not found", 10);
    
    WebElement fromAccountElement = this.waitForElementPresent(
        "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[1]", 
        "From Account value not found", 10);
    String fromAccountText = fromAccountElement.getAttribute("text");
    
    WebElement youExchangeElement = this.waitForElementPresent(
        "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[2]", 
        "You exchange value not found", 10);
    String youExchangeText = youExchangeElement.getAttribute("text");
    
    WebElement youGetElement = this.waitForElementPresent(
        "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[3]", 
        "You get value not found", 10);
    String youGetText = youGetElement.getAttribute("text");
    
    WebElement rateElement = this.waitForElementPresent(
        "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[4]", 
        "Rate value not found", 10);
    String rateText = rateElement.getAttribute("text");
    
    WebElement exchangeFeeElement = this.waitForElementPresent(
        "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[5]", 
        "Exchange fee value not found", 10);
    String exchangeFeeText = exchangeFeeElement.getAttribute("text");
    
    WebElement totalAmountElement = this.waitForElementPresent(
        "xpath:(//android.widget.TextView[@resource-id='com.crassula.demo:id/labelValue'])[6]", 
        "Total amount value not found", 10);
    String totalAmountText = totalAmountElement.getAttribute("text");
    
    System.out.println("=== DEBUG: Confirmation details ===");
    System.out.println("From Account: " + fromAccountText);
    System.out.println("You exchange: " + youExchangeText);
    System.out.println("You get: " + youGetText);
    System.out.println("Rate: " + rateText);
    System.out.println("Exchange fee: " + exchangeFeeText);
    System.out.println("Total amount: " + totalAmountText);
    System.out.println("Expected account: " + accountNumber);
    System.out.println("Expected rate: " + exchangeRate);
    System.out.println("=== END DEBUG ===");
    

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

private double extractRateFromText(String rateText)
{
    try {
        String[] parts = rateText.split(" = €");
        if (parts.length == 2) {
            return Double.parseDouble(parts[1]);
        }
        parts = rateText.split("=");
        if (parts.length == 2) {
            String ratePart = parts[1].trim();
            return Double.parseDouble(ratePart.replaceAll("[^0-9.]", ""));
        }
        throw new RuntimeException("Cannot extract rate from text: " + rateText);
    } catch (Exception e) {
        throw new RuntimeException("Error parsing rate from text: " + rateText, e);
    }
}

private double extractAmountFromText(String text)
{
    String numericPart = text.replaceAll("[^0-9.]", "");
    return Double.parseDouble(numericPart);
}

    public void clickConfirmExchange()
    {
        this.waitForElementAndClick(CONFIRM_BUTTON, "Cannot click on Confirm Exchange button", 5);
    }

    public void verifySuccessTitle()
    {
        this.waitForElementPresent(SUCCESS_TITLE, "Success title not found", 10);
    }

    public void verifySuccessMessage()
    {
        this.waitForElementPresent(SUCCESS_MESSAGE, "Success message not found", 10);
    }

    public void verifyExchangeAmount()
    {
        this.waitForElementPresent(EXCHANGE_AMOUNT, "Exchange amount not found", 10);
    }

    public void verifyTransactionDetailsTitle()
    {
        this.waitForElementPresent(TRANSACTION_DETAILS_TITLE, "Transaction details title not found", 10);
    }

    public void clickTransactionDetails()
    {
        this.waitForElementAndClick(TRANSACTION_DETAILS_TITLE, "Cannot click on transaction details", 5);
    }

    public void clickBackToHome()
    {
        this.waitForElementAndClick(BACK_TO_HOME_BUTTON, "Cannot click on Back to home button", 5);
    }
}