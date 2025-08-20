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

import com.ecommerce.webdriverutility.DemoShopWebdriverUtility;

public class DemoShopBaseClass {

	DemoShopWebdriverUtility webutility = new DemoShopWebdriverUtility();
	public WebDriver driver = null;

	@BeforeClass
	public void launchBrowser() throws FileNotFoundException, IOException, ParseException {

		JSONParser parser = new JSONParser();
		Object object = parser.parse(new FileReader("./testappdata/commondata.json"));
		JSONObject jsonobj = (JSONObject) object;
		String browser = jsonobj.get("browser").toString();
		String url = jsonobj.get("url").toString();
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
