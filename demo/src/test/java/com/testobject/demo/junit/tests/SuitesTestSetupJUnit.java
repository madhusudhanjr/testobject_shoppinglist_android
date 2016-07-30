package com.testobject.demo.junit.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.Arrays;
import java.util.UUID;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testobject.appium.common.TestObject;
import org.testobject.appium.junit.TestObjectAppiumSuite;
import org.testobject.appium.junit.TestObjectTestResultWatcher;

import com.testobject.demo.pageobjects.ShoppingListPage;

@TestObject(testObjectApiKey = "D86DD6B969294904817FE4B8A31652FC", testObjectSuiteId = 456)
@RunWith(TestObjectAppiumSuite.class)
public class SuitesTestSetupJUnit {

	private static final String API_ENDPOINT = "http://appium.testobject.com";
	ShoppingListPage objShoppingListPage;

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

		capabilities.setCapability("testobject_app_id", "1");
		capabilities.setCapability("testobject_device", "LG_Nexus_4_E960_real");
		capabilities.setCapability("testobject_appium_version", "1.5.3");

		capabilities.setCapability("testobject_api_key",
				resultWatcher.getApiKey());
		capabilities.setCapability("testobject_test_report_id",
				resultWatcher.getTestReportId());

		// We generate a random UUID for later lookup in logs for debugging
		String testUUID = UUID.randomUUID().toString();
		System.out.println("TestUUID: " + testUUID);
		capabilities.setCapability("testobject_testuuid", testUUID);

		String cacheDevice = System.getenv("TESTOBJECT_CACHE_DEVICE");
		if (cacheDevice != null && cacheDevice.trim().isEmpty() == false) {
			capabilities.setCapability("testobject_cache_device", cacheDevice);
		}

		driver = new AndroidDriver(new URL(API_ENDPOINT + "/wd/hub"),
				capabilities);

		resultWatcher.setAppiumDriver(driver);

	}

	/* A simple zero divided by zero operation. */
	@Test
	public void zerosDivisionOperation() {

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
