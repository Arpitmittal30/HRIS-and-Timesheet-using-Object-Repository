package com.qait.automation.Tatoc5;



import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class AppTest extends Base{
	
	LoginForm l1 = new LoginForm(driver);

	@BeforeTest
	public void launchbrowser() {
		l1.urlToBeReached();
 	}
	
	@Test
	public void Step01_LoginWithIncorrectPassword(){
		l1.s_1_LoginWithIncorrectPassword();	
	}
	
	@Test
	public void Step02_LoginWithBlankPassword() throws InterruptedException{
		l1.s_2_LoginWithBlankPassword();	
	}
	
	@Test
	public void Step03_LoginWithCorrectPassword() throws InterruptedException{
		l1.s_3_LoginWithCorrectPassword();
	}
	
	@Test
	public void Step04_ValidateForTimeSheet() throws InterruptedException{
		l1.s_4_ValidateForTimeSheet();
	}
	}

   