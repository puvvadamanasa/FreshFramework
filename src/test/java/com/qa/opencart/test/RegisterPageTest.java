package com.qa.opencart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.XLUtil;

public class RegisterPageTest extends BaseTest{
	@BeforeClass
	public void RegisterPageSetUp() {
		register = lp.navigateToRegPage();
	}
	public String getRandomEmail() {
		Random random = new Random();
		//String email = "automation"+random.nextInt(1000)+"@gmail.com";
		String email = "automation"+System.currentTimeMillis()+"@gmail.com";
		return email;
		
	}
	@DataProvider
	public Object[][] getRegisterData() {
		return XLUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		
	}
	@Test(dataProvider = "getRegisterData")
	public void registerTest(String firstName,String lastName,String telephone,String password,String subscribe) {
		Assert.assertTrue(register.registerUser(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
	}

}
