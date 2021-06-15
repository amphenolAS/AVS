package com.avs.pages;

import org.openqa.selenium.WebElement;

import com.avs.base.BaseClass;

public class Setup_SensorConfigPage extends BaseClass{
	
	// Sensor Configuration page element variable declaration definition
	private WebElement SensorConfigPageTitle = null;
	private WebElement SensorConfigPageHeaderTxt = null;
	
	private void initializeEelements() {
		SensorConfigPageTitle = driver.findElementByName("Sensors Configuration");
		SensorConfigPageHeaderTxt = driver.findElementByAccessibilityId("SetupHeaderTextBlock");
		
	}
	
	Setup_SensorConfigPage() {
		super();
		initializeEelements();
	}
	
	// Check the presence of Sensor Configuration page
	public boolean sensorConfigPage_state() {
		return IsElementVisibleStatus(SensorConfigPageTitle);
	}
	
	// Get the Sensor Configuration page title text
	public String get_SensorConfigurationPage_titletext() {
		return FetchText(SensorConfigPageHeaderTxt);
	}
	
	// Get the Sensor Configuration page text
	public String get_SensorConfigurationPage_text() {
		return FetchText(SensorConfigPageTitle);
	}
	
	

}
