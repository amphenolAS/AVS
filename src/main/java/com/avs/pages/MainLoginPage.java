/**
 * @author manoj.ghadei
 *
 */

package com.avs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avs.base.BaseClass;


public class MainLoginPage extends BaseClass {
	
	//WindowsDriver driver = null;
	//Initializing the WindowsDriver Page elements using Constructor;	
	public MainLoginPage() {			
		PageFactory.initElements(driver, this);
	}
	
	//Main Login Page Element definition
	WebElement ProductName = driver.findElementByName("ValProbe RT System");
	@FindBy(name="UserId") WebElement MainLoginUID;
	
	//WebElement MainLoginUID = driver.findElementByName("UserId");
	//.findElementByAccessibilityId("LoginIDTextBox");
	WebElement MainLoginPW = driver.findElementByAccessibilityId("PasswordTextBox");
	WebElement MainLoginBtn = driver.findElementByAccessibilityId("LoginButton");
	WebElement MainLoginCnclBtn = driver.findElementByAccessibilityId("CancelButton");
	
	
	/*----------------------
	Methods of Main Login Page
	------------------------*/
	//Check Launch of Main App with Login page
	public boolean LaunchAppLoginScreen() {
		
		boolean stat = MainLoginUID.isEnabled();
		System.out.println(stat);
		return stat;
		/*boolean stat= IsElementEnabledStatus(MainLoginUID);
		System.out.println(stat);
		return stat;*/
	}
	
	
	//Login method
	public MainHubPage Login(String UN, String PW) throws InterruptedException 
	{		
		MainLoginUID.clear();
		MainLoginUID.sendKeys(UN);
		MainLoginPW.clear();
		MainLoginPW.sendKeys(PW);
		MainLoginBtn.click();
		Thread.sleep(1000);	
		
		return new MainHubPage();
	}
	
	
	

}
