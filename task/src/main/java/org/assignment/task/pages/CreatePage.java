package org.assignment.task.pages;

import org.assignment.task.model.EmployeeDto;
import org.openqa.selenium.By;

public class CreatePage extends PageBase {

	private By firstnameField = By.xpath("//input[@ng-model='selectedEmployee.firstName']");
	private By lastnameField = By.xpath("//input[@ng-model='selectedEmployee.lastName']");
	private By startdateField = By.xpath("//input[@ng-model='selectedEmployee.startDate']");
	private By emailField = By.xpath("//input[@ng-model='selectedEmployee.email']");
	private By addButton = By.xpath("//button[@class='main-button']");
	private By cancelButton = By.xpath("//a[@class='subButton bCancel']");

	public void addEmployee(EmployeeDto user) {
		driver.findElement(firstnameField).sendKeys(user.getFirstname());
		driver.findElement(lastnameField).sendKeys(user.getLastname());
		driver.findElement(startdateField).sendKeys(user.getStartdate());
		driver.findElement(emailField).sendKeys(user.getEmail());

		retryingFindClick(addButton);
	}

	public void cancelAddingEmployee() {
		waitUntilElementClickable(cancelButton);
		driver.findElement(cancelButton).click();

	}

	@Override
	public boolean isAt() {

		waitUntilElementExists(firstnameField);
		return driver.findElements(firstnameField).size() != 0;
	}

}
