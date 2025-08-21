package com.ecommerce.webdriverutility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DemoShopWebdriverUtility {

	public void maximizeScreen(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void waitForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	public String selectDropdown(WebElement dropdownId, String value) {
		Select dropdown = new Select(dropdownId);
		List<WebElement> options = dropdown.getOptions();
		String optiontext = null;
		for (WebElement option : options) {
			optiontext = option.getText();
			if (optiontext.equals(value)) {
				break;
			}
		}
		return optiontext;

	}

}
