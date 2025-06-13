package lib.ui;

import io.appium.java_client.AppiumDriver;

public class ValidationHelperObject extends MainPageObject
{
    protected static String
            INSUFFICIENT_FUNDS_ERROR,
            VALIDATION_ERROR_CONTAINER,
            GENERAL_ERROR_MESSAGE;

    public ValidationHelperObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void verifyInsufficientFundsError()
    {
        this.waitForElementPresent(
                INSUFFICIENT_FUNDS_ERROR,
                "Insufficient funds error message was not displayed",
                10
        );
    }

    public void verifyInsufficientFundsErrorNotPresent()
    {
        this.assertElementNotPresent(
                INSUFFICIENT_FUNDS_ERROR,
                "Insufficient funds error should not be displayed"
        );
    }

    public void verifyValidationError(String expectedErrorMessage)
    {
        String dynamicErrorLocator = GENERAL_ERROR_MESSAGE.replace("{ERROR_TEXT}", expectedErrorMessage);
        this.waitForElementPresent(
                dynamicErrorLocator,
                "Expected validation error message '" + expectedErrorMessage + "' was not displayed",
                10
        );
    }

    public void enterAmountGreaterThanBalance(String amountFieldLocator, double amount)
    {
        String amountString = String.valueOf((int) amount);

        this.waitForElementAndClick(
                amountFieldLocator,
                "Cannot click on the amount field",
                5
        );

        this.clearField(amountFieldLocator);

        for (char digit : amountString.toCharArray()) {
            this.waitForElementAndSendKeys(
                    amountFieldLocator,
                    String.valueOf(digit),
                    "Cannot input character '" + digit + "' in the amount field",
                    5
            );
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearField(String fieldLocator) {}
}