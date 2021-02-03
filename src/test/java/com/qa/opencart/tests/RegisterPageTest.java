package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;

import com.qa.opencart.base.BaseTest;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void registerPageSetup() {
		registerPage = loginPage.doRegister();
	}
	
	
}
