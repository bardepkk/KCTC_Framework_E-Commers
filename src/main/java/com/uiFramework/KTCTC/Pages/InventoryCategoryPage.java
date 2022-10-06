package com.uiFramework.KTCTC.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uiFramework.KTCTC.helper.wait.WaitHelper;

public class InventoryCategoryPage {
   // category navigation
	
	By categoryOptionUnderInventory = By.xpath("//*[starts-with(@href,'category')]");
	
	// Add
	By addNewButtonOnCategoryPage = By.xpath("//* [@href='#addnew']");

	By nameTextBoxOnAddCategoryModal = By.id("name");
	By saveButtonOnAddCategoryModal = By.name("add");
	By categoryAddedSuccessfullyMessage = By
			.xpath("//*[contains(.,'Category added successfully') and contains(@class,'success alert')]");

	By countOnCategoryPage = By.id("example1_info");
	By searchBoxOnCategoryPage = By.xpath("//*[@id='example1_filter']//input");
	
	// Edit
	
	By editButtonOnCategoryPage = By.xpath("//*[@id='example1']//*[contains(text(),'Edit')]");
	By editNameOnEditCategoryModal = By.id("edit_name");
	By updateButtonOnEditCategoryModal = By.name("edit");
	By categoryUpdatedSuccessfullyMessage = By.xpath("//*[contains(.,'Category updated successfully') and contains(@class,'success alert')]"); 
	
	// Delete
	By deleteButtonOnCategoryPage = By.xpath("//*[@id='example1']//*[contains(text(),'Delete')]");
	By deleteButtonOnDeleteCategoryModal = By.name("delete");
	By categoryDeletedSuccessfullyMessage = By.xpath("//*[contains(.,'Category deleted successfully') and contains(@class,'success alert')]");
	// Filtered count
	By filteredCountOnCategoryPage = By.xpath("//*[@id='example1_info' and contains (text(), 'filtered ')]");
	
	
	public void navigateToCategoryPageFromInventory(WebDriver driver)
	{
		driver.findElement(categoryOptionUnderInventory).click();
	}
	
	public void addNewCategoryOnCategoryPage(WebDriver driver, String name)
	{
		driver.findElement(addNewButtonOnCategoryPage).click();
		driver.findElement(nameTextBoxOnAddCategoryModal).sendKeys(name);
		driver.findElement(saveButtonOnAddCategoryModal).click();
		
	}
	
	public void editExistingCategoryOnCategoryPage(WebDriver driver, String existingCategory, String newCategoryName)
	{
		searchStringInSearchBox(driver, existingCategory);
		driver.findElement(editButtonOnCategoryPage).click();
		driver.findElement(editNameOnEditCategoryModal).clear();
		driver.findElement(editNameOnEditCategoryModal).sendKeys(newCategoryName);
		driver.findElement(updateButtonOnEditCategoryModal).click();
		
	}
	
	public void deleteExistingCategoryOnCategoryPage(WebDriver driver, String name)
	{
		searchStringInSearchBox(driver, name);
		driver.findElement(deleteButtonOnCategoryPage).click();
		driver.findElement(deleteButtonOnDeleteCategoryModal).click();
	}
	
	public boolean isCategoryAddedSuccessfullyMessageDisplayed(WebDriver driver)
	{
		boolean flag = false;

		try {
			flag = driver.findElement(categoryAddedSuccessfullyMessage).isDisplayed();
		}

		catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	public boolean isCategoryUpdatedSuccessfullyMessageDisplayed(WebDriver driver)
	{
		boolean flag = false;

		try {
			flag = driver.findElement(categoryUpdatedSuccessfullyMessage).isDisplayed();
		}

		catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	public boolean isCategoryDeletedSuccessfullyMessageDisplayed(WebDriver driver)
	{
		boolean flag = false;

		try {
			flag = driver.findElement(categoryDeletedSuccessfullyMessage).isDisplayed();
		}

		catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	public void clearSearchBox(WebDriver driver)
	{
		driver.findElement(searchBoxOnCategoryPage).clear();
	}
	
	public void searchStringInSearchBox(WebDriver driver, String data)
	{
		clearSearchBox(driver);
		driver.findElement(searchBoxOnCategoryPage).sendKeys(data);
	}

	public boolean isMentionedCategoryPresentInTable(WebDriver driver, String name) {
		
		searchStringInSearchBox(driver, name);
		boolean flag = false;

		try {
			flag = driver.findElement(By.xpath("//*[contains(text(),'" + name + "')]")).isDisplayed();
		}

		catch (Exception e) {
			flag = false;
		}
		clearSearchBox(driver);
		return flag;

	}
	
	public int getCountOfRecordsOnCategoryPage(WebDriver driver)
	{
		try {
			WaitHelper wt = new WaitHelper(driver);
			wt.waitForElementNotPresent(driver.findElement(filteredCountOnCategoryPage), 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String count = driver.findElement(countOnCategoryPage).getText();
		String[] arr = count.trim().split(" ");
		String cnt = arr[5];
		
		return Integer.parseInt(cnt);
	}

}
