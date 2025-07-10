package com.learn.utility;

import java.io.File;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.*;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestListener implements ITestListener {

	private ExtentReports reports;
	private ExtentTest test;

	public void configureReport() {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("ExtentListenerReportDemo.html");
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);

		reports.setSystemInfo("Machine", "RaviPc");
		reports.setSystemInfo("OS", "Windows11");

		htmlReporter.config().setDocumentTitle("Extent Listener Report Demo");
		htmlReporter.config().setReportName("This is my first Report");
		htmlReporter.config().setTheme(Theme.DARK);
	}

	@Override
	public void onStart(ITestContext context) {
		configureReport();
		System.out.println("Test Suite Started.");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test Suite Finished.");
		reports.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = reports.createTest(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Passed: " + result.getName());
		test.pass(MarkupHelper.createLabel("Test Passed: " + result.getName(), ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed: " + result.getName());
		test.fail(MarkupHelper.createLabel("Test Failed: " + result.getName(), ExtentColor.RED));

		String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + result.getName() + ".png";
		File file = new File(screenshotPath);

		if (file.exists()) {
			try {
				test.fail("Screenshot:", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			} catch (Exception e) {
				test.warning("Screenshot could not be attached.");
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped: " + result.getName());
		test.skip(MarkupHelper.createLabel("Test Skipped: " + result.getName(), ExtentColor.YELLOW));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Optional to implement
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}
}
