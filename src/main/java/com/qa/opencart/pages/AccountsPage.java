package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By accountPageHeaderLoc = By.cssSelector("div#logo a");
	private By h2HeadLoc = By.cssSelector("div#content h2");
	private By searchTxtLoc = By.cssSelector("div#search input[name='search']");
	private By searchBtnLoc = By.cssSelector("div#search button[type='button']");
	private By resultLoc = By.cssSelector("div.caption h4 a");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	public String getAccountsPageTitle() {
		//return driver.getTitle();
		return elementUtil.doGetTitle();
	}
	
	public String getAccountPageHeader() {
		//return driver.findElement(accountPageHeaderLoc).getText();
		return elementUtil.doGetText(accountPageHeaderLoc);
	}
	
	public int getAccountSectionsCount() {
		//List<WebElement> h2HeadEle = driver.findElements(h2HeadLoc);
		List<WebElement> h2HeadEle = elementUtil.getElements(h2HeadLoc);
		return h2HeadEle.size();
	}
	
	public List<String> getAccountSectionsList() {
		//List<WebElement> h2HeadEle = driver.findElements(h2HeadLoc);
		List<WebElement> h2HeadEle = elementUtil.getElements(h2HeadLoc);
		List<String> h2HeadList = new ArrayList<>();
		for (WebElement we:h2HeadEle) {
			h2HeadList.add(we.getText());
		}
		return h2HeadList;
	}
	
	public int doSearch(String productName) {
		elementUtil.doSendKeys(searchTxtLoc, productName);
		elementUtil.doClick(searchBtnLoc);
		List<WebElement> resultEle = elementUtil.getElements(resultLoc);
		return resultEle.size();
	}
	
	public ProductDetailsPage selectProductFromResults(String searchProduct, String selectProduct) {
		elementUtil.doSendKeys(searchTxtLoc, searchProduct);
		elementUtil.doClick(searchBtnLoc);
		List<WebElement> resultEle = elementUtil.getElements(resultLoc);
		for (WebElement we:resultEle) {
			if (we.getText().equals(selectProduct)) {
				we.click();
				break;
			}
		}
		return (new ProductDetailsPage(driver));
	}

}
