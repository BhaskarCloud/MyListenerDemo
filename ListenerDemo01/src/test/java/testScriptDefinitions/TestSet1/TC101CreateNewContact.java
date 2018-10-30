package testScriptDefinitions.TestSet1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ObjectRepository.pgHome;
import ObjectRepository.pg_Contact;
//import testScriptDefinitions.UserLibrary;

@SuppressWarnings("unused")
public class TC101CreateNewContact extends testScriptDefinitions.UserLibrary.UserLibrary{

	boolean stepstatus;
	String plog;
	String flog;
	//String BrowserName = "htmlunit";
	@Parameters("browser")
	@Test
	public void TC101_CreateNewContact(String BrowserName)
	{
		WebDriver driver = launchBrowser(BrowserName); 
		driver = SetImplicitWait( 5, driver);
		 
		stepstatus = OpenUrl (driver); 
		plog = "Able to launch Application";  
		flog = "Unable to launch Application";
		logEvent(stepstatus, plog, flog, driver, true); // false means don't take screenshot.
		wait(5);
		
		
		stepstatus =  login(getdata("UserName"), getdata("Password"), driver);
		plog = "login is successful";  
		flog = "login is not successful";
		logEvent(stepstatus, plog, flog, driver, true);
		wait(5);
		//driver.switchTo().frame("mainpanel");
		switchToframe(By.name("mainpanel"), driver);
		highlight(pgHome.LnkCONTACTS, driver);
		stepstatus = clickElement((By.xpath("//a[@title='Contacts']")), driver) ; 
		plog = "Able to clik CONTACTS";  
		flog = "Unable to clik CONTACTS";
		logEvent(stepstatus, plog, flog, driver, false); // True  means  take screenshot
		
		
		highlight(By.xpath("//td[contains(text(), 'User: bhaskar khimania')]"), driver);
		stepstatus = verifyElementPresent((By.xpath("//td[contains(text(), 'User: bhaskar khimania')]")), driver);
		plog = "Able to verify CONTACTS lable";  
		flog = "Unable to verify CONTACTS lable";
		logEvent(stepstatus, plog, flog, driver, true); // True  means  take screenshot
		
		stepstatus = quitDriver(driver);
		plog = "Able to quit browser";  
		flog = "Unable to quit browser";
		logEvent(stepstatus, plog, flog, driver, false); 
				
	}

}
