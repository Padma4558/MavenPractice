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

public class LoginAndCheckoutTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        // Open Chrome
        driver = new ChromeDriver();

        // Maximize browser
        driver.manage().window().maximize();

        // Explicit Wait
        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        // Open SauceDemo
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void loginFillFormSubmitAndVerify() {

        // ==================================================
        // STEP 1: LOGIN
        // ==================================================

        System.out.println("STEP 1: Login");

        // Username
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("user-name")
                )
        ).sendKeys("standard_user");

        pause();

        // Password
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("password")
                )
        ).sendKeys("secret_sauce");

        pause();

        // Login button
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("login-button")
                )
        ).click();

        pause();

        // Verify login
        wait.until(
                ExpectedConditions.urlContains("inventory.html")
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "Login failed"
        );

        System.out.println("Login Successful!");


        // ==================================================
        // STEP 2: ADD PRODUCT TO CART
        // ==================================================

        System.out.println("STEP 2: Adding product to cart");

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("add-to-cart-sauce-labs-backpack")
                )
        ).click();

        pause();

        System.out.println("Product added to cart");


        // ==================================================
        // STEP 3: OPEN CART
        // ==================================================

        System.out.println("STEP 3: Opening cart");

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.className("shopping_cart_link")
                )
        ).click();

        pause();


        // ==================================================
        // STEP 4: CLICK CHECKOUT
        // ==================================================

        System.out.println("STEP 4: Clicking Checkout");

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("checkout")
                )
        ).click();

        pause();


        // ==================================================
        // STEP 5: FILL CHECKOUT FORM
        // ==================================================

        System.out.println("STEP 5: Filling checkout form");


        // ---------- First Name ----------

        System.out.println("Entering First Name");

        WebElement firstName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("first-name")
                )
        );

        firstName.sendKeys("Padma");

        pause();


        // ---------- Last Name ----------

        System.out.println("Entering Last Name");

        WebElement lastName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("last-name")
                )
        );

        lastName.sendKeys("Bathula");

        pause();


        // ---------- Postal Code ----------

        System.out.println("Entering Postal Code");

        WebElement postalCode = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("postal-code")
                )
        );

        postalCode.sendKeys("560035");

        pause();

        System.out.println("Form filled successfully");


        // ==================================================
        // STEP 6: SCROLL DOWN TO CONTINUE
        // ==================================================

        System.out.println("STEP 6: Scrolling down");

        WebElement continueButton = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id("continue")
                )
        );

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({" +
                        "behavior: 'smooth'," +
                        "block: 'center'" +
                        "});",
                continueButton
        );

        pause();


        // ==================================================
        // STEP 7: CLICK CONTINUE
        // ==================================================

        System.out.println("STEP 7: Clicking Continue");

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        continueButton
                )
        ).click();

        pause();


        // ==================================================
        // STEP 8: CLICK FINISH
        // ==================================================

        System.out.println("STEP 8: Clicking Finish");

        WebElement finishButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("finish")
                )
        );

        // Scroll to Finish button
        js.executeScript(
                "arguments[0].scrollIntoView({" +
                        "behavior: 'smooth'," +
                        "block: 'center'" +
                        "});",
                finishButton
        );

        pause();

        finishButton.click();

        pause();


        // ==================================================
        // STEP 9: VERIFY SUCCESS
        // ==================================================

        System.out.println("STEP 9: Verifying order");

        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("complete-header")
                )
        );

        String actualMessage =
                successMessage.getText();

        System.out.println(
                "Success Message: " + actualMessage
        );

        Assert.assertEquals(
                actualMessage,
                "Thank you for your order!",
                "Order was not completed successfully"
        );

        System.out.println(
                "TEST PASSED: Order completed successfully!"
        );

        pause();
    }


    // ==================================================
    // SLOW DOWN EXECUTION FOR DEMONSTRATION
    // ==================================================

    private void pause() {

        try {

            Thread.sleep(2000);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }


    // ==================================================
    // CLOSE BROWSER
    // ==================================================

    @AfterMethod
    public void tearDown() {

        pause();

        if (driver != null) {
            driver.quit();
        }
    }
}
