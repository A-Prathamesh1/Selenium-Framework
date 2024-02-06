package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//input[@name='uid']")
	@CacheLookup
	public WebElement userIDField;

	@FindBy(name = "password")
	@CacheLookup
	public WebElement passwordField;

	@FindBy(name = "btnLogin")
	@CacheLookup
	public WebElement loginButton;

	public void setUserID(String uname) {
		userIDField.sendKeys(uname);
	}

	public void setPassword(String pwd) {
		passwordField.sendKeys(pwd);
	}

	public void clickSubmit() {
		loginButton.click();
	}

}
