package Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    private final String URL = "http://training.skillo-bg.com/users/login";
    WebDriverWait wait;

    @FindBy(xpath = "//p[text()=\"Sign in\"]")
    WebElement signInHeader;

    @FindBy(id = "defaultLoginFormUsername")
    WebElement usernameField;

    @FindBy(id = "defaultLoginFormPassword")
    WebElement passwordField;

    @FindBy(id = "sign-in-button")
    WebElement signInButton;

    @FindBy(linkText = "Register")
    WebElement registerButton;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver,Duration.ofSeconds(4));
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
    }

    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }

    public String getSignInHeaderText() {
        wait.until(ExpectedConditions.visibilityOf(signInHeader));
        return signInHeader.getText();
    }

    public void verifyURL() {
        wait.until(ExpectedConditions.urlToBe(URL));
    }

    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
    }

}
