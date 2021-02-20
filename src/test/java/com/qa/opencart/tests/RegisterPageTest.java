package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtils;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void registerPageSetup() {
		registerPage = loginPage.doRegister();
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object dataSheet[][] = ExcelUtils.getTestData(Constants.SHEETNAME);
		return dataSheet;
	}
	
	@Test(dataProvider="getRegisterData")
	public void registerPageTest(String firstName, String lastName, String emailId, String telephone, String password, String subscribe) {
		Assert.assertTrue(registerPage.registerNewUser(firstName, lastName, emailId, telephone, password, subscribe));
	}
}
