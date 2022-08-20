package Tests;

import Pages.FlightBookPage;
import Pages.HomePage;
import Pages.LandingPage;
import Tests.AbstractBaseTests.TestBase;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.net.MalformedURLException;

@CucumberOptions(
        strict = true,
        monochrome = true,
        features = "classpath:LoginTest",
        plugin = {"pretty"}
)

public class MyStepdefs extends TestBase {

    public static AppiumDriver<MobileElement> mobileDriver;
    protected LandingPage landingPage;
    protected HomePage homePage;
    protected FlightBookPage flightBookPage;

    void setUp() throws MalformedURLException {
        mobileDriver = (AppiumDriver<MobileElement>) setUpTheDriver();
        landingPage = new LandingPage(mobileDriver);
        homePage = new HomePage(mobileDriver);
        flightBookPage = new FlightBookPage(mobileDriver);

    }

    @Given("^\\[SetUp\\] Launch the JustDail App$")
    public void setupLaunchTheJustDailApp() throws MalformedURLException {
        setUp();
        if (!homePage.homePageIsDisplayed()) {
            landingPage.goToTheHomePage();
        }
    }

    @Then("^Go the the Flight section and choose the round trip$")
    public void goTheTheFlightSection() {
        homePage.clickOnTravel();
        flightBookPage.clickOnRoundTrip();
    }

    @And("^Close the app$")
    public void closeTheApp() {
        tearDownAppium();
    }

    @And("^Enter the Source and Designation$")
    public void enterTheSourceAndDesignation() {
        flightBookPage.enterTheSource("BLR");
        flightBookPage.enterTheDesignation("MAA");
    }


    @Then("^Enter the Departure data of Tomorrow and Return date of three days from today$")
    public void enterTheDepartureDataOfTomorrowAndReturnDateOfThreeDaysFromToday() {
        flightBookPage.enterTheDepartureDate();
        flightBookPage.enterTheReturnDate();
    }

    @And("^Click on the Continue Button and Select the flights$")
    public void clickOnTheContinueButton() {
        flightBookPage.clickTheSearchButton();
        flightBookPage.selectTheFlights();

    }

    @Then("^Click on Book and then Continue$")
    public void clickOnBookAndThenContinue() {
        flightBookPage.clickBook();
        flightBookPage.clickSecureAndContinue();

    }

    @And("^Verify the Login Screen appeared$")
    public void verifyTheLoginScreenAppeared() {
        landingPage.verifyLogInScreen();
    }
}
