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
import java.time.Duration;
import java.util.Date;

public class RegistrationTest {

    ChromeDriver driver;
    String homepageUrl = "http://training.skillo-bg.com:4200/posts/all";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(homepageUrl);
    }
    @Test
    public void registrationTest() {

        Date date = new Date();
        String randomString = String.valueOf(date.getTime());
        String newRandomString = randomString.substring(4,randomString.length()-0);

        String username = "momc" + newRandomString;
        String emailName = "momc" + newRandomString + "@abv.bg";

        System.out.println(username);
        System.out.println(emailName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebDriverWait smallWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        System.out.println("Navigate to Login page");
        WebElement loginButton = driver.findElement(By.id("nav-link-login"));
        loginButton.click();

        System.out.println("Validate URL");
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4200/users/login"));

        System.out.println("Click on Register button");
        WebElement registerButton = driver.findElement(By.xpath(("//a[text()='Register']")));
        registerButton.click();

        System.out.println("Enter correct username,email,password and confirm password");
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        usernameField.sendKeys((username));

        WebElement email = driver.findElement(By.xpath("//input[@placeholder='email']"));
        email.sendKeys((emailName));

        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        password.sendKeys("test123");

        WebElement confirmPassword = driver.findElement(By.xpath("//input[@placeholder='Confirm Password']"));
        confirmPassword.sendKeys("test123");

        WebElement signInButton = driver.findElement(By.id("sign-in-button"));
        smallWait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();

        System.out.println("Validate green toast message");
        WebElement toastMessage = driver.findElement(By.xpath("//*[contains(text(), 'Successful register')]"));
        wait.until(ExpectedConditions.invisibilityOf(toastMessage));

        System.out.println("Click Profile button");
        WebElement profilebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-link-profile")));
        profilebutton.click();

        System.out.println("Check username is correct");
        WebElement nameOfUser = driver.findElement(By.tagName("h2"));
        wait.until(ExpectedConditions.visibilityOf(nameOfUser));
        Assert.assertEquals(nameOfUser.getText(), username , "username is not correct");

        System.out.println("Logout");
        WebElement logoutButton = wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/nav[1]/div[1]/div[1]/ul[2]/li[1]/a[1]/i[1]"))));
        logoutButton.click();

        System.out.println("Verify URL after Logout");
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4200/users/login"));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://training.skillo-bg.com:4200/users/login", actualUrl);
    }
}
