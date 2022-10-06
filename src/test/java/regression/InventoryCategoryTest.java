package regression;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.uiFramework.KTCTC.Pages.InventoryCategoryPage;
import com.uiFramework.KTCTC.testbase.TestBase;

public class InventoryCategoryTest extends TestBase{

	InventoryCategoryPage inventoryCategoryPage = new InventoryCategoryPage();
	String categoryName = "";
	String newCategoryName = "";
	@Test (priority = 1)
	public void verifyUserCanAddNewCategoryOnCategoryPage()
	{
		SoftAssert sAssert = new SoftAssert();
		cmObj.expandInventoryOption(driver);
		inventoryCategoryPage.navigateToCategoryPageFromInventory(driver);
		categoryName = cmObj.getcharacterString(7);
		int initialCount = inventoryCategoryPage.getCountOfRecordsOnCategoryPage(driver);				
		inventoryCategoryPage.addNewCategoryOnCategoryPage(driver, categoryName);
		boolean flag = inventoryCategoryPage.isCategoryAddedSuccessfullyMessageDisplayed(driver);		
		int finalCount = inventoryCategoryPage.getCountOfRecordsOnCategoryPage(driver);
		sAssert.assertTrue(flag, "Category added successFully banner id not displayed");		
		sAssert.assertEquals(initialCount, finalCount-1, "Count is not updated after addition new category");	
		sAssert.assertAll();
		
	}
	
	@Test (priority = 2)
	public void verifyAddedCategoryIsDisplayedOnCategoryPage()
	{
		SoftAssert sAssert = new SoftAssert();
		boolean fl = inventoryCategoryPage.isMentionedCategoryPresentInTable(driver, categoryName);
		sAssert.assertTrue(fl, "Added Category is not displayed on Category Page");		
		sAssert.assertAll();
	}
	
	@Test (priority = 3)
	public void verifyUserCanEditExistingCategory()
	{
		SoftAssert sAssert = new SoftAssert();
		newCategoryName = cmObj.getcharacterString(7);
		int initialCount = inventoryCategoryPage.getCountOfRecordsOnCategoryPage(driver);
		inventoryCategoryPage.editExistingCategoryOnCategoryPage(driver, categoryName, newCategoryName);
		int finalCount = inventoryCategoryPage.getCountOfRecordsOnCategoryPage(driver);
		boolean flag = inventoryCategoryPage.isCategoryUpdatedSuccessfullyMessageDisplayed(driver);		
		sAssert.assertTrue(flag, "Category updated successFully banner id not displayed");
		sAssert.assertEquals(initialCount, finalCount, "Count is falsely updated after updating category");		
		sAssert.assertAll();
	}
	
	@Test (priority = 4)
	public void verifyUpdatedCategoryIsDisplayedOnCategoryPage()
	{
		SoftAssert sAssert = new SoftAssert();
		boolean fl = inventoryCategoryPage.isMentionedCategoryPresentInTable(driver, newCategoryName);			
		sAssert.assertTrue(fl, "Added Category is not displayed on Category Page");		
		boolean fl2 = inventoryCategoryPage.isMentionedCategoryPresentInTable(driver, categoryName);			
		sAssert.assertFalse(fl2, "Existing category is still displayed");
		sAssert.assertAll();
	}
	
	
}
