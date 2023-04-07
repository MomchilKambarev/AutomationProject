package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    WebDriver driver;

    WebDriverWait wait;

    @FindBy(tagName = "i")
    WebElement editProfileButton;

    @FindBy(tagName = "h2")
    WebElement profileName;


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(4));
        PageFactory.initElements(driver, this);
    }

    public void clickEditProfileButton() {
        wait.until(ExpectedConditions.elementToBeClickable(editProfileButton));
        editProfileButton.click();
    }

    public String verifyUsername() {
        wait.until(ExpectedConditions.visibilityOf(profileName));
        return profileName.getText();
    }


}
