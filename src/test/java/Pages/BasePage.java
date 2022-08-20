

package Pages;


import com.google.common.collect.ImmutableList;
import io.appium.java_client.AppiumDriver;

import io.appium.java_client.AppiumFluentWait;
import io.appium.java_client.MobileElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public abstract class BasePage {
    private static final int KEYBOARD_ANIMATION_DELAY = 1000;
    private static final int XML_REFRESH_DELAY = 1000;


    protected final AppiumDriver driver;

    protected BasePage(AppiumDriver driver){
        this.driver = driver;
    }





    public void sleepSafely(final long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public boolean isDisplayed(MobileElement iosElement, int waitTime) {
        boolean fState = false;
        try {
            driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
            fState = iosElement.isDisplayed();
            driver.manage().timeouts().implicitlyWait(
                    50, TimeUnit.SECONDS);
        } catch (NoSuchElementException ignored) {
            driver.manage().timeouts().implicitlyWait(
                    50, TimeUnit.SECONDS);
        }
        return fState;
    }

    public void waitForElement(AppiumDriver<?> driver, int timeLimitInSeconds, By element) {
        Wait wait = new AppiumFluentWait(driver)
                .withTimeout(Duration.ofSeconds(timeLimitInSeconds))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void swipeScreen(String dir) {

        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 750; // ms

        int edgeBorder = 10;
        PointOption pointOptionStart, pointOptionEnd;


        Dimension dims = driver.manage().window().getSize();



        switch (dir) {
            case "DOWN":
                pointOptionStart = PointOption.point(dims.width / 2, dims.height / 3);
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case "UP":
                pointOptionStart = PointOption.point(dims.width / 2, dims.height / 10);
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release()
                    .perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }


        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

}

