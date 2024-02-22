package com.Salesforce.base;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.Salesforce.utilities.Constants;
import com.Salesforce.utilities.PropertiesUtility;
@Listeners(com.Salesforce.utilities.SFListener.class)

public class BaseTestSF extends BaseClass
{

	protected static Logger BaseTestlog = LogManager.getLogger();

	@BeforeMethod
	@Parameters("browsername")
	public void SFBeforeMethod(@Optional("chrome") String name)
	{
		BaseTestlog.info("---------------------SFBeforeMethod Executed----------------------------");

		launchBrowser(name);
		String url=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"url");
		goToURL(url);		
	}
	
	@AfterMethod
	public void SFAfterMethod()
	{
		BaseTestlog.info("---------------------SFAfterMethod Executed----------------------------");
		
		Set<String> handles = driver.getWindowHandles();	
		if(handles.size()==1)
		{
		closeBrowser();	
		}
		else if(handles.size()>1)
		{
			closeBrowsers();
		}
		
	}
	
	
	
	
	
		public static void loginSalesforce() 
		{
			String expLgTitle = "Login | Salesforce"; 
			String expTitle ="Home Page ~ Salesforce - Developer Edition";
		    
			String userName=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"username");
			String passWord=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");

			String lgntitle = getTitle(driver);
			
			checkText(expLgTitle,lgntitle,"Login Page display");
				
			WebElement userid = driver.findElement(By.id("username"));
			enterText(userid,userName,"username");
			
			WebElement passwordtxt = driver.findElement(By.id("password"));
			enterText(passwordtxt,passWord,"password");

			WebElement btn = driver.findElement(By.id("Login"));
			clickElement(btn, "login");			
			
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.titleContains("Home"));
							
			String strtitle = getTitle(driver);
			BaseTestlog.info(strtitle);
			extentReport.logTestInfo(strtitle);
			checkText(expTitle,strtitle,"HomePage display");		

		}
		public static void logoutSalesforce() 
		{
			
			String expLgTitle = "Login | Salesforce"; 	
			
			WebElement usernavbtn = driver.findElement(By.xpath("//div[@id='userNavButton']"));
			if(usernavbtn.isDisplayed())
			{
				clickElement(usernavbtn, "User Navigation");
			}				
			
			WebElement logoutlink = driver.findElement(By.linkText("Logout"));
	

			if(logoutlink.isDisplayed())
			{
				clickElement(logoutlink, "Logout Link");
			
				WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.titleContains("Login"));
				
				String lgntitle = getTitle(driver);

				checkText(expLgTitle,lgntitle,"Login Page display");			
				BaseTestlog.info("Logout Test has passed");
				extentReport.logTestInfo("Logout Test has passed");

			}
		}
	
		
		

}

