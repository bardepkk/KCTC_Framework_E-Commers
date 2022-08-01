package com.uiFramework.KTCTC.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.uiFramework.KTCTC.helper.javaScript.JavaScriptHelper;
import com.uiFramework.KTCTC.helper.wait.WaitHelper;

public class InventoryCategoryPage {
	// Locators on category Page
	By newButtonOnCategoryPage = By.xpath("//*[contains(@href,'addnew')]");
	By searchBoxOnCategoryPage = By.xpath("//*[contains(@type,'search')]");
	By categoryCountMessageOnCategoryPage = By.id("example1_info");
	By editButtonOnCategoryPage = By.xpath("//button[contains(text(),' Edit')]");
	By deleteButtonOnCategoryPage = By.xpath("//td//button[contains(text(),' Delete')]");
	// Locators of add new category modals
	By nameTextBoxOnAddNewCategoryModal = By.id("name");
	By saveButtonOnAddNewCategoryModal = By.name("add");
	
	By nameTextBoxOnEditCategoryModal = By.id("edit_name");
	By updateButtonOnEditCategoryModal = By.name("edit");
	
	By deleteButtonOnDelteModal = By.name("delete");
	By categoryOptionFromInventory = By.xpath("//*[starts-with(@href,'category')]");
	// success messages
	By categoryAddedSuccessfullyMessage = By.xpath("//section//*[contains(.,'Category added successfully')]");
	By categoryupdatedSuccessfullyMessage = By.xpath("//section//*[contains(.,'Category updated successfully')]");
	By categorydeletedSuccessfullyMessage = By.xpath("//section//*[contains(.,'Category deleted successfully')]");
	
	public void expandCategoruOption(WebDriver driver)
	{
		//WaitHelper wt = new WaitHelper(driver);
	//	wt.WaitForElementClickable(driver.findElement(categoryOptionFromInventory), 4);
		driver.findElement(categoryOptionFromInventory).click();
	}
	
	public boolean isCategoryAddedSuccessMessageDisplayed(WebDriver driver)
	{
		boolean flag = false;
		
		try {
			flag = driver.findElement(categoryAddedSuccessfullyMessage).isDisplayed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag = false;
		}
		return flag;
		
	}
	public boolean isCategoryUpdatedSuccessMessageDisplayed(WebDriver driver)
	{
		boolean flag = false;
		
		try {
			flag = driver.findElement(categoryupdatedSuccessfullyMessage).isDisplayed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag = false;
		}
		return flag;
		
	}
	public boolean isCategoryDeletedSuccessMessageDisplayed(WebDriver driver)
	{
		boolean flag = false;
		
		try {
			flag = driver.findElement(categorydeletedSuccessfullyMessage).isDisplayed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag = false;
		}
		return flag;
		
	}
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
		driver.findElement(searchBoxOnCategoryPage).sendKeys(Keys.BACK_SPACE);
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
		flag = driver.findElement(By.xpath("//td[contains(text(),'"+category+"')]")).isDisplayed();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		flag = false;
	}
	return flag;
}

public int getCountOfRecordsOnCategoryPage(WebDriver driver)
{
	JavaScriptHelper js = new JavaScriptHelper(driver);
	js.scrollToElement(driver.findElement(categoryCountMessageOnCategoryPage));
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String str = driver.findElement(categoryCountMessageOnCategoryPage).getText();
	String[] ar = str.split(" ");
	String str1 = ar[5];
	int a = Integer.parseInt(str1);
	js.scrollUpVertically();
	
	return a;
	
}

}
