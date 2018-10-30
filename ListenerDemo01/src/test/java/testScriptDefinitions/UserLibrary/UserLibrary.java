package testScriptDefinitions.UserLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import FrameworkLibrary.WebLibrary;
import ObjectRepository.PgLogin;
/**
 * @AUTHOR Bhaskar
 */
public class UserLibrary extends WebLibrary
{
	public static boolean login(String UserName,String Password,WebDriver driver)
	{
		boolean stepstatus = false; 
		//try
		//{
			String title = driver.getTitle();
		/*	if(title.contains("#1 Free CRM software in the cloud for sales and service"))
			{System.out.println("---------16-------UL");
			//	return true;
			}
	//	}
	/*	catch(Exception e)
		{
			stepstatus = false;
		}*/
		wait(10);
		System.out.println("---------25-------UL");
		highlight(PgLogin.Username,   driver);  
		setText(PgLogin.Username,  UserName,  driver);  // line 44
		
		highlight(PgLogin.pasward,   driver);  // line 44
		setText(PgLogin.pasward,  Password,  driver);
		
		highlight(PgLogin.btnLogIn, driver);
		clickElement(PgLogin.btnLogIn, driver);
		
	/*	RobotKeysSetText(UserName);
		RobotKeysTab();
		RobotKeysSetText(Password);
		RobotKeysEnter();*/
		wait(3);
		try
		{
			//String title = driver.getTitle();
			if(title.contains("#1 Free CRM software in the cloud for sales and service"))
			{
				stepstatus = true;
			}
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
	
	
}
