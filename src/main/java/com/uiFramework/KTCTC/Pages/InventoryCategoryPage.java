package com.uiFramework.KTCTC.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uiFramework.KTCTC.helper.wait.WaitHelper;

public class InventoryCategoryPage {
	
	WebDriver driver;
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
	
	//@FindBy (xpath="//*[@id='example1_info' and contains (text(), 'filtered ')]")
	//WebElement filter;
	
	public InventoryCategoryPage(WebDriver driver)
	{
		this.driver = driver;
		//PageFactory.initElements(driver, this);
	}
	
	
	
	public void navigateToCategoryPageFromInventory()
	{
		driver.findElement(categoryOptionUnderInventory).click();
	}
	
	public void addNewCategoryOnCategoryPage(String name)
	{
		driver.findElement(addNewButtonOnCategoryPage).click();
		driver.findElement(nameTextBoxOnAddCategoryModal).sendKeys(name);
		driver.findElement(saveButtonOnAddCategoryModal).click();
		
	}
	
	public void editExistingCategoryOnCategoryPage(String existingCategory, String newCategoryName)
	{
		searchStringInSearchBox( existingCategory);
		driver.findElement(editButtonOnCategoryPage).click();
		driver.findElement(editNameOnEditCategoryModal).clear();
		driver.findElement(editNameOnEditCategoryModal).sendKeys(newCategoryName);
		driver.findElement(updateButtonOnEditCategoryModal).click();
		clearSearchBox();
		
	}
	
	public void deleteExistingCategoryOnCategoryPage(String name)
	{
		searchStringInSearchBox( name);
		driver.findElement(deleteButtonOnCategoryPage).click();
		driver.findElement(deleteButtonOnDeleteCategoryModal).click();
	}
	
	public boolean isCategoryAddedSuccessfullyMessageDisplayed()
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
	public boolean isCategoryUpdatedSuccessfullyMessageDisplayed()
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
	public boolean isCategoryDeletedSuccessfullyMessageDisplayed()
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
	public void clearSearchBox()
	{
		
		driver.findElement(searchBoxOnCategoryPage).sendKeys(Keys.CONTROL+"A"+Keys.BACK_SPACE);
	}
	
	public void searchStringInSearchBox(String data)
	{
		clearSearchBox();
		driver.findElement(searchBoxOnCategoryPage).sendKeys(data);
	}

	public boolean isMentionedCategoryPresentInTable(String name) {
		
		searchStringInSearchBox(name);
		boolean flag = false;

		try {
			flag = driver.findElement(By.xpath("//*[contains(text(),'" + name + "')]")).isDisplayed();
		}

		catch (Exception e) {
			flag = false;
		}
		clearSearchBox();
		return flag;

	}
	
	public int getCountOfRecordsOnCategoryPage()
	{
		
		String count = driver.findElement(countOnCategoryPage).getText();
		String[] arr = count.trim().split(" ");
		String cnt = arr[5];
		
		return Integer.parseInt(cnt);
	}

}
