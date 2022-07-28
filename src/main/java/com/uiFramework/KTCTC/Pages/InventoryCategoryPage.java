package com.uiFramework.KTCTC.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryCategoryPage {
	// Locators on category Page
	By newButtonOnCategoryPage = By.xpath("//*[contains(@href,'addnew')]");
	By searchBoxOnCategoryPage = By.xpath("//*[contains(@type,'search')]");
	By categoryCountMessageOnCategoryPage = By.id("example1_info");
	By editButtonOnCategoryPage = By.className("btn btn-success btn-sm edit btn-flat");
	By deleteButtonOnCategoryPage = By.className("btn btn-danger btn-sm delete btn-flat");
	// Locators of add new category modals
	By nameTextBoxOnAddNewCategoryModal = By.id("name");
	By saveButtonOnAddNewCategoryModal = By.name("add");
	
	By nameTextBoxOnEditCategoryModal = By.id("edit_name");
	By updateButtonOnEditCategoryModal = By.name("edit");
	
	By deleteButtonOnDelteModal = By.name("delete");
	
	
	public void addNewCategory(WebDriver driver, String name)
	{
		driver.findElement(newButtonOnCategoryPage).click();
		driver.findElement(nameTextBoxOnAddNewCategoryModal).sendKeys(name);
		driver.findElement(saveButtonOnAddNewCategoryModal).click();
		
	}
	
	public void searchStringInSearchBox(WebDriver driver, String str)
	{
		clearSearchBox(driver);
		driver.findElement(searchBoxOnCategoryPage).sendKeys(str);
	}
	public void clearSearchBox(WebDriver driver)
	{
		driver.findElement(searchBoxOnCategoryPage).clear();
	}
	
	public void editExistingCategory(WebDriver driver, String oldCategory, String newCatString)
	{
		searchStringInSearchBox(driver, oldCategory);
		driver.findElement(editButtonOnCategoryPage).click();
		driver.findElement(nameTextBoxOnEditCategoryModal).clear();
		driver.findElement(nameTextBoxOnEditCategoryModal).sendKeys(newCatString);
		driver.findElement(updateButtonOnEditCategoryModal).click();
		
	}
	
	public void deleteExistingCategory(WebDriver driver, String category)
	{
		searchStringInSearchBox(driver, category);
		driver.findElement(deleteButtonOnCategoryPage).click();
		driver.findElement(deleteButtonOnDelteModal).click();
		
		
	}
	
public boolean isCategoryDIsplayedOnCategoryPage(WebDriver driver, String category)
{
	searchStringInSearchBox(driver, category);
	boolean flag = false;
	
	try {
		flag = driver.findElement(By.xpath("//td[contains(text(),'Bakery & Dairy')]")).isDisplayed();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		flag = false;
	}
	return flag;
}

public int getCountOfRecordsOnCategoryPage(WebDriver driver)
{
	String str = driver.findElement(categoryCountMessageOnCategoryPage).getText();
	String[] ar = str.split(" ");
	String str1 = ar[5];
	int a = Integer.parseInt(str1);
	
	return a;
	
}

}
