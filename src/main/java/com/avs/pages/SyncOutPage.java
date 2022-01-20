/**
 * @author manoj ghadei
 *
 */

package com.avs.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.avs.base.BaseClass;
import com.avs.utility.TestUtilities;

public class SyncOutPage extends BaseClass{
	TestUtilities tu = new TestUtilities();
	//Page element variable declaration definition
	WebElement SyncOutHeaderBtn = null;
	WebElement SyncOutBrowseBtn = null;
	WebElement SyncOutTextBox = null;
	WebElement SyncOutOKbtn = null;
		
	//Page element Initialize method
	private void initElements()
	{
		SyncOutHeaderBtn = driver.findElementByAccessibilityId("SyncOut");
		SyncOutBrowseBtn = driver.findElementByAccessibilityId("SyncOutFolderBrowseButton");
		SyncOutTextBox = driver.findElementByAccessibilityId("SyncOutFolderTextBox");
		SyncOutOKbtn = driver.findElementByAccessibilityId("SyncOutFolderOKButton");
		

	}
	
	//Constructor for initializing the page elements
	SyncOutPage() throws IOException
	{
		super();
		initElements();
	}
	
	//Release memory
	public void resetWebElements()
	{
		SyncOutHeaderBtn = null;
		SyncOutBrowseBtn = null;
		SyncOutTextBox = null;
		SyncOutOKbtn = null;
		
	}
	
	/*----------------------
	Methods of Sync In Page
	------------------------*/

	// SyncOutTextBox is visible
	public boolean SyncOutTextBoxVisible() throws InterruptedException {
		return IsElementVisibleStatus(SyncOutTextBox);
	}
	
	// Enter sync Out folder path
	public void enter_Filepath(String pathname) throws AWTException, IOException, InterruptedException{
		clickOn(SyncOutBrowseBtn);
		Thread.sleep(500);
		// switch to the file upload window
		WebElement alert = driver.switchTo().activeElement();
		Thread.sleep(1000);

		// enter the filename
		String filepath1 = System.getProperty("user.dir") +  "\\src\\test\\resources\\TestData\\";
		//System.out.println(filepath);
		alert.sendKeys(filepath1);
		Thread.sleep(500);

		// hit enter
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
		String fp2 = System.getProperty("user.dir") +  "\\src\\test\\resources\\TestData\\" + pathname;
		//System.out.println(filepath);
		alert.sendKeys(fp2);
		Thread.sleep(500);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

		// switch back
		driver.switchTo().activeElement();
		Thread.sleep(500);
	}

	
	// Click the Filter button
	/*public SyncOutInAssetListPage click_SyncInOK_btn() throws IOException {
		clickOn(SyncOutOKbtn);
		return new SyncInAssetListPage();
	}*/
	


}
