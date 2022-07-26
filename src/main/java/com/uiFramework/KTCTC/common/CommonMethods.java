package com.uiFramework.KTCTC.common;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.uiFramework.KTCTC.helper.javaScript.JavaScriptHelper;

public class CommonMethods {
	
	
	/**
	 * Method returns alpha numeric string of mentioned length
	 * @param n
	 * @return
	 */
	public String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    }
	public String getNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "0123456789"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    }
	public String getcharacterString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    }
	
	/**
	 * @param driver
	 * @param number
	 * @param pass
	 */
	public void loginToApplication(WebDriver driver, String number, String pass)
	{
		driver.findElement(By.name("email")).sendKeys(number);
		driver.findElement(By.id("myInput")).sendKeys(pass);
		driver.findElement(By.name("login")).click();
	}
	/**
	 * Method navigates to required page
	 * @param driver
	 * @param pageName
	 */
	public void navigateToReQuiredPage(WebDriver driver, String pageName )
	{
		
		JavaScriptHelper js = new JavaScriptHelper(driver);
		js.clickElement(driver.findElement(By.xpath("//*[@class='card-body']//*[contains(text(),'"+pageName+"')]")));
		
	}
	
	
	

}
