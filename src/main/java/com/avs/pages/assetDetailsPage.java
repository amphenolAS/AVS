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
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.avs.base.BaseClass;
import com.avs.utility.TestUtilities;
import com.avs.pages.RW_FileSelctionPage;

public class assetDetailsPage extends BaseClass {

	TestUtilities tu = new TestUtilities();
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
	WebElement AssetHub_ImgHldr = null;
	WebElement AssetHub_ImgHldr_NextBtn = null;
	WebElement AssetHub_ImgHldr_PrvsBtn = null;
	WebElement CopyAsset = null;
	WebElement Print_Button = null;
	WebElement AssetID = null;
	WebElement Model = null;
	WebElement Manufacturer = null;
	WebElement Type = null;
	WebElement LastValidated = null;
	WebElement assetDeleteIcon = null;
	WebElement Yesbtn = null;

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
		AssetHub_ImgHldr = driver.findElementByClassName("Image");
		CopyAsset = driver.findElementByAccessibilityId("CopyAssetsButton");
		AssetID = driver.findElementByName("Asset ID");
		Model = driver.findElementByName("Model");
		Manufacturer = driver.findElementByName("Manufacturer");
		Type = driver.findElementByName("Type");
		LastValidated = driver.findElementByName("Last Validated");
		assetDeleteIcon = driver.findElementByAccessibilityId("DeleteAssetsButton");

	}

	assetDetailsPage() throws IOException {
		super();
		initElements();
	}

	// Release memory
	public void resetWebElements() {
		AssetDetailPageTitle = null;
		AssetDetail_IDinfo = null;
		AssetDetail_Mdlinfo = null;
		AssetDetail_Mfginfo = null;
		AssetDetail_Typeinfo = null;
		AssetDetail_Dateinfo = null;
		BackBtn = null;
		AssetEditBtn = null;
		Asset_SetupTile = null;
		Asset_QualTile = null;
		Asset_ReportsTile = null;
		Asset_DocsTile = null;
		SetupTile_Count = null;
		QualTile_Count = null;
		ReportsTile_Count = null;
		DocsTile_Count = null;
		UploadDocs_Btn = null;
		VRTappClose_Btn = null;
		InitiateQual_Btn = null;
		SetupsHeaderTxt = null;
		NewSetupCreate_Btn = null;
		CopySetup_Btn = null;
		AssetEdit_Btn = null;
		AssetHub_ImgHldr = null;
		AssetHub_ImgHldr_NextBtn = null;
		AssetHub_ImgHldr_PrvsBtn = null;
		CopyAsset = null;
		Print_Button = null;
		AssetID = null;
		Model = null;
		Manufacturer = null;
		Type = null;
		LastValidated = null;
		assetDeleteIcon = null;
		Yesbtn = null;
	}

// Check the presence of AssetID field

	public boolean AssetIDPresence() {
		return IsElementEnabledStatus(AssetID);
	}

// Check the presence of AssetID field

	public boolean ModelPresence() {
		return IsElementEnabledStatus(Model);
	}

// Check the presence of AssetHub_ImgHldr field
	public boolean AssetHub_ImgHldrPresence() {
		return IsElementEnabledStatus(AssetHub_ImgHldr);
	}

// Check the presence of Manufacturer field
	public boolean TypePresence() {
		return IsElementEnabledStatus(Type);
	}

// Check the presence of Manufacturer field
	public boolean ManufacturerPresence() {
		return IsElementEnabledStatus(Manufacturer);
	}

// Check the presence of Manufacturer field
	public boolean LastValidatedDatePresence() {
		return IsElementEnabledStatus(LastValidated);
	}

// Check the presence of setupTile_countdata
	public boolean IsSetupTile_countdataPresence() {
		return IsElementEnabledStatus(SetupTile_Count);
	}

// Check the presence of Setup tile
	public boolean setupTile_state() {
		return IsElementVisibleStatus(Asset_SetupTile);
	}

// Check the presence of Setup tile
	public boolean SetupsHeader_state() {
		return IsElementVisibleStatus(SetupsHeaderTxt);
	}

// Check the presence of Asset DeleteIcon button
	public boolean assetDeleteIcon_state() {
		return IsElementVisibleStatus(assetDeleteIcon);
	}

// Check the presence of Asset DeleteIcon button
	public boolean NewSetupCreateBtn_State() {
		return IsElementVisibleStatus(NewSetupCreate_Btn);
	}

// Check the presence of Edit button in set up section
	public boolean SetupEditBtn_state() {
		WebElement Edit_Button = driver.findElementByAccessibilityId("EditButton");
		return IsElementVisibleStatus(Edit_Button);
	}

// check the presence of date under setup ( which has sync in to application )
	public boolean DateUnder_Setup() {
		WebElement Date = driver.findElementByName("18-Mar-2020 13:11:17");
		return IsElementVisibleStatus(Date);
	}

	// fetch the date under set up
	public String get_DateUnder_Setup() {
		WebElement Date = driver.findElementByName("18-Mar-2020 13:11:17");

		return FetchText(Date);
	}

	// check the presence of date under Qual study file ( which has sync in to
	// application )
	public String get_DateUnder_Qual() {
		WebElement Date_qual = driver.findElementByName("18-Mar-2020 13:12:41");
		return FetchText(Date_qual);
	}

// Check the presence of Print Button in set up section
	public boolean PrintButton_state() {
		WebElement PrintBtn = driver.findElementByAccessibilityId("PrintButton");
		return IsElementVisibleStatus(PrintBtn);
	}

// Check the presence of WiringImg Button in set up section
	public boolean WiringImgButton_state() {
		WebElement WiringImg_Button = driver.findElementByAccessibilityId("WiringImgButton");
		return IsElementVisibleStatus(WiringImg_Button);
	}


// Check the presence of Delete Button in set up section
	public boolean DeleteBtn_state() {
		WebElement DeleteBtn = driver.findElementByAccessibilityId("DeleteButton");
		return IsElementVisibleStatus(DeleteBtn);
	}

	// Check the presence of Delete Button in set up section
	public void Click_DeleteBtn_report() {
		WebElement DeleteBtn_rpt = driver.findElementByAccessibilityId("DeleteButton");
		clickOn(DeleteBtn_rpt);
	}

// Check the presence of Delete Btn in Qual section
	public boolean Qual_DeleteBtn_state() {
		WebElement DeleteBtn_Qual = driver.findElementByAccessibilityId("DeleteQualificationButton");
		return IsElementVisibleStatus(DeleteBtn_Qual);
	}

	// Qual list Count

	public int QualCount() throws InterruptedException, IOException {
		List<WebElement> QUALList = driver.findElementByClassName("ListView")
				.findElements(By.className("ListViewItem"));
		return QUALList.size();
	}

	// Study file name for syncIn Setup
	public String qual_StudyFilename_text() {
		List<WebElement> Qual_StudyFilename = driver.findElementByClassName("ListViewItem")
				.findElements(By.className("TextBlock"));
		return FetchText(Qual_StudyFilename.get(0));

	}

	// Run number field for the study file for syncIn Setup
	public String get_QualStudyFile_Runnumberfield_() {
		List<WebElement> runstate = driver.findElementByClassName("ListViewItem")
				.findElements(By.className("TextBlock"));
		return FetchText(runstate.get(3));
	}

	// Comments given during saving the study for syncIn Setup

	public String qual_StudyFile_Comments_txt() {
		List<WebElement> comment = driver.findElementByClassName("ListViewItem")
				.findElements(By.className("TextBlock"));
		return FetchText(comment.get(5));
	}

	// click on DeleteQualificationButton
	public void click_DeleteQualificationButton() {
		WebElement DeleteBtn_Qual = driver.findElementByAccessibilityId("DeleteQualificationButton");
		clickOn(DeleteBtn_Qual);
	}

// Check the Setup tile default selected
	public String get_Setupheader_txt() {
		return FetchText(SetupsHeaderTxt);
	}

// Get the Setup count data form the Setup tile
	public String setupTile_countdata() {
		return FetchText(SetupTile_Count);
	}

// click on the setup tile
	public void click_SetupTile() {
		clickOn(Asset_SetupTile);
	}

// click on any setup file
	public void click_Setupfile(String SteupName) {
		WebElement Setup = driver.findElementByName(SteupName);
		clickOn(Setup);
	}

// Check the enable state of InitiateQual button under Setup tile
	public boolean InitiateQualBtn_state() {
		return IsElementEnabledStatus(InitiateQual_Btn);
	}

	// verify InitiateQual button under Setup tile is visible
	public boolean isInitiateQualBtn_Visible() {
		return IsElementVisibleStatus(InitiateQual_Btn);
	}

// click on Initiate Qual button under Setup tile
	public void click_InitiateQualBtn() {
		clickOn(InitiateQual_Btn);
	}

// Check the presence of Sop Number Field
	public boolean IsSOPNumberField_Presence() {
		WebElement SopName_Field = driver.findElementByAccessibilityId("SopProtocolTextBox");
		return IsElementEnabledStatus(SopName_Field);
	}

// Check the presence of Run Number Text Box
	public boolean IsRunNumberField_Presence() {
		WebElement RunNum_Field = driver.findElementByAccessibilityId("RunNumberTextBox");
		return IsElementEnabledStatus(RunNum_Field);
	}

// Enter Value into Sop Protocol Text Box
	public void Enter_SOPNum(String SOPNum) {
		WebElement SopNum_Field = driver.findElementByAccessibilityId("SopProtocolTextBox");
		clickOn(SopNum_Field);
		ClearText(SopNum_Field);
		enterText(SopNum_Field, SOPNum);
	}

// Fetch Sop Number Field text
	public String GetSOPNumText() {
		WebElement SopNum_Field = driver.findElementByAccessibilityId("SopProtocolTextBox");
		return FetchText(SopNum_Field);
	}

// Enter Value into Run Number Text Box
	public void Enter_RunNumber(String RunNum) throws InterruptedException {
		WebElement Run_Field = driver.findElementByAccessibilityId("RunNumberTextBox");
		clickOn(Run_Field);
		ClearText(Run_Field);
		Thread.sleep(500);
		enterText(Run_Field, RunNum);
	}

// Fetch Run Number Field text
	public String GetRunNumText() {
		WebElement RunNum_Field = driver.findElementByAccessibilityId("RunNumberTextBox");
		return FetchText(RunNum_Field);
	}

// Click on OK button for SOP/Run creation
	public void StartQual_OKButton() {
		WebElement OK_Btn_InSOPBlock = driver.findElementByAccessibilityId("StartQualificationOKButton");
		clickOn(OK_Btn_InSOPBlock);
	}



	// Time of the day window will display
	public void SOP_Next(String SNum) throws InterruptedException, IOException {
		Enter_SOPNum(SNum);
		StartQual_OKButton();
	}

	// SOP Invalid value where application should not move to next page and should
	// give an error message
	public void SOP_InvalidData(String SNum) throws InterruptedException, IOException {
		Enter_SOPNum(SNum);
		StartQual_OKButton();
	}



	// Run Invalid value where application should not move to next page and should
	// give an error message
	public void RUN_InvalidData(String RNum) throws InterruptedException, IOException {
		Enter_RunNumber(RNum);
		StartQual_OKButton();
	}
//verify the presence of TimeOfTheDay window 

	public boolean isTimeOfTheDay_alertbox_visible() {
		WebElement TimeOfTheDay_title = driver.findElementByAccessibilityId("TimeOfTheDay");
		return IsElementVisibleStatus(TimeOfTheDay_title);
	}
//fetch date from Time Of The Day window

	public String fetch_Date_TOD() {
		WebElement PART_PickerButton = driver.findElementByAccessibilityId("PART_PickerButton");
		return FetchText(PART_PickerButton);
	}

//fetch Hour from Time Of The Day window

	public String fetch_Hour_TOD() {
		WebElement Hours = driver.findElementByAccessibilityId("Hours");
		return FetchText(Hours);
	}

	// fetch Minute from Time Of The Day window

	public String fetch_Min_TOD() {
		WebElement Min = driver.findElementByAccessibilityId("Min");
		return FetchText(Min);
	}

	// fetch Second from Time Of The Day window

	public String fetch_Sec_TOD() {
		WebElement Sec = driver.findElementByAccessibilityId("Sec");
		return FetchText(Sec);
	}

// Check the presence of Qual tile
	public boolean qualTile_state() {
		return IsElementVisibleStatus(Asset_QualTile);
	}

// Check the presence of Qual tile
	public boolean qualTile_Header_Text() {
		WebElement QUAL_Header = driver.findElementByAccessibilityId("QualificationHeaderTextBlock");
		return IsElementVisibleStatus(QUAL_Header);
	}

// click on the Qual tile
	public void click_QualTile() {
		clickOn(Asset_QualTile);
	}

// Click on delete icon for qual study
	/*
	 * public void click_DeleteBtn() { WebElement Deletestudy =
	 * driver.findElementByAccessibilityId("DeleteQualificationButton");
	 * clickOn(Deletestudy); }
	 */
// Check the presence of Delete Popup Window
	public boolean DeletePopupWindowVisible() {
		WebElement Deletewindow = driver.findElementByAccessibilityId("Popup Window");
		return IsElementVisibleStatus(Deletewindow);
	}

// Click on Qual file delete icon

	public void click_qualstudy_DeleteBtn() {
		WebElement DeleteIcon = driver.findElementByAccessibilityId("DeleteQualificationButton");
		clickOn(DeleteIcon);
	}

// Get the Qual count data form the Setup tile
	public String qualTile_countdata() {
		return FetchText(QualTile_Count);
	}

// select the qual under qual tile
	public void qualfile() {
		WebElement qualbox = driver.findElementByName("VRT.DataModule.Asset.ViewModels.ActivityItemViewModel");
		clickOn(qualbox);
	}

// application should display alert msg when user doest have access to generatereport
	public void Click_GenerateReportsBtn() {
		WebElement GenerateReports = driver.findElementByAccessibilityId("GenerateReportsForQualButton");
		clickOn(GenerateReports);
	}


// check the presence of GenerateReportsForQualButton

	public boolean IsGenerateReportsBtn_Visible() {
		WebElement GenerateReports = driver.findElementByAccessibilityId("GenerateReportsForQualButton");
		return IsElementVisibleStatus(GenerateReports);
	}

	public boolean IsGenerateReportsBtn_Enable() {
		WebElement GenerateReports_Enbl = driver.findElementByAccessibilityId("GenerateReportsForQualButton");
		return IsElementEnabledStatus(GenerateReports_Enbl);

	}

// Check the presence of Reports tile
	public boolean reportsTile_state() {
		return IsElementVisibleStatus(Asset_ReportsTile);
	}

// Click on Copy to drive
	public void click_Copytodrive() {
		WebElement CopytodriveBox = driver.findElementByName("Copy to drive");
		clickOn(CopytodriveBox);
	}

// Check the presence of Select Folder window is visible
	public boolean SelectFolder_PopupWindowVisible() {
		WebElement SelectFolderWindow = driver.findElementByName("Select Folder");
		return IsElementVisibleStatus(SelectFolderWindow);
	}

// click on the Reports tile
	public void Click_reportsTile() {
		clickOn(Asset_ReportsTile);
	}

// Check the presence of DocsTile_Count in asset details page
	public boolean DocsTileCount_state() {
		return IsElementVisibleStatus(DocsTile_Count);
	}

// Check the presence of Upload Documents Button
	public boolean UploadDocumentsButton_state() {
		UploadDocs_Btn = driver.findElementByAccessibilityId("UploadDocumentsButton");
		return IsElementVisibleStatus(UploadDocs_Btn);
	}

	// verify the presence of Copy Document To Drive button
	public boolean CopyDocumentToDrive_State() {
		WebElement CopyToDrive_Document = driver.findElementByAccessibilityId("CopyDocumentToDrive");

		return IsElementVisibleStatus(CopyToDrive_Document);
	}

	// Click on asset Delete Icon
	public void DeleteAsset() {
		clickOn(assetDeleteIcon);
	}

	// Check the presence of report view(print) icon
	public boolean ReportView_Btn_State() {
		WebElement View_Button = driver.findElementByAccessibilityId("ViewButton");
		return IsElementVisibleStatus(View_Button);
	}

//Click On print buton under report section in order to view reports 
	public void click_printBtn_Report() {
		WebElement View_Button = driver.findElementByAccessibilityId("ViewButton");
		clickOn(View_Button);
	}

// click on the qualification sub section from Reports tile
	public void Click_QualReportsButton() {
		WebElement QualUnderReport = driver.findElementByAccessibilityId("QualificationReportsButton");
		clickOn(QualUnderReport);
	}

// Check the presence of Qual Reports Button under report section
	public boolean QualReportsButton_State() {
		WebElement QualReports_Btn = driver.findElementByAccessibilityId("QualificationReportsButton");
		return IsElementVisibleStatus(QualReports_Btn);
	}

// Check the presence of PassFail Reports Button under report section
	public boolean PassFailReportButton_State() {
		WebElement PassFailReport = driver.findElementByAccessibilityId("PassFailReportsButton");
		return IsElementVisibleStatus(PassFailReport);
	}

// click on the Pass/Fail sub section from Reports tile
	public void Click_PassFailReportBtn() {
		WebElement PassFailReport = driver.findElementByAccessibilityId("PassFailReportsButton");
		clickOn(PassFailReport);
	}

// Check the presence of Setup Reports Button under report section
	public boolean SetupReportsButton_State() {
		WebElement SetupReports_Btn = driver.findElementByAccessibilityId("SetupReportsButton");
		return IsElementVisibleStatus(SetupReports_Btn);
	}

	// click on the setup sub section from Reports tile
	public void Click_SetupReportsButton() {
		WebElement SetupReportsButton = driver.findElementByAccessibilityId("SetupReportsButton");
		clickOn(SetupReportsButton);
	}

// Get the Reports count data form the Setup tile
	public String reportsTile_countdata() {
		return FetchText(ReportsTile_Count);
	}

// Check the presence of ReportsTile_Count
	public boolean ReportsTile_Count_state() {
		return IsElementVisibleStatus(ReportsTile_Count);
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

// verify the presence of the assetDetail PageTitle
	public boolean assetDetailPageTitle_Visible() {
		return IsElementVisibleStatus(AssetDetailPageTitle);
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
		return tu.convert_StringDate_to_ActualDate_inCertainFormat(Dt);
	}

// Check the presence of Asset edit button
	public boolean assetEditBtn_state() {
		return IsElementVisibleStatus(AssetEditBtn);
	}

// Check the presence of Asset DeleteIcon button
	public boolean NewSetupCreate_Btn_Visible() {
		return IsElementVisibleStatus(NewSetupCreate_Btn);
	}

// Check the presence of Asset Copy button
	public boolean CopyAsset_state() {
		return IsElementVisibleStatus(CopyAsset);
	}

// Check the presence of Asset Copy button
	public boolean ReportsHeaderText_state() {
		WebElement ReportsHeader_TextBlock = driver.findElementByAccessibilityId("ReportsHeaderTextBlock");
		return IsElementVisibleStatus(ReportsHeader_TextBlock);
	}

// Click the Asset edit button
	public assetCreationPage click_assetEditBtn() throws InterruptedException, IOException {
		clickOn(AssetEditBtn);
		Thread.sleep(1000);
		return new assetCreationPage();
	}

// Click the Asset edit button to get alert msg..when admin does not have the  privilege to edit the asset
	public void click_assetEditBtn_alrt() throws InterruptedException {
		clickOn(AssetEditBtn);
		Thread.sleep(1000);
	}

// Fetch the alert message when user does not have the privilege to edit the asset
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}

	// Alert meg for copy setup
	public boolean CopySetup_AlertMsg() {

		WebElement CopysetupMsg = driver.findElementByAccessibilityId("Content_String");
		return IsElementVisibleStatus(CopysetupMsg);

	}

// Click the Back Button
	public assetHubPage ClickBackBtn() throws InterruptedException, IOException {
		clickOn(BackBtn);
		Thread.sleep(1000);
		return new assetHubPage();
	}

// Get the Asset info in Asset details page
	public String[] get_assetinfo() throws ParseException {
		String[] asstDetailinfo = new String[6];
		asstDetailinfo[0] = get_asset_IDinfo();
		asstDetailinfo[1] = get_asset_Modelinfo();
		asstDetailinfo[2] = get_asset_Mfginfo();
		asstDetailinfo[3] = get_asset_Typeinfo();
		asstDetailinfo[4] = get_asset_LastValidatedinfo();
		String[] AsstTitle = assetDetail_PageTitle().split(" - ");
		asstDetailinfo[5] = AsstTitle[1];

		return asstDetailinfo;
	}

// Click new Setup create button
	public Setup_defineSetupPage click_NewStupCreateBtn() throws InterruptedException, IOException {
		clickOn(NewSetupCreate_Btn);
		// Thread.sleep(1000);
		return new Setup_defineSetupPage();
	}

	// Fetch the alert message when does not have access to on new Setup create
	// button
	public void click_NewStupCreateBtn_alert() throws InterruptedException {
		clickOn(NewSetupCreate_Btn);
		Thread.sleep(1000);
	}

	// IS Copy setup button visible

	public boolean CopySetup_Btn_State() {
		return IsElementVisibleStatus(CopySetup_Btn);
	}


	// Click Copy Setup button functionality when there is only one Asset available
	public void CopyStupBtn_WITH_oneAsset() throws InterruptedException, IOException {
		clickOn(CopySetup_Btn);
		Thread.sleep(500);
	}

	// click on Ok button inside the alert box when user clicked on CopyStupBtn for
	// oneAsset
	public void CopySetupAlertBox_clickOk() {
		WebElement OK_Btn = driver.findElementByName("Ok");
		clickOn(OK_Btn);
	}

// Click VRT app close button
	public void click_vrtAppcloseBtn() throws InterruptedException {
		clickOn(VRTappClose_Btn);
		Thread.sleep(1000);
	}

// Upload Documents method under Asset details page
	public void uploadDoc_Assetdetails(String filepath, String filename) throws AWTException, IOException, InterruptedException {

		// switch to the file upload window
		WebElement alert = driver.switchTo().activeElement();
		Thread.sleep(1000);

		alert.sendKeys(filepath+filename);
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
		WebElement alertMsg = driver
				.findElementByName("Another file with same name already exists. Please use different name.");
		Thread.sleep(1000);
		// System.out.println(alertMsg.getAttribute("Name"));
		String msg = alertMsg.getAttribute("Name");
		return msg;
	}

// Verify if More than One Asset required for copy setup alert message displayed
	public String alertMeg_CopyAsset_WithOneAsset() throws InterruptedException {
		WebElement alertMsg = driver.findElementByName("To perform Copy Setup more than 1 asset required.");
		Thread.sleep(1000);
		// System.out.println(alertMsg.getAttribute("Name"));
		String msg = alertMsg.getAttribute("Name");
		return msg;
	}

// click Next button of the Asset Image Holder
	public void click_ImgNextkBtn() throws InterruptedException {
		clickOn(AssetHub_ImgHldr);
		Thread.sleep(500);
		AssetHub_ImgHldr_NextBtn = driver.findElementByAccessibilityId("NextButtonHorizontal");
		clickOn(AssetHub_ImgHldr_NextBtn);
		clickOn(SetupsHeaderTxt);
		Thread.sleep(5000);
		// clickOn(AssetHub_ImgHldr);
	}

// click Previous button of the Asset Image Holder
	public void click_ImgPrevkBtn() throws InterruptedException {
		clickOn(AssetHub_ImgHldr);
		Thread.sleep(500);
		AssetHub_ImgHldr_PrvsBtn = driver.findElementByAccessibilityId("PreviousButtonHorizontal");
		clickOn(SetupsHeaderTxt);
		Thread.sleep(5000);
		// clickOn(AssetHub_ImgHldr);
	}

//Capture image from the asset hub holder save it in Data folder
	public void Capture_AsstImg(String Img_Name) throws IOException {
		TestUtilities tu = new TestUtilities();
		tu.capture_element_screenshot(driver, AssetHub_ImgHldr, "TestData", Img_Name);
	}

// Verify delete pop up window displayed
	public boolean Deletepopupwindow() {
		WebElement dp = driver.findElementByAccessibilityId("Popup Window");
		return IsElementVisibleStatus(dp);
	}

//fetch text from delete pop up window
	public String get_text_DeleteAst_popup() {
		WebElement deleteAsset_popup = driver.findElementByAccessibilityId("Content_String");
		return deleteAsset_popup.getAttribute("Name");
	}

// Click on "Yes" button from the delete confirmation pop-up for asset 
	public assetHubPage Delete_ClickYesBtn() throws InterruptedException, IOException {
		WebElement Yesbtn = driver.findElementByAccessibilityId("Button1");
		clickOn(Yesbtn);
		return new assetHubPage();
	}

// Click on Yes Btn with files
	public void YesBtn_WithFiles() throws InterruptedException, IOException {
		WebElement Yesbtn = driver.findElementByAccessibilityId("Button1");
		clickOn(Yesbtn);
	}

// Click on "No" button from the delete confirmation pop-up
	public void Delete_ClickNoBtn() throws InterruptedException {
		WebElement Nobtn = driver.findElementByAccessibilityId("Button0");
		clickOn(Nobtn);
	}
	// user who has acess will navigate to next page
		public RW_FileSelctionPage Click_GenerateReportsBtn_RWpage() throws IOException {
			WebElement GenerateReports = driver.findElementByAccessibilityId("GenerateReportsForQualButton");
			clickOn(GenerateReports);
			return new RW_FileSelctionPage();
		}


	public void Click_SetupName(String SetupName) throws IOException {
		List<WebElement> SetupList = driver.findElementByClassName("ListView")
				.findElements(By.className("ListViewItem"));

		// System.out.println("Total setup created: " +SetupList.size());
		// Loop for the different setup tiles created
		for (int i = 0; i < SetupList.size(); i++) {
			// System.out.println("setup list: " +SetupList.get(i).getText());

			List<WebElement> SetupTileInfoList = SetupList.get(i).findElements(By.className("TextBlock"));

			// System.out.println(" setup info count: " + SetupTileInfoList.size());
			// Fetch all the contents of the Asset tile
			for (int j = 0; j < SetupTileInfoList.size(); j++) {
				// System.out.println("setup TileInfo: "+SetupTileInfoList.get(j).getText());

				String st = SetupTileInfoList.get(j).getText();
				if (st.equals(SetupName)) {
					SetupTileInfoList.get(j).click();

					break;
				} else {
					Actions ac = new Actions(driver);
					ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				}

			}
		}

	}

// ||Hard code || Select and click on the mentioned set up file

	public void ClickSetup_file() throws InterruptedException, IOException {
		WebElement Setupname = driver.findElementByName("Qual_case_51");
		clickOn(Setupname);
	}

// click on the Print_Button

	public void Click_Print_Button() throws AWTException, IOException, InterruptedException {
		Print_Button = driver.findElementByAccessibilityId("PrintButton");
		Thread.sleep(2000);
		clickOn(Print_Button);
	}

// Select any qual file and click on that

	public void Select_QualFile(String QN) throws InterruptedException, IOException {

		List<WebElement> QUALList = driver.findElementByClassName("ListView")
				.findElements(By.className("ListViewItem"));
		// Loop for the different serial number created
		for (int i = 0; i < QUALList.size(); i++) {
			// System.out.println("serial number : " + IrtdList.get(i).getText());
			List<WebElement> QUALInfoList = QUALList.get(i).findElements(By.className("TextBlock"));
			// System.out.println(" IRTD tile info count: " + IRTDTileInfoList.size());

			// Fetch all the contents of the Asset tile
			for (int j = 0; j < QUALInfoList.size(); j++) {
				// System.out.println("AssetTileInfo: "+IRTDTileInfoList.get(j).getText());

				String st = QUALInfoList.get(j).getText();
				if (st.equals(QN)) {
					QUALInfoList.get(j).click();

					break;
				} else {
					Actions ac = new Actions(driver);
					ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				}
			}
		}
	}

	// Select any report file and click on that
	public void Select_ReportFile(String SR) throws InterruptedException, IOException {

		List<WebElement> REPORTList = driver.findElementByClassName("ListView")
				.findElements(By.className("ListViewItem"));
// Loop for the different serial number created
		for (int i = 0; i < REPORTList.size(); i++) {
			// System.out.println("serial number : " + REPORTList.get(i).getText());
			List<WebElement> REPORTInfoList = REPORTList.get(i).findElements(By.className("TextBlock"));

			// System.out.println(" setup info count: " + SetupTileInfoList.size());
			// Fetch all the contents of the Asset tile
			for (int j = 0; j < REPORTInfoList.size(); j++) {
				// System.out.println("setup TileInfo: "+SetupTileInfoList.get(j).getText());

				String rt = REPORTInfoList.get(j).getText();
				if (rt.equals(SR)) {
					REPORTInfoList.get(j).click();

					break;
				} else {
					Actions ac = new Actions(driver);
					ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				}

			}
		}
	}

//Select Detailed Report
	public void Select_DetailedReport() throws InterruptedException, IOException {

		List<WebElement> DRList = driver.findElementByClassName("ListView").findElements(By.className("ListViewItem"));

		// Loop for the different serial number created
		for (int i = 0; i < DRList.size(); i++) {
			List<WebElement> DREPORTList = DRList.get(i).findElements(By.name("D"));

			for (int j = 0; j < DREPORTList.size(); j++) {
				DREPORTList.get(0).click();
			}
		}

	}
	
	// Select Summary Report
	public void Select_SummaryReport() throws InterruptedException, IOException {

		List<WebElement> SRList = driver.findElementByClassName("ListView").findElements(By.className("ListViewItem"));

		// Loop for the different serial number created
		for (int i = 0; i < SRList.size(); i++) {
			List<WebElement> SREPORTList = SRList.get(i).findElements(By.name("S"));

			for (int j = 0; j < SREPORTList.size(); j++) {
				SREPORTList.get(0).click();
			}
		}

	}

	// Get the setup report count
	public int setupReport_Count() throws InterruptedException, IOException {
		List<WebElement> REPORTList = driver.findElementByClassName("ListView")
				.findElements(By.className("ListViewItem"));
		return REPORTList.size();
	}

	// check the presence of date under setup report ( which has sync in to
	// application )
	public String get_Date_SetupReport() {
		WebElement Date_SR = driver.findElementByName("19-Mar-2020 13:52:45");
		return FetchText(Date_SR);
	}

// Select any Doc file and click on that
	public void Select_DocFile(String DC) throws InterruptedException, IOException {
		List<WebElement> DOCList = driver.findElementByName("VRT.DataModule.Asset.ViewModels.ActivityItemViewModel")
				.findElements(By.className("TextBlock"));

		// Loop for the different serial number created
		for (int i = 0; i < DOCList.size(); i++) {
			// System.out.println("serial number : " + IrtdList.get(i).getText());

			List<WebElement> DOCListInfoList = DOCList.get(i).findElements(By.className("TextBlock"));

			// System.out.println(" IRTD tile info count: " + IRTDTileInfoList.size());

			// Fetch all the contents of the Asset tile
			for (int j = 0; j < DOCListInfoList.size(); j++) {
				// System.out.println("AssetTileInfo: "+IRTDTileInfoList.get(j).getText());

				if (DOCListInfoList.get(j).getText().contains(DC)) {
					clickOn(DOCListInfoList.get(j));
					Thread.sleep(1000);
					break;

				} else {
					Actions ac = new Actions(driver);
					ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				}
			}
		}
	}

// Click the Copy to drive button
	public void click_Setup_CopyToDrive() throws InterruptedException {
		WebElement Copytodrive_Btn = driver.findElementByAccessibilityId("CopySetupToDriveButton");
		clickOn(Copytodrive_Btn);
		Thread.sleep(2000);
	}

// CopyToDrive Btn in QUAL sub group
	public void click_Qual_CopyToDrive_Btn() throws InterruptedException {
		WebElement CopyQualTo_Drive = driver.findElementByAccessibilityId("CopyQualToDrive");
		clickOn(CopyQualTo_Drive);
		Thread.sleep(2000);
	}

// CopyToDrive Btn in Setup Reports
	public void click_Report_CopyToDrive_Btn() throws InterruptedException {
		WebElement CopyToDrive_Report = driver.findElementByAccessibilityId("CopyReportToDrive");
		clickOn(CopyToDrive_Report);
		Thread.sleep(2000);
	}

	// CopyToDrive Btn state
	public boolean CopyToDrive_Btn_state() throws InterruptedException {
		WebElement CopyToDrive_Btn = driver.findElementByAccessibilityId("CopyReportToDrive");
		return IsElementVisibleStatus(CopyToDrive_Btn);
	}

// CopyToDrive Btn in Document Reports
	public void click_Docs_CopyToDrive_Btn() throws InterruptedException {
		WebElement CopyToDrive_Document_btn = driver.findElementByAccessibilityId("CopyDocumentToDrive");
		clickOn(CopyToDrive_Document_btn);
		Thread.sleep(2000);
	}

// verify the presence of the setupname
	public boolean SetupName_Visible() {
		WebElement Setupname = driver.findElementByName("manual 1 min sampling");
		return IsElementVisibleStatus(Setupname);
	}
	// Initiate Qualification button

	// verify the presence of the setupReportname
	public boolean SetupReportASST001B_Visible() {
		WebElement SetupReportname = driver.findElementByName("ASST001B");
		return IsElementVisibleStatus(SetupReportname);
	}

	// verify the presence of the Qual CopyToDrive button
	public boolean CopyQualToDrive_State() {
		WebElement CopyQualTo_Drive = driver.findElementByAccessibilityId("CopyQualToDrive");
		return IsElementVisibleStatus(CopyQualTo_Drive);
	}

// Select any folder present in the resource folder TestData for copying 
	// any files from the respective Asset tiles
//This method requires user input o which tile he has selected like 
	// Setup(default) or Qual, Reports or Doc

	public void selectFolder_CopyToDrive(String folderName, String tile)
			throws InterruptedException, AWTException, IOException {
		if (tile.equalsIgnoreCase("setup")) {
			click_Setup_CopyToDrive();
			// Select any folder present in the resource folder TestData
			tu.selectFolder(folderName);
		} else if (tile.equalsIgnoreCase("qual")) {
			click_Qual_CopyToDrive_Btn();
			tu.selectFolder(folderName);
		} else if (tile.equalsIgnoreCase("reports")) {
			click_Report_CopyToDrive_Btn();
			tu.selectFolder(folderName);
		} else if (tile.equalsIgnoreCase("docs")) {
			click_Docs_CopyToDrive_Btn();
			tu.selectFolder(folderName);
		} else {
			System.out.println("Incorrect Folder path name or wrong tile name selected");
		}

	}

//Click on Ok button under delete login pop upm (Not able to delete the asset with files)

	public assetHubPage ClickOK_btn() throws InterruptedException, AWTException, IOException {
		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		return new assetHubPage();

	}

// check the Login Popup presence when user  click upon print icon

	public boolean UserLoginPopupVisible() throws InterruptedException {
		WebElement LgInPopup = driver.findElementByName("Enter User Credentials");
		return IsElementVisibleStatus(LgInPopup);
	}

// Click edit Setup create button
	public Setup_defineSetupPage click_editStupBtn() throws InterruptedException, IOException {

		WebElement SetupEdit_Btn = driver.findElementByAccessibilityId("EditButton");
		clickOn(SetupEdit_Btn);
		// Thread.sleep(1000);
		return new Setup_defineSetupPage();
	}

	public Setup_defineSetupPage editStupBtn_Position_0() throws InterruptedException, IOException {
		List<WebElement> SetupEdit_Btn = driver.findElementsByAccessibilityId("EditButton");

		clickOn(SetupEdit_Btn.get(0));
		return new Setup_defineSetupPage();
	}

	public Setup_defineSetupPage editStupBtn_Position_1() throws InterruptedException, IOException {
		List<WebElement> SetupEdit_Btn = driver.findElementsByAccessibilityId("EditButton");

		clickOn(SetupEdit_Btn.get(1));
		return new Setup_defineSetupPage();
	}

// Check on-click functionality of PDF icon for Detailed report under Reports tile-Qualifications sub tab

	public boolean ReportView_Popupvisible() throws InterruptedException {
		driver.switchTo().activeElement();
		// Thread.sleep(500);
		WebElement ReportView_Popup = driver.findElementByAccessibilityId("HeadText");
		return IsElementVisibleStatus(ReportView_Popup);
	}

	// Verify the presence of "how do you want to open this file" window is
	// displaying
	public void click_PDFpopup_OkBtn() throws InterruptedException, AWTException {
		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		r = null;

	}

	// Right click on the Asset Creation page to invoke the bottom apps bar
	public void Rt_Click_AstDetails_Buttom_AppBar() {
		Actions ac = new Actions(driver);
		ac.contextClick().build().perform();
		ac = null;
	}

	// Verify the presence of Home button in the bottom apps bar
	public boolean check_Home_Buttom_AppBar_Presence() {
		WebElement bottomMenu_Home_Icon = driver.findElementByAccessibilityId("HomeAppBarButton");
		return IsElementVisibleStatus(bottomMenu_Home_Icon);
	}

	// Verify the presence of Apps Help icon/button in the bottom apps bar
	public boolean check_Help_Buttom_AppBar_Presence() {
		WebElement bottomMenu_AppHelp_Icon = driver.findElementByAccessibilityId("HelpAppBarButton");
		return IsElementVisibleStatus(bottomMenu_AppHelp_Icon);
	}

	// Verify the presence of WndsHelp Help icon/button in the bottom apps bar
	public boolean check_WndsHelp_Buttom_AppBar_Presence() {
		WebElement bottomMenu_WndsHelp_Icon = driver.findElementByAccessibilityId("WindowsHelpAppBarButton");
		return IsElementVisibleStatus(bottomMenu_WndsHelp_Icon);
	}

	// Verify the presence of About Help icon/button in the bottom apps bar
	public boolean check_About_Buttom_AppBar_Presence() {
		WebElement bottomMenu_About_Icon = driver.findElementByAccessibilityId("AboutAppBarButton");
		return IsElementVisibleStatus(bottomMenu_About_Icon);
	}

	// Click on the Home icon of the bottom apps bar to move to Main Hub page
	public MainHubPage Click_Home_Icon_AppBar() throws InterruptedException, IOException {
		Actions ac = new Actions(driver);
		ac.contextClick().build().perform();

		WebElement bottomMenu_Home_Icon = driver.findElementByAccessibilityId("HomeAppBarButton");
		clickOn(bottomMenu_Home_Icon);
		Thread.sleep(500);
		return new MainHubPage();
	}

	// Click on the Help icon of the bottom apps bar to move to Main Hub page
	public void Click_Help_Icon_AppBar() throws InterruptedException {
		Actions ac = new Actions(driver);
		ac.contextClick().build().perform();

		WebElement bottomMenu_AppHelp_Icon = driver.findElementByAccessibilityId("HelpAppBarButton");
		clickOn(bottomMenu_AppHelp_Icon);
		Thread.sleep(500);
	}

	// Click on the WndsHelp icon of the bottom apps bar
	public void Click_WndsHelp_Icon_AppBar() throws InterruptedException {
		Actions ac = new Actions(driver);
		ac.contextClick().build().perform();

		WebElement bottomMenu_WndsHelp_Icon = driver.findElementByAccessibilityId("WindowsHelpAppBarButton");
		clickOn(bottomMenu_WndsHelp_Icon);
		Thread.sleep(500);
	}

	// Click on the About icon of the bottom apps bar to invoke the ABout window
	public void Click_About_Icon_AppBar() throws InterruptedException {
		Actions ac = new Actions(driver);
		ac.contextClick().build().perform();

		WebElement bottomMenu_About_Icon = driver.findElementByAccessibilityId("AboutAppBarButton");
		clickOn(bottomMenu_About_Icon);
		Thread.sleep(500);
	}

	// Get the Asset details Help context header text on clicking Help icon of the
	// bottom apps bar
	public String get_Asstdetails_HelpMenu_HdrText() {
		WebElement AsstDetails_HelpMenu = driver.findElementByAccessibilityId("helpHeader");
		return FetchText(AsstDetails_HelpMenu);
	}

	// Verify the presence of About window on clicking the ABout icon in the bottom
	// apps bar
	public boolean check_About_wndw_Presence() {
		WebElement About_Wndw = driver.findElementByName("About");
		return IsElementVisibleStatus(About_Wndw);
	}

	// Get the Sw version info from the About window on clicking About icon of the
	// bottom apps bar
	public String get_SWVersion_About_Text() {
		WebElement SWVersion_About_info = driver.findElementByAccessibilityId("SoftwareVersion");
		return FetchText(SWVersion_About_info);
	}

	public int setupcount() {
		List<WebElement> SetupList = driver.findElementByName("VRT.DataModule.Asset.ViewModels.ActivityItemViewModel")
				.findElements(By.className("ListViewItem"));
		return SetupList.size();
	}

	// Get Setup File report name
	public String Get_ReportFile_Name() throws InterruptedException, IOException {
		List<WebElement> REPORTList = driver.findElementByClassName("ListViewItem")
				.findElements(By.className("TextBlock"));
		return FetchText(REPORTList.get(0));

	}

	// Check is setup tile empty

	public boolean IsSetupTile_Empty() throws IOException {
		List<WebElement> SetupList = driver.findElementByClassName("ListView")
				.findElements(By.className("ListViewItem"));
		// WebElement SetupList = driver.findElementByClassName("ListViewItem");
		return SetupList.isEmpty();
	}

	// Get Setup File name
	public String Get_Setup_Name() throws InterruptedException, IOException {
		List<WebElement> SetupList = driver.findElementByClassName("ListViewItem")
				.findElements(By.className("TextBlock"));
		return FetchText(SetupList.get(0));

	}

	// Get Setup created date File name
	public String Get_Setup_Date() throws InterruptedException, IOException {
		List<WebElement> SetupList = driver.findElementByClassName("ListViewItem")
				.findElements(By.className("TextBlock"));
		return FetchText(SetupList.get(1));

	}

}
