package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Date;

public class RegisterPage {
    private WebDriver driver;
    private final String URL = "http://training.skillo-bg.com/users/register";
    WebDriverWait wait;

    Date date = new Date();
    String randomString = String.valueOf(date.getTime());
    String newRandomString = randomString.substring(4,randomString.length()-0);
    public String username = "momc" + newRandomString;
    public String emailName = "momc" + newRandomString + "@abv.bg";

    @FindBy(xpath = "//input[@placeholder='Username']")
    WebElement usernameField;

    @FindBy(xpath = "//input[@placeholder='email']")
    WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@placeholder='Confirm Password']")
    WebElement confirmPasswordField;

    @FindBy(id = "sign-in-button")
    WebElement signInButton;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        PageFactory.initElements(driver,this);
    }

    public void verifyUrl() {
        wait.until(ExpectedConditions.urlToBe(URL));
    }

    public void enterUsername() {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.sendKeys(username);
    }

    public void enterMail() {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(emailName);
    }

    public void enterPassword() {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys("test123");
    }

    public void enterConfirmPassword() {
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordField));
        confirmPasswordField.sendKeys("test123");
    }

    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }
}
