package com.wipro.selenium.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {
	
	public void takeScreenshot(WebDriver driver)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String timestamp=String.valueOf(System.currentTimeMillis());
		
		File dest=new File(System.getProperty("user.dir")+"/ScreenShots/"+"screenshot_"+timestamp+".png");
		try {
		FileUtils.copyFile(src, dest);	
		System.out.println("ScreenShot taken");
		}
		catch(IOException e)
		{
			System.out.println("ScreenShot Failed");
			e.printStackTrace();
		}
	}
	
	 public String takeScreenshotPath(WebDriver driver, String screenshotName) {
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File src = ts.getScreenshotAs(OutputType.FILE);

	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        String path = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + timestamp + ".png";
	        File dest = new File(path);

	        try {
	            FileUtils.copyFile(src, dest);
	            System.out.println("Screenshot saved at: " + path);
	        } catch (IOException e) {
	            System.out.println("Failed to capture screenshot.");
	            e.printStackTrace();
	        }

	        return path;
	    }

}
