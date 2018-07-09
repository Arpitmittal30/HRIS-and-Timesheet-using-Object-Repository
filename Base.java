package com.qait.automation.Tatoc5;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {

		WebDriver driver;


	public Base() {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\ArpitMittal\\\\Downloads\\\\chromedriver_win32\\\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
}
