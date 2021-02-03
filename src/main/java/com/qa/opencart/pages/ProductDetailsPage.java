package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class ProductDetailsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By productNameLoc = By.cssSelector("div#content h1");
	private By footerHeaderLoc = By.cssSelector("div.col-sm-3 h5");
	
	
	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	public String getPageTitle() {
		return (elementUtil.doGetTitle());
	}
	
	public String getProductName() {
		return (elementUtil.getElement(productNameLoc).getText());
	}
	
	public int getFooterHeadersCount() {
		return elementUtil.getElements(footerHeaderLoc).size();
	}

}
