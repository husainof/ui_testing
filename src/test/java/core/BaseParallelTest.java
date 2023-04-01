package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseParallelTest {
    protected static final ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    @BeforeMethod
    public void initialBrowser() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        drivers.set(driver);
        BasePage.setDriver(driver);
    }
    public WebDriver getDriver() {
        return drivers.get();
    }
    @AfterMethod
    public void cleanupBrowser() {
        getDriver().close();
        getDriver().quit();
    }
}
