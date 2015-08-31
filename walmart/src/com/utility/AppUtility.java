package com.utility;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import com.utility.PropertiesFileReader;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AppUtility {

	public static WrapperActionTest test = new WrapperActionTest(); 
	
	public static void searchItem(WebDriver driver, String keyword) throws Exception {
		
		System.out.println("Entering Search keyword");
		//test.waitInSeconds(10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		test.enterText(driver,PropertiesFileReader.getProperty("xpath_search_bar"),keyword);
		System.out.println("Clicking on Search button");
		test.click(driver, PropertiesFileReader.getProperty("xpath_search_button"));
		test.waitInSeconds(6);
		test.enterText(driver,PropertiesFileReader.getProperty("xpath_search_bar"),keyword);
		System.out.println("Clicking on Search button");
		test.waitInSeconds(4);
		test.click(driver, PropertiesFileReader.getProperty("xpath_search_button"));		
		//test.waitInSeconds(4);
		if(test.isElementPresent(driver, PropertiesFileReader.getProperty("xpath_noresults")))
		{
				System.out.println(test.getText(driver, PropertiesFileReader.getProperty("xpath_noresults")));
				Assert.assertFalse(true, "0 results found");
		}
	}
	
public static void productDetails(WebDriver driver, String locator) throws Exception {
		
			test.click(driver,  locator);	
			test.waitInSeconds(2);
			System.out.println("Clicking on product details");
			String prodTitle = test.getText(driver,PropertiesFileReader.getProperty("xpath_product_title"));
			System.out.println("Prod Title : "+prodTitle);
			PropertiesFileReader.setProperty("ProdTitle", prodTitle);
			//test.waitInSeconds(5);
	}

public static void viewCart(WebDriver driver, String locator) throws Exception {
	
		System.out.println("Clicking on View Cart");
		test.click(driver, locator);
		test.waitInSeconds(5);
	}

public static void login(WebDriver driver, String username, String password) throws Exception {
	
		System.out.println("Clicking on Sign In link");
		test.waitInSeconds(5);
		test.click(driver, PropertiesFileReader.getProperty("xpath_signin"));
		test.waitInSeconds(5);
		System.out.println("Entering Username");
		test.enterText(driver, PropertiesFileReader.getProperty("xpath_loginId"), username);
		System.out.println("Entering Password");
		test.enterText(driver, PropertiesFileReader.getProperty("xpath_loginPwd"), password);
		System.out.println("Clicking on SignIn button");
		test.click(driver, PropertiesFileReader.getProperty("xpath_signIn_button"));
		test.waitInSeconds(5);
}

public static void logout(WebDriver driver) throws Exception {

		test.waitInSeconds(5);
		System.out.println("Clicking on My Account Dropdown");
		test.onMouseOver(driver, PropertiesFileReader.getProperty("xpath_myaccount_dropdown"));
		//test.click(driver, PropertiesFileReader.getProperty("xpath_myaccount"));
		System.out.println("Clicking on Logout Link");
		test.click(driver, PropertiesFileReader.getProperty("xpath_logout"));
		System.out.println("Log Out");
}	

public static void clickContinue(WebDriver driver, String locator) throws Exception {

		test.waitInSeconds(2);
		test.click(driver, locator);
}

public static void validateText(WebDriver driver, String locator, String cart){
	test.waitInSeconds(2);
	String txtValidate = test.getText(driver,locator);
	Assert.assertEquals(txtValidate, cart);	
}

public static void validatePaymentPageText(WebDriver driver, String locator, String paymenttext){
	test.waitInSeconds(2);
	String txtValidate = test.getText(driver,locator);
	//System.out.println("Text : "+txtValidate);
	Assert.assertEquals(txtValidate.substring(2), paymenttext);
	
}

public static void removeCartItem(WebDriver driver, String locator) throws Exception{
	
	test.click(driver, locator);
	
}

}

