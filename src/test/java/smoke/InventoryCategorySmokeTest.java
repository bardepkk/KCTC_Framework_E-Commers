package smoke;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.uiFramework.KTCTC.Pages.InventoryCategoryPage;
import com.uiFramework.KTCTC.helper.browserConfiguration.ChromeBrowser;
import com.uiFramework.KTCTC.testbase.TestBase;

public class InventoryCategorySmokeTest extends TestBase{
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
}
