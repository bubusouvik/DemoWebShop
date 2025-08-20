package com.ecommerce.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public WebDriver driver;

	@FindBy(linkText = "Register")
	private WebElement registerLink;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueBtn;

	@FindBy(xpath = "(//a[contains(text(),'Electronics')])[1]")
	private WebElement electronics;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getRegister() {
		return registerLink;
	}

	public WebElement getcontinueBtn() {
		return continueBtn;
	}

	public WebElement getElectronics() {
		return electronics;
	}

}
