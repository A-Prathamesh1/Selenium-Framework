package com.inetbanking.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	@Test(testName = "Login Test")
	public void loginTest() {
		driver.get(APP_URL);
		log.info("URL opened");

		log.warn("test warning");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserID(user_name);
		log.info("User name entered");

		loginPage.setPassword(password);
		log.info("Password entered");

		loginPage.clickSubmit();
		log.info("submit button clicked");

		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			log.info("login test passed");
		} else {
			Assert.assertTrue(false);
			log.info("login test failed");
		}
	}

}
