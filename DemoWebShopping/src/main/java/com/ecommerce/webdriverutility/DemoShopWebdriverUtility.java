package com.ecommerce.webdriverutility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class DemoShopWebdriverUtility {

	public void maximizeScreen(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void waitForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	

}
