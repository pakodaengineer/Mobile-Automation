package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    AppiumDriver<?> driver;

    @iOSXCUITFindBy(id = "JDLogoIcn")
    private IOSElement jdLogo;

    @iOSXCUITFindBy(id = "Flights")
    private IOSElement flightsButton;

    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Travel\"")
    private IOSElement travelButton;

    public HomePage(AppiumDriver<?> driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean homePageIsDisplayed() {
        return isDisplayed(jdLogo, 2);
    }

    public void clickOnTravel() {
        sleepSafely(100);
        travelButton.click();
        sleepSafely(50);
        flightsButton.click();
    }
}
