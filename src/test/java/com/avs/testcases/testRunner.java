/**
 * @author manoj.ghadei
 *
 */

package com.avs.testcases;

import org.testng.TestNG;

//import com.avs.Listners.AllureReportListner;

public class testRunner {
	
	static TestNG testng;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		//AllureReportListner ar = new AllureReportListner();

		testng = new TestNG();
		//testng.setTestClasses(new Class[] {assetCreationTest.class});
		//testng.setTestClasses(new Class[] {LoginTest.class});
		testng.setTestClasses(new Class[] {assetHubTest.class});
		//testng.addListener(ar);
		testng.run();	
		
	}

}
