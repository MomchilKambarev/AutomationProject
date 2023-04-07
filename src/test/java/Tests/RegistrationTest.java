package Tests;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

public class RegistrationTest {

    private WebDriver driver;

    public static final String TEST_RESOURCES_DIR = "src" + File.separator + "test" + File.separator + "resources" + File.separator;

    public static final String SCREENSHOT_DIR = TEST_RESOURCES_DIR.concat("screenshots" + File.separator);

    @BeforeSuite
    public void prepareSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUpDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(4));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }
    @Test
    public void registrationTest() {

        HomePage homePage = new HomePage(driver);
        Header header = new Header(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        System.out.println("Opel URL");
        homePage.openUrl();

        System.out.println("Click Login button");
        header.clickLogin();

        System.out.println("Verify URL");
        homePage.verifyUrl();

        System.out.println("Click Register button");
        loginPage.clickRegisterButton();

        System.out.println("Verify URL");
        registerPage.verifyUrl();

        System.out.println("Enter input in username,mail,password and confirm password");
        registerPage.enterUsername();
        registerPage.enterMail();
        registerPage.enterPassword();
        registerPage.enterConfirmPassword();

        System.out.println("Click Sign in button");
        registerPage.clickSignInButton();

        System.out.println("Validate green toast message for successful registration");
        Assert.assertTrue(header.visibilityOfProfileButton(),"The button not visible");

        System.out.println("Click Profile button");
        header.clickProfileButton();

        System.out.println("Check username is correct");
        profilePage.verifyUsername();
        Assert.assertEquals(profilePage.verifyUsername(),registerPage.username,"Username is not correct");

        System.out.println("Click Logout button");
        header.clickLogoutButton();

    }

    @AfterMethod
    public void tearDown(ITestResult testResult) {
        makeScreenShot(testResult);
        if (driver != null) {
            driver.close();
        }
    }

    private void makeScreenShot(ITestResult testResult) {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String testName = testResult.getName();
            try {
                FileUtils.copyFile(screenshot, new File(SCREENSHOT_DIR.concat(testName).concat(".jpg")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
