package com.wipro.selenium.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public WebDriver driver;
	
	 @Parameters("browser")
	    public void setup(String browser) {
	        if (browser.equalsIgnoreCase("chrome")) {
	        	ChromeOptions options = new ChromeOptions();
	        	options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080", "--no-sandbox");
	        	driver = new ChromeDriver(options);
	            //driver = new ChromeDriver();
	        } else if (browser.equalsIgnoreCase("edge")) {
	            driver = new EdgeDriver();
	        } else if (browser.equalsIgnoreCase("firefox")) {
	            driver = new FirefoxDriver();
	        } else {
	            throw new IllegalArgumentException("Browser not supported: " + browser);
	        }

	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	    }

	
	public void teardown()
	{
		if(driver!=null)
		{
		driver.quit();
		System.out.println("Browser closed successfully.");
		}
	}
	
	 public WebDriver getDriver() {
	        return driver;
	    }

}
