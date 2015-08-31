package com.testscripts;

import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.utility.AppUtility;
import com.utility.PropertiesFileReader;
import com.utility.WrapperActionTest;

public class searchAndOrder {

	public static WebDriver driver;
	public static Properties commonProperties = null;
	public static WrapperActionTest test = new WrapperActionTest(); 
	public static AppUtility app = new AppUtility();
	
	@BeforeClass
	public static void launch(){
		//driver = new FirefoxDriver();	
		commonProperties = PropertiesFileReader.getInstance().readProperties("common.properties");// Enter property file name as per the application
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");

	}

	@BeforeMethod
	public static void openSite()
	{
		String url = PropertiesFileReader.getProperty("url");
		if(PropertiesFileReader.getProperty("browser").equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		}
		else if(PropertiesFileReader.getProperty("browser").equalsIgnoreCase("chrome")){ 
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();	
		driver.get(url);
	}
	
	@DataProvider(name = "keywords")
    public Object[][] dataProviderMethod() {
        return new Object[][] { 
        						{ "tv" }
        					/*	{ "iphone" } 
        						{ "socks" },
        						{ "dvd" },
        						{ "toys" }*/
        };
   
    }
	
	
	@Test(dataProvider = "keywords")
	public static void searchandorder(String keyword) throws Exception{

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		AppUtility.searchItem(driver,keyword);
		
		AppUtility.productDetails(driver,PropertiesFileReader.getProperty("xpath_prod_details"));
		
		System.out.println("Clicking on Addto Cart button");
		test.click(driver, PropertiesFileReader.getProperty("xpath_addto_cart"));
		
		AppUtility.viewCart(driver,PropertiesFileReader.getProperty("xpath_view_cart"));

		AppUtility.login(driver,PropertiesFileReader.getProperty("username"),PropertiesFileReader.getProperty("password"));
		
		//Navigate to view cart After login
		AppUtility.viewCart(driver,PropertiesFileReader.getProperty("xpath_viewcart_icon"));
		
		//Validating cart count After login
		AppUtility.validateText(driver, PropertiesFileReader.getProperty("xpath_cart_item_count"), PropertiesFileReader.getProperty("INITIAL_CART_COUNT"));

		//Validating product title of cart After login
		AppUtility.validateText(driver,PropertiesFileReader.getProperty("xpath_product_title_cart"), PropertiesFileReader.getProperty("ProdTitle"));
		
		System.out.println("Clicking on CheckOut");
		test.click(driver, PropertiesFileReader.getProperty("xpath_checkouts"));
		test.waitInSeconds(7);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Selecting Shipping Method
		System.out.println("Selecting Shipping Method");
		test.click(driver, PropertiesFileReader.getProperty("xpath_shipping_method"));
		test.waitInSeconds(2);
			
		//Click Continue Shipping details page
		System.out.println("Clicking on Continue button on shipping Method page");
		AppUtility.clickContinue(driver,PropertiesFileReader.getProperty("xpath_continue_checkout1"));
				
		//Click Continue
		System.out.println("Clicking on Continue button on shipping details page");
		AppUtility.clickContinue(driver,PropertiesFileReader.getProperty("xpath_continue_checkout2"));
		
		//Validating payment details page
		AppUtility.validatePaymentPageText(driver, PropertiesFileReader.getProperty("xpath_payment_details"), PropertiesFileReader.getProperty("PAYEMNT_PAGE_TEXT"));
		
		AppUtility.viewCart(driver,PropertiesFileReader.getProperty("xpath_checkout_viewcart_icon"));
		test.waitInSeconds(4);
		
		System.out.println("Clicking on Remove Item");
		AppUtility.removeCartItem(driver, PropertiesFileReader.getProperty("xpath_cart_remove_item"));
		
		//Validating cart count
		AppUtility.validateText(driver, PropertiesFileReader.getProperty("xpath_cart_item_count"), PropertiesFileReader.getProperty("FINAL_CART_COUNT"));
		
		//Logout
		AppUtility.logout(driver);
	}
	
	@AfterMethod
	public static void closeBrowser(){
		test.shutDownDriver(driver);
	}
	
}
