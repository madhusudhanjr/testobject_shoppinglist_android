package com.testobject.demo.testng.tests;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.testobject.demo.pageobjects.ShoppingListPage;

/**
 * Test class to test Shopping List App functionalities
 * 
 * @author Madhusudhan (madhusudhan.jr@gmail.com)
 *
 */
public class ShoppingListTest extends BaseTest {

	/**
	 * Class variable reference to ShoppingList Page Object
	 */
	private ShoppingListPage m_shoppingListPage;

	/**
	 * This Test Method creates shopping List by adding few items and delete the
	 * shopping list.
	 * 
	 * @throws Exception
	 */
	@Test(priority = 0)
	public void createAndDeleteShoppingList() throws Exception {

		System.out
				.println("START OF TEST CASE:: createAndDeleteShoppingList()");

		m_shoppingListPage = new ShoppingListPage(getAppiumDriver());

		System.out.println("Creating New List");
		m_shoppingListPage.createNewList("FRUITS");

		System.out.println("Adding Items to the List");
		m_shoppingListPage
				.addItems(Arrays.asList("Apple", "Orange", "Mangoes"));

		System.out.println("Check and Delete item in the List");
		m_shoppingListPage.checkAndDeleteItem();

		System.out.println("Delete List!!");
		m_shoppingListPage.deletCreatedList();

	}

	/**
	 * This Test Method is used to create multiple shopping lists
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void createMultipleShoppingLists() throws Exception {

		System.out
				.println("START OF TEST CASE:: createMultipleShoppingLists()");

		m_shoppingListPage = new ShoppingListPage(getAppiumDriver());

		System.out.println("Create New List");
		m_shoppingListPage.createNewList("GROCERIES");
		System.out.println("Add Items to the List");
		m_shoppingListPage.addItems(Arrays.asList("Rice", "Wheat", "Oil"));

		System.out.println("Create New List");
		m_shoppingListPage.createNewList("SNACKS");
		System.out.println("Add Items to the List");
		m_shoppingListPage.addItems(Arrays.asList("Bread", "Butter", "Jam"));

		System.out.println("Display Lists");
		m_shoppingListPage.displayAllLists();
	}

}
