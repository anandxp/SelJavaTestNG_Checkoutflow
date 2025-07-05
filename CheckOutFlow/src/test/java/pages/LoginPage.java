package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;


    @FindBy(id = "loginEmail_userForm_txtEmail")
    WebElement emailField;

    @FindBy(id = "loginEmail_userForm_txtPassword")
    WebElement passwordField;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/div[2]/button[2]")
    WebElement acceptCookiesButton;

    @FindBy(id = "login_button_Login_page")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public void login(String email, String password) throws InterruptedException {
        driver.get("https://staging.way.com/login");
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        passwordField.sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
}
