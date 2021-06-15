/**
 * @author ruchika.behura
 *
 */

package com.avs.pages;

import java.awt.AWTException;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.avs.pages.EquipmentPage;
import com.avs.base.BaseClass;

public class AuditPage extends BaseClass {
	
	// FileManagementPage Element definition
	WebElement Audit_HeadTitle = null;
	

	
	private void initElements() {
		Audit_HeadTitle = driver.findElementByName("Audit Trail");	

	}
	
	AuditPage()
	{
		super();
		initElements();

	}
	

	//Archive TextBox is Visible
		public boolean AuditHeadTitleVisible() throws InterruptedException {
			return IsElementVisibleStatus(Audit_HeadTitle);
		}	
}