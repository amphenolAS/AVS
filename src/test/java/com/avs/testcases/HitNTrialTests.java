/**
 * @author manoj.ghadei
 *
 */

package com.avs.testcases;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
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
import com.avs.pages.assetCreationPage;
import com.avs.pages.assetHubPage;
import com.avs.utility.TestUtilities;

public class HitNTrialTests extends BaseClass {
	
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	LoginPage MainLoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	assetHubPage assetHubPage;
	assetCreationPage assetCreationPage;

	/*@BeforeClass
	private void testsetup() throws IOException {
		//Rename the cache Asset file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");
		
		//Rename the Asset folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
	}*/
	
	
	//Before All the tests are conducted
	@BeforeTest
	private void setExtent() throws IOException {
		//Rename the cache Asset file (Asset.txt) if exists
		//renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");
		
		//Rename the Asset folder (Asset) if exists
		//renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html",true);
		extent.addSystemInfo("VRT Version", "1.0.0.37");
		extent.addSystemInfo("BS Version", "0.6.13");
		extent.addSystemInfo("Lgr Version", "1.2.6");
		extent.addSystemInfo("User Name", "Manoj");
		extent.addSystemInfo("TestSuiteName", "Hit&Trail");

	}
	
	
	//After All the tests are conducted
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}
	
	/*@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException {		
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		MainLoginPage = new LoginPage();
		MainHubPage = MainLoginPage.Login(getUN("adminFull"), getPW("adminFull"));
		assetHubPage = MainHubPage.ClickAssetTile();
		//assetCreationPage=assetHubPage.ClickAddAssetBtn();
	}*/

	
	/*// TearDown of the App
	@AfterMethod
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
			String screenshotPath2 = TestUtilities.getPassTCScreenshot(driver, result.getName());
			extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath2)); //to add screenshot in extent report

		}		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();
	}
*/
	
	@Test
	public void PrivTest() throws InterruptedException, ParseException {
		
		String expted = "abcd";
		String act = "efgh";
		
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(act, expted);
		sa.assertEquals(act, expted, "Fail");
		sa.assertAll();
		
//		if (!expted.equals(act)) {
//			sa.assertEquals(true, false);
//			sa.assertAll();
//		}
//		else {
//			sa.assertEquals(true, true);
//			sa.assertAll();
//		}



	}
	 

/*	@Test
	public void fetchAssetlist() {
		List<WebElement> AssetList = driver.findElementByAccessibilityId("ItemGridView")
				.findElements(By.className("GridViewItem"));
		System.out.println("Total Assets count: " + AssetList.size());

		for (int i = 0; i < AssetList.size(); i++) {
			System.out.println("Asset type : " + AssetList.get(i).getText());

			List<WebElement> AssetTileInfo = AssetList.get(i).findElements(By.className("TextBlock"));
			//System.out.println(AssetTileInfo.size());
			for (int j = 0; j < AssetTileInfo.size(); j++) {
				//System.out.println("AssetTileInfo: "+AssetTileInfo.get(j).getText());
				if (AssetTileInfo.get(j).getText().equals("7")) {
					System.out.println("AssetTileInfo (AssetName): "+AssetTileInfo.get(j).getText());
					AssetTileInfo.get(j).click();
				}
			}
		}
		
		
	}
	*/
	
	
	/*@Test
	public void retryFailTC() {
		Assert.assertEquals(true, false);		
	}*/
	
	/*@Test
	public void fetchAssetLocationfilter() throws InterruptedException {
		extentTest = extent.startTest("fetchAssetLocationfilter");
		boolean state1 = assetHubPage.assetList_LocationFilter();
		System.out.println(state1);

		
		Assert.assertEquals(state1, true);	
	
	}*/
	

		
	/*@Test (dataProvider = "tcasst014", dataProviderClass = TestUtilities.class,
			description="check the behaviour issue of the Asset creation")
	public void fetchAssettypelist(String Name, String ID, String Type, String Manufacturer, String Location, String Model,
			String Size, String SizeUnit, String Frequency, String FrequencyInterval, String Description) throws InterruptedException {
		//Asset creation method
		assetCreationPage = assetHubPage.ClickAddAssetBtn();
		assetCreationPage.assetCreationWithAllFieldEntry(Name, ID, Type, Manufacturer, Location, Model, Size, SizeUnit,
				Frequency, FrequencyInterval, Description);		
		UserLoginPopup(getUN("adminFull"), getPW("adminFull")); //Enter User Credentials to Save Asset
		// Click the Back button in the Asset creation/details page & click the Save message if
		// displayed in order to move to Asset Hub Page
		assetHubPage = assetCreationPage.clickBackBtn();	
	}*/
	


	/*
	@Test (description="Check for File renaming")
	public void renameFile() throws IOException {

		// create a new file
		String filepath ="C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData";
		
		File file = new File(filepath + "/" + "NgvUsers.uux");
		System.out.println(file.getName());
		System.out.println(file.exists());
		if (!file.exists()) {
			//file.createNewFile();
			System.out.println("No User DB File present");
		} else {
			String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
			File backupFile = new File(filepath + "/" + timestamp + "_NgvUsers.uux");
			file.renameTo(backupFile);
		}	
				
	}
	*/
	
/*	@Test (description="Check for File renaming")
	public void renameFile2() throws IOException {
		//Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		
		//Rename the cache Asset file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");
		
		//Rename the Asset folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		
		//renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
				
	}*/
	
	
/*	@Test
	public void fetchAssetUnit() {
		assetCreationPage.selectAssetSizeUnit("cu ft");		
	}*/
	
}
