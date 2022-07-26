package com.uiFramework.KTCTC.Pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.uiFramework.KTCTC.helper.wait.WaitHelper;

public class WebTablesPage {
	private WebDriver driver;
	
	By addButtonOnWebTablePage = By.id("addNewRecordButton");
	By webTablePageLink = By.xpath("//*[contains(text(),'Web Tables')]");
	// Registation forms locator
	By firstNameOnRegistrationForm = By.id("firstName");
	By lastNameOnRegistrationForm = By.id("lastName");
	By emailOnRegistrationForm = By.id("userEmail");
	By ageOnRegistrationForm = By.id("age");
	By salaryOnRegistrationForm = By.id("salary");
	By departmentOnRegistrationForm = By.id("department");
	By submitButtonOnRegistrationForm = By.id("submit");
	//search box
	By searchBoxOnWebTablePage = By.id("searchBox");
	
	// edit button
	By editButtonOnWebTablePage = By.xpath("//*[contains(@id,'edit-record')]");
	// delete button
	By deleteButtonOnWebTablePage = By.xpath("//*[contains(@id,'delete-record')]");
	
	public WebTablesPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void navigateToWebTablePage()
	{
		driver.findElement(webTablePageLink).click();
		
	}
	
	public void addNewUserOnWebTablePage(String fname, String lName,String email,String age, String salary, String department)
	{
		driver.findElement(addButtonOnWebTablePage).click();
		WaitHelper wt = new WaitHelper(driver);
		wt.WaitForElementVisibleWithPollingTime(driver.findElement(firstNameOnRegistrationForm), 5, 200);
		driver.findElement(firstNameOnRegistrationForm).sendKeys(fname);
		driver.findElement(lastNameOnRegistrationForm).sendKeys(lName);
		driver.findElement(emailOnRegistrationForm).sendKeys(email);
		driver.findElement(ageOnRegistrationForm).sendKeys(age);
		driver.findElement(salaryOnRegistrationForm).sendKeys(salary);
		driver.findElement(departmentOnRegistrationForm).sendKeys(department);
		driver.findElement(submitButtonOnRegistrationForm).click();
		
	}
	
	public void searchStringInSearchBox( String email)
	{
		driver.findElement(searchBoxOnWebTablePage).clear();
		driver.findElement(searchBoxOnWebTablePage).sendKeys(email);
		
	}
	
	public boolean isUserWithProvidedEmailIdDisplayed(String email)
	{
		searchStringInSearchBox(email);
		boolean flag = false;
		
		try {
			flag = driver.findElement(By.xpath("//*[contains(text(),'"+email+"')]")).isDisplayed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		return flag;
		
	}
	
	public HashMap<String,String> getAllDetailsOfProvidedUser(String email)
	{
		searchStringInSearchBox(email);
		HashMap<String, String> data = new HashMap<>();		
		data.put("FirstName", driver.findElement(By.xpath("//*[contains(text(),'"+email+"')]/preceding-sibling::div[3]")).getText());
		data.put("LastName", driver.findElement(By.xpath("//*[contains(text(),'"+email+"')]/preceding-sibling::div[2]")).getText());
		data.put("Age", driver.findElement(By.xpath("//*[contains(text(),'"+email+"')]/preceding-sibling::div[1]")).getText());
		data.put("Email", driver.findElement(By.xpath("//*[contains(text(),'"+email+"')]")).getText());
		data.put("Salary", driver.findElement(By.xpath("//*[contains(text(),'"+email+"')]/following-sibling::div[1]")).getText());
		data.put("Department", driver.findElement(By.xpath("//*[contains(text(),'"+email+"')]/following-sibling::div[2]")).getText());
		return data;
	}
	
	public void editExistringUserWithProvidedDetails(String existingEmail,String fname, String lName,String email,String age, String salary, String department)
	{
		searchStringInSearchBox(existingEmail);
		driver.findElement(editButtonOnWebTablePage).click();
		WaitHelper wt = new WaitHelper(driver);
		wt.WaitForElementVisibleWithPollingTime(driver.findElement(firstNameOnRegistrationForm), 5, 200);
		driver.findElement(firstNameOnRegistrationForm).clear();				
		driver.findElement(firstNameOnRegistrationForm).sendKeys(fname);
		driver.findElement(lastNameOnRegistrationForm).clear();		
		driver.findElement(lastNameOnRegistrationForm).sendKeys(lName);
		driver.findElement(emailOnRegistrationForm).clear();		
		driver.findElement(emailOnRegistrationForm).sendKeys(email);
		driver.findElement(ageOnRegistrationForm).clear();		
		driver.findElement(ageOnRegistrationForm).sendKeys(age);
		driver.findElement(salaryOnRegistrationForm).clear();		
		driver.findElement(salaryOnRegistrationForm).sendKeys(salary);
		driver.findElement(departmentOnRegistrationForm).clear();
		driver.findElement(departmentOnRegistrationForm).sendKeys(department);
		driver.findElement(submitButtonOnRegistrationForm).click();
		
	}
	
	public void deleteExistingUserWithProvidedDetails( String email)
	{
		searchStringInSearchBox(email);		
		driver.findElement(deleteButtonOnWebTablePage).click();
		
	}
	
	
	

}
