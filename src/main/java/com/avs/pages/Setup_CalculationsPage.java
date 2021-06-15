package com.avs.pages;

import org.openqa.selenium.WebElement;

import com.avs.base.BaseClass;

public class Setup_CalculationsPage extends BaseClass{
	
	// Calculation page element variable declaration definition
	WebElement CalculationPageTitle = null;
	
	private void initializeEelements() {
		CalculationPageTitle = driver.findElementByName("Calculations");
	}
	
	Setup_CalculationsPage() {
		super();
		initializeEelements();
	}
	
	// Check the presence of Calculation page
	public boolean GrpsensorPage_state() {
		return IsElementVisibleStatus(CalculationPageTitle);
	}
	
	// Get the Calculation page title text
	public String get_CalculationPage_titletext() {
		return FetchText(CalculationPageTitle);
	}

}
