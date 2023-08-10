package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{
	@BeforeClass
	public void productInfoPageSetUp() {
		accPage = lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"iMac","iMac",3},
			{"Apple","Apple Cinema 30\"",6},
			{"Samsung","Samsung SyncMaster 941BW",1}
			
		};
	}
	@Test(dataProvider = "getProductImagesTestData")
	public void productImagesCountTest(String searchKey,String prodName,int images) {
		searchPage = accPage.performSearch(searchKey);
		proInfo = searchPage.selectProduct(prodName);	
		int actImagesCount = proInfo.getProductImages();
		Assert.assertEquals(actImagesCount, images);
	}
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"macbook","MacBook Pro"},
		};	
	}
	@Test(dataProvider = "getProductData")
	public void productMetaDataTest(String searchKey,String prodName) {
		searchPage = accPage.performSearch(searchKey);
		proInfo = searchPage.selectProduct(prodName);
		Map<String,String> map = proInfo.getProductData();
		System.out.println(map);
		softassert.assertEquals(map.get("Brand"), "Apple");
		softassert.assertEquals(map.get("Product Code"), "Product 18");
		softassert.assertEquals(map.get("productName"), "MacBook Pro");
		softassert.assertEquals(map.get("productPrice"), "$2,000.00");
		softassert.assertAll();
	}
	@DataProvider
	public Object[][] addToCart() {
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"}
		};
	}
	@Test(dataProvider = "addToCart")
	public void addToCartTest(String searchKey,String prodName) {
		searchPage = accPage.performSearch(searchKey);
		proInfo = searchPage.selectProduct(prodName);
		proInfo.addToCart(1);
		String msg = proInfo.clickOnAddToCart();
		//Success: You have added MacBook Pro to your shopping cart!
		softassert.assertTrue(msg.indexOf("Success")>=0);
		softassert.assertTrue(msg.indexOf(prodName)>=0);
		softassert.assertEquals(msg, "Success: You have added "+prodName+" to your shopping cart!");
		softassert.assertAll();
		
	}
	

}
