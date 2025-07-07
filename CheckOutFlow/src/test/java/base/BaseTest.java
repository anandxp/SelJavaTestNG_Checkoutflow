package base;

import utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {

    // Thread-safe WebDriver and ExtentTest
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void setUpReport() {
        extent = ExtentReportManager.getExtentReports();
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver localDriver = new ChromeDriver();
        localDriver.manage().window().maximize();
        driver.set(localDriver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();  // Remove thread-local reference
        }
    }

    @AfterSuite
    public void flushReports() {
        extent.flush();
    }

    // Provide global access to the thread-safe WebDriver instance
    public WebDriver getDriver() {
        return driver.get();
    }
}