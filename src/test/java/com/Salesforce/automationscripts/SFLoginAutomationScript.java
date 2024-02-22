package com.Salesforce.automationscripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Salesforce.base.*;
import com.Salesforce.utilities.Constants;
import com.Salesforce.utilities.PropertiesUtility;

public class SFLoginAutomationScript extends BaseTestSF {
	
	protected static Logger SFLoginlog = LogManager.getLogger();
	
	@Test
	public static void TC1ErrormsgEmptyPassword()
	{

		String expError = "Please enter your password.";
		String loginTle = "Login | Salesforce";

		waitforTitle(60,"Login","Login Page");
				
		String loginpg = getTitle(driver);
		
		checkText(loginTle, loginpg, "Login Page title");
		
		Assert.assertEquals(loginpg, loginTle, "Login Page title");
					
		String userName=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"username");

		WebElement userid = driver.findElement(By.id("username"));
		enterText(userid,userName,"username");
		
		String emailvalue = userid.getAttribute("value");
		SoftAssert sftasrt = new SoftAssert();
	
		sftasrt.assertEquals(emailvalue, "rami@solids.com", "Username visible");
		checkText("rami@solids.com", emailvalue, "Username text visible");
				
		WebElement passwordtxt = driver.findElement(By.id("password"));
		enterText(passwordtxt,"","password");

		sftasrt.assertEquals("", "", "Password txt is empty");

		checkText("", "", "Password txt is empty");

		WebElement btn = driver.findElement(By.id("Login"));
		clickElement(btn, "Login button");
		
		WebElement errormsglb = driver.findElement(By.id("error"));
		String errormsg = getTextFromElement(errormsglb,"Error Message Element");
		
		sftasrt.assertEquals(errormsg, expError, "Error Message Element");
		checkText(expError, errormsg, "Error Message Element");

		sftasrt.assertAll();

	}
	


	@Test
	
	public static void TC2HomepageDisplay()
	{
		String expLgTitle = "Login | Salesforce"; 
		String expTitle ="Home Page ~ Salesforce - Developer Edition";
	    		
		String lgntitle = getTitle(driver);
		
		checkText(expLgTitle,lgntitle,"Login Page display");
		
		Assert.assertEquals(lgntitle, expLgTitle, "Login Page display");
			
		String userName=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"username");
		String passWord=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");

		WebElement userid = driver.findElement(By.id("username"));
		enterText(userid,userName,"username");
		
		WebElement passwordtxt = driver.findElement(By.id("password"));
		enterText(passwordtxt,passWord,"password");

		WebElement btn = driver.findElement(By.id("Login"));
		clickElement(btn, "login button");
		
		waitforTitle(60,"Home","Home Page");
			
		String title = getTitle(driver);
		
		checkText(expTitle,title,"HomePage display");
		Assert.assertEquals(title, expTitle, "HomePage display");

	}
	
	@Test
	public static void 	TC3RememberCheckboxUsername()
	{
		String expLgTitle = "Login | Salesforce"; 
		String expTitle ="Home Page ~ Salesforce - Developer Edition";
					
		implicitwait(30);
	
		String lgntitle = getTitle(driver);
		
		checkText(expLgTitle,lgntitle,"Login Page");
		Assert.assertEquals(lgntitle, expLgTitle);
	
		String userName=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"username");
		String passWord=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");

		WebElement userid = driver.findElement(By.id("username"));
		enterText(userid,userName,"username");
		
		WebElement passwordtxt = driver.findElement(By.id("password"));
		enterText(passwordtxt,passWord,"password");
		
		WebElement chkbox =driver.findElement(By.id("rememberUn"));
		checkboxclick(chkbox, "check box");
		
		WebElement btn = driver.findElement(By.id("Login"));
		clickElement(btn, "Login button");
		
		waitforTitle(60,"Home","Home Page");
		
		String title = getTitle(driver);
		
		checkText(expTitle,title,"HomePage display");
		
		WebElement userNavDrdown =driver.findElement(By.id("userNavButton"));
		clickElement(userNavDrdown, "User Navigation Dropdown");
		
		WebElement logoutlink = driver.findElement(By.linkText("Logout"));
		clickElement(logoutlink, "logout button");
				
		WebElement inputUserId = driver.findElement(By.id("username"));
		String getUserId = inputUserId.getAttribute("value");
		
		checkText(userName, getUserId, "User TxtBox is populated with valid User Id");
		Assert.assertEquals(getUserId, userName);
		
	}
	

	@Test
	
	public static void TC4AForgotPassword() 
	{
		String expcheckemail = "Check Your Email";
		
		String userName=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"username");

		WebElement userid = driver.findElement(By.id("username"));
		enterText(userid,userName,"username");

		
		WebElement passwordtxt = driver.findElement(By.id("password"));
		enterText(passwordtxt,"","password");

				
		WebElement forgotpassword = driver.findElement(By.partialLinkText("Forgot"));
		clickElement(forgotpassword, "Forgot Password button");
		
		WebElement txtuserid = driver.findElement(By.xpath("//input[@id='un']"));
		enterText(txtuserid, userName, "User name");
		
		WebElement continuebtn = driver.findElement(By.xpath("//*[contains(@value,'Continue')]"));
		clickElement(continuebtn, "Continue button");
	
		WebElement header =driver.findElement(By.id("header"));		
		
		String checkemail = getTextFromElement(header,"Check Email header");
		
		checkText(expcheckemail, checkemail, "Check Email Header");
		Assert.assertEquals(checkemail, expcheckemail);

	}
	
	
	@Test
	public static void TC4BErrormsgWrongIdPassword() 
	{
		String expErrorMsg = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
									
		WebElement userid = driver.findElement(By.id("username"));
		enterText(userid,"121","username");

		
		WebElement passwordtxt = driver.findElement(By.id("password"));
		enterText(passwordtxt,"22131","password");
		
		WebElement checkbox = driver.findElement(By.xpath("//input[@id='rememberUn']"));
		checkboxclick(checkbox, "check box");
		
		WebElement btn = driver.findElement(By.id("Login"));
		clickElement(btn, "Login button");
		
		WebElement errormsg = driver.findElement(By.xpath("//div[@id='error']"));
		
		String errorlabel = getTextFromElement(errormsg,"Error Message Element");
		
		checkText(expErrorMsg,errorlabel,"Error Message");
		Assert.assertEquals(errorlabel, expErrorMsg);

	}
	
	
	}
