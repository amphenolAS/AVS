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
import com.avs.pages.Equipment_IRTDHubPage;
import com.avs.base.BaseClass;

public class EquipmentHubPage extends BaseClass {
	//IRTDHubPage IRTDHubPage;
	// EquipmentHubPage Element definition
	WebElement AddButton = null;

	private void initElements() {
		AddButton = driver.findElementByAccessibilityId("AddEquipmentsButton");
	}
	
	EquipmentHubPage()
	{
		super();
		initElements();

	}
	
	// Click AddButton 
		public EquipmentPage ClickAddButton() throws InterruptedException {
			clickOn(AddButton);
			Thread.sleep(1000);
			return new EquipmentPage();
		}
		
		//IRTD
				//Click on IRTD List box of Equipment page
				public IRTDHubPage ClickonIRTDlistbox() {
					WebElement irtdbox = driver.findElementByName("IRTD");
					clickOn(irtdbox);
					return new IRTDHubPage();
				}
				
				// Click AddButton to get Alert message when supervisor does not have default
				// privilege
				public void Alert_ClickAddBtn() throws InterruptedException {
					clickOn(AddButton);
					Thread.sleep(1000);
				}
				// Alert message for non default privilege of supervisor
				public String AlertMsg() {
					WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
					return FetchText(Msg);
				}
				// Click on IRTD List box of Equipment page
				public Equipment_IRTDHubPage click_IRTDTile() throws IOException {
					WebElement irtdbox = driver.findElementByName("IRTD");
					clickOn(irtdbox);
					return new Equipment_IRTDHubPage();
				}

}

