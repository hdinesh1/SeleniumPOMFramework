package com.qa.opencart.utils;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

//Class with Custom Wrapper Methods
public class ElementUtil {
	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	private Alert alert;
	
	/**
	 * Constructor to pass the WebDriver Object to this Utility
	 * @param driver
	 */
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(this.driver);
	}
	
	/**
	 * This method takes the By locator as parameter and returns the WebElement by calling findElement() 
	 * @param By locator
	 * @return WebElement
	 */
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if (DriverFactory.highlight.equals("true")) {
			jsUtil.flash(element);
		}
		return element;
	}
	
	/**
	 * This method takes the By locator as parameter and returns the list of WebElement by calling findElements()
	 * @param By locator
	 * @return List<WebElements>
	 */
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	/**
	 * This method takes the By locator and String value to send the string to the WebElement identified by locator. Calls sendKeys()
	 * @param By locator
	 * @param String str
	 */
	public void doSendKeys(By locator, String str) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(str);
	}
	
	/**
	 * This method clears the text present in an element whose locator has been passed.
	 * @param By locator
	 */
	public void clearElementText(By locator) {
		getElement(locator).clear();
	}
	/**
	 * This method returns the text of the WebElement whose locator has been passed. Call getText()
	 * @param locator of WebElement
	 * @return String containing text
	 */
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	/**
	 * This methods clicks on the WebElement whose By locator is passed as parameter. Calls click()
	 * @param By locator
	 */
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	/**
	 * This methods checks whether the element whose locator is passed is displayed or not.
	 * @param locator
	 * @return true if element is displayed else false
	 */
	public boolean checkDisplay(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	/**
	 * This method returns the title of current page
	 * @return
	 */
	public String doGetTitle() {
		return driver.getTitle();
	}
	
	public void doSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	
	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	/**
	 * Generic method to select the dropdown value based on String method passed. Dropdown is identified using By locator of WebElement and value to the selected is passed as String
	 * @param By locator
	 * @param String method
	 * @param String value
	 */
	public void selectDropdownByDiffOptions(By locator, String method, String value) {
		//Select select = new Select(getElement(locator));
		switch (method) {
		case "index":
			doSelectByIndex(locator, Integer.parseInt(value));
			break;
		case "value":
			doSelectByValue(locator, value);
			break;
		case "visibletext":
			doSelectByVisibleText(locator, value);
			break;
		}		
	}
	
	/**
	 * This method returns all the options in a Select list of the WebElement whose By locator is passed. 
	 * @param By locator
	 * @return List<WebElement>
	 */
	public List<WebElement> doSelectGetOptions(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions();
	}
	
	/**
	 * This will select a value from dropdown but without using select class
	 * @param locator 
	 * @param value - String value to be selected from dropdown
	 */
	public void selectDropDownValue(By locator, String value) {
		List<WebElement> element = getElements(locator);
		System.out.println("Size of element list: " + element.size());
		for (WebElement we : element) {
			System.out.println(we.getText());
			if (we.getText().equalsIgnoreCase(value)) {
				we.click();
				break;
			}
		}
	}
	
	/**
	 * This will select single, multiple or ALL values from a choice List drop down whose locator is passed. 
	 * Pass single value in String for Single Selection from drop down
	 * Pass multiple string values for multiple selection from drop down
	 * Pass "ALL" to selection all values from drop down
	 * @param locator
	 * @param string[]
	 */
	public void selectChoiceFromDropDown(By locator, String...str) {
		List<WebElement> choiceList = getElements(locator);
		int size = choiceList.size();
		System.out.println("Size of element list: " + size);
		if (str[0].equalsIgnoreCase("ALL")) {
			for (WebElement we : choiceList) {
				if (!we.getText().isEmpty()) {
					we.click();
				}
				/*OR use TRY CATCH Block
				//try{
					for (WebElement we : choiceList) {
						if (!we.getText().isEmpty()) {
							we.click();
						}
					}
				} catch (Exception e){
				}
				*/
			}
		}
		else {
			for (int i=0;i<choiceList.size();i++) {
				String choiceText = choiceList.get(i).getText();
				for (int j=0;j<str.length;j++) {
					if (choiceText.equalsIgnoreCase(str[j])) {
						choiceList.get(i).click();
						break; //breaks inner loop
					}
				}
			}	
		}
	}
//*********************************************************Action Class Utilities *********************************************************	
	/**
	 * This method will hover over mouse on element whose location has been passed as parameter.
	 * @param locator
	 */
	public void doMoveToElement(By locator) {
		WebElement menuEle = getElement(locator);
		Actions act = new Actions(driver);
		act.moveToElement(menuEle).perform();
	}
	
	/**
	 * This method will RightClick on WebElement whose By locator has been passed. Calls Actions Class contextClick()
	 * @param locator
	 */
	public void doRightClick(By locator) {
		WebElement rightClickEle = getElement(locator);
		Actions act = new Actions(driver);
		act.contextClick(rightClickEle).perform();
	}
	
	/**
	 * This method will return a list of String with menu options shown after righclicking a WebElement
	 * @param By locator of menu items
	 * @return List<String>
	 */
	public List<String> getRightClickMenuList(By locator) {
		List<String> rightClickMenuList = new ArrayList<String>();
		List<WebElement> rightClickPopUpEle = getElements(locator);
		for (WebElement we: rightClickPopUpEle) {
			String text = we.getText();
			rightClickMenuList.add(text);
		}
		return rightClickMenuList;
	}
	
	/**
	 * This method will click on value passed from right click pop up menu of a WebElement that is already clicked
	 * @param locator
	 * @param value
	 */
	public void selectRightClickOption(By locator, String value) {
		List<WebElement> rightClickPopUpEle = getElements(locator);
		System.out.println("Size of element list: " + rightClickPopUpEle.size());
		for (WebElement we : rightClickPopUpEle) {
			System.out.println(we.getText());
			if (we.getText().equalsIgnoreCase(value)) {
				we.click();
				break;
			}
		}
	}
	
	/**
	 * This method will right click on element whose By locator is passed as rightClickLoc and returns a list of String with menu options shown after right clicking the WebElement
	 * @param rightClickLoc locator of WebElement to be rightClicked
	 * @param locator of menu elements after right clicking
	 * @return List<String> with all menu options text
	 */
	public List<String> rightClickAndMenuList(By rightClickLoc, By locator) {
		doRightClick(rightClickLoc);
		List<String> rightClickMenuList = new ArrayList<String>();
		List<WebElement> rightClickPopUpEle = getElements(locator);
		for (WebElement we: rightClickPopUpEle) {
			String text = we.getText();
			rightClickMenuList.add(text);
		}
		return rightClickMenuList;
	}
	
	/**
	 * This method will right click on element whose By locator is passed as rightClickLoc and clicks on right Click menu option passed as Value. By locator of right click menu option also need to be passed
	 * @param rightClickLoc locator of WebElement to be rightClicked
	 * @param locator locator of menu elements after right clicking
	 * @param value String value that need to be clicked/selected from right click pop up menu
	 */
	public void rightClickAndSelectOption(By rightClickLoc, By locator, String value) {
		doRightClick(rightClickLoc);
		List<WebElement> rightClickPopUpEle = getElements(locator);
		System.out.println("Size of element list: " + rightClickPopUpEle.size());
		for (WebElement we : rightClickPopUpEle) {
			System.out.println(we.getText());
			if (we.getText().equalsIgnoreCase(value)) {
				we.click();
				break;
			}
		}
	}
	
	/**
	 * Calls Action Class sendKeys() method to send string value passed to the web element whose locator is also passed
	 * @param locator of webElement passed
	 * @param str String value that need to be passed
	 */
	public void doActionSendKeys(By locator, String str) {
		Actions act = new Actions(driver);
		WebElement element = getElement(locator);
		act.sendKeys(element, str).perform();
		
	}
	
	/**
	 * Calls Action Class click() method to click on the web element whose locator is passed
	 * @param locator of WebElement to be clicked
	 */
	public void doActionClick(By locator) {
		Actions act = new Actions(driver);
		WebElement element = getElement(locator);
		act.click(element).perform();
	}
	
	//*********************************************************Alert Utilities ****************************************************************
	
	/**
	 * Switches to alert pop up. Calls switchTo()
	 */
	public void doSwitchToAlert() {
		alert = driver.switchTo().alert();
	}
	
	/**
	 * Switches to alert and return the text of alert and switches back to default content. Calls getText()
	 * @return String of alert text
	 */
	public String getAlertText() {
		doSwitchToAlert();
		String str = alert.getText();
		alert.accept();
		doSwitchToDefaultContent();
		return str;
	}
	
	/**
	 * Switches to Alert and accepts the alert. It also switches back to default Content
	 */
	public void acceptAlert() {
		doSwitchToAlert();
		alert.accept();
		doSwitchToDefaultContent();
	}
	
	/**
	 * Switches to Alert and dismisses the alert. It also switches back to default Content
	 */
	public void dismissAlert() {
		doSwitchToAlert();
		alert.dismiss();
		doSwitchToDefaultContent();
	}
	
	public void sendTextToAlertAccept(String str) {
		doSwitchToAlert();
		alert.sendKeys(str);
		alert.accept();
		doSwitchToDefaultContent();
	}
	
	public void sendTextToAlertCancel(String str) {
		doSwitchToAlert();
		alert.sendKeys(str);
		alert.dismiss();
		doSwitchToDefaultContent();
	}
	
	public void doSwitchToDefaultContent() {
		driver.switchTo().defaultContent();
	}
	
	//*********************************************************Wait Utilities ****************************************************************
	/**
	 * This method will wait for title to be displayed till the time given in timeout is passed. This methods checks whether title contains the String passed and returns the title of the page 
	 * @param title that should be part of page title
	 * @param timeout in seconds till when program will wait
	 * @return String contains title of the page
	 */
	public String waitForPageTitleContains(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}
	
	/**
	 * This method will wait for title to be displayed till the time given in timeout is passed. This methods checks whether title matches the String passed and returns the title of the page. The match is case sensitive. 
	 * @param title that should be part of page title
	 * @param timeout in seconds till when program will wait
	 * @return String contains title of the page
	 */
	public String waitForPageTitleMatch(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}
	
	/**
	 * This method will wait for url to be displayed till the time given in timeout is passed. This methods checks whether url contains the String passed and returns the url of the page 
	 * @param title that should be part of page title
	 * @param timeout in seconds till when program will wait
	 * @return String contains title of the page
	 */
	public String waitForPageURL(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.urlContains(url));
		return driver.getCurrentUrl();
	}
}

