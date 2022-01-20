/**
 * @author ruchika.behura
 *
 */

package com.avs.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

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
import com.avs.pages.PreferencesPage;
import com.avs.pages.Setup_defineSetupPage;
import com.avs.pages.AuditPage;
import com.avs.pages.EquipmentPage;
import com.avs.pages.Equipment_IRTDHubPage;
import com.avs.pages.FileManagementPage;
import com.avs.pages.UserManagementPage;
import com.avs.pages.assetCreationPage;
import com.avs.pages.assetHubPage;
import com.avs.pages.assetDetailsPage;
import com.avs.pages.SyncInPage;
import com.avs.pages.SyncOutPage;
import com.avs.pages.EquipmentPage;
import com.avs.pages.SyncInAssetListPage;
import com.avs.utility.TestUtilities;
import com.avs.utility.userManagementUtility;
import com.avs.pages.EquipmentHubPage;
import com.avs.pages.IRTDHubPage;
import com.avs.pages.IRTDDetailspage;
import com.avs.pages.RW_FileSelctionPage;
import com.avs.pages.Equipment_IRTDDetailspage;


public class UM2 extends BaseClass {

	public UM2() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExtentReports extent;
	public ExtentTest extentTest;
	TestUtilities tu = new TestUtilities();

	// Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	AuditPage AuditPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	FileManagementPage FileManagementPage;
	SyncInPage SyncInPage;
	SyncOutPage SyncOutPage;
	SyncInAssetListPage SyncInAssetListPage;
	EquipmentHubPage EquipmentHubPage;
	Equipment_IRTDHubPage IRTDHubPage;
	Equipment_IRTDDetailspage Equipment_IRTDDetailspage;
	Setup_defineSetupPage Setup_defineSetupPage;
	EquipmentPage EquipmentPage;
	PreferencesPage PreferencesPage;
	RW_FileSelctionPage RW_FileSelctionPage;

	@BeforeTest
	public void UserCreationSetup() throws InterruptedException, IOException, AWTException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		extent.addSystemInfo("VRT Version", "1.0.0.37");
		extent.addSystemInfo("BS Version", "0.6.13");
		extent.addSystemInfo("Lgr Version", "1.2.6");
		extent.addSystemInfo("User Name", "Manoj");
		extent.addSystemInfo("TestSuiteName", "Asset Creation Test");

		// Rename the User file (NgvUsers.uxx) if exists

		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");

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
		// SUPERVISOR user creation
				MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
				UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
				UserManagementPage.CreateSupervisorUser(getUID("adminFull"), getPW("adminFull"), "Sup1",
						getUID("SysSupervisor"), "4Start@4AM", "SUpNew", "123345678", "newSup@gmail.com");
				MainHubPage = UserManagementPage.ClickBackButn();
				LoginPage = MainHubPage.UserSignOut();
				Thread.sleep(500);
				MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(getUID("SysSupervisor"), "4Start@4AM",
						getPW("SysSupervisor"));
				LoginPage = MainHubPage.UserSignOut();

				// OPERATOR user creation
				MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
				UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
				UserManagementPage.CreateOperatorUser(getUID("adminFull"), getPW("adminFull"), "Ope1", getUID("SysOperator"),
						"6Start@6AM", "OperatorNew", "12345678", "newOpe@gmail.com");
				MainHubPage = UserManagementPage.ClickBackButn();
				LoginPage = MainHubPage.UserSignOut();
				Thread.sleep(500);
				MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(getUID("SysOperator"), "6Start@6AM", getPW("SysOperator"));
				LoginPage = MainHubPage.UserSignOut();

				MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
				FileManagementPage = MainHubPage.ClickFileManagementTitle();
				SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPage(getUID("adminFull"), getPW("adminFull"));
				SyncInPage.enter_Filepath("syncin");
				SyncInPage.click_FltrBtn();
				SyncInAssetListPage = SyncInPage.click_SyncInOK_btn();
				SyncInAssetListPage.click_EquipmentCheckBox();
				SyncInAssetListPage.click_SelectAllBtn();
				SyncInAssetListPage.click_OkBtn();
				SyncInAssetListPage.click_AlrtYesBtn();
				Thread.sleep(7000);
				AppClose();
			
				Thread.sleep(2000);
	

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
			// //to add screencast/video in extent report
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

	
	//UM028-Confirm if the user  is able to perform access all the privilages
   // UM029 - Check if the Operator can access the privillaged modules and modules which the permissions are denied, an appropriate warning message appears as “User doesn’t have sufficient privileges to perform this operation
			
	
	@Test(groups = {
	"Regression" }, description = "UM029A-Verify if Operator is able to access the default privilege-Create  Assets")
public void UM029A() throws InterruptedException, ParseException, IOException, AWTException {
extentTest = extent
		.startTest("UM029A-Verify if Operator is able to access the default privilege-Create  Assets");
SoftAssert sa = new SoftAssert();
MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
assetHubPage = MainHubPage.Click_AssetTile2();
assetCreationPage = assetHubPage.Click_AddAssetButton();
assetCreationPage.assetCreation("oppo2", "1012", "HeatBath", "HYdd", "Ind");
UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
String ExpMsg = "Data saved successfully";
String ActMsg = assetCreationPage.AlertMsg();
sa.assertEquals(ActMsg, ExpMsg, "FAIL: Alert message Not Matched");
sa.assertAll();
}

	
// UM029B - Verify if Operator is able to access the default privilege-Edit
// Assets

@Test(groups = {
		"Regression" }, description = "UM029B - Verify if Operator is able to access the default privilege-Edit Assets ")
public void UM029B() throws InterruptedException, ParseException, IOException, AWTException {
	extentTest = extent.startTest("UM029B - Verify if Operator is able to access the default privilege-Edit Assets ");
	SoftAssert s = new SoftAssert();
	MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
	assetHubPage = MainHubPage.Click_AssetTile2();
	assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
	assetCreationPage = assetDetailsPage.click_assetEditBtn();
	s.assertEquals(assetCreationPage.IsEditAssetscreenDisplayed(), true,
			"FAIL:User should be able to access the default privilege-Edit Assets");
	s.assertAll();
}

@Test(groups = {
		"Regression" }, description = "UM029C-Verify if Operator is unable to access the non-default privilege-Delete Assets")
public void UAM029C() throws InterruptedException, ParseException, IOException, AWTException {
	extentTest = extent
			.startTest("UM029C-Verify if Operator is unable to access the non-default privilege-Delete Assets");
	SoftAssert s = new SoftAssert();
	MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
	assetHubPage = MainHubPage.Click_AssetTile2();
	assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
	assetDetailsPage.DeleteAsset();
	UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
	String ExpAlrtMsg = "User do not have permission to perform this operation";
	String ActAlertMsg = assetDetailsPage.AlertMsg();
	s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User is unable to access Delete Assets");
	s.assertAll();
}

//UAM029D
	@Test(groups = {
			"Regression" }, description = "UAM029D-Verify if Operator is  unable to access the non-default privilege-Archive data")
	public void UAM029D() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("UAM029D-Verify if Operator is  unable to access the non-default privilege-Archive data");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickArchiveBtn();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = FileManagementPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Operator should be unable to access Archive data");
		s.assertAll();
	}
	
	// UAM029E
	@Test(groups = {
			"Regression" }, description = "UAM029E-Verify if Operator is unable to access the non-default privilege-Admin")
	public void UAM029E() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("UAM029E-Verify if Operator is unable to access the non-default privilege-Admin");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		MainHubPage.ClickAdminTile();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = MainHubPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Operator should not be able to access Admin");
		sa.assertAll();
	}

	
	//-----------------------------------------------------------------------------------------------------------
	
	
	//UAM029F
	@Test(groups = {
			"Regression" }, description = "UAM029F - Verify if Operator is able to access the non-default privilege-Manual syncIn")
	public void UAM029F() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("UAM029F-Verify if Operator is able to access the non-default privilege-Manual syncIn");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncInBtn(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsgForSyncIn = "User do not have permission to perform this operation";
		String ActAlertMsgForSyncIn = FileManagementPage.AlertMsg();
		s.assertEquals(ExpAlrtMsgForSyncIn, ActAlertMsgForSyncIn,
				"FAIL: Operator is able to access the non-default privilege-Manual sync In");
		s.assertAll();
	}
	

	// UAM029G
	@Test(groups = {
			"Regression" }, description = "UAM029G-Verify if Operator is able to access the non-default privilege-Manual syncOut")
	public void UAM029G() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("UAM029G-Verify if Operator is able to access the non-default privilege-Manual syncOut");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncInBtn(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsgForSyncOut = "User do not have permission to perform this operation";
		String ActAlertMsgForSyncOut = FileManagementPage.AlertMsg();
		s.assertEquals(ExpAlrtMsgForSyncOut, ActAlertMsgForSyncOut,
				"FAIL: Operator is able to access the non-default privilege-Manual syncOut");
		s.assertAll();
	}
	
	// UAM029H
	
		@Test(groups = {"Regression" }, description = "UAM029H-Verify if Operator is able to access the default privilege-Access Audit trail")
		public void UAM029H() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("UAM029H-Verify if Operator is able to access the default privilege-Access Audit trail");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
			AuditPage = MainHubPage.ClickAuditTitle();
			sa.assertEquals(AuditPage.AuditHeadTitleVisible(), true, "FAIL:Operator should be able to access Audit trail");
			sa.assertAll();
		}
		
   // UAM029I
		
		@Test(groups = {
				"Regression" }, description = "UAM029I-Verify  Operator is unable to access the non-default privilege-Create Setups")
		public void UAM029I() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent
					.startTest("UAM029I- Verify Operator is unable to access the non-default privilege-Create Setups");
			SoftAssert s = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
			assetHubPage = MainHubPage.Click_AssetTile2();
			assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
			assetDetailsPage.click_NewStupCreateBtn_alert();
			String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
			String ActAlertMsg = assetDetailsPage.AlertMsg();
			s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User Is able to access the non-default privilege-Delete Assets");
			s.assertAll();
		}

		
		// UAM029J
		
		@Test(groups = { "Regression" }, description = "UAM029J-Verify if Operator is unable to access the "
				+ "non-default privilege-Create- Equipments")
		public void UAM029J() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest(
					"UAM029J-Verify if Operator is able to access the " + "non-default privilege-Create- Equipments");
			SoftAssert s = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
			EquipmentHubPage = MainHubPage.ClickEquipmentTile();
			EquipmentHubPage.Alert_ClickAddBtn();
			String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
			String ActAlertMsg = EquipmentHubPage.AlertMsg();
			s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Operator User is able to create Equipment ");
			s.assertAll();
		}
		
		
		// UAM029k
		@Test(groups = { "Regression" }, description = "UAM029k-Verify if Operator is not able to access the "
				+ "non-default privilege-Edit Equipments")
		public void UAM029k() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent
					.startTest("UAM029k-Verify if Operator is able to access the non-default privilege-Edit Equipments");
			SoftAssert s = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
			EquipmentHubPage = MainHubPage.ClickEquipmentTile();
			IRTDHubPage = EquipmentHubPage.click_IRTDTile();
			Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
			Equipment_IRTDDetailspage.enter_IRTDEquipName("Editing");
			String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
			String ActAlertMsg = EquipmentHubPage.AlertMsg();
			s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Operator user able to edit equipment");
			s.assertAll();
		}
		// UAM029L
		@Test(groups = { "Regression" }, description = "UAM029L-Verify if Operator is unable to access the "
				+ "non-default privilege-Delete Equipments")
		public void UAM029L() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest(
					"UAM029L-Verify if Operator is unable to access the non-default " + "privilege-Delete Equipments");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
			EquipmentHubPage = MainHubPage.ClickEquipmentTile();
			IRTDHubPage = EquipmentHubPage.click_IRTDTile();
			Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
			Equipment_IRTDDetailspage.clickDeleteEquipmentIcon();
			Equipment_IRTDDetailspage.ClickYesBtn();
			UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
			Thread.sleep(1000);
			String ExpAlrtMsg = "User do not have permission to perform this operation";
			String ActAlertMsg = tu.get_AlertMsg_text();
			sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Operator able to Delete Equipments");
			sa.assertAll();
		}

	// UAM029M-Verify if Operator is unable to access the non-default privilege-Copy
	// Files_Reports
		@Test(groups = { "Regression" }, description = "UAM029M-Verify if Operator is unable to access the "
				+ "non-default privilege-Copy Files_Reports_Setup reports")
		public void UAM029M() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("UAM029M-Verify if Operator is unable to access the non-default "
					+ "privilege-Copy Files_Reports_Setup reports");
			SoftAssert s = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
			assetHubPage = MainHubPage.Click_AssetTile2();
			assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
			assetDetailsPage.Click_reportsTile();
			assetDetailsPage.Click_SetupReportsButton();
			assetDetailsPage.Select_ReportFile("manual 1 min sampling");
			assetDetailsPage.selectFolder_CopyToDrive("syncin", "reports");
			String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
			String ActAlertMsg = tu.get_AlertMsg_text();
			s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Operator User is able to perform copy to drive ");
			s.assertAll();
		}
  //--------------------------------------------------------------------------------------------------------------------------
	//UAM030-Confirm the user Supervisor is able access the specified privilaged modules and  
		//For modules which the permissions are denied, an appropriate warning message appears
		//as “User doesn’t have sufficient privileges to perform this operation!.	
		
		//// UAM030A
		@Test(groups = {
		"Regression" }, dataProvider = "UAM030A", dataProviderClass = userManagementUtility.class, description = "UAM030A-Verify if Supervisor is able to access the default privilege-Create Assets")
public void UAM030A(String AName, String AID, String AType, String AManufacturer, String ALocation)
		throws InterruptedException, ParseException, IOException, AWTException {
	extentTest = extent
			.startTest("UAM030A-Verify if Supervisor is able to access the default privilege-Create Assets");
	SoftAssert sa = new SoftAssert();
	MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
	assetHubPage = MainHubPage.Click_AssetTile2();
	assetCreationPage = assetHubPage.Click_AddAssetButton();
	assetCreationPage.assetCreation(AName, AID, AType, AManufacturer, ALocation);
	UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
	String ExpMsg = "Data saved successfully";
	String ActMsg = assetCreationPage.AlertMsg();
	sa.assertEquals(ActMsg, ExpMsg, "FAIL:Data saved successfully alert msg should be displayed ");
	sa.assertAll();
}

	// UAM030B
		@Test(groups = {
				"Regression" }, description = "UAM030B-Verify if Supervisor is able to access the default privilege-Edit Assets")
		public void UAM030B() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent
					.startTest("UAM030B-Verify if Supervisor is able to access the default privilege-Edit Access");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
			assetHubPage = MainHubPage.Click_AssetTile2();
			assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
			assetCreationPage = assetDetailsPage.click_assetEditBtn();
			sa.assertEquals(assetCreationPage.IsEditAssetscreenDisplayed(), true,
					"FAIL:EditAssetscreen should be Displayed");
			sa.assertAll();
		}
		
		// UAM030C
		@Test(groups = {
				"Regression" }, description = "UAM030C-Verify if Supervisor is able to access the default privilege-Audit Trail")
		public void UAM030C() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent
					.startTest("UAM030C-Verify if Supervisor is able to access the default privilege-Audit Trail");
			SoftAssert sa = new SoftAssert();
			
			MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));		
			AuditPage = MainHubPage.ClickAuditTitle();
			sa.assertEquals(AuditPage.AuditHeadTitleVisible(), true, "FAIL: Title not present");
			sa.assertAll();
		}

	// UAM030D
		@Test(groups = {
				"Regression" }, description = "UAM030D-Verify if Supervisor is able to access the default privilege-Camera Access")
		public void UAM030D() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent
					.startTest("UAM030D-Verify if Supervisor is able to access the default privilege-Camera Access");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
			assetHubPage = MainHubPage.Click_AssetTile2();
			assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
			assetCreationPage = assetDetailsPage.click_assetEditBtn();
			assetCreationPage.click_CameraIcon();
			sa.assertEquals(assetCreationPage.CameraOnTitleVisible(), true, "FAIL:CameraOn window should be Visible");
			sa.assertAll();

		}
		
		// UAM030E
		@Test(groups = { "Regression" }, description = "UAM030E-Verify Supervisor is unable to access the non-default "
				+ "privilege-Create Equipment")
		public void UAM030E() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest(
					"ADMN077A:Verify Supervisor is unable to access the non-default " + "privilege-Create Equipment");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
			EquipmentHubPage = MainHubPage.ClickEquipmentTile();
			EquipmentHubPage.Alert_ClickAddBtn();
			Thread.sleep(2000);
			String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
			String ActAlertMsg = EquipmentHubPage.AlertMsg();
			sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Supervisor was able to Create Equipment");
			sa.assertAll();
		}
		
		// UAM030F
		@Test(groups = { "Regression" }, description = "UAM030F-Verify Supervisor is unable to access"
				+ "the non-default privilege-Edit Equipment")
		public void UAM030F() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest(
					"UAM030F-Verify Supervisor is unable to access the " + "the non-default privilege-Edit Equipment");
			SoftAssert sa = new SoftAssert();

			MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
			EquipmentHubPage = MainHubPage.ClickEquipmentTile();
			IRTDHubPage = EquipmentHubPage.click_IRTDTile();
			Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("  J1129");
			Equipment_IRTDDetailspage.enter_IRTDEquipName("Editing");
			String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
			String ActAlertMsg = tu.get_AlertMsg_text();
			sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
			sa.assertAll();
		}
		
	// UAM030G
		@Test(groups = { "Regression" }, description = "UAM030G-Verify Supervisor is unable to access the"
				+ "non-default privilege-Delete Asset")
		public void UAM030G() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent
					.startTest("UAM030G-Verify Supervisor is unable to access the non-default privilege-Delete Asset");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
			assetHubPage = MainHubPage.Click_AssetTile2();
			assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
			assetDetailsPage.DeleteAsset();
			UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
			String ExpAlrtMsg = "User do not have permission to perform this operation";
			String ActAlertMsg = assetDetailsPage.AlertMsg();
			sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access- Delete Asset");
			sa.assertAll();
		}
		
		// UAM030H
		@Test(groups = { "Regression" }, description = "UAM030H-Verify Supervisor is unable to access the non-default "
				+ "privilege-Delete Equipments")
		public void UAM030H() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest(
					"UAM030H-Verify Supervisor is unable to access the" + " non-default privilege-Delete Equipments");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
			EquipmentHubPage = MainHubPage.ClickEquipmentTile();
			IRTDHubPage = EquipmentHubPage.click_IRTDTile();
			Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
			Equipment_IRTDDetailspage.clickDeleteEquipmentIcon();
			Equipment_IRTDDetailspage.ClickYesBtn();
			UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
			String ExpAlrtMsg = "User do not have permission to perform this operation";
			String ActAlertMsg = tu.get_AlertMsg_text();
			sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL:Supervisor should be unable to access Delete Equipments");
			sa.assertAll();
		}
//--------------------------------------------------------------------------------------------------------------------------------
		
		// UAM030I
		@Test(groups = {
				"Regression" }, description = "UAM030I-Verify Supervisor is unable to access the non-default privilege-Archive data")
		public void UAM030I() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent
					.startTest("UAM030I-Verify Supervisor is unable to access the non-default privilege-Archive data");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FileManagementPage.ClickArchiveBtn();
			UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
			String ExpAlrtMsg = "User do not have permission to perform this operation";
			String ActAlertMsg = FileManagementPage.AlertMsg();
			sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Supervisor should be unable to access Archive data");
			sa.assertAll();
		}

		// UAM030J
		@Test(groups = { "Regression" }, description = "UAM030J-Verify if Supervisor user is unable to access Admin")
		public void ADMN084() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent
					.startTest("UAM030J-Verify if Supervisor is unable to access the non-default privilege-Admin");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
			MainHubPage.ClickAdminTile();

			String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
			String ActAlertMsg = MainHubPage.AlertMsg();
			sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
					"FAIL: Supervisor should be unable to access the non-default privilege-Admin");
			sa.assertAll();
		}

		// UAM030K

		@Test(groups = {
				"Regression" }, description = "UAM030K-Verify if Supervisor is able to access the default privilege-Create Setups")
		public void UAM030K() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent
					.startTest("UAM030K-_Verify if Supervisor is able to access the default privilege-Create Setups");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
			assetHubPage = MainHubPage.Click_AssetTile2();
			assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
			Setup_defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
			sa.assertEquals(Setup_defineSetupPage.defineSetupPage_state(), true,
					"FAIL: set up page should be displayed");
			sa.assertAll();

		}

		// UAM030L
		@Test(groups = {
				"Regression" }, description = "UAM030L-Verify if Supervisor is able to access the default privilege-SyncIn")
		public void UAM030L() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent
					.startTest("UAM030L_Verify if Supervisor is able to access the default privilege-SyncIn");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPage(getUID("SysSupervisor"), getPW("SysSupervisor"));
			sa.assertEquals(SyncInPage.SyncInTextBoxVisible(), true, "FAIL: SyncIn TextBox should be Visible ");
			sa.assertAll();
		}

		// UAM030M
		@Test(groups = {
				"Regression" }, description = "UAM030M-Verify if Supervisor is able to access the default privilege-SyncOut")
		public void UAM030M() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent
					.startTest("UAM030M_Verify if Supervisor is able to access the default privilege-SyncOut");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			SyncOutPage = FileManagementPage.ClickSyncOutBtn_SyncOutPage(getUID("SysSupervisor"),
					getPW("SysSupervisor"));
			sa.assertEquals(SyncOutPage.SyncOutTextBoxVisible(), true, "FAIL: SyncOut TextBox should be Visible ");
			sa.assertAll();
		}
		
	//..............................................................................................................................
		
		//UAM031-Check if the System Administrator is able access specified privilaged modules and  For modules which the permissions are denied, an appropriate warning message appears as “User doesn’t have sufficient privileges to perform this operation!.



		// UAM031
		
		@Test(groups = { "Regression" }, description = "UAM031A-Verify if Administrator is able to access the"
				+ "default privilege-Create Equipment")
		public void UAM031A() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent
					.startTest("UAM031A-if Administrator is able to access the " + "default privilege-Create Equipment");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
			EquipmentHubPage = MainHubPage.ClickEquipmentTile();
			EquipmentPage = EquipmentHubPage.ClickAddButton();
			EquipmentPage.EqipCreation_MandatoryFields("IRTD", "ADMN048A", "48A");
			UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
			String ExpAlrtMsg = "Data saved successfully";
			String ActAlertMsg = tu.get_AlertMsg_text();
			sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Equipment should be created successfully");
			sa.assertAll();
		}

  

		// UAM031B
		
		@Test(groups = { "Regression" }, description = "UAM031B-Verify if Administrator is able to access "
				+ "the default privilege Edit Equipment")
		public void UAM031B() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest(
					"UAM031B-Verify if Administrator is able to access the" + "default privilege Edit Equipment");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
			EquipmentHubPage = MainHubPage.ClickEquipmentTile();
			EquipmentPage = EquipmentHubPage.ClickAddButton();
			EquipmentPage.EqipCreation_MandatoryFields("IRTD", "ADMN048B", "48B");
			UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
			tu.click_Close_alertmsg();
			EquipmentHubPage = EquipmentPage.ClickBackBtn();
			IRTDHubPage = EquipmentHubPage.click_IRTDTile();
			Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("ADMN048B");
			Equipment_IRTDDetailspage.enter_IRTDEquipName("Editing");
			UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
			String ExpAlrtMsg = "Equipment \"ADMN048B\" Updated successfully.";
			String ActAlertMsg = EquipmentHubPage.AlertMsg();
			sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Saved Successfully message should be displayed");
			sa.assertAll();
		}

		// UAM031C
		
	@Test(groups = {"Regression" }, description = "UAM031C-Verify if Administrator is able to access the default privilege-Archive")
		public void UAM031C() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("UAM031C-Verify if Administrator is able to access the default privilege-Archive");
			
			SoftAssert sa = new SoftAssert();	
			
			MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
			FileManagementPage = MainHubPage.ClickFileManagementTitle();
			FileManagementPage.ClickArchiveBtn();
			UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));		
			sa.assertEquals(FileManagementPage.ArchiveTextBoxVisible(), true, "FAIL: Archive window should be Visible");	

			sa.assertAll();
		}
	
		// UAM031D
	
	@Test(groups = {
			"Regression" }, description = "UAM031D-Verify if Administrator is able to access the default privilege-SyncIn")
	public void UAM031D() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("UAM031D_Verify if Administrator is able to access the default privilege-SyncIn");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPage(getUID("SysAdmin"), getPW("SysAdmin"));
		
		sa.assertEquals(SyncInPage.SyncInTextBoxVisible(), true, "FAIL:Sync In TextBox should be Visible");
		sa.assertAll();
	}

		
	// UAM031E

	@Test(groups = {
			"Regression" }, description = "UAM031E-Verify if Administrator is able to access the default privilege-SyncOut")
	public void UAM031E() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("UAM031E_Verify if Administrator is able to access the default privilege-SyncOut");
		SoftAssert s5 = new SoftAssert();
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(500);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncOutPage = FileManagementPage.ClickSyncOutBtn_SyncOutPage(getUID("SysAdmin"), getPW("SysAdmin"));

		s5.assertEquals(SyncOutPage.SyncOutTextBoxVisible(), true, "FAIL: Sync Out window not present");
		s5.assertAll();
	}
	
		//UAM031F
		
		@Test(groups = {
		"Regression" }, description = "UAM031F-Verify if Administrator is able to access the default privilege-Access Audit Trail")
public void UAM031F() throws InterruptedException, ParseException, IOException, AWTException {
	extentTest = extent.startTest(
			"ADMN052-Verify if Administrator is able to access the default privilege-Access Audit Trail");
	SoftAssert sa = new SoftAssert();
	MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
	AuditPage = MainHubPage.ClickAuditTitle();
	sa.assertEquals(AuditPage.AuditHeadTitleVisible(), true, "FAIL: Title not present");
	sa.assertAll();
}

// UAM031G
		
@Test(groups = {
		"Regression" }, description = "UAM031G-Verify if Administrator is able to access the default privilege-Camera Access")
public void UAM031G() throws InterruptedException, ParseException, IOException, AWTException {
	extentTest = extent
			.startTest("UAM031G-Verify if Administrator is able to access the default privilege-Camera Access");
	SoftAssert sa = new SoftAssert();
	MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
	UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
	UserManagementPage.clickAnyUserinUserList("Adm1");
	UserManagementPage.click_UserImageTile();
	UserManagementPage.click_CameraIcon();
	sa.assertEquals(UserManagementPage.IsCameracloseBtn_Enable(), true, "FAIL:Camera window should be Visible");
	sa.assertAll();
}



// UAM031H
@Test(groups = {
		"Regression" }, description = "Verify Administrator is unable to access the non-default privilege-Create Asset")
public void UAM031H() throws InterruptedException, ParseException, IOException, AWTException {
	extentTest = extent
			.startTest("ADMN054-Verify Administrator is unable to access the non-default privilege-Create Asset");
	SoftAssert sa = new SoftAssert();
	MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
	assetHubPage = MainHubPage.Click_AssetTile2();
	assetHubPage.Click_AddButton();
	String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
	String ActAlertMsg = assetHubPage.AlertMsg();
	sa.assertEquals(ActAlertMsg, ExpAlrtMsg,"FAIL:User should be unable to access the non-default privilege-Create Asset");
	sa.assertAll();
}

// UAM031I
@Test(groups = {
		"Regression" }, description = "UAM031I-Verify Administrator is unable to access the non-default privilege-Edit Asset")
public void UAM031I() throws InterruptedException, ParseException, IOException, AWTException {
	extentTest = extent
			.startTest("UAM031I_Verify Administrator is unable to access the non-default privilege Edit Asset");
	SoftAssert sa = new SoftAssert();
	MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
	assetHubPage = MainHubPage.Click_AssetTile2();
	assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
	assetDetailsPage.click_assetEditBtn_alrt();
	String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
	String ActAlertMsg = assetDetailsPage.AlertMsg();

	sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
	sa.assertAll();
}

//UAM031J

	@Test(groups = {
			"Regression" }, description = "UAM031J-Verify Administrator is unable to access the non-default privilege-Create Setups")
	public void UAM031J() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("UAM031J-Verify Administrator is unable to access the non-default privilege-Create Setups");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_NewStupCreateBtn_alert();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: User should be unable to access the non-default privilege-Create Setups");
		sa.assertAll();
	}

	// UAM031K-Verify if Administrator is able to access the default
		// privilege-Create Reports
		@Test(groups = {
				"Regression" }, description = "UAM031K-Verify if Administrator is able to access the default privilege-Create Reports")
		public void UAM031K() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent
					.startTest("UAM031K-Verify if Administrator is able to access the default privilege-Create Reports");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
			assetHubPage = MainHubPage.Click_AssetTile2();
			assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
			assetDetailsPage.click_QualTile();
			assetDetailsPage.Select_QualFile("manual 1 min sampling");
			RW_FileSelctionPage = assetDetailsPage.Click_GenerateReportsBtn_RWpage();

			sa.assertEquals(RW_FileSelctionPage.assetDetailTitle_Visible(), true, "AssetsNameText  should be Visible");
			sa.assertAll();
		}
		
		//UAM031L
		
		@Test(groups = {
		"Regression" }, dataProvider = "UAM031L", dataProviderClass = userManagementUtility.class, description = "UAM031L-Verify if Administrator is able to access the "
				+ "default privilege-Delete Assets")
public void UAM031L(String AName, String AID, String AType, String AManufacturer, String ALocation)
		throws InterruptedException, ParseException, IOException, AWTException {
	extentTest = extent
			.startTest("UAM031L-Verify if Administrator is able to access the default privilege-Delete Assets");
	SoftAssert sa = new SoftAssert();
	MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
	assetHubPage = MainHubPage.Click_AssetTile2();
	assetCreationPage = assetHubPage.Click_AddAssetButton();
	assetCreationPage.assetCreation(AName, AID, AType, AManufacturer, ALocation);
	UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
	tu.click_Close_alertmsg();
	assetHubPage = assetCreationPage.clickBackBtn();
	assetDetailsPage = assetHubPage.click_assetTile(AName);
	assetDetailsPage.DeleteAsset();
	UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
	Thread.sleep(2000);
	String alrtMsg = assetDetailsPage.get_text_DeleteAst_popup();
	// System.out.println(alrtMsg);
	String[] wordlist = alrtMsg.split(":");
	// System.out.println(wordlist[0]);
	sa.assertEquals(wordlist[0], "Do you want to delete asset",
			"FAIL:Admin unable to delete Asset or" + " a diferent popup is observed");
	sa.assertAll();
}

		//UAM031M

		@Test(groups = { "Regression" }, description = "UAM031M-Verify if Administrator is able to access the "
				+ "default privilege-Delete Equipment")
		public void UAM031M() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest(
					"ADMN064_Verify if Administrator is able to access the default" + " privilege-Delete Equipment");
			SoftAssert sa = new SoftAssert();
			MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
			EquipmentHubPage = MainHubPage.ClickEquipmentTile();
			IRTDHubPage = EquipmentHubPage.click_IRTDTile();
			Equipment_IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
			Equipment_IRTDDetailspage.clickDeleteEquipmentIcon();
			sa.assertEquals(Equipment_IRTDDetailspage.IRTD_DeletePopupWindow(), true, "FAIL:IRTD_Delete Popup Window should present");
			sa.assertAll();
		}

  //UM032-Check if except for the above mentioned activities, the application throws “User doesn’t have sufficient privileges to perform this operation!” error message.
  //This TC handeled in UM-3 test class
		
		
	//UAM033-Check if the access denied entries and the successful login entries are captured in the audit trail with Date and time, Permission violation, attempted action,
	//Name of the new User, Console Mac ID and/or AVS mac ID.  Attach the audit trail as proof.

	@Test(groups = {

			"Regression" }, description = "UAM033-Check if the access denied entries and the successful login entries are captured "
					+ "in the audit trail with Date and time, Permission violation, attempted action, Name of the new User,"
					+ " Console Mac ID and/or AVS mac ID.  Attach the audit trail as proof.")
	public void UAM033() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("UAM033-Check if the access denied entries and the successful login entries are captured "
						+ "in the audit trail with Date and time, Permission violation, attempted action, Name of the new User, "
						+ "Console Mac ID and/or AVS mac ID.  Attach the audit trail as proof.");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_assetEditBtn_alrt();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();

		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		assetHubPage = assetDetailsPage.ClickBackBtn();
		MainHubPage = assetHubPage.ClickBackBtn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		System.out.println(Actionmsg);
		String ExpectMSG = "User ID : \"1\", User Name : \"user1\"  has insufficient Privileges to perform Asset creation  ";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL:The Audit trail record for Edit Assets activity is not exist ");
		sa.assertAll();

	}
	
	//UAM034-verify the audit message when  System Administrator modify the details

	@Test(groups = {
			"Regression" }, description = "UAM034-verify the audit message when  System Administrator modify the details")

	public void UAM034() throws InterruptedException, IOException {
		extentTest = extent.startTest("UAM034-verify the audit message when  System Administrator modify the details");

		SoftAssert sa = new SoftAssert();

		UserManagementPage.UMCreation_MandatoryFields("UAM034", "34", "123456", "123456", "Admin",
				"System Administrator");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		UserManagementPage.clickAnyUserinUserList("UAM034");
		UserManagementPage.UMCreation_MandatoryFields("UMAA1", "", "1234567", "1234567", "SUP", "Supervisor");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage.Click_OKBtn();
		MainHubPage = UserManagementPage.ClickBackButn();
		AuditPage = MainHubPage.ClickAuditTitle();
		Thread.sleep(2000);
		String Actionmsg = AuditPage.get_auditEvent_text();
		System.out.println(Actionmsg);
		String ExpectMSG = "User Management : User  Id : \"1\"  has  Updated the Name of User \"2\"  from \" UAM034\" to  \"UMAA1 \"";

		sa.assertEquals(Actionmsg, ExpectMSG, "FAIL:The Audit trail record for Edit Assets activity is not exist ");
		sa.assertAll();

 }
	//UAM035-Launch the audit trail and check if the modifications in preferences are captured with old and new values. 

	@Test(groups = {
			"Regression" }, description = "UM035-Launch the audit trail and check if the modifications in preferences are captured with old and new values.")

	public void UAM035() throws InterruptedException, IOException {
		extentTest = extent.startTest("UM035-Launch the audit trail and check if the modifications in preferences are captured with old and new values. ");

		SoftAssert sa = new SoftAssert();

	PreferencesPage.enterCompanyName("Test1");
	PreferencesPage.click_SaveBtn();
	PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
	Thread.sleep(1000);
	PreferencesPage.enterCompanyName("Test2");
	PreferencesPage.click_SaveBtn();
	PreferencesPage.UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

	MainHubPage = PreferencesPage.ClickBackButn();
	AuditPage = MainHubPage.ClickAuditTitle();
	Thread.sleep(3000);
	String Actionmsg = AuditPage.get_auditEvent_text();
	String ExpectMSG = "Preferences : \"Company Name\" field modified from \"Test1 to Test2\" by User ID : \"1\", User Name : \"User1\"";
	sa.assertEquals(Actionmsg, ExpectMSG,
			"FAIL: Audit trial record does not exists for company name modification ");
	sa.assertAll();
}


}