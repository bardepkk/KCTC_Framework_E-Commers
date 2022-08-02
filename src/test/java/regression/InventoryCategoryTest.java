package regression;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.uiFramework.KTCTC.Pages.InventoryCategoryPage;
import com.uiFramework.KTCTC.helper.browserConfiguration.ChromeBrowser;
import com.uiFramework.KTCTC.testbase.TestBase;

public class InventoryCategoryTest extends TestBase{
	InventoryCategoryPage inventoryCategoryPage = null;
	String categoryName = "";
	String newCategoryName = "";
	int initialCount = 0;
	int afterAddCount = 0;
	@BeforeClass
	public void beforeClassOfA() {
		driver = ChromeBrowser.getBrowserInstance();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		//driver.manage().window().maximize();
		test = extent.createTest(getClass().getSimpleName());
		driver.get(proObj.getPropertyValueFromFile("baseURL"));
		cmObj.loginToApplication(driver, proObj.getPropertyValueFromFile("adminNumber"),proObj.getPropertyValueFromFile("adminPass"));
	}
	
	@Test (priority = 1)
	public void verifyUserCanAddNewCategory()
	{
		inventoryCategoryPage = new InventoryCategoryPage(driver);
		SoftAssert sAssert = new SoftAssert();
		cmObj.expandInventoryOption(driver);	    
		inventoryCategoryPage.expandCategoruOption();
		initialCount = inventoryCategoryPage.getCountOfRecordsOnCategoryPage();
		 categoryName = cmObj.getcharacterString(6);
		inventoryCategoryPage.addNewCategory(categoryName);
		boolean flag = inventoryCategoryPage.isCategoryDIsplayedOnCategoryPage(categoryName);
		sAssert.assertTrue(flag, "Newly Added category is not displayed on UI");
		sAssert.assertAll();
	}
	@Test (priority = 2)
	public void verifyCategoryAddedSuccessMessageIsDisplayed()
	{
		SoftAssert sAssert = new SoftAssert();
		boolean flag = inventoryCategoryPage.isCategoryAddedSuccessMessageDisplayed();
		sAssert.assertTrue(flag, "Category added message is not displayed");
		sAssert.assertAll();
	}
	@Test (priority = 3)
	public void verifyCategoryPageCountGetsIncreasedOnAddingNewCategory()
	{
		SoftAssert sAssert = new SoftAssert();
		inventoryCategoryPage.clearSearchBox(driver);
		 afterAddCount = inventoryCategoryPage.getCountOfRecordsOnCategoryPage();
		sAssert.assertEquals((initialCount+1), afterAddCount);
		
		sAssert.assertAll();
	}
	@Test (priority = 4)
	public void verifyUserCanEditExistingCategory()
	{
		SoftAssert sAssert = new SoftAssert();
		newCategoryName = cmObj.getcharacterString(6);
		inventoryCategoryPage.editExistingCategory(categoryName, newCategoryName);
		boolean flag = inventoryCategoryPage.isCategoryDIsplayedOnCategoryPage(newCategoryName);
		sAssert.assertTrue(flag, "Edited category is not displayed on UI");
		boolean flag1 = inventoryCategoryPage.isCategoryDIsplayedOnCategoryPage(categoryName);
		sAssert.assertFalse(flag1, "Old category is still displayed on UI");
		sAssert.assertAll();
		
	}
	@Test (priority = 5)
	public void verifyCategoryUpdatedSuccessMessageIsDisplayed()
	{
		SoftAssert sAssert = new SoftAssert();
		boolean flag = inventoryCategoryPage.isCategoryUpdatedSuccessMessageDisplayed();
		sAssert.assertTrue(flag, "Category updated message is not displayed");
		sAssert.assertAll();
	}
	@Test (priority = 6)
	public void verifyCategoryPageCountWillBeSameAfterEdit()
	{
		SoftAssert sAssert = new SoftAssert();
		inventoryCategoryPage.clearSearchBox(driver);
		 int afterEdit = inventoryCategoryPage.getCountOfRecordsOnCategoryPage();
		sAssert.assertEquals(afterEdit, afterAddCount);
		sAssert.assertAll();
	}
	
	@Test (priority = 7)
	public void verifyUserCanDeleteExistingCategory()
	{
		SoftAssert sAssert = new SoftAssert();
		inventoryCategoryPage.deleteExistingCategory(newCategoryName);
		boolean flag1 = inventoryCategoryPage.isCategoryDIsplayedOnCategoryPage(newCategoryName);
		sAssert.assertFalse(flag1, "Deleted category is still displayed on UI");
		sAssert.assertAll();
		
	}
	@Test (priority = 8)
	public void verifyCategoryDeletedSuccessMessageIsDisplayed()
	{
		SoftAssert sAssert = new SoftAssert();		
		sAssert.assertTrue(inventoryCategoryPage.isCategoryDeletedSuccessMessageDisplayed(), "Category deleted message is not displayed");
		sAssert.assertAll();
	}
	@Test (priority = 9)
	public void verifyCategoryPageCountWillGetDecreasedAfterDelete()
	{
		SoftAssert sAssert = new SoftAssert();
		inventoryCategoryPage.clearSearchBox(driver);
		 int afterDelete = inventoryCategoryPage.getCountOfRecordsOnCategoryPage();
		sAssert.assertEquals((afterDelete+1), afterAddCount);
		sAssert.assertAll();
	}
	
	

}
