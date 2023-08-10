package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest{
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actTitle = lp.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	@Test(priority=2)
	public void loginPageURLTest() {
		String actUrl = lp.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_URL_VALUE));
	}
	@Test(priority=3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(lp.isforgotPwdLinkExistornot());
	}
	@Test(priority=4)
	public void searchExistTest() {
		Assert.assertTrue(lp.searchExitsOrnot());
	}
	@Test(priority=5)
	public void loginTest() {
		accPage = lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.logoutLinkExist());	
	}

}
