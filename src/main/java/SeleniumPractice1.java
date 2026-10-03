import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumPractice1 {

    public static void main(String[] args) {

        // 1. Launch Chrome
        WebDriver driver = new ChromeDriver();

        // 2. Create Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 3. Open SauceDemo
        driver.get("https://www.saucedemo.com/");

        // 4. Maximize browser
        driver.manage().window().maximize();

        // 5. Wait for Username field
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("user-name")
        )).sendKeys("standard_user");

        System.out.println("Username entered");

        // 6. Wait for Password field
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("password")
        )).sendKeys("secret_sauce");

        System.out.println("Password entered");

        // 7. Wait for Login button and click
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("login-button")
        )).click();

        System.out.println("Login button clicked");

        // 8. Wait until Inventory page is displayed
        wait.until(ExpectedConditions.urlContains("inventory.html"));

        System.out.println("Login Successful!");

        // 9. Keep browser open for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 10. Close browser
        driver.quit();
    }
}