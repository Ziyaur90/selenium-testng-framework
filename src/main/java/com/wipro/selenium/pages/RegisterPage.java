package com.wipro.selenium.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(name = "firstName") WebElement firstName;
    @FindBy(name = "lastName") WebElement lastName;
    @FindBy(name = "phone") WebElement phone;
    @FindBy(name = "userName") WebElement email;
    @FindBy(name = "address1") WebElement address;
    @FindBy(name = "city") WebElement city;
    @FindBy(name = "state") WebElement state;
    @FindBy(name = "postalCode") WebElement postalCode;
    @FindBy(name = "email") WebElement userId;
    @FindBy(name = "password") WebElement password;
    @FindBy(name = "confirmPassword") WebElement confirmPassword;
    @FindBy(name = "submit") WebElement submitBtn;

    public void registerUser(String fName, String lName, String userPhone, String userEmail, String userIdVal, String pass) {
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        phone.sendKeys(userPhone);
        email.sendKeys(userEmail);
        address.sendKeys("123 Automation Lane");
        city.sendKeys("Testville");
        state.sendKeys("TX");
        postalCode.sendKeys("75001");
        userId.sendKeys(userIdVal);
        password.sendKeys(pass);
        confirmPassword.sendKeys(pass);
        submitBtn.click();
    }
}
