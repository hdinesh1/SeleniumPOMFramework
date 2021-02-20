package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
		//productDetailsPage = accountsPage.selectProductFromResults("mac", "MacBook Air");
	}
	
	@Test(priority = 1)
	@Description("Test to verify Product Details Page Title")
	@Severity(SeverityLevel.MINOR)
	public void productDetailsPageTitleTest_MacBook_Air() {
		productDetailsPage = accountsPage.selectProductFromResults("mac", "MacBook Air");
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
		Assert.assertEquals(productName, "MacBook Air");
	}
	
	@Test
	(priority = 3)
	@Description("Test to verify the headers count in Footer section of Product Details Page")
	@Severity(SeverityLevel.NORMAL)
	public void footerHeaderCountTest() {
		int count = productDetailsPage.getFooterHeadersCount();
		Assert.assertEquals(count, 4);
	}
	
	@Test
	(priority = 10)
	@Description("Test to verify the Correct Product Page is shown when iMac is searched and selected")
	@Severity(SeverityLevel.CRITICAL)
	public void productDetailsPageTitleTest_iMac() {
		productDetailsPage = accountsPage.selectProductFromResults("iMac", "iMac");
		String title = productDetailsPage.getPageTitle();
		System.out.println("Product Details Page Title is: "+title);
		Assert.assertEquals(title, "iMac");
	}
	
	@Test
	(priority=4)
	@Description("Test to verify the Image count in MacBook Air Product Details Page")
	@Severity(SeverityLevel.NORMAL)
	public void verifyProductImageTest() {
		productDetailsPage = accountsPage.selectProductFromResults("mac", "MacBook Air");
		int countOfImages = productDetailsPage.getProductImages();
		Assert.assertTrue(countOfImages == 4);
		//Map<String, String> productInfo = productDetailsPage.getProductInfo();
		
	}
	
	@Test
	(priority=5)
	@Description("Test to verify the metadata for MacBook Air Product Details Page")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyProductInfoTest() {
		productDetailsPage = accountsPage.selectProductFromResults("mac", "MacBook Air");
		Map<String, String> productInfoMap = productDetailsPage.getProductInfo();
		System.out.println(productInfoMap);
		
		productInfoMap.forEach((k,v) -> System.out.println(k+":"+v));
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productInfoMap.get("Name"), "MacBook Air");
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 17");
		softAssert.assertEquals(productInfoMap.get("Reward Points"), "700");
		softAssert.assertEquals(productInfoMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(productInfoMap.get("Price"), "$1,000.00");
		softAssert.assertEquals(productInfoMap.get("ExTax"), "$1,000.00");
		softAssert.assertAll(); 
	}
}
