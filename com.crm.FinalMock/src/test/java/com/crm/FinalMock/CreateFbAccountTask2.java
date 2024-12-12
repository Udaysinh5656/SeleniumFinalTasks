package com.crm.FinalMock;

import java.io.IOException;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.Basic.BaseClass;
import com.crm.FileUtility.AccessExcel;
import com.crm.POM.FacebookAccount;


@Listeners(com.crm.listeners.takeScreenShot.class)
public class CreateFbAccountTask2 extends BaseClass{
	@Test
	public void createAccount() throws InterruptedException, EncryptedDocumentException, IOException {
		String parent = driver.getWindowHandle();
		
	FacebookAccount fa = new FacebookAccount(driver);
		fa.facebook().click();
		Thread.sleep(1000);
		
		Actions act = new Actions(driver);
		act.keyDown(Keys.PAGE_DOWN);
		act.keyDown(Keys.PAGE_DOWN);
		
		Set<String> fbHandle = driver.getWindowHandles();
		for (String str : fbHandle) {
			driver.switchTo().window(str);
		}
		Thread.sleep(2000);
		fa.createAccount().click();
		Thread.sleep(2000);
		Set<String> createnewaccount = driver.getWindowHandles();
		for (String str : createnewaccount) {
			driver.switchTo().window(str);
		}
	
		AccessExcel exc = new AccessExcel();
		String sheetdata = exc.orgInfo("Sheet1", 1, 0);
		fa.firstname().sendKeys(sheetdata);
		
		String sheetemail = exc.orgInfo("Sheet1", 1, 1);
		fa.lastname().sendKeys(sheetemail);
		
		Select sel = new Select(fa.day());
		sel.selectByVisibleText("15");
		
		Select sel1 = new Select(fa.month());
		sel1.selectByVisibleText("May");
		
		Select sel2 = new Select(fa.year());
		sel2.selectByVisibleText("2002");
		Thread.sleep(2000);
		
		fa.gender().click();
		String sheetemai2 = exc.orgInfo("Sheet1", 1, 2);
		fa.mob_no().sendKeys(sheetemai2);
		
		String sheetemai3 = exc.orgInfo("Sheet1", 1, 3);
		fa.psw().sendKeys(sheetemai3);
		
		driver.switchTo().window(parent);
		Thread.sleep(2000);
	}

}
