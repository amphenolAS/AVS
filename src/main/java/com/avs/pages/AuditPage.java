package com.avs.pages;


import java.awt.AWTException;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.avs.base.BaseClass;


public class AuditPage extends BaseClass {

	// FileManagementPage Element definition
	WebElement Audit_HeadTitle = null;
	WebElement ActionFilter_Icon = null;

	void initElements() {
		Audit_HeadTitle = driver.findElementByName("Audit Trail");
		ActionFilter_Icon = driver.findElementByAccessibilityId("PART_FilterButton");

	}

	AuditPage() throws IOException {
		super();
		initElements();

	}

	// Release memory
	public void resetWebElements() {
		ActionFilter_Icon = null;

	}

	// Audit TextBox is Visible
	public boolean AuditHeadTitleVisible() throws InterruptedException {
		return IsElementVisibleStatus(Audit_HeadTitle);
	}

// Fetch the alert message when a user does not have privilege to access
	public String AlertMsg() {
		WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
		return FetchText(Msg);
	}
// Click on Action filter icon to open the filter

	public void Click_ActionFilter_Icon() {

		List<WebElement> filtericon = driver.findElementsByAccessibilityId("PART_FilterButton");
		System.out.println(filtericon.size());
		filtericon.get(3).click();
	}

	// Click on Action Filter (ok)Btn to get the filter result

	public void click_Action_FilterBtn() throws IOException, InterruptedException {
		// List<WebElement> filterBtn =
		// driver.findElementsByAccessibilityId("PART_FilterButton");
		List<WebElement> filterBtn = driver.findElementsByName("Filter");
		// System.out.println(filterBtn.size());

		filterBtn.get(1).click();

		// WebElement msg = driver.findElementByName(");

	}

// Enter value into Action filter text box

	public void EnterTxt_ActionFilter(String val) {
		WebElement ActionFltr_ValueBox = driver.findElementByAccessibilityId("PART_ValueBox");
		clickOn(ActionFltr_ValueBox);
		ClearText(ActionFltr_ValueBox);
		enterText(ActionFltr_ValueBox, val);
	}

	// Verify that the below details is displaying when user filter the paticular
	// action
	public String get_auditEvent_text() {
		List<WebElement> Act_Txt = driver.findElementByAccessibilityId("PART_ScrollViewer")
				.findElements(By.className("TextBlock"));
		return FetchText(Act_Txt.get(3));
	}

}
