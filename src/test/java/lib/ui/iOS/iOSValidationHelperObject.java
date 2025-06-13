package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.ValidationHelperObject;

public class iOSValidationHelperObject extends ValidationHelperObject {
    static {
        INSUFFICIENT_FUNDS_ERROR = "id:Login Successful"; // ID текст ошибки недостатка средств
        VALIDATION_ERROR_CONTAINER = "id:TO ACCOUNT"; // ID для контейнера ошибок
        GENERAL_ERROR_MESSAGE = "id:Exchange"; // Общая ошибка
    }

    public iOSValidationHelperObject(AppiumDriver driver) {
        super(driver);
    }
}