package org.assignment.task.pages;

import org.assignment.task.model.EmployeeDto;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeesPage extends PageBase {

	private By greetingsField = By.id("greetings");
	private By logoutField = By.xpath("//p[@class='main-button']");
	private By createField = By.id("bAdd");
	private By editField = By.id("bEdit");
	private By deleteField = By.id("bDelete");

	public void logout() {

		retryingFindClick(logoutField);

	}

	public void createEmployee() {

		waitUntilElementClickable(createField);
		driver.findElement(createField).click();

	}

	public void editEmployee() {

		waitUntilElementClickable(editField);
		driver.findElement(editField).click();

	}

	public void deleteEmployee(EmployeeDto user) {

		String searchKey = user.getFirstname() + " " + user.getLastname();

		String xPath = "//*[text()[contains(.,'" + searchKey + "')]]";
		By searchedElement = By.xpath(xPath);

		boolean isPresent = driver.findElements(searchedElement).size() > 0;

		if (isPresent) {
			driver.findElement(searchedElement).click();
			waitUntilElementClickable(deleteField);
			driver.findElement(deleteField).click();
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

	}

	public boolean selectUser(EmployeeDto user) {
		boolean isPresent = false;
		String searchKey = user.getFirstname() + " " + user.getLastname();

		String xPath = "//*[text()[contains(.,'" + searchKey + "')]]";
		By searchedElement = By.xpath(xPath);

		try {
			isPresent = driver.findElement(searchedElement).isEnabled();
		} catch (NoSuchElementException e) {
			//swallow the exception
		}

		if (isPresent) {
			driver.findElement(searchedElement).click();
			return true;
		} else {
			return false;
		}

	}

	public boolean isDeleteEnable() {

		return isElementEnabled(deleteField);
	}

	public boolean isEditEnable() {
		return isElementEnabled(editField);
	}

	@Override
	public boolean isAt() {

		waitUntilElementExists(greetingsField);

		return driver.findElements(greetingsField).size() != 0;
	}

}
