package com.testobject.demo.junit.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testobject.appium.common.TestObject;
import org.testobject.appium.common.TestObjectCapabilities;
import org.testobject.appium.junit.TestObjectAppiumSuite;
import org.testobject.appium.junit.TestObjectTestResultWatcher;

import com.testobject.demo.pageobjects.ShoppingListPage;

/* You must add these two annotations on top of your test class. */
@TestObject(testObjectApiKey = "D86DD6B969294904817FE4B8A31652FC", testObjectSuiteId = 1, testObjectAppId = 1, testObjectDeviceIds = "LG_Nexus_4_E960_real", testObjectApiEndpoint = "http://appium.testobject.com")
@RunWith(TestObjectAppiumSuite.class)
public class WatcherTestSetup {

	ShoppingListPage objShoppingListPage;
	/*
	 * This is the key piece of our test, since it allows us to connect to the
	 * device we will be running the app onto.
	 */
	private AppiumDriver driver;

	/* Sets the test name to the name of the test method. */
	@Rule
	public TestName testName = new TestName();

	/* Takes care of sending the result of the tests over to TestObject. */
	@Rule
	public TestObjectTestResultWatcher resultWatcher = new TestObjectTestResultWatcher();

	/* This is the setup that will be run before the test. */
	@Before
	public void setUp() throws Exception {

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability(TestObjectCapabilities.TESTOBJECT_API_KEY,
				resultWatcher.getApiKey());
		capabilities.setCapability(
				TestObjectCapabilities.TESTOBJECT_TEST_REPORT_ID,
				resultWatcher.getTestReportId());

		/*
		 * capabilities.setCapability("testobject_api_key",
		 * "D86DD6B969294904817FE4B8A31652FC");
		 * capabilities.setCapability("testobject_app_id", "1");
		 * capabilities.setCapability("testobject_device",
		 * "LG_Nexus_4_E960_real");
		 * capabilities.setCapability("testobject_appium_version", "1.5.3");
		 */

		driver = new AndroidDriver(new URL(
				"http://appium.testobject.com/wd/hub"), capabilities);

		/*
		 * IMPORTANT! We need to provide the Watcher with our initialized
		 * AppiumDriver
		 */
		resultWatcher.setAppiumDriver(driver);

	}

	@Test
	public void testMethod() {
		System.out
				.println("START OF TEST CASE:: createAndDeleteShoppingList()");

		objShoppingListPage = new ShoppingListPage(driver);

		System.out.println("Creating New List");
		objShoppingListPage.createNewList("FRUITS");

		System.out.println("Adding Items to the List");
		objShoppingListPage.addItems(Arrays
				.asList("Apple", "Orange", "Mangoes"));

		System.out.println("Check and Delete item in the List");
		objShoppingListPage.checkAndDeleteItem();

		System.out.println("Delete List!!");
		objShoppingListPage.deletCreatedList();
	}

}