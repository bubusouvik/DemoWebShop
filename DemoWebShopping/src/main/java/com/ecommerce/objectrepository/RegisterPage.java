package com.ecommerce.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	@FindBy(id = "gender-male")
	private WebElement male;

	@FindBy(id = "gender-female")
	private WebElement female;

	@FindBy(id = "FirstName")
	private WebElement firstName;

	@FindBy(id = "LastName")
	private WebElement lastName;

	@FindBy(id = "Email")
	private WebElement email;

	@FindBy(id = "Password")
	private WebElement password;

	@FindBy(id = "ConfirmPassword")
	private WebElement confirmPassword;

	@FindBy(id = "register-button")
	private WebElement registerbtn;

	public RegisterPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getMale() {
		return male;
	}

	public WebElement getFemale() {
		return female;
	}

	public WebElement getfirstName() {
		return firstName;
	}

	public WebElement getlastName() {
		return lastName;
	}

	public WebElement getEmail() {
		return email;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getconfirmPassword() {
		return confirmPassword;
	}

	public WebElement getRegisterbtn() {
		return registerbtn;
	}

	public void getRegister(String gender, String fname, String lname, String email, String pass, String confirmpass) {

		if (gender.equalsIgnoreCase("male")) {
			getMale().sendKeys(gender);
		} else {
			getFemale().sendKeys("gender");
		}
		getfirstName().sendKeys(fname);
		getlastName().sendKeys(lname);
		getEmail().sendKeys(email);
		getPassword().sendKeys(pass);
		getconfirmPassword().sendKeys(confirmpass);
		getRegisterbtn().click();

	}

}
