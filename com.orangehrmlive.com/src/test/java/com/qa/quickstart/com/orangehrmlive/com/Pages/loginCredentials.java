package com.qa.quickstart.com.orangehrmlive.com.Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


public class loginCredentials {
	@FindBy(id="txtUsername")
	private WebElement inpLoginUserName;
	
	@FindBy(id="txtPassword")
	private WebElement inpLoginPassword;
	
	@FindBy(id="btnLogin")
	private WebElement btnLoginButton;
	
	//run through the login process
	public void adminLogin() {
		inpLoginUserName.click();
		inpLoginUserName.sendKeys("Admin");
		inpLoginPassword.click();
		inpLoginPassword.sendKeys("admin");
		btnLoginButton.click();
	}
}
