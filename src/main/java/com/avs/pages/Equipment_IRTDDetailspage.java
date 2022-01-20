/**
* @author ruchika.behura
*
*/

package com.avs.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import com.avs.pages.Equipment_IRTDHubPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.avs.utility.TestUtilities;
import com.avs.base.BaseClass;

public class Equipment_IRTDDetailspage extends BaseClass {

	Equipment_IRTDHubPage Equipment_IRTDHubPage;
	TestUtilities tu = new TestUtilities();
	// EquipmentHubPage Element definition
	WebElement IRTDHeader = null;
	WebElement DeleteBtn = null;
	WebElement ID = null;
	WebElement SaveBtn = null;
	WebElement CancelBtn = null;
	WebElement serialNo = null;
	WebElement ModelNumber = null;
	WebElement ManufacturingCalDate = null;
	WebElement ManufacturingCalDueDate = null;
	WebElement Browse_btn = null;
	WebElement Camera_Btn = null;
	WebElement EquipmentImage = null;
	WebElement Back_btn = null;
	WebElement DocumentsButton = null;
	WebElement UploadDocs_Btn = null;
	WebElement DocsTile_Count = null;

	// Button1
	private void initElements() {
		IRTDHeader = driver.findElementByAccessibilityId("EquipmentsHeaderTextBlock");

		DocumentsButton = driver.findElementByAccessibilityId("DocumentsButton");
		UploadDocs_Btn = driver.findElementByAccessibilityId("UploadDocumentsButton");
		DocsTile_Count = driver.findElementByAccessibilityId("DocumentsCountTextBlock");

		DeleteBtn = driver.findElementByAccessibilityId("DeleteEquipmentsButton");
		ID = driver.findElementByAccessibilityId("AssetNameTextBox");
		SaveBtn = driver.findElementByAccessibilityId("SaveButton");

		CancelBtn = driver.findElementByAccessibilityId("CancelButton1");
		serialNo = driver.findElementByAccessibilityId("VRTEquipmentIDTextBlock");
		ModelNumber = driver.findElementByAccessibilityId("ModelNumberTextBox");
		ManufacturingCalDate = driver.findElementByAccessibilityId("CalibrationDateTextBlockView");
		ManufacturingCalDueDate = driver.findElementByAccessibilityId("CalibrationDueDateTextBlockView");
		Browse_btn = driver.findElementByAccessibilityId("EquipmentImageUploadButton");
		Camera_Btn = driver.findElementByAccessibilityId("EquipmentCameraUploadButton");
		EquipmentImage = driver.findElementByAccessibilityId("EquipmentImage");
		Back_btn = driver.findElementByAccessibilityId("ArrowGlyph");
	}

	Equipment_IRTDDetailspage() throws IOException {
		super();
		initElements();

	}

	// Release memory
	public void resetWebElements() {
		IRTDHeader = null;
		DeleteBtn = null;
		CancelBtn = null;
		ID = null;
		SaveBtn = null;
		serialNo = null;
		ModelNumber = null;
		ManufacturingCalDate = null;
		ManufacturingCalDueDate = null;
		Browse_btn = null;
		Camera_Btn = null;
		EquipmentImage = null;
		Back_btn = null;
		DocumentsButton = null;
		UploadDocs_Btn = null;
		DocsTile_Count = null;
	}

	// IRTD Details page Header is presence...
	public boolean IRTD_DetailsHeaderPresence() {
		return IsElementVisibleStatus(IRTDHeader);
	}

	// Back_btn

	public boolean is_Back_btn_Visible() {
		return IsElementVisibleStatus(Back_btn);
	}

	// is serialNo visible
	public boolean is_IRTDserialNo_Visible() {
		return IsElementVisibleStatus(serialNo);
	}

	// is saveBtn visible
	public boolean is_SaveBtn_Visible() {
		return IsElementVisibleStatus(SaveBtn);
	}

	// is CancelBtn visible
	public boolean is_CancelBtn_Visible() {
		return IsElementVisibleStatus(CancelBtn);
	}

	// DeleteBtn
	public boolean is_DeleteBtn_Visible() {
		return IsElementVisibleStatus(DeleteBtn);
	}

	// is ID visible

	public boolean is_ID_Visible() {
		return IsElementVisibleStatus(ID);
	}

	// is CalibrationDueDateTextBlockView visible

	public boolean is_ManufacturingCalDueDate_Visible() {
		return IsElementVisibleStatus(ManufacturingCalDueDate);
	}

	// ManufacturingCalDate
	public boolean is_ManufacturingCalDate_Visible() {
		return IsElementVisibleStatus(ManufacturingCalDate);
	}

	// Fetch text from ManufacturingCalDate

	public String fetch_ManufacturingCalDate() {
		return FetchText(ManufacturingCalDate);
	}

	// Fetch text from ManufacturingCalDueDate

	public String fetch_ManufacturingCalDueDate() {
		return FetchText(ManufacturingCalDueDate);
	}

	// is Browse_btn visible

	public boolean is_Browse_btn_Visible() {
		return IsElementVisibleStatus(Browse_btn);
	}

	// click on Browse_btn
	public void click_Browse_btn() {
		clickOn(Browse_btn);
	}

	public void Capture_EqpImg(String Img_Name) throws IOException {

		TestUtilities tu = new TestUtilities();
		tu.capture_element_screenshot(driver, EquipmentImage, "TestData", Img_Name);

	}

	// Camera_Btn
	public boolean is_Camera_Btn_Visible() {
		return IsElementVisibleStatus(Camera_Btn);
	}

	// Login Popup presence
	public boolean UserLoginPopupVisible() throws InterruptedException {
		WebElement LgInPopup = driver.findElementByName("Enter User Credentials");
		return IsElementVisibleStatus(LgInPopup);
	}

	// Delete Equipment
	public void clickDeleteEquipmentIcon() {
		clickOn(DeleteBtn);
	}

	// Delete Pop up Window is presence...
	public boolean IRTD_DeletePopupWindow() {
		WebElement DeletePopupWindow = driver.findElementByAccessibilityId("Popup Window");
		return IsElementVisibleStatus(DeletePopupWindow);
	}

	// clickYesBtn
	public void ClickYesBtn() {
		if (!IRTD_DeletePopupWindow()) {
			System.out.println("IRTD delete popup didnt display");
		} else {
			WebElement YesBtn = driver.findElementByAccessibilityId("Button1");
			clickOn(YesBtn);
		}
	}

	// Navigate to IRTD Hub page after Deleting IRTD in IRTDDetails page
	public Equipment_IRTDHubPage delete_IRTD(String UID, String PW) throws InterruptedException, IOException {
		clickDeleteEquipmentIcon();
		WebElement YesBtn = driver.findElementByAccessibilityId("Button1");
		clickOn(YesBtn);
		UserLoginPopup(UID, PW);
		return new Equipment_IRTDHubPage();
	}

	// Edit Equipment Name
	public void enter_IDname(String idNm) {
		ClearText(ID);
		enterText(ID, idNm);
	}

	// Click on save button after edited
	public void ClickSaveButton() throws InterruptedException {
		clickOn(SaveBtn);
		Thread.sleep(1000);
	}

	// Edit Functionality for IRTD Equipments
	public void enter_IRTDEquipName(String id) throws InterruptedException {
		enter_IDname(id);
		ClickSaveButton();
	}

	public void enter_EquipName(String id) throws InterruptedException {
		enter_IDname(id);
	}

	// fetch the Equip ID

	public String fetch_ID() {
		return FetchText(ID);
	}

	// ModelNumber

	public String fetch_ModelNumber() {
		return FetchText(ModelNumber);
	}

	public void clear_IDname() {
		ClearText(ID);
	}

	// Fetch the alert message when Supervisor not have the privilege to edit the
	// equipments
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}

	public String get_text_DeleteAst_popup() {
		WebElement deleteAsset_popup = driver.findElementByAccessibilityId("Content_String");
		return deleteAsset_popup.getAttribute("Name");
	}

	public Equipment_IRTDHubPage click_Back_btn() throws IOException, InterruptedException {
		clickOn(Back_btn);
		Thread.sleep(1000);
		return new Equipment_IRTDHubPage();
	}

	public Equipment_IRTDHubPage click_Back_btn_alert() throws IOException, InterruptedException {
		clickOn(Back_btn);
		WebElement yes_Button1 = driver.findElementByAccessibilityId("Button1");
		clickOn(yes_Button1);
		return new Equipment_IRTDHubPage();
	}
	// Click Camera Image button
	public void click_Img_Camera_Btn() throws InterruptedException {
		clickOn(Camera_Btn);
		Thread.sleep(5000);
	}

	public void capture_Camera_Img() throws InterruptedException {
		click_Img_Camera_Btn();

		WebElement Click_Camera_Wdw_capture_Btn = driver.findElementByName("Take Photo");
		clickOn(Click_Camera_Wdw_capture_Btn);
		Thread.sleep(500);
		WebElement Click_Camera_Wdw_Accept_Btn = driver.findElementByName("Accept");
		clickOn(Click_Camera_Wdw_Accept_Btn);
		Thread.sleep(500);

	}

	// EquipmentImage

	public void click_DeleteEquipImage() throws InterruptedException {
		clickOn(EquipmentImage);
		Thread.sleep(500);
		WebElement RemoveImageButton = driver.findElementByAccessibilityId("PopupRemoveImageButton");
		clickOn(RemoveImageButton);

	}

// click on DocumentsButton 

	public void click_DocumentsButton() throws InterruptedException {
		clickOn(DocumentsButton);

	}
	// UploadDocumentsButton

	public void click_UploadDocsBtn() throws InterruptedException {

		clickOn(UploadDocs_Btn);
		Thread.sleep(2000);
	}

	// Get the Docs count data form the Docs tile
	public String docsTile_countdata() {
		return FetchText(DocsTile_Count);
	}

	public void click_CopyToDrive() throws InterruptedException {
		WebElement Copytodrive_Btn = driver.findElementByAccessibilityId("CopyDocumentToDrive");
		clickOn(Copytodrive_Btn);
		Thread.sleep(2000);
	}

	public void selectFolder_CopyToDrive(String folderName) throws InterruptedException, AWTException, IOException {
		click_CopyToDrive();
		tu.selectFolder(folderName);
		Thread.sleep(2000);

	}

	public void Select_DocName(String DocName) throws IOException {
		List<WebElement> SetupList = driver.findElementByClassName("ListView")
				.findElements(By.className("ListViewItem"));

		for (int i = 0; i < SetupList.size(); i++) {

			List<WebElement> DocTileInfoList = SetupList.get(i).findElements(By.className("TextBlock"));

			for (int j = 0; j < DocTileInfoList.size(); j++) {

				String st = DocTileInfoList.get(j).getText();
				if (st.equals(DocName)) {
					DocTileInfoList.get(j).click();

					break;
				} else {
					Actions ac = new Actions(driver);
					ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				}

			}
		}

	}

	public void Click_Print_Button() throws AWTException, IOException, InterruptedException {
		WebElement Print_Button = driver.findElementByAccessibilityId("ViewButton");
		Thread.sleep(2000);
		clickOn(Print_Button);
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

	// click on Delete_Button
	public void Click_Delete_DocumentButton() throws AWTException, IOException, InterruptedException {
		WebElement Delete_Button = driver.findElementByAccessibilityId("DeleteButton");
		Thread.sleep(2000);
		clickOn(Delete_Button);
	}

	// ckick on yes button in delete windows popup

	public void Click_Yes_DeleteWindowPopup() throws AWTException, IOException, InterruptedException {
		WebElement Delete_Button = driver.findElementByAccessibilityId("Button1");
		Thread.sleep(2000);
		clickOn(Delete_Button);
	}

	// HistoryButton

	public void Click_HistoryButton() throws IOException {
		WebElement Historybtn = driver.findElementByAccessibilityId("HistoryButton");
		clickOn(Historybtn);
	}

	// Is PrintButton visible when user clicked on History tab
	public boolean is_PrintButton_visible() throws AWTException, IOException, InterruptedException {
		WebElement Print_Button = driver.findElementByAccessibilityId("PrintButton");
		return IsElementVisibleStatus(Print_Button);
	}

	// click on Print_Button
	public void clickon_PrintBtn() throws AWTException, IOException, InterruptedException {
		WebElement Print_Button = driver.findElementByAccessibilityId("PrintButton");
		clickOn(Print_Button);
	}

	// FILE NAME

	public boolean is_FILENAME_visible_HISTORYTab() throws AWTException, IOException, InterruptedException {
		WebElement Filename_Tab = driver.findElementByName("FILE NAME");
		return IsElementVisibleStatus(Filename_Tab);
	}

	// STUDYTYPE

	public boolean is_STUDYTYPEvisible_HISTORYTab() throws AWTException, IOException, InterruptedException {
		WebElement STUDYTYPE_Tab = driver.findElementByName("STUDYTYPE");
		return IsElementVisibleStatus(STUDYTYPE_Tab);
	}

	// DATE

	public boolean is_DATEvisible_HISTORYTab() throws AWTException, IOException, InterruptedException {
		WebElement DATE_Tab = driver.findElementByName("DATE");
		return IsElementVisibleStatus(DATE_Tab);
	}

	// Action
	public boolean is_ACTIONvisible_HISTORYTab() throws AWTException, IOException, InterruptedException {
		WebElement Action_tab = driver.findElementByName("Action");
		return IsElementVisibleStatus(Action_tab);
	}
	
	
}
