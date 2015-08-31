package com.utility;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WrapperActionTest {

	public WebElement webelement;

	public void startBrowser(WebDriver driver, String url) throws Exception {
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	
	public WebElement findElement(WebDriver driver,String locator) {
		WebElement webelement = null;
		if (locator != null) {
			String[] arrLocator = locator.split("==");
			String locatorTag = arrLocator[0].trim();
			String objectLocator = arrLocator[1].trim();
			try {
				if (locatorTag.equalsIgnoreCase("id")) {
					webelement = driver.findElement(By.id(objectLocator));
				} else if (locatorTag.equalsIgnoreCase("name")) {
					webelement = driver.findElement(By.name(objectLocator));
				} else if (locatorTag.equalsIgnoreCase("xpath")) {
					webelement = driver.findElement(By.xpath(objectLocator));
				} else if (locatorTag.equalsIgnoreCase("linkText")) {
					webelement = driver.findElement(By.linkText(objectLocator));
				} else if (locatorTag.equalsIgnoreCase("class")) {
					webelement = driver.findElement(By.className(objectLocator));
				} else {
					String error = "Please Check the Given Locator Syntax :"+ locator;
					error = error.replaceAll("'", "\"");
					return null;
				}
			} catch (Exception exception) {
				//exception.printStackTrace();
				String error = "Please Check the Given Locator Syntax :"+ locator;
				System.out.println("error=="+error);
				error = error.replaceAll("'", "\"");
				return null;
			}
		}
		return webelement;
	}
	
	public static void shutDownDriver(WebDriver driver) {
		System.out.println("driver::"+driver);
		if (driver != null)
			driver.quit();
	}
	
	public void click(WebDriver driver,String field)throws Exception {
		try{
			WebElement element = findElement(driver,field);
			//element.getText();
			element.click();
	        Thread.sleep(2000);
		} catch (Exception e){
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	public void enterText(WebDriver driver,String field, String value) {

		try {
			WebElement element = findElement(driver,field);
			element.sendKeys(value);
			//waitInSeconds(10);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
		
	public String getText(WebDriver driver,String locator) {
		WebElement element;
		String text = "NO VALUE RETRIVED";
		try{
		element = findElement(driver,locator);
		if (element != null){
			text = element.getText();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		element = null;
		return text;
	}

	public boolean isElementPresent(WebDriver driver,String field){
		try {
			WebElement element = null;
			element= findElement(driver,field);
			if(element != null && field != null){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
		
	public synchronized void onMouseOver(WebDriver driver,String xpath) throws Exception{
		
		Actions action = new Actions(driver);
					
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		action.moveToElement(findElement(driver,xpath)).build().perform();
		waitInSeconds(2);
		
	}

		public void waitInSeconds(int time) {
			try {
				Thread.sleep(time * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("exception in tread.sleep..");
			}
		}
		
		
	
}
