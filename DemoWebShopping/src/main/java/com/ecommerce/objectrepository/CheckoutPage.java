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

	@FindBy(xpath = "(//input[@title='Continue'])[1]")
	private WebElement continueBtn;

	@FindBy(xpath = "(//input[@title='Continue'])[2]")
	private WebElement continueBtnsecond;

	@FindBy(xpath = "//input[@id='shippingoption_1']")
	private WebElement nextDayAir;

	@FindBy(xpath = "(//input[@value='Continue'])[3]")
	private WebElement continueBtnThird;

	@FindBy(xpath = "(//input[@value='Continue'])[4]")
	private WebElement continueBtnFour;

	@FindBy(xpath = "(//input[@value='Continue'])[5]")
	private WebElement continueBtnFive;
	
	@FindBy(xpath = "//input[@value='Confirm']")
	private WebElement confirmBtn;

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

	public WebElement getcontinueBtn() {
		return continueBtn;
	}

	public WebElement getcontinueBtnsecond() {
		return continueBtnsecond;
	}

	public WebElement getnextDayAir() {
		return nextDayAir;
	}

	public WebElement getcontinueBtnThird() {
		return continueBtnThird;
	}

	public WebElement getcontinueBtnFour() {
		return continueBtnFour;
	}

	public WebElement getcontinueBtnFive() {
		return continueBtnFive;
	}
	
	public WebElement getconfirmBtn() {
		return confirmBtn;
	}

	public void addcheckOut(String country, String city, String address1, String address2, String zip, String phone) {
		getbillingCountry().sendKeys(country);
		getbillingCity().sendKeys(city);
		getBilling_Address1().sendKeys(address1);
		getBilling_Address2().sendKeys(address2);
		getzipPostalCode().sendKeys(zip);
		getphoneNumber().sendKeys(phone);
		getcontinueBtn().click();

	}

}
