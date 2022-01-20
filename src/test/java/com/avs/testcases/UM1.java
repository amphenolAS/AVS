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
import com.avs.pages.AuditPage;
import com.avs.pages.UserManagementPage;
import com.avs.utility.TestUtilities;
import com.avs.utility.userManagementUtility;

public class UM1 extends BaseClass {
	public ExtentReports extent;
	public ExtentTest extentTest;

	// Initialization of the Pages
	LoginPage LoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	AuditPage AuditPage;

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

	// UAM001

	@Test(groups = { "Sanity",
			"Regression" }, description = "Navigate to the User Management tab and Verify that, as it’s a fresh installation, no users are created and the database is empty")
	public void UAM001() throws InterruptedException {
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

	// UAM002_Confirm that ‘New user’ button is enabled

	@Test(groups = { "Sanity", "Regression" }, description = "UAM002_Confirm that ‘New user’ button is enabled")
	public void UAM002() throws Exception {
		extentTest = extent.startTest("UAM002_Confirm that ‘New user’ button is enabled");
		SoftAssert sa2 = new SoftAssert();

		boolean state = UserManagementPage.IsNewUserBtnPresence();
		sa2.assertEquals(state, true, "FAIL: New User Button Not Available");

		sa2.assertAll();
	}

	// UAM003_Select the ‘New User’ button and check if a form to fill in the Name,
	// User ID, Password, ConfirmPassword, Title, User Type, Phone, Email and
	// Assigning privileges is opened up for entries.

	@Test(groups = {
			"Regression" }, description = "UAM003_Select the ‘New User’ button and check if a form to fill in the Name, User ID, Password, ConfirmPassword, Title, User Type, Phone, Email and Assigning privileges is opened up for entries.")
	public void ADMN123() throws Exception {
		extentTest = extent.startTest(
				"UAM003_Select the ‘New User’ button and check if a form to fill in the Name, User ID, Password, ConfirmPassword, Title, User Type, Phone, Email and Assigning privileges is opened up for entries.");

		// Click on NewUser button

		UserManagementPage.ClickNewUser();
		SoftAssert sa = new SoftAssert();

		// Validate presence of UserName text field
		sa.assertEquals(UserManagementPage.UserNameFieldPresence(), true, "FAIL: No UName field present");

		// Validate presence of UserID text field
		sa.assertEquals(UserManagementPage.UserIDFieldPresence(), true, "FAIL: No UID field present");

		// Validate presence of password text field
		sa.assertEquals(UserManagementPage.PassworFieldPresence(), true, "FAIL: No PWD field present");

		// Validate presence of password text field
		sa.assertEquals(UserManagementPage.ConPassworFieldPresence(), true, "FAIL: No Confirm PWD field present");

		// Validate presence of Title text field
		sa.assertEquals(UserManagementPage.TitleFieldPresence(), true, "FAIL: No Title field present");

		// Validate presence of User Type DropDown field
		sa.assertEquals(UserManagementPage.UserTypeField_EnableState(), true, "FAIL: No Title field present");

		// Validate presence of Phone text field
		sa.assertEquals(UserManagementPage.PhoneFieldPresence(), true, "FAIL: No Phone field present");

		// Validate presence of Email text field
		sa.assertEquals(UserManagementPage.EmailFieldPresence(), true, "FAIL: No Email field present");

		// Validate presence of Email text field
		sa.assertEquals(UserManagementPage.EmailFieldPresence(), true, "FAIL: No Email field present");

		// Validate presence of Privilege check box field
		sa.assertEquals(UserManagementPage.PrivillagecheckboxPresence(), true, "FAIL: No Privilege field present");

	}

	// UAM004_Check if mandatory fields are marked on the user management screen
	// Name User ID PasswordConfirm Password Title and User Type.

	@Test(dataProvider = "UAM004", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "UAM004_Check if mandatory fields are marked on the user management screen  Name  User ID  PasswordConfirm Password Title and User Type.")

	public void UAM004(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType, String ExpAlrtMsg) throws InterruptedException {
		extentTest = extent.startTest(
				"UAM004_Check if mandatory fields are marked on the user management screen  Name  User ID  PasswordConfirm Password Title and User Type.");

		SoftAssert sa = new SoftAssert();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);
		String ActBlankFieldAlertMsg = UserManagementPage.AlertMsg();

		sa.assertEquals(ActBlankFieldAlertMsg, ExpAlrtMsg);
		sa.assertAll();
	}

	// UAM005

	
	// UAM006

	@Test(dataProvider = "UAM006", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "UAM006_Verify if Password and Confirm Password entry does not match then the message should display as  Password andConfirm Password should match and the Save button is disabled.")

	public void UAM006() throws InterruptedException {
		extentTest = extent.startTest(
				"UAM006_Verify if Password and Confirm Password entry does not match then the message should display as  Password andConfirm Password should match and the Save button is disabled.");

		SoftAssert sa = new SoftAssert();

		UserManagementPage.ClickNewUser();

		UserManagementPage.enterNewUserName("AzAA");
		UserManagementPage.enterNewUserID("45z");
		UserManagementPage.enterNewUserPW(getPW("adminFull"));
		UserManagementPage.enterNewUserConfPW("Welcome2@AM");
		UserManagementPage.ClickPhone();
		String ExpAlrtMsg = "Password and confirm password should match";
		String ActAlertMsg = UserManagementPage.AlertMsg();
		sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert Message Not Matched");
		UserManagementPage.enterNewUserTitle("TestManager");
		UserManagementPage.select_UserType("System Administrator");

		sa.assertEquals(UserManagementPage.IsSaveButtonEnable(), false);
		sa.assertAll();
	}

	// UAM007

	@Test(groups = {
			"Regression" }, description = "UAM007-Check if the Title and User type boxes has the text ‘Select’ as the default option")
	public void UAM007() throws InterruptedException, AWTException, IOException {
		extentTest = extent
				.startTest("UAM007-Check if the Title and User type boxes has the text ‘Select’ as the default option");

		SoftAssert sa = new SoftAssert();

		UserManagementPage.ClickNewUser();

		sa.assertEquals(UserManagementPage.FetchTxt_UserType(), "Select",
				"FAIL: Select is not the default option for User type drop down box");

		sa.assertAll();
	}

	// UAM008 - Check if the user is restricted from entering more than 50
	// characters in the Title field.

	@Test(groups = {
			"Regression" }, description = "UAM008 - Check if the user is restricted from entering more than 50 characters in the Title field.")
	public void UAM008() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest(
				"UAM008 - Check if the user is restricted from entering more than 50 characters in the Title field.");

		SoftAssert sa = new SoftAssert();

		UserManagementPage.ClickNewUser();

		String expectedvalue = "12345678901234567890123456789012345678901234567890b"; // 51 Char input
		UserManagementPage.enterNewUserTitle(expectedvalue);
		String actualvalueentered = UserManagementPage.get_UserTitle();

		sa.assertEquals(actualvalueentered.length(), 50, "FAIL: Title field accepts more than 50 characters");
		sa.assertAll();

	}

	// UAM009 - Check if the Title field accepts upper case, lower case and special
	// character like hyphen, underscore and space only.

	@Test(dataProvider = "UAM009", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "UAM009 - Check if the Title field accepts upper case, lower case and special character like hyphen, underscore and space only.")

	public void UAM009(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws InterruptedException {
		extentTest = extent.startTest(
				"UAM009 - Check if the Title field accepts upper case, lower case and special character like hyphen, underscore and space only.");

		SoftAssert sa = new SoftAssert();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);

		sa.assertEquals(UserManagementPage.UserLoginPopupVisible(), true, "FAIL: Invalid Tile  has entered");
		sa.assertAll();
	}

	// UAM009A - Check if the Title field display alert message for Invalid data.

	@Test(dataProvider = "UAM009A", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "UAM009A - Check if the Title field display alert message for Invalid data.")

	public void UAM009A(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws InterruptedException {
		extentTest = extent.startTest("UAM009A - Check if the Title field display alert message for Invalid data.");

		SoftAssert sa = new SoftAssert();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);

		sa.assertEquals(UserManagementPage.AlertMsg(),
				"Title accepts only upper case , lower case and special character like hyphen, underscore and blank",
				"FAIL: Alert message for invalid data enrty to title field has not displayed");
		sa.assertAll();
	}

	// UAM009B - Check if the Title field accepts upper case, lower case and special
	// character like hyphen, underscore and space only.

	@Test(dataProvider = "UAM009B", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "UAM009B - Check if the Title field accepts upper case, lower case and special character like hyphen, underscore and space only.")

	public void UAM009B(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws InterruptedException {
		extentTest = extent.startTest(
				"UAM009B - Check if the Title field accepts upper case, lower case and special character like hyphen, underscore and space only.");

		SoftAssert sa = new SoftAssert();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);

		sa.assertEquals(UserManagementPage.UserLoginPopupVisible(), true, "FAIL: Invalid User Type  has entered");
		sa.assertAll();
	}

	// UAM009C - Check if the User Type field display alert message for not
	// selecting any value

	@Test(dataProvider = "UAM009C", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "UAM009C - Check if the User Type field display alert message for Invalid data.")

	public void UAM009C(String Name, String UserID, String Password, String ConfirmPassword, String Title,
			String UserType) throws InterruptedException {
		extentTest = extent.startTest("UAM009C - Check if the User Type field display alert message for Invalid data.");

		SoftAssert sa = new SoftAssert();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_MandatoryFields(Name, UserID, Password, ConfirmPassword, Title, UserType);

		sa.assertEquals(UserManagementPage.AlertMsg(), "Please select valid user type",
				"FAIL: alaert message is not available");
		sa.assertAll();
	}

	// UAM010 - Create few Titles and Check if the designations are listed out in
	// the alphabetical order.

	// UAM011 - Check if the phone number accepts up to 20 characters.

	@Test(groups = { "Regression" }, description = "UAM011 - Check if the phone number accepts up to 20 characters.")
	public void UAM011() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest("UAM011 - Check if the phone number accepts up to 20 characters.");

		SoftAssert sa = new SoftAssert();

		UserManagementPage.ClickNewUser();

		String expectedvalue = "123456789012345678901234"; // 24 Char input
		UserManagementPage.enterNewUserPhone(expectedvalue);
		String actualvalueentered = UserManagementPage.get_PhoneUMFieldText();

		sa.assertEquals(actualvalueentered.length(), 20, "FAIL:phone number field accepts more than 20 characters");
		sa.assertAll();

	}

	// UAM012 - Perform the “Phone” field validation for the contact numbers as
	// mentioned below and verify the results.

	@Test(dataProvider = "UAM012", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "UAM012 - Perform the “Phone” field validation for the contact numbers as mentioned below and verify the results.")

	public void UAM012(String UName, String UID, String Pwd, String Cpwd, String Titl, String Utype, String phno,
			String Emil) throws InterruptedException {
		extentTest = extent.startTest(
				"UAM012 - Perform the “Phone” field validation for the contact numbers as mentioned below and verify the results.");

		SoftAssert sa = new SoftAssert();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_NonmandatoryFields(UName, UID, Pwd, Cpwd, Titl, Utype, phno, Emil);

		sa.assertEquals(UserManagementPage.UserLoginPopupVisible(), true, "FAIL: Invalid phone number has entered");
		sa.assertAll();
	}
	
// UAM012A - Perform the “Phone” field validation for the Invalid values.

	@Test(dataProvider = "UAM012A", dataProviderClass = userManagementUtility.class, groups = {
			"Regression" }, description = "UAM012A - Perform the “Phone” field validation for the contact numbers as mentioned below and verify the results.")

	public void UAM012A(String UName, String UID, String Pwd, String Cpwd, String Titl, String Utype, String phno,
			String Emil) throws InterruptedException {
		extentTest = extent.startTest(
				"UAM012 - Perform the “Phone” field validation for the contact numbers as mentioned below and verify the results.");

		SoftAssert sa = new SoftAssert();

		UserManagementPage.ClickNewUser();
		UserManagementPage.UMCreation_NonmandatoryFields(UName, UID, Pwd, Cpwd, Titl, Utype, phno, Emil);

		sa.assertEquals(UserManagementPage.AlertMsg(), "Phone accepts only numbers",
				"FAIL: Phone field accepted charecters with numbers");
		sa.assertAll();
	}
	
	//UAM013 - Check if the email field accepts up to 255 characters
	
	@Test(groups = { "Regression" }, description = "UAM013 - Check if the email field accepts up to 255 characters")
	public void UAM013() throws InterruptedException, AWTException, IOException {
		extentTest = extent.startTest("UAM013 - Check if the email field accepts up to 255 characters");

		SoftAssert sa = new SoftAssert();

		UserManagementPage.ClickNewUser();

		String expectedvalue = "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvb"; // 300 Char input
		UserManagementPage.enterNewUserEmail(expectedvalue);
		String actualvalueentered = UserManagementPage.get_EmailUMFieldText();

		sa.assertEquals(actualvalueentered.length(), 255, "FAIL: Email field accepts more than 255 characters");
		sa.assertAll();
	}
	
	//UAM014 - verify the valid email format and check if the application accepts the email field without any errors	
		
		@Test(dataProvider = "UAM014", dataProviderClass = userManagementUtility.class, groups = { "Regression" }, description = "UAM014 - verify the valid email format and check if the application accepts the email field without any errors")
		

			public void UAM014(String UName, String UID, String Pwd, String Cpwd, String Titl, String Utype, String phno,
					String Emil) throws InterruptedException {
				extentTest = extent.startTest("UAM014 - verify the valid email format and check if the application accepts the email field without any errors");

				SoftAssert sa = new SoftAssert();	
				UserManagementPage.ClickNewUser();
				UserManagementPage.UMCreation_NonmandatoryFields(UName, UID, Pwd, Cpwd, Titl, Utype, phno, Emil);
				sa.assertEquals(UserManagementPage.UserLoginPopupVisible(), true, "FAIL: Invalid Email ID has entered");
				sa.assertAll();
}
		
		//UAM015 - verify the Invalid email format and check if the application accepts the email field without any errors	
		
				@Test(dataProvider = "UAM015", dataProviderClass = userManagementUtility.class, groups = { "Regression" }, description = "UAM014 - verify the Invalid email format and check if the application accepts the email field without any errors")
				

					public void UAM015(String UName, String UID, String Pwd, String Cpwd, String Titl, String Utype, String phno,
							String Emil) throws InterruptedException {
						extentTest = extent.startTest("UAM014 - verify the Invalid email format and check if the application accepts the email field without any errors");

						SoftAssert sa = new SoftAssert();	
						UserManagementPage.ClickNewUser();
						UserManagementPage.UMCreation_NonmandatoryFields(UName, UID, Pwd, Cpwd, Titl, Utype, phno, Emil);
						
						sa.assertEquals(UserManagementPage.AlertMsg(), "Please enter a valid Email ID",
								"FAIL: Email field accepting invalid data");
						sa.assertAll();
		}	

		// UAM016 - Check if the User Type drop-down displays the default option as Select
				
		@Test(groups = {
				"Regression" }, description = "UAM016 - Check if the User Type drop-down displays the default option as Select")
		public void UAM016() throws InterruptedException, AWTException, IOException {
			extentTest = extent.startTest(
					"UAM016 - Check if the User Type drop-down displays the default option as Select");

			SoftAssert sa = new SoftAssert();

			UserManagementPage.ClickNewUser();

			sa.assertEquals(UserManagementPage.FetchTxt_UserType(), "Select",
					"FAIL: Select is not the default option for User type drop down box");

			sa.assertAll();
		}

		// UAM017-Check if the User Type drop-down has the following predefined user
		// types: System Administrator, Supervisor and Operator

		@Test(groups = {
				"Regression" }, description = "UAM017-Check if the User Type drop-down has the following predefined user types: System Administrator, Supervisor and Operator")

		public void UAM005() throws InterruptedException {
			extentTest = extent.startTest(
					"UAM017-Check if the User Type drop-down has the following predefined user types: System Administrator, Supervisor and Operator");

			SoftAssert sa = new SoftAssert();

			UserManagementPage.ClickNewUser();

			sa.assertEquals(UserManagementPage.FetchTxtfrom_UserType_dd(1), "System Administrator",
					"FAIL: System Administrator user type is not available indropdown");
			sa.assertEquals(UserManagementPage.FetchTxtfrom_UserType_dd(2), "Supervisor",
					"FAIL: System Administrator user type is not available indropdown");
			sa.assertEquals(UserManagementPage.FetchTxtfrom_UserType_dd(3), "Operator",
					"FAIL: System Administrator user type is not available indropdown");

			sa.assertAll();
		}
		
 //UAM018 - Check if a photo (.jpeg/.bmp/ of size less than 5 MB) can be uploaded for newly created user 
	
		@Test(groups = { "Regression" }, description = "UAM018 - Check if a photo (.jpeg/.bmp/ of size less than 5 MB) can be uploaded for newly created user ")
		public void ADMN184() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("UAM018 - Check if a photo (.jpeg/.bmp/ of size less than 5 MB) can be uploaded for newly created user ");
			SoftAssert sa = new SoftAssert();
			
			UserManagementPage.ClickNewUser();
			UserManagementPage.click_UserImageTile();
			UserManagementPage.click_UploadBrowseBtn();
			UserManagementPage.upload_UserImage("UserimageInValid");	
			String ExpAlrtMsg = "Select image file with size less than 5 mb";
			String ActAlertMsg = UserManagementPage.AlertMsg();
			sa.assertEquals(ActAlertMsg, ExpAlrtMsg, "FAIL: Alert message Not Matched");
			sa.assertAll();
         }
		
		//UAM019 - Check if Disable User Account check box is unchecked by default for a fresh user creation page
		
		@Test(groups = { "Regression" }, description = "UAM019 - Check if Disable User Account check box is unchecked by default for a fresh user creation page")
		public void UAM019() throws InterruptedException {
			extentTest = extent.startTest("UAM019 - Check if Disable User Account check box is unchecked by default for a fresh user creation page");
			
			SoftAssert sa = new SoftAssert();
			UserManagementPage.ClickNewUser();
			sa.assertEquals(UserManagementPage.DisableUserCheckBox_state(), false,
					"FAIL: Check Box should be in disable state For new user");
			sa.assertAll();
		
		}
		
	//UAM020 - Disable some of the user’s accounts and check if only the disabled users have the Disable User Account check box selected.

		@Test(groups = {"Regression" }, description = "/UAM020 - Disable some of the user’s accounts and check if only the disabled users have the Disable User Account check box selected")

		public void UAM020() throws InterruptedException {
			extentTest = extent.startTest("UAM020 - Disable some of the user’s accounts and check if only the disabled users have the Disable User Account check box selected");

			SoftAssert sa = new SoftAssert();
			
			// Create a User & disable it
			UserManagementPage.UMCreation_MandatoryFields("dsbl1", "1D", "1234569", "1234569","Admin","System Administrator");
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			UserManagementPage.clickAnyUserinUserList("dsbl1");
			UserManagementPage.Select_DisableUserCheckBox();
			UserManagementPage.ClickNewUserSaveButton();
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			UserManagementPage.clickAnyUserinUserList("dsbl1");
			sa.assertEquals(UserManagementPage.DisableUserCheckBox_state(), true,
					"FAIL: Check Box should be in disable state For new user");
			sa.assertAll();
		
		
		}
		
		//UAM021 -Check if the following Preferences options are present with a check box such that the user can check or uncheck
		
		//UAM021A - Verify the default privileges for Administrator
		@Test(groups = { "Regression" }, description = "Verify the default privileges for Administrator", alwaysRun = true)
		public void UAM021A() throws Exception {
			extentTest = extent.startTest("ADMN046-Verify the default privileges for Administrator");
			SoftAssert sa = new SoftAssert();
			UserManagementPage.ClickNewUser();
			UserManagementPage.select_UserType("System Administrator");
		
			
			sa.assertEquals(UserManagementPage.Adminstatus(), true, "FAIL:Adminstatus Not Checked");
			sa.assertEquals(UserManagementPage.CreateAndEditEquipmentstatus(), true,
					"FAIL: CreateAndEditEquipmentstatus CheckBox Not Checked");
			sa.assertEquals(UserManagementPage.CreateReportsstatus(), true,
					"FAIL: CreateReportsstatus CheckBox Not Checked");

			sa.assertEquals(UserManagementPage.DeleteAssetsstatus(), true, "FAIL: DeleteAssetsstatus Not Checked");
			sa.assertEquals(UserManagementPage.DeleteSetupstatus(), true, "FAIL: DeleteSetupstatus CheckBox Not Checked");
			sa.assertEquals(UserManagementPage.DeleteEquipmentstatus(), true,
					"FAIL:DeleteEquipmentstatus CheckBox Not Checked");
			sa.assertEquals(UserManagementPage.DeleteStudyFilesReportsstatus(), true,
					"FAIL: DeleteStudyFilesReportsstatus CheckBox Not Checked");

			sa.assertEquals(UserManagementPage.CopyFilesReportsstatus(), true, "FAIL:CopyFilesReportsstatus Not Checked");
			sa.assertEquals(UserManagementPage.Archivedatastatus(), true, "FAIL: Archivedatastatus CheckBox Not Checked");
			sa.assertEquals(UserManagementPage.ManualSyncstatus(), true, "FAIL:ManualSyncstatus CheckBox Not Checked");
			sa.assertEquals(UserManagementPage.CameraAccessstatus(), true, "FAIL:CameraAccessstatus CheckBox Not Checked");
			// UserManagementPage.ClkscrollBar_down();
			sa.assertEquals(UserManagementPage.Deletepassfailtemplatestatus(), true,
					"FAIL:Deletepassfailtemplatestatus CheckBox Not Checked");
			sa.assertEquals(UserManagementPage.EditPassFailtemplatestatus(), true,
					"FAIL:EditPassFailtemplatestatus CheckBox Not Checked");
			sa.assertEquals(UserManagementPage.CreatePassFailtemplatestatus(), true,
					"FAIL:CreatePassFailtemplatestatus CheckBox Not Checked");
			UserManagementPage.ClkscrollBar_down();
			sa.assertEquals(UserManagementPage.Audittrailstatus(), true, "FAIL: Audittrailstatus CheckBox Not Checked");
			sa.assertAll();
		}
		
		//UAM021B-Verify default privileges  for Supervisor User
		
		@Test(groups = { "Regression" }, description = "UAM021B-Verify default privileges  for Supervisor User")
		public void UAM021B() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("UAM021B-Verify default privileges  for Supervisor User");
			
			UserManagementPage.ClickNewUser();
			UserManagementPage.select_UserType("Supervisor");
			SoftAssert sa = new SoftAssert();
			// Validate check boxes are checked
			sa.assertEquals(UserManagementPage.CreaeteEditAssetPrivstatus(), true,
					"FAIL:CreaeteEditAssetPrivstatus should Checked");
			sa.assertEquals(UserManagementPage.CreaeteEditSetupstatus(), true,
					"FAIL:CreaeteEditSetupstatus should Checked");
			sa.assertEquals(UserManagementPage.CreateReportsstatus(), true, "FAIL :CreateReportsstatus should Checked");
			sa.assertEquals(UserManagementPage.RunQualificationstatus(), true,
					"FAIL: RunQualificationstatus should Checked");
			sa.assertEquals(UserManagementPage.RunCalibrationstatus(), true, "FAIL: RunCalibrationstatus should Checked");
			sa.assertEquals(UserManagementPage.ManualSyncstatus(), true, "FAIL: ManualSyncstatus should Checked");
			sa.assertEquals(UserManagementPage.CameraAccessstatus(), true, "FAIL:CameraAccessstatus should Checked");
			UserManagementPage.ClkscrollBar_down();
			UserManagementPage.ClkscrollBar_down();
			sa.assertEquals(UserManagementPage.CreatePassFailtemplatestatus(), true,
					"FAIL: CreatePassFailtemplatestatus checkbox should  be Checked");
			sa.assertEquals(UserManagementPage.EditPassFailtemplatestatus(), true,
					"FAIL: EditPassFailtemplatestatus checkbox should  be Checked");
			sa.assertEquals(UserManagementPage.Deletepassfailtemplatestatus(), true,
					"FAIL: Deletepassfailtemplatestatus checkbox should  be Checked");
			sa.assertEquals(UserManagementPage.Audittrailstatus(), true, "FAIL: Audittrailstatus should Checked");
			sa.assertAll();
		}
		
		//UAM021C-Verify default privileges  for Supervisor User
		
		@Test(groups = { "Regression" }, description = "Verify default privileges  for Operator User")
		public void UAM021C() throws InterruptedException, ParseException, IOException, AWTException {
			extentTest = extent.startTest("ADMN097-Verify default privileges  for Operator User");
			
			UserManagementPage.ClickNewUser();
			UserManagementPage.select_UserType("Supervisor");
			SoftAssert sa = new SoftAssert();
			// Validate check boxes are checked
			sa.assertEquals(UserManagementPage.CreaeteEditAssetPrivstatus(), true,
					"FAIL: CreaeteEditAssetPrivstatus should be Checked");
			sa.assertEquals(UserManagementPage.CreateReportsstatus(), true, "FAIL: CreateReportsstatus should be Checked");
			sa.assertEquals(UserManagementPage.RunQualificationstatus(), true,
					"FAIL: RunQualificationstatus should be Checked");
			sa.assertEquals(UserManagementPage.RunCalibrationstatus(), true,
					"FAIL: RunCalibrationstatus should be Checked");
			sa.assertEquals(UserManagementPage.CameraAccessstatus(), true, "FAIL: CameraAccessstatus should be Checked");

			UserManagementPage.ClkscrollBar_down();
			sa.assertEquals(UserManagementPage.Audittrailstatus(), true, "FAIL: Audittrailstatus should be Checked");
			sa.assertAll();

		}

		//UAM022-Check if the users list created appears at left hand side in alphabetical order.
		//UAM022 - MANUAL
		
		//UAM023 - Confirm that the Active users are differentiated by color from the disabled users in the list
	
		
		//UAM024 - Launch the audit trail report and check if the user creation entries are captured with Date and time, Create User entry with Success or Failure attempt, 
		//Name of the new User, Tablet Mac ID and/or IO box mac ID. Print audit trail report as a proof
		
		//UAM025- Check in the audit trail when user tries to create a user account with already existing user name a failure event is captured with Date and time, 
		//Create User entry with Success or Failure attempt, Name of the new User
		
		@Test(groups = {
				"Regression" }, description = "UAM025- Check in the audit trail when user tries to create a user account with already existing user name a failure event is captured with Date and time Create User entry with Success or Failure attempt, Name of the new User")

		public void UAM025A() throws InterruptedException, IOException {
			extentTest = extent.startTest(
					"UAM025- Check in the audit trail when user tries to create a user account with already existing user name a failure event is captured with Date and time Create User entry with Success or Failure attempt, Name of the new User");

			SoftAssert sa = new SoftAssert();

			UserManagementPage.UMCreation_MandatoryFields("User12", "1a", "1234569", "1234569", "Admin",
					"System Administrator");
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			MainHubPage = UserManagementPage.ClickBackButn();

			AuditPage = MainHubPage.ClickAuditTitle();
			Thread.sleep(2000);
			String Actionmsg = AuditPage.get_auditEvent_text();
			String ExpectMSG = "User Id : \"1a\" , User Name : \"User12\" , User level : \"System Administrator\" , User Privileges : \"DeleteSetup & Reports & DeleteFilesReports & AuditTrail & Admin & DeleteAssets & EquipmentPriveleges & DeleteEquipments & ManualSync & ArchiveConvertData & CopyFilesReports & CameraAccess & CreatePassFailTemplate & EditPassFailTemplate & DeletePassFailTemplate \" created by User Id : \"1\" , User Name : \"User1\"";
			sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for the new user account");
			sa.assertAll();
		}

		//UAM025B : 
		
		@Test(groups = {
				"Regression" }, description = "UAM025B- Check in the audit trail when user tries to create a user account with already existing user name a failure event is captured with Date and time Create User entry with Success or Failure attempt, Name of the new User")

		public void UAM025B() throws InterruptedException, IOException {
			extentTest = extent.startTest(
					"UAM025B- Check in the audit trail when user tries to create a user account with already existing user name a failure event is captured with Date and time Create User entry with Success or Failure attempt, Name of the new User");

			SoftAssert sa = new SoftAssert();

			UserManagementPage.ClickNewUser();
			UserManagementPage.UMCreation_MandatoryFields("UAM025B", "25B", "1234569", "1234569", "Admin",
					"System Administrator");
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
			Thread.sleep(1000);

			UserManagementPage.ClickNewUser();
			UserManagementPage.UMCreation_MandatoryFields("UAM025B", "25B", "1234569", "1234569", "Admin",
					"System Administrator");

			MainHubPage = UserManagementPage.ClickBackButn();

			AuditPage = MainHubPage.ClickAuditTitle();
			Thread.sleep(2000);
			String Actionmsg = AuditPage.get_auditEvent_text();
			String ExpectMSG = "User  creation attempt failed as there is existing user with the same Name or ID";
			sa.assertEquals(Actionmsg, ExpectMSG, "FAIL: Audit trial record does not exists for the already existing user name");
			sa.assertAll();
		}
		
		
	//UAM026-Enter the first name or last name, or any character in the string in the Search field, it should highlight the exact character match from the list

		@Test(groups = { "Regression" }, description = "UAM026-Enter the first name or last name, or any character in the string in the Search field, it should highlight the exact character match from the list")
		
	    public void UAM026() throws InterruptedException {
		extentTest = extent.startTest(
				"UAM026-Enter the first name or last name, or any character in the string in the Search field, it should highlight the exact character match from the list");

		SoftAssert sa = new SoftAssert();
		UserManagementPage.clickAnyUserinUserList("User1");
		sa.assertEquals(UserManagementPage.GetUserNametext(), "User1" , "FAIL: User name is available");
		sa.assertAll();
 }
		
	//UM027-create users and confirm that the details given at the time of user creation is exactly getting reflected
		
		@Test(groups = { "Regression" }, description = "UM027-create users and confirm that the details given at the time of user creation is exactly getting reflected")

		public void UM027() throws InterruptedException {
			extentTest = extent.startTest("UM027-create users and confirm that the details given at the time of user creation is exactly getting reflected");

			SoftAssert sa = new SoftAssert();

			UserManagementPage.UMCreation_MandatoryFields("UM027", "27", "1234569", "1234569", "Admin",
					"System Administrator");
			UserLoginPopup(getUID("adminFull"), getPW("adminFull"));
		
			sa.assertEquals(UserManagementPage.GetUserNametext(), "User1" , "FAIL: User name is not available");
			sa.assertEquals(UserManagementPage.GetUserIDtext(), "27" , "FAIL: User id is not available");
			sa.assertEquals(UserManagementPage.FetchTxt_UserType(), "System Administrator" , "FAIL: User type is not available");
			sa.assertEquals(UserManagementPage.get_UserTitle(), "FullAdmin" , "FAIL: User title is not available");
			sa.assertAll();
		}
		
	//UM028-Confirm if the user  is able to perform access all the privilages
	//Check if the Operator can access the privillaged modules and modules which the permissions are denied, an appropriate warning message appears as “User doesn’t have sufficient privileges to perform this operation!.
		
		
}

