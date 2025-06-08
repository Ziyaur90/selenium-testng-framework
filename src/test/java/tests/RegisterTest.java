package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.wipro.selenium.pages.RegisterPage;
import com.wipro.selenium.utils.Base;
import com.wipro.selenium.utils.ExtentUtil;
import com.wipro.selenium.utils.Utility;

public class RegisterTest extends Base {

    Utility util = new Utility();

    @BeforeMethod
    @Parameters("browser")
    public void InitilizeSetup(String browser) {
        setup(browser);
    }

    @Test
    public void registerUserTest() {
        
        ExtentUtil.getTest().log(Status.INFO, "Opening Register Page");
        driver.get("https://demo.guru99.com/test/newtours/register.php");
        ExtentUtil.getTest().log(Status.PASS, "Register Page Launched");

        RegisterPage reg = new RegisterPage(driver);

        ExtentUtil.getTest().log(Status.INFO, "Filling Registration Form");
        reg.registerUser("Ziya", "Ansari", "9876543210", "ziya@test.com", "ziyaUser", "ziyaPass");

        String pageSource = driver.getPageSource();
        if (pageSource.contains("Thank you for registering")) {
            ExtentUtil.getTest().log(Status.PASS, "Registration successful");
        } else {
            ExtentUtil.getTest().log(Status.FAIL, "Registration failed");
        }

        String screenshot = util.takeScreenshotPath(driver, "Register");
        ExtentUtil.getTest().addScreenCaptureFromPath(screenshot);
    }

    @AfterMethod
    public void tearDown() {
        teardown();
    }
}
