package com.qa.quickstart.com.orangehrmlive.com.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class viewPersonalDetails {
	
	@FindBy(xpath="//*[@id=\"profile-pic\"]/h1")
	private WebElement txtUserName;
	
	@FindBy(xpath="//*[@id=\"pdMainContainer\"]/div[1]/h1")
	private WebElement txtPersonalDetails;
	
	//if the Name on the page is the same as the inputterr username  and the personal details are on the page then we are in the right place  
	public boolean checkPageDetails(String fName, String mName, String lName) {
		String strFullName = fName + " " + mName + " " + lName;
		
		if((strFullName.equals(txtUserName.getText())) && (txtPersonalDetails.getText().equals("Personal Details") )) {
			return true;
		}else {
			return false;
		}
	}
}
