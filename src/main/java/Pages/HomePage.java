package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    WebDriverWait wait;

    WebDriverWait longWait;
    private final String URL = "http://training.skillo-bg.com/users/login";

    @FindBy(xpath = "//*[contains(text(), 'Successful register')]")
    WebElement successfulRegistrationToast;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        longWait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    public void openUrl() {
        driver.get(URL);
    }

    public void verifyUrl() {
        wait.until(ExpectedConditions.urlToBe(URL));
    }

    public void getToastMessageSuccessfulRegister() {
        longWait.until(ExpectedConditions.visibilityOf(successfulRegistrationToast));
    }
}
