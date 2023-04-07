package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {

    private final WebDriver driver;

    private WebDriverWait wait;

    @FindBy(id = "homeIcon")
    WebElement logoButton;

    @FindBy(id = "nav-link-home")
    WebElement homeButton;

    @FindBy(id = "nav-link-login")
    WebElement loginButton;

    @FindBy(id = "nav-link-profile")
    WebElement profileButton;

    @FindBy(xpath = "//header/nav[1]/div[1]/div[1]/ul[2]/li[1]/a[1]/i[1]")
    WebElement logoutButton;


    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public void goToHomePage() {
        wait.until(ExpectedConditions.elementToBeClickable(homeButton));
        homeButton.click();
    }

    public void clickProfileButton() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton));
        profileButton.click();
    }

    public boolean visibilityOfProfileButton() {
        wait.until(ExpectedConditions.visibilityOf(profileButton));
        return true;
    }

    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }

}
