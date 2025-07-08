package base;

import utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public class BaseTest {

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

        ChromeOptions options = new ChromeOptions();

        // === Required for headless CI environments like GitHub Actions ===
        options.addArguments("--headless=new");                 // Use new headless mode (recommended for Chrome 109+)
        options.addArguments("--no-sandbox");                   // Disable sandbox for containerized environments
        options.addArguments("--disable-dev-shm-usage");        // Avoid /dev/shm crash in low-memory environments
        options.addArguments("--disable-gpu");                  // Optional: avoid GPU dependency
        options.addArguments("--remote-allow-origins=*");       // Optional: allow remote origins if needed

        WebDriver localDriver = new ChromeDriver(options);
        localDriver.manage().window().maximize();
        driver.set(localDriver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    @AfterSuite
    public void flushReports() {
        extent.flush();
    }

    public WebDriver getDriver() {
        return driver.get();
    }
}