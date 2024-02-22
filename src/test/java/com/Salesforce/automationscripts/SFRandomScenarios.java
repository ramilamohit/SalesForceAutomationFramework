package com.Salesforce.automationscripts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.Salesforce.base.BaseTestSF;

public class SFRandomScenarios extends BaseTestSF
{
	protected static Logger SFRandomScenlog = LogManager.getLogger();

	@Test
public void TC33() throws InterruptedException
{
	loginSalesforce();
	
	Thread.sleep(5000);
	WebElement hometab = driver.findElement(By.xpath("//*[@id='home_Tab']/a"));
	clickElement(hometab, "Home Tab");
	
	Thread.sleep(5000);
	WebElement username = driver.findElement(By.xpath("//h1[@class='currentStatusUserName']/a"));
	SFRandomScenlog.info("xpath has a at end , so username is a link");
	SFRandomScenlog.info("Location of username : " + username.getLocation());
	
	int winheight = driver.manage().window().getSize().getHeight();
	SFRandomScenlog.info("Window height: " + winheight);
	int winwidth = driver.manage().window().getSize().getWidth();
	SFRandomScenlog.info("Window width: " + winwidth);
	
	int xPos = username.getLocation().getX();
	SFRandomScenlog.info("Username link x axis point: " + xPos);
	int yPos = username.getLocation().getY();
	SFRandomScenlog.info("Username link y axis point: " + yPos);
	
	int eleWidth = username.getSize().getWidth();
	SFRandomScenlog.info("Element height: " + eleWidth);
	
	int eleHeight = username.getSize().getHeight();
	SFRandomScenlog.info("Element weight: " + eleHeight);

	if(((xPos+eleWidth)<winwidth/2) && ((yPos+eleHeight)<winheight/2))
	{
		SFRandomScenlog.info("Username is in upper left side of the window");
		SFRandomScenlog.info("Username Link position test case passed");
	}
	else
	{
		SFRandomScenlog.error("Username is not in upper left side of the window");
		SFRandomScenlog.error("Username Link position test case failed");

	}

	clickElement(username, "User name Link");

	WebElement usernamepropg = driver.findElement(By.id("tailBreadcrumbNode"));
	String name = getTextFromElement(usernamepropg, "User name");
	SFRandomScenlog.info("User name :" + name);
		
	WebElement usermenu = driver.findElement(By.id("userNavButton"));
	clickElement(usermenu, "User menu drop down");
	
	WebElement myprofile = driver.findElement(By.xpath("//div[@id='userNav-menuItems']/a[@title=\'My Profile\']"));
	clickElement(myprofile, "My Profile");
	
	WebElement pagetle = driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']"));
	String name2 =getTextFromElement(pagetle, "Profile name");
	SFRandomScenlog.info("Profile name:" + name2);
	checkText(name2, name, "My profile and Home-- My profile");
		
}
	
	@Test
	public void TC34() throws InterruptedException 
	{
		loginSalesforce();
		
		Thread.sleep(5000);
		WebElement hometab = driver.findElement(By.xpath("//*[@id='home_Tab']/a"));
		clickElement(hometab, "Home Tab");
		
		Thread.sleep(5000);
		WebElement username = driver.findElement(By.xpath("//h1[@class='currentStatusUserName']/a"));
		clickElement(username, "User name Link");
		
		WebElement editpen = driver.findElement(By.xpath("//a[@class=\"contactInfoLaunch editLink\"]"));
		clickElement(editpen, "Edit Profile");
		
		WebElement iframe = driver.findElement(By.id("contactInfoContentId"));	
		driver.switchTo().frame(iframe);
		
		WebElement abouttab = driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));
		clickElement(abouttab, "About tab");
				
		WebElement firstname = driver.findElement(By.id("firstName"));
		String first = getAttributFromElement(firstname, "First name");
		
		String explastname = "ABCD";
		WebElement lastname = driver.findElement(By.id("lastName"));
		enterText(lastname,explastname , "Last name");
		
		WebElement saveall = driver.findElement(By.xpath("//input[@value='Save All']"));
		clickElement(saveall, "Save all");
		
		driver.switchTo().parentFrame();

		WebElement pagetle1 = driver.findElement(By.xpath("//*[@id=\"tailBreadcrumbNode\"]"));
		
		WebDriverWait wait = new WebDriverWait(driver,100);
		wait.until(ExpectedConditions.visibilityOf(pagetle1));
		
		String name3 =getTextFromElement(pagetle1, "Profile name");
		SFRandomScenlog.info("Updated User name: "+name3);
		
		String name4[] = name3.split(" ");
		for(String name5:name4)
		{
			if(name5.trim().equals(first.trim()))
			{
				SFRandomScenlog.info("First name: " + name5);
			}
			else
			{
				SFRandomScenlog.info("Last name: " + name5 );
				if(name5.equals(explastname))
				{
					SFRandomScenlog.info("Last name is updated Test case passed");
				}
				else
				{
					SFRandomScenlog.error("Last name is not updated Test case failed");
				}
			}
		}
		
		int winheight = driver.manage().window().getSize().getHeight();
		SFRandomScenlog.info("Window height: " + winheight);
		int winwidth = driver.manage().window().getSize().getWidth();
		SFRandomScenlog.info("Window width: " + winwidth);
		
		int xPos = pagetle1.getLocation().getX();
		SFRandomScenlog.info("Username x axis point: " + xPos);
		int yPos = pagetle1.getLocation().getY();
		SFRandomScenlog.info("Username y axis point: " + yPos);
		
		int eleWidth = pagetle1.getSize().getWidth();
		SFRandomScenlog.info("Element height: " + eleWidth);
		
		int eleHeight = pagetle1.getSize().getHeight();
		SFRandomScenlog.info("Element weight: " + eleHeight);

		if(((xPos+eleWidth)<winwidth/2) && ((yPos+eleHeight)<winheight/2))
		{
			SFRandomScenlog.info("Username is in upper left side of the window");
			SFRandomScenlog.info("Username position test case passed");
		}
		else
		{
			SFRandomScenlog.error("Username is not in upper left side of the window");
			SFRandomScenlog.error("Username  position test case failed");

		}
		
		WebElement usermenutxt = driver.findElement(By.xpath("//*[@id=\"userNavButton\"]/span"));
		String chkusername = getTextFromElement(usermenutxt,"user drop down menu");
		SFRandomScenlog.info(chkusername);
		
		WebElement usermenu = driver.findElement(By.id("userNavButton"));
		
		int xPos1 = usermenu.getLocation().getX();
		SFRandomScenlog.info("Username x axis point: " + xPos1);
		int yPos1 = usermenu.getLocation().getY();
		SFRandomScenlog.info("Username y axis point: " + yPos1);
		
		int eleWidth1 = usermenu.getSize().getWidth();
		SFRandomScenlog.info("Element height: " + eleWidth1);
		
		int eleHeight1 = usermenu.getSize().getHeight();
		SFRandomScenlog.info("Element weight: " + eleHeight1);

		SFRandomScenlog.info(((xPos1+eleWidth1)));
		SFRandomScenlog.info("Window Width midpoint " + winwidth/2);
		
		SFRandomScenlog.info(yPos1+eleHeight1);
		SFRandomScenlog.info("Window Height midpoint: " + winheight/2);
				
		if(((xPos1+eleWidth1)>winwidth/2) && ((yPos1+eleHeight1)<winheight/2))
		{
			SFRandomScenlog.info("Username menudropdown is in upper right side of the window");
			SFRandomScenlog.info("Username menudropdown position test case passed");
		}
		else
		{
			SFRandomScenlog.error("Username menudropdown is not in upper right side of the window");
			SFRandomScenlog.error("Username menudropdown position test case failed");

		}
			
	}
	
	@Test
	 public static int checkAvailabeTab()
	 {
			List<WebElement> availableTabs = driver.findElements(By.xpath("//select[@id ='duel_select_0']/option[starts-with(text(),'A')]"));
			
			int availableTabsize = availableTabs.size();
			SFRandomScenlog.info(availableTabsize);
			
			int i=0;
			for(WebElement availableTab : availableTabs)
			{
				if(availableTab.getText().equalsIgnoreCase("Accounts"))
				{
					SFRandomScenlog.info("Accounts is in Available Tab");
					i++;
					break;
				}
			}
			
			if(i==0)
				SFRandomScenlog.info("Accounts is not in Available Tab");
			
			List<WebElement> SelectedTabs = driver.findElements(By.xpath("//select[@id ='duel_select_1']/option[starts-with(text(),'A')]"));
			
			int SelectedTabsize = SelectedTabs.size();
			SFRandomScenlog.info(SelectedTabsize);
			
			int j=0;
			for(WebElement selectedTab : SelectedTabs)
			{
				if(selectedTab.getText().equalsIgnoreCase("Accounts"))
				{
					SFRandomScenlog.info("Accounts is in Selected Tab");
					SFRandomScenlog.info("Accounts is already in Tab bar");
					j++;
					break;
				}
			}
			
			if(j==0)
				SFRandomScenlog.info("Accounts is not in Selected Tab");
			
			return j;
	 }

	@Test
	protected void TC35() throws InterruptedException
	{
		loginSalesforce();
		Thread.sleep(3000);

		WebElement plusTab = driver.findElement(By.className("allTabsArrow"));
		clickElement(plusTab, "Tab Plus");
		Thread.sleep(3000);
		
		String expallTabPage = "All Tabs";
		WebElement allTablabel = driver.findElement(By.xpath("//h1[text()='All Tabs']"));
		String allTabPage = getTextFromElement(allTablabel, "All Tab Label");
		checkText(expallTabPage, allTabPage, "All Tab Page diaplay");

		WebElement customizeTab = driver.findElement(By.xpath("//input[contains(@class,'btnImportant')]"));
		clickElement(customizeTab, "Customize my tab");
		
		String expCustomizeMyTabPage = "Customize My Tabs";
		WebElement CustomizeMyTablabel = driver.findElement(By.xpath("//h1[text()='Customize My Tabs']"));
		String CustomizeMyTabPage = getTextFromElement(CustomizeMyTablabel, "All Tab Label");
		SFRandomScenlog.info(CustomizeMyTabPage);
		checkText(expCustomizeMyTabPage, CustomizeMyTabPage, "Customize My Tabs Page diaplay");

		Thread.sleep(3000);

		int j = checkAvailabeTab();
		if(j==0) 
		{
		
		WebElement dropdown = driver.findElement(By.id("duel_select_0"));
		String selectstatus = selectbyVisibility(dropdown, "Accounts", "Available Tabs");
		
		Thread.sleep(3000);
		if(selectstatus.equalsIgnoreCase("selected"))
		{
		WebElement addbtn = driver.findElement(By.xpath("//img[contains(@class,'rightArrowIcon')]"));
		clickElement(addbtn, "Add Available Tab to Select Tab");
		Thread.sleep(3000);
		
		WebElement save = driver.findElement(By.xpath("//input[@class=\"btn primary\"]"));
		clickElement(save,"New Tab Save ");
		
		Thread.sleep(3000);
	
		WebElement allTablabel2 = driver.findElement(By.xpath("//h1[text()='All Tabs']"));

		String allTabPage1 = getTextFromElement(allTablabel2, "All Tab Label");
		checkText(expallTabPage, allTabPage1, "All Tab Page diaplay");

		logoutSalesforce();
		Thread.sleep(3000);

		loginSalesforce();
		
		
		List<WebElement> tabs = driver.findElements(By.xpath("//ul[@id='tabBar']/li/a"));
		
		int tabcount = tabs.size();
		SFRandomScenlog.info(tabcount);
		
		int k=0;
		for(WebElement tab : tabs)
		{
			if(tab.getText().equalsIgnoreCase("Accounts"))
			{
				SFRandomScenlog.info("Accounts is in Tab bar");
				k++;
				break;
			}
		}
		
		if(k==0)
		
			SFRandomScenlog.info("Accounts is not in Tab bar");
		
		}
		else
		{
	//	closeBrowser();//closing after desired tab
		}
		}
		//closeBrowser();//closing after knowing tab is already there

	}
	
	@Test
public void TC36() throws InterruptedException
{
	loginSalesforce();

	Thread.sleep(5000);
	WebElement hometab = driver.findElement(By.xpath("//*[@id='home_Tab']/a"));
	clickElement(hometab, "Home Tab");
	
	WebElement username = driver.findElement(By.xpath("//h1[@class='currentStatusUserName']/a"));
	String expusername = getTextFromElement(username, "Username Link");
	
	WebElement time = driver.findElement(By.xpath("//div[@class='content']//descendant::a[2]"));
	String dattime = getTextFromElement(time, "current date link");
	SFRandomScenlog.info(dattime);
	
	SimpleDateFormat curtime = new SimpleDateFormat("EEEE MMMM dd, yyyy");
	Date thisDate = new Date();	
	SFRandomScenlog.info(curtime.format(thisDate));
	String today = curtime.format(thisDate);
	checkText(today, dattime, "Day Month Date, Year Format");
	
	WebElement datelink = driver.findElement(By.xpath("//*[@class=\"pageDescription\"]/a"));
	clickElement(datelink, "Date Link");
	
	WebElement eightpm = driver.findElement(By.partialLinkText("8:00 PM"));
	clickElement(eightpm, "8 pm");
	
	WebElement currentElement = driver.switchTo().activeElement();
	String str = currentElement.getTagName();
	SFRandomScenlog.info(str);
	
	String exppgtile ="Calendar";
	WebElement pgtile = driver.findElement(By.xpath("//h1[@class=\"pageType\"]"));
	String actpgtile = getTextFromElement(pgtile, "Page Title");
	checkText(exppgtile, actpgtile, "Page Title");
	
	String expevtile ="New Event";
	WebElement evttile = driver.findElement(By.xpath("//h2[@class=\"pageDescription\"]"));
	String actevtile = getTextFromElement(evttile, "Event");
	checkText(expevtile, actevtile, "Event title");
	
	WebElement subjectcombo = driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img"));
	clickElement(subjectcombo, "Subject combo");
	
	String parentwindow = driver.getWindowHandle();
	String title = getTitle(driver);
	SFRandomScenlog.info("Title of parent window: "+ title );
	
	Thread.sleep(5000);
	
	Set<String> handles = driver.getWindowHandles();
	SFRandomScenlog.info("Number of windows: " +handles.size());
	for(String handle: handles)
	{

		if(!handle.equals(parentwindow))
		{
			driver.switchTo().window(handle);
			String title1 = getTitle(driver);
			SFRandomScenlog.info("Title of current window:"+ title1);
			
			WebElement other = driver.findElement(By.xpath("//div[@class='choicesBox tertiaryPalette brandSecondaryBrd']/ul/li[5]/a"));
			clickElement(other, "Other");
		}
	}
	driver.switchTo().window(parentwindow);
	int count = driver.getWindowHandles().size();
	if(count==1)
	{
		SFRandomScenlog.info("ComboBox window closed Test case passed");
	}
	else
	{
		SFRandomScenlog.error("ComboBox window closed Test case failed");

	}
	Thread.sleep(5000);
	
	WebElement subtxt1 = driver.findElement(By.id("evt5"));
	String subtxt = getAttributFromElement(subtxt1, "Subject Combo");
	checkText("Other", subtxt, "Subject combo text");
 
	WebElement endtm = driver.findElement(By.id("EndDateTime_time"));
	clickElement(endtm, "End Time");
	
	
	 List<WebElement> dropdownendtime = driver.findElements(By.xpath("//div[@id='simpleTimePicker']/div[text()='8:30 PM']//following::div"));
		
		SFRandomScenlog.info("Shown time count = " + dropdownendtime.size());
		
		WebElement firstendtime = dropdownendtime.get(0);
		WebElement lastendtime = dropdownendtime.get(dropdownendtime.size()-1);
		String firstendtimetxt = getTextFromElement(firstendtime, "First time shown in dropdown endtime");
		String lastendtimetxt = getTextFromElement(lastendtime, "Last time shown in dropdown endtime");
		
		if(firstendtimetxt.equalsIgnoreCase("9:00 PM"))
		{
			SFRandomScenlog.info("Time shown from 9:00 PM Testcase is passed");
		}
		if(lastendtimetxt.equalsIgnoreCase("11:30 PM"))
		{
			SFRandomScenlog.info("Time shown till 11:30 PM Testcase is passed");

		}
		
		WebElement selendtime = driver.findElement(By.xpath("//div[@id='simpleTimePicker']/div[text()='9:00 PM']"));
		clickElement(selendtime, "Select 9:00 PM");
		
		WebElement save = driver.findElement(By.xpath("//*[@id='topButtonRow']/input[@value=' Save ']"));
		clickElement(save, "Save");
		
		WebElement header = driver.findElement(By.xpath("//h1[@class='pageType']"));
		String headertxt = getTextFromElement(header, "Header name");
		SFRandomScenlog.info(headertxt);
		if(headertxt.contains(expusername))
		{
			SFRandomScenlog.info("Calender Page for " + expusername + " displayed Test case passed");
		}
		else
		{
			SFRandomScenlog.error("Calender Page for " + expusername + " displayed Test case failed");
		}
				
		String explink = "Other";
		List<WebElement> othereles = driver.findElements(By.xpath("//div[@class='multiLineEventBlock dragContentPointer']/descendant::a"));
		
		WebElement lastElement = othereles.get(othereles.size()-1);
		String lastelestr = lastElement.getText();
		
		checkText(explink, lastelestr, "Other Link");
				
		Actions action = new Actions(driver);	
		action.moveToElement(lastElement).build().perform();

		String str1 = lastElement.getText();
		checkText(explink, str1, "mouseover text");
				
}
	@Test
 protected void TC37() throws InterruptedException
 {
	 loginSalesforce();

		Thread.sleep(5000);
		WebElement hometab = driver.findElement(By.xpath("//*[@id='home_Tab']/a"));
		clickElement(hometab, "Home Tab");
		
		WebElement username = driver.findElement(By.xpath("//h1[@class='currentStatusUserName']/a"));
		String expusername = getTextFromElement(username, "Username Link");
		
		WebElement time = driver.findElement(By.xpath("//div[@class='content']//descendant::a[2]"));
		String dattime = getTextFromElement(time, "current date link");
		SFRandomScenlog.info(dattime);
		
		SimpleDateFormat curtime = new SimpleDateFormat("EEEE MMMM dd, yyyy");
		Date thisDate = new Date();	
		SFRandomScenlog.info(curtime.format(thisDate));
		String today = curtime.format(thisDate);
		checkText(today, dattime, "Day Month Date, Year Format");
		
		WebElement datelink = driver.findElement(By.xpath("//*[@class=\"pageDescription\"]/a"));
		clickElement(datelink, "Date Link");
		
		WebElement fourpm = driver.findElement(By.partialLinkText("4:00 PM"));
		clickElement(fourpm, "4 pm");
		
		String exppgtile ="Calendar";
		WebElement pgtile = driver.findElement(By.xpath("//h1[@class=\"pageType\"]"));
		String actpgtile = getTextFromElement(pgtile, "Page Title");
		checkText(exppgtile, actpgtile, "Page Title");
		
		String expevtile ="New Event";
		WebElement evttile = driver.findElement(By.xpath("//h2[@class=\"pageDescription\"]"));
		String actevtile = getTextFromElement(evttile, "Event");
		checkText(expevtile, actevtile, "Event title");
		
		WebElement subjectcombo = driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img"));
		clickElement(subjectcombo, "Subject combo");
		
		String parentwindow = driver.getWindowHandle();
		String title = getTitle(driver);
		SFRandomScenlog.info("Title of parent window: "+ title );
		
		Thread.sleep(5000);
		
		Set<String> handles = driver.getWindowHandles();
		SFRandomScenlog.info("Number of windows: " +handles.size());
		int count1 = handles.size();
		for(String handle: handles)
		{
			if(count1>3)
			{
				driver.switchTo().window(handle);
				driver.close();
			}

			else if(!handle.equals(parentwindow))
			{
				driver.switchTo().window(handle);
				String title1 = getTitle(driver);
				SFRandomScenlog.info("Title of current window:"+ title1);
				
				WebElement other = driver.findElement(By.xpath("//div[@class='choicesBox tertiaryPalette brandSecondaryBrd']/ul/li[5]/a"));
				clickElement(other, "Other");
			}
			
		}
		
		driver.switchTo().window(parentwindow);
		int count = driver.getWindowHandles().size();
		if(count==1)
		{
			SFRandomScenlog.info("ComboBox window closed Test case passed");
		}
		else
		{
			SFRandomScenlog.error("ComboBox window closed Test case failed");

		}
		Thread.sleep(5000);
		
		WebElement subtxt1 = driver.findElement(By.id("evt5"));
		String subtxt = getAttributFromElement(subtxt1, "Subject Combo");
		checkText("Other", subtxt, "Subject combo text");
	 
		WebElement endtm = driver.findElement(By.id("EndDateTime_time"));
		clickElement(endtm, "End Time");
		
		
		 List<WebElement> dropdownendtime = driver.findElements(By.xpath("//div[@id='simpleTimePicker']/div[text()='4:30 PM']//following::div"));
			
			SFRandomScenlog.info("Shown time count = " + dropdownendtime.size());
			
			WebElement firstendtime = dropdownendtime.get(0);
			WebElement lastendtime = dropdownendtime.get(dropdownendtime.size()-1);
			String firstendtimetxt = getTextFromElement(firstendtime, "First time shown in dropdown endtime");
			String lastendtimetxt = getTextFromElement(lastendtime, "Last time shown in dropdown endtime");
			
			if(firstendtimetxt.equalsIgnoreCase("5:00 PM"))
			{
				SFRandomScenlog.info("Time shown from 5:00 PM Testcase is passed");
			}
			if(lastendtimetxt.equalsIgnoreCase("11:30 PM"))
			{
				SFRandomScenlog.info("Time shown till 11:30 PM Testcase is passed");

			}
			
			WebElement sevenpm = driver.findElement(By.id("timePickerItem_38"));
			clickElement(sevenpm, "7 pm dropdown");
			
			WebElement checkrecurrence = driver.findElement(By.id("IsRecurrence"));
			checkboxclick(checkrecurrence, "Recurrence");
			
			WebElement frequencylabel = driver.findElement(By.xpath("//div[@id=\"recpat\"]/descendant::td[@class=\"labelCol\"]/label"));
			if(frequencylabel.isDisplayed())
			{
				SFRandomScenlog.info("Frequency in Recurrence is displayed test case passed");
			}
			else
			{
				SFRandomScenlog.error("Frequency in Recurrence is displayed test case failed");

			}
			
			WebElement recStartlabel = driver.findElement(By.xpath("//*[@id=\"recpat\"]/table/tbody/tr[2]/td[1]/label"));
			if(recStartlabel.isDisplayed())
			{
				SFRandomScenlog.info("Recurrence start label is displayed test case passed");
			}
			else
			{
				SFRandomScenlog.error("Recurrence start label is displayed test case failed");

			}

			WebElement recEndlabel = driver.findElement(By.xpath("//*[@id=\"recpat\"]/table/tbody/tr[2]/td[1]/label"));
			if(recEndlabel.isDisplayed())
			{
				SFRandomScenlog.info("Recurrence end label is displayed test case passed");
			}
			else
			{
				SFRandomScenlog.error("Recurrence end label is displayed test case failed");

			}

			WebElement chkWeekly = driver.findElement(By.id("rectypeftw"));
			checkboxclick(chkWeekly, "Weekly");
			
			WebElement enddate = driver.findElement(By.id("RecurrenceEndDateOnly"));
			clickElement(enddate, "End date");
			
			Thread.sleep(5000);
								
			Calendar cal = Calendar.getInstance();
			int j = cal.get(Calendar.DAY_OF_MONTH);
			SFRandomScenlog.info(j);
		    cal.add(Calendar.DAY_OF_YEAR, +14);
		    
		    
		    Date after = cal.getTime();
		    
		    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		    String let = formatter.format(after);
		    SFRandomScenlog.info(let);

		    String monafter = new SimpleDateFormat("MMMM").format(after);
			SFRandomScenlog.info(monafter);	
			Thread.sleep(5000);

		    String yearafter = new SimpleDateFormat("YYYY").format(after);
			SFRandomScenlog.info(yearafter);	

			String dateafter = new SimpleDateFormat("dd").format(after);
			
	
			SFRandomScenlog.info(dateafter);	

			WebElement calmonth = driver.findElement(By.id("calMonthPicker"));
			clickElement(calmonth, "Calculator month");
			selectbyVisibility(calmonth, monafter, "Month after 14 days");
			
			WebElement calyear = driver.findElement(By.id("calYearPicker"));
			clickElement(calyear, "Calculator Year");
			selectbyVisibility(calyear, yearafter, "Year after 14 days");
			
			WebElement afterdays = driver.findElement(By.xpath("//table[@class='calDays']/tbody/tr/td[text()='"+dateafter+"']"));
		
			WebDriverWait wait6 = new WebDriverWait(driver,200);
			wait6.until(ExpectedConditions.visibilityOf(afterdays));
					
			Actions action = new Actions(driver);
			action.moveToElement(afterdays).build().perform();			
			clickElement(afterdays, "Date after 14 days");
			
			WebElement save = driver.findElement(By.xpath("//*[@id='topButtonRow']/input[@value=' Save ']"));
			clickElement(save, "Save");
		
			WebElement header = driver.findElement(By.xpath("//h1[@class='pageType']"));
			String headertxt = getTextFromElement(header, "Header name");
			SFRandomScenlog.info(headertxt);
			if(headertxt.contains(expusername))
			{
				SFRandomScenlog.info("Calender Page for day view " + expusername + " displayed Test case passed");
			}
			else
			{
				SFRandomScenlog.error("Calender Page for day view" + expusername + " displayed Test case failed");
			}
					
			String explink = "Other";
			List<WebElement> othereles = driver.findElements(By.xpath("//div[@class='multiLineEventBlock dragContentPointer']/descendant::a"));
			
			WebElement lastElement = othereles.get(othereles.size()-1);
			String lastelestr = lastElement.getText();
			
			checkText(explink, lastelestr, "Other Link");
					
			Actions action1 = new Actions(driver);	
			action1.moveToElement(lastElement).build().perform();

			String str1 = lastElement.getText();
			checkText(explink, str1, "mouseover text");
			
			WebElement monthview = driver.findElement(By.className("monthViewIcon"));
			clickElement(monthview, "Month view Icon");

			WebElement header1 = driver.findElement(By.xpath("//h1[@class='pageType']"));
			String headertxt1 = getTextFromElement(header1, "Header name");
			SFRandomScenlog.info(headertxt1);
			if(headertxt1.contains(expusername))
			{
				SFRandomScenlog.info("Calender Page for month view" + expusername + " displayed Test case passed");
			}
			else
			{
				SFRandomScenlog.error("Calender Page for month view" + expusername + " displayed Test case failed");
			}
			
			String expeventtxt ="Other";
			WebElement othertoday = driver.findElement(By.xpath("//table[@class='calendarMonthView secondaryPalette']/tbody/tr[4]/td[4]/div/following-sibling::div/a"));
			String acteventtxt = getTextFromElement(othertoday, "Event Link Text");
			checkText(expeventtxt, acteventtxt, "Event Link Text");
			
 }

}
