/**
 * @author ruchika.behura
 *
 */

package com.avs.pages;

import java.awt.AWTException;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.avs.pages.EquipmentPage;
import com.avs.utility.TestUtilities;
import com.avs.base.BaseClass;

public class FileManagementPage extends BaseClass {


	// FileManagementPage Element definition
	WebElement ArchiveBtn = null;
	WebElement ArchiveTextBox = null;
	WebElement SyncInBtn = null;
	WebElement SyncOutBtn = null;

	private void initElements() {
		ArchiveBtn = driver.findElementByAccessibilityId("Archive");
		SyncInBtn = driver.findElementByAccessibilityId("SyncIn");
		SyncOutBtn = driver.findElementByAccessibilityId("SyncOut");

	}

	FileManagementPage() throws IOException {
		super();
		initElements();

	}
	
	//Release memory
	public void resetWebElements()
	{
		ArchiveBtn = null;
		ArchiveTextBox = null;
		SyncInBtn = null;
		SyncOutBtn = null;
			
	}

	/*----------------------
	Methods of Archive Page
	------------------------*/
	// Click Archive Button
	public void ClickArchiveBtn() throws InterruptedException {
		clickOn(ArchiveBtn);
		Thread.sleep(500);
	}

	// Archive TextBox is Visible
	public boolean ArchiveTextBoxVisible() throws InterruptedException {
		ArchiveTextBox = driver.findElementByAccessibilityId("ArchiveFolderTextBox");
		return IsElementVisibleStatus(ArchiveTextBox);
	}
	

	// Check if SyncInHeaderBtn is displayed
	public boolean IsDisplayed_SyncInHeaderBtn() {
		return IsElementEnabledStatus(SyncInBtn);
	}

	// Move to SynIn Page by Clicking the header SyncIn Button
	public SyncInPage ClickSyncInBtn_SyncinPage(String UID, String PW) throws InterruptedException, IOException {
		clickOn(SyncInBtn);
		Thread.sleep(500);
		UserLoginPopup(UID, PW);
		return new SyncInPage();
	}
	
	// Verify if login Pop is displayed by Clicking the header SyncIn Button
	public void ClickSyncInBtn(String UID, String PW) throws InterruptedException, IOException {
		clickOn(SyncInBtn);
		Thread.sleep(500);
		UserLoginPopup(UID, PW);

	}



	/*----------------------
	Methods of Sync Out Page
	------------------------*/
	// Move to SynOut Page by Clicking the header SyncIn Button
	public SyncOutPage ClickSyncOutBtn_SyncOutPage(String UID, String PW) throws InterruptedException, IOException {
		clickOn(SyncOutBtn);
		Thread.sleep(500);
		UserLoginPopup(UID, PW);
		return new SyncOutPage();
	}
	
	// Verify if login Pop is displayed by Clicking the header SyncIn Button
	public void ClickSyncOutBtn(String UID, String PW) throws InterruptedException, IOException {
		clickOn(SyncOutBtn);
		Thread.sleep(500);
		UserLoginPopup(UID, PW);
	}

	// Fetch the alert message when a user does not have privilege to access
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}
}
