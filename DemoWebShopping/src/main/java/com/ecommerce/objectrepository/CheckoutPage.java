package com.ecommerce.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

	@FindBy(id = "BillingNewAddress_CountryId")
	private WebElement billingCountry;

	@FindBy(id = "BillingNewAddress_City")
	private WebElement billingCity;

	@FindBy(id = "BillingNewAddress_Address1")
	private WebElement billingAddress1;

	@FindBy(id = "BillingNewAddress_Address2")
	private WebElement billingAddress2;

	@FindBy(id = "BillingNewAddress_ZipPostalCode")
	private WebElement zipPostalCode;

	@FindBy(id = "BillingNewAddress_PhoneNumber")
	private WebElement phoneNumber;
	
	

	public CheckoutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getbillingCountry() {
		return billingCountry;
	}

	public WebElement getbillingCity() {
		return billingCity;
	}

	public WebElement getBilling_Address1() {
		return billingAddress1;
	}

	public WebElement getBilling_Address2() {
		return billingAddress2;
	}

	public WebElement getzipPostalCode() {
		return zipPostalCode;
	}

	public WebElement getphoneNumber() {
		return phoneNumber;
	}
	
	public void addcheckOut(String country, String city, String address1, String address2, String zip, double phone) {
		getbillingCountry().sendKeys(country);
		getbillingCity().sendKeys(city);
		getBilling_Address1().sendKeys(address1);
		getBilling_Address2().sendKeys(address2);
		getzipPostalCode().sendKeys(zip);
		getphoneNumber().sendKeys(phone);
		
	}

}
