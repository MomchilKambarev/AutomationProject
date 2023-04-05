package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class CreateNewPost {

    ChromeDriver driver;
    final String homepageUrl = "http://training.skillo-bg.com:4200/posts/all";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(homepageUrl);
    }
        public void uploadPic(File file) {
            String newFile = file.getAbsolutePath();
        }

        @Test
        public void createNewPost() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebDriverWait smallWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        System.out.println("Navigate to Login page by clicking Login button");
        WebElement loginButton = driver.findElement(By.id("nav-link-login"));
        loginButton.click();

        System.out.println("Validate the URL");
        String expectedUrl = "http://training.skillo-bg.com:4200/users/login";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        System.out.println("Validate the Sign in text");
        WebElement signInText = driver.findElement(By.xpath("//p[text()=\"Sign in\"]"));
        Assert.assertTrue(signInText.isDisplayed(),"Sign in not visible");

        System.out.println("Enter username");
        WebElement username = driver.findElement(By.id("defaultLoginFormUsername"));
        username.sendKeys("momchi123");

        System.out.println("Enter password");
        WebElement password = driver.findElement(By.id("defaultLoginFormPassword"));
        password.sendKeys("test123");

        System.out.println("CLick Sign in button");
        WebElement signInButton = driver.findElement(By.id("sign-in-button"));
        signInButton.click();

        System.out.println("Validate the url");
        expectedUrl = "http://training.skillo-bg.com:4200/posts/all";
        wait.until(ExpectedConditions.urlToBe(homepageUrl));
        Assert.assertEquals(expectedUrl, homepageUrl, "URL is invalid");

        System.out.println("Validate there is a Profile button in header");
        WebElement profileButton = driver.findElement(By.id("nav-link-profile"));
        smallWait.until(ExpectedConditions.visibilityOf(profileButton));

        System.out.println("Validate New Post button is present");
        WebElement newPost = driver.findElement(By.id("nav-link-new-post"));
        Assert.assertTrue(newPost.isDisplayed(),"The button is not visible");

        System.out.println("Click New Post button and verify URL");
        newPost.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4200/posts/create"));


        WebElement uploadFile = driver.findElement(By.cssSelector("input-file[type-'file']"));
        uploadFile.click();
        uploadFile.sendKeys((CharSequence) new File("src/main/resources/handshake.jpeg"));

        

    }


}
