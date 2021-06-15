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
import com.avs.pages.UserManagementPage;
import com.avs.utility.TestUtilities;
import com.avs.utility.userManagementUtility;

public class UserManagementTest extends BaseClass {
	public ExtentReports extent;
	public ExtentTest extentTest;

	// Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;

	@BeforeTest
	public void UserCreationSetup() throws InterruptedException, IOException {

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

		AppClose();
		Thread.sleep(1000);

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

	// ADMN120

	@Test(groups = { "Sanity",
			"Regression" }, description = "ADMN120-Verify able to navigate to User Management screen from Admin screen"
					+ "hub page , user is navigated to the User Management screen screen")
	public void ADMN120() throws InterruptedException {
		extentTest = extent.startTest("ADMN120-Verify able to navigate to User Management screen from Admin screen"
				+ "hub page , user is navigated to the User Management screen screen");
		SoftAssert sa1 = new SoftAssert();

		sa1.assertEquals(UserManagementPage.IsUMscreenDisplayed(), true,
				"FAIL: TC-ADMN120 -Incorrect UserManagementPage title or landed into incorrect Page");
		sa1.assertAll();
	}

	// Skipped : ADMN121- Verify with Fresh installation no users are created and
	// database is empty // Above test case is already Tested in LoginTest class
	// LOGIN_001

	// ADMN122

	@Test(groups = { "Sanity", "Regression" }, description = "ADMN122-Verify that new user button is enabled")
	public void ADMN122() throws Exception {
		extentTest = extent.startTest("ADMN122-Verify that new user button is enabled");
		SoftAssert sa2 = new SoftAssert();

		boolean state = UserManagementPage.IsNewUserBtnPresence();
		sa2.assertEquals(state, true, "FAIL: New User Button Not Available");

		sa2.assertAll();
	}

	// ADMN123

	@Test(groups = { "Regression" }, description = "ADMN123-Verify if select the New User button and "
			+ "check if a form to fill in the Name, User ID, Password, Confirm Password, Title, User Type, "
			+ "Phone, Email and Assigning privileges is opened up for entries.")
	public void ADMN123() throws Exception {
		extentTest = extent.startTest(
				"LADMN123-ADMN123-Verify if select the New User button and check if a form to fill in the Name, "
						+ "User ID, Password, Confirm Password, Title, User Type, Phone, Email and "
						+ "Assigning privileges is opened up for entries.");

		// Click on NewUser button

		UserManagementPage.ClickNewUser();
		SoftAssert sa3 = new SoftAssert();

		// Validate presence of UserName text field
		sa3.assertEquals(UserManagementPage.UserNameFieldPresence(), true, "FAIL: No UName field present");

		// Validate presence of UserID text field
		sa3.assertEquals(UserManagementPage.UserIDFieldPresence(), true, "FAIL: No UID field present");

		// Validate presence of password text field
		sa3.assertEquals(UserManagementPage.PassworFieldPresence(), true, "FAIL: No PWD field present");

		// Validate presence of password text field
		sa3.assertEquals(UserManagementPage.ConPassworFieldPresence(), true, "FAIL: No Confirm PWD field present");

		// Validate presence of Title text field
		sa3.assertEquals(UserManagementPage.TitleFieldPresence(), true, "FAIL: No Title field present");

		// Validate presence of User Type DropDown field
		sa3.assertEquals(UserManagementPage.UserTypeField_EnableState(), true, "FAIL: No Title field present");

		// Validate presence of Phone text field
		sa3.assertEquals(UserManagementPage.PhoneFieldPresence(), true, "FAIL: No Phone field present");

		// Validate presence of Email text field
		sa3.assertEquals(UserManagementPage.EmailFieldPresence(), true, "FAIL: No Email field present");

		// Validate presence of Email text field
		sa3.assertEquals(UserManagementPage.EmailFieldPresence(), true, "FAIL: No Email field present");

		// Validate presence of Privilege check box field
		sa3.assertEquals(UserManagementPage.PrivillagecheckboxPresence(), true, "FAIL: No Privilege field present");

	}

	// ADMN124

	@Test(dataProvider = "tcADMN124", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "ADMN124-Verify if mandatory fields are marked on the user management screen-Name, "
					+ "User ID, Password, Confirm Password, Title and User Type")

	public void ADMN124(Object... dataProvider) throws InterruptedException {
		extentTest = extent
				.startTest("ADMN124-Verify if mandatory fields are marked on the user management screen-Name, "
						+ "User ID, Password, Confirm Password, Title and User Type");

		SoftAssert sa4 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String ExpAlrtMsg = (String) dataProvider[6];

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		String ActBlankFieldAlertMsg = UserManagementPage.AlertMsg();

		sa4.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);
		sa4.assertAll();
	}

	// ADMN125

	@Test(dataProvider = "tcADMN125", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify the Valdiations  for User Name Field at User Management screen")

	public void ADMN125(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN125-Verify the User Name Field at User Management screen"
				+ " do not accept In-Valid Data parameters");

		SoftAssert sa5 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String ExpAlrtMsg = (String) dataProvider[6];

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		String ActInvalidFieldAlertMsg = UserManagementPage.AlertMsg();

		sa5.assertEquals(ActInvalidFieldAlertMsg, ExpAlrtMsg, "FAIL: Not Matched");
		sa5.assertAll();
	}

	// ADMN125a

	@Test(dataProvider = "tcADMN125a", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify the Validations  for User Name Field at User Management screen")

	public void ADMN125a(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN125a-Verify the User Name Field at User Management screen"
				+ " do not accept In-Valid Data parameters");

		SoftAssert sa6 = new SoftAssert();

		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		sa6.assertEquals(UserManagementPage.UserLoginPopupVisible(), true);
		sa6.assertAll();
	}

	// ADMN125b

	@Test(groups = {
			"Regression" }, description = "Verify Name field at User Management screen accepts maximum of 35 characters")
	public void ADMN125b() throws InterruptedException {
		extentTest = extent.startTest("ADMN125b-Verify the User Name Field at User Management screen"
				+ "Verify Name field accepts maximum of 35 characters");

		SoftAssert sa7 = new SoftAssert();
		UserManagementPage.ClickNewUser();
		String expectedtxt = "123456789A123456789A123456789012345Z"; // 36 Char input
		System.out.println("count of User Name text entered: " + expectedtxt.length());
		UserManagementPage.enterNewUserName(expectedtxt);
		String actualtextentered = UserManagementPage.GetUserNametext();
		System.out.println("count of User Name text to be entered: " + actualtextentered.length());

		sa7.assertEquals(actualtextentered.length(), 35,
				"FAIL:ADMN125b Username field accepts more than 35 characters");
		sa7.assertAll();
	}

	// ADMN129

	@Test(dataProvider = "tcADMN129", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify Validations for User ID Field at User management screen")

	public void ADMN129(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN129-Verify Validations for User ID Field at User management screen"
				+ " do not accept In-Valid Data parameters");

		SoftAssert sa8 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String ExpAlrtMsg = (String) dataProvider[6];

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		String ActInvalidUIDAlertMsg = UserManagementPage.AlertMsg();

		sa8.assertEquals(ActInvalidUIDAlertMsg, ExpAlrtMsg, "FAIL: Not Matched");
		sa8.assertAll();
	}

	// ADMN129a

	@Test(dataProvider = "tcADMN129a", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify Validations for User ID Field at User management screen")

	public void ADMN129a(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN129a-Verify Validations for User ID Field at User management screen"
				+ " accept Valid Data parameters");

		SoftAssert sa9 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];

		System.out.println(UserID);

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);

		sa9.assertEquals(UserManagementPage.UserLoginPopupVisible(), true);
		sa9.assertAll();
	}

	// ADMN129b

	@Test(groups = {
			"Regression" }, description = "Verify User ID field at User Management screen accepts maximum of 16 characters")
	public void ADMN129b() throws InterruptedException {
		extentTest = extent.startTest("ADMN129b-Verify the User Name Field at User Management screen"
				+ "Verify user id field accepts maximum of 16 characters");

		SoftAssert sa10 = new SoftAssert();
		UserManagementPage.ClickNewUser();
		String expectedvalue = "123456789A123456B"; // 16 Char input
		System.out.println("count of User ID text entered: " + expectedvalue.length());
		UserManagementPage.enterNewUserID(expectedvalue);
		String actualvalueentered = UserManagementPage.GetUserIDtext();
		System.out.println("count of User ID text to be entered: " + actualvalueentered.length());

		sa10.assertEquals(actualvalueentered.length(), 16,
				"FAIL:ADMN125b Username field accepts more than 16 characters");
		sa10.assertAll();
	}

	// ADMN133

	@Test(dataProvider = "tcADMN133", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify Validations for  unique User ID Field at User management screen")

	public void ADMN133(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN133-Verify Validations for  unique User ID Field at User management screen");

		SoftAssert sa11 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String ExpAlrtMsg = (String) dataProvider[6];

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		String ActAlertMsg = UserManagementPage.AlertMsg();

		sa11.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Not Matched");
		sa11.assertAll();
	}

	// ADMN134

	@Test(dataProvider = "tcADMN134", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify Validations for  unique User Name Field at User management screen")

	public void ADMN134(Object... dataProvider) throws InterruptedException {
		extentTest = extent
				.startTest("ADMN134-Verify Validations for  unique User Name Field at User management screen");

		SoftAssert sa12 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String ExpAlrtMsg = (String) dataProvider[6];

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		String ActAlertMsg = UserManagementPage.AlertMsg();

		sa12.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Not Matched");
		sa12.assertAll();
	}

	// ADMN135

	@Test(groups = { "Regression" }, description =
	  "Verify Validations for Password Field at User Management Screen")
	  
	  public void ADMN135() throws InterruptedException { extentTest = extent.
	  startTest("ADMN135-Verify Save button is disbale before entering value to password field"
	  ); SoftAssert sa13 = new SoftAssert();
	  
	  UserManagementPage.ClickNewUser();
	  
	  UserManagementPage.UMDisablesaveButton("usx", "45z", "666", "Sr Manager",
	  "System Administrator");
	  sa13.assertEquals(UserManagementPage.IsSaveButtonEnable(), false);
	  
	  // Verify Save button is Enable After entering value to password and 
	  //confirm password field //at User management screen
	  UserManagementPage.UMEnablesaveButton("usx", "45z", "234", "234");
	  sa13.assertEquals(UserManagementPage.IsSaveButtonEnable(), true);
	  sa13.assertAll(); }

	// ADMN140

	@Test(groups = {
			"Regression" }, description = "Verify Validations for Confirm Password Field at User Management Screen")

	public void ADMN140() throws InterruptedException {
		extentTest = extent
				.startTest("ADMN140-Verify Save button is disbale before entering value to Confirm password field");
		SoftAssert sa14 = new SoftAssert();

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMDisablesaveButton1("usx", "45z", "88", "Sr Manager", "System Administrator");
		sa14.assertEquals(UserManagementPage.IsSaveButtonEnable(), false);

		// Verify Save button is Enable After entering value to password field // at
		// User management screen

		UserManagementPage.UMEnablesaveButton("usx", "45z", "234", "234");
		sa14.assertEquals(UserManagementPage.IsSaveButtonEnable(), true);
		sa14.assertAll();
	}

	// ADMN140a

	@Test(groups = {
			"Regression" }, description = "Verify the password mismatch functionality in User Management Screen")

	public void ADMN140a() throws InterruptedException {
		extentTest = extent.startTest("ADMN140a-Verify the password mismatch functionality in User Management Screen");
		SoftAssert sa15 = new SoftAssert();

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMEnablesaveButton("AzAA", "45z", "234", "432");

		String ExpAlrtMsg = "Password and confirm password should match";
		String ActAlertMsg = UserManagementPage.AlertMsg();

		sa15.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert Message Not Matched");

		sa15.assertEquals(UserManagementPage.IsSaveButtonEnable(), false);
		sa15.assertAll();
	}

	// ADMN140c

	@Test(groups = { "Regression" }, description = "Verify the save btn when both password and"
			+ " confirm password are not entered in User Management Screen")

	public void ADMN140c() throws InterruptedException {
		extentTest = extent.startTest("ADMN140.3-Verify the save btn when both password "
				+ "and confirm password are not entered in User Management Screen");
		SoftAssert sa16 = new SoftAssert();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMDisablesaveButton2("ATestA", "55z", "Sr Manager", "System Administrator");
		sa16.assertEquals(UserManagementPage.IsSaveButtonEnable(), false);
	}

	// ADMN146

	@Test(dataProvider = "tcADMN146", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify the Valid inputs allowed for Title field validations")

	public void ADMN146(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN146-Verify the Valid inputs allowed for Title field validations");

		SoftAssert s18 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];

		System.out.println(UserID);

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);

		s18.assertEquals(UserManagementPage.UserLoginPopupVisible(), true);
		s18.assertAll();
	}

	// ADMN146a

	@Test(dataProvider = "tcADMN146", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify the Invalid inputs not allowed for Title field validations")

	public void ADMN146a(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN146a-Verify the Invalid inputs not allowed for Title field validations");

		SoftAssert sa18 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String ExpAlrtMsg = (String) dataProvider[6];
		System.out.println(UserID);

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		String ActAlertMsg = UserManagementPage.AlertMsg();

		sa18.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		sa18.assertAll();
	}

	// ADMN147a

	@Test(dataProvider = "tcADMN147a", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify the Invalid inputs for User type field validations")

	public void ADMN147a(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN147a-Verify the Invalid inputs for User type field validations");

		SoftAssert s19 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String ExpAlrtMsg = (String) dataProvider[6];

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		String ActAlertMsg = UserManagementPage.AlertMsg();
		s19.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		s19.assertAll();
	}

	// ADMN150

	/*@Test(dataProvider = "tcADMN150", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify the Valid inputs for Phone number field")

	public void ADMN150(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN150-Verify the Valid inputs for Phone number field");

		SoftAssert sa20 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String PhoneNo = (String) dataProvider[6];
		String Email = (String) dataProvider[7];

		UserManagementPage.ClickNewUser();

		UserManagementPage.UMCreation_NonmandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType,
				PhoneNo, Email);
		sa20.assertEquals(UserManagementPage.UserLoginPopupVisible(), true);
		sa20.assertAll();
	}*/

	// ADMN150a

	/*@Test(dataProvider = "tcADMN150a", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify the Valid inputs for Phone number field")

	public void ADMN150a(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN150a-Verify the inValid inputs for Phone number field");

		SoftAssert s20 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String PhoneNo = (String) dataProvider[6];
		String Email = (String) dataProvider[7];
		String ExpAlrtMsg = (String) dataProvider[8];
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_NonmandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType,
				PhoneNo, Email);
		String ActAlertMsg = UserManagementPage.AlertMsg();
		s20.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		s20.assertAll();
	} */

	// ADMN155t

	/*@Test(groups = { "Regression" }, description = "Verify that Email field is non-mandatory")

	public void ADMN155t() throws InterruptedException {
		extentTest = extent.startTest("ADMN155-Verify that Email field is non-mandatory");
		SoftAssert sa21 = new SoftAssert();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_NonmandatoryFields("tesb", "UI6", "4", "4", "titlrr", "System Administrator",
				"9023456789", "");
		sa21.assertEquals(UserManagementPage.UserLoginPopupVisible(), true);
		sa21.assertAll();
	} */

	// ADMN159

	/*@Test(dataProvider = "tcADMN159", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify Invalid formats for Email Field at User Management Screen")

	public void tcADMN159(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN159-Verify Invalid formats for Email Field at User Management Screen");

		SoftAssert s21 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String PhoneNo = (String) dataProvider[6];
		String Email = (String) dataProvider[7];
		String ExpAlrtMsg = (String) dataProvider[8];
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_NonmandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType,
				PhoneNo, Email);
		String ActAlertMsg = UserManagementPage.AlertMsg();
		s21.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		s21.assertAll();
	}*/

	// ADMN160 : Verify valid formats for Email Field at User Management Screen

	/*@Test(dataProvider = "tcADMN160", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "Verify valid formats for Email Field at User Management Screen")
	public void ADMN160(Object... dataProvider) throws InterruptedException {
		extentTest = extent.startTest("ADMN160-Verify Valid formats for  Email Field at User Management Screen");

		SoftAssert sab21 = new SoftAssert();
		String Name = (String) dataProvider[0];
		String UserID = (String) dataProvider[1];
		String Password = (String) dataProvider[2];
		String ConfirmPassword = (String) dataProvider[3];
		String Title = (String) dataProvider[4];
		String UserType = (String) dataProvider[5];
		String PhoneNo = (String) dataProvider[6];
		String Email = (String) dataProvider[7];
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_NonmandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType,
				PhoneNo, Email);
		sab21.assertEquals(UserManagementPage.UserLoginPopupVisible(), true);
		sab21.assertAll();
	}*/

	// ADMN183-Verify Valid validation for Photo field

	/*@Test(groups = { "Regression" }, description = "ADMN183-Verify Valid validation for Photo field")
	public void ADMN183() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN183-Verify Valid validation for Photo field");
		UserManagementPage.ClickNewUser();
		UserManagementPage.click_UserImageTile();
		UserManagementPage.click_UploadBrowseBtn();
		UserManagementPage.upload_UserImage("UserimageValid");
		System.out.println("Image Uploaded Successfully");
	}*/

//ADMN184-Verify Invalid validation for Photo field

	/*@Test(groups = { "Regression" }, description = "ADMN184-Verify InValid validation for Photo field")
	public void ADMN184() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN184-Verify Invalid validation for Photo field");
		UserManagementPage.ClickNewUser();
		UserManagementPage.click_UserImageTile();
		UserManagementPage.click_UploadBrowseBtn();
		UserManagementPage.upload_UserImage("UserimageInValid");
		SoftAssert sa22 = new SoftAssert();
		String ExpAlrtMsg = "Select image file with size less than 5 mb";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		sa22.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		sa22.assertAll();
	}*/

	// Verify the Disable User Account check Box validations
	// ADMN188-Verify that Disable user Account check box is default unselected for
	// any new user that is created

	/*@Test(groups = { "Regression" }, description = "ADMN188-Verify InValid validation for Photo field")
	public void ADMN188() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest(
				"ADMN188-Verify that Disable user Account check box is default unselected for any new user that is created");
		UserManagementPage.ClickNewUser();
		UserManagementPage.Select_DisableUserCheckBox();
	}*/

	// ADMN188a-Verify user is able to disable a user while creating it

	/*@Test(dataProvider = "tcADMN188a", dataProviderClass = TestUtilities.class,
	  groups = { "Regression" }, description =
	  "Verify user is able to disable a user while creating it") 
	public void ADMN188a(Object... dataProvider) throws InterruptedException { 
	extentTest =extent.startTest("ADMN188a-Verify user is able to disable a user while creating it" ); 
	  String Name = (String) dataProvider[0]; 
	  String UserID = (String)dataProvider[1]; 
	  String Password = (String) dataProvider[2];
	  //System.out.println(Password); 
	  String ConfirmPassword = (String)dataProvider[3]; //System.out.println(ConfirmPassword); 
	  String Title =(String) dataProvider[4]; 
	  String UserType = (String) dataProvider[5];
	  UserManagementPage.ClickNewUser();
	  UserManagementPage.Select_DisableUserCheckBox();
	  UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password,ConfirmPassword, Title, UserType);
	  UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
	  
	  }*/
	 
	// ADMN188b- Verify Application should not allow to login with disable User id

	/*@Test(groups = {
			"Regression" }, description = "ADMN188b-Verify Application should not allow to login with disable User id")
	public void ADMN188b() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN188b-Verify Application should not allow to login with disable User id ");
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		LoginPage.DisableUserLogin();
		SoftAssert sa23 = new SoftAssert();
		String ExpAlrtMsg = "User account has been disabled,please contact administrator";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		sa23.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		sa23.assertAll();
	}*/

//	ADMN188c-Verify if a user can disable his own account

	/*@Test(groups = { "Regression" }, description = "ADMN188c-Verify if a user can disable his own account")
	public void ADMN188c() throws InterruptedException {
		extentTest = extent.startTest("ADMN188c-Verify if a user can disable his own account");
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("User1");
		UserManagementPage.Select_DisableUserCheckBox();
		UserManagementPage.ClickNewUserSaveButton();
		SoftAssert sa24 = new SoftAssert();
		sa24.assertEquals(UserManagementPage.DisableAlertMsgVisible(), true);
		sa24.assertAll();

	}*/

	// ADMN194-Verify User Creation for an Administrator User

	/*@Test(groups = { "Regression" }, description = "ADMN194-Verify User Creation for an Administrator User")
	public void ADMN194() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("Verify User Creation for an Administrator User");
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("AdminTest1", "202", "2", "2", "Admin", "System Administrator");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
	}*/

	// ADMN196-Verify Reset pwd functionality for first Admin user

	/*@Test(groups = { "Regression" }, description = "ADMN194-Verify User Creation for an Administrator User")
	public void ADMN196() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("Verify User Creation for an Administrator User");
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		LoginPage.FirstTest_AdminLogin();
		MainHubPage = LoginPage.EnterNewPWtext("6");
	}*/
	
// ADMN196a-Verify user should not be able to login with the previous password

	/*@Test(groups = { "Regression" }, description = "ADMN194-Verify User Creation for an Administrator User")

	public void ADMN196a() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("Verify User Creation for an Administrator User");
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		LoginPage.FirstTest_AdminLogin();
	}*/

//ADMN196b-Verify After resetting the password user should be able to login with the new password

	/*@Test(groups = { "Regression" }, description = "ADMN194b-Verify After resetting the password "
			+ "user is able to login with the new password")
	public void ADMN196b() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("Verify After resetting the password user is able to login with the new password");
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		LoginPage.AfterReset_AdminLogin();
	}*/

	// ADMN198-Verify the default privileges for Administrator

	/*@Test(groups = { "Regression" }, description = "Verify the default privileges for Administrator")
	public void ADMN198() throws Exception {
		extentTest = extent.startTest("ADMN198-Verify the default privileges for Administrator");

		// Click on NewUser button
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMPrivilages("PrevTest", "66", "9", "9", "Admin", "System Administrator");
		SoftAssert sa26 = new SoftAssert();

		// Validate check boxes are checked
		sa26.assertEquals(UserManagementPage.Adminstatus(), true, "FAIL: Not Checked");
		sa26.assertEquals(UserManagementPage.CreateAndEditEquipmentstatus(), true, "FAIL: CheckBox Not Checked");
		sa26.assertEquals(UserManagementPage.CreateReportsstatus(), true, "FAIL: CheckBox Not Checked");
		sa26.assertEquals(UserManagementPage.CreatePassFailtemplatestatus(), true, "FAIL: CheckBox Not Checked");
		sa26.assertEquals(UserManagementPage.Audittrailstatus(), true, "FAIL: CheckBox Not Checked");
		sa26.assertEquals(UserManagementPage.DeleteAssetsstatus(), true, "FAIL: Not Checked");
		sa26.assertEquals(UserManagementPage.DeleteSetupstatus(), true, "FAIL: CheckBox Not Checked");
		sa26.assertEquals(UserManagementPage.DeleteEquipmentstatus(), true, "FAIL: CheckBox Not Checked");
		sa26.assertEquals(UserManagementPage.DeleteStudyFilesReportsstatus(), true, "FAIL: CheckBox Not Checked");
		sa26.assertEquals(UserManagementPage.EditPassFailtemplatestatus(), true, "FAIL: CheckBox Not Checked");
		sa26.assertEquals(UserManagementPage.CopyFilesReportsstatus(), true, "FAIL: Not Checked");
		sa26.assertEquals(UserManagementPage.Archivedatastatus(), true, "FAIL: CheckBox Not Checked");
		sa26.assertEquals(UserManagementPage.CameraAccessstatus(), true, "FAIL: CheckBox Not Checked");
		sa26.assertEquals(UserManagementPage.ManualSyncstatus(), true, "FAIL: CheckBox Not Checked");
		sa26.assertEquals(UserManagementPage.Deletepassfailtemplatestatus(), true, "FAIL: CheckBox Not Checked");
	}*/

// ADMN198a-Verify the non- default privileges  for Administrator User

	/*@Test(groups = { "Regression" }, description = "Verify the other parameters are unchecked for Administrator")
	public void ADMN198a() throws Exception {
		extentTest = extent.startTest("ADMN198a-Verify the other parameters are unchecked for Administrator");

		// Click on NewUser button
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMPrivilages("PrevTest", "66", "9", "9", "Admin", "System Administrator");
		SoftAssert s26 = new SoftAssert();
		s26.assertEquals(UserManagementPage.CreaeteEditAssetPrivstatus(), false, "FAIL: CheckBox Checked");
		s26.assertEquals(UserManagementPage.RunQualificationstatus(), false, "FAIL: CheckBox Checked");
		s26.assertEquals(UserManagementPage.RunCalibrationstatus(), false, "FAIL: CheckBox Checked");
	}*/

	// ADMN243_Verify a confirmation pop-up should be displayed with Yes and No
	// buttons when user click on delete button

	/*@Test(groups = { "Regression" }, description = "ADMN243_Verify a confirmation pop-up should be displayed")
	public void ADMN243() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN243_Verify a confirmation pop-up should be displayed");
		SoftAssert s28 = new SoftAssert();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("Test1a", "200", "2", "2", "Admin", "System Administrator");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		//UserManagementPage.Click_CreatedUsernameBtn();
		UserManagementPage.clickAnyUserinUserList("Test1a");
		UserManagementPage.ClickDeletebtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		s28.assertEquals(UserManagementPage.confirmationPopupvisible(), true);
		s28.assertAll();
	}*/

	// ADMN243a-Verify user is able to delete the selected account
	/*@Test(groups = { "Regression" }, description = "Verify user is able to delete the selected account")
	public void ADMN243a() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN243a_Verify user is able to delete the selected account");
		SoftAssert s29 = new SoftAssert();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("ORNA4", "5n5", "2", "2", "Admin", "System Administrator");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("ORNA4");
		UserManagementPage.ClickDeletebtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage.Delete_ClickYesBtn();
		String actrslt = UserManagementPage.AlertMsg();
		String rplc = actrslt.replace("\"", "").replace(".", "").replace(":", "");
		String[] actwords = rplc.split("\\s");
		for (String w : actwords) {
			System.out.println(w);
		}

		// String exptrslt = "User ORN deleted"; String[] expwords =
		// exptrslt.split("\\s"); if (Arrays.deepEquals(actwords, expwords))
		// System.out.println("Same"); else System.out.println("Not same");

		System.out.println(actwords[0]);

		// s29.assertEquals(Arrays.equals(expwords, actwords), true,"Result is Not
		// matching");
		// System.out.println(Arrays.equals(actwords,expwords));
		s29.assertEquals(actwords[0], "Userffg", "Result is Not matching");
		s29.assertEquals("User", "Userffg", "Result is Not matching");
		s29.assertEquals(actwords[1], "ORNA3", "Result is Not matching");
		s29.assertEquals(actwords[2], "deleted", "Result is Not matching");
	}*/
	// 'ADMN243b-The pop-up should get dismissed and user should remain on the same
	// screen when user click on NO button from the confirmation pop-up

	/*@Test(groups = {
			"Regression" }, description = "Verify user is able to should remain on the same screen when user click on NO button from the confirmation pop-up ")
	public void ADMN243b() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN243b_The pop-up should get dismissed and user should remain on "
				+ "the same screen when user click on NO button from the confirmation pop-up ");
		SoftAssert sa29 = new SoftAssert();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("Mango", "390", "2", "2", "Admin", "System Administrator");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Mango");
		UserManagementPage.ClickDeletebtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage.Delete_ClickNoBtn();
		sa29.assertEquals(UserManagementPage.IsUMscreenDisplayed(), true);
		sa29.assertAll();
	}*/
	// ADMN243c-Verify the deleted user should not be able to login to the
	// application
	// (Verify the alert message)

	/*@Test(groups = {
			"Regression" }, description = "Verify the deleted user should not be able to login to the application")
	public void ADMN243c() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent
				.startTest("ADMN243c_Verify the deleted user should not be able to login to the application");
		SoftAssert s29 = new SoftAssert();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("Cor38", "38am", "3", "3", "Admin", "System Administrator");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("Cor38");
		UserManagementPage.ClickDeletebtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage.Delete_ClickYesBtn();
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		LoginPage.DeletedUserLogin();
		String ExpAlrtMsg = "Invalid Credential, Please try again.";
		String ActAlertMsg = LoginPage.Deleteduser_AlertMsg();
		s29.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		s29.assertAll();
	}*/

	// ADMN243d-Verify a validation message should be displayed when user try to
	// create a new user id with the already deleted user id details

	/*@Test(groups = {
			"Regression" }, description = "Verify a validation message should be displayed when user try to create a new user id with the already deleted user id details ")
	public void ADMN243d() throws InterruptedException, ParseException, IOException, AWTException {
		extentTest = extent.startTest("ADMN243d_Verify a validation message should be displayed when "
				+ "user try to create a new user id with the already deleted user id details");
		SoftAssert s30 = new SoftAssert();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("BM2", "2am", "3", "3", "Admin", "System Administrator");
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.clickAnyUserinUserList("BM2");
		UserManagementPage.ClickDeletebtn();
		UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage.Delete_ClickYesBtn();
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		LoginPage = new LoginPage();
		MainHubPage = LoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		UserManagementPage = MainHubPage.ClickAdminTile_UMpage();
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields("BM2", "2am", "3", "3", "Admin", "System Administrator");
		String ExpAlrtMsg = "UserId already in use and in deleted list, Please try different UserID";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		s30.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
		s30.assertAll();
	}*/
	@Test(groups = { "Regression" }, description ="Verify User Creation for Supervisor User"
			  ) public void ADMN201() throws InterruptedException, ParseException,
			  IOException, AWTException { extentTest = extent.
			  startTest("ADMN201-Verify User Creation for Supervisor User"); 
			  UserManagementPage.ClickNewUser();
			  UserManagementPage.UMPrivilages("Schck","s1","3Check@3","3Check@3", "Srviser","Supervisor");
			  SoftAssert s31 = new SoftAssert();
			// Validate check boxes are checked
				s31.assertEquals(UserManagementPage.CreaeteEditAssetPrivstatus(), true, "FAIL: Not Checked");
				s31.assertEquals(UserManagementPage.CreaeteEditSetupstatus(), true, "FAIL: Not Checked");
				s31.assertEquals(UserManagementPage.CreateReportsstatus(), true, "FAIL: Not Checked");
				s31.assertEquals(UserManagementPage.Audittrailstatus(), true, "FAIL: Not Checked");
				s31.assertEquals(UserManagementPage.RunQualificationstatus(), true, "FAIL: Not Checked");
				s31.assertEquals(UserManagementPage.RunCalibrationstatus(), true, "FAIL: Not Checked");
				s31.assertEquals(UserManagementPage.ManualSyncstatus(), true, "FAIL: Not Checked");
				s31.assertEquals(UserManagementPage.CameraAccessstatus(), true, "FAIL: Not Checked");
	}
	//ADMN202_1.0-Verify the non- default privileges  for Supervisor User
	@Test(groups = { "Regression" }, description = "Verify the other parameters are unchecked for Administrator")
	public void ADMN198a() throws Exception {
		extentTest = extent.startTest("ADMN198a-Verify the other parameters are unchecked for Administrator");

		// Click on NewUser button
		UserManagementPage.ClickNewUser();
		UserManagementPage.UMPrivilages("Schck", "s1", "3Check@3", "3Check@3", "Srviser", "Supervisor");
		SoftAssert s32 = new SoftAssert();
		// Validate check boxes are not checked
		s32.assertEquals(UserManagementPage.Adminstatus(), false, "FAIL: Checked");
		s32.assertEquals(UserManagementPage.CreateAndEditEquipmentstatus(), false, "FAIL: CheckBox Checked");
		s32.assertEquals(UserManagementPage.CreatePassFailtemplatestatus(), false, "FAIL: CheckBox Not Checked");
		s32.assertEquals(UserManagementPage.DeleteAssetsstatus(), false, "FAIL: Checked");
		s32.assertEquals(UserManagementPage.DeleteSetupstatus(), false,  "FAIL: CheckBox  Checked");
		s32.assertEquals(UserManagementPage.DeleteEquipmentstatus(), false, "FAIL: CheckBox Checked");
		s32.assertEquals(UserManagementPage.DeleteStudyFilesReportsstatus(), false, "FAIL: CheckBox Checked");
		s32.assertEquals(UserManagementPage.EditPassFailtemplatestatus(), false, "FAIL: CheckBox Checked");
		s32.assertEquals(UserManagementPage.CopyFilesReportsstatus(), false, "FAIL: Checked");
		s32.assertEquals(UserManagementPage.Archivedatastatus(), false, "FAIL: CheckBox Checked");
		s32.assertEquals(UserManagementPage.Deletepassfailtemplatestatus(), false,  "FAIL: CheckBox Checked");
		
	}
	
	// ADMN201-Verify User Creation for Supervisor User
	/*@Test(groups = { "Regression" }, description ="Verify User Creation for Supervisor User"
			  ) public void ADMN201() throws InterruptedException, ParseException,
			  IOException, AWTException { extentTest = extent.
			  startTest("ADMN201-Verify User Creation for Supervisor User");
			  UserManagementPage.ClickNewUser();
			  UserManagementPage.UMCreation_MandatoryFields("Sup1", getUID("SysSupervisor"), "3Welcome3@AM", "3Welcome3@AM","Supervisor", "Supervisor");  
			  UserLoginPopup(getUID("adminFull"),getPW("adminFull")); 
			  MainHubPage = UserManagementPage.ClickBackButn();
			  LoginPage = MainHubPage.UserSignOut();
			  Thread.sleep(1000);
			  LoginPage.EnterUserID("3");
			  LoginPage.EnterUserPW("3Welcome3@AM");
			  LoginPage.ClickLoginBtn();
			  MainHubPage = LoginPage.EnterNewPWtext("Welcome3@AM");
			  LoginPage = MainHubPage.UserSignOut();
	}*/
	
}