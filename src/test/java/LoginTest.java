import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    // --------------------------------
    // Setup
    // --------------------------------
    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        // Explicit Wait - maximum 10 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open website
        driver.get("https://www.saucedemo.com/");

        pause();
    }

    // --------------------------------
    // Login Test
    // --------------------------------
    @Test
    public void validLoginTest() {

        // Wait for username field and enter username
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("user-name")
        )).sendKeys("standard_user");

        pause();

        // Wait for password field and enter password
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("password")
        )).sendKeys("secret_sauce");

        pause();

        // Wait for Login button and click
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("login-button")
        )).click();

        pause();

        // Wait for successful login
        wait.until(ExpectedConditions.urlContains("inventory.html"));

        pause();

        // Get current URL
        String currentUrl = driver.getCurrentUrl();

        // Verify login
        Assert.assertTrue(
                currentUrl != null && currentUrl.contains("inventory.html"),
                "Login failed"
        );

        System.out.println("Login Successful!");

        pause();
    }

    // --------------------------------
    // Close Browser
    // --------------------------------
    @AfterMethod
    public void tearDown() {

        pause();

        if (driver != null) {
            driver.quit();
        }
    }

    // --------------------------------
    // 2-second pause
    // --------------------------------
    private void pause() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}