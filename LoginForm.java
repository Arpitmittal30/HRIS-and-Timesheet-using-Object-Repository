package com.qait.automation.Tatoc5;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;



public class LoginForm {
	private Repofile parser;
	WebDriver driver;
	String YesDate;

	
	public LoginForm(WebDriver driver) {
		this.driver = driver;
		try {
			parser = new Repofile("Datafile.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void urlToBeReached() {
		 driver.get("https://hris.qainfotech.com");
		  driver.manage().window().maximize();
		  Reporter.log("Browser Launch Successfully");
	}
	
	public void clickToLoginPanel() {
		driver.findElement(parser.getbjectLocator("class1")).click();
		Reporter.log("Login panel clicked successfully");
	}

	public void s_1_LoginWithIncorrectPassword() {
		clickToLoginPanel();
		loginCredentials("arpitmittal", "Incorrect");
		clickToLoginPanel();
		boolean check = checkSignIn();
		assertFalse(check);
		Reporter.log("Invalid password is working correctly");
	}

	public void s_2_LoginWithBlankPassword() throws InterruptedException {
		
		ClearScreen();
		Thread.sleep(2000);
		loginCredentials("arpitmittal", "");
		boolean check = checkSignIn();
		assertFalse(check);
		Reporter.log("Null or No password is working correctly");

	}

	public void s_3_LoginWithCorrectPassword() throws InterruptedException {
		ClearScreen();
		Thread.sleep(2000);
		loginCredentials("arpitmittal", "Arpit@321#");
		try {
			driver.findElement(parser.getbjectLocator("css2")).getText().contains("Invalid Login");
		} catch (NoSuchElementException e) {
			assertTrue(true);
		}
		Reporter.log("Correct password is working correctly. User Signed in to HRIS");
	}

	public void s_4_ValidateForTimeSheet() {
		assertTrue(driver.findElement(parser.getbjectLocator("css3")).getText().contains("Timesheet"));
	Reporter.log("User Loged in to HRIS. TimeSheet page is opened and checked.");
	}

	public void loginCredentials(String username, String Password) {
	 driver.findElement(parser.getbjectLocator("css1")).sendKeys(username);	
	driver.findElement(parser.getbjectLocator("Id1")).sendKeys(Password);
	driver.findElement(parser.getbjectLocator("Id1")).submit();
	}

	public void ClearScreen() {
		driver.findElement(parser.getbjectLocator("css1")).clear();
		driver.findElement(parser.getbjectLocator("Id1")).clear();
	}

	public boolean checkSignIn() {
		if (driver.findElement(parser.getbjectLocator("css2")).getText().contains("Invalid Login")) {
			return false;
		} else if (driver.findElement(parser.getbjectLocator("css4")).getAttribute("style").contains("red")) {
			return false;
		} else {
			return true;
		}
	}

	public void step_05PreviousDate() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE, -1);
		 YesDate = dateFormat.format(cal.getTime());
		System.out.println("Yesterday's date was " + YesDate); 
		Reporter.log("Yesterday's Date was displayed successfully");
	}
	
//	public void step_06timesheet() throws InterruptedException {
//		Thread.sleep(2000);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		String temp = js.executeScript(
//				"return (document.evaluate(\"//div[@class='td_cell']/span[text()='12']/following-sibling::div/div[1]/p/span/span\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).innerText")
//				.toString();
//		String temp1 = driver.findElement(By.cssSelector("[id=dv_"+ YesDate +"] [class='text-center ng-binding ng-scope']")).getText();
//		System.out.println(temp1); // IT is to print TRAINEES
//		String temp2 = driver.findElement(By.cssSelector("[id=dv_2018-06-12] [class='ng-binding']")).getText();
//		System.out.println(temp2);// IT is to Print TIME
//	}
	
	public void step_06_TimeSheet_Hover() 
	{
		try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		WebElement element = driver.findElement(By.id("dv_"+ YesDate));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		
		
		List<WebElement> temp = driver.findElements(By.cssSelector("[id=dv_"+ YesDate +"] [class='ng-binding']"));
		for (WebElement webElement : temp) 
		{
			System.out.println(webElement.getText());// IT is to Print the Hover Text including Time
		}
		Reporter.log("Hover and displaying values successfully");
	}	
	
	public void step_07_LogOutFromTimeSheet() {
		
		driver.findElement(parser.getbjectLocator("class2")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(parser.getbjectLocator("css6")).click();
		
		clickToLoginPanel();
		assertTrue(driver.findElement(parser.getbjectLocator("css7")).getText().contains("Login Here"));
		System.out.println("True");
		Reporter.log("User get LOGGED OUT from Timesheet");
	}
	public void browserClosed() {
		driver.quit();
		
	}
}
