package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {
	private WebDriver driver;
	private JavaScriptUtil jutil;
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jutil = new JavaScriptUtil(driver);
	}
	public WebElement getElement(By locator) {
		WebElement ele = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
		jutil.flash(ele);
		}
		return ele;
	}
	public WebElement getElement(By locator,int timeout) {
		return waitForElementVisible(locator, timeout);
	}
	public void doSendKeys(By locator,String value) {
		WebElement ele = getElement(locator);
		ele.clear();
	    ele.sendKeys(value);
	}
	public void doClick(By locator) {
		getElement(locator).click();
	}
	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}
	public boolean doElementIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	public String getElementAttribute(By locator,String attrName) {
		return getElement(locator).getAttribute(attrName);
	}
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	public void getElementAttributes(By locator,String attrName) {
		List<WebElement> eleList = getElements(locator);
		for(WebElement e:eleList) {
			String attrValue = e.getAttribute(attrName);
			System.out.println(attrValue);
		}
	}
	public List<String> getElementsTextList(By locator) {
		List<String> eleTextList = new ArrayList<String>();
		List<WebElement> eleList = getElements(locator);
		for(WebElement e:eleList) {
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}
	public void doSearch(By locator,String suggName) {
		List<WebElement> suggList = getElements(locator);
		System.out.println(suggList.size());
		for(WebElement e:suggList) {
			String text = e.getText();
			if(text.contains(suggName)) {
				e.click();
				break;
			}
		}
	}
	public void doActionSendKeys(By locator,String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
	}
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}
	/**
	 * Drop down utils
	 * 
	 */
	public void doSelectDropDownByIndex(By locator,int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
    public void doSelectDropDownByValue(By locator,String value) {
    	Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
    public void doSelectDropDownByVisibleText(By locator,String text) {
    	Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
    public List<WebElement> getDropDownOptionsList(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions();
	}
	public int getTotalDropDownOptions(By locator) {
		return getDropDownOptionsList(locator).size();
	}
	public List<String> getDropDownOptionsTextList(By locator) {
		List<String> optionsVal = new ArrayList<String>();
		List<WebElement> options = getDropDownOptionsList(locator);
		for(WebElement e:options) {
			String text = e.getText();
			optionsVal.add(text);
		}
		return optionsVal;
	}
	public void selectDropDownValue(By locator,String value) {
		List<WebElement> options = getDropDownOptionsList(locator);
		for(WebElement e:options) {
			String text = e.getText();
			if(text.equals(value)) {
				e.click();
				break;
			}
		}
	}
	
	public WebElement waitForElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public List<WebElement> waitForElementsPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	public List<WebElement> waitForElementsVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public Alert waitForAlertPresent(int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		   return wait.until(ExpectedConditions.alertIsPresent());
	}
	public String getAlertText(int timeout) {
		return waitForAlertPresent(timeout).getText();
	}
	public void acceptAlert(int timeout) {
		waitForAlertPresent(timeout).accept();
	}
	public void dismissAlert(int timeout) {
		waitForAlertPresent(timeout).dismiss();
	}
	public void alertSendkeys(int timeout,String value) {
		waitForAlertPresent(timeout).sendKeys(value);
	}
	public String waitForTitleContains(int timeout,String titleFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.titleContains(titleFractionValue));
		 return driver.getTitle();
	}
	public String waitForTitleIsandFetch(int timeout,String titleValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.titleIs(titleValue));
		 return driver.getTitle();
	}
	public String waitForURLContainsandFetch(int timeout,String urlFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.urlContains(urlFractionValue));
		 return driver.getCurrentUrl();
	}
	public boolean waitForURLContains(int timeout,String urlFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.urlContains(urlFractionValue)); 
	}
	public String waitForURLIsandFetch(int timeout,String urlValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.urlToBe(urlValue));
		 return driver.getCurrentUrl();
	}
	public void waitForFrameandSwitchToIt(int timeout,String idOrName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}
	public void waitForFrameandSwitchToItByIndex(int timeout,int frameindex) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameindex));
	}
	public void waitForFrameandSwitchToItByframeElement(int timeout,WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
	}
	public void waitForFrameandSwitchToItByframelocator(int timeout,By loc) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(loc));
	}
	public void clickWhenReady(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	public WebElement waitForElementToBeClickable(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void doClickWithActionsAndWait(int timeOut, By locator) {
		WebElement ele = waitForElementToBeClickable(timeOut, locator);
		Actions act = new Actions(driver);
		act.click(ele).build().perform();
	}
	public WebElement waitForElementPresenceWithFluentWait(int timeOut, int pollingTime, By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.withMessage("...element is not found on the page....");
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));								

	}


}
