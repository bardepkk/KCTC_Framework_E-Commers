package regression;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.uiFramework.KTCTC.Pages.InventoryCategoryPage;
import com.uiFramework.KTCTC.helper.browserConfiguration.ChromeBrowser;
import com.uiFramework.KTCTC.testbase.TestBase;

public class InventoryProductsTest extends TestBase{
	
	
	@BeforeClass
	public void beforeClassOfA() {
		driver = ChromeBrowser.getBrowserInstance();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().deleteAllCookies();
		//driver.manage().window().maximize();
		test = extent.createTest(getClass().getSimpleName());
		driver.get(proObj.getPropertyValueFromFile("baseURL"));
		cmObj.acceptPrivateConnectionWarningIfPresent(driver);		
		cmObj.loginToApplication(driver, proObj.getPropertyValueFromFile("adminNumber"),proObj.getPropertyValueFromFile("adminPass"));
	}

	InventoryCategoryPage inventoryCategoryPage;
	String categoryName = "";
	@Test
	public void verifyUserCanAddNewProduct()
	{
		cmObj.expandInventoryOption(driver);
		inventoryCategoryPage = new InventoryCategoryPage(driver);
		categoryName = cmObj.getcharacterString(7);
		inventoryCategoryPage.navigateToCategoryPageFromInventory();		
		inventoryCategoryPage.addNewCategoryOnCategoryPage(categoryName);
		
		
		
		
		
	}
}
