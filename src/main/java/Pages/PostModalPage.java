package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PostModalPage {
    WebDriver driver;

    WebDriverWait wait;

    @FindBy(className = "post-user")
    WebElement usernameLink;
    @FindBy(tagName = "app-post-modal")
    WebElement modalDialog;

    @FindBy(xpath = "//textarea[@formcontrolname='publicInfo']")
    WebElement infoText;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    WebElement saveButton;

    public PostModalPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void waitForDialogToAppear() {
        wait.until(ExpectedConditions.visibilityOf(modalDialog));
    }
    public String getElementText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public String getPostUsername() {
        return getElementText(usernameLink);
    }

    public void deleteInfo(){
        infoText.clear();
    }

    public void changePublicInfo() {
        infoText.sendKeys("hellooouu");
    }

    public void clickSaveButton() {
        saveButton.click();
    }

}
