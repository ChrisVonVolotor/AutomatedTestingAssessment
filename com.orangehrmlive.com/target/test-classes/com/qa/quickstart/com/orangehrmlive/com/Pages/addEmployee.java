package com.qa.quickstart.com.orangehrmlive.com.Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


public class addEmployee {
	
	@FindBy(id="firstName")
	private WebElement inpFirstName;

	@FindBy(id="middleName")
	private WebElement inpMiddleName;
	
	@FindBy(id="lastName")
	private WebElement inpLastName;
	
	@FindBy(id="chkLogin")
	private WebElement btnAddLogin;
	
	@FindBy(id="user_name")
	private WebElement inpUserName;
	
	@FindBy(id="user_password")
	private WebElement inpPassword;
	
	@FindBy(id="re_password")
	private WebElement inpRePassword;
	
	@FindBy(id="btnSave")
	private WebElement btnSave;
	
	public void fillName(String fName, String mName, String lName) {
		//Fill out the required details for the user
		inpFirstName.click();
		inpFirstName.sendKeys(fName);
		inpMiddleName.click();
		inpMiddleName.sendKeys(mName);
		inpLastName.click();
		inpLastName.sendKeys(lName);
	}
	
	public void addLoginButton() {
		btnAddLogin.click();
	}
	
	public void fillLogin(String userName, String password) {
		//fill out the login details for the user
		inpUserName.click();
		inpUserName.sendKeys(userName);
		inpPassword.click();
		inpPassword.sendKeys(password);
		inpRePassword.click();
		inpRePassword.sendKeys(password);
		
	}
	
	public void submitUser() {
		btnSave.click();
	}
	
	
	
}
