Project Name : walmart
Framework : TestNG
Tools : Selenium WebDriver
Language : Java
Build Tool : Maven

Components :
------------
com.testscripts - 
	searchAndOrder.java : 
	It is the main file where actual test case is executing and test flow is starting from.

com.utility - 
	AppUtility.java : 
	All reusable methods which are useful related walmart.com site are created in this class file. It includes login, logout, searchItems, clickOnProductDetails, viewCart and removeCartItem methods.

com.utility - 
	WrapperActionTest.java : 
	All reusable methods related selenium webdriver are written in this class file. It inlcudes startBrowser, findElement, click, enterText, getText, onMouseOver.

com.utility - 
	PropertiesFileReader.java : 
	Code related to property file loading.

resources/common.properties : 
	All Webpages element locators, userid & password are stored in properties file.

Execution startig point : testng.xml

Search keywords:
---------------
Search keywords are providing in @Dataprovider annotation with dataProviderMethod() method. Currently for testing purpose all keywords are commented except 1 keyword (tv).

Results:
--------
All actions performing are displaying in console.
TestNG report will be generated with the test results in the path : \walmart\test-output\emailable-report.html



