/**
* @author ruchika.behura
*
*/

package com.avs.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.avs.base.BaseClass;

public class Equipment_IRTDHubPage extends BaseClass {
	// EquipmentIRTDDetailspage EquipmentIRTDDetailspage;

	// EquipmentHubPage Element definition
	WebElement IRTD = null;
	WebElement Back_btn = null;
	WebElement SerialNo = null;
	WebElement Mfgcaldate = null;
	WebElement MfgcalDuedate = null;

	WebElement filter_btn = null;

	private void initElements() {

		IRTD = driver.findElementByName("IRTD");
		SerialNo = driver.findElementByName("Serial No: ");
		Mfgcaldate = driver.findElementByName("Mfg cal date: ");

		MfgcalDuedate = driver.findElementByName("Mfg cal due date: ");
		Back_btn = driver.findElementByAccessibilityId("ArrowGlyph");
		filter_btn = driver.findElementByAccessibilityId("FilterEquipmentsButton");

	}

	Equipment_IRTDHubPage() throws IOException {
		super();
		initElements();

	}

	// Release memory
	public void resetWebElements() {
		IRTD = null;
		Back_btn = null;
		SerialNo = null;
		Mfgcaldate = null;
		MfgcalDuedate = null;
		filter_btn = null;
	}

	// Click IRTD
	/*
	 * public EquipmentIRTDDetailspage ClickIrtdSerialNo() throws
	 * InterruptedException { clickOn(IrtdSerial); return EquipmentIRTDDetailspage;
	 */

	// Click/Select the IRTD serial number in IRTDHubPage
	public Equipment_IRTDDetailspage Click_IrtdSerialNo(String SN) throws InterruptedException, IOException {

		List<WebElement> IrtdList = driver.findElementByAccessibilityId("loggersGrid")
				.findElements(By.className("GridViewItem"));
		// List<WebElement> IrtdSerial = driver.findElementByName("Serial No:
		// ").findElements(By.className("TextBlock"));
		// System.out.println("Total IRTD Equipments created: " + IrtdList.size());

		// Loop for the different serial number created
		for (int i = 0; i < IrtdList.size(); i++) {
			// System.out.println("serial number : " + IrtdList.get(i).getText());

			List<WebElement> IRTDTileInfoList = IrtdList.get(i).findElements(By.className("TextBlock"));
			// System.out.println(" IRTD tile info count: " + IRTDTileInfoList.size());

			// Fetch all the contents of the Asset tile
			for (int j = 0; j < IRTDTileInfoList.size(); j++) {
				// System.out.println("AssetTileInfo: "+IRTDTileInfoList.get(j).getText());

				if (IRTDTileInfoList.get(j).getText().contains(SN)) {
					clickOn(IRTDTileInfoList.get(j));
					Thread.sleep(1000);
					break;
				}
			}
		}
		return new Equipment_IRTDDetailspage();
	}

	// Verify if the IRTD is present in IRTDHubPage
	public boolean Is_Irtd_visible(String SN) throws InterruptedException {

		List<WebElement> IrtdList = driver.findElementByAccessibilityId("loggersGrid")
				.findElements(By.className("GridViewItem"));

		boolean visible = false;
		// List<WebElement> IrtdSerial = driver.findElementByName("Serial No:
		// ").findElements(By.className("TextBlock"));
		// System.out.println("Total IRTD Equipments created: " + IrtdList.size());

		// Loop for the different serial number created
		for (int i = 0; i < IrtdList.size(); i++) {
			// System.out.println("serial number : " + IrtdList.get(i).getText());

			List<WebElement> IRTDTileInfoList = IrtdList.get(i).findElements(By.className("TextBlock"));
			// System.out.println(" IRTD tile info count: " + IRTDTileInfoList.size());

			// Fetch all the contents of the Asset tile
			for (int j = 0; j < IRTDTileInfoList.size(); j++) {
				// System.out.println("AssetTileInfo: "+IRTDTileInfoList.get(j).getText());

				if (IRTDTileInfoList.get(j).getText().contains(SN)) {
					visible = true;
					// clickOn(IRTDTileInfoList.get(j));
					Thread.sleep(500);
					break;
				}
			}
		}
		return visible;
	}

	
	
	public boolean isIRTDHeader_Visible() {
		return IsElementVisibleStatus(IRTD);

	}

	public boolean isSerialNo_Visible() {
		return IsElementVisibleStatus(SerialNo);

	}

	public boolean isMfgcaldate_Visible() {
		return IsElementVisibleStatus(Mfgcaldate);

	}

	public boolean isMfgcalduedate_Visible() {

		return IsElementVisibleStatus(MfgcalDuedate);

	}

	public EquipmentHubPage click_Back_btn() throws IOException {
		clickOn(Back_btn);
		return new EquipmentHubPage();
	}

	// is filter_btn visible

	public boolean isfilter_btn_Visible() {
		return IsElementVisibleStatus(filter_btn);

	}

	// click on filter_btn

	public void clickOn_filterbtn() {
		clickOn(filter_btn);
	}

	// Filter popup visible

	public boolean filter_popup_Visible() {
		WebElement Filter_popup = driver.findElementByName("Filter");
		return IsElementVisibleStatus(Filter_popup);

	}

	// FilterByBlock

	public boolean filter_By_Visible() {
		WebElement FilterBy = driver.findElementByAccessibilityId("FilterByBlock");
		return IsElementVisibleStatus(FilterBy);

	}

	// OKButton_Visible

	public boolean OKButton_Visible() {
		WebElement OKButton = driver.findElementByAccessibilityId("OKButton");
		return IsElementVisibleStatus(OKButton);

	}

	// CancelButton

	public boolean CancelButton_Visible() {
		WebElement CancelButton = driver.findElementByAccessibilityId("CancelButton");
		return IsElementVisibleStatus(CancelButton);

	}

	public String FetchTxt_from_filterByDD(int index) throws InterruptedException {
		List<WebElement> options = driver.findElementByAccessibilityId("FilterByComboBox")
				.findElements(By.className("ComboBoxItem"));

		return FetchText(options.get(index));
	}

	public void SelectOptions_from_filterByDD(int index) throws InterruptedException {
		List<WebElement> options = driver.findElementByAccessibilityId("FilterByComboBox")
				.findElements(By.className("ComboBoxItem"));

		clickOn(options.get(index));
	}

	// Select Date and click ok
	public void Select_datepickerfield(int index) throws InterruptedException {
		List<WebElement> options = driver.findElementsByAccessibilityId("PART_PickerButton");
		clickOn(options.get(index));
		Thread.sleep(1000);
		WebElement ok = driver.findElementByAccessibilityId("PART_SelectorOKButton");
		clickOn(ok);

	}

	// OKButton

	public void click_OKbtn_Filterpopup() throws InterruptedException {
		WebElement ok = driver.findElementByAccessibilityId("OKButton");
		clickOn(ok);
	}

	// CancelButton
	public void click_Cancelbtn_Filterpopup() throws InterruptedException {
		WebElement CancelButton = driver.findElementByAccessibilityId("CancelButton");
		clickOn(CancelButton);
	}

	// click on FilterByComboBox
	public void clickOn_FilterByComboBox() throws InterruptedException {
		WebElement FilterByComboBox = driver.findElementByAccessibilityId("FilterByComboBox");
		clickOn(FilterByComboBox);
	}

//FilterByComboBox
	public String FetchTxt_filterBY_selectedOption() throws InterruptedException {
		WebElement filterbytxt = driver.findElementByAccessibilityId("FilterByComboBox");

		return FetchText(filterbytxt);
	}

	// enter the serial no to filter

	public void enterSerialNo(String sl) {

		WebElement eqpSL = driver.findElementByAccessibilityId("EquipmentIDTextBox");
		ClearText(eqpSL);
		enterText(eqpSL, sl);

	}

	// FilterPopup

	public boolean is_FilterPopup_Visible() {

		boolean status = false;
		try {
			status = driver.findElementByAccessibilityId("FilterPopup").isDisplayed();
		}

		catch (Exception e) {
			System.out.println("FilterPopup is closed now");

		}
		return status;
	}

	// click on RefreshButton to clear the filters

	public void clickOn_RefreshFilterBtn() throws InterruptedException {
		WebElement RefreshButton = driver.findElementByAccessibilityId("RefreshButton");
		clickOn(RefreshButton);
	}

}
