package com.actitime.testscript;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.actitime.generic.BaseClass;
import com.actitime.pom.HomePage;
import com.actitime.pom.TaskListPage;

@Listeners(com.actitime.generic.ListenerImplimentation.class)
public class CustomerModule extends BaseClass{

	@Test
	public void testCreateCustomer() throws EncryptedDocumentException, IOException, InterruptedException {
		Reporter.log("CreateCustomer",true);
		FileInputStream fis=new FileInputStream("./data/testscript.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String custName = wb.getSheet("CreateCustomer").getRow(1).getCell(2).getStringCellValue();
		String custDesc = wb.getSheet("createcustomer").getRow(1).getCell(3).getStringCellValue();
		
		HomePage h=new HomePage(driver);
		h.setTaskTab();	
		TaskListPage t=new TaskListPage(driver);
		t.getAddNewBtn().click();
		t.getNewCustOption().click();
		t.getEnterCustNameTbx().sendKeys(custName);
		t.getEnterCustDesTbx().sendKeys(custDesc);
		t.getSelectCustDD().click();
		t.getOurCompanyTxt().click();
		t.getCreateCustBtn().click();
		Thread.sleep(4000);
		String actualCustName=t.getActualCustCreated().getText();
		
		Assert.assertEquals(custName, actualCustName);
		
	}
	


}