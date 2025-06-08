package com.wipro.selenium.testngutils;

import com.aventstack.extentreports.Status;
import com.wipro.selenium.utils.Base2;
import com.wipro.selenium.utils.ExtentUtil;
import com.wipro.selenium.utils.Utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

public class Listener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String method = result.getMethod().getMethodName();
        Object[] params = result.getParameters();

        String testName = method;
        if (params != null && params.length > 0) {
            testName += " - Username: " + params[0];
        }

        ExtentUtil.createTest(testName);
        ExtentUtil.getTest().log(Status.INFO, "Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentUtil.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentUtil.getTest().log(Status.FAIL, "Test failed: " + result.getThrowable().getMessage());
        WebDriver driver = ((Base2) result.getInstance()).driver;
        String screenshot = new Utility().takeScreenshotPath(driver, result.getName());
        try {
            ExtentUtil.getTest().addScreenCaptureFromPath(screenshot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentUtil.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentUtil.flushReports();
    }
}
