package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By registerBtnLoc = By.linkText("Register");
	private By firstNameLoc = By.id("input-firstname");
	private By lastNameLoc = By.id("input-lastname");
	private By emailIdLoc = By.id("input-email");
	private By telephoneLoc = By.id("input-telephone");
	private By passwordLoc = By.id("input-password");
	private By confirmPasswordLoc = By.id("input-confirm");
	private By subscribeYesLoc = By.cssSelector("label.radio-inline:nth-of-type(1) input");
	private By subscribeNoLoc = By.cssSelector("label.radio-inline:nth-of-type(2) input");
	private By agreeLoc = By.name("agree");
	private By continueBtnLoc = By.cssSelector("input[value='Continue'][type='submit']");
	private By confirmationMsgLoc = By.cssSelector("div[id='content'] h1");
	private By logoutBtnLoc = By.linkText("Logout");
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	public String getPageTitle() {
		return (elementUtil.doGetTitle());
	}
	
	public boolean registerNewUser(String firstName, String lastName, String emailId, String telephone, String password, String subscribe) {
		
		elementUtil.doSendKeys(firstNameLoc, firstName);
		elementUtil.doSendKeys(lastNameLoc, lastName);
		elementUtil.doSendKeys(emailIdLoc, emailId);
		elementUtil.doSendKeys(telephoneLoc, telephone);
		elementUtil.doSendKeys(passwordLoc, password);
		elementUtil.doSendKeys(confirmPasswordLoc, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			elementUtil.doClick(subscribeYesLoc);
		}else {
			elementUtil.doClick(subscribeNoLoc);
		}
		
		elementUtil.doClick(agreeLoc);
		elementUtil.doClick(continueBtnLoc);
		
		if(elementUtil.getElement(confirmationMsgLoc).getText().equalsIgnoreCase(Constants.REGISTER_CONFIRM_MSG)) {
			elementUtil.doClick(logoutBtnLoc);
			elementUtil.doClick(registerBtnLoc);
			return true;
		} 
		
		return false;
	}

}
