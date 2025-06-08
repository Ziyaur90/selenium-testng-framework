package com.wipro.selenium.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
    private WebDriver driver;

    @Parameters("browser")
    public void setup(String browser) {
        if (browser == null || browser.isEmpty()) {
            throw new IllegalArgumentException("Browser parameter is missing or empty!");
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();  // Automatically setup ChromeDriver
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(
                    "--headless", 
                    "--disable-gpu", 
                    "--window-size=1920,1080",
                    "--no-sandbox",
                    "--disable-dev-shm-usage"  // Prevent limited /dev/shm crash
                );
                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();  // Setup EdgeDriver
                EdgeOptions edgeOptions = new EdgeOptions();
                // Add headless mode for Edge if needed
                edgeOptions.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
                driver = new EdgeDriver(edgeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup(); // Setup GeckoDriver
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                // Headless Firefox
                firefoxOptions.addArguments("-headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public void teardown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
