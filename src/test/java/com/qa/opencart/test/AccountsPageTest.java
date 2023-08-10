package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{
	@BeforeClass
	public void accPageLogin() {
		accPage = lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccountspageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}
	@Test
	public void accPageURLTest() {
		String actURL = accPage.getAccountsPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.ACCOUNTS_PAGE_URL_VALUE));
	}
	@Test
	public void searchExistTest() {
		Assert.assertTrue(accPage.searchExist());
	}
	@Test
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.logoutLinkExist());
	}
	@Test
	public void accPageHeadersCountTest() {
		Assert.assertEquals(accPage.getAccPageHeadersCount(), 4);
	}
	@Test
	public void accPageHeadersTest() {
		List<String> actList  =accPage.getAccPageHeadersList();
		System.out.println("Acc page: "+actList);
		Assert.assertEquals(actList,AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
	}
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"macbook"},
			{"IMac"},
			{"Samsung"},
			{"Apple"}
		};
	}
	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey) {
		searchPage = accPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getProductsCount()>0);
	}
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"macbook","MacBook Air"},
			{"Apple","Apple Cinema 30\""},
			{"samsung","Samsung SyncMaster 941BW"},
			{"macbook","MacBook Pro"}
		};
		
	}
	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String searchKey,String prodName) {
		searchPage = accPage.performSearch(searchKey);
		if(searchPage.getProductsCount()>0) {
			proInfo = searchPage.selectProduct(prodName);
			Assert.assertEquals(proInfo.getProductHeader(), prodName);
		}
	}
	
	

}
