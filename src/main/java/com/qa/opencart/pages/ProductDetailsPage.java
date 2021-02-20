package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductDetailsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By productNameLoc = By.cssSelector("div#content h1");
	private By footerHeaderLoc = By.cssSelector("div.col-sm-3 h5");
	private By productMetaDataLoc = By.cssSelector("div#content .list-unstyled:nth-of-type(1) li");
	private By productPriceLoc = By.cssSelector("div#content .list-unstyled:nth-of-type(2) li");
	private By quantityLoc = By.id("input-quantity");
	private By addToCartLoc = By.id("button-cart");
	private By productImgLoc = By.cssSelector("ul.thumbnails img");
		
	
	
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
	
	public Map<String, String> getProductInfo() {
		
		Map<String, String> productInfoMap = new HashMap<String, String>();
		productInfoMap.put("Name", getProductName().trim());
		List<WebElement> productMetaDataEle = elementUtil.getElements(productMetaDataLoc);
		
		for(WebElement we: productMetaDataEle) {
			System.out.println(we.getText());
			String meta[] = we.getText().split(":");
			String key = meta[0].trim();
			String value = meta[1].trim();
			productInfoMap.put(key, value);
		}
		
		List<WebElement> productPriceEle = elementUtil.getElements(productPriceLoc);
		productInfoMap.put("Price", productPriceEle.get(0).getText().trim());
		productInfoMap.put("ExTax", productPriceEle.get(1).getText().split(":")[1].trim());

		return productInfoMap;
	}
	
	public void selectQuantity(String qty) {
		elementUtil.doSendKeys(quantityLoc, qty);
	}
	
	public void addToCart() {
		elementUtil.doClick(addToCartLoc);
	}
	
	public int getProductImages() {
		int imageCount = elementUtil.getElements(productImgLoc).size();
		System.out.println("Total number of product images are: " + imageCount);
		return imageCount;
	}

}
