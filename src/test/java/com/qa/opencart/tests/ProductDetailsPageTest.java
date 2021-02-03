package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//@Listeners(ExtentReportListener.class)
@Epic("Open Cart Shopping")
@Story("Story 3.0")
public class ProductDetailsPageTest extends BaseTest {
	
	@BeforeClass
	public void productDetailsSetup() {
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		accountsPage = loginPage.doLogin(username, password);
		productDetailsPage = accountsPage.selectProductFromResults("mac", "MacBook Air");
	}
	
	@Test(priority = 1)
	@Description("Test to verify Product Details Page Title")
	@Severity(SeverityLevel.MINOR)
	public void productDetailsPageTitleTest() {
		String title = productDetailsPage.getPageTitle();
		System.out.println("Product Details Page Title is: "+title);
		Assert.assertEquals(title, "MacBook Air");
	}
	
	@Test
	(priority = 2)
	@Description("Test to verify Correct Product Page is selected or not for MacBook Air")
	@Severity(SeverityLevel.CRITICAL)
	public void productNameHeaderTest() {
		String productName = productDetailsPage.getProductName();
		System.out.println("Product Name is: "+productName);
		Assert.assertEquals(productName, "MacBook Airy");
	}
	
	@Test
	(priority = 3)
	@Description("Test to verify the headers count in Footer section of Product Details Page")
	@Severity(SeverityLevel.NORMAL)
	public void footerHeaderCountTest() {
		int count = productDetailsPage.getFooterHeadersCount();
		Assert.assertEquals(count, 5);
	}

}
