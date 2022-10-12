package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

public class BaseTest {
    public static WebDriver driver;
    public static String url;
    public static DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    @BeforeTest
    public void setup() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        url ="https://www.belgenet.com.tr/";
        driver.get(url);
    }

    @AfterTest
    public void killServer() throws InterruptedException {
        driver.quit();
        try {
            Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void lutfenBekleyiniz() throws Exception {
        Thread.sleep(500);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
        By lutfenBekleyiniz = By.id("bekleyinizStatusDialog");
        boolean disp = driver.findElement(lutfenBekleyiniz).isDisplayed();
        if (disp) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(lutfenBekleyiniz));
            Thread.sleep(500);
        }
    }
}
