/**
 * @author manoj.ghadei
 *
 */

package com.avs.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import org.openqa.selenium.WebElement;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import com.avs.base.BaseClass;
import com.avs.utility.TestUtilities;

public class assetDetailsPage extends BaseClass {

	// Asset Details element variable declaration definition
	WebElement AssetDetailPageTitle = null;
	WebElement AssetDetail_IDinfo = null;
	WebElement AssetDetail_Mdlinfo = null;
	WebElement AssetDetail_Mfginfo = null;
	WebElement AssetDetail_Typeinfo = null;
	WebElement AssetDetail_Dateinfo = null;
	WebElement BackBtn = null;
	WebElement AssetEditBtn = null;
	WebElement Asset_SetupTile = null;
	WebElement Asset_QualTile = null;
	WebElement Asset_ReportsTile = null;
	WebElement Asset_DocsTile = null;
	WebElement SetupTile_Count = null;
	WebElement QualTile_Count = null;
	WebElement ReportsTile_Count = null;
	WebElement DocsTile_Count = null;
	WebElement UploadDocs_Btn = null;
	WebElement VRTappClose_Btn = null;
	WebElement InitiateQual_Btn = null;
	WebElement SetupsHeaderTxt = null;
	WebElement NewSetupCreate_Btn = null;
	WebElement CopySetup_Btn = null;
	WebElement AssetEdit_Btn = null;
	
	
	

	private void initElements() {
		AssetDetailPageTitle = driver.findElementByAccessibilityId("AssetsNameTextBlock");
		AssetDetail_IDinfo = driver.findElementByAccessibilityId("EquipmentIDTextBlock");
		AssetDetail_Mdlinfo = driver.findElementByAccessibilityId("ModelNoTextBlock");
		AssetDetail_Mfginfo = driver.findElementByAccessibilityId("ManufacturerTextBlock");
		AssetDetail_Typeinfo = driver.findElementByAccessibilityId("TypeTextBlock");
		AssetDetail_Dateinfo = driver.findElementByAccessibilityId("LastValidationTextBlock");
		BackBtn = driver.findElementByAccessibilityId("ArrowGlyph");
		AssetEditBtn = driver.findElementByAccessibilityId("EditAssetsButton");
		Asset_SetupTile = driver.findElementByAccessibilityId("SetupsButton");
		Asset_QualTile = driver.findElementByAccessibilityId("QualificationsButton");
		Asset_ReportsTile = driver.findElementByAccessibilityId("ReportsButton");
		Asset_DocsTile = driver.findElementByAccessibilityId("DocumentsButton");
		SetupTile_Count = driver.findElementByAccessibilityId("SetupsCountTextBlock");
		QualTile_Count = driver.findElementByAccessibilityId("QualificationCountTextBlock");
		ReportsTile_Count = driver.findElementByAccessibilityId("ReportsCountTextBlock");
		DocsTile_Count = driver.findElementByAccessibilityId("DocumentsCountTextBlock");
		VRTappClose_Btn = driver.findElementByAccessibilityId("Close");
		InitiateQual_Btn = driver.findElementByAccessibilityId("StartQualificationButton");
		SetupsHeaderTxt = driver.findElementByAccessibilityId("SetupsHeaderTextBlock");		
		NewSetupCreate_Btn = driver.findElementByAccessibilityId("CreateSetupButton");
		CopySetup_Btn = driver.findElementByAccessibilityId("CopySetupButton");
		AssetEdit_Btn = driver.findElementByAccessibilityId("EditAssetsButton");

	}

	assetDetailsPage() {
		super();
		initElements();
	}
	
	// Check the presence of Setup tile
	public boolean setupTile_state() {
		return IsElementVisibleStatus(Asset_SetupTile);
	}
	
	// Check the Setup tile default selected
	public String get_Setupheader_txt() {
		return FetchText(SetupsHeaderTxt);
	}
	
	// Get the Setup count data form the Setup tile
	public String setupTile_countdata() {
		return FetchText(SetupTile_Count);
	}
	
	// Check the enable state of Initiate Qual button under Setup tile
	public boolean InitiateQualBtn_state() {
		return IsElementEnabledStatus(InitiateQual_Btn);
	}
	
	// Check the presence of Qual tile
	public boolean qualTile_state() {
		return IsElementVisibleStatus(Asset_QualTile);
	}
	
	// Get the Qual count data form the Setup tile
	public String qualTile_countdata() {
		return FetchText(QualTile_Count);
	}
	
	// Check the presence of Reports tile
	public boolean reportsTile_state() {
		return IsElementVisibleStatus(Asset_ReportsTile);
	}
	
	// Get the Reports count data form the Setup tile
	public String reportsTile_countdata() {
		return FetchText(ReportsTile_Count);
	}
	
	// Check the presence of Docs tile
	public boolean docsTile_state() {
		return IsElementVisibleStatus(Asset_DocsTile);
	}
	
	// Get the Docs count data form the Docs tile
	public String docsTile_countdata() {
		return FetchText(DocsTile_Count);
	}
	
	// Click the Asset Docs tile button
	public void click_DocsTileBtn() throws InterruptedException {
		clickOn(Asset_DocsTile);
		Thread.sleep(1000);		
	}
	
	// Click the Upload Docs button under Assets Docs tile
	public void click_UploadDocsBtn() throws InterruptedException {
		UploadDocs_Btn = driver.findElementByAccessibilityId("UploadDocumentsButton");
		clickOn(UploadDocs_Btn);
		Thread.sleep(2000);		
	}

	// Get the Asset details Page header data for the corresponding Asset
	public String assetDetail_PageTitle() {
		return FetchText(AssetDetailPageTitle);
	}

	// Get the Asset ID data for the corresponding Asset
	public String get_asset_IDinfo() {
		return FetchText(AssetDetail_IDinfo);
	}

	// Get the Asset Model data for the corresponding Asset
	public String get_asset_Modelinfo() {
		return FetchText(AssetDetail_Mdlinfo);
	}

	// Get the Asset Manufacturer data for the corresponding Asset
	public String get_asset_Mfginfo() {
		return FetchText(AssetDetail_Mfginfo);
	}

	// Get the Asset Type data for the corresponding Asset
	public String get_asset_Typeinfo() {
		return FetchText(AssetDetail_Typeinfo);
	}

	// Get the Asset Last Validated data for the corresponding Asset
	public String get_asset_LastValidatedinfo() throws ParseException {
		String Dt = FetchText(AssetDetail_Dateinfo);		
		TestUtilities TU = new TestUtilities();
		return TU.convert_StringDate_to_ActualDate_inCertainFormat(Dt);
	}

	// Check the presence of Asset edit button
	public boolean assetEditBtn_state() {
		return IsElementVisibleStatus(AssetEditBtn);
	}

	// Click the Asset edit button
	public assetCreationPage click_assetEditBtn() throws InterruptedException {
		clickOn(AssetEditBtn);
		Thread.sleep(1000);
		return new assetCreationPage();
	}
	//Click the Asset edit button to get alert msg..when admin does not have the privilege to edit the asset
		public void click_assetEditBtn_alrt() throws InterruptedException {
			clickOn(AssetEditBtn);
			Thread.sleep(1000);
		}
		//Fetch the alert message when admin not have the privilege to edit the asset
		public String AlertMsg() {
			WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
			return FetchText(Msg);
		}

	// Click the Back Button
	public assetHubPage ClickBackBtn() throws InterruptedException {
		clickOn(BackBtn);
		Thread.sleep(1000);
		return new assetHubPage();
	}

	// Get the Asset info in Asset details page
	public String[] get_assetinfo() throws ParseException {
		String[] asstDetailinfo = new String[5];
		asstDetailinfo[0] = get_asset_IDinfo();
		asstDetailinfo[1] = get_asset_Modelinfo();
		asstDetailinfo[2] = get_asset_Mfginfo();
		asstDetailinfo[3] = get_asset_Typeinfo();
		asstDetailinfo[4] = get_asset_LastValidatedinfo();

		return asstDetailinfo;
	}
	
	// Click new Setup create button
	public Setup_defineSetupPage click_NewStupCreateBtn() throws InterruptedException {
		clickOn(NewSetupCreate_Btn);
		Thread.sleep(1000);
		return new Setup_defineSetupPage();
	}
	// Fetch the alert message when does not have access to on new Setup create button 
		public void click_NewStupCreateBtn_alert() throws InterruptedException {
			clickOn(NewSetupCreate_Btn);
			Thread.sleep(1000);
		}
	
	// Click Copy Setup button
	public void click_CopyStup_Btn() throws InterruptedException {
		clickOn(CopySetup_Btn);
		Thread.sleep(1000);
	}
	
	// Click the Asset Edit button
	public assetCreationPage click_AssetEditBtn() throws InterruptedException {
		clickOn(AssetEdit_Btn);
		Thread.sleep(1000);
		return new assetCreationPage();
	}
	
	//Click VRT app close button
	public void click_vrtAppcloseBtn() throws InterruptedException {
		clickOn(VRTappClose_Btn);
		Thread.sleep(1000);
	}
	
	// Upload Documents method under Asset details page
	public void uploadDoc_Assetdetails(String filename) throws AWTException, IOException, InterruptedException{

		// switch to the file upload window
		WebElement alert = driver.switchTo().activeElement();
		Thread.sleep(1000);

		// enter the filename
		String filepath = System.getProperty("user.dir") + "\\TestData\\" + filename;
		//System.out.println(filepath);
		alert.sendKeys(filepath);
		Thread.sleep(1000);

		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

		// switch back
		driver.switchTo().activeElement();
	}
	
	// Verify if duplicate doc alert message displayed
	public String alertMeg_duplicateDocupload_Assetdetails() throws InterruptedException {	

		WebElement alertMsg = driver.findElementByName("Another file with same name already exists. Please use different name.");
		Thread.sleep(1000);		
		//System.out.println(alertMsg.getAttribute("Name"));	
		String msg=alertMsg.getAttribute("Name");		
		return msg;
	}
	
	// Verify if More than One Asset required for copy setup alert message displayed
	public String alertMeg_CopyAsset_WithOneAsset() throws InterruptedException {

		WebElement alertMsg = driver
				.findElementByName("To perform Copy Setup more than 1 asset required.");
		Thread.sleep(1000);
		// System.out.println(alertMsg.getAttribute("Name"));
		String msg = alertMsg.getAttribute("Name");
		return msg;
	}
	
	
	
	

}
