/**
 * @author ruchika.behura
 *
 */
package com.avs.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.avs.base.BaseClass;
import com.avs.pages.LoginPage;
import com.avs.pages.MainHubPage;
import com.avs.pages.UserManagementPage;
import com.avs.pages.EquipmentHubPage;
import com.avs.pages.EquipmentPage;
import com.avs.utility.TestUtilities;
import com.avs.pages.IRTDDetailspage;
import com.avs.pages.IRTDHubPage;
import com.avs.pages.FileManagementPage;
import com.avs.pages.AuditPage;
import com.avs.pages.assetHubPage;
import com.avs.pages.assetCreationPage;
import com.avs.pages.assetDetailsPage;




import bsh.ParseException;

public class UserPrivilagesTest extends BaseClass {
	public ExtentReports extent;
	public ExtentTest extentTest;

	// Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	EquipmentHubPage EquipmentHubPage;
	EquipmentPage EquipmentPage;
	IRTDDetailspage IRTDDetailspage;
	IRTDHubPage IRTDHubPage;
	FileManagementPage FileManagementPage;
	AuditPage AuditPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	@BeforeTest
	public void UserCreationSetup() throws InterruptedException, IOException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		extent.addSystemInfo("VRT Version", "1.0.0.37");
		extent.addSystemInfo("BS Version", "0.6.13");
		extent.addSystemInfo("Lgr Version", "1.2.6");
		extent.addSystemInfo("User Name", "Manoj");
		extent.addSystemInfo("TestSuiteName", "Asset Creation Test");

		// Rename the User file (NgvUsers.uxx) if exists
		/*renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		UserManagementPage = LoginPage.DefaultLogin();
		LoginPage = UserManagementPage.FirstUserCreation("User1", getUID("adminFull"), getPW("adminFull"),
				getPW("adminFull"), "FullAdmin", "12345678", "abc@gmail.com");
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("User1");
		UserManagementPage.clickPrivRunQual();
		UserManagementPage.clickPrivCreateEditAsset();
		UserManagementPage.clickPrivCreateEditSetup();
		UserManagementPage.clickPrivRunCal();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();

		AppClose();
		Thread.sleep(1000); */

}

// After All the tests are conducted

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}

// Before Method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
	}

	@AfterMethod(alwaysRun = true)
	public void Teardown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getName() + " #"); // to add name in extent
																								// report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getThrowable() + " #"); // to add
																										// error/exception
																										// in extent
																										// report

			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1)); // to add screenshot in extent
																							// report
			// extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));
			// //to add screen cast/video in extent report
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName() + " #");
			// String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver,
			// result.getName());
			// extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2));
			// //to add screenshot in extent report

		}
		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report

		driver.quit();
	}
	//ADMN198.1- Verify if Administrator is able to access the default privilege-Create_Edit Equipment
	/*@Test(groups = { "Regression" }, description ="Verify if Administrator is able to access the default privilege-Create_Edit Equipment"
	  ) public void ADMN198b() throws InterruptedException, ParseException,
	  IOException, AWTException { extentTest = extent.
	  startTest("ADMN198b_Verify if Administrator is able to access the default privilege-Create_Edit Equipment");
	  SoftAssert s1 = new SoftAssert(); 
	  UserManagementPage.ClickNewUser();
	  UserManagementPage.UMCreation_MandatoryFields("Eqp3", getUID("SysAdmin"), "1Start@1AM", "1Start@1AM","AdminNew", "System Administrator"); 
	  UserLoginPopup(getUID("adminFull"),getPW("adminFull")); 
	  MainHubPage = UserManagementPage.ClickBackButn();
	  LoginPage = MainHubPage.UserSignOut();
	  Thread.sleep(1000);
	  LoginPage.EnterUserID("E3");
	  LoginPage.EnterUserPW("1Start@1AM");
	  LoginPage.ClickLoginBtn();
	  MainHubPage = LoginPage.EnterNewPWtext("Start@1AM");
	  EquipmentHubPage = MainHubPage.ClickEquipmentTile();
	  EquipmentPage=EquipmentHubPage.ClickAddButton();
	  EquipmentPage.EqipCreation_MandatoryFields("15","22","IRTD");
	  UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		  //UserLoginPopup("E3","3"); 
	  String ExpAlrtMsg = "Equipment  \"15\"  Created successfully."; String ActAlertMsg =
	  EquipmentPage.AlertMsg(); s1.assertEquals(ActAlertMsg,ExpAlrtMsg,"FAIL: Alert message Not Matched"); s1.assertAll();		 
} */
	// ADMN198.6
	/*@Test(groups = {"Regression" }, 
			description = "Verify if Administrator is able to access the default privilege-Delete Equipment")
	public void ADMN198c() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN198c_Verify if Administrator is able to access the default privilege-Delete Equipment");
		SoftAssert s2 = new SoftAssert();
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.ClickonIRTDlistbox();
		// IRTDHubPage.Click_IrtdSerialNo("600");
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("13");
		IRTDDetailspage.clickDeleteEquipmentIcon();
		s2.assertEquals(IRTDDetailspage.IRTD_DeletePopupWindow(), true, "FAIL: No pop up present");	
		IRTDDetailspage.ClickYesBtn();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		System.out.println("user deleted");
	}*/
	//ADMN198.1-a- Verify if Administrator is able to access the default privilege Edit Equipment
	/*@Test(groups = {"Regression" }, 
			description = "Verify if Administrator is able to access the default privilege Edit Equipment")
	public void ADMN198a() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN198a_Verify if Administrator is able to access the default privilege Edit Equipment");
		//SoftAssert s3 = new SoftAssert();
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage=EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("904","303","IRTD");
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		EquipmentHubPage=EquipmentPage.ClickBackBtn();
		 IRTDHubPage = EquipmentHubPage.ClickonIRTDlistbox();
		 IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("904");
		 IRTDDetailspage.EditIRTDEquip("Editing");
		 }*/
	//ADMN198.10
		/*@Test(groups = {"Regression" }, 
				description = "Verify if Administrator is able to access the default privilege-Archive")
		public void ADMN198_10() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("ADMN198_10_Verify if Administrator is able to access the default privilege-Archive");
			SoftAssert s4 = new SoftAssert();
			LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
			Thread.sleep(1000);
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FileManagementPage.ClickArchiveBtn();
			UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
			s4.assertEquals(FileManagementPage.ArchiveTextBoxVisible(), true, "FAIL: Texxt box not present");
	}*/
	//ADMN198.11-Verify if Administrator is able to access the default privilege-SyncIn	
		/*@Test(groups = {"Regression" }, 
				description = "Verify if Administrator is able to access the default privilege-SyncIn")
		public void ADMN198_11() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("ADMN198_11_Verify if Administrator is able to access the default privilege-SyncIn");
			SoftAssert s4 = new SoftAssert();
			LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
			Thread.sleep(1000);
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FileManagementPage.ClickSyncInBtn();
			UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
			s4.assertEquals(FileManagementPage.SyncInTextBoxVisible(), true, "FAIL: Texxt box not present");
	}*/
		//ADMN198.11a-Verify if Administrator is able to access the default privilege-SyncOut
		/*@Test(groups = {"Regression" }, 
				description = "Verify if Administrator is able to access the default privilege-SyncOut")
		public void ADMN198_11a() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("ADMN198_11a_Verify if Administrator is able to access the default privilege-SyncOut");
			SoftAssert s5 = new SoftAssert();
			LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
			Thread.sleep(1000);
			LoginPage = new LoginPage();
			MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FileManagementPage.ClickSyncOutBtn();
			UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
			s5.assertEquals(FileManagementPage.SyncOutTextBoxVisible(), true, "FAIL: Text box not present");
	}*/
		//ADMN198.13
	/*@Test(groups = {"Regression" }, description = "Verify if Administrator is able to access the default privilege-Access Audit Trail")
	    public void ADMN198_13() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN198_113-Verify if Administrator is able to access the default privilege-Access Audit Trail");
		SoftAssert s6 = new SoftAssert();
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		AuditPage = MainHubPage.ClickAuditTitle();
		s6.assertEquals(AuditPage.AuditHeadTitleVisible(), true, "FAIL: Title not present");
	}*/
	//ADMN198.14-Verify if Administrator is able to access the default privilege-Camera Access
	/*@Test(groups = {"Regression" }, 
			description = "Verify if Administrator is able to access the default privilege-Camera Access")
	public void ADMN198_14() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN198_14_Verify if Administrator is able to access the default privilege-Camera Access");
		SoftAssert s7 = new SoftAssert();
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Eqp3");
		//System.out.println("CLICKED ON EQIP3");
		UserManagementPage.click_UserImageTile();
		UserManagementPage.click_CameraIcon();
		s7.assertEquals(UserManagementPage.CameraOnTitleVisible(), true, "FAIL: Title not present");	
	}	*/
	
//ADMN198.15-Verify Administrator is unable to access the non-default privilege-Create Asset	
	/*@Test(groups = {"Regression" }, 
			description = "Verify Administrator is unable to access the non-default privilege-Create Asset")
	public void ADMN198_15() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN198_15_Verify Administrator is unable to access the non-default privilege-Create Asset");
		SoftAssert s8 = new SoftAssert();
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetHubPage.Click_AddButton();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetHubPage.AlertMsg();
		s8.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		s8.assertAll();
	}*/
	
	//ADMN198_15a: Verify Administrator is unable to access the non-default privilege-Edit Asset
	/*@Test(groups = {"Regression" }, 
			description = "Verify Administrator is unable to access the non-default privilege-Edit Asset")
	public void ADMN198_15a() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN198_15a_Verify Administrator is unable to access the non-default privilege Edit Asset");
		SoftAssert s9 = new SoftAssert();
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation("Altrt","506","HeatBath","HYdd","Ind");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetCreationPage.clickBackBtn();
		MainHubPage = assetHubPage.clickBackBtn();
		LoginPage = MainHubPage.UserSignOut();
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		  Thread.sleep(1000);
			MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
			assetHubPage = MainHubPage.ClickAssetTile();
			assetDetailsPage=assetHubPage.click_assetTile("Altrt");
			assetDetailsPage.click_assetEditBtn_alrt();	
			String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
			String ActAlertMsg = assetDetailsPage.AlertMsg();
			s9.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
			s9.assertAll();
}*/
	
	//ADMN198.16-Verify Administrator is unable to access the non-default privilege-Create Setups
	@Test(groups = {"Regression" }, 
			description = "Verify Administrator is unable to access the non-default privilege-Create Setups")
	public void ADMN198_16() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN198_16_Verify Administrator is unable to access the non-default privilege-Create Setups");
		SoftAssert s10 = new SoftAssert();
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation("Clim","509","HeatBath","HYdd","Ind");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetCreationPage.clickBackBtn();
		MainHubPage = assetHubPage.clickBackBtn();
		LoginPage = MainHubPage.UserSignOut();
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		  Thread.sleep(1000);
			MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
			assetHubPage = MainHubPage.ClickAssetTile();
			assetDetailsPage=assetHubPage.click_assetTile("Clim");
			assetDetailsPage.click_NewStupCreateBtn_alert();
			String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
			String ActAlertMsg = assetDetailsPage.AlertMsg();
			s10.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
			s10.assertAll();
	}
}