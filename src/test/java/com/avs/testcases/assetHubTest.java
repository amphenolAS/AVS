/**
 * @author manoj.ghadei
 *
 */

package com.avs.testcases;

import java.io.IOException;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.avs.base.BaseClass;
import com.avs.pages.LoginPage;
import com.avs.pages.MainHubPage;
import com.avs.pages.UserManagementPage;
import com.avs.pages.assetCreationPage;
import com.avs.pages.assetHubPage;
import com.avs.pages.assetDetailsPage;

import com.avs.utility.TestUtilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class assetHubTest extends BaseClass {

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
	
	
	//Before All the tests are conducted
	@BeforeTest
	public void AssetCreationSetup() throws InterruptedException, IOException {
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html",true);
		extent.addSystemInfo("VRT Version", "1.0.0.37");
		extent.addSystemInfo("BS Version", "0.6.13");
		extent.addSystemInfo("Lgr Version", "1.2.6");
		extent.addSystemInfo("User Name", "Manoj");
		extent.addSystemInfo("TestSuiteName", "AssetHubTest");

		//Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");		
		//Rename the cache Asset file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");		
		//Rename the Asset folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");

		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		UserManagementPage = LoginPage.DefaultLogin();
		LoginPage = UserManagementPage.FirstUserCreation("User1", getUID("adminFull"), getPW("adminFull"), getPW("adminFull"), "FullAdmin",
				"12345678", "abc@gmail.com");
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
		Thread.sleep(1000);
	}

	//After All the tests are conducted
	@AfterTest
	public void endReport_releaseMomory(){
		extent.flush();
		extent.close();
		assetHubPage.resetWebElements();
		//System.out.println("Reset Webelement memory released");
	}
	
	//Before Method(Test) method
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();		
	}

	//TearDown of the App
	@AfterMethod(alwaysRun=true)
	public void Teardown(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # "+result.getName()+" #"); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS # "+result.getThrowable()+" #"); //to add error/exception in extent report
			
			String screenshotPath1 = TestUtilities.getFailedTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath1)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS # " + result.getName()+" #");
			//String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver, result.getName());
			//extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2)); //to add screenshot in extent report
		}		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report		
		
		driver.quit();
	}

	//ASST001
	@Test(groups = {"Sanity", "Regression"}, description = "ASST001-Verify if selecting the Assets "
			+ "tile from the main hub page , user is navigated to the Asset Details screen")
	public void ASST001() throws InterruptedException {
		extentTest = extent.startTest("ASST001-Verify if selecting the Assets "
				+ "tile from the main hub page , user is navigated to the Asset Details screen");
		SoftAssert sa1 = new SoftAssert();

		sa1.assertEquals(assetHubPage.assetPageTitle(), "Assets",
				"FAIL: TC-ASST001 -Incorrect Asset Page title or landed into incorrect Page");
		sa1.assertAll();	
	}	
	
	
	// ASST002
	@Test(groups = {"Regression"}, description = "ASST002-Verify if with  fresh installation,"
			+ " no assets should be displayed")
	public void ASST002() throws InterruptedException {
		extentTest = extent.startTest("ASST002-Verify if with  fresh installation," + 
				" no assets should be displayed");
		SoftAssert sa2 = new SoftAssert();

		sa2.assertEquals(assetHubPage.assetcount(), "0",
				"FAIL: TC-ASST002 -Already assets present in the system which "
				+ "suggests its not an Fresh Installation may have been upgraded");
		sa2.assertAll();
	}
	
	
	//ASST003 TC = ASST149 below
	
	
	//ASST004
	@Test(groups = {"Regression"}, description = "ASST004-Verify if Predefined Sort Options namely -Type,"
			+ "Manufacturer and Location are present")
	public void ASST004() throws InterruptedException {
		extentTest = extent.startTest("ASST004-Verify if Predefined Sort Options namely -Type,"
			+ "Manufacturer and Location are present");
		SoftAssert sa3 = new SoftAssert();

		sa3.assertEquals(assetHubPage.typeFilterBtnstate(), true, "FAIL: TC-ASST004 -Type FIlter is absent from the Assethub Page");
		sa3.assertEquals(assetHubPage.manufacturerFilterBtnstate(), true, "FAIL: TC-ASST004 -Manufacturer FIlter is absent from the Assethub Page");
		sa3.assertEquals(assetHubPage.locationFilterBtnstate(), true, "FAIL: TC-ASST004 -Location FIlter is absent from the Assethub Page");
		sa3.assertAll();
	}
	
	
	//ASST005
	@Test(groups = {"Regression"}, description = "ASST005-Verify  if Add New and Search -magnifier Icons"
	 +"are present at the right top corner of the assets page")
	public void ASST005() throws InterruptedException {
		extentTest = extent.startTest("ASST005-Verify  if Add New and Search -magnifier Icons"
	 +"are present at the right top corner of the assets page");
		SoftAssert sa4 = new SoftAssert();

		sa4.assertEquals(assetHubPage.serachAstBtn_state(), true, "FAIL: TC-ASST005 -Asset Search button missing");
		sa4.assertEquals(assetHubPage.addAst(), true, "FAIL: TC-ASST005 -Asset Add button missing");
		
		sa4.assertAll();
	}
		
	
	//ASST006
	@Test(groups = {"Regression"}, description = "ASST006-Verify  if clicking on New icon opens a New Asset creation page")
	public void ASST006() throws InterruptedException {
		extentTest = extent.startTest("ASST006-Verify  if clicking on New icon opens a New Asset creation page");
		SoftAssert sa5 = new SoftAssert();
		
		assetCreationPage=assetHubPage.ClickAddAssetBtn();

		sa5.assertEquals(assetCreationPage.newAssetCreatePagetitle(), true, "FAIL: TC-ASST006 -Landed "
				+ "to Wrong page instead of New Asset creation page");				
		sa5.assertAll();
	}
	
	
	//ASST007
	@Test(groups = {"Regression"}, description = "ASST007-Verify if clicking on Back Button"
			+ " at the left top to return to main Hub page")
	public void ASST007() throws InterruptedException {
		extentTest = extent.startTest("ASST007-Verify if clicking on Back Button"
			+ " at the left top to return to main Hub page");
		SoftAssert sa6 = new SoftAssert();
		
		MainHubPage=assetHubPage.ClickBackBtn();

		sa6.assertEquals(MainHubPage.mainPageTitle(), true, "FAIL: TC-ASST007 -Landed "
				+ "to Wrong page instead of Main Hub page");				
		sa6.assertAll();
	}
	

	//ASST008
	@Test(groups = {"Sanity", "Regression"}, description = "ASST008-Verify if the help section in the "
			+ "Asset hub page is displayed by clicking Help icon")
	public void ASST008() throws InterruptedException {
		extentTest = extent.startTest("ASST008-Verify if the help section in the "
			+ "Asset hub page is displayed by clicking Help icon");
		SoftAssert sa7 = new SoftAssert();
		
		assetHubPage.rightclickonAssetPageTitle();
		assetHubPage.clickHelpIcon();

		sa7.assertEquals(assetHubPage.is_assetHubHelpWindow_Displayed(), true, "FAIL: TC-ASST008 -AssetHub Help"
				+ "window did not appear or wrong Help window displayed");				
		sa7.assertAll();
	}
	
		
	//ASST009 = Manual Test	
	//ASST010 = ASST006	
	
	
	//ASST011
	@Test(groups = {"Regression"}, dataProvider = "tcasst011", dataProviderClass = TestUtilities.class,
			description = "ASST011-Verify if click on the Type filter user is able to "
					+ "filter all the assets correctly by Asset Type")
	public void ASST011(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String Frequency, String FrequencyInterval, String Description) 
					throws InterruptedException {
		extentTest = extent.startTest("ASST011-Verify if click on the Type filter user is able to "
					+ "filter all the assets correctly by Asset Type");
		SoftAssert sa8 = new SoftAssert();
		
		//Asset creation method
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				Frequency, FrequencyInterval, Description);		
		UserLoginPopup(getUID("adminFull"), getPW("adminFull")); //Enter User Credentials to Save Asset
		// Click the Back button in the Asset creation/details page & click the Save message if
		// displayed in order to move to Asset Hub Page
		assetHubPage = assetCreationPage.clickBackBtn();
		
		//Check for the Asset Filter method
		boolean state = assetHubPage.assetList_TypeFilter();
		System.out.println(state);

		sa8.assertEquals(state, true, "FAIL: TC-ASST011 -Type filter is not working in the Asset Hub page");				
		sa8.assertAll();
	}

	
	//ASST012 = Not Valid	
	//ASST013 = Not Valid
	
	
	//ASST014
	@Test(groups = { "Regression" }, description = "ASST014-Verify if click on the Manufacturer filter"
			+ " user is able to filter all the assets correctly by Asset Manufacturer")
	public void ASST014()
			throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST014-Verify if click on the Manufacturer filter"
			+ " user is able to filter all the assets correctly by Asset Manufacturer");
		SoftAssert sa9 = new SoftAssert();

		// Check for the Asset Filter method
		boolean state = assetHubPage.assetList_ManufacturerFilter();
		System.out.println(state);

		sa9.assertEquals(state, true, "FAIL: TC-ASST014 -Manufacturer filter is not working in the Asset Hub page");
		sa9.assertAll();
	}
	
	
	//ASST015
	@Test(groups = { "Regression" }, description = "ASST015-Verify if click on the Location filter user"
					+ " is able to filter all the assets correctly by Asset Location")
	public void ASST015()
			throws InterruptedException, IOException {
		extentTest = extent.startTest("ASST015-Verify if click on the Location filter user" + 
				" is able to filter all the assets correctly by Asset Location");
		SoftAssert sa10 = new SoftAssert();

		// Check for the Asset Filter method
		boolean state = assetHubPage.assetList_LocationFilter();
		System.out.println(state);

		sa10.assertEquals(state, true, "FAIL: TC-ASST015 -Location filter is not working in the Asset Hub page");
		sa10.assertAll();
	}	

	
	// ASST147
	@Test(groups ={"Sanity", "Regression"}, dataProvider = "tc147", dataProviderClass = TestUtilities.class,  
			description = "Verify all the changes made to the asset are displayed correctly at Asset Hub Page")
	public void ASST147(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		extentTest = extent.startTest("ASST147-Verify all the changes made to the asset are displayed"
				+ " correctly at Asset Hub Page");
		SoftAssert sa1 = new SoftAssert();
		
		//Asset creation method
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				Frequency, FrequencyInterval, Description);		
		UserLoginPopup(getUID("adminFull"), getPW("adminFull")); //Enter User Credentials to Save Asset
		// Click the Back button in the Asset creation/details page & click the Save message if
		// displayed in order to move to Asset Hub Page
		assetHubPage = assetCreationPage.clickBackBtn();
		
		assetDetailsPage=assetHubPage.click_assetTile(Name);
		assetCreationPage=assetDetailsPage.click_assetEditBtn();		
		
		
		//Expected Asset elements (Asset Type, Asset ID, Asset Name) to be edited
		String[] expectedAssetInfo = {"Sterilizer","147b","Asset147b"};
		//System.out.println("exp asst info1:"+Arrays.toString(expectedAssetInfo));
		
		//Change/Update the Asset Name,ID & Type info
		assetCreationPage.enterAssetName(expectedAssetInfo[2]);
		assetCreationPage.enterAssetID(expectedAssetInfo[1]);
		assetCreationPage.SelectAssetType(expectedAssetInfo[0]);
		assetCreationPage.clickSaveBtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull")); //Enter User Credentials to Save Asset
		//Move back to Asset Hub Page		
		assetDetailsPage = assetCreationPage.click_BackBtn();
		assetHubPage = assetDetailsPage.ClickBackBtn();	
				
		String[] ActualAssetinfo = assetHubPage.assetTile(expectedAssetInfo[2]);
		//System.out.println("Actual asst info1:"+Arrays.toString(ActualAssetinfo));
		for (String str2 : ActualAssetinfo) {
			System.out.println("Act asst info2: "+str2);
		}

		sa1.assertEquals(ActualAssetinfo, expectedAssetInfo);;
		sa1.assertAll();
	}
	
	
	//ASST149_ASST003
	@Test(groups ={"Regression"}, dataProvider = "tc149", dataProviderClass = TestUtilities.class,  
			description = "ASST149 - Verify on the main hub page ,if the number of assets "
					+ "has increased by one, Print screen shot of the main hub")
	public void ASST149_ASST003(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String Frequency, String FrequencyInterval, String Description)
			throws InterruptedException {
		extentTest = extent.startTest("ASST149_ASST003- Verify on the main hub page ,if the number of "
				+ "assets has increased by one, Print screen shot of the main hube");
		SoftAssert sa2 = new SoftAssert();
		
		MainHubPage = assetHubPage.ClickBackBtn();
		int AssetCountInMainHubPage_BeforeNewAssetCreation = Integer.parseInt(MainHubPage.AssetCountInAssetTileOfMainHubPage());
		System.out.println("AssetCountInMainHubPage_BeforeNewAssetCreation: "+AssetCountInMainHubPage_BeforeNewAssetCreation);
		
		assetHubPage=MainHubPage.ClickAssetTile(); //Move to Asset Hub page
		//Asset creation method
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				Frequency, FrequencyInterval, Description);		
		UserLoginPopup(getUID("adminFull"), getPW("adminFull")); //Enter User Credentials to Save Asset
		// Click the Back button in the Asset creation/details page & click the Save message if
		// displayed in order to move to Asset Hub Page
		assetHubPage = assetCreationPage.clickBackBtn();

		//String AssetCountInAssetHubPage = assetHubPage.assetcount();
		//System.out.println("Asst count in AsstHubPage:" + AssetCountInAssetHubPage);
		
		MainHubPage = assetHubPage.ClickBackBtn();
		int AssetCountInMainHubPage_afterAssetCreation = Integer.parseInt(MainHubPage.AssetCountInAssetTileOfMainHubPage());
		System.out.println("AssetCountInMainHubPage_afterAssetCreation: "+AssetCountInMainHubPage_afterAssetCreation);

		//Verify if After asset creation count in the Asset tile info of Main Hub page has increased by 1
		sa2.assertEquals(AssetCountInMainHubPage_afterAssetCreation, AssetCountInMainHubPage_BeforeNewAssetCreation+1, "FAIL: TC149 -Mismatch in "
				+ "Asset count compared between Main Hub Page & Asset Hub page");
		sa2.assertAll();
	}
		
	
	
	//ASST167
	@Test(groups = {"Regression"},  description = "ASST167 - Verify if clicking on Search icon opens a search text box")
	public void ASST167() throws InterruptedException {
		extentTest = extent.startTest("ASST167- Verify if clicking on Search icon opens a search text box");
		SoftAssert sa2 = new SoftAssert();
		
		//Expected search Asset elements(Asset Type, Asset ID, Asset Name) info
		String[] expectedAssetInfo = {"HeatBath","149","Asset149"};
		//System.out.println("exp asst info"+Arrays.toString(expectedAssetInfo));

		assetHubPage.click_serachAstBtn();
		if (assetHubPage.searchAstTxtfiled_state()) {
			assetHubPage.enter_serachAsttxt("Asset149");
		}
		
		assetHubPage.click_serachAstBtn();
		assetHubPage.click_serachAstBtn();		

		//Verify if only One asset is displayed based on Search criteria
		sa2.assertEquals(assetHubPage.asset_Count(), 1, "FAIL: Serach Asset not found");
		
		String[] ActualAssetinfo = assetHubPage.assetTile("Asset149");
		//System.out.println("Actual asst info:"+Arrays.toString(ActualAssetinfo));	

		//Verify if the expected Asset info is same has the searched Asset info
		sa2.assertEquals(ActualAssetinfo, expectedAssetInfo,
				"FAIL: ASST167- Mismatch in the searched Asset & display asset");
		sa2.assertAll();
	}
	
	
	//ASST169 = ASST011
	//ASST173 = Not Valid
	//ASST174 = Not Valid
	//ASST175 = ASST014
	//ASST176 = ASST015
	

}
