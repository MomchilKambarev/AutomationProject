package Tests;

import Pages.Header;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    private WebDriver driver;

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
    public void loginTest() {

        HomePage homePage = new HomePage(driver);
        homePage.openUrl();

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyURL();

        ProfilePage profilePage = new ProfilePage(driver);

        String headerText = loginPage.getSignInHeaderText();
        Assert.assertEquals(headerText,"Sign in", "URL is incorrect");

        loginPage.enterUsername("momchi123");

        loginPage.enterPassword("test123");

        loginPage.clickSignInButton();

        homePage.verifyUrl();

        header.clickProfileButton();
        Assert.assertEquals("momchi123","momchi123","Username is not correct");

        header.clickLogoutButton();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
