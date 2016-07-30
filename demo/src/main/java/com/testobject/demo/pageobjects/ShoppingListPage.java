package com.testobject.demo.pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * ShoppingListPage Page Object class contains all the WebElements present in
 * the Shopping Page and the necessary actions on the WebElements
 * 
 * @author Madhusudhan (madhusudhan.jr@gmail.com)
 */
public class ShoppingListPage {

	/**
	 * Class variable reference for Web Driver
	 */
	AppiumDriver<MobileElement> m_PageDriver;

	/**
	 * All WebElements are identified by @FindBy annotation
	 */
	@FindBy(xpath = "//*[contains(@content-desc,'More options')]")
	WebElement moreOptions;

	@FindBy(xpath = "//*[contains(@text,'New list')]")
	WebElement newList;

	@FindBy(id = "edittext")
	WebElement listName;

	@FindBy(xpath = "//*[contains(@text,'OK')]")
	WebElement okButton;

	@FindBy(id = "autocomplete_add_item")
	WebElement itemName;

	@FindBy(id = "button_add_item")
	WebElement addButton;

	@FindBy(id = "check")
	WebElement itemCheck;

	@FindBy(xpath = "//*[contains(@content-desc,'Clean up list')]")
	WebElement deleteItem;

	@FindBy(xpath = "//*[contains(@text,'Mark all items')]")
	WebElement markAllItems;

	@FindBy(xpath = "//*[contains(@text,'Delete list')]")
	WebElement deleteList;

	@FindBy(id = "spinner_listfilter")
	WebElement listFilter;

	@FindAll({ @FindBy(className = "android.widget.CheckedTextView") })
	public List<WebElement> listView;

	/**
	 * Constructor for ShoppingListPage
	 * 
	 * @param driver
	 */
	public ShoppingListPage(AppiumDriver<MobileElement> driver) {

		this.m_PageDriver = driver;
		// This initElements method will create all WebElements
		AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(
				driver, 15);
		PageFactory.initElements(ajaxElementLocatorFactory, this);

	}

	/**
	 * This method sets the name to create new list
	 * 
	 * @param name
	 */
	public void setListName(String name) {

		listName.clear();
		listName.sendKeys(name);

	}

	/**
	 * This method is used to enter Item name for the created list
	 * 
	 * @param item
	 */
	public void enterItemName(String item) {
		itemName.sendKeys(item);

	}

	/**
	 * This method is used to add Items to the list
	 */
	public void clickAddItemButton() {
		addButton.click();

	}

	/**
	 * This method clicks on New List option
	 */
	public void clickNewList() {
		newList.click();

	}

	/**
	 * This method clicks on OK button of the Alert
	 */
	public void clickOkButton() {
		okButton.click();

	}

	/**
	 * This method clicks on the options icon
	 */
	public void clickOptions() {
		moreOptions.click();

	}

	/**
	 * This method is used to check the Item in the List
	 */
	public void checkItem() {
		itemCheck.click();

	}

	/**
	 * This method is used to delete Item form the list
	 */
	public void deleteItem() {
		deleteItem.click();

	}

	/**
	 * This method is used to mark all items in the list
	 */
	public void markAllItems() {
		markAllItems.click();

	}

	/**
	 * This method is used to delete list
	 */
	public void deleteList() {
		deleteList.click();

	}

	/**
	 * This method is used to handle alert
	 */
	public void handleAlert() {

		clickOkButton();
	}

	/**
	 * This method is used to click on List Filter
	 */
	public void clickFilter() {

		listFilter.click();
	}

	/**
	 * This method is used display all the lists created
	 */
	public void displayAllLists() {

		clickFilter();

		System.out
				.println("No. Of Created Shopping Lists:: " + listView.size());

		for (WebElement element : listView) {

			System.out.println("Created Lists: " + element.getText());
		}
	}

	/**
	 * This method creates new List
	 * 
	 * @param listName
	 */
	public void createNewList(String listName) {

		clickOptions();

		clickNewList();

		setListName(listName);

		handleAlert();

	}

	/**
	 * This method add's item to the List
	 * 
	 * @param item
	 * 
	 */
	public void addItem(String item) {

		enterItemName(item);

		clickAddItemButton();
	}

	/**
	 * This method adds list of items to the list
	 * 
	 * @param items
	 */
	public void addItems(List<String> items) {

		for (String item : items) {

			enterItemName(item);

			clickAddItemButton();
		}
	}

	/**
	 * This method deletes list
	 * 
	 * @param item
	 */
	public void deletCreatedList() {

		clickOptions();

		markAllItems();

		clickOptions();

		deleteList();

		handleAlert();

	}

	/**
	 * This method deletes first item form the list
	 */
	public void checkAndDeleteItem() {

		checkItem();

		deleteItem();

	}
}
