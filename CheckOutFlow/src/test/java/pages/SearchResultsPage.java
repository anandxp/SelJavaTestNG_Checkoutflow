package pages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchResultsPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "(//button[@class='bk-now'][normalize-space()='Reserve Now'])[1]")
    WebElement reserveNowButton;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchAndSelectParking(String url) {
        driver.navigate().to(url);
        wait.until(ExpectedConditions.elementToBeClickable(reserveNowButton)).click();
    }
}