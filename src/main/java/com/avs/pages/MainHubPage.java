/**
 * @author manoj.ghadei
 *
 */

package com.avs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.avs.base.BaseClass;

public class MainHubPage extends BaseClass {

	// Main Hub Page Element definition
	WebElement MainUILoggedinUserTitle = driver.findElementByAccessibilityId("UserDesignationTextBlock");
	WebElement MainUILoggedinUserName = driver.findElementByAccessibilityId("UserNameTextBlock");
	WebElement MainUIAdminTile = driver.findElementByName("Admin");
	WebElement MainUIAssetTile = driver.findElementByName("Assets");
	WebElement AssetCountInfoInAsstTile = driver.findElementByAccessibilityId("TitleCountTextBlock");
	WebElement MainUIPageTitle= driver.findElementByName("ValProbe RT System");
	WebElement MainUIEquipmentTitle= driver.findElementByName("Equipment");
	WebElement FileManagementTitle= driver.findElementByName("File Management");
	WebElement AuditTitle= driver.findElementByName("Audit");

	
	
	//Verify the Main Hub Page title name
	public boolean mainPageTitle() {
		return IsElementVisibleStatus(MainUIPageTitle);
	}
	
	
	//Verify the Logged in User credentials
	public String LoggedinUserName() {
		return FetchText(MainUILoggedinUserName);
	}
	
	//Signout Operation
	public LoginPage UserSignOut() throws InterruptedException {
		clickOn(MainUILoggedinUserName);
		Thread.sleep(1000);
		WebElement MainUISignOut = driver.findElementByAccessibilityId("SignoutButton");
		clickOn(MainUISignOut);
		Thread.sleep(1000);
		return new LoginPage();
	}
	
	//Click the Admin Tile
	public UserManagementPage ClickAdminTile_UMpage() throws InterruptedException {
		clickOn(MainUIAdminTile);
		Thread.sleep(1000);
		return new UserManagementPage();
	}
	
	//Click the Asset Tile
	public assetHubPage ClickAssetTile() throws InterruptedException {
		clickOn(MainUIAssetTile);
		Thread.sleep(1000);
		return new assetHubPage();
	}
	
	//Fetch the Asset count data in the Asset Tile
	public String AssetCountInAssetTileOfMainHubPage() throws InterruptedException {
		String AstCnt = FetchText(AssetCountInfoInAsstTile);
		//System.out.println("AstCnt in Main Hub Page: "+AstCnt);
		return AstCnt;
	}
	
	//Click the Equipment Tile
		public EquipmentHubPage ClickEquipmentTile() throws InterruptedException {
			clickOn(MainUIEquipmentTitle);
			Thread.sleep(1000);
			return new EquipmentHubPage();
		}
		
		//Click the Equipment Tile
				public FileManagementPage ClickFileManagementTitle() throws InterruptedException {
					clickOn(FileManagementTitle);
					Thread.sleep(1000);
					return new FileManagementPage();
				}
		
	//Click the Audit Title 
				public AuditPage ClickAuditTitle() throws InterruptedException {
					clickOn(AuditTitle);
					Thread.sleep(1000);
					return new AuditPage();
				}				
}
