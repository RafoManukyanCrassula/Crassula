package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeoutInSeconds) {
        By by = getLocatorByString(locator);
        return waitForElementPresent(by, errorMessage, timeoutInSeconds);
    }

    public void waitForElementAndClick(String locator, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.click();
    }

    public void waitForElementAndSendKeys(String locator, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
    }

    public void waitForElementNotPresent(String locator, String errorMessage, long timeoutInSeconds) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(errorMessage + "\n");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected void clearInputField(WebElement field) {
        field.click();
        int length = field.getText().length();
        for (int i = 0; i < length; i++) {
            field.sendKeys("\b");
        }
    }

    protected void clearInputFieldFromLeft(WebElement field) {
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
                return;
            }
        } catch (Exception e) {
            System.out.println("Method 4 (Backspace from end) failed: " + e.getMessage());
        }

        String finalTextByValue = field.getAttribute("value");
        String finalTextByGetText = field.getText();
        String finalTextByLabel = field.getAttribute("label");

        String finalText = finalTextByValue != null && !finalTextByValue.isEmpty() ? finalTextByValue : finalTextByGetText;
        if (finalText == null || finalText.isEmpty()) {
            finalText = finalTextByLabel;
        }

        assert (finalText == null || finalText.isEmpty()) : "The input field is not empty after clearing. Final value: " + finalText;
    }

    protected void clickOutsideModal() {
        if (Platform.getInstance().isIOS()) {
            try {
                Dimension size = driver.manage().window().getSize();
                int centerX = size.width / 2;
                int centerY = size.height / 2;

                PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
                Sequence tap = new Sequence(finger, 1);

                tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, centerY));
                tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

                driver.perform(Arrays.asList(tap));
                Thread.sleep(1000);

            } catch (Exception e) {
                System.out.println("Failed to close modal with center tap: " + e.getMessage());

                try {
                    Dimension size = driver.manage().window().getSize();
                    int centerX = size.width / 2;
                    int centerY = size.height / 2;

                    Actions actions = new Actions(driver);
                    actions.moveToLocation(centerX, centerY).click().perform();
                    Thread.sleep(1000);

                } catch (Exception ex) {
                    System.out.println("Alternative center click also failed: " + ex.getMessage());
                }
            }
        }
    }

    public void swipeUpQuick() {
        var size = driver.manage().window().getSize();
        int x = size.getWidth() / 2, startY = (int) (size.getHeight() * 0.8), endY = (int) (size.getHeight() * 0.2);
        var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), x, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(swipe));
    }

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        while (!isElementLocatedOnTheScreen(locator)) {
            if (alreadySwiped > maxSwipes) {
                fail("Cannot find element by swiping up.\n" + errorMessage);
            }
            swipeUpQuick();
            alreadySwiped++;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        List<WebElement> elements = driver.findElements(getLocatorByString(locator));
        if (elements.isEmpty()) {
            return false;
        }
        int elementY = elements.get(0).getLocation().getY();
        int screenHeight = driver.manage().window().getSize().getHeight();
        return elementY > 0 && elementY < screenHeight;
    }

    public int getAmountOfElements(String locator) {
        By by = getLocatorByString(locator);
        List<WebElement> elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(String locator, String errorMessage) {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements > 0) {
            String defaultMessage = "An element '" + locator + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    private static By getLocatorByString(String locator_with_type) {
        if (locator_with_type == null) {
            throw new IllegalArgumentException("Locator with type cannot be null.");
        }
        String[] exploded_locator = locator_with_type.split(":", 2);

        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void swipeElementToLeft(String locator, String errorMessage) {
        WebElement element = waitForElementPresent(locator, errorMessage, 10);

        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        if (Platform.getInstance().isIOS()) {
            int swipeEndX = rightX - (int) (element.getSize().getWidth() * 0.4);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1)
                    .addAction(finger.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), rightX, middleY))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), swipeEndX, middleY))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(swipe));

        } else {
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1)
                    .addAction(finger.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), rightX, middleY))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), leftX, middleY))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(swipe));
        }
    }

    public String takeScreenshot(String name) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/src/test/screenshots/" + name + ".png";
        try {
            new File(System.getProperty("user.dir") + "/src/test/screenshots/").mkdirs();
            Files.copy(source.toPath(), new File(path).toPath());
            System.out.println("Screenshot saved to " + path);
        } catch (Exception e) {
            System.out.println("Something went wrong while taking screenshot: " + e.getMessage());
        }
        return path;
    }

    public static byte[] screenshot(String path) {
        byte[] bytes = new byte[0];

        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Something went wrong while taking screenshot: " + e.getMessage());
        }
        return bytes;
    }
}