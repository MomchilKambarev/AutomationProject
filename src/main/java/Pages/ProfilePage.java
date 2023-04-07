package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProfilePage {

    WebDriver driver;

    WebDriverWait wait;

    @FindBy(xpath = "//*[contains(@class, 'fas fa-user-edit ng-star-inserted')]")
    WebElement editProfileButton;

    @FindBy(tagName = "h2")
    WebElement profileName;

    @FindBy(css = "app-post")
    List<WebElement> existingPosts;

    @FindBy(xpath = "//*[contains(@class, 'post-img')]")
    WebElement postedImage;

    @FindBy(xpath = "//a[contains(text(),'Delete post')]")
    WebElement deletePostButton;

    @FindBy(xpath = "//button[contains(text(),'Yes')]")
    WebElement confirmDeleteButton;


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
    public int getExistingPostsCount() {
        return existingPosts.size();
    }

    public String getElementText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public void openPostByIndex(int index) {
        getElementText(existingPosts.get(index));
    }

    public void clickPostedImage() {
        postedImage.click();
    }

    public void clickDeletePostButton() {
        deletePostButton.click();
    }

    public void clickConfirmDeleteButton() {
        confirmDeleteButton.click();
    }

}
