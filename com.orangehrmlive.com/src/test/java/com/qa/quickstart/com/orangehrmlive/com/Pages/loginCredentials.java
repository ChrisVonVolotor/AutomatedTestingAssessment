package com.qa.quickstart.com.orangehrmlive.com.Pages;

import org.openqa.selenium.WebElement;
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
