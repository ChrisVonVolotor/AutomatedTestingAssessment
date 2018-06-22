package com.qa.quickstart.com.orangehrmlive.com;

import static org.junit.Assert.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.quickstart.net.phptravels.pages.resultPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import com.relevantcodes.extentreports.ExtentReports;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class assessmentSteps {
	public ChromeDriver driver;
	static ExtentReports extent;
	private String strFirstName;
	private String strMiddleName;
	private String strLastName;
	private String strUserName;
	private String strPassword;
	ExtentTest test1;

	


	@Given("^the Add Employee Tab$")
	public void addEmplyeeTab() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Documents\\AutomatedTesting\\chromedriver.exe");
		driver = new ChromeDriver();
		extent= new ExtentReports("C:\\Users\\Admin\\Documents\\AutomatedTesting\\com.orangehrmlive.com\\reports\\extent.html", true);
		test = extent.startTest("Add an employee to the CRM");
		
		driver.navigate().to("http://opensource.demo.orangehrmlive.com/index.php/pim/addEmployee");
		test1.log(LogStatus.INFO, "Accessing the Add Employee Tab: " + driver.getCurrentUrl());
	}
	
	@When("^I fill out the Employee Details correctly$")
	public void fillEmployeeDetails() {
		strFirstName = "Frederic";
		strMiddleName = "Filipe";
		strLastName = "Obi";
		addEmployee addEmp = PageFactory.initElements(driver, addEmployee.class);
		addEmp.fillName(strFirstName,strMiddleName,strLastName);
		test1.log(LogStatus.INFO, "I fill out the Employee Details: "+ strFirstName + " " + strMiddleName + " " + strLastName);

		
	}
	
	@When("^I choose to create Login Details$")
	public void createLoginDetails() {
		addEmployee addEmp = PageFactory.initElements(driver, addEmployee.class);
		addEmp.addLoginButton();
		test1.log(LogStatus.INFO, "Check login button");
	}
	
	@When("^I fill out the Login Details correctly$")
	public void fillLoginDetails() {
		strUserName = strFirstName.substring(0,1) + strMiddleName.substring(0,1) + strLastName;
		strPassword = "password";
		addEmployee addEmp = PageFactory.initElements(driver, addEmployee.class);
		addEmp.fillLogin(strUserName,strPassword);
		test1.log(LogStatus.INFO, "I fill out the Login Details: usr" + strUserName + " pass:" + strPassword);

	}
	
	@When("^I click the Save button$")
	public void clickSaveButton() {
		addEmployee addEmp = PageFactory.initElements(driver, addEmployee.class);
		addEmp.submitUser();
		test1.log(LogStatus.INFO, "I click the Save button");
	}
	
	@Then("^I can see information about the user$")
	public void viewUserInformation() {
		viewPersonalDetails viewEmp = PageFactory.initElements(driver, viewPersonalDetails.class);
		assertTrue(viewEmp.submitUser(strFirstName,strMiddleName,strLastName));
		test1.log(LogStatus.INFO, "Check login button");
		try {
			assertTrue(viewEmp.submitUser(strFirstName,strMiddleName,strLastName));
			test1.log(LogStatus.PASS, "I can see information about the user: " strFirstName + " " + strMiddleName + " " + strLastName);
		}catch(AssertionError e) {
			test1.log(LogStatus.FAIL, "I can not see information about the user");
			fail();
		}finally {
			test1.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test1);
		}
	}
	
}
