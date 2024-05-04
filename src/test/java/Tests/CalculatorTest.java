package Tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorTest {

    AppiumDriver<AndroidElement> driver;


@Before
    public void setup() throws MalformedURLException {

    //  1) create an object of DesiredCapabilities class
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    // 2) set up which framework to use for automation
    desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
    // or
    // desiredCapabilities.setCapability("automationName","UiAutomator2"); // same with above


    // 3)  setup which platform of device  we use
    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
    // or
    // desiredCapabilities.setCapability("platformName", "Android");


   // 4) specify the version of platform (we specified the version of android)
    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"7.0");
    // or
    //desiredCapabilities.setCapability("platformVersion","7.0");

    // 5) specify the device name
    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel_2");
    //or
   // desiredCapabilities.setCapability("deviceName","pixel2");

   // set up the app or appActivity and appPackage name  ;  Options-->  option1: new app; option2: preinstalled apps, e.g.calculator
   // appActivity --> path to the main class that runs the program (com.google.calculator.Main)
    // appPackage --> path that leads to the main class (com.google.calculator)
    // 6) set up the appActivity and appPackage
    desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.android.calculator2");
    desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.android.calculator2.Calculator");


    // appium driver --> appium server --> device
    // specify address of appium server
    // if appium server runs on the same computer, address is always "localhost"
    // sometimes you will see "0.0.0.0" or "127.0.0.1" instead of "localhost"
    // 7) create an object of URL and setup
    URL url = new URL("http://localhost:4723/wd/hub");


     //  WebDriver driver = new ChromeDriver();
     //  WebDriver driver = new AndroidDriver<>(url, desiredCapabilities);
    // 8) creat a driver instance and setup
      driver= new AndroidDriver(url, desiredCapabilities);
    // WebDriver --> RemoteWebDriver --> DefaultGenericMobileDriver --> AppiumDriver --> AndroidDriver
    // Question: what are the relations in between the ChromeDriver and AppiumDriver?
    // They are cousins. both of them are children of WebDriver.

}


@Test
    public void firstTest(){

    // find an element
    // WebElement btn2 = driver.findElement(By.id("com.android.calculator2:id/digit_2\n"));
    // IOSElement btn2 = driver.findElement(By.id("com.android.calculator2:id/digit_2\n"));
    AndroidElement btn2 = driver.findElement(By.id("com.android.calculator2:id/digit_2"));
    AndroidElement plus = driver.findElement(MobileBy.AccessibilityId("plus"));
    AndroidElement equal = driver.findElement(MobileBy.AccessibilityId("equals"));
    //AndroidElement equalB = driver.findElementByAccessibilityId("equal");

    // what if we need to wait for visibility, clickability ...
    WebDriverWait wait = new WebDriverWait(driver,20);
    wait.until(ExpectedConditions.elementToBeClickable(btn2));

    // how can we tap instead of click?
    //btn2.click();
    TouchAction touchAction = new TouchAction(driver);
    touchAction.tap(new TapOptions().withElement(new ElementOption().withElement(btn2))).perform();


    plus.click();
    btn2.click();
    equal.click();
    AndroidElement result = driver.findElement(MobileBy.id("com.android.calculator2:id/result"));
    String actualResult = result.getText();
    String expectedResult = "4";
    Assert.assertEquals(actualResult, expectedResult);

}

@After
    public void tearDown(){
    driver.closeApp();
}





}
