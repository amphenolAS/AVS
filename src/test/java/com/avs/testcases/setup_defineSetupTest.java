package com.avs.testcases;

import java.io.IOException;
import java.text.ParseException;

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
import com.avs.pages.Setup_SensorConfigPage;
import com.avs.pages.Setup_defineSetupPage;
import com.avs.pages.UserManagementPage;
import com.avs.pages.assetCreationPage;
import com.avs.pages.assetDetailsPage;
import com.avs.pages.assetHubPage;
import com.avs.utility.TestUtilities;
import com.avs.utility.setupCreationUtility;

public class setup_defineSetupTest extends BaseClass{

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>SetupTestData.xlsx sheet for test data i/p

	public ExtentReports extent;
	public ExtentTest extentTest;

	// Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;
	assetDetailsPage assetDetailsPage;
	Setup_defineSetupPage defineSetupPage;
	Setup_SensorConfigPage SensorConfigPage;

	// Before All the tests are conducted
	@BeforeTest
	public void AssetCreationSetup() throws InterruptedException, IOException {		

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		extent.addSystemInfo("VRT Version", "1.0.0.37");
		extent.addSystemInfo("BS Version", "0.6.13");
		extent.addSystemInfo("Lgr Version", "1.2.6");
		extent.addSystemInfo("User Name", "Manoj");
		extent.addSystemInfo("TestSuiteName", "AssetHubTest");

		// Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		// Rename the cache Asset file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");
		// Rename the Asset folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		// Rename the cache Setup file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache\\ValProbeRT", "Setup.txt");
		// Rename the VRT Setups folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "VRTSetups");

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();

		// Method to Create Very 1st User with All privilege
		UserManagementPage=LoginPage.DefaultLogin();
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

		// Method to Create 1st Asset 
		assetHubPage=MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithAllFieldEntry("Asset01", "01", "HeatBath", "AAS", "Hyderabad", "VRT-RF", "2",
				"cu", "5", "Weeks", "1st Asset Creation");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));

		AppClose();
		Thread.sleep(1000);

	}

	// After All the tests are conducted
	@AfterTest
	public void endReport_releaseMomory() {
		extent.flush();
		extent.close();
		assetHubPage.resetWebElements();
		// System.out.println("Reset Webelement memory released");
	}

	// Before Method(Test) method
	@BeforeMethod(alwaysRun = true)
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();		
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");
		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();
	}

	// TearDown of the App
	@AfterMethod(alwaysRun = true)
	public void Teardown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			// to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getName() + " #");
			// to add error/exception in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # " + result.getThrowable() + " #");
			// to add screenshot in extent report
			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1));
			// to add screencast/video in extent report
			// extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName() + " #");
			// to add screenshot in extent report
			//String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver, result.getName());
			//extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2));

		}
		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report

		driver.quit();
	}
	
	
	// Test Cases
	// 01-SET002
	@Test(groups = {
			"Regression" }, description = "SET 002-UI_Verify if on Asset Details  page the _Setups_ tile is active")
	public void SET002() throws InterruptedException {
		extentTest = extent
				.startTest("SET 002-UI_Verify if on Asset Details  page the _Setups_ tile is active");
		SoftAssert sa = new SoftAssert();
		
		defineSetupPage.click_defineSetupPage_backBtn();
		assetDetailsPage=defineSetupPage.click_YesofAlert_msg();
		

		sa.assertEquals(assetDetailsPage.get_Setupheader_txt(), "Setups", "FAIL: SET 002-Setup tile is not Active under Asset details page");
		sa.assertAll();
	}
	
	
	// 02-SET009
	@Test(groups = {
			"Regression" }, description = "SET 009- UI_Verify if the setup name created in the _Define Setup_ page is "
					+ "displayed at the top left of the Sensors Configuration page when click on the next tab at Define Setup")
	public void SET009() throws InterruptedException {
		extentTest = extent
				.startTest("SET 009- UI_Verify if the setup name created in the _Define Setup_ page is displayed at the top "
						+ "left of the Sensors Configuration page when click on the next tab at Define Setup");
		SoftAssert sa = new SoftAssert();
		
		String SetupNameEntered = defineSetupPage.get_defineSetupPage_setupName();
		//System.out.println(SetupNameEntered);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.enter_defineSetupPage_SensorCount("1");
		SensorConfigPage=defineSetupPage.click_defineSetupPage_nxtBtn();
		String SetupNameDisplayed = SensorConfigPage.get_SensorConfigurationPage_titletext();
		//System.out.println(SetupNameDisplayed);
		

		sa.assertEquals(SetupNameEntered, SetupNameDisplayed, 
				"FAIL: SET 009-Setup Name not displayed in the header of Sensor Config page of Setup");
		sa.assertAll();
	}
	
	
	// 03-SET010
	@Test(groups = {
			"Regression" }, description = "SET 010- UI_Verify if _New Setup_, _Type of Asset_ , _Equipment Name_ is "
					+ "displayed at the top left of the Define Setup page when clicked on _ (+) New_ button in Asset details page.")
	public void SET010() throws InterruptedException {
		extentTest = extent
				.startTest("SET 010- UI_Verify if _New Setup_, _Type of Asset_ , _Equipment Name_ is displayed at the top left of the "
						+ "Define Setup page when clicked on _ (+) New_ button in Asset details page.");
		SoftAssert sa = new SoftAssert();
		
		String DefineSetupTitleTxt = defineSetupPage.get_defineSetupPage_Titletext();
		String StHd1 = DefineSetupTitleTxt.split("-")[0];
		//System.out.println(StHd1);
		String StHd2 = DefineSetupTitleTxt.split("-")[1];
		//System.out.println(StHd2);
		String StHd3 = DefineSetupTitleTxt.split("-")[2];
		//System.out.println(StHd3);
		
		defineSetupPage.click_defineSetupPage_backBtn();
		assetDetailsPage=defineSetupPage.click_YesofAlert_msg();
		String AstHeaderTxt = assetDetailsPage.assetDetail_PageTitle();
		String ADHd1 = AstHeaderTxt.split(" - ")[0];
		//System.out.println(ADHd1);
		String ADHd2 = AstHeaderTxt.split(" - ")[1];
		//System.out.println(ADHd2);		

		sa.assertEquals(StHd1, "New Setup", 
				"FAIL: SET 010-Define Setup Header text mismatches for New Setup String");
		sa.assertEquals(StHd2, ADHd1, 
				"FAIL: SET 010-Define Setup Header text mismatches for Asset Type String");
		sa.assertEquals(StHd3, ADHd2, 
				"FAIL: SET 010-Define Setup Header text mismatches for Asset Name String");
		sa.assertAll();
	}
	
	
	// 04-SET011
	@Test(groups = {
			"Sanity", "Regression" }, description = "SET 011- UI_Verify if _Setup Name_ (mandatory field) "
					+ "is displayed in the _Define Setup_ screen.")
	public void SET011() throws InterruptedException {
		extentTest = extent
				.startTest("SET 011- UI_Verify if _Setup Name_ (mandatory field) is displayed "
						+ "in the _Define Setup_ screen.");
		SoftAssert sa = new SoftAssert();
		
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.enter_defineSetupPage_SensorCount("1");
		defineSetupPage.click_defineSetupPage_nxtBtn();

		String ExpAlertMsg = "Setup Name is mandatory, please enter Setup Name";
		String ActualAlertMsg = defineSetupPage.get_ButtomBarAlertmsg_txt();
		//System.out.println(ActualAlertMsg);

		sa.assertEquals(ActualAlertMsg, ExpAlertMsg, 
				"FAIL: SET 011-Setup Name field mandatory alert message not displayed or Wrong Alert msg");
		sa.assertAll();
	}
	
	
	// 05-SET012
	@Test(groups = {
			"Sanity", "Regression" }, description = "SET 012- UI_Verify if  _Number of Sensors _ (mandatory field)"
					+ " is displayed in the _Define Setup_ screen.")
	public void SET012() throws InterruptedException {
		extentTest = extent
				.startTest("SET 012- UI_Verify if  _Number of Sensors _ (mandatory field) is displayed "
						+ "in the _Define Setup_ screen.");
		SoftAssert sa = new SoftAssert();
		
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.click_defineSetupPage_nxtBtn();

		String ExpAlertMsg = "Number of Sensors is mandatory, please enter Number of Sensors";
		String ActualAlertMsg = defineSetupPage.get_ButtomBarAlertmsg_txt();
		System.out.println(ActualAlertMsg);

		sa.assertEquals(ActualAlertMsg, ExpAlertMsg, 
				"FAIL: SET 012-Sensor Data field mandatory alert message not displayed or Wrong Alert msg");
		sa.assertAll();
	}
	
	
	// 06-SET013
	@Test(groups = {
			"Sanity", "Regression" }, description = "SET 013- UI_Verify if  _ Asset ID_ (prepoluted field) "
					+ "is displayed in the _Define Setup_ screen.")
	public void SET013() throws InterruptedException {
		extentTest = extent
				.startTest("SET 013-Verify if Asset ID (prepoluted field) is displayed in the _Define Setup_ screen.");
		SoftAssert sa = new SoftAssert();
		
		String AssetIDTxtinSetup = defineSetupPage.get_AssetID_text();
		//System.out.println(AssetIDTxtinSetup);		
		defineSetupPage.click_defineSetupPage_backBtn();
		assetDetailsPage=defineSetupPage.click_YesofAlert_msg();
		assetCreationPage = assetDetailsPage.click_AssetEditBtn();
		String AssetIDTxtinAssetEditPage = assetCreationPage.getEqpID();
		//System.out.println(AssetIDTxtinAssetEditPage);

		sa.assertEquals(AssetIDTxtinAssetEditPage, AssetIDTxtinSetup, 
				"FAIL: SET 013-Asset ID field Data do not match with the actual Asset ID created");
		sa.assertAll();
	}
	
	
	// 07-SET017
	@Test(groups = {
			"Sanity", "Regression" }, description = "'SET 017-UI_Verify if the setup name field, "
					+ "by default is displayed as current date and 24 hour time format DD-MMM-YYYY HH-MM-SS")
	public void SET017() throws InterruptedException, ParseException {
		extentTest = extent
				.startTest("'SET 017-UI_Verify if the setup name field, by default is displayed "
						+ "as current date and 24 hour time format DD-MMM-YYYY HH-MM-SS");
		SoftAssert sa = new SoftAssert();
		
		defineSetupPage.click_defineSetupPage_backBtn();
		assetDetailsPage=defineSetupPage.click_YesofAlert_msg();
		
		TestUtilities tu = new TestUtilities();
		String Current_DtTime_txt = tu.get_CurrentDate_inCertainFormat("dd-MMM-YYYY HH:mm:ss");
		String Current_DtTime1 = Current_DtTime_txt.split("0", 2)[1];		
		//System.out.println(Current_DtTime1);
		String[] output = Current_DtTime1.split("\\:");		
		String Current_DtTime2 = output[0]+":"+output[1];
		//System.out.println(Current_DtTime2);
		
		defineSetupPage=assetDetailsPage.click_NewStupCreateBtn();
		String SetupName_txt = defineSetupPage.get_defineSetupPage_setupName();
		//System.out.println(SetupName_txt);	
		
		if (SetupName_txt.equals(Current_DtTime1) || (SetupName_txt.contains(Current_DtTime2))) {
			sa.assertEquals(true, true);
			sa.assertAll();
		} else {
			sa.assertEquals(false, true, "FAIL: SET 017-Setup Name default data does not match with Current Date & Time stamp");
			sa.assertAll();
		}
	}
	
	
	// 08-SET019
	@Test(groups = {
			"Sanity", "Regression" }, dataProvider="SET019", dataProviderClass=setupCreationUtility.class,
					description = "SET 019-UI_Verify if the setup name text field allows up to "
					+ "35 character input that comprises of alphanumeric, special characters -,_,: and space.")
	public void SET019(Object ...dataProvider) throws InterruptedException, ParseException {
		extentTest = extent
				.startTest("SET 019-UI_Verify if the setup name text field allows up to 35 character input that "
						+ "comprises of alphanumeric, special characters -,_,: and space.");
		SoftAssert sa = new SoftAssert();
		
		String SetUpName = (String) dataProvider[0];
		System.out.println(SetUpName);
		String SensorNumb = (String) dataProvider[1];		
			
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetUpName);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		SensorConfigPage=defineSetupPage.click_defineSetupPage_nxtBtn();
		
		sa.assertEquals(SensorConfigPage.get_SensorConfigurationPage_text(), "Sensors Configuration", 
				"FAIL:SET 019- Setup Name do not accept the Valid characters ");
				
		sa.assertAll();

	}
	
	
	// 09-SET020
	@Test(groups = {
			"Regression" }, dataProvider="SET020", dataProviderClass=setupCreationUtility.class,
					description = "SET 019-UI_Verify if the setup name text field do not allow "
							+ "invalid data except alphanumeric, special characters -,_,: and space.")
	public void SET020(Object ...dataProvider) throws InterruptedException, ParseException {
		extentTest = extent
				.startTest("SET020-UI_Verify if the setup name text field do not allow invalid data "
						+ "except alphanumeric, special characters -,_,: and space.");
		SoftAssert sa = new SoftAssert();
		
		String SetUpName = (String) dataProvider[0];
		System.out.println(SetUpName);
		String SensorNumb = (String) dataProvider[1];	
		String ErrorAlertMsg = (String) dataProvider[2];
			
		defineSetupPage.clear_defineSetupPage_setupName();
		defineSetupPage.enter_defineSetupPage_setupName(SetUpName);
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		defineSetupPage.click_defineSetupPage_nxtBtn();
		
		sa.assertEquals(defineSetupPage.get_ButtomBarAlertmsg_txt(), ErrorAlertMsg, 
				"FAIL:SET020- Setup Name accepts the In-Valid characters ");	
				
		sa.assertAll();

	}
	
	
	// 10-SET024
	@Test(groups = {
			"Sanity", "Regression" }, dataProvider="SET024", dataProviderClass=setupCreationUtility.class,
					description = "SET 024-UI_Verify if the _Number of Sensors_ text box is editable and allows "
							+ "only numeric up to 3 digits. i.e. from 1 to 300")
	public void SET024(Object ...dataProvider) throws InterruptedException, ParseException {
		extentTest = extent
				.startTest("SET 024-UI_Verify if the _Number of Sensors_ text box is editable and allows only "
						+ "numeric up to 3 digits. i.e. from 1 to 300");
		SoftAssert sa = new SoftAssert();
		
		String SensorNumb = (String) dataProvider[0];	
		System.out.println(SensorNumb);		
			
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		
		sa.assertEquals(SensorConfigPage.get_SensorConfigurationPage_text(), "Sensors Configuration", 
				"FAIL:SET024- Setup Sensor Count field do NOT accept the Valid data ");		
		
		sa.assertAll();

	}
	
	
	// 11-SET025
	@Test(groups = {
			"Regression" }, dataProvider="SET025", dataProviderClass=setupCreationUtility.class,
					description = "SET 025-UI_Verify if _Number of Sensors_ field can accept only numeric values.")
	public void SET025(Object ...dataProvider) throws InterruptedException, ParseException {
		extentTest = extent
				.startTest("SET 025-UI_Verify if _Number of Sensors_ field can accept only numeric values.");
		SoftAssert sa = new SoftAssert();
		
		String SensorNumb = (String) dataProvider[0];	
		System.out.println(SensorNumb);
		String ErrorAlertMsg = (String) dataProvider[1];
			
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		defineSetupPage.click_defineSetupPage_nxtBtn();
		
		sa.assertEquals(defineSetupPage.get_ButtomBarAlertmsg_txt(), ErrorAlertMsg, "FAIL:SET020- Setup "
				+ "Sensor Count field accepts the In-Valid characters");		
				
		sa.assertAll();

	}
	
	
	// 12-SET026a
	@Test(groups = {
			"Regression" }, dataProvider="SET026a", dataProviderClass=setupCreationUtility.class,
					description = "SET 026-UI_Verify if _SOP Protocol Number_ field  can accept upto 50 characters that comprises"
							+ " of alphanumeric and special character like hyphen, underscore, space, Slash (Forward  and backward ).")
	public void SET026a(Object ...dataProvider) throws InterruptedException, ParseException {
		extentTest = extent
				.startTest("SET 026-UI_Verify if _SOP Protocol Number_ field  can accept upto 50 characters that comprises of "
						+ "alphanumeric and special character like hyphen, underscore, space, Slash (Forward  and backward ).");
		SoftAssert sa = new SoftAssert();
		
		String SensorNumb = (String) dataProvider[0];	
		String SOP = (String) dataProvider[1];
		System.out.println(SOP);			
			
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		defineSetupPage.click_defineSetupPage_SOPField();
		defineSetupPage.clear_defineSetupPage_SOP();
		defineSetupPage.enter_defineSetupPage_SOP(SOP);
		SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		
		sa.assertEquals(SensorConfigPage.get_SensorConfigurationPage_text(), "Sensors Configuration", 
				"FAIL:SET026a- Setup SOP field do NOT accept the Valid data ");	
		sa.assertAll();

	}
	
	
	// 13-SET026b
	@Test(groups = {
			"Regression" }, dataProvider="SET026b", dataProviderClass=setupCreationUtility.class,
					description = "SET 026-UI_Verify if _SOP Protocol Number_ field  can accept upto 50 characters that comprises"
							+ " of alphanumeric and special character like hyphen, underscore, space, Slash (Forward  and backward ).")
	public void SET026b(Object ...dataProvider) throws InterruptedException, ParseException {
		extentTest = extent
				.startTest("SET 026-UI_Verify if _SOP Protocol Number_ field  can accept upto 50 characters that comprises of "
						+ "alphanumeric and special character like hyphen, underscore, space, Slash (Forward  and backward ).");
		SoftAssert sa = new SoftAssert();
		
		String SensorNumb = (String) dataProvider[0];	
		String SOP = (String) dataProvider[1];
		System.out.println(SOP);	
		String ErrorAlertMsg = (String) dataProvider[2];
			
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		defineSetupPage.click_defineSetupPage_SOPField();
		defineSetupPage.clear_defineSetupPage_SOP();
		defineSetupPage.enter_defineSetupPage_SOP(SOP);
		SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();
		
		sa.assertEquals(defineSetupPage.get_ButtomBarAlertmsg_txt(), ErrorAlertMsg, 
				"FAIL: SET026b- Setup SOP field accepts In-Valid data ");
		sa.assertAll();

	}
	
	
	// 14-SET027a
	@Test(groups = { "Sanity",
			"Regression" }, dataProvider = "SET027a", dataProviderClass = setupCreationUtility.class,
			description = "SET 027-UI_Verify if _Load Description_ field  can accept upto 50 characters that comprises"
					+ " of alphanumeric and special character like hyphen, underscore, space, Slash (Forward  and backward ).")
	public void SET027a(String SensorNumb, String LD) throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SET 027-UI_Verify if _Load Description_ field  can accept upto 50 characters that comprises of "
						+ "alphanumeric and special character like hyphen, underscore, space, Slash (Forward  and backward ).");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		defineSetupPage.click_defineSetupPage_LoadDescField();
		defineSetupPage.clear_defineSetupPage_LoadDesc();
		defineSetupPage.enter_defineSetupPage_LoadDesc(LD);
		SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();

		sa.assertEquals(SensorConfigPage.get_SensorConfigurationPage_text(), "Sensors Configuration",
				"FAIL: SET027a- Setup Load Description field do NOT accept the Valid data ");
		sa.assertAll();

	}
		
		
	// 15-SET027b
	@Test(groups = {
			"Regression" }, dataProvider = "SET027b", dataProviderClass = setupCreationUtility.class, 
					description = "SET 027-UI_Verify if _Load Description_ field  can accept upto 50 characters that comprises"
					+ " of alphanumeric and special character like hyphen, underscore, space, Slash (Forward  and backward ).")
	public void SET027b(String SensorNumb, String LD, String ErrorAlertMsg)
			throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SET027b-UI_Verify if _Load Description_ field  can accept upto 50 characters that comprises of "
						+ "alphanumeric and special character like hyphen, underscore, space, Slash (Forward  and backward ).");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		defineSetupPage.click_defineSetupPage_LoadDescField();
		defineSetupPage.clear_defineSetupPage_LoadDesc();
		defineSetupPage.enter_defineSetupPage_LoadDesc(LD);
		SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();

		sa.assertEquals(defineSetupPage.get_ButtomBarAlertmsg_txt(), ErrorAlertMsg,
				"FAIL: SET027b- Setup Load Description field accepts In-Valid data ");
		sa.assertAll();

	}
	
	// 16-SET028a
	@Test(groups = { "Sanity",
			"Regression" }, dataProvider = "SET028", dataProviderClass = setupCreationUtility.class,
			description = "SET 028-UI_Verify if the _Comments_ field accepts up to 25 characters input "
					+ "that comprises of alphanumeric and unlimited special characters.")
	public void SET028a(String SensorNumb, String Cmnt) throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SET 028-UI_Verify if the _Comments_ field accepts up to 25 characters input that "
				+ "comprises of alphanumeric and unlimited special characters.");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount(SensorNumb);
		defineSetupPage.click_defineSetupPage_commentsField();
		defineSetupPage.clear_defineSetupPage_comments();
		defineSetupPage.enter_defineSetupPage_comments(Cmnt);
		SensorConfigPage = defineSetupPage.click_defineSetupPage_nxtBtn();

		sa.assertEquals(SensorConfigPage.get_SensorConfigurationPage_text(), "Sensors Configuration",
				"FAIL: SET028- Setup Comment field do NOT accept the Valid data ");
		sa.assertAll();

	}
		
		
	// 17-SET028b
	@Test(groups = { "Regression" }, description = "SET 028-UI_Verify if the _Comments_ field accepts up to 25 characters input")
	public void SET028b()
			throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SET 028-UI_Verify if the _Comments_ field accepts up to 25 characters input");
		SoftAssert sa = new SoftAssert();

		String expectedtxt = "1234567890123456789012345a";  //26 Char input
		
		defineSetupPage.click_defineSetupPage_SensorCountField();
		defineSetupPage.clear_defineSetupPage_SensorCount();
		defineSetupPage.enter_defineSetupPage_SensorCount("1");
		defineSetupPage.click_defineSetupPage_commentsField();
		defineSetupPage.clear_defineSetupPage_comments();
		defineSetupPage.enter_defineSetupPage_comments(expectedtxt);
		String actualtextentered = defineSetupPage.get_defineSetupPage_comments_txtData();

		sa.assertEquals(actualtextentered.length(), 25,
				"FAIL: SET028b- Setup Comment field accepts more than 25 Character ");
		sa.assertAll();

	}
	
	
	// 18-SET029
	@Test(groups = { "Sanity", "Regression" }, 
			description = "SET 029- Fnc_Verify if clicking on the back button pops up a warning message, "
					+ "You are about to lose your changes. Do you want to continue_ with Yes and No buttons.")
	public void SET029() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SET 029- Fnc_Verify if clicking on the back button pops up a warning message, _You are about to "
				+ "lose your changes. Do you want to continue_ with Yes and No buttons.");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.click_defineSetupPage_backBtn();
		

		sa.assertEquals(defineSetupPage.visible_AlertMsg_state(), true,
				"FAIL: SET029- No ALert message displayed on clicking the Setup Back Button");
		sa.assertAll();

	}
	
	
	// 19-SET030
	@Test(groups = { "Sanity", "Regression" }, 
			description = "SET 030- Fnc_Verify if clicking on _Yes_ discards the changes made "
					+ "and bring application back to Assets details page")
	public void SET030() throws InterruptedException, ParseException {
		extentTest = extent.startTest(
				"SET 030- Fnc_Verify if clicking on _Yes_ discards the changes made and bring "
				+ "application back to Assets details page");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.click_defineSetupPage_backBtn();
		assetDetailsPage=defineSetupPage.click_YesofAlert_msg();
		

		sa.assertEquals(assetDetailsPage.get_Setupheader_txt(), "Setups",
				"FAIL: SET030- Acknowledging the ALert message displayed on clicking the Setup Back Button"
				+ "do not take one to the Asset Details page");
		sa.assertAll();

	}
	
	
	// 20-SET031
	@Test(groups = { "Regression" }, 
			description = "SET 031-Fnc_Verify if clicking on _No_ allows the user to stay in the current page")
	public void SET031() throws InterruptedException, ParseException {
		extentTest = extent.startTest("SET 031-Fnc_Verify if clicking on _No_ allows the user to stay in the current page.");
		SoftAssert sa = new SoftAssert();

		defineSetupPage.click_defineSetupPage_backBtn();
		defineSetupPage.click_NoofAlert_msg();;
		

		sa.assertEquals(defineSetupPage.defineSetupPage_state(), true,
				"FAIL: SET031- Clicking No to the ALert message displayed on clicking the Setup Back Button"
				+ "do not retain in the Define Setup Page");
		sa.assertAll();

	}
	


}
