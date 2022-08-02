package com.uiFramework.KTCTC.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.uiFramework.KTCTC.helper.javaScript.JavaScriptHelper;
import com.uiFramework.KTCTC.helper.wait.WaitHelper;


/**
 * @author 91992
 *
 */
public class InventoryCategoryPage {
	WebDriver driver=null;
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
	
	public InventoryCategoryPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	/**
	 * This method expands Category option
	 */
	public void expandCategoruOption()
	{
		//WaitHelper wt = new WaitHelper(driver);
	//	wt.WaitForElementClickable(driver.findElement(categoryOptionFromInventory), 4);
		driver.findElement(categoryOptionFromInventory).click();
	}
	
	/**
	 * This method checks if category added success message displayed
	 * @return
	 */
	public boolean isCategoryAddedSuccessMessageDisplayed()
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
	/**
	 * This method checks if category updated success message displayed
	 * @return
	 */
	public boolean isCategoryUpdatedSuccessMessageDisplayed()
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
	/**
	 * This method checks if category deleted success message displayed
	 * @return
	 */
	public boolean isCategoryDeletedSuccessMessageDisplayed()
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
	/**
	 * This method adds new category in application
	 * @param name
	 */
	public void addNewCategory(String name)
	{
		driver.findElement(newButtonOnCategoryPage).click();
		driver.findElement(nameTextBoxOnAddNewCategoryModal).sendKeys(name);
		driver.findElement(saveButtonOnAddNewCategoryModal).click();
		
	}
	
	/**
	 * This method search provided string in search box
	 * @param str
	 */
	public void searchStringInSearchBox( String str)
	{
		clearSearchBox(driver);
		driver.findElement(searchBoxOnCategoryPage).sendKeys(str);
	}
	/**
	 * This method clears search box 
	 * @param driver
	 */
	public void clearSearchBox(WebDriver driver)
	{
		driver.findElement(searchBoxOnCategoryPage).clear();
		driver.findElement(searchBoxOnCategoryPage).sendKeys(Keys.BACK_SPACE);
	}
	
	/**
	 * This method edits/update existing category 
	 * @param oldCategory
	 * @param newCatString
	 */
	public void editExistingCategory(String oldCategory, String newCatString)
	{
		searchStringInSearchBox(oldCategory);
		driver.findElement(editButtonOnCategoryPage).click();
		driver.findElement(nameTextBoxOnEditCategoryModal).clear();
		driver.findElement(nameTextBoxOnEditCategoryModal).sendKeys(newCatString);
		driver.findElement(updateButtonOnEditCategoryModal).click();
		
	}
	
	/**
	 * This method deletes existing category
	 * @param category
	 */
	public void deleteExistingCategory( String category)
	{
		searchStringInSearchBox(category);
		driver.findElement(deleteButtonOnCategoryPage).click();
		driver.findElement(deleteButtonOnDelteModal).click();
		
		
	}
	
/**
 * This method checks if provided category is displayed on UI
 * @param category
 * @return
 */
public boolean isCategoryDIsplayedOnCategoryPage(String category)
{
	searchStringInSearchBox(category);
	boolean flag = false;
	
	try {
		flag = driver.findElement(By.xpath("//td[contains(text(),'"+category+"')]")).isDisplayed();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		flag = false;
	}
	return flag;
}

/**
 * This method returns count of records displayed on category page
 * @return
 */
public int getCountOfRecordsOnCategoryPage()
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
