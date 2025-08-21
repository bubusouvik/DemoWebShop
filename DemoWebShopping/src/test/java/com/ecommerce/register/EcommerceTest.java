package com.ecommerce.register;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerce.baseclass.DemoShopBaseClass;
import com.ecommerce.genericutility.ExcelFileUtility;
import com.ecommerce.genericutility.FileUtility;
import com.ecommerce.genericutility.JsonFileUtility;
import com.ecommerce.objectrepository.CartPage;
import com.ecommerce.objectrepository.ElectronicsPage;
import com.ecommerce.objectrepository.HomePage;
import com.ecommerce.objectrepository.RegisterPage;
import com.ecommerce.webdriverutility.DemoShopWebdriverUtility;

public class EcommerceTest extends DemoShopBaseClass {

	ExcelFileUtility efileutility = new ExcelFileUtility();
	JsonFileUtility fileutility = new JsonFileUtility();
	DemoShopWebdriverUtility webUtility = new DemoShopWebdriverUtility();
	FileUtility file = new FileUtility();
	String sheetname = "camera";
	String product = "Digital SLR Camera 12.2 Mpixel";
	String innerProduct = "Digital SLR Camera - Black";

	@Test
	public void registerApp() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		String status = "register";
		String gender = fileutility.getJSONdata("gender", status);
		String firstname = fileutility.getJSONdata("firstname", status);
		String lastname = fileutility.getJSONdata("lastname", status);
		String email = firstname + file.getRandomNumber() + "@gmail.com";
		String password = fileutility.getJSONdata("password", status);
		String confirmpassword = fileutility.getJSONdata("confirmpassword", status);
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
	public void addToCart() throws EncryptedDocumentException, IOException, InterruptedException {
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
				Thread.sleep(1200);
			}

		}
	}

	@Test(dependsOnMethods = "addToCart")
	public void checkOut() {
		HomePage home = new HomePage(driver);
		home.getshoppingCart().click();
		CartPage cart = new CartPage(driver);
		String dropdownValue = webUtility.selectDropdown(cart.getCountryDropdown(), "India");
		cart.addToCheckout(dropdownValue);

	}

}
