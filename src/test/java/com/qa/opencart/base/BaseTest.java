package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductDetailsPage;
import com.qa.opencart.pages.RegisterPage;

public class BaseTest {
	DriverFactory df;
	public Properties prop;
	WebDriver driver;
	public LoginPage loginPage;
	public AccountsPage accountsPage;
	public ProductDetailsPage productDetailsPage;
	public RegisterPage registerPage;
	
	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.init_prop();
		//String browserName = prop.getProperty("browserName");
		//driver = df.init_driver(browserName);
		driver = df.init_driver(prop);
		String url = prop.getProperty("url");
		driver.get(url);
		loginPage = new LoginPage(driver);
		accountsPage = new AccountsPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
