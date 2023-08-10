package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchPage;

public class BaseTest {
	DriverFactory driverFact;
	WebDriver driver;
	protected LoginPage lp;
	protected AccountsPage accPage;
	protected Properties prop;
	protected SearchPage searchPage;
	protected ProductInfoPage proInfo;
	protected SoftAssert softassert;
	protected RegisterPage register;
	
	@BeforeTest
	public void setUp() {
		driverFact = new DriverFactory();
		prop = driverFact.initProp();
		driver = driverFact.initDriver(prop);
		lp = new LoginPage(driver);
		softassert = new SoftAssert();
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	} 

}
