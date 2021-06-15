package com.avs.testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import com.avs.pages.Setup_defineSetupPage;
import com.avs.pages.UserManagementPage;
import com.avs.pages.assetCreationPage;
import com.avs.pages.assetDetailsPage;
import com.avs.pages.assetHubPage;
import com.avs.utility.TestUtilities;


public class assetDetailsTest extends BaseClass {

	// Refer TestUtilities Class for Data provider methods
	// Refer Test data folder>AssetNameTestData.xlsx sheet for test data i/p

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

	// Before All the tests are conducted
	@BeforeTest
	public void AssetCreationSetup() throws InterruptedException, IOException {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		extent.addSystemInfo("VRT Version", "1.0.0.39");
		extent.addSystemInfo("BS Version", "0.6.18");
		extent.addSystemInfo("Lgr Version", "1.2.9");
		extent.addSystemInfo("User Name", "Manoj");
		extent.addSystemInfo("TestSuiteName", "AssetetailsTest");

		// Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		// Rename the cache Asset file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");
		// Rename the Asset folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");

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
			// String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver,
			// result.getName());
			// extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2));

		}
		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report

		driver.quit();
	}

	
	// 01-ASST016
	@Test(groups = { "Sanity", "Regression" }, description = "ASST016-Verify if selecting the target Asset "
			+ "tile in Asset hub page , user is navigated to the target Asset Details screen "
			+ "with Asset name & Asset Type info displayed in the Header")
	public void ASST016() throws InterruptedException {
		extentTest = extent.startTest("ASST016-Verify whether the type of the asset - eg- "
				+ "sterilizer and the name of the asset is displayed at the left top of the "
				+ "Asset details page is as per selected asset");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(assetDetailsPage.assetDetail_PageTitle(), "HeatBath - Asset01",
				"FAIL: TC-ASST016 -Incorrect AssetDetails Page title or landed into incorrect Page");
		sa.assertAll();
	}

	
	// 02-ASST017
	@Test(groups = { "Sanity", "Regression" }, description = "ASST017-Verify if Edit Icon is present"
			+ " at the right top corner of assets detail page and opens the Edit asset - asset details "
			+ "screen with the possibility to edit the selected asset")
	public void ASST017() throws InterruptedException {
		extentTest = extent
				.startTest("ASST017-Verify if clicking Edit Icon in assets " + "detail page opens the Edit asset");
		SoftAssert sa = new SoftAssert();

		// Verify Asset Edit button present or not in Asset details page
		sa.assertEquals(assetDetailsPage.assetEditBtn_state(), true,
				"FAIL: " + "TC-ASST017 -Asset Edit button not present in Asset details page");

		if (assetDetailsPage.assetEditBtn_state()) {
			assetCreationPage = assetDetailsPage.click_assetEditBtn();
		}

		// Verify clicking Asset edit button takes one to Asset creation page in edit
		// mode
		sa.assertEquals(assetCreationPage.get_newAssetCreatePagetitle(), "Edit Asset",
				"FAIL: TC-ASST017 -Incorrect AssetCreation Page title in Asset Edit mode or landed into incorrect Page");
		sa.assertAll();
	}

	
	// 03-ASST018
	@Test(groups = { "Sanity", "Regression" }, description = "ASST018-Verify if clicking on "
			+ "Back Button at the left top to return to Assets Hub page")
	public void ASST018() throws InterruptedException {
		extentTest = extent
				.startTest("ASST018-Verify if clicking on Back Button at the left top to return to Assets Hub page");
		SoftAssert sa = new SoftAssert();

		assetHubPage = assetDetailsPage.ClickBackBtn();

		sa.assertEquals(assetHubPage.assetPageTitle(), "Assets",
				"FAIL: TC-ASST018 -Incorrect Asset Hub Page title or landed into incorrect Page");
		sa.assertAll();
	}

	
	// ASST019 = Manual Test

	
	// 04-ASST020
	@Test(groups = { "Sanity", "Regression" }, description = "ASST020-Verify if the data displayed "
			+ "in the assets detail page is exactly same as the information given for asset in Create new Asset page")
	public void ASST020() throws InterruptedException, ParseException {
		extentTest = extent.startTest("ASST020-Verify if the data displayed in the assets detail page "
				+ "is exactly same as the information given for asset in Create new Asset page");
		SoftAssert sa = new SoftAssert();

		String[] act_AssetDetailData = assetDetailsPage.get_assetinfo();
		// System.out.println(Arrays.toString(act_AssetDetailData));

		assetCreationPage = assetDetailsPage.click_assetEditBtn();
		String[] act_AssetCreationData = assetCreationPage.get_assetCreationinfo();
		// System.out.println(Arrays.toString(act_AssetCreationData));

		sa.assertEquals(act_AssetDetailData, act_AssetCreationData,
				"FAIL: TC-ASST020 -Mismatch in the Asset data compared between Asset details & Asset creation Page");
		sa.assertAll();
	}

	
	// 05-ASST021
	@Test(groups = { "Regression" }, description = "ASST021-Verify the first tier at the right hand side of the asset "
					+ "detail page should have the following tiles arranged sequentially- Setups, Qualifications,Reports, Documents.")
	public void ASST021() throws InterruptedException, ParseException {
		extentTest = extent
				.startTest("ASST021-Verify the first tier at the right hand side of the asset detail page should "
						+ "have the following tiles arranged sequentially- Setups, Qualifications,Reports, Documents.");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(assetDetailsPage.setupTile_state(), true,
				"FAIL: TC-ASST021 - Setup tile absent under Asset details page");
		sa.assertEquals(assetDetailsPage.qualTile_state(), true,
				"FAIL: TC-ASST021 - Qual tile absent under Asset details page");
		sa.assertEquals(assetDetailsPage.reportsTile_state(), true,
				"FAIL: TC-ASST021 - Reports tile absent under Asset details page");
		sa.assertEquals(assetDetailsPage.docsTile_state(), true,
				"FAIL: TC-ASST021 - Docs tile absent under Asset details page");
		sa.assertAll();
	}
	
	
	// 06-ASST022
	@Test(groups = { "Regression" }, description = "ASST022-Verify for  a fresh asset with no activities - "
			+ "Setups, Qualifications, Reports and Docs -  as mentioned, all tiles should display 0")
	public void ASST022() throws InterruptedException, ParseException {
		extentTest = extent
				.startTest("ASST022-Verify for  a fresh asset with no activities for "
						+ "Setups, Qualifications, Reports and Docs -  as mentioned, all tiles should display 0");
		SoftAssert sa = new SoftAssert();
		
		sa.assertEquals(assetDetailsPage.setupTile_countdata(), "0",
				"FAIL: TC-ASST022 - Setup tile count displayed >0 under Asset details page");
		sa.assertEquals(assetDetailsPage.qualTile_countdata(), "0",
				"FAIL: TC-ASST022 - Qual tile count displayed >0 under Asset details page");
		sa.assertEquals(assetDetailsPage.reportsTile_countdata(), "0",
				"FAIL: TC-ASST022 - Reports tile count displayed >0 under Asset details page");
		sa.assertEquals(assetDetailsPage.docsTile_countdata(), "0",
				"FAIL: TC-ASST022 - Docs tile count displayed >0 under Asset details page");
		sa.assertAll();
	}
	

	
	// 07-ASST024
	@Test(groups = { "Regression" }, description = "ASST024-Verify if any number of documents uploaded,"
			+ "the same number is displayed on the documents tile")
	public void ASST024() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ASST024-Verify if  any number of documents uploaded,"
						+ "the same number is displayed on the documents tile");
		SoftAssert sa = new SoftAssert();
				
		//Close Vrt app
		assetDetailsPage.click_vrtAppcloseBtn();
		
		// Rename the cache Asset file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");
		// Rename the Asset folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		
		Thread.sleep(2000);
		
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		
		// Method to Create 1st Asset 
		assetHubPage=MainHubPage.ClickAssetTile();
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithAllFieldEntry("Asset01", "01", "HeatBath", "AAS", "Hyderabad", "VRT-RF", "2",
				"cu", "5", "Weeks", "1st Asset Creation");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		assetHubPage=assetCreationPage.clickBackBtn();
		assetDetailsPage = assetHubPage.click_assetTile("Asset01");
		
		
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_UploadDocsBtn();		
		//Upload File 1
		assetDetailsPage.uploadDoc_Assetdetails("HelpFileWord.docx");		
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_UploadDocsBtn();
		//Upload File 2
		assetDetailsPage.uploadDoc_Assetdetails("RW Review_Chapter 4.pdf");			

		sa.assertEquals(assetDetailsPage.docsTile_countdata(), "2",
				"FAIL: TC-ASST024 - Docs tile count not matching to the total amount of Docs(2) uploaded");
		sa.assertAll();
	}
	
	
	// 08-ASST025
	@Test(groups = { "Regression" }, description = "ASST025-Verif if the application declines uploading documents that has same name")
	public void ASST025() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ASST025-Verif if the application declines uploading documents that has same name");
		SoftAssert sa = new SoftAssert();
				
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_UploadDocsBtn();		
		//Upload File 1
		assetDetailsPage.uploadDoc_Assetdetails("HelpFileWordPartOneReview.docx");		
		assetDetailsPage.click_DocsTileBtn();
		assetDetailsPage.click_UploadDocsBtn();
		//Upload File 2
		assetDetailsPage.uploadDoc_Assetdetails("HelpFileWordPartOneReview.docx");	
		
		String expAlert_msg = "Another file with same name already exists. Please use different name.";
		String actAlert_msg = assetDetailsPage.alertMeg_duplicateDocupload_Assetdetails();


		sa.assertEquals(actAlert_msg, expAlert_msg,
				"FAIL: TC-ASST025 - Duplicate alert message not displayed while uploading duplicate document");
		sa.assertAll();
	}
	
	
	// 09-ASST029
	@Test(groups = { "Regression" }, description = "ASST029-Verify Initiate Qualification, "
			+ "Initiate Calibration and Initiate Verification buttons are enable if any study is selected")
	public void ASST029() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ASST029-Verify Initiate Qualification, Initiate Calibration and "
				+ "Initiate Verification buttons are enable if any study is selected");
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(assetDetailsPage.InitiateQualBtn_state(), false,
				"FAIL: TC-ASST029 - Initiate Qual button appears to be enabled without any Setup file created");
		sa.assertAll();
	}
	
	
	// 10-ASST033
	@Test(groups = { "Sanity", "Regression" }, description = "ASST033-Verify  New button is available "
			+ "and clicking on button user should able to define new setup")
	public void ASST033() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ASST033-Verify  New button is available and clicking on "
				+ "button user should able to define new setup");
		SoftAssert sa = new SoftAssert();

		defineSetupPage = assetDetailsPage.click_NewStupCreateBtn();

		sa.assertEquals(defineSetupPage.defineSetupPage_state(), true,
				"FAIL: TC-ASST033 - Clicking New Setup button under Sstup tile did not launch Define Setup page");
		sa.assertAll();
	}
	
	
	// 11-ASST034
	@Test(groups = { "Sanity", "Regression" }, description = "ASST034-Verify if only one Asset is available then "
			+ "copy setup screen should not allow any action")
	public void ASST034() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ASST034-Verify if only one Asset is available then copy setup screen should not allow any action");
		SoftAssert sa = new SoftAssert();

		assetDetailsPage.click_CopyStup_Btn();
		
		String expAlert_msg = "To perform Copy Setup more than 1 asset required.";
		String actAlert_msg = assetDetailsPage.alertMeg_CopyAsset_WithOneAsset();

		sa.assertEquals(actAlert_msg, expAlert_msg,
				"FAIL: TC-ASST034 - Copy asset window appears even if only Asset present");
		sa.assertAll();
	}
	
	
	
	

}
