package com.ecommerce.webdriverutility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.io.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.ecommerce.genericutility.FileUtility;

public class DemoShopWebdriverUtility {
	FileUtility file = new FileUtility();

	public void maximizeScreen(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void waitForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	public void scrollToElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.scrollToElement(element).perform();
	}

	public void scrollByAmount(WebDriver driver, int y) {
		Actions action = new Actions(driver);
		action.scrollByAmount(0, y).perform();
	}

	public void takecaptureImage(WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/" + "confirmationReport" + file.getRandomNumber() + ".png");
		FileHandler.copy(src, dest);
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
