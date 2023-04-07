package Tests;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class NewPostTest {
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
    @DataProvider(name = "getData")
    public Object[][] getData() {
        return new Object[][] {
              {"momchi321", "test123", new File("src/main/resources/handshake.jpeg"),"some caption text"}
        };
    }

    @Test(dataProvider = "getData")
    public void createNewPostTest(String username, String password, File file, String captionText) {

        System.out.println("Open page");
        HomePage homePage = new HomePage(driver);
        homePage.openUrl();

        System.out.println("Navigate to Login page by clicking login button");
        Header header = new Header(driver);
        header.clickLogin();

        System.out.println("Validate URL");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyURL();

        System.out.println("Enter username and password");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();

        System.out.println("Go to Profile page and check posts");
        header.clickProfileButton();
        ProfilePage profilePage = new ProfilePage(driver);
        int initialPosts = profilePage.getExistingPostsCount();

        System.out.println("Go to new post");
        header.clickNewPostButton();
        NewPostPage newPostPage = new NewPostPage(driver);

        System.out.println("Upload pic");
        newPostPage.uploadImage(file);

        System.out.println("Verify image is uploaded");
        newPostPage.waitForImageToShow();

        System.out.println("Verify image name is correct");
        Assert.assertEquals(newPostPage.getImageFilename(), file.getName(), "Image did not show up");

        System.out.println("Fill the caption");
        newPostPage.populateCaption(captionText);

        System.out.println("Submit post");
        newPostPage.submitPost();

        System.out.println("Check post count again");
        int existingPosts = profilePage.getExistingPostsCount();
        Assert.assertEquals(existingPosts, initialPosts + 1, "Incorrect posts count!");

        System.out.println("Open newly created post");
        profilePage.openPostByIndex(existingPosts - 1);

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
