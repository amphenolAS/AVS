package com.avs.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.avs.base.BaseClass;

public class PreferencesPage extends BaseClass {
	// Page element variable declaration definition
	WebElement PreferencesHeaderText = null;
	WebElement CompanyNameTextBox = null;
	WebElement TemparatureComboBox = null;
	WebElement MaxGroupComboBox = null;
	WebElement LineFrequencyComboBox = null;
	WebElement PressureComboBox = null;
	WebElement MachineIDTextBlock = null;
	WebElement AlternateMachineID = null;
	WebElement DataDictionaryTextBlock = null;
	WebElement AllowLethalityComboBox = null;
	WebElement IRTDStudyTextBoxText = null;
	WebElement ChageUserImageTextBlock = null;
	WebElement BatteryWarningComboBox = null;
	WebElement PerformedByTextBlock = null;
	WebElement ReviewedByTextBlock = null;
	WebElement cbkFooterFirstPage = null;
	WebElement cbkFooterLastPage = null;
	WebElement cbkFooterAllPages = null;
	WebElement SaveButton = null;
	WebElement CancelButton = null;
	WebElement BackUMBtn = null;

	// Page element Initialize method
	private void initElements() {
		PreferencesHeaderText = driver.findElementByAccessibilityId("PreferencesButton");
		CompanyNameTextBox = driver.findElementByAccessibilityId("CompanyNameTextBox");
		TemparatureComboBox = driver.findElementByAccessibilityId("TemparatureComboBox");
		MaxGroupComboBox = driver.findElementByAccessibilityId("MaxGroupComboBox");
		LineFrequencyComboBox = driver.findElementByAccessibilityId("LineFrequencyComboBox");
		PressureComboBox = driver.findElementByAccessibilityId("PressureComboBox");
		MachineIDTextBlock = driver.findElementByAccessibilityId("MachineIDTextBlock");
		AlternateMachineID = driver.findElementByAccessibilityId("AlternatIDTextBox");
		DataDictionaryTextBlock = driver.findElementByAccessibilityId("DataDictionaryTextBlock");
		AllowLethalityComboBox = driver.findElementByAccessibilityId("AllowLethalityComboBox");
		IRTDStudyTextBoxText = driver.findElementByAccessibilityId("IRTDStudyTextBoxText");
		ChageUserImageTextBlock = driver.findElementByAccessibilityId("ChageUserImageTextBlock");
		BatteryWarningComboBox = driver.findElementByAccessibilityId("BatteryWarningComboBox");
		PerformedByTextBlock = driver.findElementByAccessibilityId("PerformedByTextBlock");
		ReviewedByTextBlock = driver.findElementByAccessibilityId("ReviewedByTextBlock");
		cbkFooterFirstPage = driver.findElementByAccessibilityId("cbkFooterFirstPage");
		cbkFooterLastPage = driver.findElementByAccessibilityId("cbkFooterLastPage");
		cbkFooterAllPages = driver.findElementByAccessibilityId("cbkFooterAllPages");
		SaveButton = driver.findElementByAccessibilityId("SaveButton");
		CancelButton = driver.findElementByAccessibilityId("CancelButton");
		BackUMBtn = driver.findElementByAccessibilityId("BackButton");

	}

	// Constructor for initializing the page elements
	PreferencesPage() throws IOException {
		super();
		initElements();
	}

	// Release memory
	public void resetWebElements() {
		PreferencesHeaderText = null;
		CompanyNameTextBox = null;
		TemparatureComboBox = null;
		MaxGroupComboBox = null;
		LineFrequencyComboBox = null;
		PressureComboBox = null;
		MachineIDTextBlock = null;
		AlternateMachineID = null;
		DataDictionaryTextBlock = null;
		AllowLethalityComboBox = null;
		IRTDStudyTextBoxText = null;
		ChageUserImageTextBlock = null;
		BatteryWarningComboBox = null;
		PerformedByTextBlock = null;
		ReviewedByTextBlock = null;
		cbkFooterFirstPage = null;
		cbkFooterLastPage = null;
		cbkFooterAllPages = null;
		SaveButton = null;
		CancelButton = null;
		BackUMBtn = null;

	}

	/*----------------------
	Methods of Preferences Page
	------------------------*/
	// Check if Preferences page is displayed
	public boolean IsPreferences_screenDisplayed() {
		return IsElementEnabledStatus(PreferencesHeaderText);
	}

	// Check if CompanyName TextBox is displayed
	public boolean IsCompanyNameTextBox_Presence() {
		return IsElementEnabledStatus(CompanyNameTextBox);
	}

	// Check if Temparature ComboBox is displayed
	public boolean IsTemparatureComboBox_Presence() {
		return IsElementEnabledStatus(TemparatureComboBox);
	}

	// Check if MaxGroupComboBox is displayed
	public boolean IsMaxGroupComboBox_Presence() {
		return IsElementEnabledStatus(MaxGroupComboBox);
	}

	// Check if LineFrequencyComboBox is displayed
	public boolean IsLineFrequencyComboBox_Presence() {
		return IsElementEnabledStatus(LineFrequencyComboBox);
	}

	// Check if Preferences page is displayed
	public boolean IsPressureComboBox_Presence() {
		return IsElementEnabledStatus(PressureComboBox);
	}

	// Check if MachineIDTextBlock is displayed
	public boolean IsMachineIDTextBlock_Presence() {
		return IsElementEnabledStatus(MachineIDTextBlock);
	}

	// Check if AlternateMachineID is displayed
	public boolean IsAlternateMachineID_Presence() {
		return IsElementEnabledStatus(AlternateMachineID);
	}

	// Check if DataDictionaryTextBlock displayed
	public boolean IsDataDictionaryTextBlock_Presence() {
		return IsElementEnabledStatus(DataDictionaryTextBlock);
	}

	// Check if AllowLethalityComboBox displayed
	public boolean IsAllowLethalityComboBox_Presence() {
		return IsElementEnabledStatus(AllowLethalityComboBox);
	}

	// Check if IRTDStudyTextBoxText displayed
	public boolean IsIRTDStudyTextBoxText_Presence() {
		return IsElementEnabledStatus(IRTDStudyTextBoxText);
	}

	// Check if ChageUserImageTextBlock displayed
	public boolean IsChageUserImageTextBlock_Presence() {
		return IsElementEnabledStatus(ChageUserImageTextBlock);
	}

	// Check if BatteryWarningComboBox displayed
	public boolean IsBatteryWarningComboBox_Presence() {
		return IsElementEnabledStatus(BatteryWarningComboBox);
	}

	// Check if PerformedByTextBlock displayed
	public boolean IsPerformedByTextBlock_Presence() {
		return IsElementEnabledStatus(PerformedByTextBlock);
	}

	// Check if ReviewedByTextBlock displayed
	public boolean IsReviewedByTextBlock_Presence() {
		return IsElementEnabledStatus(ReviewedByTextBlock);
	}

	// Check if cbkFooterFirstPage displayed
	public boolean IsFooterFirstPage_Presence() {
		return IsElementEnabledStatus(cbkFooterFirstPage);
	}

	// Check if cbkFooterLastPage displayed
	public boolean IsFooterLastPage_Presence() {
		return IsElementEnabledStatus(cbkFooterLastPage);
	}

	// Check if cbkFooterAllPages displayed
	public boolean IsFooterAllPages_Presence() {
		return IsElementEnabledStatus(cbkFooterAllPages);
	}

	// ENTER Company Name
	public void enterCompanyName(String CN) {
		clickOn(CompanyNameTextBox);
		ClearText(CompanyNameTextBox);
		enterText(CompanyNameTextBox, CN);
	}

	// clickOn CompanyNameTextBox
	public void clickOn_CompanyName() {
		clickOn(CompanyNameTextBox);
	}

	// clickOn CompanyNameTextBox

	public void clickOn_ChangeImage() {
		clickOn(ChageUserImageTextBlock);

	}

	// Fetch default value from CompanyName text field

	public String Fetch_CompanyName() {
		return FetchText(CompanyNameTextBox);
	}

	// Click on Save Btn

	public void click_SaveBtn() throws InterruptedException {
		Thread.sleep(1000);
		clickOn(SaveButton);
	}

	// Fetch the Alert message
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}

	// Fetch default unit from Temparature ComboBox
	public String Fetch_Defaultval_TemparatureUnits() {
		return FetchText(TemparatureComboBox);
	}

	// Fetch default value from MaxGroup ComboBox
	public String Fetch_Defaultval_MaxGroupComboBox() {
		return FetchText(MaxGroupComboBox);
	}

	// Fetch default value from Pressure ComboBox
	public String Fetch_Defaultval_PressureComboBox() {
		return FetchText(PressureComboBox);
	}

	// Fetch default value from AllowLethality ComboBox
	public String Fetch_Defaultval_AllowLethalityComboBox() {
		return FetchText(AllowLethalityComboBox);
	}

	// click on Line freq
	public void click_LineFrequency() {
		WebElement LineFrequency = driver.findElementByAccessibilityId("LineFrequencyComboBox");
		clickOn(LineFrequency);
	}

	// fetch the option 1 from Line Frequency drop down field

	public String get_LineFrequency_options(int index) throws InterruptedException {

		List<WebElement> from_dd = driver.findElementByAccessibilityId("LineFrequencyComboBox")
				.findElements(By.className("ComboBoxItem"));
		return FetchText(from_dd.get(index));
	}

	// Fetch default value from LineFrequency ComboBox

	public String Fetch_Defaultval_LineFrequencyComboBox() {
		return FetchText(LineFrequencyComboBox);
	}

	// Fetch default value from LineFrequency ComboBox
	public String Fetch_MachineIDTextBlock() {
		return FetchText(MachineIDTextBlock);
	}

	// ENTER AlternateMachineID
	public void enter_AlternateMachineID(String AMID) {

		clickOn(AlternateMachineID);
		ClearText(AlternateMachineID);
		enterText(AlternateMachineID, AMID);
	}

	// Fetch value from AlternateMachineID text box
	public String Fetch_AlternateMachineID() {
		return FetchText(AlternateMachineID);
	}

	// Fetch text from Fetch_DataDictionary
	public String Fetch_DataDictionaryText() {
		return FetchText(DataDictionaryTextBlock);
	}

	// Fetch text from Fetch_DataDictionary
	public String Fetch_IRTDStudyTextBoxText() {
		return FetchText(IRTDStudyTextBoxText);
	}

	// click on CancelButton
	public void click_CancelBtn() {
		clickOn(CancelButton);
	}

	// click back btn
	public MainHubPage ClickBackButn() throws IOException, InterruptedException {
		clickOn(BackUMBtn);
		Thread.sleep(1000);
		return new MainHubPage();
	}

	// click on clickOn(MaxGroupComboBox);

	public void clickOn_MaxGroupComboBox() {
		clickOn(MaxGroupComboBox);
	}

	// click on max group n select option 1

	public void Select_maxgroup_Limit(int index) throws InterruptedException {

		List<WebElement> option1 = driver.findElementByAccessibilityId("MaxGroupComboBox")
				.findElements(By.className("ComboBoxItem"));
		option1.get(index).click();
	}

	// click on LineFrequencyComboBox n select option as 60Hz

	public void click_LineFrequency_60Hz() throws InterruptedException {
		clickOn(LineFrequencyComboBox);
		WebElement option2 = driver.findElementByName("60 Hz");
		option2.click();

	}

	// click on LineFrequencyComboBox n select option as 60Hz

	public void click_PressureComboBox_Pascal() throws InterruptedException {
		clickOn(PressureComboBox);
		WebElement option1 = driver.findElementByName("Pascal");
		option1.click();

	}

	// click on PressureComboBox

	public void click_PressureComboBox() {

		clickOn(PressureComboBox);
	}

// get PressureComboBox option1

	public String gettxt_from_pressurecombobox(int index) {
		List<WebElement> options = driver.findElementByAccessibilityId("PressureComboBox")
				.findElements(By.className("ComboBoxItem"));

		return options.get(index).getText();
	}

//click on AllowLethalityComboBox

	public void click_AllowLethalityComboBox() {
		clickOn(AllowLethalityComboBox);
	}

//select any value  from AllowLethalityComboBox
	public void selectValue_From_AllowLethalityComboBox(int index) {

		List<WebElement> option1 = driver.findElementByAccessibilityId("AllowLethalityComboBox")
				.findElements(By.className("ComboBoxItem"));
		option1.get(index).click();

	}

//Enter value into IRTDStudyTextBoxText

	public void enter_IRTDStudyText(String text) {
		clickOn(IRTDStudyTextBoxText);
		ClearText(IRTDStudyTextBoxText);
		enterText(IRTDStudyTextBoxText, text);
	}

//verify PerformedByTextBlock

	public void enter_PerformedByText(String text) {
		clickOn(PerformedByTextBlock);
		ClearText(PerformedByTextBlock);
		enterText(PerformedByTextBlock, text);
	}

//get value from PerformedByText

	public String get_PerformedByText() {
		return FetchText(PerformedByTextBlock);

	}
//get value from ReviewedByTextBlock

	public String get_ReviewedByText() {
		return FetchText(ReviewedByTextBlock);

	}

//verify ReviewedByTextBlock

	public void enter_ReviewedByText(String text) {
		clickOn(ReviewedByTextBlock);
		ClearText(ReviewedByTextBlock);
		enterText(ReviewedByTextBlock, text);
	}

//click FooterFirstPage

	public void click_FooterFirstPage() {
		clickOn(cbkFooterFirstPage);
	}

//click FooterLastPage

	public void click_FooterLastPage() {
		clickOn(cbkFooterLastPage);
	}

//click FooterAllPages

	public void click_FooterAllPages() {
		clickOn(cbkFooterAllPages);
	}

}
