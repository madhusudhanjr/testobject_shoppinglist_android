package com.testobject.demo.testng.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testobject.appium.common.TestObjectCapabilities;
import org.testobject.appium.testng.AppiumDriverProvider;
import org.testobject.appium.testng.TestObjectTestNGTestResultWatcher;

/**
 * BaseTest Class holds common methods to execute before and after executing the
 * Test Cases
 * 
 * @author Madhusudhan (madhusudhan.jr@gmail.com)
 */
@Listeners({ TestObjectTestNGTestResultWatcher.class })
public class BaseTest implements AppiumDriverProvider {

	/**
	 * Class variable which holds the reference to the AppiumDriver Object
	 */
	private AppiumDriver<MobileElement> m_driver;

	/**
	 * API_ENDPOINT points to the Test Object URL to to connect to Appium server
	 * NEW END POINT URL:: http://appium.testobject.com/wd/hub
	 */
	private static final String API_ENDPOINT = "http://appium.testobject.com";

	/**
	 * Before Test executes before executing any Tests (contains nor or more
	 * Test Methods)
	 * 
	 * @param apiKey
	 * @param appID
	 * @param deviceID
	 * @throws MalformedURLException
	 */
	@Parameters({ "apiKey", "appID", "deviceID" })
	@BeforeTest(alwaysRun = true)
	public void beforeTest(String apiKey, String appID, String deviceID)
			throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability(TestObjectCapabilities.TESTOBJECT_API_KEY, apiKey);
		capabilities.setCapability(TestObjectCapabilities.TESTOBJECT_APP_ID, appID);
		capabilities.setCapability(TestObjectCapabilities.TESTOBJECT_DEVICE, deviceID);
		capabilities.setCapability(TestObjectCapabilities.TESTOBJECT_APPIUM_VERSION, "1.5.3");
		capabilities.setCapability(TestObjectCapabilities.TESTOBJECT_SUITE_NAME, "MADHU_APP_TEST");
		capabilities.setCapability(TestObjectCapabilities.TESTOBJECT_TEST_NAME, "MADHU_SHOPPING_TEST");

		System.out
				.println("Connecting to Test Object Appium server with URL:: "
						+ "http://appium.testobject.com/wd/hub");
		m_driver = new AndroidDriver<MobileElement>(new URL(API_ENDPOINT
				+ "/wd/hub"), capabilities);

		m_driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		System.out.println("Connection established successfully!!");

	}

	/**
	 * Quits driver after executing all Test Methods which are part of Test
	 */
	@AfterTest(alwaysRun = true)
	public void tearDown() {
		m_driver.quit();
		System.out.println("Driver quit success!!");
		System.out.println("Completed Test Suite Execution successfully!!");
	}

	public AppiumDriver<MobileElement> getAppiumDriver() {
		return this.m_driver;
	}
}
