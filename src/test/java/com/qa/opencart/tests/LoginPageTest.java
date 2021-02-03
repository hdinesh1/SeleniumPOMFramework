package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Open Cart Shopping")
public class LoginPageTest extends BaseTest {
	
	@Story("Story 1.1")
	@Test(priority=1)
	@Description("Test to verify Login Page Title")
	@Severity(SeverityLevel.MINOR)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login Page Title is: " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Story("Story 1.2")
	@Test(priority=2)
	@Description("Test to verify forgot password links is shown or not")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPwdLinkTest() {
		boolean pwdLinkYesNo = loginPage.isForgotPwdLinkExist();
		Assert.assertTrue(pwdLinkYesNo);
	}
	
	@Story("Story 1.3")
	@Test(priority=3)
	@Description("Test to verify whether user is able to login or not")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		accountsPage = loginPage.doLogin(username, password);
		Assert.assertEquals(accountsPage.getAccountsPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
	}
}
