package com.wipro.selenium.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentUtil {
	private static ExtentReports extent;
	private static ExtentTest test;
	private static String reportPath;

	public static ExtentReports getInstance()
	{
		if(extent==null)
		{
			initReports();
		}
		return extent;
	}

	public static void initReports()
	{
		String timeStamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		reportPath=System.getProperty("user.dir")+"/reports/ExtentReports_"+timeStamp+".html";
		ExtentSparkReporter spark=new ExtentSparkReporter(reportPath);
		spark.config().setReportName("Test Automation Report");
		spark.config().setDocumentTitle("Test Automation Execution Report");
		extent=new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("QA", "Ziyaur Rahman");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("OS", System.getProperty("os.name"));

	}

	public static ExtentTest createTest(String testName) {
		test = getInstance().createTest(testName);
		return test;
	}
	public static void flushReports() {
		if (extent != null) {
			extent.flush();
		}
	}
	
	  public static ExtentTest getTest() {
	        return test;
	    }
}