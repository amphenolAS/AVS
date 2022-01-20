/**
* @author ruchika.behura
*
*/
package com.avs.testcases;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
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
import com.avs.utility.TestUtilities;

import bsh.ParseException;

import com.avs.pages.Equipment_IRTDDetailspage;
import com.avs.pages.Equipment_IRTDHubPage;
import com.avs.pages.FileManagementPage;
import com.avs.pages.AuditPage;
import com.avs.pages.assetHubPage;
import com.avs.pages.assetCreationPage;
import com.avs.pages.assetDetailsPage;
import com.avs.pages.Setup_defineSetupPage;
import com.avs.pages.SyncInPage;
import com.avs.pages.SyncOutPage;
import com.avs.pages.SyncInAssetListPage;
import com.avs.pages.EquipmentPage;

public class UM3 extends BaseClass {

	public UM3() throws IOException {
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
	EquipmentHubPage EquipmentHubPage;
	EquipmentPage EquipmentPage;
	Equipment_IRTDDetailspage IRTDDetailspage;
	Equipment_IRTDHubPage IRTDHubPage;
	FileManagementPage FileManagementPage;
	AuditPage AuditPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	Setup_defineSetupPage Setup_defineSetupPage;
	SyncInPage SyncInPage;
	SyncOutPage SyncOutPage;
	SyncInAssetListPage SyncInAssetListPage;

	// @BeforeTest
	@BeforeClass
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

		UserManagementPage.clickPrivCreateEditAsset();
		UserManagementPage.clickPrivCreateEditSetup();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		// ADMIN user creation
		UserManagementPage.ClickNewUser();
		UserManagementPage.CreateAdminUser(getUID("adminFull"), getPW("adminFull"), "Admintest1", getUID("SysAdmin"),
				"1Start@1AM", "AdminNew", "123345678", "newAdmin@gmail.com");

		UserManagementPage.clickAnyUserinUserList("Admintest1");
		Thread.sleep(1000);
		UserManagementPage.Click_AllCheckBox();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(500);
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(getUID("SysAdmin"), "1Start@1AM", getPW("SysAdmin"));
		LoginPage = MainHubPage.UserSignOut();

		// SUPERVISOR user creation
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.CreateSupervisorUser(getUID("adminFull"), getPW("adminFull"), "Suptest1",
				getUID("SysSupervisor"), "3Welcome3@AM", "SUpNew", "123345678", "newSup@gmail.com");
		UserManagementPage.clickAnyUserinUserList("Suptest1");
		UserManagementPage.Click_AllCheckBox();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(500);
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(getUID("SysSupervisor"), "3Welcome3@AM",
				getPW("SysSupervisor"));
		LoginPage = MainHubPage.UserSignOut();

		// OPERATOR User creation
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.CreateOperatorUser(getUID("adminFull"), getPW("adminFull"), "OPE1", getUID("SysOperator"),
				"4Welcome4@AM", "OperatorNew", "12345678", "newOpe@gmail.com");
		UserManagementPage.clickAnyUserinUserList("OPE1");
		UserManagementPage.Click_AllCheckBox();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		MainHubPage = UserManagementPage.ClickBackButn();
		LoginPage = MainHubPage.UserSignOut();
		Thread.sleep(500);
		MainHubPage = LoginPage.ChangeNewPWwithoutPWCheckBox(getUID("SysOperator"), "4Welcome4@AM",
				getPW("SysOperator"));
		LoginPage = MainHubPage.UserSignOut();

		// Conduct a Syncin operation
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
	// @AfterTest
	@AfterClass
	public void endReport() throws InterruptedException {
		extent.flush();
		extent.close();
		System.out.println("UM3_customized_UserPrivilagesTest Completed.");
		Thread.sleep(500);
	}

	// Before Method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException, IOException {
		// System.out.println("Synin Process check in UM3");
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		LoginPage = new LoginPage();
	}

	// After Method
	@AfterMethod(alwaysRun = true)
	public void Teardown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getName() + " #");
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getThrowable() + " #");
			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1));
			// extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));
			// //to add screen cast/video in extent report
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName() + " #");
		}
		extent.endTest(extentTest);
		driver.quit();
	}

	/*
	 * // Check
	 * 
	 * @Test(description = "check for SyncIn im UM1") public void Check() throws
	 * Exception { extentTest = extent.startTest("Syncin Process check in UM1");
	 * System.out.println("Syncin Process check in UM1"); }
	 */

	// CUAM1
	@Test(groups = { "Regression" }, description = "CUAM1-Verify the customized (non defult) "
			+ "privileges for Administrator")
	public void CUAM1() throws Exception {
		extentTest = extent.startTest("CADMN1-Verify the customized unchecked privileges for Administrator");
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Admintest1");
		SoftAssert sa1 = new SoftAssert();
		sa1.assertEquals(UserManagementPage.Adminstatus(), false, "FAIL:Adminstatus should not Checked");
		sa1.assertEquals(UserManagementPage.CreateAndEditEquipmentstatus(), false,
				"FAIL: CreateAndEditEquipmentstatus CheckBox should not Checked");
		sa1.assertEquals(UserManagementPage.CreateReportsstatus(), false,
				"FAIL: CreateReportsstatus CheckBox should not Checked");
		sa1.assertEquals(UserManagementPage.CreatePassFailtemplatestatus(), false,
				"FAIL:CreatePassFailtemplatestatus CheckBox should not Checked");
		sa1.assertEquals(UserManagementPage.DeleteAssetsstatus(), false, "FAIL: DeleteAssetsstatus should not Checked");
		sa1.assertEquals(UserManagementPage.DeleteSetupstatus(), false, "FAIL: DeleteSetupstatus should not Checked");
		sa1.assertEquals(UserManagementPage.DeleteEquipmentstatus(), false,
				"FAIL:DeleteEquipmentstatus should not Checked");
		sa1.assertEquals(UserManagementPage.DeleteStudyFilesReportsstatus(), false,
				"FAIL: DeleteStudyFilesReportsstatus should not Checked");
		sa1.assertEquals(UserManagementPage.EditPassFailtemplatestatus(), false,
				"FAIL:EditPassFailtemplatestatus CheckBox should not Checked");
		sa1.assertEquals(UserManagementPage.CopyFilesReportsstatus(), false,
				"FAIL:CopyFilesReportsstatus should not Checked");
		sa1.assertEquals(UserManagementPage.Archivedatastatus(), false,
				"FAIL: Archivedatastatus CheckBox should not Checked");
		sa1.assertEquals(UserManagementPage.CameraAccessstatus(), false,
				"FAIL:CameraAccessstatus CheckBox should not Checked");
		sa1.assertEquals(UserManagementPage.ManualSyncstatus(), false,
				"FAIL:ManualSyncstatus CheckBox should not Checked");
		sa1.assertEquals(UserManagementPage.Deletepassfailtemplatestatus(), false,
				"FAIL:Deletepassfailtemplatestatus CheckBox should not Checked");
		UserManagementPage.ClkscrollBar_down();
		sa1.assertEquals(UserManagementPage.Audittrailstatus(), false,
				"FAIL: Audittrailstatus CheckBox should be Checked");
		sa1.assertAll();
	}

	// CUAM2
	@Test(groups = { "Regression" }, description = "CUAM2-Verify the customized default privileges for Administrator")
	public void CUAM2() throws Exception {
		extentTest = extent.startTest("CUAM2-Verify the customized privileges for Administrator");
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Admintest1");
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(UserManagementPage.CreaeteEditAssetPrivstatus(), true, "FAIL: CheckBox should be Checked");
		sa.assertEquals(UserManagementPage.RunQualificationstatus(), true, "FAIL: CheckBox should be Checked");
		sa.assertEquals(UserManagementPage.RunCalibrationstatus(), true, "FAIL: CheckBox should be Checked");
		sa.assertEquals(UserManagementPage.PrivCreateEditSetupstatus(), true, "FAIL: CheckBox should be Checked");

		sa.assertAll();
	}

	// CUAM3
	@Test(groups = { "Regression" }, description = "CUAM3-Verify if Administrator is Unable to access the "
			+ "customized unchecked privilege-Create_Equipment")
	public void CUAM3() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN058A-Verify if Administrator is Unable to access customized unchecked "
				+ "privilege-Create_Equipment");
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentHubPage.Alert_ClickAddBtn();
		SoftAssert s = new SoftAssert();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = EquipmentHubPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User should not able to create Equipment");
		s.assertAll();
	}

	// CUAM4
	@Test(groups = { "Regression" }, description = "CUAM4-Verify Administrator is unable to access the "
			+ "customized unchecked privilege-Edit Equipment")
	public void CUAM4() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN058B-Verify if Administrator is able to access the customized "
				+ "unchecked privilege-Edit_ Equipment");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		IRTDDetailspage.enter_IRTDEquipName("Editing");
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = tu.get_AlertMsg_text();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User should not able to edit Equipment");
		s.assertAll();
	}

	// CUAM5
	@Test(groups = { "Regression" }, description = "CUAM5-Verify Admin is unable to access the customized unchecked "
			+ "privilege-Delete Equipments")
	public void CUAM5() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM5_Verify Admin is unable to access the customized unchecked privilege-Delete Equipments");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		IRTDDetailspage.clickDeleteEquipmentIcon();
		IRTDDetailspage.ClickYesBtn();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = tu.get_AlertMsg_text();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		s.assertAll();
	}

	// CUAM6
	@Test(groups = { "Regression" }, description = "CUAM6-Verify if Administrator is unable to access the "
			+ "customized unchecked privilege-Archive")
	public void CUAM6() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM5-Verify if Administrator is unable to access the customized unchecked privilege-Archive");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickArchiveBtn();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = FileManagementPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User should not able to access Archive");
		s.assertAll();
	}

	// CUAM7
	@Test(groups = { "Regression" }, description = "CUAM7-Verify if Administrator is able to access the "
			+ "customized unchecked privilege-SyncIn")
	public void CUAM7() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN7_Verify if Administrator is able to access the customized unchecked privilege-Sync In");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncInBtn(getUID("SysAdmin"), getPW("SysAdmin"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = FileManagementPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User should not able to access SyncIn");
		s.assertAll();
	}

	// CUAM8
	@Test(groups = { "Regression" }, description = "CUAM8-Verify if Administrator is able to access the "
			+ "customized unchecked privilege-SyncOut")
	public void CUAM8() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM8_Verify if Administrator is able to access the customized unchecked privilege-SyncOut");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncOutBtn(getUID("SysAdmin"), getPW("SysAdmin"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = FileManagementPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User should not able to access SyncOut");
		s.assertAll();
	}

	// CUAM9
	@Test(groups = { "Regression" }, description = "CUAM9-Verify if Administrator is able to access the "
			+ "customized unchecked privilege-Access Audit Trail")
	public void CUAM9() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("CUAM9-Verify if Administrator is able to access the customized unchecked "
				+ "privilege-Access Audit Trail");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		MainHubPage.Alert_AuditTitle();
		String ActAlertMsg = MainHubPage.AlertMsg();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User should not able to access Audit");
		s.assertAll();
	}

	// CUAM10
	@Test(groups = { "Regression" }, description = "CUAM10-Verify Administrator is able to access the "
			+ "Customized checked privilege-Create Asset")
	public void CUAM10() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN10_Verify Administrator is able to access the Customized checked privilege-Create Asset");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation("CAssert22", "402A", "HeatBath", "HYdd", "Ind");
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		String ExpMsg = "Data saved successfully";
		String ActMsg = assetCreationPage.AlertMsg();
		s.assertEquals(ActMsg, ExpMsg, "FAIL: Data saved successfully Msg should be displaying");
		s.assertAll();
	}

	// CUAM11
	@Test(groups = { "Regression" }, description = "CUAM11-Verify Administrator is able to access the "
			+ "Customized chcked privilege-Edit Asset")
	public void CUAM11() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM11_Verify Administrator is able to access the Customized checked privilege-Edit Asset");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		s.assertEquals(assetCreationPage.IsEditAssetscreenDisplayed(), true,
				"FAIL: Admin should be able to access the Customized privilege-Edit Asset");
		s.assertAll();
	}

	// CUAM12
	@Test(groups = { "Regression" }, description = "CUAM12-Verify if Administrator is unable to access the "
			+ "Customized unchecked privilege-Camera Access in edit asset")
	public void CUAM12() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM12_Verify if Administrator is unable to access the Customized unchecked privilege-Camera Access");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		assetCreationPage.click_CameraIcon();
		String ExpMsg = "User does not have sufficient privileges to perform this operation";
		String ActMsg = assetCreationPage.AlertMsg();
		s.assertEquals(ActMsg, ExpMsg,
				"FAIL:Administrator should be unable to access the Customized privilege-Camera Access");
		s.assertAll();
	}

	// dependsOnMethods = "CADMN10",
	// CUAM13
	@Test(groups = { "Regression" }, description = "CUAM13-Verify Administrator is able to access the "
			+ "customized privilege-Create Setups")
	public void CUAM13() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CUAM13_Verify Administrator is able to access the customized privilege-Create Setups");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		Setup_defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
		s.assertEquals(Setup_defineSetupPage.defineSetupPage_state(), true, "FAIL: setup page should be displayed");
		s.assertAll();
	}

	// dependsOnMethods = "CUAM10",
	// CADMN14
	@Test(groups = {
			"Regression" }, description = "CUAM14-Verify if Administrator is unable to access the Customized non default privilege-Delete Assets")
	public void CUAM14() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM14-Verify if Administrator is unable to access the Customized unchecked privilege-Delete Assets");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysAdmin"), getPW("SysAdmin"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.DeleteAsset();
		UserLoginPopup(getUID("SysAdmin"), getPW("SysAdmin"));
		String ExpAlrtMsg = "User do not have permission to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL:Administrator should unable to access the default privilege-Delete Assets");
		s.assertAll();
	}

	// CUAM15
	@Test(groups = {
			"Regression" }, description = "CUAM15-Verify the Customized checked privileges for Supervisor User")
	public void CUAM15() throws Exception {
		extentTest = extent.startTest("CUAM15-Verify the Customized default privileges  for Supervisor User");
		SoftAssert sa = new SoftAssert();

		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Suptest1");

		// Validate check boxes are checked
		sa.assertEquals(UserManagementPage.Adminstatus(), true, "FAIL:Adminstatus checkbox should be Checked");
		sa.assertEquals(UserManagementPage.CreateAndEditEquipmentstatus(), true,
				"FAIL:CreateAndEditEquipmentstatus CheckBox should  be Checked");

		sa.assertEquals(UserManagementPage.DeleteAssetsstatus(), true,
				"FAIL:DeleteAssetsstatus checkbox should  be Checked");
		sa.assertEquals(UserManagementPage.DeleteSetupstatus(), true,
				"FAIL: DeleteSetupstatus checkbox should  be Checked");
		sa.assertEquals(UserManagementPage.DeleteEquipmentstatus(), true,
				"FAIL:DeleteEquipmentstatus checkbox should  be Checked");
		sa.assertEquals(UserManagementPage.DeleteStudyFilesReportsstatus(), true,
				"FAIL:DeleteStudyFilesReportsstatus checkbox should  be Checked");

		sa.assertEquals(UserManagementPage.CopyFilesReportsstatus(), true,
				"FAIL:CopyFilesReportsstatus checkbox should be Checked");
		sa.assertEquals(UserManagementPage.Archivedatastatus(), true,
				"FAIL:Archivedatastatus CheckBox checkbox should be Checked");
		sa.assertAll();
	}

	// CUAM16
	@Test(groups = { "Regression" }, description = "CUAM16-Verify the Customized Non-default privileges  for Supervisor User")
	public void CUAM16() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("CUAM16-Verify Customized Non-default privileges for Supervisor User");
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Suptest1");
		SoftAssert sa = new SoftAssert();
		// Validate check boxes are not checked
		sa.assertEquals(UserManagementPage.CreaeteEditAssetPrivstatus(), false,
				"FAIL:CreaeteEditAssetPrivstatus should not Checked");
		sa.assertEquals(UserManagementPage.CreaeteEditSetupstatus(), false,
				"FAIL:CreaeteEditSetupstatus should not Checked");
		sa.assertEquals(UserManagementPage.CreateReportsstatus(), false,
				"FAIL :CreateReportsstatus should not Checked");

		sa.assertEquals(UserManagementPage.RunQualificationstatus(), false,
				"FAIL: RunQualificationstatus should not Checked");
		sa.assertEquals(UserManagementPage.RunCalibrationstatus(), false,
				"FAIL: RunCalibrationstatus should not Checked");
		sa.assertEquals(UserManagementPage.ManualSyncstatus(), false, "FAIL: ManualSyncstatus should not Checked");
		sa.assertEquals(UserManagementPage.CameraAccessstatus(), false, "FAIL:CameraAccessstatus should not Checked");

		UserManagementPage.ClkscrollBar_down();
		sa.assertEquals(UserManagementPage.CreatePassFailtemplatestatus(), false,
				"FAIL: CreatePassFailtemplatestatus checkbox should not be Checked");
		sa.assertEquals(UserManagementPage.EditPassFailtemplatestatus(), false,
				"FAIL: EditPassFailtemplatestatus checkbox should not  be Checked");
		sa.assertEquals(UserManagementPage.Audittrailstatus(), false,
				"FAIL: Audittrailstatus CheckBox should not be Checked");
		sa.assertEquals(UserManagementPage.Deletepassfailtemplatestatus(), false,
				"FAIL: Deletepassfailtemplatestatus checkbox should not be Checked");
		sa.assertAll();
	}

	// CUAM17
	@Test(groups = { "Regression" }, description = "CUAM17-Verify Supervisor is Unable to access the "
			+ "customized  privilege-Create Assets")
	public void CUAM17() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CUAM17-if Supervisor is Unable to access the " + "customized  privilege-Create Assets");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetHubPage.Click_AddButton();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetHubPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		s.assertAll();
	}

	// CUAM18
	@Test(groups = { "Regression" }, description = "CUAM18-Verify Supervisor is unable to "
			+ "access the customized privilege-Edit Asset")
	public void CUAM18() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CUAM18-Verify Supervisor is unable to access the customized privilege-Edit Asset");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.click_assetEditBtn_alrt();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Supervisor should be unable to access the customized privilege-Edit Asset");
		s.assertAll();
	}

	// CUAM19
	@Test(groups = {
			"Regression" }, description = "CUAM19-Verify Supervisor is able to access the customized default privilege-Delete Asset")
	public void CUAM19() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CUAM19-Verify Supervisor is able to access the customized default privilege-Delete Asset");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation("Supcas5", "503A", "HeatBath", "HYdd", "Ind");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetCreationPage.clickBackBtn();
		MainHubPage = assetHubPage.clickBackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("Supcas5");
		assetDetailsPage.DeleteAsset();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		s.assertEquals(assetDetailsPage.Deletepopupwindow(), true, "FAIL:Delete popup window should be Visible");
		s.assertAll();
	}

	// CUAM20
	@Test(groups = {
			"Regression" }, description = "CUAM20-Verify Operator is unable to access the customized unchecked privilege-Camera Access")
	public void CUAM20() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM20-Verify Operator is unable to access the customized checked privilege-Camera Access");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Suptest1");
		UserManagementPage.click_UserImageTile();
		UserManagementPage.click_CameraIcon();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Operator should be unable to access the customized unchecked privilege-Camera Access");
		s.assertAll();
	}

	// CUAM21
	@Test(groups = { "Regression" }, description = "CUAM21-Verify Supervisor is able to access the "
			+ "customized non-default privilege-Create Equipment")
	public void CUAM21() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM21-Verify Supervisor is able to access the customized non-default privilege-Create Equipment");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("IRTD", "CADMN21", "21");
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "Data saved successfully";
		String ActAlertMsg = tu.get_AlertMsg_text();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Equipment should be created successfully");
		s.assertAll();
	}

	// CUAM22
	@Test(groups = { "Regression" }, description = "CUAM22-Verify Supervisor is able to access the "
			+ "customized non-default privilege-Edit Equipment")
	public void CUAM22() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CADMN22-Verify Supervisor is able to access the customized " + "non-default privilege-Edit Equipment");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("IRTD", "CADMN22", "22");
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		tu.click_Close_alertmsg();
		EquipmentHubPage = EquipmentPage.ClickBackBtn();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("CADMN22");
		IRTDDetailspage.enter_IRTDEquipName("Editing");
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsg = "Equipment \"CADMN22\" Updated successfully.";
		String ActAlertMsg = EquipmentHubPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Saved Successfully message should be displayed");
		s.assertAll();
	}

	// CUAM23
	@Test(groups = { "Regression" }, description = "CUAM23-Verify Supervisor is able to access the "
			+ "customized non-default privilege-Delete Equipments")
	public void CUAM23() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("CADMN23A_Verify Supervisor is able to access the customized "
				+ "non-default privilege-Delete Equipments");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("IRTD", "CADMN23A", "23A");
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		tu.click_Close_alertmsg();
		EquipmentHubPage = EquipmentPage.ClickBackBtn();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("CADMN23A");
		IRTDHubPage = IRTDDetailspage.delete_IRTD(getUID("SysSupervisor"), getPW("SysSupervisor"));
		// System.out.println(IRTDHubPage.Is_Irtd_visible("CADMN23"));
		sa.assertEquals(IRTDHubPage.Is_Irtd_visible("CADMN23A"), false, "FAIL:Supervisor Unable to delete IRTD");
		sa.assertAll();
	}

	// CUAM23B
	@Test(groups = { "Regression" }, description = "CUAM23B-Verify Supervisor is able to access the "
			+ "customized default privilege-Archive")
	public void CUAM23B() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CADMN23B-Verify Supervisor is able to access the customized default privilege-Archive");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickArchiveBtn();
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		sa.assertEquals(FileManagementPage.ArchiveTextBoxVisible(), true, "FAIL: Archive window should be Visible");
		sa.assertAll();
	}

	// CUAM24
	@Test(groups = { "Regression" }, description = "CUAM24-Verify Supervisor is able to access the customized "
			+ "non-default privilege-Manual sync In")
	public void CUAM24() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM24-Verify Supervisor is able to access the customized non-default privilege-Manual sync In");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncInBtn(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsgForSyncIn = "User do not have permission to perform this operation";
		String ActAlertMsgForSyncIn = FileManagementPage.AlertMsg();
		s.assertEquals(ExpAlrtMsgForSyncIn, ActAlertMsgForSyncIn,
				"FAIL: Operator is able to access the non-default privilege-Manual sync In");
		s.assertAll();
	}

	// CUAM25
	@Test(groups = {
			"Regression" }, description = "CUAM25-Verify Supervisor is able to access the customized non-default privilege-Manual sync Out")
	public void CUAM25() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM25-Verify Supervisor is able to access the customized non-default privilege-Manual sync Out");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickSyncInBtn(getUID("SysSupervisor"), getPW("SysSupervisor"));
		// UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		String ExpAlrtMsgForSyncOut = "User do not have permission to perform this operation";
		String ActAlertMsgForSyncOut = FileManagementPage.AlertMsg();
		s.assertEquals(ExpAlrtMsgForSyncOut, ActAlertMsgForSyncOut,
				"FAIL: Operator is able to access the non-default privilege-Manual sync Out");
		s.assertAll();
	}

	// CUAM26
	@Test(groups = { "Regression" }, description = "CUAM26-Verify if Supervisor is unable to access the"
			+ "customized Non-default privilege-Access Audit Trail")
	public void CUAM26() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("CUAM26-Verify if Supervisor is able to access the customized Non-default "
				+ "privilege-Access Audit Trail");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));

		MainHubPage.Alert_AuditTitle();

		String ActAlertMsg = MainHubPage.AlertMsg();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";

		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User should not able to access for Audit Trail");
		s.assertAll();

	}

	// CUAM27
	@Test(groups = { "Regression" }, description = "CUAM27-Verify customized checked privileges  for Operator User")
	public void CUAM27() throws Exception {
		
		extentTest = extent.startTest("CUAM27-Verify customized checked privileges  for Operator User");
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("OPE1");
		SoftAssert sa = new SoftAssert();
		// Validate check boxes checked
		sa.assertEquals(UserManagementPage.Adminstatus(), true, "FAIL:Adminstatus should be Checked");
		sa.assertEquals(UserManagementPage.CreateAndEditEquipmentstatus(), true,
				"FAIL:CreateAndEditEquipmentstatus CheckBox should be Checked");
		sa.assertEquals(UserManagementPage.CreatePassFailtemplatestatus(), true,
				"FAIL: CreatePassFailtemplatestatus CheckBox should be Checked");
		sa.assertEquals(UserManagementPage.DeleteAssetsstatus(), true, "FAIL:DeleteAssetsstatus should be Checked");
		sa.assertEquals(UserManagementPage.DeleteSetupstatus(), true, "FAIL:DeleteSetupstatus should be Checked");
		sa.assertEquals(UserManagementPage.DeleteEquipmentstatus(), true,
				"FAIL:DeleteEquipmentstatus should be Checked");
		sa.assertEquals(UserManagementPage.DeleteStudyFilesReportsstatus(), true,
				"FAIL:DeleteStudyFilesReportsstatus should be Checked");
		sa.assertEquals(UserManagementPage.EditPassFailtemplatestatus(), true,
				"FAIL:EditPassFailtemplatestatus should be Checked");
		sa.assertEquals(UserManagementPage.CopyFilesReportsstatus(), true,
				"FAIL: CopyFilesReportsstatus should be Checked");
		sa.assertEquals(UserManagementPage.Archivedatastatus(), true, "FAIL: Archivedatastatus should be Checked");
		sa.assertEquals(UserManagementPage.ManualSyncstatus(), true, "FAIL: ManualSyncstatus should be Checked");
		sa.assertEquals(UserManagementPage.Deletepassfailtemplatestatus(), true,
				"FAIL: Deletepassfailtemplatestatus should be Checked");
		sa.assertAll();
	}

	// CUAM28
	@Test(groups = { "Regression" }, description = "CUAM28-Verify customized unchecked privileges for Operator User")
	public void CUAM28() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("CUAM28-Verify customized unchecked privileges for Operator User");
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("OPE1");
		SoftAssert sa = new SoftAssert();
		// Validate check boxes are unchecked
		sa.assertEquals(UserManagementPage.CreaeteEditAssetPrivstatus(), false,
				"FAIL: CreaeteEditAssetPrivstatus should not be Checked");
		sa.assertEquals(UserManagementPage.CreateReportsstatus(), false,
				"FAIL: CreateReportsstatus should not be Checked");
		sa.assertEquals(UserManagementPage.RunQualificationstatus(), false,
				"FAIL: RunQualificationstatus should not be Checked");
		sa.assertEquals(UserManagementPage.RunCalibrationstatus(), false,
				"FAIL: RunCalibrationstatus should not be Checked");
		sa.assertEquals(UserManagementPage.CameraAccessstatus(), false,
				"FAIL: CameraAccessstatus should not be Checked");
		UserManagementPage.ClkscrollBar_down();
		sa.assertEquals(UserManagementPage.Audittrailstatus(), false, "FAIL: Audit trail status should not be Checked");
		sa.assertAll();
	}

	// CUAM29
	@Test(groups = {
			"Regression" }, description = "CUAM29-Verify Operator is able to access the customized checked privileges-Admin")
	public void CADMN29() throws Exception {
		extentTest = extent
				.startTest("CUAM29-Verify Operator is able to access the customized checked privileges-Admin");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		sa.assertEquals(UserManagementPage.IsUMscreenDisplayed(), true,
				"FAIL: User Management screen should be displayed");
		sa.assertAll();
	}

	// CUAM30
	@Test(groups = { "Regression" }, description = "CUAM30-Verify Operator is able to access the "
			+ "customized checked privileges-Create Equipment")
	public void CUAM30() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM30-Verify Operator is able to access the customized checked privilege-Create Equipment");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("IRTD", "CADMN30", "30");
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "Data saved successfully";
		String ActAlertMsg = tu.get_AlertMsg_text();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Equipment should be created successfully");
		EquipmentHubPage = EquipmentPage.ClickBackBtn();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		// System.out.println(IRTDHubPage.Is_Irtd_visible("CADMN30"));
		s.assertEquals(IRTDHubPage.Is_Irtd_visible("CADMN30"), true, "FAIL:IRTD_is not created");
		s.assertAll();

	}

	// CUAM31
	@Test(groups = { "Regression" }, description = "CUAM31-Verify Operator is able to access the "
			+ "customized checked privilege-Edit Equipment")
	public void CUAM31() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CUAM31-Verify Operator is able to access the customized checked-Edit Equipment");
		SoftAssert s = new SoftAssert();

		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("J1129");
		IRTDDetailspage.enter_IRTDEquipName("Editing");
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		String ExpAlrtMsg = "Equipment \" J1129\" Updated successfully.";
		String ActAlertMsg = EquipmentHubPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Saved Successfully message should be displayed");
		s.assertAll();
	}

	// CUAM32
	@Test(groups = { "Regression" }, description = "CUAM32-Verify Operator is able to access the "
			+ "customized checked privilege-Delete Equipments")
	public void CUAM32() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM32-Verify Operator is able to access the customized checked privilege-Delete Equipments");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		EquipmentHubPage = MainHubPage.ClickEquipmentTile();
		EquipmentPage = EquipmentHubPage.ClickAddButton();
		EquipmentPage.EqipCreation_MandatoryFields("IRTD", "CADMN32", "CADMN32");
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		tu.click_Close_alertmsg();
		EquipmentHubPage = EquipmentPage.ClickBackBtn();
		IRTDHubPage = EquipmentHubPage.click_IRTDTile();
		IRTDDetailspage = IRTDHubPage.Click_IrtdSerialNo("CADMN32");
		IRTDHubPage = IRTDDetailspage.delete_IRTD(getUID("SysOperator"), getPW("SysOperator"));
		// System.out.println(IRTDHubPage.Is_Irtd_visible("CADMN32"));
		s.assertEquals(IRTDHubPage.Is_Irtd_visible("CADMN32"), false, "FAIL:Operator unable to delete equipment");
		s.assertAll();
	}

	// CUAM33
	@Test(groups = {
			"Regression" }, description = "CUAM33-Verify Operator is unable to access the customized unchecked-Create Asset")
	public void CUAM33() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CUAM33-Verify Operator is unable to access the customized unchecked-Create Asset");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetHubPage.Click_AddButton();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetHubPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL:User should be unable to access the customized unchecked-Create Asse");
		sa.assertAll();
	}

	// CUAM34
	@Test(groups = {
			"Regression" }, description = "CUAM34-Verify if Operator is unable to access the customized Unchecked privilege-Edit Assets")
	public void CUAM34() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM34-Verify if Operator is unable to access the customized unchecked privilege-Edit Assets");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetCreationPage = assetHubPage.Click_AddAssetButton();
		assetCreationPage.assetCreation("CuOpAst5", "73NB", "HeatBath", "HYdd", "Ind");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = assetCreationPage.clickBackBtn();
		MainHubPage = assetHubPage.clickBackBtn();
		LoginPage = MainHubPage.UserSignOut();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("CuOpAst5");
		assetDetailsPage.click_assetEditBtn_alrt();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = assetDetailsPage.AlertMsg();

		sa.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Operator should be unable to access the customized unchecked privilege-Edit Assets");
		sa.assertAll();
	}


	// CUAM35
	@Test(groups = {
			"Regression" }, description = "CUAM35-Verify if Operator is able to access the customized checked privilege-Delete Assets")
	public void CUAM35() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM35-Verify if Operator is able to access the customized checked privilege-Delete Assets");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("CuOpAst5");
		assetDetailsPage.DeleteAsset();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		sa.assertEquals(assetDetailsPage.Deletepopupwindow(), true, "FAIL:Delete popup window should be Visible");
		System.out.println("Deleted successfully");
		sa.assertAll();
	}

	// CUAM36
	@Test(groups = {
			"Regression" }, description = "CUAM36-Verify Operator is able to access the customized checked privilege -Archive")
	public void CUAM36() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CUAM36-Verify Operator is able to access the customized checked privilege-Archive");
		SoftAssert sa = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		FileManagementPage.ClickArchiveBtn();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		sa.assertEquals(FileManagementPage.ArchiveTextBoxVisible(), true, "FAIL: Archive window should be Visible");
		sa.assertAll();
	}

	// CUAM37
	@Test(groups = {
			"Regression" }, description = "CUAM37-Verify Operator is able to access the customized checked privilege-SyncIn")
	public void CUAM37() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CUAM37-Verify if Administrator is able to access the default privilege-Sync In");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncInPage = FileManagementPage.ClickSyncInBtn_SyncinPage(getUID("SysOperator"), getPW("SysOperator"));
		// UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		s.assertEquals(SyncInPage.SyncInTextBoxVisible(), true, "FAIL:Sync In TextBox should be Visible");
		s.assertAll();
	}

	// CUAM38
	@Test(groups = {
			"Regression" }, description = "CUAM38-Verify Operator is able to access the customized checked privilege-SyncOut")
	public void CUAM38() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("CUAM38-Verify if Administrator is able to access the default privilege-SyncOut");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		FileManagementPage = MainHubPage.ClickFileManagementTitle();
		SyncOutPage = FileManagementPage.ClickSyncOutBtn_SyncOutPage(getUID("SysOperator"), getPW("SysOperator"));
	
		s.assertEquals(SyncOutPage.SyncOutTextBoxVisible(), true, "FAIL:Sync Out TextBox should be Visible");
		s.assertAll();
	}

	// CUAM39
	@Test(groups = {
			"Regression" }, description = "CUAM39-Verify Operator is unable to access the customized unchecked privilege-Camera Access")
	public void CUAM39() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM39-Verify Operator is unable to access the customized checked privilege-Camera Access");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("OPE1");
		UserManagementPage.click_UserImageTile();
		UserManagementPage.click_CameraIcon();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		s.assertEquals(ActAlertMsg, ExpAlrtMsg,
				"FAIL: Operator should be unable to access the customized unchecked privilege-Camera Access");
		s.assertAll();
	}

	// CUAM40
	@Test(groups = {
			"Regression" }, description = "CUAM40-Verify operator  is unable to access the customized unchecked privilege-Access Audit Trail")
	public void CUAM40() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM40-Verify operator is unable to access the customized unchecked privilege-Access Audit Trail");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		MainHubPage.Alert_AuditTitle();

		String ActAlertMsg = MainHubPage.AlertMsg();
		String ExpAlrtMsg = "User does not have sufficient privileges to perform this operation";

		s.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: User should not able to access for Audit Trail");
		s.assertAll();
	}

	// CUAM41
	@Test(groups = { "Regression" }, description = "CUAM41-Verify if Supervisor is able to access "
			+ "the customised non-default privilege-Delete setup")
	public void CUAM41() throws InterruptedException, IOException, AWTException {
		extentTest = extent.startTest(
				"CUAM41-Verify if Supervisor is able to access the customised " + "non-default privilege-Delete setup");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysSupervisor"), getPW("SysSupervisor"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.Click_DeleteBtn_report();
		
		UserLoginPopup(getUID("SysSupervisor"), getPW("SysSupervisor"));
		s.assertEquals(assetDetailsPage.DeletePopupWindowVisible(), true, "FAIL: User should able to delete");
		s.assertAll();
	}

	// CUAM42
	@Test(groups = { "Regression" }, description = "CUAM42-Verify Operator is able to access the"
			+ "customised non-default privilege-Delete setup")
	public void CUAM42() throws InterruptedException, IOException, AWTException {
		extentTest = extent
				.startTest("CUAM42-Verify Operator is able to access the customised " + "non-default privilege-Delete setup");
		SoftAssert s = new SoftAssert();
		MainHubPage = LoginPage.Login(getUID("SysOperator"), getPW("SysOperator"));
		assetHubPage = MainHubPage.Click_AssetTile2();
		assetDetailsPage = assetHubPage.click_assetTile("SyncInAsset");
		assetDetailsPage.Click_SetupName("manual 1 min sampling");
		assetDetailsPage.Click_DeleteBtn_report();
		UserLoginPopup(getUID("SysOperator"), getPW("SysOperator"));
		s.assertEquals(assetDetailsPage.DeletePopupWindowVisible(), true, "FAIL: User should able to delete");
		s.assertAll();
	}

}
