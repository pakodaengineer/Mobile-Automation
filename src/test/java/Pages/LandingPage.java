package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LandingPage extends BasePage {

    AppiumDriver<?> driver;

    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Agree and Continue\"")
    private IOSElement agreeButton;

    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Maybe Later\"")
    private IOSElement laterButton;

    @iOSXCUITFindBy(id = "HomePage.png")
    private IOSElement homePage;

    @iOSXCUITFindBy(id = "AuthenticationVCView")
    private IOSElement authPage;

    @iOSXCUITFindBy(id = "AuthInputsViewPhoneTextField")
    private IOSElement authMobile;

    @iOSXCUITFindBy(id = "Allow While Using App")
    private IOSElement allowLocation;


    public LandingPage(AppiumDriver<?> driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void goToTheHomePage() {
        if (isDisplayed(homePage, 2)) {
            agreeButton.click();
        }
        if (isDisplayed(laterButton, 2)) {
            laterButton.click();
        }
        if (isDisplayed(allowLocation, 2)) {
            allowLocation.click();
        }
    }

    public void verifyLogInScreen() {

        Assert.assertTrue((isDisplayed(authPage, 2) && isDisplayed(authMobile, 2)));

    }
}
