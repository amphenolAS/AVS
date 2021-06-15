/**
 * @author manoj.ghadei
 *
 */

package com.avs.testcases;


import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//import com.avs.Listners.AllureReportListner;
import com.avs.base.BaseClass;
import com.avs.utility.TestUtilities;
import com.avs.pages.LoginPage;
import com.avs.pages.MainHubPage;
import com.avs.pages.UserManagementPage;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class LoginTest extends BaseClass{
	
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	LoginPage MainLoginPage;
	MainHubPage MainHubPage;
	UserManagementPage UserManagementPage;
	
	//Before All the tests are conducted
	@BeforeTest
	private void setExtent() throws IOException {
		
		// Rename the User file (NgvUsers.uxx) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\AppData", "NgvUsers.uux");
		//Rename the cache Asset file (Asset.txt) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles\\Cache", "Asset.txt");		
		//Rename the Asset folder (Asset) if exists
		renameFile("C:\\Program Files (x86)\\Kaye\\Kaye AVS Service\\DataFiles", "Assets");
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html",true);
		extent.addSystemInfo("VRT Version", "1.0.0.37");
		extent.addSystemInfo("BS Version", "0.6.13");
		extent.addSystemInfo("Lgr Version", "1.2.6");
		extent.addSystemInfo("User Name", "Manoj");
		extent.addSystemInfo("TestSuiteName", "LoginTest");

	}
	
	//After All the tests are conducted
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void Setup() throws InterruptedException {
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		MainLoginPage= new LoginPage();
	}

	// TearDown of the App
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
	
	
	@Test(groups = {"Regression"}, description="Verify if user can log into "
			+ "the Kaye Application after installation with default Kaye/411 credentials")
	public void LOGIN_001() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_001");
		//String result = "";
		//String exception = null;
		SoftAssert sa1 = new SoftAssert();
		UserManagementPage = MainLoginPage.DefaultLogin();

		sa1.assertEquals(UserManagementPage.IsUMscreenDisplayed(), true, "FAIL: Unable to Login"
				+" with defualt Kaye/411 login credentials");
		
		sa1.assertAll();
	}
	

	@Test(groups = {"Regression", "Sanity"},description="Verify if clicking on the "
			+ "Kaye application tab opens the Login Screen of the application")
	public void LOGIN_002() throws Exception {		
		extentTest = extent.startTest("LOGIN_002");
		SoftAssert sa2 = new SoftAssert();
		
		boolean state = MainLoginPage.LaunchAppLoginScreen();
				
		sa2.assertEquals(state, true, "FAIL: VRT App either didn't launch" 
		+" or Launched but not into LOGIN SCREEN");
		
		sa2.assertAll();		
	}
	
	
	@Test(groups = {"Regression"}, description="Verify  the contents of the Kaye application Login Screen ")
	public void LOGIN_003() throws Exception {	
		extentTest = extent.startTest("LOGIN_003");
		SoftAssert sa3 = new SoftAssert();
		//Validate Product Name
		String expectedAppName = "ValProbe RT System";
		//String ActualAppName = MainLoginPage.AppName();
		sa3.assertEquals(MainLoginPage.AppName(), expectedAppName, "FAIL: Invalid Product Name displayed");
		
		// Validate presence of UserID text field
		sa3.assertEquals(MainLoginPage.UserIDFieldPresence(), true, "FAIL: No UID field present");
		
		//Validate for Password text field presence
		sa3.assertEquals(MainLoginPage.UserPWFieldPresence(), true, "FAIL: No PW field present");
		
		//Validate for Login Button presence
		sa3.assertEquals(MainLoginPage.LoginBtnPresence(), true, "FAIL: LOGIN button is not Present");
		
		// Check for CANCEL Button presence
		sa3.assertEquals(MainLoginPage.CancelBtnPresence(), true, "FAIL: CANCEL button is not displayed");
				
		sa3.assertAll();
	}
	
	
	@Test(groups = {"Regression"}, description="Verify if the input data in the "
			+ "Password field is displayed as astrisk")
	public void LOGIN_004() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_004");
		SoftAssert sa4 = new SoftAssert();
		
		MainLoginPage.EnterUserPW("abc");
		Thread.sleep(1000);
		String actualPWTxt = MainLoginPage.GetTextUserPWField();
				
		sa4.assertNotEquals(actualPWTxt, "abc", "FAIL: The PW field data is not displayed in Astrisk");
		sa4.assertAll();
	}
	
	
	@Test(groups = {"Regression", "Sanity"}, description="Verify if user can login into "
			+ "the application by entering UserID and Password (Create 1st User) and then clicking on Login button")
	public void LOGIN_005() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_005");
		SoftAssert sa5 = new SoftAssert();
		
		//Login using Default Kaye/411
		UserManagementPage=MainLoginPage.DefaultLogin();
		//Create the 1st User
		MainLoginPage = UserManagementPage.FirstUserCreation("User1", getUID("adminFull"), getPW("adminFull"),
				getPW("adminFull"), "FullAdmin", "123456789", "abc@gmail.com");
		//Login with new User Credentials
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), getPW("adminFull"));
				
		//Verify if New User is logged in correctly
		sa5.assertEquals(MainHubPage.LoggedinUserName(), "User1", "FAIL: Incorrect User "
				+ "is logged to the system or unable to Login in");
		
		sa5.assertAll();
	}

	
/*	@Test(groups = "Regression", description="Verify if the Cancel button resets "
			+ "the UserId and Password fields to Null")
	public void LOGIN_006() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_006");
		SoftAssert sa6 = new SoftAssert();
		
		MainLoginPage.EnterUserID("a");
		MainLoginPage.EnterUserPW("abc");
		MainLoginPage.ClickCancelBtn();
		Thread.sleep(1000);
		
		String UIDtxt= MainLoginPage.GetTextUserIDField();
		String PWtxt = MainLoginPage.GetTextUserPWField();
				
		sa6.assertEquals(UIDtxt, "", "FAIL: Cancel button unable to clear the UserID field");
		sa6.assertEquals(PWtxt, "", "FAIL: Cancel button unable to clear the Password field");	
		
		sa6.assertAll();
	}*/
	
	
	@Test(groups = {"Regression"}, description="Verify if user is not allowed to login with invalid credentials")
	public void LOGIN_007() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_007");
		SoftAssert sa7 = new SoftAssert();
		
		MainLoginPage.EnterUserID("a");
		MainLoginPage.EnterUserPW("123");
		MainLoginPage.ClickLoginBtn();
		Thread.sleep(1000);
		
		sa7.assertEquals(MainLoginPage.InvalidLoginAlertmsgPresence(), true, "FAIL: App allowing"
				+" to login with INVALID LOGIN credentials");
		sa7.assertAll();
	}
	
	
	@Test(groups = {"Regression"}, description="Verify if user is not allowed to "
			+ "login if the UserId or Password field is left blank")
	public void LOGIN_008() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_008");
		SoftAssert sa8 = new SoftAssert();
		
		sa8.assertEquals(MainLoginPage.LoginBtnEnablestatus(), false, "FAIL: Login button enabled"
				+ " without any UID/PW entry");
		
		sa8.assertAll();
	}
		
	
	@Test(groups = {"Regression"}, description="Verify if the application closes on three unsuccessful login attempts")
	public void LOGIN_009() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_009");
		SoftAssert sa9 = new SoftAssert();
		
		MainLoginPage.EnterUserID("1");
		MainLoginPage.EnterUserPW("123");
		MainLoginPage.ClickLoginBtn();
		MainLoginPage.EnterUserID("2");
		MainLoginPage.EnterUserPW("123");
		MainLoginPage.ClickLoginBtn();
		MainLoginPage.EnterUserID("3");
		MainLoginPage.EnterUserPW("123");
		MainLoginPage.ClickLoginBtn();
		
		sa9.assertEquals(MainLoginPage.LaunchAppLoginScreen(), false, "FAIL: App does not SHUTDOWN "
				+"on entering 3 times INVALID User Credentials");
		sa9.assertAll();
	}

	
	@Test(groups = {"Regression"}, description="Verify if the first created admin user "
			+ "is not allowed to change his password during first login instance after user creation")
	public void LOGIN_010() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_010");
		SoftAssert sa10 = new SoftAssert();
		
		MainLoginPage.EnterUserID(getUID("adminFull"));
		MainLoginPage.EnterUserPW("Welcome1@AM");
		
		sa10.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), false, "FAIL: The 1st User is "
		+"allowed to Change its PW on 1st time Login");
		sa10.assertAll();		
	}
	
	
	@Test(groups = {"Regression"}, description="Verify if the Change Password tickbox "
			+ "is in enabled state during consecutive logins by the first admin user")
	public void LOGIN_012() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_012");
		SoftAssert sa11 = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), getPW("adminFull"));
		MainLoginPage=MainHubPage.UserSignOut();
		MainLoginPage.EnterUserID(getUID("adminFull"));
		MainLoginPage.EnterUserPW(getPW("adminFull"));
		
		sa11.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), true, "FAIL: The 1st User is "
		+"NOT allowed to Change its PW with Change PW option in disbaled state");
		sa11.assertAll();	
	}
	
	
	@Test(groups = {"Regression"}, description="Verify if checking the "
			+ "Change Password tickbox allows the user to change his password")
	public void LOGIN_013() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_013");
		SoftAssert sa12 = new SoftAssert();
		
		MainLoginPage.EnterUserID(getUID("adminFull"));
		MainLoginPage.EnterUserPW(getPW("adminFull"));
		MainLoginPage.ClickChangePWCheckbox();
		MainLoginPage.ClickLoginBtn();

		sa12.assertEquals(MainLoginPage.NewPWFieldPresence(), true, "FAIL: New PW field "
				+ "is not enabled/displayed to Change PW");
		sa12.assertAll();
	}
	
	
	@Test(groups = {"Regression"}, description="Verify if unchecking the "
			+ "Change Password tickbox restricts the user from changing his password")
	public void LOGIN_014() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_014");
		SoftAssert sa13 = new SoftAssert();
		
		MainLoginPage.EnterUserID(getUID("adminFull"));
		MainLoginPage.EnterUserPW(getPW("adminFull"));
		MainLoginPage.ClickChangePWCheckbox();
		MainLoginPage.ClickLoginBtn();
		
		
		try {
			MainLoginPage.ClickChangePWCheckbox();
			
			sa13.assertEquals(MainLoginPage.NewPWFieldPresence(), false, "FAIL: New PW field is enabled/displayed to Change PW"
					+ " even if the Change PW checkbox is unchecked");
			sa13.assertAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	@Test(groups = {"Regression"}, description="Verify if user can change "
			+ "the password by entering new password and clicking on the OK button")
	public void LOGIN_015() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_015");
		SoftAssert sa14 = new SoftAssert();
		
		MainHubPage=MainLoginPage.ChangeNewPW(getUID("adminFull"), getPW("adminFull"), "Welcome2@AM");
		
		sa14.assertEquals(MainHubPage.LoggedinUserName(), "User1", "FAIL: Password did not change for the User");
		sa14.assertAll();
	}
		
	
	@Test(groups = {"Regression"}, description="Verify if clicking on the Cancel button"
			+ " in the Change password field restores the previous password")
	public void LOGIN_016() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_016");
		SoftAssert sa15 = new SoftAssert();
		
		MainLoginPage.LoginEntry(getUID("adminFull"), "Welcome2@AM");
		MainLoginPage.ClickChangePWCheckbox();
		MainLoginPage.ClickLoginBtn();
		MainLoginPage.enterNewPW("Welcome3@AM");
		MainLoginPage.enterConfNewPW("Welcome3@AM");
		MainLoginPage.ClickCancelBtn();
		Thread.sleep(1000);
		
		LaunchApp("Kaye.ValProbeRT_racmveb2qnwa8!App");
		Thread.sleep(1000);
		MainLoginPage= new LoginPage();
		
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), "Welcome2@AM");

		sa15.assertEquals(MainHubPage.LoggedinUserName(), "User1", "FAIL: Cancel button"
				+ " at New PW change did not work as intended");
		sa15.assertAll();
	}
	
	
	//A Sys Admin User created
	@Test(groups = {"Regression", "Sanity"}, description="Verify if subsequent users created "
			+ "are forced to change their password during first login instance")
	public void LOGIN_017_018() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_017_18");
		SoftAssert sa16 = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), "Welcome2@AM");
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();
		
		UserManagementPage.CreateAdminUser(getUID("adminFull"), "Welcome2@AM", "User2", getUID("SysAdmin"), "Welcome1@AM", "SysAdmin", "123456789", "user1@aas.com");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry(getUID("SysAdmin"), "Welcome1@AM");
		MainLoginPage.ClickLoginBtn();
		
		sa16.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), false, "FAIL: A New User created"
				+ " is not forced to change PW on logging in for 1st time with ChangePWCheck box in Enabled state and in Unchecked state");
		sa16.assertEquals(MainLoginPage.NewPWFieldPresence(), true, "FAIL: A New User created" 
				+ " is not forced to change PW on logging in for 1st time with New PW field in Disabled/Invisible state");
				
		MainHubPage=MainLoginPage.EnterNewPWtext(getPW("SysAdmin"));
		MainHubPage.UserSignOut();
		sa16.assertAll();
	}
	
	//A Sys Supervisor User created	
	@Test(groups = {"Regression"}, description="Verify if the Change Password tickbox"
			+ " is in enabled state during furthur login attempts by the subsequent users")
	public void LOGIN_019() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_019");
		SoftAssert sa17 = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), "Welcome2@AM");
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();
		
		UserManagementPage.CreateSupervisorUser(getUID("adminFull"), "Welcome2@AM", "User3", getUID("SysSupervisor"), "Welcome1@AM", "SysSupervisor", "123456789", "user2@aas.com");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry(getUID("SysSupervisor"), "Welcome1@AM");
		MainLoginPage.ClickLoginBtn();
		MainHubPage=MainLoginPage.EnterNewPWtext(getPW("SysSupervisor"));
		MainLoginPage=MainHubPage.UserSignOut();
		MainLoginPage.LoginEntry(getUID("SysSupervisor"), getPW("SysSupervisor"));

		sa17.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), true, "FAIL: ChangePWCheck box "
				+ "appear to be in Disable state");
		sa17.assertAll();	
	}
	
	//A Sys Operator User created	
	@Test(groups = {"Regression"}, description="Verify if a user"
			+ " is forced to change his password while login, if his password has been changed by the admin user")
	public void LOGIN_020() throws InterruptedException {
		extentTest = extent.startTest("LOGIN_020");
		SoftAssert sa18 = new SoftAssert();
		
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), "Welcome2@AM");
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();
		
		UserManagementPage.CreateOperatorUser(getUID("adminFull"), "Welcome2@AM", "User4", getUID("SysOperator"), "Welcome1@AM", "SysOperator", "123456789", "user8@aas.com");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry(getUID("SysOperator"), "Welcome1@AM");
		MainLoginPage.ClickLoginBtn();
		MainHubPage=MainLoginPage.EnterNewPWtext("Welcome2@AM"); //User8 forced to Rest PW
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainHubPage=MainLoginPage.Login(getUID("adminFull"), "Welcome2@AM");
		UserManagementPage=MainHubPage.ClickAdminTile_UMpage();
		
		UserManagementPage.clickAnyUserinUserList("User4");
		UserManagementPage.enterNewUserPW("Welcome3@AM");
		UserManagementPage.enterNewUserConfPW("Welcome3@AM");
		UserManagementPage.ClickTitlefield();
		UserManagementPage.ClickNewUserSaveButton();
		UserLoginPopup(getUID("adminFull"), "Welcome2@AM");
		MainHubPage=UserManagementPage.ClickBackButn();
		MainLoginPage=MainHubPage.UserSignOut();
		
		MainLoginPage.LoginEntry(getUID("SysOperator"), getPW("SysSupervisor"));
		MainLoginPage.ClickLoginBtn();

		sa18.assertEquals(MainLoginPage.ChangePWCheckBoxEnableStatus(), false, "FAIL: User is NOT "
				+ "forced to change his password while login, if his password has been changed by the admin user");
		sa18.assertEquals(MainLoginPage.NewPWFieldPresence(), true, "FAIL: User is NOT " 
				+ " forced to change his password while login with New PW field in Disabled/Invisible state");
		
		MainHubPage=MainLoginPage.EnterNewPWtext(getPW("SysOperator"));
		MainHubPage.UserSignOut();
		sa18.assertAll();
	}		

}
