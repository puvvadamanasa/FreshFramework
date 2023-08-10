package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> metaVal;
	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By quant = By.id("input-quantity");
	private By cartSuccMsg = By.cssSelector("div.alert.alert-success");
	private By addToCartBtn = By.id("button-cart");
	private By productMetaData= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPricingData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public String getProductHeader() {
		String proHeader = eleUtil.doElementGetText(productHeader);
		System.out.println("Product header: "+proHeader);
		return proHeader;
	}
	public int getProductImages() {
		int imagesCount = eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Images Count: "+imagesCount);
		return imagesCount;
	}
	public Map<String, String> getProductData() {
		//metaVal = new HashMap<String, String>();
		metaVal = new LinkedHashMap<String, String>();//same sequence
		//metaVal = new TreeMap<String, String>();//sorting order
		metaVal.put("productName", getProductHeader());
		 getProductMetaData();
		 getProductPriceData();
		return metaVal;
	}

	private void getProductMetaData() {
		List<WebElement> metaList=eleUtil.getElements(productMetaData);
		for(WebElement e:metaList) {
			String text = e.getText();
			String[] metaData = text.split(":");
			String key = metaData[0].trim();
			String value = metaData[1].trim(); 
			metaVal.put(key, value);
		}
	}

	private void getProductPriceData() {
		List<WebElement> metaPrice = eleUtil.getElements(productPricingData);
		String price = metaPrice.get(0).getText();
		String tax = metaPrice.get(1).getText();
		metaVal.put("productPrice", price);
		String taxVal = tax.split(":")[1].trim();
		metaVal.put("exTax", taxVal);
	}
	public void addToCart(int quantity) {
		eleUtil.doSendKeys(quant, String.valueOf(quantity));
		
	}
	public String clickOnAddToCart() {
		eleUtil.doClick(addToCartBtn);
		String succMsg = eleUtil.waitForElementVisible(cartSuccMsg, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
		succMsg = succMsg.substring(0, succMsg.length()-1).replace("\n", "");
		System.out.println("Success msg: "+succMsg);
		return succMsg;
	}

}
