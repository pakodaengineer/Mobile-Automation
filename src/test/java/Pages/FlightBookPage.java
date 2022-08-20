package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.util.Arrays;

public class FlightBookPage extends BasePage {
    AppiumDriver<?> driver;
    String depDateStr;
    By flightPage = MobileBy.iOSNsPredicateString("label == \"banner\"");
    By continueBTn = By.id("Continue");

    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Round Trip\"")
    private IOSElement roundTripButton;
    @iOSXCUITFindBy(id = "From")
    private IOSElement fromTab;
    @iOSXCUITFindBy(id = "To")
    private IOSElement toTab;

    @iOSXCUITFindBy(id = "Return")
    private IOSElement returnDate;

    @iOSXCUITFindBy(id = "Book")
    private IOSElement bookButton;

    @iOSXCUITFindBy(id = "Continue")
    private IOSElement continueButton;

    @iOSXCUITFindBy(id = "Departure")
    private IOSElement departureDate;

    @iOSXCUITFindBy(id = "Yes, secure my trip")
    private IOSElement secureBtn;

    @iOSXCUITFindBy(iOSNsPredicate = "value == \"Search\"")
    private IOSElement searchButton;

    public FlightBookPage(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickOnRoundTrip() {
        roundTripButton.click();
    }

    public void enterTheSource(String Code) {
        int getX = fromTab.getCenter().getX();
        int getY = fromTab.getCenter().getY();
        TouchAction tap1 = new TouchAction(driver);
        tap1.tap(PointOption.point(getX, getY)).perform();
        sleepSafely(50);
        searchButton.sendKeys(Code);
        sleepSafely(50);
        driver.findElementById(Code).click();
    }

    public void enterTheDesignation(String Code) {
        int getX = toTab.getCenter().getX();
        int getY = toTab.getCenter().getY();
        TouchAction tap1 = new TouchAction(driver);
        tap1.tap(PointOption.point(getX, getY)).perform();
        sleepSafely(50);
        searchButton.sendKeys(Code);
        sleepSafely(50);
        driver.findElementById(Code).click();
    }

    public void enterTheDepartureDate() {
        int getX = departureDate.getCenter().getX();
        int getY = departureDate.getCenter().getY();
        TouchAction tap1 = new TouchAction(driver);
        tap1.tap(PointOption.point(getX, getY)).perform();
        LocalDate depDate = LocalDate.now().plusDays(1);
//            LocalDate depDate= LocalDate.of(2022, 8, 30).plusDays(1);
        depDateStr = Arrays.asList(depDate.toString().split("-")).get(2);
        sleepSafely(100);
        String s = String.format("%d", Integer.parseInt(depDateStr));

        if (s.equals("1")) {
            driver.findElement(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == \"1\"`][2]")).click();
        } else {
            driver.findElement(MobileBy.iOSNsPredicateString("label == \"" + s + "\"")).click();
        }
    }

    public void enterTheReturnDate() {
        sleepSafely(50);
        LocalDate retDate = LocalDate.now().plusDays(3);
//        LocalDate retDate= LocalDate.of(2022, 8, 30).plusDays(3);
        String retDateStr = Arrays.asList(retDate.toString().split("-")).get(2);
        String s = String.format("%d", Integer.parseInt(retDateStr));

        if (Integer.parseInt(depDateStr) < Integer.parseInt(retDateStr)) {
            driver.findElement(MobileBy.iOSNsPredicateString("label == \"" + s + "\"")).click();
        } else {
            if (s.equals("1")) {
                driver.findElement(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == \"1\"`][3]")).click();
            } else {
                System.out.println(s);
                driver.findElement(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == \"" + s + "\"`][2]")).click();
            }
        }

    }

    public void clickTheSearchButton() {
        searchButton.click();
    }

    public void selectTheFlights() {
        waitForElement(driver, 5, flightPage);
        sleepSafely(400);
        IOSElement flight1 = (IOSElement) driver.findElementByXPath("//XCUIElementTypeOther[@name=\"Flights Booking Online – Lowest Rates on Flight Tickets – Justdial Travel\"]/XCUIElementTypeOther[4]/XCUIElementTypeOther");
        int f1X = flight1.getCenter().getX();
        int f1Y = flight1.getCenter().getY();
        TouchAction tap1 = new TouchAction(driver);
        tap1.tap(PointOption.point(f1X, f1Y)).perform();
        Dimension dims = driver.manage().window().getSize();
        int f2X= f1X+ (dims.width/2);
        tap1.tap(PointOption.point(f2X, f1Y)).perform();
    }

    public void clickBook(){bookButton.click();}

    public void clickSecureAndContinue(){
        waitForElement(driver, 5,continueBTn);
        sleepSafely(100);
        continueButton.click();
        sleepSafely(100);
        secureBtn.click();
        continueButton.click();

    }


}
