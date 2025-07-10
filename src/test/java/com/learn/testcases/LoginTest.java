package com.learn.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.learn.pages.BaseTest;
import com.learn.pages.LoginPage;

public class LoginTest extends BaseTest {
	
	@Test
	public void verifyLogin() {
		LoginPage lp = new LoginPage(driver);
		String username = "Demo12";
		String password = "Test123456$";
		lp.loginPortal(username, password);
		WebElement dashboard = driver.findElement(By.linkText("Dashboard"));
		Assert.assertTrue(dashboard.isDisplayed(), "Login passed but Dashboard link not visible");
	}

}
