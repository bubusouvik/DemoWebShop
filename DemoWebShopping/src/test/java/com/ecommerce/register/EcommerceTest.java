package com.ecommerce.register;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerce.baseclass.DemoShopBaseClass;
import com.ecommerce.genericutility.ExcelFileUtility;
import com.ecommerce.objectrepository.ElectronicsPage;
import com.ecommerce.objectrepository.HomePage;
import com.ecommerce.objectrepository.RegisterPage;

public class EcommerceTest extends DemoShopBaseClass {

	ExcelFileUtility efileutility = new ExcelFileUtility();
	String sheetname = "camera";
	String product = "Digital SLR Camera 12.2 Mpixel";
	String innerProduct = "Digital SLR Camera - Black";

	@Test
	public void registerApp() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		JSONParser parser = new JSONParser();
		Object object = parser.parse(new FileReader("./testappdata/register.json"));
		JSONObject jsonobj = (JSONObject) object;
		String gender = jsonobj.get("gender").toString();
		String firstname = jsonobj.get("firstname").toString();
		String lastname = jsonobj.get("lastname").toString();
		String email = jsonobj.get("email").toString();
		String password = jsonobj.get("password").toString();
		String confirmpassword = jsonobj.get("confirmpassword").toString();
		HomePage home = new HomePage(driver);
		RegisterPage register = new RegisterPage(driver);

		home.getRegister().click();
		register.getRegister(gender, firstname, lastname, email, password, confirmpassword);
		Thread.sleep(1000);
		home.getcontinueBtn().click();
	}

	@Test(dependsOnMethods = "registerApp")
	public void navigateToProductlist() throws EncryptedDocumentException, IOException {
		HomePage home = new HomePage(driver);
		ElectronicsPage electronics = new ElectronicsPage(driver);
		home.getElectronics().click();
		String sheet = efileutility.getSheetFromExcel(sheetname);
		Assert.assertEquals(sheetname, sheet);
		System.out.println("Sheet name is verified !!");
		if (sheet.equalsIgnoreCase("camera")) {

			electronics.getCamera().click();
		}

	}

	@Test(dependsOnMethods = "navigateToProductlist")
	public void addToCart() throws EncryptedDocumentException, IOException {
		String productName = efileutility.getProductFromExcel("camera", "Digital SLR Camera 12.2 Mpixel");
		Assert.assertEquals(productName, product);
		if (productName.equals(product)) {
			driver.findElement(By.xpath("//a[contains(text(),'" + productName + "')]")).click();
			String innerproductInfo = efileutility.getProductFromCell(sheetname, productName, innerProduct);
			Assert.assertEquals(innerproductInfo, innerProduct);
			if (innerproductInfo.equalsIgnoreCase(innerProduct)) {
				System.out.println(innerproductInfo + " passed");
				driver.findElement(By.xpath("//div[contains(text(),'" + innerproductInfo
						+ "')]/following-sibling::div[@class='add-to-cart']/descendant::input[@value='Add to cart']"))
						.click();
			}

		}
	}

}
