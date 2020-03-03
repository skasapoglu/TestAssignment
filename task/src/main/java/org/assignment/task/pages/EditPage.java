package org.assignment.task.pages;

import org.assignment.task.model.EmployeeDto;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditPage extends PageBase {

	private By firstnameField = By.xpath("//input[@ng-model='selectedEmployee.firstName']");
	private By lastnameField = By.xpath("//input[@ng-model='selectedEmployee.lastName']");
	private By startdateField = By.xpath("//input[@ng-model='selectedEmployee.startDate']");
	private By emailField = By.xpath("//input[@ng-model='selectedEmployee.email']");
	private By updateButton = By.xpath("//button[@class='main-button']");
	private By deleteButton = By.xpath("//p[contains(text(),'Delete')]");
	private By backButton = By.xpath("//a[@class='subButton bBack']");

	public void editEmployee(EmployeeDto user) {
		driver.findElement(firstnameField).clear();
		driver.findElement(firstnameField).sendKeys(user.getFirstname());
		driver.findElement(lastnameField).clear();
		driver.findElement(lastnameField).sendKeys(user.getLastname());
		driver.findElement(startdateField).clear();
		driver.findElement(startdateField).sendKeys(user.getStartdate());
		driver.findElement(emailField).clear();
		driver.findElement(emailField).sendKeys(user.getEmail());

		waitUntilElementClickable(updateButton);
		driver.findElement(updateButton).click();
	}

	public void cancelEditing() {
		waitUntilElementClickable(backButton);
		driver.findElement(backButton).click();

	}

	public void deleteEmployee() {

		waitUntilElementClickable(deleteButton);
		driver.findElement(deleteButton).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
		try {
			Alert alert = driver.switchTo().alert();
			Thread.sleep(1000);
			alert.accept();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean isAt() {
		waitUntilElementExists(firstnameField);
		return driver.findElements(firstnameField).size() != 0;

	}

}
