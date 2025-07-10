package com.learn.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class configDataProvider {
	Properties pro;

	public configDataProvider() {
		File src = new File("./Configuration/config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);

			pro = new Properties();

			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Not able to load config file " + e.getMessage());
		}
	}

	public String getBrowser() {
		return pro.getProperty("browser");
	}

	public String getStagingUrl() {
		return pro.getProperty("testurl");
	}
}