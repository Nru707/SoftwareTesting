package com.learn.utility;

import java.time.Duration;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	public static WebDriver startApplication(WebDriver driver, String browserName, String appUrl) {

		switch (browserName.toLowerCase()) {
		case "chrome": {
			WebDriverManager.chromedriver().setup();

			String userProfile = System.getProperty("java.io.tmpdir") + "/chrome_profile_" + UUID.randomUUID();

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--user-data-dir=" + userProfile); // Unique for each session
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--no-sandbox"); // Required when running as root
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-gpu"); // Safe fallback
			options.addArguments("--disable-extensions");

			// If needed:
			// options.addArguments("--headless=new");

			driver = new ChromeDriver(options);
			break;
		}

		case "firefox": {
			WebDriverManager.firefoxdriver().clearResolutionCache().setup();

			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setBinary("/opt/firefox-latest/firefox");
			firefoxOptions.addArguments("--no-sandbox");
			firefoxOptions.addArguments("--disable-dev-shm-usage");

			System.out.println("✅ FirefoxOptions: " + firefoxOptions.toString());

			driver = new FirefoxDriver(firefoxOptions);
			break;
		}

		case "ie": {
			WebDriverManager.iedriver().setup();
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			driver = new InternetExplorerDriver(ieOptions);
			break;
		}

		default: {
			throw new IllegalArgumentException("❌ Browser not supported: " + browserName);
		}
		}

		driver.manage().window().maximize();
		driver.get(appUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		return driver;
	}

	public static void quitBrowser(WebDriver driver) {
		if (driver != null) {
			driver.quit();
		}
	}
}
