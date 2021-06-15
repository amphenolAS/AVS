package com.avs.pages;

import org.openqa.selenium.WebElement;

import com.avs.base.BaseClass;

public class Setup_defineSetupPage extends BaseClass {
	
	// Define Setup page element variable declaration definition
	private WebElement DefineSetupPageName = null;
	private WebElement DefineSetupPageTitle = null;
	private WebElement DefineSetup_backBtn = null;	
	private WebElement DefineSetup_SetupName_txtBx = null;
	private WebElement DefineSetup_Sensordata_txtBx = null;
	private WebElement DefineSetup_AssetID_txtBx = null;
	private WebElement DefineSetup_SOP_txtBx = null;
	private WebElement DefineSetup_LoadDesc_txtBx = null;
	private WebElement DefineSetup_Cmnts_txtBx = null;
	private WebElement Next_Btn = null;
	
	private void initializeEelements() {
		DefineSetupPageName = driver.findElementByName("Define Setup");
		DefineSetupPageTitle = driver.findElementByAccessibilityId("SetupHeaderTextBlock");
		DefineSetup_backBtn = driver.findElementByAccessibilityId("GoButton");
		DefineSetup_SetupName_txtBx = driver.findElementByAccessibilityId("SetupNameTextBox");
		DefineSetup_Sensordata_txtBx = driver.findElementByAccessibilityId("ContentElement");
		DefineSetup_AssetID_txtBx = driver.findElementByAccessibilityId("VessalTextBox");
		DefineSetup_SOP_txtBx = driver.findElementByAccessibilityId("SopProtocolTextBox");
		DefineSetup_LoadDesc_txtBx = driver.findElementByAccessibilityId("LoadDescTextBox");
		DefineSetup_Cmnts_txtBx = driver.findElementByAccessibilityId("CommentTextBox");
		Next_Btn = driver.findElementByAccessibilityId("NextButton");
		
	}
	
	Setup_defineSetupPage() {
		super();
		initializeEelements();
	}
	
	// Check the presence of Define Setup page
	public boolean defineSetupPage_state() {
		return IsElementVisibleStatus(DefineSetupPageName);
	}
	
	// Get the Define Setup page Name text
	public String get_defineSetupPage_Nametext() {
		return FetchText(DefineSetupPageName);
	}
	
	// Get the Define Setup page Title text
	public String get_defineSetupPage_Titletext() {
		return FetchText(DefineSetupPageTitle);
	}
	
	// Click the Define Setup page Back button
	public void click_defineSetupPage_backBtn() throws InterruptedException {
		clickOn(DefineSetup_backBtn);
		Thread.sleep(1000);
	}
	
	// Check the presence of Alert message on clicking the Setup Back Button
	public boolean visible_AlertMsg_state() {
		WebElement alrtmsg = driver.findElementByName("You are about to lose your changes.Do you want to continue ?");
		return IsElementVisibleStatus(alrtmsg);
	}
	
	// Click Yes to Alert message
	public assetDetailsPage click_YesofAlert_msg() throws InterruptedException {
		WebElement alrtmsg = driver.findElementByName("You are about to lose your changes.Do you want to continue ?");
		
		if (IsElementVisibleStatus(alrtmsg)) {
			WebElement alrtmsg_YesBtn = driver.findElementByName("Yes");	
			clickOn(alrtmsg_YesBtn);
			Thread.sleep(1000);
			return new assetDetailsPage();
		} else {
			System.out.println("No Alert message displayed");
			return new assetDetailsPage();
		}
	}
	
	// Click No to Alert message
	public void click_NoofAlert_msg() throws InterruptedException {
		WebElement alrtmsg = driver.findElementByName("You are about to lose your changes.Do you want to continue ?");
		
		if (IsElementVisibleStatus(alrtmsg)) {
			WebElement alrtmsg_NoBtn = driver.findElementByName("No");	
			clickOn(alrtmsg_NoBtn);
		} else {
			System.out.println("No Alert message displayed");
		}

	}
	
	// Clear Setup Name	
	public void clear_defineSetupPage_setupName() {
		ClearText(DefineSetup_SetupName_txtBx);
	}
		
	// Enter Setup Name	
	public void enter_defineSetupPage_setupName(String setUpNm) {
		enterText(DefineSetup_SetupName_txtBx, setUpNm);
	}
	
	// Get Setup Name data
	public String get_defineSetupPage_setupName() {
		return FetchText(DefineSetup_SetupName_txtBx);
	}
	
	// Clear Sensor count data	
	public void clear_defineSetupPage_SensorCount() {
		ClearText(DefineSetup_Sensordata_txtBx);
	}
	
	// Click Sensor count data field
	public void click_defineSetupPage_SensorCountField() {
		clickOn(DefineSetup_Sensordata_txtBx);
	}	
	
	// Enter Sensor count data
	public void enter_defineSetupPage_SensorCount(String sensorCnt) {
		enterText(DefineSetup_Sensordata_txtBx, sensorCnt);
	}
	
	// Get the Asset ID text for the Asset ID test field
	public String get_AssetID_text() {
		return FetchText(DefineSetup_AssetID_txtBx);
	}
	
	// Get text of the Button Bar Alert message
	public String get_ButtomBarAlertmsg_txt() {
		WebElement alrtmsg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(alrtmsg);
	}
	
	// Check the presence of SOP field in Define Setup page
	public boolean visible_SOPField() {
		return IsElementVisibleStatus(DefineSetup_SOP_txtBx);
	}
	
	// Click SOP field
	public void click_defineSetupPage_SOPField() {
		clickOn(DefineSetup_SOP_txtBx);
	}	
	
	// Clear Sensor count data	
	public void clear_defineSetupPage_SOP() {
		ClearText(DefineSetup_SOP_txtBx);
	}
	
	// Enter SOP data
	public void enter_defineSetupPage_SOP(String SOPdt) {
		enterText(DefineSetup_SOP_txtBx, SOPdt);
	}
	
	// Check the presence of Load Description field in Define Setup page
	public boolean visible_LoadDescField() {
		return IsElementVisibleStatus(DefineSetup_LoadDesc_txtBx);
	}
	
	// Click Load Description field
	public void click_defineSetupPage_LoadDescField() {
		clickOn(DefineSetup_LoadDesc_txtBx);
	}	
	
	// Clear Load Description data field
	public void clear_defineSetupPage_LoadDesc() {
		ClearText(DefineSetup_LoadDesc_txtBx);
	}
	
	// Enter Load Description data
	public void enter_defineSetupPage_LoadDesc(String LDdt) {
		enterText(DefineSetup_LoadDesc_txtBx, LDdt);
	}
	
	// Check the presence of comments field in Define Setup page
	public boolean visible_Comments_Field() {
		return IsElementVisibleStatus(DefineSetup_Cmnts_txtBx);
	}
	
	// Click comments field
	public void click_defineSetupPage_commentsField() {
		clickOn(DefineSetup_Cmnts_txtBx);
	}	
	
	// Clear comments data field
	public void clear_defineSetupPage_comments() {
		ClearText(DefineSetup_Cmnts_txtBx);
	}
	
	// Enter comments data
	public void enter_defineSetupPage_comments(String Cmntdt) {
		enterText(DefineSetup_Cmnts_txtBx, Cmntdt);
	}
	
	// Fetch comments field data
	public String get_defineSetupPage_comments_txtData() {
		return FetchText(DefineSetup_Cmnts_txtBx);
	}
	
	
	
	
	
	
	
	
	// Click the Next button in the Define Setup page to move to Sensor Config page
	public Setup_SensorConfigPage click_defineSetupPage_nxtBtn() throws InterruptedException {
		clickOn(Next_Btn);
		Thread.sleep(1000);
		return new Setup_SensorConfigPage();
	}
	

}
