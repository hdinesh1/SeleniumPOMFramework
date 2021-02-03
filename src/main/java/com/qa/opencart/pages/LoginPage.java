package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	//1. By Locators
	private By usernameLoc = By.id("input-email");
	private By passwordLoc = By.id("input-password");
	private By loginBtnLoc = By.cssSelector("input.btn.btn-primary");
	private By forgotPwdLinkLoc = By.cssSelector("div.form-group a");
	//aside[@id='column-right']//a[text()='Register'] 
	private By registerLinkLoc = By.cssSelector("side#column-right a[href*='register']");
	//2. Constructor of Login Page
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	//3. Page actions or Page Methods or Page Libraries for Login Page
	public String getLoginPageTitle() {
		//return (driver.getTitle());
		return elementUtil.doGetTitle();
		//return elementUtil.waitForPageTitleContains(Constants.LOGIN_PAGE_TITLE, 5);
	}
	
	public Boolean isForgotPwdLinkExist() {
		//return (driver.findElement(forgotPwdLinkLoc).isDisplayed());
		return elementUtil.checkDisplay(forgotPwdLinkLoc);
	}
	
	@Step("Login with username: {0} and password: {1}")
	public AccountsPage doLogin(String username, String password) {
		System.out.println("Login with " + username + " and " + password);
		/*
		driver.findElement(usernameLoc).sendKeys(username);
		driver.findElement(passwordLoc).sendKeys(password);
		driver.findElement(loginBtnLoc).click();
		*/
		elementUtil.doSendKeys(usernameLoc, username);
		elementUtil.doSendKeys(passwordLoc, password);
		elementUtil.doClick(loginBtnLoc);
		return (new AccountsPage(driver));
	}
	
	public RegisterPage doRegister() {
		elementUtil.doClick(registerLinkLoc);
		return (new RegisterPage(driver));
	}

}
