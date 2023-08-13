package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By search = By.name("search");
	private By logout = By.linkText("Logout");
	private By headers = By.cssSelector("div#content h2");
	private By searchIcon = By.cssSelector("div#search button");
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public String getAccountspageTitle() {
		String accTitle = eleUtil.waitForTitleIsandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		System.out.println("Accounts Page Title is: "+accTitle);
		return accTitle;
	}
	public String getAccountsPageURL() {
		String accURL = eleUtil.waitForURLContainsandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_VALUE);
		System.out.println("Accounts Page URL: "+accURL);
		return accURL;
	}
	public boolean searchExist() {
		boolean flag = eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
		return flag;
	}
	public boolean logoutLinkExist() {
		boolean flag = eleUtil.waitForElementVisible(logout, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
		return flag;
	}
	public int getAccPageHeadersCount() {
		return eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
	}
	public List<String> getAccPageHeadersList() {
		List<String> accHeadersList = new ArrayList<String>();
		List<WebElement> accHeadersValList =eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		for(WebElement e:accHeadersValList) {
			String text = e.getText();
			accHeadersList.add(text);
		}
		return accHeadersList;
	}
	public SearchPage performSearch(String searchKey) {
		if(searchExist()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchPage(driver);
		}
		else {
			System.out.println("Search does not exist");
			return null;
		}
	}

}
