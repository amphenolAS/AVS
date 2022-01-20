/**
 * @author manoj ghadei
 *
 */

package com.avs.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avs.base.BaseClass;
import com.avs.utility.TestUtilities;

public class SyncInAssetListPage  extends BaseClass{
	TestUtilities tu = new TestUtilities();
	//Page element variable declaration definition
	WebElement SyncInUsr_chkbx = null;
	WebElement SyncInAudit_chkbx = null;
	WebElement SyncInEqp_chkbx = null;
	WebElement SyncInSelectAll_Btn = null;
	WebElement OKbtn = null;
	WebElement SyncIn_Cancelbtn = null;
	List<WebElement> buttons = null;
	
	
	//Page element Initialize method
	private void initElements()
	{
		SyncInUsr_chkbx = driver.findElementByAccessibilityId("SyncInUserCheckBox");
		SyncInAudit_chkbx = driver.findElementByAccessibilityId("SyncInAuditCheckBox");
		SyncInEqp_chkbx = driver.findElementByAccessibilityId("SyncInEquipmentCheckBox");
		SyncInSelectAll_Btn = driver.findElementByAccessibilityId("SyncInSelectAllButton");
		buttons = driver.findElementsByClassName("TextBlock");
		OKbtn = driver.findElementByName("OK");
		SyncIn_Cancelbtn = driver.findElementByName("Cancel");
		

	}
	
	//Constructor for initializing the page elements
	public SyncInAssetListPage() throws IOException {
		super();
		initElements();
	}
	
	//Release memory
	public void resetWebElements()
	{
		SyncInUsr_chkbx = null;
		SyncInAudit_chkbx = null;
		SyncInEqp_chkbx = null;		
		SyncInSelectAll_Btn = null;
		buttons = null;
		OKbtn = null;
		SyncIn_Cancelbtn = null;
		
	}
	

	/*----------------------
	Methods of SyncIn AssetList Page
	------------------------*/
	
	//Click the SelectAll button
	public void click_SelectAllBtn() throws InterruptedException {
		clickOn(SyncInSelectAll_Btn);
		Thread.sleep(1000);
	}
	// click on SyncInEquipmentCheckBox
		public void click_EquipmentCheckBox() {
			WebElement SyncIn_EquipmentCheckBox = driver.findElementByAccessibilityId("SyncInEquipmentCheckBox");
			clickOn(SyncIn_EquipmentCheckBox);
		}
		
	
	//Click the Ok button
	public void click_OkBtn() throws InterruptedException {
		
		//clickOn(SyncIn_Cancelbtn);
		//clickOn(OKbtn);	
		for (WebElement btn : buttons) {
			if (btn.getText().contains("OK")) {
				clickOn(btn);
			}
		}
		
		//clickOn(buttons.get(5));
		
		//clickOn(SyncIn_Cancelbtn);	
		Thread.sleep(1000);
	}
	
	//Click the Yes button of Confirm alert mssg
	public void click_AlrtYesBtn() {
		WebElement alrtMeg_YesBtn = driver.findElementByAccessibilityId("Button1");
		
		try {
			checkingElementClickable(alrtMeg_YesBtn, 10).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Click the OK button of Successful alert mssg
	public void click_Success_alrtMeg_OkBtn() {		
		WebElement Success_alrtMeg_OkBtn = driver.findElementByAccessibilityId("Button0");
		try {
			checkingElementClickable(Success_alrtMeg_OkBtn, 20).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	

}
