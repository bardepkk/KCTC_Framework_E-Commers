package regression;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.uiFramework.KTCTC.Pages.InventoryCategoryPage;
import com.uiFramework.KTCTC.helper.browserConfiguration.ChromeBrowser;
import com.uiFramework.KTCTC.testbase.TestBase;

public class InventoryProduct extends TestBase{
	String categoryName = cmObj.getcharacterString(6);
	@BeforeClass
	public void beforeClassOfA() {
		driver = ChromeBrowser.getBrowserInstance();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		//driver.manage().window().maximize();
		test = extent.createTest(getClass().getSimpleName());
		driver.get(proObj.getPropertyValueFromFile("baseURL"));
		cmObj.loginToApplication(driver, proObj.getPropertyValueFromFile("adminNumber"),proObj.getPropertyValueFromFile("adminPass"));
		InventoryCategoryPage inventoryCategoryPage = new InventoryCategoryPage(driver);
		cmObj.expandInventoryOption(driver);
		inventoryCategoryPage.expandCategoruOption();
		inventoryCategoryPage.addNewCategory(categoryName);
		
	}
	@Test
	public void verifyUserCanAddProduct()
	{
		
	}

}
