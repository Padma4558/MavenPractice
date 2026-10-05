import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleLoginTest {

    public static void main(String[] args) {

        // 1. Open Chrome
        WebDriver driver = new ChromeDriver();

        // 2. Launch URL
        driver.get("https://practicetestautomation.com/practice-test-login/");

        // 3. Maximize browser
        driver.manage().window().maximize();

        // 4. Enter username
        driver.findElement(By.id("username"))
                .sendKeys("student");

        // 5. Enter password
        driver.findElement(By.id("password"))
                .sendKeys("Password123");

        // 6. Scroll down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400);");

        // 7. Find Submit button
        WebElement submitButton =
                driver.findElement(By.id("submit"));

        // 8. Click Submit
        submitButton.click();

        // 9. Wait so we can see the result
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 10. Print current URL
        System.out.println("Current URL: "
                + driver.getCurrentUrl());

        // 11. Close browser
        driver.quit();
    }
}