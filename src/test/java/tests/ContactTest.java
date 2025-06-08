package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.wipro.selenium.pages.ContactPage;
import com.wipro.selenium.utils.Base2;
import com.wipro.selenium.utils.ExtentUtil;
import com.wipro.selenium.utils.Utility;

public class ContactTest extends Base2 {

    Utility util = new Utility();

    @BeforeMethod
    @Parameters("browser")
    public void InitilizeSetup(String browser) {
        setup(browser);
    }

    @Test
    public void contactFormSubmissionTest() {
       
        ExtentUtil.getTest().log(Status.INFO, "Navigating to Contact Page");

        driver.get("https://demo.guru99.com/test/newtours/support.php");
        ExtentUtil.getTest().log(Status.PASS, "Contact Page Loaded");

        ContactPage contact = new ContactPage(driver);

        ExtentUtil.getTest().log(Status.INFO, "Submitting Contact Form");
        contact.submitContactForm("ziya.contact@test.com");

        String pageTitle = driver.getTitle();
        if (pageTitle.contains("Support")) {
            ExtentUtil.getTest().log(Status.PASS, "Form submitted, Support Page still visible");
        } else {
            ExtentUtil.getTest().log(Status.FAIL, "Unexpected navigation after form submission");
        }

        String screenshot = util.takeScreenshotPath(driver, "ContactForm");
        ExtentUtil.getTest().addScreenCaptureFromPath(screenshot);
    }

    @AfterMethod
    public void tearDownDriver() {
        teardown();
    }
}
