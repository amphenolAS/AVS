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

public class IRTDHubPage extends BaseClass {
	//EquipmentIRTDDetailspage EquipmentIRTDDetailspage;

	// EquipmentHubPage Element definition
	//List <WebElement> IrtdSerial = null;
	
	private void initElements() {
		//IrtdSerial = driver.findElementByName("Serial No: ").findElements(By.className("TextBlock"));
	}
	
	IRTDHubPage()
	{
		super();
		initElements();

	}
	
	// Click IRTD
	/*
	 * public EquipmentIRTDDetailspage ClickIrtdSerialNo() throws
	 * InterruptedException { clickOn(IrtdSerial); return EquipmentIRTDDetailspage;
	 */
		
		//Click/Select the IRTD serial number in IRTDHubPage 
		public IRTDDetailspage Click_IrtdSerialNo(String SN) throws InterruptedException {
			
			List<WebElement> IrtdList =  driver.findElementByAccessibilityId("loggersGrid").findElements(By.className("GridViewItem"));
			//List<WebElement> IrtdSerial =  driver.findElementByName("Serial No: ").findElements(By.className("TextBlock"));
			System.out.println("Total IRTD Equipments created: " + IrtdList.size());
                         
			// Loop for the different serial number created
			for (int i = 0; i < IrtdList.size(); i++) {
				System.out.println("serial number : " + IrtdList.get(i).getText());

				List<WebElement> IRTDTileInfoList = IrtdList.get(i).findElements(By.className("TextBlock"));
				System.out.println(" IRTD tile info count: " + IRTDTileInfoList.size());

				// Fetch all the contents of the Asset tile
				for (int j = 0; j < IRTDTileInfoList.size(); j++) {
					System.out.println("AssetTileInfo: "+IRTDTileInfoList.get(j).getText());

					if (IRTDTileInfoList.get(j).getText().contains(SN)) {
						clickOn(IRTDTileInfoList.get(j));
						Thread.sleep(1000);
						break;
					}
				}
			}		
			return new IRTDDetailspage();
		}
}

