package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	private By email = By.id("input-email");
	private By pwd = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By search = By.name("search");
	private By forgotPwd = By.linkText("Forgotten Password");
	private By register = By.linkText("Register");
	
	public String getLoginPageTitle() {
		String loginPagetile = eleUtil.waitForTitleIsandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("LoginPageTitle: "+loginPagetile);
		return loginPagetile;
	}
	public String getLoginPageUrl() {
		String loginPageURL = eleUtil.waitForURLContainsandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_VALUE);
		System.out.println("LoginPageURL: "+loginPageURL);
		return loginPageURL;
	}
	public boolean isforgotPwdLinkExistornot() {
		boolean flag = eleUtil.waitForElementVisible(forgotPwd, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
		return flag;
	}
	public boolean searchExitsOrnot() {
		boolean flag = eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
		return flag;
	}
	public AccountsPage doLogin(String emailid,String password) {
		eleUtil.waitForElementVisible(email, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(emailid);
		eleUtil.doSendKeys(pwd, password);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	public RegisterPage navigateToRegPage() {
		eleUtil.doClick(register);
		return new RegisterPage(driver);
	}

}
