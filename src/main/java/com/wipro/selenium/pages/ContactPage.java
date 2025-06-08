package com.wipro.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

    WebDriver driver;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "email") WebElement emailField;
    @FindBy(name = "submit") WebElement submitBtn;

    public void submitContactForm(String email) {
        emailField.sendKeys(email);
        submitBtn.click();
    }
}
