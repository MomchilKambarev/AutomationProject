package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {

    private final WebDriver driver;

    private WebDriverWait wait;

    @FindBy(id = "homeIcon")
    WebElement logoButton;

    @FindBy(id = "nav-link-home");
    WebElement homeButton;

    @FindBy(id = "nav-link-login")
    WebElement loginButton;

    public BaseTest(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public void goToHome() {
        wait.until(ExpectedConditions.elementToBeClickable(homeButton));
        homeButton.click();
    }

}
