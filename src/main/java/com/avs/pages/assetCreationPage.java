/**
 * @author manoj.ghadei
 *
 */

package com.avs.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.avs.base.BaseClass;

public class assetCreationPage extends BaseClass{
	
	// Asset Details Page Element definition
	WebElement CreateAssetPageTitle = driver.findElementByAccessibilityId("pageTitle");
	WebElement AssetNameTxtBox = driver.findElementByAccessibilityId("NameTextBox");
	WebElement AssetIDTxtBox = driver.findElementByAccessibilityId("EqidTextBox");	
	List <WebElement> Combobx = driver.findElementsByAccessibilityId("EditableCombo");
	List <WebElement> AssetEditBox = driver.findElementsByAccessibilityId("EditableTextBox");
	WebElement AssetModelTxtBox = driver.findElementByAccessibilityId("ModelTextBox");
	WebElement AssetSizeTxtBox = driver.findElementByAccessibilityId("SizeTextBox");
	WebElement AssetLstVldtdDate = driver.findElementByAccessibilityId("PART_PickerButton");
	WebElement AssetFrqBtn = driver.findElementByAccessibilityId("CalibrationFrequencyComboBox");
	WebElement AssetFrqIntrvlBtn = driver.findElementByAccessibilityId("CalibrationFrequencyMeasurementComboBox");
	WebElement AssetDescTextField = driver.findElementByAccessibilityId("DescriptionTextBox");
	WebElement AssetSaveBtn = driver.findElementByName("Save");
	WebElement AssetClearBtn = driver.findElementByName("Clear");
	WebElement AssetBackBtn = driver.findElementByAccessibilityId("BackButton");
	
	
	//Verify the presence of New Asset Creation text 
	public boolean newAssetCreatePagetitle() {
		return IsElementVisibleStatus(CreateAssetPageTitle);
	}
	
	//Get the Asset Creation page title text 
	public String get_newAssetCreatePagetitle() {
		return FetchText(CreateAssetPageTitle);
	}
	
	//Enter Asset Name
	public void enterAssetName(String AN) {
		ClearText(AssetNameTxtBox);
		enterText(AssetNameTxtBox, AN);
	}
	
	//Fetch the Asset Name text
	public String getAssetName() {
		return FetchText(AssetNameTxtBox);
	}
	
	//Enter Asset ID
	public void enterAssetID(String AID) {
		ClearText(AssetIDTxtBox);
		enterText(AssetIDTxtBox, AID);
	}
	
	//Fetch the Asset/Eqp ID text
	public String getEqpID() {
		return FetchText(AssetIDTxtBox);
	}
		
	//Fetch default Asset Type data
	public String getAssetTypetext() {
		return AssetEditBox.get(0).getText();		
	}
	
	//Fetch list of raw Asset Type data as is viewed
	public String[] getAssetTypelist() {
		clickOn(Combobx.get(0));
		List <WebElement> Combobxlist = driver.findElementsByClassName("ComboBoxItem");		
		
		String str[] = new String[Combobxlist.size()];
		
		for (int i = 0; i < Combobxlist.size(); i++) {
			str[i]=Combobxlist.get(i).getText();		
		}
		
		String[] Obtainedlist= removeDuplicateStringinArray(str, "Select");
		System.out.println(Arrays.toString(Obtainedlist));
		
		clickOn(AssetModelTxtBox);
		System.out.println("---------");
		return Obtainedlist;		
	}
	
	//Fetch list of Asset Type data in sorted order(Alphabetically)
	public String[] getAssetTypeSortedlist() {
		clickOn(Combobx.get(0));
		List <WebElement> Combobxlist = driver.findElementsByClassName("ComboBoxItem");

		String str[] = new String[Combobxlist.size()];
		
		for (int i = 0; i < Combobxlist.size(); i++) {
			str[i]=Combobxlist.get(i).getText();			
		}		
		
		//Remove the duplicate element from the Array list(Here Select string element in our case)
		String[] Sortedlist = removeDuplicateStringinArray(str, "Select");
		
		//Sort method used to order the Strings 
		Arrays.sort(Sortedlist);
		clickOn(AssetModelTxtBox);
		return Sortedlist;		
	}
	
	// Select Asset Type
	public void SelectAssetType(String AType) {
		clickOn(Combobx.get(0));
		clickOn(driver.findElementByName(AType));
	}
	
	//Enter Asset Type
	public void enterAssetType(String Atype) {
		ClearText(AssetEditBox.get(0));
		AssetEditBox.get(0).sendKeys(Atype);		
	}
	
	//Get Asset Type
	public String get_AssetType() {
		return FetchText(AssetEditBox.get(0));				
	}
	
	//Enter Manufacturer Name
	public void enterManufacturerName(String AMN) {
		clickOn(AssetEditBox.get(3));
		clickOn(AssetEditBox.get(3));
		ClearText(AssetEditBox.get(3));
		enterText(AssetEditBox.get(3), AMN);
		//AssetEditBox.get(3).sendKeys(AMN);
	}
	
	//Fetch default Asset Manufacturer data
	public String getAssetManufacturertext() {		
		return FetchText(AssetEditBox.get(3));		
	}
	
	//Fetch list of raw Asset Manufacturer data as is viewed
	public String[] getAssetMakerlist() {
		clickOn(Combobx.get(3));
		List<WebElement> Combobxlist = driver.findElementsByClassName("ComboBoxItem");

		String str[] = new String[Combobxlist.size()];

		for (int i = 0; i < Combobxlist.size(); i++) {
			str[i] = Combobxlist.get(i).getText();
		}

		String[] Obtainedlist = removeDuplicateStringinArray(str, "Select");
		// System.out.println(Arrays.toString(Obtainedlist));

		clickOn(AssetSizeTxtBox);
		// System.out.println("---------");
		return Obtainedlist;
	}
			
	// Fetch list of Asset Manufacturer data in sorted order(Alphabetically)
	public String[] getAssetMakerSortedlist() {
		clickOn(Combobx.get(3));
		List<WebElement> Combobxlist = driver.findElementsByClassName("ComboBoxItem");
		
		String str[] = new String[Combobxlist.size()];

		for (int i = 0; i < Combobxlist.size(); i++) {
			str[i] = Combobxlist.get(i).getText();
		}

		// Remove the duplicate element from the Array list(Here Select string element
		// in our case)
		String[] Sortedlist = removeDuplicateStringinArray(str, "Select");

		// Sort method used to order the Strings
		Arrays.sort(Sortedlist);
		clickOn(AssetSizeTxtBox);
		return Sortedlist;
	}
	
	//Enter Location 
	public void enterLocation(String AL) {
		clickOn(AssetEditBox.get(1));
		clickOn(AssetEditBox.get(1));
		ClearText(AssetEditBox.get(1));
		enterText(AssetEditBox.get(1), AL);
		//AssetEditBox.get(1).sendKeys(AL);
	}
	
	//Fetch default Asset Location data
	public String getAssetLocationtext() {		
		return FetchText(AssetEditBox.get(1));		
	}
	
	//Enter Model 
	public void enterModelName(String AModel) {
		ClearText(AssetModelTxtBox);
		enterText(AssetModelTxtBox, AModel);
	}
	
	//Fetch default Asset Model data
	public String getAssetModeltext() {		
		return FetchText(AssetModelTxtBox);		
	}
		
	//Enter Asset Size
	public void enterSize_Unit(String ASize, String ASUnit) {
		//System.out.println("~~~"+ASize);
		ClearText(AssetSizeTxtBox);
		enterText(AssetSizeTxtBox, ASize);
		enterAssetSizeUnit(ASUnit);
	}
	
	//Enter Asset Size
	public void enterSize_Unit1(String ASize, String ASUnit) {
		//System.out.println("~~~"+ASize);
		clickOn(AssetSizeTxtBox);
		clickOn(AssetSizeTxtBox);
		ClearText(AssetSizeTxtBox);
		enterText(AssetSizeTxtBox, ASize);
		selectAssetSizeUnit(ASUnit);
	}
	
	//Fetch default Asset Size  data
	public String getAssetSizetext() {
		return FetchText(AssetSizeTxtBox);		
	}
	
	//Fetch default Asset Size Unit data
	public String getAssetSizeUnittext() {
		return AssetEditBox.get(2).getText();		
	}
	
	//Enter Asset Size Unit data
	public void enterAssetSizeUnit(String ASizeUnit) {
		clickOn(AssetEditBox.get(2));
		ClearText(AssetEditBox.get(2));
		AssetEditBox.get(2).sendKeys(ASizeUnit);		
	}

	//Select Asset Size Unit data from CuM/Ft/in
	public void selectAssetSizeUnit(String ASizeUnit) {
		clickOn(Combobx.get(2));
		List<WebElement> CombobxItemList = driver.findElementsByClassName("ComboBoxItem");
		for (WebElement unit : CombobxItemList) {
			if (unit.getText().equals(ASizeUnit)) {
				unit.click();
			}
		}
	}
	

	// Fetch list of raw Asset Frequency data as is viewed
	public String[] getAssetFreqlist() {
		clickOn(AssetFrqBtn);
		List<WebElement> Combobxlist = driver.findElementsByClassName("ComboBoxItem");

		String str[] = new String[Combobxlist.size()];
		System.out.println(Combobxlist.size());

		for (int i = 0; i < Combobxlist.size(); i++) {
			Actions ac = new Actions(driver);
			ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
			str[i] = FetchText(AssetFrqBtn);
		}

		String[] Obtainedlist = removeDuplicateStringinArray(str, "Select");
		//System.out.println(Arrays.toString(Obtainedlist));

		clickOn(AssetIDTxtBox);
		// System.out.println("---------");
		return Obtainedlist;
	}
	
	//Select Asset Frequency
	public void selectAssetFreq(String AF) {
		clickOn(AssetFrqBtn);
		//System.out.println("---"+AF);
		List<WebElement> Combobxlist = driver.findElementsByClassName("ComboBoxItem");
		System.out.println(Combobxlist.size());
		Actions ac = new Actions(driver);
		
		for (int i = 0; i < Combobxlist.size(); i++) {
			ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();			
			if (FetchText(AssetFrqBtn).contains(AF)) {				
				break;
			}			
		}
		clickOn(AssetSizeTxtBox);
	}
	
	//Fetch the Asset Frequency text
	public String getAssetFreqtext() {
		return FetchText(AssetFrqBtn);		
	}
	
	//Select Asset Frequency Interval
	public void selectAssetFreqIntrvl(String FI) throws InterruptedException {
		clickOn(AssetFrqIntrvlBtn);
		Thread.sleep(2000);
		List<WebElement> Combobxlist = driver.findElementsByClassName("ComboBoxItem");
		System.out.println(Combobxlist.size());
		Actions ac = new Actions(driver);
		
		for (int i = 0; i < Combobxlist.size(); i++) {
			ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
			if (FetchText(AssetFrqIntrvlBtn).contains(FI)) {
				break;
			}			
		}
	}
	
	//Fetch the Asset Frequency Interval text
	public String getAssetFreqIntrvltext() {
		return FetchText(AssetFrqIntrvlBtn);
	}
	
	//Fetch list of raw Asset Manufacturer data as is viewed
	public String[] getAssetFreqIntrvllist() {
		ArrayList<String> Alist = new ArrayList<String>();
		clickOn(AssetFrqIntrvlBtn);
		List<WebElement> Combobxlist = driver.findElementsByClassName("ComboBoxItem");
		
		String str[] = new String[Combobxlist.size()];

		for (int i = 0; i < Combobxlist.size(); i++) {
			str[i] = Combobxlist.get(i).getText();
		}		

		String[] Obtainedlist = removeDuplicateStringinArray(str, "Select");
		// System.out.println(Arrays.toString(Obtainedlist));

		clickOn(AssetSizeTxtBox);
		// System.out.println("---------");
		return Obtainedlist;
	}
	
	//Fetch Asset Description data
	public String get_Asset_LastValidatedtext() {		
		return FetchText(AssetLstVldtdDate);		
	}
	
	//Enter Description 
	public void enterAstDescription(String ADesc) {
		ClearText(AssetDescTextField);
		enterText(AssetDescTextField, ADesc);
	}
	
	//Fetch Asset Description data
	public String getAssetDescriptiontext() {		
		return FetchText(AssetDescTextField);		
	}
	
	//verify Save button presence
	public boolean SaveBtn() {
		return IsElementVisibleStatus(AssetSaveBtn);
	}
	
	//Click Save button
	public void clickSaveBtn() throws InterruptedException {
		clickOn(AssetSaveBtn);
		Thread.sleep(1000);
	}
	
	//Verify the Save Alert message displayed
	public boolean saveAlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return IsElementVisibleStatus(Msg);
	}
	
	//Fetch the Save Alert message
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}
	
	//Close the Alert message method
	public void CloseAlertMsg() {
		WebElement AlertCloseBtn = driver.findElementByAccessibilityId("btnDelete");
		clickOn(AlertCloseBtn);
	}
	
	//Click Clear button
	public void clickClearBtn() throws InterruptedException {
		clickOn(AssetClearBtn);
		Thread.sleep(1000);
	}
	
	//click Back button to move to assetHub Page in case new Asset is created
	public assetHubPage clickBackBtn() throws InterruptedException {
		clickOn(AssetBackBtn);		
		Thread.sleep(1000);
		return new assetHubPage();
	}
	
	//click Back button to move to assetHub Page in case new Asset is created
	public assetDetailsPage click_BackBtn() throws InterruptedException {
		clickOn(AssetBackBtn);
		Thread.sleep(1000);
		return new assetDetailsPage();
	}
	
	//click Back button to get the Discard message
	public void clickBkBtn() throws InterruptedException {
		clickOn(AssetBackBtn);		
		Thread.sleep(1000);		
	}
	
	//Discard alert message
	public boolean discardAlert() throws InterruptedException {
		clickOn(AssetBackBtn);		
		Thread.sleep(1000);	
		return IsElementVisibleStatus(driver.findElementByAccessibilityId("Popup Window"));		
	}
	
	//Discard alert message- No option
	public void discardAlertNoBtn() throws InterruptedException {
		clickOn(AssetBackBtn);		
		Thread.sleep(1000);	
		if (IsElementVisibleStatus(driver.findElementByAccessibilityId("Popup Window"))) {
			clickOn(driver.findElementByName("No"));
		} 	
	}
	
	//Move to AssetHub page by Discarding the changes made to Asset creationpage
	public assetHubPage discardAlertYesBtn() throws InterruptedException {
		clickOn(AssetBackBtn);		
		Thread.sleep(1000);	
		if (IsElementVisibleStatus(driver.findElementByAccessibilityId("Popup Window"))) {
			clickOn(driver.findElementByName("Yes"));
		} 			
		return new assetHubPage();
	}
	
	//Asset Creation with Mandatory fields
	public void assetCreation(String AName, String AID, String AType, String AManufaturer, String ALocation) throws InterruptedException {
		enterAssetName(AName);
		enterAssetID(AID);
		SelectAssetType(AType);
		enterManufacturerName(AManufaturer);
		enterLocation(ALocation);
		
		clickSaveBtn();		
	}
	
	//Asset Creation with all Data entry
	public void assetCreationWithAllFieldEntry(String AName, String AID, String AType, 
			String AManufacturer, String ALocation, String AModel, String ASize, String AUnit, 
			String AFreq, String AFreqInt, String ADesc) throws InterruptedException {
		enterAssetName(AName);
		enterAssetID(AID);
		SelectAssetType(AType);
		enterManufacturerName(AManufacturer);
		enterLocation(ALocation);
		enterModelName(AModel);
		enterSize_Unit(ASize, AUnit);
		selectAssetFreq(AFreq);
		selectAssetFreqIntrvl(AFreqInt);
		enterAstDescription(ADesc);
		
		clickSaveBtn(); 
	}
	
	//Asset Creation with Type
	public void assetCreationWithType(String AName, String AID, String AType, String AManufaturer, String ALocation) throws InterruptedException {
		enterAssetName(AName);
		enterAssetID(AID);
		enterAssetType(AType);
		enterManufacturerName(AManufaturer);
		enterLocation(ALocation);
		
		clickSaveBtn();		
	}
	
	//Asset Creation with model data
	public void assetCreationWithModel(String AName, String AID, String AType, String AManufaturer, String ALocation, String AModel) throws InterruptedException {
		enterAssetName(AName);
		enterAssetID(AID);
		SelectAssetType(AType);
		enterManufacturerName(AManufaturer);
		enterLocation(ALocation);
		enterModelName(AModel);
		
		clickSaveBtn();		
	}
	
	//Asset Creation with Size data
	public void assetCreationWithSize(String AName, String AID, String AType, String AManufaturer, String ALocation, String ASize, String AUnit) throws InterruptedException {
		enterAssetName(AName);
		enterAssetID(AID);
		SelectAssetType(AType);
		enterManufacturerName(AManufaturer);
		enterLocation(ALocation);
		enterSize_Unit(ASize, AUnit);
		
		clickSaveBtn();		
	}
	
	//Asset Creation with Size Unit data
	public void assetCreationWithSizeUnit(String AName, String AID, String AType, 
			String AManufaturer, String ALocation, String ASize, String AUnit) throws InterruptedException {
		
		enterAssetName(AName);
		enterAssetID(AID);
		SelectAssetType(AType);
		enterManufacturerName(AManufaturer);
		enterLocation(ALocation);
		enterSize_Unit(ASize, AUnit);
		
		clickSaveBtn();		
	}
	
	//Fetch list of Asset Size Unit data as is viewed
	public String[] getAssetSizeUnitlist() {
		clickOn(Combobx.get(2));
		List <WebElement> Combobxlist = driver.findElementsByClassName("ComboBoxItem");		
		
		String str[] = new String[Combobxlist.size()];
		
		for (int i = 0; i < Combobxlist.size(); i++) {
			str[i]=Combobxlist.get(i).getText();		
		}
		
		String[] Obtainedlist= removeDuplicateStringinArray(str, "Select");
		//System.out.println(Arrays.toString(Obtainedlist));
		
		clickOn(AssetModelTxtBox);
		//System.out.println("---------");
		return Obtainedlist;
	}
	
	//Asset Creation with Description data
	public void assetCreationWithDesc(String AName, String AID, String AType, String AManufaturer, String ALocation, String ADescp) throws InterruptedException {
		enterAssetName(AName);
		enterAssetID(AID);
		SelectAssetType(AType);
		enterManufacturerName(AManufaturer);
		enterLocation(ALocation);
		enterAstDescription(ADescp);
		
		clickSaveBtn();		
	}
	
	//Login Popup presence 
	public boolean UserLoginPopupVisible() throws InterruptedException {
		WebElement LgInPopup = driver.findElementByName("Enter User Credentials");
		return IsElementVisibleStatus(LgInPopup);
	}
	
	//Close Login Popup 
	public void UserLoginPopupClose() throws InterruptedException {
		WebElement LgInPopupCancel = driver.findElementByName("Cancel");
		clickOn(LgInPopupCancel);
	}
	
	//Get the Asset info in Asset details page
	public String[] get_assetCreationinfo() {
		String[] asstCreationinfo = new String[5];
		asstCreationinfo[0] = getEqpID();
		asstCreationinfo[1] = getAssetModeltext();
		asstCreationinfo[2] = getAssetManufacturertext();
		asstCreationinfo[3] = get_AssetType();
		asstCreationinfo[4] = get_Asset_LastValidatedtext();
		
		return asstCreationinfo;
	}
	
	
}
