package com.Salesforce.automationscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.Salesforce.base.BaseTestSF;

public class SFLeadsAutomationScripts extends BaseTestSF{
	
	@Test
	public void TC20leadsTab() 
	{
		String expLeadsTitle = "Leads: Home ~ Salesforce - Developer Edition";
		loginSalesforce();
		WebElement leads = driver.findElement(By.partialLinkText("Leads"));
		//waitforVisibility(leads, 40, "Leads Tab");
		WebDriverWait wait = new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.visibilityOf(leads));
	
		clickElement(leads, "Leads Tab");

		String actualLeadsTitle = getTitle(driver);
		checkText(expLeadsTitle,actualLeadsTitle, "Lead Title");
		

	}
	@Test
	protected void TC21leadsSelectView()
	{
		String expleadviewtxt1 = "All Open Leads";
		String expleadviewtxt2 ="My Unread Leads";
		String expleadviewtxt3 ="Recently Viewed Leads";
		String expleadviewtxt4 ="Today's Leads";
								
		loginSalesforce();
		
		
		  	WebElement leads =  driver.findElement(By.partialLinkText("Leads"));
		  
		  	WebDriverWait wait = new WebDriverWait(driver,30);
		  	wait.until(ExpectedConditions.visibilityOf(leads));
			  	  
			clickElement(leads, "Leads Tab");
			  
			WebElement view1 = driver.findElement(By.xpath("//*[@id='fcf']//child::option[1]"));
			String actualleadviewtxt1 = getTextFromElement(view1, "Lead view dropdown option 1");
			checkText(expleadviewtxt1,actualleadviewtxt1, "Lead view All open Leads");

			  
			WebElement view2 = driver.findElement(By.xpath("//*[@id='fcf']//child::option[2]"));
			String actualleadviewtxt2 = getTextFromElement(view2, "Lead view dropdown option 2");
			checkText(expleadviewtxt2,actualleadviewtxt2, "Lead view My Unread Leads");

			 
			 WebElement view3 = driver.findElement(By.xpath("//*[@id='fcf']//child::option[3]"));
			 String actualleadviewtxt3 = getTextFromElement(view3, "Lead view dropdown option 3");
			checkText(expleadviewtxt3,actualleadviewtxt3, "Lead view Recently View Leads");


			WebElement view4 = driver.findElement(By.xpath("//*[@id='fcf']//child::option[4]"));
			String actualleadviewtxt4 = getTextFromElement(view4, "Lead view dropdown option 4");
			checkText(expleadviewtxt4,actualleadviewtxt4, "Lead view Today's Lead");
				
		
	}
	@Test
	protected void TC22defaultView() 
	{
			loginSalesforce();
			WebElement leads =  driver.findElement(By.partialLinkText("Leads"));
			clickElement(leads, "Leads Tab");
			WebElement viewTxtbox = driver.findElement(By.xpath("//*[@id=\"fcf\"]"));
			selectbyindex(viewTxtbox, 1, "viewTxtbox");

			WebElement view3 = driver.findElement(By.xpath("//*[@name='fcf']//child::option[@selected='selected']"));
			String actualleadviewtxt3 = getTextFromElement(view3, "Lead selected view");
			System.out.println(actualleadviewtxt3 + "is selected");
			logoutSalesforce();
						
			loginSalesforce();
			
			WebElement leads2 =  driver.findElement(By.partialLinkText("Leads"));		
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOf(leads2));
				
			clickElement(leads2, "Leads Tab");
			WebElement go = driver.findElement(By.xpath("//input[@value=' Go! ']"));
			clickElement(go, "Go");

			WebElement viewnow = driver.findElement(By.xpath("//select[@name='fcf']//child::option[@selected='selected']"));
			String expleadviewtxt3 = getTextFromElement(viewnow, "Lead selected view");
			checkText(expleadviewtxt3, actualleadviewtxt3, "Lead Default View");
	}
	
	@Test
	protected void TC23Leadviewcheck() 
	{
	String expleadviewtxt ="Today's Leads";

		loginSalesforce();
	 	WebElement leads =  driver.findElement(By.partialLinkText("Leads"));
		clickElement(leads, "Leads Tab");
		
		WebElement viewTxtbox = driver.findElement(By.xpath("//*[@id=\"fcf\"]"));
		selectbyindex(viewTxtbox, 3, "viewTxtbox");
		
		WebElement view3 = driver.findElement(By.xpath("//*[@name='fcf']//child::option[@selected='selected']"));
		String actualleadviewtxt3 = getTextFromElement(view3, "Lead selected view");
		System.out.println(actualleadviewtxt3 + " is selected");
		checkText(expleadviewtxt,actualleadviewtxt3, "Lead view Today's Lead");

		logoutSalesforce();
	
}
	@Test
	protected void TC24LeadNewButton() 
	{
		loginSalesforce();
		
		WebElement leads =  driver.findElement(By.partialLinkText("Leads"));
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(leads));	
		clickElement(leads, "Leads Tab");
		
		WebElement newbtn = driver.findElement(By.xpath("//input[@value=' New ']"));
		clickElement(newbtn, "New Lead");
		
		WebElement lastname = driver.findElement(By.id("name_lastlea2"));
		enterTextBox(lastname, "ABCD", "lastname");
	
		WebElement companyname = driver.findElement(By.id("lea3"));
		enterTextBox(companyname, "ABCD", "companyname");
		
		WebElement save = driver.findElement(By.xpath("//input[@value=' Save ']"));
		clickElement(save, "Save New Lead");	
		
		WebElement tophead = driver.findElement(By.xpath("//h2[@class='topName']"));
		String str = getTextFromElement(tophead, "New Lead Name");
		checkText("ABCD", str, "New Lead created");
		System.out.println(str + "is the newly created Lead");
	
		logoutSalesforce();

	}

}
