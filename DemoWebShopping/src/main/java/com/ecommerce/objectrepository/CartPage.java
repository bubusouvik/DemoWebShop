package com.ecommerce.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	@FindBy(id = "CountryId")
	private WebElement countryDropdown;

	@FindBy(id = "termsofservice")
	private WebElement termsofservice;

	@FindBy(id = "checkout")
	private WebElement checkoutbtn;

	public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getCountryDropdown() {
		return countryDropdown;
	}

	public WebElement getTermsofservice() {
		return termsofservice;
	}

	public WebElement getCheckoutBtn() {
		return checkoutbtn;
	}

	public void addToCheckout(String dropdownvalue) {
		getCountryDropdown().sendKeys(dropdownvalue);
		getTermsofservice().click();
		getCheckoutBtn().click();
	}
}
