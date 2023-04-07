package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class NewPostPage {
    private final String URL = "http://training.skillo-bg.com:4200/posts/create";
    private final WebDriver driver;

    WebDriverWait wait;

    @FindBy(css = ".image-preview")
    WebElement imagePreview;

    @FindBy(css = "input.input-lg")
    WebElement fileNameField;

    @FindBy(name = "caption")
    WebElement captionInput;

    @FindBy(id = "create-post")
    WebElement submitButton;

    @FindBy(css = "input.file[type='file']")
    WebElement fileUploadInput;

    public NewPostPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver,Duration.ofSeconds(4));
    }


    public void uploadImage(File file) {
        fileUploadInput.sendKeys(file.getAbsolutePath());
    }

    public void waitForImageToShow() {
        wait.until(ExpectedConditions.visibilityOf(imagePreview));
    }

    public String getImageFilename() {
        wait.until(ExpectedConditions.visibilityOf(fileNameField));
        return fileNameField.getAttribute("placeholder");
    }
    public void enterText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

    public void populateCaption(String captionText) {
        enterText(captionInput, captionText);
    }

    public void submitPost() {
        submitButton.click();
    }

}
