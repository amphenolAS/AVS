/**
 * @author manoj.ghadei
 *
 */

package com.avs.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.appium.java_client.windows.WindowsDriver;


public class BaseClass {
	
	//Declare the Windows Driver and make it Public/Static to be used throughout the classes
	public static WindowsDriver driver;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();


	// Launch App/Setup Configuration Function
	//Initialize Windows Driver
	public static void LaunchApp(String Url) {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("app", Url);
			
			driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			tdriver.set(driver);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
	}
	
	
	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}
	

	// Check if Element is present to be able to click or not
	public WebElement checkingElementClickable(WebElement element, long WaitTime) {
		return new WebDriverWait(driver, WaitTime).until(ExpectedConditions.elementToBeClickable(element));
	}

	// ClickOn any Element method
	public void clickOn(WebElement element) {
		try {
			checkingElementClickable(element, 5).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Check Visibility of the Element method
	public WebElement checkingVisibilityOfElement(WebElement element, long WaitTime) {
		return new WebDriverWait(driver, WaitTime).until(ExpectedConditions.visibilityOf(element));
	}
	
	// Enter Text to any Edit field method
	public void enterText(WebElement element, String text) {
		try {
			checkingVisibilityOfElement(element, 5).sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Enter Text to any Edit field method
	public void enterNumeric(WebElement element, int num) {
		try {
			String text = String.valueOf(num);
			checkingVisibilityOfElement(element, 5).sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Clear any Edit filed method
	public void ClearText(WebElement element) {
		try {
			checkingVisibilityOfElement(element, 5).clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Get Text for any Element method
	public String FetchText(WebElement element) {
		String text = null;
		try {
			text=checkingVisibilityOfElement(element, 5).getText();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return text;
	}
	
	
	//Very if the Element is Enabled or Not Method
	public boolean IsElementEnabledStatus(WebElement element) {
		boolean status = false;
		try {
			status=checkingVisibilityOfElement(element, 5).isEnabled();
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return status;
	}
	
	//Very if the Element is Visible or Not Method
	public boolean IsElementVisibleStatus(WebElement element) {
		boolean status = false;
		try {
			status=checkingVisibilityOfElement(element, 5).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return status;
	}
	
	//Very if any check-box Element is checked/slected or Not Method
	public boolean checkboxSelectStatus(WebElement element) {
		boolean status = false;
		try {
			status=checkingVisibilityOfElement(element, 5).isSelected();
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return status;
	}
	

	//Login Popup function to be called where invoked in respective pages 
	public void UserLoginPopup(String UN, String PW) throws InterruptedException {
		WebElement LgInPopup = driver.findElementByName("Enter User Credentials");
		WebElement LgInUID = driver.findElementByAccessibilityId("UserIdTextBox");
		WebElement LgInPW = driver.findElementByAccessibilityId("PasswordTextBox");
		WebElement LgInOK = driver.findElementByAccessibilityId("ValidateUserOK");
		if (LgInPopup.isDisplayed()) {
			LgInUID.sendKeys(UN);
			LgInPW.sendKeys(PW);
			LgInOK.click();
			Thread.sleep(1000);
		}		
	} 
	
	//Close the App any time using the Top right Close button 
	public void AppClose() throws InterruptedException {
		WebElement AppCloseBtn = driver.findElementByName("Close ValProbe RT");
		clickOn(AppCloseBtn);		
		Thread.sleep(1000);				
	} 
	
	
	// function to remove all occurrences of an element from an array
	public static String[] removeDuplicateStringinArray(String[] stringarray, String removalItem) {		
			// Move all other elements to beginning 
			int index = 0; 
			for (int i=0; i<stringarray.length; i++) {
				if (!(stringarray[i].equals(removalItem))) 
					stringarray[index++] = stringarray[i]; 
			}
			// Create a copy of stringarray[]
			return Arrays.copyOf(stringarray, index); 		
	}
	
	//Defining a User list with Credentials using HashMap
	//HashMap is basically used in App where there is a 
	//Different Users/Roles : Permission Based User Management System 
	//with Unique UN:PW (kay Value) comibination with no duplicates to be used
	public static HashMap<String, String> getUIDCredentials() {
		HashMap<String, String> UserMap = new HashMap<String, String>();
		
		UserMap.put("adminFull", "1:Welcome1@AM");
		UserMap.put("SysAdmin", "2:Welcome2@AM");
		UserMap.put("SysSupervisor", "3:Welcome3@AM");
		UserMap.put("SysOperator", "4:Welcome4@AM");	
		
		return UserMap;
	}
	
	//Fetch the UserName credentials from the above GetUNCredentials HashMap method
	public static String getUID(String role) {
		String UID = getUIDCredentials().get(role).split(":")[0];
		return UID;
	}
	
	//Fetch the Password credentials from the above GetUNCredentials HashMap method
	public static String getPW(String role) {
		String PW = getUIDCredentials().get(role).split(":")[1];
		return PW;
	}
	
	//File renaming/creating method
	public void renameFile(String filePath, String fileName) throws IOException {

		// File path
		String filepath = filePath;
		File file = new File(filepath + "/" + fileName);
		
		//System.out.println(file.exists());
		if (!file.exists()) {
			//file.createNewFile();
			System.out.println("Target File: " +fileName+ " NOT present");
		} else {
			String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
			File backupFile = new File(filepath + "/" + timestamp + "_"+fileName);
			file.renameTo(backupFile);
			System.out.println("Target file renamed");
		}				
	}
	
	/*//Login Popup presence 
	public boolean UserLoginPopupVisible() throws InterruptedException {
		WebElement LgInPopup = driver.findElementByName("Enter User Credentials");
		return IsElementVisibleStatus(LgInPopup);
	}
	
	//Close Login Popup 
	public void UserLoginPopupClose() throws InterruptedException {
		WebElement LgInPopupCancel = driver.findElementByName("Cancel");
		clickOn(LgInPopupCancel);
	}*/
	
	
}
