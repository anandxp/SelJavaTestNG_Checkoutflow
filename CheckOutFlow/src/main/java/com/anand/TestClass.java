package com.anand;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestClass {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            runTest(); // move your script logic to this method
            Thread.sleep(2 * 60 * 1000); // 5 minutes
        }
    }

    public static void runTest() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://staging.way.com/login");
        driver.findElement(By.id("loginEmail_userForm_txtEmail")).sendKeys("anandxp@outlook.com");
        driver.findElement(By.id("loginEmail_userForm_txtPassword")).sendKeys("12345678");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div[2]/button[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("login_button_Login_page")).click();
        Thread.sleep(5000);
        driver.navigate().to("https://staging.way.com/parking/32.731692/-117.193937/San-Diego-International-Airport-SAN/Airport");
        Thread.sleep(5500);
        driver.findElement(By.xpath("(//button[@class='bk-now'][normalize-space()='Reserve Now'])[1]")).click();
        Thread.sleep(5500);
        try {
            WebElement element = driver.findElement(By.id("checkoutBtn"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
            Thread.sleep(2500);
            driver.findElement(By.id("checkoutBtn")).click();
        }catch (ElementClickInterceptedException e) {
            System.out.println("Element not found or not clickable: " + e.getMessage());
        }
        Thread.sleep(13000);
        driver.close();
        driver.quit();
    }
}