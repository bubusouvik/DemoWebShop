package com.ecommerce.baseclass;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.ecommerce.genericutility.FileUtility;
import com.ecommerce.genericutility.JsonFileUtility;
import com.ecommerce.webdriverutility.DemoShopWebdriverUtility;

public class DemoShopBaseClass {

	DemoShopWebdriverUtility webutility = new DemoShopWebdriverUtility();
	JsonFileUtility fileutility = new JsonFileUtility();
	FileUtility file = new FileUtility();
	public WebDriver driver = null;

	@BeforeClass
	public void launchBrowser() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		// authentication pop up for chrome
		ChromeOptions chromeoption = new ChromeOptions();

//		Map<String, Object> prefs = new HashMap<>();
		chromeoption.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {
			{
				put("autofill.profile_enabled", false); // Disables "Save address?" prompt
				put("credentials_enable_service", false); //other preferences as needed, e.g., for password manager
				put("profile.password_manager_enabled", false);
			}
		});

		// authentication pop up for firefox
		FirefoxOptions firefoxoption = new FirefoxOptions();
		firefoxoption.addArguments("--disable-notifications");

		// authentication pop up for edge
		EdgeOptions edgeoption = new EdgeOptions();
		edgeoption.addArguments("--disable-notifications");

		String status = "common";
		String browser = fileutility.getJSONdata("browser", status);
		String url = fileutility.getJSONdata("url", status);
		if (browser.equals("chrome")) {
			driver = new ChromeDriver(chromeoption);
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver(firefoxoption);
		} else {
			driver = new EdgeDriver(edgeoption);
		}
		webutility.maximizeScreen(driver);
		webutility.waitForPageLoad(driver);
		driver.get(url);
	}

	@AfterClass
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

}
