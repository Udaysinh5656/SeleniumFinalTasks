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
import com.crm.POM.TwitterAccount;

@Listeners(com.crm.listeners.takeScreenShot.class)
public class CreatetwitterAccountTask4 extends BaseClass{
	@Test
	public void createAccount() throws InterruptedException, EncryptedDocumentException, IOException {
		String parent = driver.getWindowHandle();
		
	TwitterAccount ta = new TwitterAccount(driver);
		ta.twitter().click();
		Thread.sleep(1000);
		
		Actions act = new Actions(driver);
		act.keyDown(Keys.PAGE_DOWN);
		act.keyDown(Keys.PAGE_DOWN);
		
		Set<String> twitterHandle = driver.getWindowHandles();
		for (String str : twitterHandle) {
			driver.switchTo().window(str);
		}
		Thread.sleep(2000);
		ta.create_account().click();
		Thread.sleep(1000);
		AccessExcel exc = new AccessExcel();
		String sheetdata = exc.orgInfo("Sheet2", 1, 0);
		ta.name().sendKeys(sheetdata);
		
		
		String sheetemail = exc.orgInfo("Sheet2", 1, 1);
		ta.email().sendKeys(sheetemail);
		
		Select sel = new Select(ta.month());
		sel.selectByVisibleText("May");
		
		Select sel1 = new Select(ta.day());
		sel1.selectByVisibleText("15");
		
		Select sel2 = new Select(ta.year());
		sel2.selectByVisibleText("2002");
		Thread.sleep(2000);
		
		driver.switchTo().window(parent);
		Thread.sleep(2000);
	}

}
