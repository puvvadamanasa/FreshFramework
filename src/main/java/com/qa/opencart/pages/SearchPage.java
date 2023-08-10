package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By searchResult = By.cssSelector("div#content div.product-layout");
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public ProductInfoPage selectProduct(String productName) {
		By product = By.linkText(productName);
		eleUtil.waitForElementVisible(product, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);	
	}
	public int getProductsCount() {
		int size = eleUtil.waitForElementsVisible(searchResult, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Size: "+size);
		return size;
	}
	
	

}
