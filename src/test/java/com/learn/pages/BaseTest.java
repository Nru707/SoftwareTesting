package com.learn.pages;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import com.learn.utility.BrowserFactory;
import com.learn.utility.configDataProvider;

public class BaseTest {

	protected WebDriver driver;
	protected configDataProvider config;

	@BeforeClass
	public void setUp() {
		config = new configDataProvider();
		driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingUrl());

		if (driver == null) {
			throw new RuntimeException("WebDriver initialization failed. Check BrowserFactory logs.");
		}
	}

	@AfterClass
	public void tearDown() {
		BrowserFactory.quitBrowser(driver);
	}
}
