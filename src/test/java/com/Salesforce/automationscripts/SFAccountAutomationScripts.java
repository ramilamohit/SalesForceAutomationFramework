package com.Salesforce.automationscripts;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.Salesforce.base.BaseTestSF;


public class SFAccountAutomationScripts extends BaseTestSF 
{
	protected static Logger SFAccountlog = LogManager.getLogger();

		 public static int checkAvailabeTab()
	 {
			List<WebElement> availableTabs = driver.findElements(By.xpath("//select[@id ='duel_select_0']/option[starts-with(text(),'A')]"));
			
			int availableTabsize = availableTabs.size();
			SFAccountlog.info(availableTabsize);
			
			int i=0;
			for(WebElement availableTab : availableTabs)
			{
				if(availableTab.getText().equalsIgnoreCase("Accounts"))
				{
					SFAccountlog.info("Accounts is in Available Tab");
					i++;
					break;
				}
			}
			
			if(i==0)
				SFAccountlog.info("Accounts is not in Available Tab");
			
			List<WebElement> SelectedTabs = driver.findElements(By.xpath("//select[@id ='duel_select_1']/option[starts-with(text(),'A')]"));
			
			int SelectedTabsize = SelectedTabs.size();
			SFAccountlog.info(SelectedTabsize);
			
			int j=0;
			for(WebElement selectedTab : SelectedTabs)
			{
				if(selectedTab.getText().equalsIgnoreCase("Accounts"))
				{
					SFAccountlog.info("Accounts is in Selected Tab");
					SFAccountlog.info("Accounts is already in Tab bar");
					j++;
					break;
				}
			}
			
			if(j==0)
				SFAccountlog.info("Accounts is not in Selected Tab");
			
			return j;
	 }
	
		 @Test
	protected void TC10CreateAccountTab() throws InterruptedException
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
		SFAccountlog.info(tabcount);
		
		int k=0;
		for(WebElement tab : tabs)
		{
			if(tab.getText().equalsIgnoreCase("Accounts"))
			{
				SFAccountlog.info("Accounts is in Tab bar");
				k++;
				break;
			}
		}
		
		if(k==0)
		
			SFAccountlog.info("Accounts is not in Tab bar");
		
		}
		else
		{
	//	closeBrowser();//closing after desired tab
		}
		}
		//closeBrowser();//closing after knowing tab is already there
	}
		 
		@Test 
public void TC10CreateAccount() throws InterruptedException
{
	loginSalesforce();
	String explabel = "Accounts";
	String explabel1 ="Home";

	//Thread.sleep(5000);
	WebElement accountslink = driver.findElement(By.partialLinkText("Accounts"));
	WebDriverWait wait = new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.visibilityOf(accountslink));
	clickElement(accountslink, "Accounts link");
	
	WebElement accountlabel = driver.findElement(By.xpath("//h1[text()='Accounts']"));
	String acctlabel = getTextFromElement(accountlabel, "Account label");
	checkText(explabel, acctlabel, "Account Label");
	
	WebElement homelabel = driver.findElement(By.xpath("//h2[text()=' Home']"));
	String homelabeltxt = getTextFromElement(homelabel, "Home label");
	checkText(explabel1, homelabeltxt, "Home Label");
	
	WebElement newbtn = driver.findElement(By.xpath("//input[@value=' New ']"));
	clickElement(newbtn, "New account");

	WebElement accname = driver.findElement(By.id("acc2"));
	enterText(accname, "Balakumar", " Account Name");
	
	WebElement acctype = driver.findElement(By.id("acc6"));

	String status =selectbyVisibility(acctype, "Technology Partner", "Account tyle");
	SFAccountlog.info(status);
	
	WebElement customerpriority = driver.findElement(By.id("00Nao000000NbOg"));

	String status1 =selectbyVisibility(customerpriority, "High", "Customer Priority");
	SFAccountlog.info(status1);
	
	WebElement save = driver.findElement(By.xpath("//input[@value=' Save ']"));
	clickElement(save, "Save New Account");	
	
	WebElement tophead = driver.findElement(By.xpath("//h2[@class='topName']"));
	String str = getTextFromElement(tophead, "New Account Name");
	checkText("Balakumar", str, "New Account created");
	SFAccountlog.info(str + " is the newly created Account Name");
	
	WebElement techpartner = driver.findElement(By.id("acc6_ileinner"));
	String str1 = getTextFromElement(techpartner, "Technology parter");
	checkText("Technology Partner", str1, "Technology parter");
	
	WebElement customerprioritytxt = driver.findElement(By.id("00Nao000000NbOg_ileinner"));
	String str2 = getTextFromElement(customerprioritytxt, "Customer Priority");
	checkText("High", str2, "Customer Priority");
	
	
}
   @Test
   
public void TC11Createnewview() throws InterruptedException
{
	loginSalesforce();
	String explabel = "Accounts";
	String explabel1 ="Home";
	String expviewname = "Bala1";

	Thread.sleep(5000);
	WebElement accountslink = driver.findElement(By.partialLinkText("Accounts"));
	WebDriverWait wait = new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.visibilityOf(accountslink));
	clickElement(accountslink, "Accounts link");
	
	WebElement accountlabel = driver.findElement(By.xpath("//h1[text()='Accounts']"));
	String acctlabel = getTextFromElement(accountlabel, "Account label");
	checkText(explabel, acctlabel, "Account Label");
	
	WebElement homelabel = driver.findElement(By.xpath("//h2[text()=' Home']"));
	String homelabeltxt = getTextFromElement(homelabel, "Home label");
	checkText(explabel1, homelabeltxt, "Home Label");
	
	
	List<WebElement> chkviewname = driver.findElements(By.xpath("//select[@id='fcf']/option"));
	
	int i=0;	
	for(WebElement view: chkviewname)
	{	

		
		String chkview = getTextFromElement(view, "View name");
		if(chkview.equalsIgnoreCase(expviewname))
		{
			i++;
		}
	}
	Thread.sleep(5000);
	if(i==0)
	{	
	WebElement createNewView = driver.findElement(By.linkText("Create New View"));
	clickElement(createNewView, "Create New View");
		
	WebElement viewname = driver.findElement(By.id("fname"));
	enterText(viewname, "Bala1", "View name");
	
	WebElement viewUniquename = driver.findElement(By.id("devname"));
	enterText(viewUniquename, "Priya", "View Unique name");
	
	WebElement save = driver.findElement(By.xpath("//input[@value=' Save ']"));
	clickElement(save, "Save New View");	
	Thread.sleep(2000);
	WebElement accviewname = driver.findElement(By.xpath("//select[@name='fcf']/option[@selected='selected']"));
	String accviewname1 = getTextFromElement(accviewname, "Account view list");
	checkText(expviewname, accviewname1, "Account view name matched");
	}
	else
	{
		SFAccountlog.info("View name is not unique");
	}
	
}
   
   @Test
   public void TC12Editview() throws InterruptedException
   {

		loginSalesforce();
		String explabel = "Accounts";
		String explabel1 ="Home";
		String expEditViewLabel = "Edit View";

		//Thread.sleep(5000);
		WebElement accountslink = driver.findElement(By.partialLinkText("Accounts"));
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(accountslink));
		clickElement(accountslink, "Accounts link");
		
		WebElement accountlabel = driver.findElement(By.xpath("//h1[text()='Accounts']"));
		String acctlabel = getTextFromElement(accountlabel, "Account label");
		checkText(explabel, acctlabel, "Account Label");
		
		WebElement homelabel = driver.findElement(By.xpath("//h2[text()=' Home']"));
		String homelabeltxt = getTextFromElement(homelabel, "Home label");
		checkText(explabel1, homelabeltxt, "Home Label");
		
		WebElement viewname = driver.findElement(By.xpath("//select[@id='fcf']"));			

		selectbyindex(viewname, 0, "View Name to edit");
		
		WebElement viewname1 = driver.findElement(By.xpath("//select[@name='fcf']/option[@selected='selected']"));					
		WebDriverWait wait3 = new WebDriverWait(driver,30);
		wait3.until(ExpectedConditions.visibilityOf(viewname1));	
		String chkview = getTextFromElement(viewname1, "View name to edit");
		
		WebElement editlink = driver.findElement(By.linkText("Edit"));
		clickElement(editlink, "Edit Link");
		
		WebElement editviewlabel = driver.findElement(By.xpath("//h2[text()=' Edit View']"));
		WebDriverWait wait1 = new WebDriverWait(driver,30);
		wait1.until(ExpectedConditions.visibilityOf(editviewlabel));
		
		String edithome = getTextFromElement(editviewlabel, "Edit view label");		
		checkText(expEditViewLabel, edithome, "Edit view");
		
		WebElement vieweditname = driver.findElement(By.id("fname"));
		String name = getAttributFromElement(vieweditname, "View Edit Name");
		
		checkText(chkview, name, "View Edit name matched ");
		
		enterText(vieweditname, "aana2", "New Edit name");
		
		WebElement fieldedit = driver.findElement(By.xpath("//select[@id='fcol1']"));
		String str =selectbyVisibility(fieldedit, "Account Name", "Field - Account name");
		SFAccountlog.info(str);
		
		WebElement operatoredit = driver.findElement(By.id("fop1"));
		String str1 =selectbyVisibility(operatoredit, "contains", "Operator - contains");
		SFAccountlog.info(str1);

		WebElement valueedit = driver.findElement(By.id("fval1"));
		enterText(valueedit, "a", "value 'a' ");	
		
		WebElement savebtn = driver.findElement(By.xpath("//input[contains(@value,'Save')]"));
		clickElement(savebtn, "Save");
		
		List<WebElement> accnamelist = driver.findElements(By.xpath("//table[@class=\"x-grid3-row-table\"]/tbody/tr/td[4]/div/a"));
		int len = accnamelist.size();
		int i =0;
		for(WebElement accname:accnamelist)
		{
			if(getTextFromElement(accname, "account name").contains("a"))
			{
				SFAccountlog.info("Account name: " + getTextFromElement(accname, "account name") + " contains the value 'a'");
				i++;
			}
			else
			{
				SFAccountlog.error("Account name: "+ getTextFromElement(accname, "account name") + "does not contains the value 'a'");
				
			}
		}
		if(i == len)
		{
			SFAccountlog.info("All account name contains the value 'a' test case passed");
		}else
		{
			SFAccountlog.error("All account name contains the value 'a' test case failed");

		}
			   
   }

   @Test
   public void TC13MergeAccounts() throws InterruptedException
   {
	   	loginSalesforce();
	   
		String explabel = "Accounts";
		String explabel1 ="Home";

		WebElement accountslink = driver.findElement(By.partialLinkText("Accounts"));
		WebDriverWait wait = new WebDriverWait(driver,100);
		wait.until(ExpectedConditions.visibilityOf(accountslink));
		clickElement(accountslink, "Accounts link");
		
		WebElement accountlabel = driver.findElement(By.xpath("//h1[text()='Accounts']"));
		String acctlabel = getTextFromElement(accountlabel, "Account label");
		checkText(explabel, acctlabel, "Account Label");
		
		WebElement homelabel = driver.findElement(By.xpath("//h2[text()=' Home']"));
		String homelabeltxt = getTextFromElement(homelabel, "Home label");
		checkText(explabel1, homelabeltxt, "Home Label");

		WebElement mergeacclink = driver.findElement(By.linkText("Merge Accounts"));
		clickElement(mergeacclink, "Merge account link");

		WebElement findacc = driver.findElement(By.id("srch"));
		enterText(findacc, "Prem", "Find Account name to merge");
		
		WebElement fdaccbtn = driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[4]/input[2]"));
		fdaccbtn.click();
		
		List<WebElement> checkbx = driver.findElements(By.xpath("//table[@class='list']/tbody/tr/th[@class=\" dataCell  booleanColumn\"]/input[1]"));
		SFAccountlog.info(" Number of accounts: "+ checkbx.size());
		
		String expacc1name="";
		int i=0;
		for(WebElement checkbox : checkbx)
		{
			if(checkbox.isSelected())
			{
				i++;
				SFAccountlog.info(i);
				SFAccountlog.info("*****");
				WebElement acc1name = driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[4]/div/div[1]/div/div[2]/table/tbody/tr[2]/td[1]"));
				SFAccountlog.info(acc1name.getText());
				expacc1name = acc1name.getText();

			}
			else
			{
				i++;
				SFAccountlog.info(i);
				checkbox.click();
				WebElement acc1name = driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[4]/div/div[1]/div/div[2]/table/tbody/tr[2]/td[1]"));
				SFAccountlog.info(acc1name.getText());
				expacc1name = acc1name.getText();
			}
			if(i==2)
			{
				break;
			}
		}
		try {
		WebElement nextbtn = driver.findElement(By.xpath("//div[@class=\"pbTopButtons\"]/input[@name=\"goNext\"]"));
		clickElement(nextbtn, "Next merge button");
	
		Thread.sleep(5000);

		
		WebElement error = driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[4]/span[1]"));
		
		WebDriverWait wait2 = new WebDriverWait(driver,60);
		wait2.until(ExpectedConditions.visibilityOf(error));
		
		
		String errormsg = getTextFromElement(error, "Error - not 2 accounts available");
		String experrormsg = "You must select two or three records to merge.";
		if(errormsg.equals(experrormsg))
		{
			SFAccountlog.info(errormsg);
		}
		}
		catch(Exception e)
		{
		WebElement mergebtn = driver.findElement(By.xpath("//div[@class=\"pbTopButtons\"]/input[contains(@value,'Merge')]"));
		clickElement(mergebtn, "Merge");
		Alert al1 = driver.switchTo().alert();
		String msg1 = al1.getText();
		SFAccountlog.info(msg1);
		String expmergemsg = "These records will be merged into one record using the selected values. Merging can't be undone. Proceed with the record merge?";
		SFAccountlog.info(expmergemsg);
						
		if(msg1.equals(expmergemsg))
		{
			al1.accept();
			
			WebElement accountlabel1 = driver.findElement(By.xpath("//h1[text()='Accounts']"));
			WebDriverWait wait1 = new WebDriverWait(driver,60);
			wait1.until(ExpectedConditions.visibilityOf(accountlabel1));
			
			
			String acctlabel1 = getTextFromElement(accountlabel1, "Account label");
			checkText(explabel, acctlabel1, "Account Label");
			
			WebElement homelabel1 = driver.findElement(By.xpath("//h2[text()=' Home']"));
			String homelabeltxt1 = getTextFromElement(homelabel1, "Home label");
			checkText(explabel1, homelabeltxt1, "Home Label");
						
			WebElement mergeaccname = driver.findElement(By.xpath("//div[@class='mruItem']/a[@accesskey=\"1\"]/span"));	
			String recentaccname =getTextFromElement(mergeaccname, "Recent account name");
			SFAccountlog.info(recentaccname);
			if(expacc1name.equals(recentaccname))
			{
				SFAccountlog.info("Account merged");
			}
			
		}
		else
		{
			al1.dismiss();
			SFAccountlog.info(msg1);
		}
		
		}
   }
   
   @Test
   public void TC14CreateAccountReport() throws InterruptedException
   {
	   	loginSalesforce();
		   
		String explabel = "Accounts";
		String explabel1 ="Home";

		WebElement accountslink = driver.findElement(By.partialLinkText("Accounts"));
		WebDriverWait wait = new WebDriverWait(driver,100);
		wait.until(ExpectedConditions.visibilityOf(accountslink));
		clickElement(accountslink, "Accounts link");
		
		WebElement accountlabel = driver.findElement(By.xpath("//h1[text()='Accounts']"));
		String acctlabel = getTextFromElement(accountlabel, "Account label");
		checkText(explabel, acctlabel, "Account Label");
		
		WebElement homelabel = driver.findElement(By.xpath("//h2[text()=' Home']"));
		String homelabeltxt = getTextFromElement(homelabel, "Home label");
		checkText(explabel1, homelabeltxt, "Home Label");
		
		WebElement reportlink = driver.findElement(By.xpath("//div[@class=\"lbBody\"]/ul/li[2]/a"));
		clickElement(reportlink, "Report link");
		
		String expHome = "Unsaved Report";
		WebElement homelabel1 = driver.findElement(By.xpath("//h2[text()='Unsaved Report']"));
		String homelabeltxt1 = getTextFromElement(homelabel1, "Unsaved Report Page");
		checkText(expHome, homelabeltxt1, "Unsaved Report Page Label");
		
		WebElement calimg =driver.findElement(By.xpath("//img[@id=\"ext-gen152\"]"));
		clickElement(calimg, "Calender img1 ");
		
		WebElement today = driver.findElement(By.id("ext-gen276"));
		clickElement(today, "Today");
		
		WebElement calimg1 = driver.findElement(By.id("ext-gen154"));
		clickElement(calimg1, "Calender img2");

		WebElement today1 = driver.findElement(By.id("ext-gen292"));
		clickElement(today1, "Today1");
		
		WebElement savebtn = driver.findElement(By.id("ext-gen49"));
		clickElement(savebtn, "Save");

		String reportname = "Report15";
		WebElement name = driver.findElement(By.id("saveReportDlg_reportNameField"));
		enterText(name,reportname , "Name");

		WebElement uniquename = driver.findElement(By.id("saveReportDlg_DeveloperName"));
		clickElement(uniquename, "Unique name");
				
		WebElement saverun = driver.findElement(By.id("ext-gen312"));
		
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveToElement(saverun).build().perform();
	
		clickElement(saverun, "Save and Run Report");
		
		Thread.sleep(5000);
				
		WebElement reportlable = driver.findElement(By.xpath("//h1[@class=\"noSecondHeader pageType\"]"));

		//WebDriverWait wait1 = new WebDriverWait(driver, 100);
		//wait1.until(ExpectedConditions.visibilityOf(reportlable));

		String reportpglb = getTextFromElement(reportlable, "Report label");
		checkText(reportname, reportpglb, "Report Page " + reportname +"Test case passed");
		
		
   }


}
