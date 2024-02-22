package com.Salesforce.automationscripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Salesforce.base.BaseTestSF;

public class SFUserMenuDropdown extends BaseTestSF
{
	protected static Logger SFUserMenulog = LogManager.getLogger();

	@Test
	
	public void TC05SelectUserMenu()
	{
		loginSalesforce();
		
		String usermenulst1 ="My Profile";
		String usermenulst2 ="My Settings";
		String usermenulst3 ="Developer Console";
		String usermenulst4 ="Switch to Lightning Experience";
		String usermenulst5 ="Logout";
		
		ArrayList<String> listuser = new ArrayList<String>();
		listuser.add(usermenulst1);
		listuser.add(usermenulst2);
		listuser.add(usermenulst3);
		listuser.add(usermenulst4);
		listuser.add(usermenulst5);
			
		WebElement usermenu = driver.findElement(By.id("userNavButton"));
		waitforVisibility(usermenu,80,"Usermenu dropdown");
		clickElement(usermenu, "User menu drop down");
		
		List<WebElement> usermenulist = driver.findElements(By.xpath("//div[@id='userNav-menuItems']/a"));
		ArrayList<String> listuser1 = new ArrayList<String>();

		for(WebElement usermenult: usermenulist)
		{
			String str =getTextFromElement(usermenult, "User Menu drop down option");
			listuser1.add(str);
		}
		
		checkList(listuser, listuser1, "User Navigation Dropdown");
		Assert.assertEquals(listuser1, listuser, "Usermenu Dropdown");
				
	}

	@Test
	public void TC06MyProfile() throws InterruptedException, AWTException
	{
		loginSalesforce();
		
		implicitwait(60);

		WebElement username = driver.findElement(By.xpath("//span[@id='userNavLabel']"));		
		String name1 = getTextFromElement(username, "User Name");
		
		WebElement usermenu = driver.findElement(By.id("userNavButton"));
		waitforVisibility(usermenu,40,"Usermenu dropdown");		
		clickElement(usermenu, "User menu drop down");
		
		WebElement myprofile = driver.findElement(By.xpath("//div[@id='userNav-menuItems']/a[@title=\'My Profile\']"));
		clickElement(myprofile, "My Profile");
		
		WebElement pagetle = driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']"));
		String name2 =getTextFromElement(pagetle, "Profile name");
		checkText(name1.trim(), name2.trim(), "Profile name Page display");
		
		WebElement edit = driver.findElement(By.xpath("//img[@title='Edit Profile']"));
		clickElement(edit, "Edit Pen");
		
		WebElement iframe = driver.findElement(By.id("contactInfoContentId"));			
		switchToFrame(iframe,"Edit Contact Info");
		
		String expabout = "About";
		WebElement about = driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));
		String actabout = getTextFromElement(about, "About Tab");
		checkText(expabout.trim(), actabout.trim(),"About Tab");
		
		String expcontact = "Contact";
		WebElement contact = driver.findElement(By.xpath("//*[@id=\"contactTab\"]/a"));
		String actcontact = getTextFromElement(contact, "Contact Tab");
		checkText(expcontact.trim(), actcontact.trim(), "Contact Tab");
		
		clickElement(about, "About Tab");
		
		WebElement firstname = driver.findElement(By.id("firstName"));
		String first = getAttributFromElement(firstname, "First name");
		
		String explastname = "Bala1";
		WebElement lastname = driver.findElement(By.id("lastName"));
		enterText(lastname, explastname, "Lastname");
				
		WebElement saveallbtn = driver.findElement(By.xpath("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]"));
		clickElement(saveallbtn, "Save all");
		
		switchToParentFrame("Edit contact info frame");

		WebElement pagetle1 = driver.findElement(By.xpath("//*[@id=\"tailBreadcrumbNode\"]"));
				
		waitforVisibility(pagetle1, 100, "Profile name");
		
		String name3 =getTextFromElement(pagetle1, "Profile name");
		
		String name4[] = name3.split(" ");
		for(String name5:name4)
		{
			if(name5.trim().equals(first.trim()))
			{
				SFUserMenulog.info("First name of the user: " + name5);
				extentReport.logTestInfo("First name of the user: " + name5);
			}
			else
			{
				SFUserMenulog.info("Last name of the user: " + name5);
				extentReport.logTestInfo("Last name of the user: " + name5);

				if(name5.equals(explastname))
				{
					SFUserMenulog.info("Last name is updated");
					extentReport.logTestInfo("Last name is updated ");

				}
				else
				{
					SFUserMenulog.error("Last name is not updated ");
					extentReport.logTestFailed("Last name is not updated ");

				}
			}
		}
		
		WebElement filelk = driver.findElement(By.xpath("//a[@id='publisherAttachContentPost']"));
		clickElement(filelk, "File link");
		
		WebElement uploadlk = driver.findElement(By.xpath("//a[@id='chatterUploadFileAction']"));
		clickElement(uploadlk, "Upload a file");	
		
		WebElement choosefile = driver.findElement(By.id("chatterFile"));
		//choosefile.sendKeys("C:\\Users\\ramil\\Pictures\\Camera Roll\\image_6487327");
			
		waitforVisibility(choosefile, 30, "choose file");
		
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", choosefile);
		
		StringSelection stringSelection = new StringSelection("C:\\Users\\ramil\\Pictures\\Camera Roll\\image_6487327");
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
		
		Thread.sleep(5000);
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
			

		WebElement sharebtn = driver.findElement(By.id("publishersharebutton"));
		waitforVisibility(sharebtn, 30, "Share button");

		clickElement(sharebtn, "Share");
	
	}

	@Test
	public void TC07MySettings() throws InterruptedException
	{
		loginSalesforce();
		
		WebElement username = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
		waitforVisibility(username, 30, "User name");
		
		WebElement usermenu = driver.findElement(By.id("userNavButton"));
		clickElement(usermenu, "User menu drop down");
		
		WebElement mysettings = driver.findElement(By.xpath("//div[@id='userNav-menuItems']/a[@title='My Settings']"));
		clickElement(mysettings, "My Settings");
		
		WebElement personalinfo =driver.findElement(By.xpath("//div[@id='PersonalInfo']/a"));
		clickElement(personalinfo, "Personal Info");
		
		WebElement loginHistory = driver.findElement(By.xpath("//div[@id='PersonalInfo_child']/div[8]/a"));
		clickElement(loginHistory, "Login and History");
		
		String explabel = "Login History";
		WebElement label = driver.findElement(By.xpath("//h1[@class=\"noSecondHeader pageType\"]"));
		String labelstr = getTextFromElement(label, "Label");
		
		checkText(explabel, labelstr, "Login History");
		
		WebElement displaylayout = driver.findElement(By.xpath("//div[@id='DisplayAndLayout']/a"));
		clickElement(displaylayout, "Display and Layout");
		
		WebElement customMyTabs = driver.findElement(By.linkText("Customize My Tabs"));
		clickElement(customMyTabs, "Custom My Tabs");
		
		WebElement customApp = driver.findElement(By.id("p4"));
		selectbyVisibility(customApp, "Salesforce Chatter", "Custom App");
		
		String expSelectedcustomapp ="Salesforce Chatter";
		WebElement selectedcustomapp = driver.findElement(By.xpath("//select[@id='p4']/option[@selected='selected']"));
		String actSelectedcustomapp = getTextFromElement(selectedcustomapp, "Custom App");
		checkText(expSelectedcustomapp, actSelectedcustomapp, "Selected Custom App");
		
		WebElement email = driver.findElement(By.xpath("//div[@id='EmailSetup']/a"));
		clickElement(email, "Email");
		
		WebElement emailsetting = driver.findElement(By.id("EmailSettings_font"));
		clickElement(emailsetting, "My Email Setting");
		
		WebElement emailname = driver.findElement(By.id("sender_name"));
		enterText(emailname, "Ramila 1", "Email name");
		
		WebElement emailaddress = driver.findElement(By.id("sender_email"));
		enterText(emailaddress, "ramilabala@gmail.com", "Email Address");	
		
		WebElement automaticBcc = driver.findElement(By.id("auto_bcc1"));
		checkboxclick(automaticBcc, "Automatic Bcc");
		
		WebElement save = driver.findElement(By.xpath("//input[@value=\' Save \']"));
		clickElement(save, "save");
		
		WebElement myEmailSetting = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']"));
		String label1 = getTextFromElement(myEmailSetting, "My Email Setting Label");
		BaseClasslog.info(label1);
		
		WebElement msg = driver.findElement(By.className("messageText"));
		String label2 = getTextFromElement(msg, "My email setting");
		BaseClasslog.info(label2);
		
		WebElement calRemind = driver.findElement(By.xpath("//div[@id='CalendarAndReminders']/a"));
		clickElement(calRemind, "Calender and Reminder");
		
		WebElement actyRemind = driver.findElement(By.xpath("//a[@id='Reminders_font']"));
		clickElement(actyRemind, "Activity Remainder");
		
		WebElement openRemainder = driver.findElement(By.id("testbtn"));
		clickElement(openRemainder, "Open a test Remainder");
		
		String parentwindow = getParentWindow();
		System.out.println(parentwindow);
		String title = getTitle(driver);
		BaseClasslog.info("Title of parent window: "+ title );
		
		Thread.sleep(5000);
		
		Set<String> handles = getWindows();
		BaseClasslog.info("Number of windows: " +handles.size());
		for(String handle: handles)
		{

			if(!handle.equals(parentwindow))
			{
				driver.switchTo().window(handle);
				String title1 = getTitle(driver);
				BaseClasslog.info("Title of current window:"+ title1);
				
				String expreminder = "Sample Event.";
				WebElement subject = driver.findElement(By.xpath("//*[@id=\"summary0\"]/div"));
				String actreminder = getTextFromElement(subject, "Suject").trim();
				BaseClasslog.info("Popup window Text "+actreminder);
				checkText(expreminder.trim(), actreminder, "Reminder Page label");
				
				BaseClasslog.info("Reminder Window Display Test case passed");
				extentReport.logTestpassed("Reminder Window Display Test case passed");
			}
		}
		
	}
	
	
	@Test
	public void TC08DeveloperConsole() throws InterruptedException
	{
		loginSalesforce();
		
		WebElement username = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
		waitforVisibility(username, 60, "User name");
		String name1 = getTextFromElement(username, "User Name");
		BaseClasslog.info("User name: "+ name1);
		
		WebElement usermenu = driver.findElement(By.id("userNavButton"));
		clickElement(usermenu, "User menu drop down");
		
		WebElement devConsole = driver.findElement(By.xpath("//div[@id='userNav-menuItems']/a[@title='Developer Console (New Window)']"));
		clickElement(devConsole, "Developer Console");
		
		Thread.sleep(5000);
		String parentwindowhandle = getParentWindow();
		
		Set<String> handles = getWindows();
		BaseClasslog.info("windows count after switched to Developer Console: " + handles.size());
		for(String handle:handles)
		{
			if(!handle.equals(parentwindowhandle))
			{
				driver.switchTo().window(handle);
				String title =  getTitle(driver);
				BaseClasslog.info("Title of current window: "+title);
				extentReport.logTestInfo("Title of current window: "+title);
			}
		}
		
		driver.close();
		driver.switchTo().window(parentwindowhandle);
		Set<String> handles1 = getWindows();
		if(handles1.size()==1)
		{

		BaseClasslog.info("windows count after closing developer console window: " + handles1.size());
		String title1 = getTitle(driver);
		BaseClasslog.info("Title of current window: "+ title1);
		extentReport.logTestpassed("Title of current window: "+ title1);

		}
		else
		{
			BaseClasslog.error("Developer window is not closed");
			extentReport.logTestFailed("Developer window is not closed");
		}	
				
	}

	@Test
	public void TC09LogOut() throws InterruptedException
	{
		loginSalesforce();
		implicitwait(30);

		String usermenulst1 ="My Profile";
		String usermenulst2 ="My Settings";
		String usermenulst3 ="Developer Console";
		String usermenulst4 ="Switch to Lightning Experience";
		String usermenulst5 ="Logout";

		ArrayList<String> listuser = new ArrayList<String>();
		listuser.add(usermenulst1);
		listuser.add(usermenulst2);
		listuser.add(usermenulst3);
		listuser.add(usermenulst4);
		listuser.add(usermenulst5);

		WebElement usermenu = driver.findElement(By.id("userNavButton"));
		clickElement(usermenu, "User menu drop down");
		
		List<WebElement> usermenulist = driver.findElements(By.xpath("//div[@id='userNav-menuItems']/a"));
		ArrayList<String> listuser1 = new ArrayList<String>();

		for(WebElement usermenult: usermenulist)
		{
			String str =getTextFromElement(usermenult, "User Menu drop down option");
			listuser1.add(str);
		}
		
		checkList(listuser, listuser1, "UserMenu Navigation dropdown");
		WebElement logoutlink = driver.findElement(By.linkText("Logout"));

		String expLgTitle = "Login | Salesforce"; 	

		if(logoutlink.isDisplayed())
		{
			clickElement(logoutlink, "Logout Link");

			waitforTitle(60, "Login", "Login Page");
			String lgntitle = getTitle(driver);

			checkText(expLgTitle,lgntitle,"Login Page display");			
			BaseClasslog.info("Logout Test has passed");
			extentReport.logTestInfo("Logout Test has passed");
		}
		else
		{
			BaseClasslog.error("Logout Test has failed");
			extentReport.logTestFailed("Logout Test has failed");
		}


}
	

}
