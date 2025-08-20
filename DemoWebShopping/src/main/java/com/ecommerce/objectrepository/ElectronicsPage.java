package com.ecommerce.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ElectronicsPage {

	@FindBy(xpath = "(//a[contains(text(),'Camera, photo')])[4]")
	private WebElement camera;

	public ElectronicsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getCamera() {
		return camera;
	}
}
