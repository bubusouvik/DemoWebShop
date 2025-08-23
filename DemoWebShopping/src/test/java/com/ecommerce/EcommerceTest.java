package com.ecommerce;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecommerce.baseclass.DemoShopBaseClass;
import com.ecommerce.genericutility.ExcelFileUtility;
import com.ecommerce.genericutility.FileUtility;
import com.ecommerce.genericutility.JsonFileUtility;
import com.ecommerce.objectrepository.CartPage;
import com.ecommerce.objectrepository.CheckoutPage;
import com.ecommerce.objectrepository.ElectronicsPage;
import com.ecommerce.objectrepository.HomePage;
import com.ecommerce.objectrepository.LoginPage;
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
	String user_username = null;
	String user_password = null;

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
		user_username = email;
		user_password = password;
		home.getLogout().click();
	}

	@Test(dependsOnMethods = "registerApp")
	public void loginToApp() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		HomePage home = new HomePage(driver);
		home.getLogin().click();
		LoginPage login = new LoginPage(driver);
		login.loginToApp(user_username, user_password);
	}

	@Test(dependsOnMethods = "loginToApp")
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
				Thread.sleep(1500);
			}

		}
	}

	@Test(dependsOnMethods = "addToCart")
	public void checkOut() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		String status = "register";
		HomePage home = new HomePage(driver);
		home.getshoppingCart().click();
		CartPage cart = new CartPage(driver);
		String dropdownValue = webUtility.selectDropdown(cart.getCountryDropdown(), "India");
		cart.addToCheckout(dropdownValue);
		CheckoutPage checkout = new CheckoutPage(driver);
		checkout.addcheckOut("India", fileutility.getJSONdata("city", status),
				fileutility.getJSONdata("address1", status), fileutility.getJSONdata("address2", status),
				fileutility.getJSONdata("postalcode", status), fileutility.getJSONdata("mobileno", status));
		Thread.sleep(1000);
		webUtility.scrollToElement(driver, checkout.getcontinueBtnsecond());
		checkout.getcontinueBtnsecond().click();
		Thread.sleep(1000);
		checkout.getnextDayAir().click();
		checkout.getcontinueBtnThird().click();
		Thread.sleep(1000);
		checkout.getcontinueBtnFour().click();
		Thread.sleep(1000);
		checkout.getcontinueBtnFive().click();
		Thread.sleep(1500);
		webUtility.scrollByAmount(driver, 500);
		checkout.getconfirmBtn().click();
		Thread.sleep(2000);
		webUtility.takecaptureImage(driver);

	}

	@Test(dependsOnMethods = "checkOut")
	public void logoutApp() {
		HomePage home = new HomePage(driver);
		home.getLogout().click();
	}

}
