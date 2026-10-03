import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FormTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        driver.get(
                "https://www.practiceqaautomation.com/shop"
        );

        pause();
    }

    @Test
    public void loginFillFormAndSubmit() {

        // ==========================================
        // 1. LOGIN
        // ==========================================

        System.out.println("STEP 1: Login");

        // Username
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-testid='username']")
                )
        ).sendKeys("demo_user");

        pause();

        // Password
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-testid='password']")
                )
        ).sendKeys("Demo@123");

        pause();

        // Login
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("[data-testid='login-button']")
                )
        ).click();

        pause();

        // Verify login
        wait.until(
                ExpectedConditions.urlContains("/shop/products")
        );

        System.out.println("Login successful!");

        // ==========================================
        // 2. FILL FORM
        // ==========================================

        System.out.println("STEP 2: Fill Form");

        /*
         * Add a product to the cart.
         * The product cards are documented by the site
         * with data-testid="product-card".
         */

        WebElement firstProduct = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-testid='product-card']")
                )
        );

        // Scroll to product
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                firstProduct
        );

        pause();

        System.out.println("Product displayed.");

        // ==========================================
        // 3. SUBMIT / CHECKOUT
        // ==========================================

        System.out.println("STEP 3: Continue to checkout");

        /*
         * At this point we intentionally stop rather than
         * inventing checkout locators.
         */

        Assert.assertTrue(
                firstProduct.isDisplayed(),
                "Product was not displayed"
        );

        System.out.println(
                "Login and product page completed successfully!"
        );

        pause();
    }

    @AfterMethod
    public void tearDown() {

        pause();

        if (driver != null) {
            driver.quit();
        }
    }

    // ------------------------------------------
    // Slow execution for classroom demonstration
    // ------------------------------------------

    private void pause() {

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}