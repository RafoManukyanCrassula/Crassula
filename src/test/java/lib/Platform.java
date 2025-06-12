package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class Platform {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String APPIUM_URL = "http://127.0.0.1:4723";

    private static Platform instance;

    private Platform() {}

    public static Platform getInstance()
    {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public static AppiumDriver getDriver() throws Exception
    {
        URL URL = new URL(APPIUM_URL);
        Platform platform = Platform.getInstance();
        if (platform.isAndroid()) {
            return new AndroidDriver(URL, platform.getCapabilitiesForAndroid());
        } else if (platform.isIOS()) {
            return new IOSDriver(URL, platform.getCapabilitiesForIOS());
        } else {
            throw new Exception("Cannot detect type of the Driver. Platform value: " + platform.getPlatformName());
        }
    }

    public boolean isAndroid()
    {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS()
    {
        return isPlatform(PLATFORM_IOS);
    }

    private DesiredCapabilities getCapabilitiesForAndroid()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","12.0");
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("appPackage","com.crassula.demo");
        capabilities.setCapability("appActivity","com.cratech.crassula.ui.v2.main.MainActivity");
        capabilities.setCapability("app","/Users/rafomanukyan/Desktop/Crassula/app/crassula.apk");
        capabilities.setCapability("hideKeyboard", true);
        capabilities.setCapability("autoHideKeyboard", true);

        return capabilities;
    }

    private DesiredCapabilities getCapabilitiesForIOS()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("deviceName","iPhone 16 Plus");
        capabilities.setCapability("platformVersion","18.4");
        capabilities.setCapability("automationName","XCUITest");
        capabilities.setCapability("app", "/Users/rafomanukyan/Desktop/Crassula/app/Crassula.app");
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("autoAcceptAlertsDelay", 5000);

        return capabilities;
    }

    private boolean isPlatform(String my_platform)
    {
        String platform = System.getenv("PLATFORM");
        return my_platform.equals(platform);
    }

    private String getPlatformName()
    {
        return System.getenv("PLATFORM");
    }
}