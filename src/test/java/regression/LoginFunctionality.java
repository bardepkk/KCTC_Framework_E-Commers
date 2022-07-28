package regression;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.uiFramework.KTCTC.testbase.TestBase;

public class LoginFunctionality extends TestBase{
	
	@Test (priority = 1)
	public void verifyUsercanLogin()
	{
		Assert.assertTrue(true);
		System.out.println("Pass wali method");
	}
	
	@Test (priority = 2)
	public void verifyUsercanAddBrand()
	{
		Assert.assertTrue(false, "User can not add new brand in system");
	}
	
	@Test (priority = 3)
	public void verifyUsercanInventory()
	{
		System.out.println("Skiped wali method");
		Assert.assertTrue(false);
	}

}
