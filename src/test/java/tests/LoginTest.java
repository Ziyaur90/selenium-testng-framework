package tests;

import org.testng.annotations.*;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.wipro.selenium.pages.LoginPage;
import com.wipro.selenium.utils.Base;
import com.wipro.selenium.utils.ExcelUtils;
import com.wipro.selenium.utils.ExtentUtil;
import com.wipro.selenium.utils.Utility;

public class LoginTest extends Base {

    Utility util = new Utility();
    ExcelUtils excel;

    @BeforeClass
    public void loadExcelData() {
        String excelPath = System.getProperty("user.dir") + "/testdata/LoginData.xlsx";
        excel = new ExcelUtils(excelPath, "Sheet1");
    }

    @BeforeMethod
    @Parameters("browser")
    public void InitilizeSetup(String browser) {
        setup(browser);
    }

    @DataProvider(name = "LoginData")
    public Object[][] getData() {
        return excel.getTestData();
    }

    @Test(dataProvider = "LoginData")
    public void LoginMethod(String userName, String password) {
        

        try {
            ExtentUtil.getTest().log(Status.INFO, "Launching URL");
            driver.get("https://demo.guru99.com/test/newtours/");
            ExtentUtil.getTest().log(Status.PASS, "URL launched");

            LoginPage loginPage = new LoginPage(driver);
            ExtentUtil.getTest().log(Status.INFO, "Entering credentials");
            loginPage.login(userName, password);

            String expectedTitle = "Login: Mercury Tours"; // default title if login fails
            String actualTitle = driver.getTitle();
            ExtentUtil.getTest().log(Status.INFO, "Actual Title: " + actualTitle);

            if (actualTitle.contains("Welcome")) {
                ExtentUtil.getTest().log(Status.PASS, "Login successful for user: " + userName);
            } else {
                String screenshot = util.takeScreenshotPath(driver, "LoginFail_" + userName);
                ExtentUtil.getTest().addScreenCaptureFromPath(screenshot);
                ExtentUtil.getTest().log(Status.FAIL, "Login failed for user: " + userName);
                Assert.fail("Login failed – invalid credentials or error");
            }

        } catch (Exception e) {
            String screenshot = util.takeScreenshotPath(driver, "Exception_" + userName);
            ExtentUtil.getTest().addScreenCaptureFromPath(screenshot);
            ExtentUtil.getTest().log(Status.FAIL, "Exception occurred: " + e.getMessage());
            Assert.fail("Exception during test: " + e.getMessage());
        }
    }

    @AfterMethod
    public void closeBrowser() {
        teardown();
        excel.closeWorkBook();
    }

    @AfterSuite
    public void flushExtentReport() {
        ExtentUtil.flushReports();
    }
}
