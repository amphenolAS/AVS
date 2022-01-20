/**
* @author manoj.ghadei
*
*/

package com.avs.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.avs.base.BaseClass;

public class RW_FileSelctionPage extends BaseClass {

	// Page element variable declaration definition
	WebElement AssetsName = null;
	WebElement AssetsID = null;
	WebElement ReportButton = null;
	WebElement ExportToExcel_Button = null;

	// Page element Initialize method
	private void initElements() {
		AssetsName = driver.findElementByAccessibilityId("AssetsNameTextBlock");
		AssetsID = driver.findElementByAccessibilityId("EqIdTextBox");
		ReportButton = driver.findElementByAccessibilityId("ReportButton");
		ExportToExcel_Button = driver.findElementByAccessibilityId("ExportCSV");

	}

	// Constructor for initializing the page elements
	RW_FileSelctionPage() throws IOException {
		super();
		initElements();
	}

// Release memory
	public void resetWebElements() {
		AssetsName = null;
		AssetsID = null;
		ReportButton = null;
		ExportToExcel_Button = null;

	}

// verify the presence of  Asset Name

	public boolean assetDetailTitle_Visible() {
		return IsElementVisibleStatus(AssetsName);
	}

// verify the presence of Asset Id

	public boolean assetID_Visible() {
		return IsElementVisibleStatus(AssetsID);
	}

// verify the presence of Asset Type (HARD CODE because This asset  has sync In through application)

	public boolean assetType_Visible() {
		WebElement AssetsType = driver.findElementByName("HeatBath");

		return IsElementVisibleStatus(AssetsType);
	}

	// select Temperature

	public RW_CycleSelectionPage Select_Temperature_ClickOK() throws IOException {
		WebElement sensorType = driver.findElementByName("Temperature");
		clickOn(sensorType);
		WebElement ok = driver.findElementByName("OK");
		clickOn(ok);
		return new RW_CycleSelectionPage();

	}

// verify the presence of Manufacturer  (HARD CODE because This asset  has sync In through application)

	public boolean assetManufacturer_Visible() {
		WebElement AssetsManufacturer = driver.findElementByName("Kaye");

		return IsElementVisibleStatus(AssetsManufacturer);
	}

	// click on ReportButton

	public void Click_ReportButton() {
		clickOn(ReportButton);
	}

	// click on ExportToExcel_Button
	public void click_ExportToExcelBtn() {
		clickOn(ExportToExcel_Button);
	}

	public void selectFolder(String folderpath) throws AWTException, IOException, InterruptedException {
		// switch to the file upload window
		WebElement alert = driver.switchTo().activeElement();
		Thread.sleep(5000);

		// enter the filename
		// String folderpath = System.getProperty("user.home") + "\\Documents";
		// System.out.println(folderpath);
		alert.sendKeys(folderpath);
		Thread.sleep(500);

		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_ENTER);

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		// switch back
		driver.switchTo().activeElement();
		Thread.sleep(500);
	}

	// displayMessageTextBlock
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}

	// Right Click anywhere in the Report File selection Page to invoke the Bottom
	// app status
	// bar
	public void rightclickon_RWFSPage() throws InterruptedException {
		Actions ac = new Actions(driver);
		ac.contextClick(AssetsName).build().perform();
		Thread.sleep(2000);
	}

	// Click the App Home button in the Bottom app status bar
	public MainHubPage clickHomeIcon() throws InterruptedException, IOException {
		WebElement appHome = driver.findElementByAccessibilityId("HomeAppBarButton");
		clickOn(appHome);
		Thread.sleep(1000);
		return new MainHubPage();
	}

}
