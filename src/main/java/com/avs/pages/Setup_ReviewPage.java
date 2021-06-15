package com.avs.pages;

import org.openqa.selenium.WebElement;

import com.avs.base.BaseClass;

public class Setup_ReviewPage extends BaseClass{
	
	// Review page element variable declaration definition
	WebElement ReviewPageTitle = null;
	
	private void initializeEelements() {
		ReviewPageTitle = driver.findElementByName("Review");
	}
	
	Setup_ReviewPage() {
		super();
		initializeEelements();
	}
	
	// Check the presence of Review page
	public boolean ReviewPage_state() {
		return IsElementVisibleStatus(ReviewPageTitle);
	}
	
	// Get the Review page title text
	public String get_ReviewPage_titletext() {
		return FetchText(ReviewPageTitle);
	}

}
