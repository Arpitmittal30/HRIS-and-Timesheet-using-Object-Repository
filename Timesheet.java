package com.qait.automation.Tatoc5;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Timesheet extends Base {

LoginForm ln;
boolean check;

@BeforeTest
public void initiation() {
ln = new LoginForm(driver);
ln.urlToBeReached();
}

@Test
public void step01_ToFindPreviousDate(){
		ln.step_05PreviousDate();
}

@Test
public void step03_LoginWithCorrectPass() throws InterruptedException{
	ln.clickToLoginPanel();	
	ln.s_3_LoginWithCorrectPassword();		
}

@Test
public void step04_timesheet_validate() throws InterruptedException {
	//ln.step_06timesheet();
	ln.step_06_TimeSheet_Hover();
	 
}
@Test
public void step05_LoggedOut(){
	ln.step_07_LogOutFromTimeSheet();
	ln.browserClosed();
}





}
