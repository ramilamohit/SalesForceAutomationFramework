package com.Salesforce.base;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Salesforce.utilities.ExtentReportsUtility;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	protected static WebDriver driver = null;
	protected static WebDriverWait wait = null;
	protected static Logger BaseClasslog = LogManager.getLogger();
	protected static ExtentReportsUtility extentReport=ExtentReportsUtility.getInstance();

	public  void launchBrowser(String browserName)
	{
		switch(browserName.toLowerCase()) {
		case "chrome":
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
			
		case "firefox":
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
			
		case "edge":
			
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			break;
			
		case "opera":
			
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
			driver.manage().window().maximize();
			break;
			
		default:
			BaseClasslog.info("Unsupported Browser: " + browserName);						
		}
	}
	public static String getTitlefromElement(WebDriver driver)
	{
		String data = driver.getTitle();
		BaseClasslog.info("Title of the current page extracted");
		extentReport.logTestInfo("Title of the current page extracted");
		return data;
	}
	
	public static void switchToFrame(WebElement framename, String Objectname)
	{
		try {
		driver.switchTo().frame(framename);
		BaseClasslog.info("Switched to the frame " + Objectname);
		extentReport.logTestInfo("Switched to the frame " + Objectname);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BaseClasslog.error("Can't switch to the frame" + Objectname);
			extentReport.logTestFailed("Can't switch to the frame" + Objectname);
		}
	
	}
	
	public static void switchToParentFrame(String Objectname)
	{
		try {
		driver.switchTo().parentFrame();
		BaseClasslog.info(Objectname +" switched to the parent frame ");
		extentReport.logTestInfo(Objectname +" switched to the frame ");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BaseClasslog.error(Objectname +" can't switch to the parent frame");
			extentReport.logTestFailed(Objectname +" can't switch to the parent frame");
		}
	
	}
	public static String getParentWindow()
	{
		String parentwindow = driver.getWindowHandle();
		//BaseClasslog.info("");
		return parentwindow;
	}
	
	public static Set<String> getWindows()
	{
		Set<String> handles = driver.getWindowHandles();
		return handles;
	}
	
	public static void enterTextBox(WebElement element, String data, String objectName)
	{
		if (element.isDisplayed())
		{
			element.clear();
			element.sendKeys(data);
			BaseClasslog.info("data is entered in " + objectName + " textbox");
			extentReport.logTestInfo("data is entered in " + objectName + " textbox");

		} else {
			BaseClasslog.info(objectName + " element is not displayed");
			extentReport.logTestInfo( objectName + " element is not displayed");

		}
	}
	
	public static void goToURL(String url)
	{
		driver.get(url);
		BaseClasslog.info(url + " is entered");	
		
	}
	
	public static String getTitle(WebDriver driver)
	{
		String title = driver.getTitle();
		BaseClasslog.info("Title text fetched from the current title");
		extentReport.logTestInfo("Title of the current page extracted");
		return title;
	}
	
	
	public static void enterText(WebElement element, String data, String objectName)
	{
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(data);
			BaseClasslog.info("data is entered in " + objectName + " textbox");
			extentReport.logTestInfo("data is entered in " + objectName + " textbox");

		} else {
			BaseClasslog.info(objectName + " element is not displayed");
			extentReport.logTestInfo( objectName + " element is not displayed");

		}
	}
	
	public static void clickElement(WebElement element, String objectName) {
		if (element.isEnabled())
		{
			element.click();
			BaseClasslog.info(objectName + " button is clicked");
			extentReport.logTestInfo(objectName + " button is clicked");

			
		}
		else
		{
			BaseClasslog.info(objectName+" element is not enabled");
			extentReport.logTestInfo(objectName + " element is not enabled");

		}
	}
	
	public static void checkboxclick(WebElement element, String objectname)
	{
		if(element.isSelected()== false)
		{
			element.click();
			BaseClasslog.info(objectname +" element is checked");
			extentReport.logTestInfo(objectname + " element is checked");

		}
	}

	public static void checkText(String expText, String actualText, String text)
	{
		if(actualText.equals(expText))
		{
			BaseClasslog.info(text + " Test case is passed");
			extentReport.logTestInfo(text + " Test case is passed");

		}
		else
		{
			BaseClasslog.error(text + " Test case failed");
			extentReport.logTestFailed(text + " Test case failed");
		}
		
	
	}
	
	public static void checkList(ArrayList<String> list,ArrayList<String> list1, String Objectname )
	{
		if(list.equals(list1))
		{
			BaseClasslog.info(Objectname + " Test case is passed");
			extentReport.logTestInfo(Objectname + " Test case is passed");

		}
		else
		{
			BaseClasslog.error(Objectname + " Test case failed");
			extentReport.logTestFailed(Objectname + " Test case failed");
		
		}
	}
	
		
	public static String getTextFromElement(WebElement element, String objectName)
	{
		String data = element.getText();
		BaseClasslog.info("text is extracted from "+objectName);
		extentReport.logTestInfo("text is extracted from "+objectName);

		
		return data;
	}
	
	public static String getAttributFromElement(WebElement element, String objectName) {
		String data = element.getAttribute("value");
		BaseClasslog.info("Attribute value is extracted from "+objectName);
		extentReport.logTestInfo("Attribute value is extracted from "+objectName);

		return data;
	}
	
	public static void selectbyindex(WebElement element, int index,String objectName)
	{
		if(element.isDisplayed()) 
		{
		Select se = new Select(element);
		se.selectByIndex(index);
		BaseClasslog.info(objectName + " index "+ index +" got selected");
		extentReport.logTestInfo(objectName + " index "+ index +" got selected");

		}
		else
		{
			BaseClasslog.info(objectName + "is not displayed");
			extentReport.logTestInfo(objectName + "is not displayed");

		}
		
	}
	
	public static  String selectbyVisibility(WebElement element, String visible, String objectname)
	{
		if(element.isDisplayed())
		{
			Select se = new Select(element);
			try 
			{
			se.selectByVisibleText(visible);
			BaseClasslog.info(visible + "is selected in the" + objectname);
			extentReport.logTestInfo(visible + "is selected in the" + objectname);

			return "selected";
			}
			catch(Exception e)
			{
				BaseClasslog.info(visible +" is not visible in the " + objectname);
				extentReport.logTestInfo(visible +" is not visible in the " + objectname);
				return "not selected";
			}
		}
		else
		{
			BaseClasslog.info(objectname + "is not displayed");
			extentReport.logTestInfo(objectname + "is not displayed");
			return "not selected";
		}
		
	}
	
	public static void waitforTitle(int time,String titlecontains,String objectname)
	{
		try 
		{
		BaseClasslog.info(objectname + " is waiting for visibility");
		extentReport.logTestInfo(objectname + " is waiting for visibility");
		wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.titleContains(titlecontains));
	}
	catch(Exception e)
	{
		BaseClasslog.error(objectname+" is not visible");
		extentReport.logTestFailedWithTimeout(objectname+" is not visible");

	}
		
	}
	
	public static void implicitwait(int time)
	{
		driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);

	}

	public static void waitforVisibility(WebElement element, int time, String objectname)
	{
		try
		{
			BaseClasslog.info(objectname + " is waiting for visibility");
			extentReport.logTestInfo(objectname + " is waiting for visibility");
			wait = new WebDriverWait(driver,time);
			wait.until(ExpectedConditions.visibilityOf(element));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			BaseClasslog.error(objectname+" is not visible");
			extentReport.logTestFailedWithTimeout(objectname+" is not visible");
			
		}	
		
	}
	
	public static void takescreenshot(String filepath)
	{
		TakesScreenshot screenCapture = (TakesScreenshot)driver;
		File src = screenCapture.getScreenshotAs(OutputType.FILE);
		File destination = new File(filepath);
		try {
			Files.copy(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			BaseClasslog.info("Screenshot is not captured");
			extentReport.logTestInfo("Screenshot is not captured");

		}
		
	}
	
	public static void takescreenshot(WebElement element, String filepath)
	{
		File src = element.getScreenshotAs(OutputType.FILE);
		File destination = new File(filepath);
		try {
			Files.copy(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			BaseClasslog.info("Screenshot is not captured");
			extentReport.logTestInfo("Screenshot is not captured");

		}
	}
	
	public static void closeBrowser() {
		driver.close();
		BaseClasslog.info("browser instance closed");
		extentReport.logTestInfo("browser instance closed");
		driver=null;
	}
	
	public static void closeBrowsers()
	{
		driver.quit();
		BaseClasslog.info("browser instances closed");
		extentReport.logTestInfo("browser instances closed");
		driver=null;
	}

}
