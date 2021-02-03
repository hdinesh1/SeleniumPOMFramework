package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Open Cart Shopping")
@Story("Story 2.0")
public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accountsPageSetUp() {
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		accountsPage = loginPage.doLogin(username, password);
	}
	
	@Test (priority = 1)
	@Description("Test to verify Accounts Page Title")
	@Severity(SeverityLevel.MINOR)
	public void accountsPageTitleTest() {
		String title = accountsPage.getAccountsPageTitle();
		System.out.println("Accounts Page Title is: " + title);
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test (priority = 2)
	@Description("Test to verify Header of the Accounts page is correct or not")
	@Severity(SeverityLevel.NORMAL)
	public void accountsPageHeaderTest() {
		String header = accountsPage.getAccountPageHeader();
		System.out.println("Accounts page header is: " + header);
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER);
	}
	
	@Test (priority = 3)
	@Description("Test to verify Accounts page sections count")
	@Severity(SeverityLevel.NORMAL)
	public void accountsPageSectionCountTest() {
		int sectionCtr = accountsPage.getAccountSectionsCount();
		System.out.println("Section Count is: " + sectionCtr);
		Assert.assertTrue(sectionCtr == Constants.ACCOUNTS_PAGE_SECTION_COUNT);
	}
	
	@Test (priority = 4)
	@Description("Test to verify the list of Accounts page sections")
	@Severity(SeverityLevel.NORMAL)
	public void accountsPageSectionList() {
		List<String> sectionList = accountsPage.getAccountSectionsList();
		System.out.println(sectionList);
	}
	
	@Test (priority = 5)
	@Description("Test to verify search of iMac is working or not")
	@Severity(SeverityLevel.CRITICAL)
	public void doSearchTest_Imac() {
		int noOfItemsFound = accountsPage.doSearch("iMac");
		boolean found = false;
		if (noOfItemsFound > 0) found = true; 
		Assert.assertTrue(found);
	}
	
	@Test (priority = 6)
	@Description("Test to verify search of mac is working or not")
	@Severity(SeverityLevel.CRITICAL)
	public void doSearchTest_mac() {
		int noOfItemsFound = accountsPage.doSearch("mac");
		boolean found = false;
		System.out.println("noOfItemsFound: "+noOfItemsFound);
		if (noOfItemsFound > 0) found = true; 
		Assert.assertTrue(found);
	}
	
	@Test (priority=7)
	@Description("Test to verify selection of MacBook Air from search result is working or not")
	@Severity(SeverityLevel.CRITICAL)
	public void selectProduct_MacBook_Air() {
		accountsPage.selectProductFromResults("mac", "MacBook Air");
	}
}
