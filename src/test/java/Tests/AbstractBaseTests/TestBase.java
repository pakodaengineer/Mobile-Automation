

package Tests.AbstractBaseTests;

import cucumber.api.testng.AbstractTestNGCucumberTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public abstract class TestBase extends AbstractTestNGCucumberTests {

    public static AppiumDriver<MobileElement> mobileDriver;


    public IOSDriver<?> setUpTheDriver() throws MalformedURLException {
        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        URL url = new URL(URL_STRING);
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone.*");
        capabilities.setCapability("appium:bundleId", "com.justdial.justdialjd");
        capabilities.setCapability("udid", "UDID");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformVersion", "15");
        capabilities.setCapability("autoAcceptAlerts", true);
        mobileDriver = new IOSDriver<>(url, capabilities);
        mobileDriver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
        return (IOSDriver<?>) mobileDriver;

    }

    public void tearDownAppium() {
        mobileDriver.quit();
    }



}