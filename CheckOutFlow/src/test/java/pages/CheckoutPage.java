package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.ElementClickInterceptedException;

public class CheckoutPage {

    WebDriver driver;

    @FindBy(id = "checkoutBtn")
    WebElement checkoutButton;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void completeCheckout() throws InterruptedException {
        try {
            Thread.sleep(10500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutButton);
            checkoutButton.click();
            Thread.sleep(2500);
            checkoutButton.click();
            Thread.sleep(25000);
        } catch (ElementClickInterceptedException e) {
            System.out.println("Element not clickable: " + e.getMessage());
        }
    }
}