package org.assignment.task.common;

import org.assignment.task.model.EmployeeDto;
import org.assignment.task.model.UserAuthDto;
import org.assignment.task.pages.CreatePage;
import org.assignment.task.pages.EditPage;
import org.assignment.task.pages.EmployeesPage;
import org.assignment.task.pages.LoginPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUser {

	UserAuthDto authorizedUser;
	UserAuthDto unAuthorizedUser;
	EmployeeDto employeeToAdd;
	EmployeeDto employeeToEdit;
	EmployeeDto inAppropriateEmployeeData;

	LoginPage loginPage;
	EmployeesPage employeesPage;
	CreatePage addPage;
	EditPage editPage;

	public TestUser() {
		authorizedUser = EmployeesTestData.getAuthorizedUser();
		employeeToAdd = EmployeesTestData.getEmployeeDetailsToAdd();
		employeeToEdit = EmployeesTestData.getEmployeeDetailsToEdit();
		unAuthorizedUser = EmployeesTestData.getUnAuthorizedUser();
		inAppropriateEmployeeData = EmployeesTestData.getInappropriateEmployeeDetails();

		loginPage = new LoginPage();
		employeesPage = new EmployeesPage();
		addPage = new CreatePage();
		editPage = new EditPage();
	}

	public void logsInWithAuthUser() {
		loginPage.goTo();
		loginPage.login(authorizedUser);
	}

	public void logsInWithUnAuthUser() {
		loginPage.goTo();
		loginPage.login(unAuthorizedUser);
	}

	public void logsOut() {
		employeesPage.logout();
	}

	public void cancelsCreation() {
		addPage.cancelAddingEmployee();
	}

	public void selectsAndEditsEmployee(EmployeeDto employee) {
		employeesPage.selectUser(employee);
		employeesPage.editEmployee();
	}

	public EmployeeDto editsFieldsOfEmployee() {
		editPage.editEmployee(employeeToEdit);
		return employeeToEdit;
	}

	public EmployeeDto addsEmployee() {
		employeesPage.createEmployee();
		addPage.addEmployee(employeeToAdd);
		return employeeToAdd;
	}

	public String addsImproperEmployee() {
		employeesPage.createEmployee();
		addPage.addEmployee(inAppropriateEmployeeData);

		String errorMessage = null;
		WebDriverWait wait = new WebDriverWait(Driver.getInstance(), 10);
		wait.until(ExpectedConditions.alertIsPresent());
		try {
			Alert alert = Driver.getInstance().switchTo().alert();
			Thread.sleep(1000);
			errorMessage = alert.getText();
			alert.accept();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return errorMessage;
	}

	public void cancelsEditing() {
		editPage.cancelEditing();
	}

	public void clicksCreateEmployee() {
		employeesPage.createEmployee();

	}

	public void removesEmployee(EmployeeDto employee) {
		employeesPage.deleteEmployee(employee);
	}

	public boolean verifyLoginSuccessfull() {
		return loginPage.isLogInSuccessful();
	}

	public boolean verifyErrorMessage(String message) {
		return loginPage.getErrorMessage().equals(message);
	}

	public boolean verifyAtLoginPage() {
		return loginPage.isAt();
	}

	public boolean verifyAtCreatePage() {
		return addPage.isAt();
	}

	public boolean verifyAtEmployeesPage() {
		return employeesPage.isAt();
	}

	public boolean verifyEmployeeAdded(EmployeeDto employee) {
		return employeesPage.selectUser(employee);
	}

	public boolean verifyAtEditPage() {
		return editPage.isAt();
	}

	public boolean verifyEmployeeEdited(EmployeeDto editedEmployee) {
		return employeesPage.selectUser(editedEmployee);
	}

	public boolean verifyEmployeeRemoved(EmployeeDto employee) {
		return !employeesPage.selectUser(employee);
	}

	public void deletesEmployeeWhileEditing() {
		editPage.deleteEmployee();
	}

	public boolean verifyImproperEmployeeDataError(String errorMessage) {
		String error = "Error trying to create a new employee";
		return errorMessage.contains(error);
	}

}