package com.learn.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.learn.pages.BaseTest;
import com.learn.pages.LoginPage;
import com.learn.utility.ReadExcelfile;

public class LoginTestDriverData extends BaseTest {

	String fileName = System.getProperty("user.dir") + "/TestData/TestData.xlsx";

	@Test(priority = 1, dataProvider = "LoginDataProvider")
	public void verifyLogin(String userEmail, String userPwd) {
		LoginPage lp = new LoginPage(driver);

		lp.loginPortal(userEmail, userPwd);

		WebElement dashboard = driver.findElement(By.linkText("Dashboard"));
		Assert.assertTrue(dashboard.isDisplayed(), "Login passed but Dashboard link not visible");

		lp.logout();
	}

	@DataProvider(name = "LoginDataProvider")
	public String[][] LoginDataProvider() {

		int ttlRows = ReadExcelfile.getRowCount(fileName, "Sheet1");
		int ttlColumns = ReadExcelfile.getColCount(fileName, "Sheet1");

		String[][] data = new String[ttlRows - 1][ttlColumns];

		for (int i = 1; i < ttlRows; i++) {
			for (int j = 0; j < ttlColumns; j++) {
				data[i - 1][j] = ReadExcelfile.getCellValue(fileName, "Sheet1", i, j);
			}
		}

		return data;
	}
}
