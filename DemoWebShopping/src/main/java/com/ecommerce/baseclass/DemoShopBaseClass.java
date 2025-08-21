package com.ecommerce.baseclass;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import com.ecommerce.genericutility.JsonFileUtility;
import com.ecommerce.webdriverutility.DemoShopWebdriverUtility;

public class DemoShopBaseClass {

	DemoShopWebdriverUtility webutility = new DemoShopWebdriverUtility();
	JsonFileUtility fileutility = new JsonFileUtility();
	public WebDriver driver = null;

	@BeforeClass
	public void launchBrowser() throws FileNotFoundException, IOException, ParseException {
		String status = "common";
		String browser = fileutility.getJSONdata("browser", status);
		String url = fileutility.getJSONdata("url", status);
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new EdgeDriver();
		}
		webutility.maximizeScreen(driver);
		webutility.waitForPageLoad(driver);
		driver.get(url);

	}

}
