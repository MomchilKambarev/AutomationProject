package Tests;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class ChangeProfileInfoTest {
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
    public void changeProfileInfoTest() {

        HomePage homePage = new HomePage(driver);
        homePage.openUrl();

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyURL();

        ProfilePage profilePage = new ProfilePage(driver);

        String headerText = loginPage.getSignInHeaderText();
        Assert.assertEquals(headerText,"Sign in", "URL is incorrect");

        loginPage.enterUsername("momchi321");

        loginPage.enterPassword("test123");

        loginPage.clickSignInButton();

        homePage.verifyUrl();

        header.clickProfileButton();
        Assert.assertEquals("momchi321","momchi321","Username is not correct");

        profilePage.clickEditProfileButton();

        System.out.println("Open modal and clear public info");
        PostModalPage postModalPage = new PostModalPage(driver);
        postModalPage.deleteInfo();

        System.out.println("Fill public info with new text");
        postModalPage.changePublicInfo();

        System.out.println("Save profile afte changes");
        postModalPage.clickSaveButton();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
