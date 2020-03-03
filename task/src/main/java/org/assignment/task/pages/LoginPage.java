package org.assignment.task.pages;

import org.assignment.task.model.UserAuthDto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageBase {

	private By userNameField = By.xpath("//input[@ng-model='user.name']");
	private By passwordField = By.xpath("//input[@ng-model='user.password']");
	private By loginButton = By.xpath("//button[@class='main-button']");
	private By errorMessageField = By.xpath("//p[@class='error-message ng-binding']");
	private final EmployeesPage employeesPage;

	public LoginPage() {
		super();
		employeesPage = new EmployeesPage();
	}

	public void goTo() {

		driver.get(loginUrl);

	}

	public void login(UserAuthDto user) {

		WebElement userField = driver.findElement(userNameField);
		userField.sendKeys(user.getUsername());

		WebElement passField = driver.findElement(passwordField);
		passField.sendKeys(user.getPassword());

		WebElement button = driver.findElement(loginButton);
		button.click();
	}

	public String getErrorMessage() {

		waitUntilElementExists(errorMessageField);

		WebElement userField = driver.findElement(errorMessageField);
		return userField.getText();
	}

	public boolean isLogInSuccessful() {

		return employeesPage.isAt();
	}

	@Override
	public boolean isAt() {

		waitUntilElementClickable(loginButton);
		return driver.findElement(loginButton).isDisplayed();
	}

}
