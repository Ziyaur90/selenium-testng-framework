package com.wipro.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	@FindBy(name="userName")
	WebElement userName;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(name="submit")
	WebElement submit;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;		
		PageFactory.initElements(driver,this);
	}
	
	public void enterUserName(String vuser)
	{
		userName.sendKeys(vuser);
	}
	
	public void enterPassword(String vpassword)
	{
		password.sendKeys(vpassword);
	}

	public void clickSubmit()
	{
		submit.click();
	}
	
	public void login(String user, String password)
	{
		enterUserName(user);
		enterPassword(password);
		clickSubmit();
		
	}
}
