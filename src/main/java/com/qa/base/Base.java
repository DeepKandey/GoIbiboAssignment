package com.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.util.TestUtil;

public class Base {

	public WebDriver driver;
	public WebDriverWait wait;

	public void initiate_driver() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, TestUtil.MEDIUM_PAUSE);
		driver.manage().window().maximize();
	}

}
