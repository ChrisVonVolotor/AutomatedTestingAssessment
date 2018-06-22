package com.qa.quickstart.com.orangehrmlive.com;

import static org.junit.Assert.*;

import java.util.Random;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.quickstart.com.orangehrmlive.com.Pages.addEmployee;
import com.qa.quickstart.com.orangehrmlive.com.Pages.loginCredentials;
import com.qa.quickstart.com.orangehrmlive.com.Pages.viewPersonalDetails;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import com.relevantcodes.extentreports.ExtentReports;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class assessmentSteps {
	Random rand = new Random();

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
		//overrite off to track all tests
		extent= new ExtentReports("C:\\Users\\Admin\\Documents\\AutomatedTesting\\com.orangehrmlive.com\\reports\\extent.html", false);
		test1 = extent.startTest("Add an employee to the CRM");
		
		driver.navigate().to("http://opensource.demo.orangehrmlive.com/index.php/pim/addEmployee");
		test1.log(LogStatus.INFO, "Accessing the Add Employee Tab: " + driver.getCurrentUrl());
		//upon openign you are actually redirected to the ValidateCredentials Page
		loginCredentials loginAttempt = PageFactory.initElements(driver, loginCredentials.class);
		loginAttempt.adminLogin();
		//Soemtiems the login is not correctly accepted and requires to be re entered 
		while(!driver.getCurrentUrl().equals("http://opensource.demo.orangehrmlive.com/index.php/pim/addEmployee")) {
			loginAttempt.adminLogin();
		}
	}
	
	@When("^I fill out the Employee Details correctly$")
	public void fillEmployeeDetails() {
		//Randomise name to cut down on chance of usernames being rejected because of alrady existing
		String[] arrstrFirstName = {"John", "Sol", "Michael", "Jillian", "Sandra", "Boris", "Luke", "Steph", "Angela", "Paul", "Marcus", "Leslie", "Chucky", "Tirza", "Ezra", "Sabine", "Tsubaki", "Gad", "Tristan", "Vincent", "Weston", "Nafuna", "Goku", "Zelda", "Lennox", "Leonardo", "Jordan", "Betty", "Adriana", "Porco", "Videl", "Vaclav", "Anya", "Hector", "Cadence", "Archie", "Peter", "Blaze", "Paras", "Sam", "Kobe", "Liza", "Bort", "Augustus", "Prince", "Drogo", "Hagar", "Leah", "Ezekiel", "Renly", "Abel", "Noah", "Mohammed", "Sen", "Ivy", "Miles", "Ryder", "Mukasa", "Gohan", "Leroy", "Juliet", "Angelo", "Rosso", "Adriatic", "Valeria", "Antonio", "Frollo", "Rocco", "Winston", "Salman", "Adonis", "Lyra", "Tybal"};
		String[] arrstrLastName = {"Obi", "Wolf", "Vickers", "Wright", "Lazano", "Priestly", "Black", "Ben Hur", "Applecroft", "Patterby", "Hiram", "Tah", "Obama", "Rondon", "Scott", "Malaika", "Gurgi", "D'ath", "Sabat", "Mansburg", "Broley", "Son", "Jenkins", "Macbeth", "Shakespear", "Hamlet", "Costa", "Fields", "Carpenter", "Satan", "Mosley", "Salah", "Koke", "Bell", "Keane", "Harper", "Belmont", "Diallo", "Pope", "Fish", "Copper", "Price", "Shoora", "McDonald", "Matic", "Kirby", "Kenobi", "Ibbot", "Ibsen", "Zappa", "Qadir", "Quest", "Jager", "Jakab", "Jansen", "Danell", "Darzi", "Yamada", "Yanev", "Yoxall", "Young", "Fletcher", "Fabien", "Falco", "Fannon", "Ganon", "Farmer", "Fay", "Capello", "Carey", "Carlson"};
		strFirstName = arrstrFirstName[rand.nextInt(arrstrFirstName.length)];
		strMiddleName = arrstrFirstName[rand.nextInt(arrstrFirstName.length)];
		strLastName = arrstrLastName[rand.nextInt(arrstrLastName.length)];
		//push the fist name, second name and third name to the POM 
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
		//auto generate the username as Initials fo the first and middle name with he surname and random number between 1 and 100 (to prevent rejects for alrady existing). 
		strUserName = strFirstName.substring(0,1).toLowerCase() + strMiddleName.substring(0,1).toLowerCase() + strLastName + String.format("%02d", rand.nextInt(99));
		strPassword = "password";
		addEmployee addEmp = PageFactory.initElements(driver, addEmployee.class);
		addEmp.fillLogin(strUserName,strPassword);
		test1.log(LogStatus.INFO, "I fill out the Login Details: usr: " + strUserName + " pass: " + strPassword);
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
		test1.log(LogStatus.INFO, "Check login button");
		try {
			assertTrue(viewEmp.checkPageDetails(strFirstName,strMiddleName,strLastName));
			test1.log(LogStatus.PASS, "I can see information about the user: " + strFirstName + " " + strMiddleName + " " + strLastName);
		}catch(AssertionError e) {
			test1.log(LogStatus.FAIL, "I can not see information about the user");
			fail();
		}finally {
			test1.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());
			extent.endTest(test1);
		}
		driver.close();
		driver.quit();
		extent.flush();
	}
	
}
