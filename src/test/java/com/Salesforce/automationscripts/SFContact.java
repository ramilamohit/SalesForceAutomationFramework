package com.Salesforce.automationscripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.Salesforce.base.BaseTestSF;

public class SFContact extends BaseTestSF
{
	
	protected static Logger SFContactlog = LogManager.getLogger();

	@Test
	public void TC25CreateNewContact()
	{
		loginSalesforce();
		
		WebElement conttab = driver.findElement(By.xpath("//li[@id=\"Contact_Tab\"]/a"));
		clickElement(conttab, "Contact Tab");
		
		String expheader = "Contacts";
		WebElement contactheader = driver.findElement(By.xpath("//h1[@class='pageType']"));
		String actheader = getTextFromElement(contactheader, "Contact header");
		checkText(expheader, actheader, "Contact header");
		
		String exphome = " Home";
		WebElement homelabel = driver.findElement(By.xpath("//h2[@class='pageDescription']"));
		String acthome = getTextFromElement(homelabel, "Home Label");
		checkText(exphome.trim(), acthome.trim(), "Home Label");
		
		
		WebElement newbtn = driver.findElement(By.xpath("//input[contains(@value,'New')]"));
		clickElement(newbtn, "New");
		
		String expContactedit = "Contact Edit";
		WebElement contacteditheader = driver.findElement(By.xpath("//h1[@class='pageType']"));
		String actheader1 = getTextFromElement(contacteditheader, "Contact header");
		checkText(expContactedit, actheader1, "Contact edit Label");
		
		String expnewcontact = "New Contact";
		WebElement homelabel1 = driver.findElement(By.xpath("//h2[@class='pageDescription']"));
		String acthome1 = getTextFromElement(homelabel1, "Home Label");
		checkText(expnewcontact, acthome1, "New Contact Label");
		
		WebElement lastname = driver.findElement(By.id("name_lastcon2"));
		enterText(lastname, "Mohit", "Last name");
		
		WebElement accname = driver.findElement(By.id("con4"));
		enterText(accname, "Prem", "Account name");
		
		WebElement savebtn = driver.findElement(By.xpath("//input[contains(@value,'Save')]"));
		clickElement(savebtn, "Save");
		
		
	}
	@Test
	protected void TC26CreateNewView()
	{
	loginSalesforce();
	
	WebElement conttab = driver.findElement(By.xpath("//li[@id=\"Contact_Tab\"]/a"));
	clickElement(conttab, "Contact Tab");
	
	String expheader = "Contacts";
	WebElement contactheader = driver.findElement(By.xpath("//h1[@class='pageType']"));
	String actheader = getTextFromElement(contactheader, "Contact header");
	checkText(expheader, actheader, "Contact header");
	
	String exphome = " Home";
	WebElement homelabel = driver.findElement(By.xpath("//h2[@class='pageDescription']"));
	String acthome = getTextFromElement(homelabel, "Home Label");
	checkText(exphome.trim(), acthome.trim(), "Home Label");

	WebElement recentcontact = driver.findElement(By.id("hotlist_mode"));
	selectbyVisibility(recentcontact, "Recently Created", "Recently Created ");
	
	String explabeltxt = "Recent Contacts";
	WebElement acclabel = driver.findElement(By.xpath("//h3[text()='Recent Contacts']"));
	String acclabeltxt = getTextFromElement(acclabel, "Header");
	checkText(explabeltxt, acclabeltxt, "Recent Contact header");
	
		
	}
	@Test
	protected void TC28MyContacts()
	{
		loginSalesforce();
		
		WebElement conttab = driver.findElement(By.xpath("//li[@id=\"Contact_Tab\"]/a"));
		clickElement(conttab, "Contact Tab");

		String expheader = "Contacts";
		WebElement contactheader = driver.findElement(By.xpath("//h1[@class='pageType']"));
		String actheader = getTextFromElement(contactheader, "Contact header");
		checkText(expheader, actheader, "Contact header");
		
		String exphome = " Home";
		WebElement homelabel = driver.findElement(By.xpath("//h2[@class='pageDescription']"));
		String acthome = getTextFromElement(homelabel, "Home Label");
		checkText(exphome.trim(), acthome.trim(), "Home Label");
		
		WebElement view = driver.findElement(By.xpath("//select[@id='fcf']"));
		selectbyVisibility(view, "My Contacts", "View Dropdown");
		
		String expview = "My Contacts";
		WebElement view1 = driver.findElement(By.xpath("//select[@name='fcf']/option[@selected='selected']"));
		String viewnw = getTextFromElement(view1, "View Page");
		checkText(expview, viewnw, "View textbox");
		
	}
	@Test
	protected void TC29ViewContactPage()
	{
		loginSalesforce();
		
		WebElement conttab = driver.findElement(By.xpath("//li[@id=\"Contact_Tab\"]/a"));
		clickElement(conttab, "Contact Tab");
		
		String expheader = "Contacts";
		WebElement contactheader = driver.findElement(By.xpath("//h1[@class='pageType']"));
		String actheader = getTextFromElement(contactheader, "Contact header");
		checkText(expheader, actheader, "Contact header");
		
		String exphome = " Home";
		WebElement homelabel = driver.findElement(By.xpath("//h2[@class='pageDescription']"));
		String acthome = getTextFromElement(homelabel, "Home Label");
		checkText(exphome.trim(), acthome.trim(), "Home Label");

		WebElement recentcontact = driver.findElement(By.id("hotlist_mode"));
		selectbyVisibility(recentcontact, "Recently Created", "Recently Created ");
		
		String explabeltxt = "Recent Contacts";
		WebElement acclabel = driver.findElement(By.xpath("//h3[text()='Recent Contacts']"));
		String acclabeltxt = getTextFromElement(acclabel, "Header");
		checkText(explabeltxt, acclabeltxt, "Recent Contact header");
		
		WebElement namelst = driver.findElement(By.xpath("//table[@class='list']/tbody/tr[8]/th/a"));
		String expname = getTextFromElement(namelst, "Recent name");
		clickElement(namelst, "Name from Recent name column");
		
		WebElement namelabel = driver.findElement(By.xpath("//h2[@class='topName']"));
		String actname = getTextFromElement(namelabel, "Recent name");
		
		String[] expnm = expname.split(",");
		int len = expnm.length;
		int count =0;
		for(int i=0; i<expnm.length;i++ )
		{
			if(actname.contains(expnm[i]))
			{
				count++;
			}
			else
			{
				SFContactlog.error("Recent name on Contact page Test case failed");
				break;
			}
			if(count == len)
			{
				SFContactlog.info("Recent name on Contact page Test case passed");
			}
			
		}
	}
	@Test
	protected void TC30Errormsg()
	{
		loginSalesforce();
		
		WebElement conttab = driver.findElement(By.xpath("//li[@id=\"Contact_Tab\"]/a"));
		clickElement(conttab, "Contact Tab");
		
		String expheader = "Contacts";
		WebElement contactheader = driver.findElement(By.xpath("//h1[@class='pageType']"));
		String actheader = getTextFromElement(contactheader, "Contact header");
		checkText(expheader, actheader, "Contact header");
		
		String exphome = " Home";
		WebElement homelabel = driver.findElement(By.xpath("//h2[@class='pageDescription']"));
		String acthome = getTextFromElement(homelabel, "Home Label");
		checkText(exphome.trim(), acthome.trim(), "Home Label");
		
		WebElement createview = driver.findElement(By.linkText("Create New View"));
		clickElement(createview, "Create new view");
		
		WebElement uniquenmtxt = driver.findElement(By.id("devname"));
		enterText(uniquenmtxt, "EFGH", "View Unique name");
		
		WebElement save = driver.findElement(By.xpath("//input[contains(@value,'Save')]"));
		clickElement(save, "Save");
		
		String experrmsg = "Error: You must enter a value";
		WebElement errmsg = driver.findElement(By.className("errorMsg"));
		String acterrmsg = getTextFromElement(errmsg, "Error message");
		checkText(experrmsg, acterrmsg, "Error message");
		
				
	}
	@Test
	protected void TC31CancelButton() 
	{
		loginSalesforce();
		
		WebElement conttab = driver.findElement(By.xpath("//li[@id=\"Contact_Tab\"]/a"));
		clickElement(conttab, "Contact Tab");
		
		String expheader = "Contacts";
		WebElement contactheader = driver.findElement(By.xpath("//h1[@class='pageType']"));
		String actheader = getTextFromElement(contactheader, "Contact header");
		checkText(expheader, actheader, "Contact header");
		
		String exphome = " Home";
		WebElement homelabel = driver.findElement(By.xpath("//h2[@class='pageDescription']"));
		String acthome = getTextFromElement(homelabel, "Home Label");
		checkText(exphome.trim(), acthome.trim(), "Home Label");
		
		WebElement createview = driver.findElement(By.linkText("Create New View"));
		clickElement(createview, "Create new view");
		
		String expviewname ="ABCD";
		WebElement viewname = driver.findElement(By.id("fname"));
		enterText(viewname, expviewname, "View name");
		
		WebElement uniquenmtxt = driver.findElement(By.id("devname"));
		clickElement(uniquenmtxt, "Unique name");	
		uniquenmtxt.clear();
		enterText(uniquenmtxt, "EFGH", "View Unique name");
	
		WebElement cancelbtn = driver.findElement(By.xpath("//div[@class=\"pbHeader\"]//descendant::input[@value='Cancel']"));
		clickElement(cancelbtn, "Cancel");
		
		String explabeltxt = "Recent Contacts";
		WebElement acclabel = driver.findElement(By.xpath("//h3[text()='Recent Contacts']"));
		String acclabeltxt = getTextFromElement(acclabel, "Header");
		checkText(explabeltxt, acclabeltxt, "Recent Contact header");
		
		WebElement namelst = driver.findElement(By.xpath("//table[@class='list']/tbody/tr[2]/th/a"));
		String name = getTextFromElement(namelst, "Recent name");
		
		if(!name.equals(expviewname))
		{
			SFContactlog.info("ABCD is not in the recent name");
			SFContactlog.info("Cancel button Test case passed");
		}
		else
		{
			SFContactlog.error("Cancel button test case failed");
		}
		
	}
	@Test
	protected void TC32SaveNewButton()
	{
		loginSalesforce();
		
		WebElement conttab = driver.findElement(By.xpath("//li[@id=\"Contact_Tab\"]/a"));
		clickElement(conttab, "Contact Tab");
		
		String expheader = "Contacts";
		WebElement contactheader = driver.findElement(By.xpath("//h1[@class='pageType']"));
		String actheader = getTextFromElement(contactheader, "Contact header");
		checkText(expheader, actheader, "Contact header");
		
		String exphome = " Home";
		WebElement homelabel = driver.findElement(By.xpath("//h2[@class='pageDescription']"));
		String acthome = getTextFromElement(homelabel, "Home Label");
		checkText(exphome.trim(), acthome.trim(), "Home Label");
		
		WebElement newbtn = driver.findElement(By.xpath("//input[contains(@value,'New')]"));
		clickElement(newbtn, "New");
		
		String expContactedit = "Contact Edit";
		WebElement contacteditheader = driver.findElement(By.xpath("//h1[@class='pageType']"));
		String actheader1 = getTextFromElement(contacteditheader, "Contact header");
		checkText(expContactedit, actheader1, "Contact edit Label");
		
		String expnewcontact = "New Contact";
		WebElement homelabel1 = driver.findElement(By.xpath("//h2[@class='pageDescription']"));
		String acthome1 = getTextFromElement(homelabel1, "Home Label");
		checkText(expnewcontact, acthome1, "New Contact Label");
		
		String explastname="Indian";
		WebElement lastname = driver.findElement(By.id("name_lastcon2"));
		enterText(lastname, explastname, "Last name");
		
		
		WebElement firstname = driver.findElement(By.id("con4"));
		enterText(firstname, "Global Media", "Account name");
		
		WebElement saveNewbtn = driver.findElement(By.xpath("//*[@id=\"topButtonRow\"]/child::input[@value=\"Save & New\"]"));
		clickElement(saveNewbtn, "Save and New");
		
		WebElement recentaccname = driver.findElement(By.xpath("//div[@class='mruItem']/a[@accesskey=\"1\"]/span"));	
		String actrecentaccname =getTextFromElement(recentaccname, "Recent account name");

		if(explastname.equals(actrecentaccname))
		{
			SFContactlog.info("New Contact Edit Test case passed");
			SFContactlog.info("Save and New button test case passed");
		}
		else
		{
			SFContactlog.error("New Contact Edit Test case failed");
			SFContactlog.error("Save and New button test case failed");	
		}
	}


}
