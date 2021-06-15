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

import com.avs.base.BaseClass;

public class EquipmentPage extends BaseClass {

	// EquipmentPage Element definition
	WebElement EquipmentID = null;
	WebElement ModelNumber = null;
	WebElement EquipmentTypeUMDropDown = null;
	WebElement EquipmentSaveButton = null;
	
	private void initElements() {
		EquipmentID = driver.findElementByAccessibilityId("EquipmentIDTextBox");
		ModelNumber = driver.findElementByAccessibilityId("ModelNumberTextBox");
		EquipmentTypeUMDropDown = driver.findElementByAccessibilityId("EquipmentTypeComboBox");
		EquipmentSaveButton = driver.findElementByName("Save");
		
	}
	
	EquipmentPage() {
		super();
		initElements();
	}
	/*----------------------
	Methods of Equipment Page
	------------------------*/
	public void enterNewEquipmentID(String NewUID) {
		ClearText(EquipmentID);
		enterText(EquipmentID, NewUID);
	}
	public void enterNewModelNumber(String MNum) {
		ClearText(ModelNumber);
		enterText(ModelNumber, MNum);
	}
	// Select Equipment Type
	
		public void select_EquipmentType(String Etype) throws InterruptedException {
			System.out.println(Etype);
			clickOn(EquipmentTypeUMDropDown);
			Thread.sleep(1000);
			
			WebElement EPSelect = driver.findElementByName("Select");
			WebElement EPValidator = driver.findElementByName("Validator");
			WebElement EPVRTLogger = driver.findElementByName("VRT Logger");
			WebElement EPAVS = driver.findElementByName("AVS");
			WebElement EPIRTD = driver.findElementByName("IRTD");
			WebElement EPBath = driver.findElementByName("Bath");
			WebElement EPBaseStation = driver.findElementByName("Base Station");
			
			if (Etype.equals(EPSelect.getText())) {
				clickOn(EPSelect);
				Thread.sleep(500);
			} else if (Etype.equals(EPValidator.getText())) {
				// SelectAdministrator();
				clickOn(EPValidator);
				// Thread.sleep(500);
			} else if (Etype.equals(EPVRTLogger.getText())) {
				clickOn(EPVRTLogger);
				Thread.sleep(500);
			} else if (Etype.equals(EPAVS.getText())) {
				clickOn(EPAVS);
				Thread.sleep(500);
			}else if (Etype.equals(EPIRTD.getText())) {
					clickOn(EPIRTD);
					Thread.sleep(500);
			}else if (Etype.equals(EPBath.getText())) {
				clickOn(EPBath);
				Thread.sleep(500);
			}else if (Etype.equals(EPBaseStation.getText())) {
				clickOn(EPBaseStation);
				Thread.sleep(500);
			}

		}
		
	//Click on Save button
		public void ClickSaveButton() throws InterruptedException {
			clickOn(EquipmentSaveButton);
			Thread.sleep(1000);
		}
	// Fetch the Save Alert message
		public String AlertMsg() {
			WebElement Msg = driver.findElementByAccessibilityId("displayMessageTextBlock");
			return FetchText(Msg);
		}
// Login Pop up presence
		public boolean UserLoginPopupVisible() throws InterruptedException {
			WebElement LgInPopup = driver.findElementByName("Enter User Credentials");
			return IsElementVisibleStatus(LgInPopup);
		}
				
//Enter Mandatory fields and create Equipment
		public void EqipCreation_MandatoryFields(String EID, String EMnum,String Etype)throws InterruptedException {
			enterNewEquipmentID(EID);
			enterNewModelNumber(EMnum);
			select_EquipmentType(Etype);
			ClickSaveButton();
		}
//Click on back button 
				public EquipmentHubPage ClickBackBtn() {
				WebElement backBtn = driver.findElementByAccessibilityId("ArrowGlyph");	
				clickOn(backBtn);
				return new EquipmentHubPage();
				}

		
}

