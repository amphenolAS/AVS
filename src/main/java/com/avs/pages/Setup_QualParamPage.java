package com.avs.pages;

import org.openqa.selenium.WebElement;

import com.avs.base.BaseClass;

public class Setup_QualParamPage extends BaseClass{
	
	// Qualification Parameters page element variable declaration definition
	WebElement QualParamsPageTitle = null;
	
	private void initializeEelements() {
		QualParamsPageTitle = driver.findElementByName("Qualification Parameters");
	}
	
	Setup_QualParamPage() {
		super();
		initializeEelements();
	}
	
	// Check the presence of Qualification Parameters page
	public boolean QualParamsPage_state() {
		return IsElementVisibleStatus(QualParamsPageTitle);
	}
	
	// Get the Qualification Parameters page title text
	public String get_QualParamsPage_titletext() {
		return FetchText(QualParamsPageTitle);
	}

}
