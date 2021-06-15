package com.avs.pages;

import org.openqa.selenium.WebElement;

import com.avs.base.BaseClass;

public class Setup_GroupSensorsPage extends BaseClass{
	
	// Group Sensor page element variable declaration definition
	WebElement GrpSensorPageTitle = null;
	
	private void initializeEelements() {
		GrpSensorPageTitle = driver.findElementByName("Sensors Configuration");
	}
	
	Setup_GroupSensorsPage() {
		super();
		initializeEelements();
	}
	
	// Check the presence of Group Sensor page
	public boolean GrpsensorPage_state() {
		return IsElementVisibleStatus(GrpSensorPageTitle);
	}
	
	// Get the Group Sensor page title text
	public String get_GrpsensorPage_titletext() {
		return FetchText(GrpSensorPageTitle);
	}

}
