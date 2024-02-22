package com.Salesforce.automationscripts;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.Salesforce.base.BaseTestSF;

public class SFOptyAutomationScripts extends BaseTestSF {

	protected static Logger SFOptylog = LogManager.getLogger();

	@Test
	protected void TC15_OpportunityView() 
	{
		
		String expOptyviewtxt1 = "All Opportunities";
		String expOptyviewtxt2 = "Closing Next Month";
		String expOptyviewtxt3 = "Closing This Month";
		String expOptyviewtxt4 = "My Opportunities";
		String expOptyviewtxt5 = "New Last Week";
		String expOptyviewtxt6 = "New This Week";
		String expOptyviewtxt7 = "Opportunity Pipeline";
		String expOptyviewtxt8 = "Private";
		String expOptyviewtxt9 = "Recently Viewed Opportunities";
		String expOptyviewtxt10 = "Won";

		LinkedList<String> expOptyviewtext =  new LinkedList<String>();

		expOptyviewtext.add(expOptyviewtxt1);
		expOptyviewtext.add(expOptyviewtxt2);
		expOptyviewtext.add(expOptyviewtxt3);
		expOptyviewtext.add(expOptyviewtxt4);
		expOptyviewtext.add(expOptyviewtxt5);
		expOptyviewtext.add(expOptyviewtxt6);
		expOptyviewtext.add(expOptyviewtxt7);
		expOptyviewtext.add(expOptyviewtxt8);
		expOptyviewtext.add(expOptyviewtxt9);
		expOptyviewtext.add(expOptyviewtxt10);


		int  j= 0;
		String[] s1 = new String[expOptyviewtext.size()];

		for(String expOptyviewtt:expOptyviewtext)
		{
			s1[j] = expOptyviewtt;
			j++;
		}


		loginSalesforce();
		
		WebElement optytab = driver.findElement(By.linkText("Opportunities"));
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(optytab));
		
		clickElement(optytab, "Opportunities tab"); 
		
		List<WebElement> optyview = driver.findElements(By.xpath("//select[@id='fcf']//child::option"));
		
		SFOptylog.info("optyview count = " + optyview.size());
		
		String[] s2 = new String[optyview.size()];
		int i=0;
		for(WebElement optyviewdropdown : optyview)
		{
			
			String actualOptyviewtxt = getTextFromElement(optyviewdropdown, "Opportunity view option" + i);
			s2[i] = actualOptyviewtxt;
			i++;
		}
		
		int s1len = s1.length;
		int s2len = s2.length;
		SFOptylog.info(s1len + " " + s2len);
		SFOptylog.info(s1[1]+ "  "+s2[1]);
		boolean curious = s1[1].equalsIgnoreCase(s2[1]);
		SFOptylog.info(curious);//true
		boolean curious2 = s1[1].equals(s2[1]);
		SFOptylog.info(curious2);//true
		boolean learn = s1.equals(s2);
		SFOptylog.info(learn);//false
		
		for(int check=0;check<optyview.size();check++)
		{
			checkText(s1[check], s2[check],s2[check]+ " Opportunity dropdown view");
		}
		
		logoutSalesforce();
		
	}
	
	@Test
	protected void TC16CreateNewOpty() throws InterruptedException 
	{
		loginSalesforce();
		Thread.sleep(5000);

		WebElement optytab = driver.findElement(By.linkText("Opportunities"));
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(optytab));		
		clickElement(optytab, "Opportunities tab"); 
	
		WebElement newbtn = driver.findElement(By.xpath("//input[@value=' New ']"));
		clickElement(newbtn, "New Opportunity"); 
		
		WebElement optyname = driver.findElement(By.id("opp3"));
		enterText(optyname, "tek1", "Opportunity name");
	
		WebElement acctname = driver.findElement(By.id("opp4"));
		enterText(acctname, "Ramila", "Account name");
		
		WebElement closedate = driver.findElement(By.id("opp9"));
		clickElement(closedate, "Closedate focus");
		closedate.click();

		WebElement month = driver.findElement(By.id("calMonthPicker"));
		String status =selectbyVisibility(month, "July", "Month");
		SFOptylog.info(status);

		WebElement year = driver.findElement(By.id("calYearPicker"));
		String status1 =selectbyVisibility(year, "2025", "Year");
		SFOptylog.info(status1);
		
		List<WebElement> dates = driver.findElements(By.xpath("//*[@id=\"datePickerCalendar\"]/tbody/tr/td"));
		for(WebElement date: dates)
		{

			if(date.getText().contains("17"))
			{
				clickElement(date, "Date");
			}
		}
		
		WebElement stage = driver.findElement(By.id("opp11"));
		selectbyindex(stage, 3, "Stage");
		
		WebElement probalility = driver.findElement(By.id("opp12"));
		enterText(probalility, ".5", "Probalility");
		
		WebElement leadsource = driver.findElement(By.id("opp6"));
		selectbyindex(leadsource, 3, "leadsource");
	
		WebElement priCampaign = driver.findElement(By.id("opp17"));
		enterText(priCampaign, "null", "Primary Campaign");
		
		WebElement save = driver.findElement(By.xpath("//input[@value=' Save ']"));
		clickElement(save, "Save New Opportunity");
		
		WebElement tophead = driver.findElement(By.xpath("//h1[@class='pageType']"));
		String str = getTextFromElement(tophead, "opportunity");
		checkText("Opportunity",str , "New opportunity page display");
		
		WebElement tophead1 = driver.findElement(By.xpath("//h2[@class='pageDescription']"));
		String str1 = getTextFromElement(tophead1, "New Opportunity Name");
		checkText("tek1", str1, " New opportunity name");

		SFOptylog.info(str1 + "is the newly created opportunity");
				
		logoutSalesforce();
		
	}
	
	@Test
	protected void TC17OpportunityPipelineReport() 
	{
		loginSalesforce();
		
		WebElement optytab = driver.findElement(By.linkText("Opportunities"));
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(optytab));		
		clickElement(optytab, "Opportunities tab"); 
		
		WebElement opppipelnrpt = driver.findElement(By.linkText("Opportunity Pipeline"));
		clickElement(opppipelnrpt, "Opportunity Pipeline Report Link");
		
		WebElement page = driver.findElement(By.xpath("//div[@class='bodyDiv brdPalette brandPrimaryBrd']//child::h1"));
		String str2 = getTextFromElement(page,"Opportunity Pipeline page label");
		checkText("Opportunity Pipeline", str2, "Opportunity Pipeline page label");
				
		WebElement reportstatus = driver.findElement(By.xpath("//div[@class='progressIndicator']//child::h2"));	
		String str = getTextFromElement(reportstatus, "Opportunity Pipeline Report status label");	
		checkText("Report Generation Status:", str, "Opty Pipeline Report Page");
		
		logoutSalesforce();
	}
	
	@Test
	protected void TC18StuckOpportunityReport() 
	{
		loginSalesforce();
		
		WebElement optytab = driver.findElement(By.linkText("Opportunities"));
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(optytab));		
		clickElement(optytab, "Opportunities tab"); 
		
		WebElement stuckOpprpt = driver.findElement(By.linkText("Stuck Opportunities"));
		clickElement(stuckOpprpt, "Stuck Opportunities Report Link");
		
		WebElement page = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']"));
		String str2 = getTextFromElement(page,"Stuck Opportunities page label");
		checkText("Stuck Opportunities", str2, "Stuck Opportunities page label");
			
		WebElement reportstatus = driver.findElement(By.xpath("//div[@class='progressIndicator']//child::h2"));	
		String str = getTextFromElement(reportstatus, "Report Generation status label");	
		checkText("Report Generation Status:", str, "Stuck Opportunities Report Status label");
		
		logoutSalesforce();
	}
	
	@Test
	protected void TC19QuarterlySummaryReport() 
	{
		loginSalesforce();
		
		WebElement optytab = driver.findElement(By.linkText("Opportunities"));
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(optytab));		
		clickElement(optytab, "Opportunities tab"); 
		
		WebElement quaSumInterval = driver.findElement(By.xpath("//table[@class='opportunitySummary']//child::select[@id='quarter_q']"));
		selectbyindex(quaSumInterval, 6, "Quaterly Summary interval");
	
		WebElement selInterval = driver.findElement(By.xpath("//select[@id='quarter_q']//child::option[7]"));
		String selectledInterval = getTextFromElement(selInterval, "Quaterly Summary Interval");
									
		WebElement quaSumInclude = driver.findElement(By.id("open"));
		selectbyindex(quaSumInclude, 1, "Quaterly Summary Include");
	
		WebElement selInclude = driver.findElement(By.xpath("//select[@id='open']//child::option[2]"));		
		String selectInclude = getTextFromElement(selInclude, "Quaterly Summary Include");

		WebElement runReportbtn = driver.findElement(By.xpath("//input[@value='Run Report']"));
		clickElement(runReportbtn, "Quaterly Summary Run Report ");

		WebElement range = driver.findElement(By.xpath("//select[@id='quarter_q']//child::option[@selected='selected']"));
		String selectrange = getTextFromElement(range, "Quaterly Summary Range");
		
		WebElement optystatus = driver.findElement(By.xpath("//select[@id='open']//child::option[@selected='selected']"));
		String selectstatus = getTextFromElement(optystatus, "Quaterly Summary Status");
		
		WebElement page = driver.findElement(By.xpath("//h1[@class=\"noSecondHeader pageType\"]"));
		String str2 = getTextFromElement(page,"Opportunity Report page label");
		checkText("Opportunity Report", str2, "Opportunity Report page label");
			
		WebElement reportstatus = driver.findElement(By.xpath("//div[@class='progressIndicator']//child::h2"));	
		String str = getTextFromElement(reportstatus, "Report Generation status label");	
		checkText("Report Generation Status:", str, "Opportunity Report Status label");		
		
		checkText(selectrange, selectledInterval, "Quaterly Report Summary search criteria(Range matched with Interval)");
			
		
		String[] selectIncludewd = selectInclude.split(" ");
		String[] selectstatuswd = selectstatus.split(" ");
		
		for(String Include : selectIncludewd)
		{
			for(String status : selectstatuswd)
			{
				if(Include.equals(status))
				{
					SFOptylog.info("Quaterly Report Summary search criteria(Status matched with Include) Test case passed ");
				}
				else 
				if(Include.equalsIgnoreCase("All") && status.equalsIgnoreCase("Any"))
				{
					SFOptylog.error("Quaterly Report Summary search criteria(Status matched with Include) Test case passed");

				}				
			}
		}
		
		logoutSalesforce();
	}

}
