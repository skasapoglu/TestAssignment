package org.assignment.task.frontend;

import org.assignment.task.common.Driver;
import org.assignment.task.common.TestUser;
import org.assignment.task.model.EmployeeDto;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FunctionalTest {

	TestUser tester;

	@BeforeClass
	public void beforeClass() {

		tester = new TestUser();

	}

	@AfterMethod
	private void tearDownTestData() {
		tester.logsOut();
	}

	@AfterClass
	public void cleanUp() {
		Driver.quit();
	}

	@Test
	public void authorizedUser_CanLogin() {

		tester.logsInWithAuthUser();

		Assert.assertTrue(tester.verifyLoginSuccessfull(), "Login failed for authenticated user..");

	}

	@Test
	public void unAuthorizedUser_CanNotLogin() {

		String expectedErrorMessage = "Invalid username or password!";
		tester.logsInWithUnAuthUser();

		Assert.assertTrue(tester.verifyErrorMessage(expectedErrorMessage), "Error message is not properly displayed..");
	}

	@Test
	public void loggedinUser_CanLogout() {

		tester.logsInWithAuthUser();
		tester.logsOut();

		Assert.assertTrue(tester.verifyAtLoginPage(), "Logged in user cannot be logged out succeessfully..");
	}

	@Test
	public void loggedInUser_canCreate_newEmployee() {

		tester.logsInWithAuthUser();
		EmployeeDto employee = tester.addsEmployee();
		
		Assert.assertTrue(tester.verifyAtEmployeesPage(),
				"After adding employee, system could not navigate to Users Page");
		Assert.assertTrue(tester.verifyEmployeeAdded(employee), "Created employee cannot be seen on users menu");

		tester.removesEmployee(employee);

	}
	
	@Test
	public void loggedInUser_canNotCreate_badFormatEmployeeData() {
		
		tester.logsInWithAuthUser();
		String errorMessage = tester.addsImproperEmployee();
		
		Assert.assertTrue(tester.verifyImproperEmployeeDataError(errorMessage));
		
		
	}

	@Test
	public void loggedInUser_canCancel_creatingNewUser() {

		tester.logsInWithAuthUser();
		tester.clicksCreateEmployee();
		tester.cancelsCreation();

		Assert.assertTrue(tester.verifyAtEmployeesPage(),
				"After canceling addition, system could not navigate to Users Page");
	}

	@Test
	public void loggedInUser_canEdit_existentEmployee() {

		tester.logsInWithAuthUser();

		EmployeeDto employee = tester.addsEmployee();

		tester.selectsAndEditsEmployee(employee);
		Assert.assertTrue(tester.verifyAtEditPage(), "System could not navigate to Editing Page");

		EmployeeDto editedEmployee = tester.editsFieldsOfEmployee();

		Assert.assertTrue(tester.verifyAtEmployeesPage(),
				"After editting employee, system could not navigate to Employees Page");
		Assert.assertTrue(tester.verifyEmployeeEdited(editedEmployee),
				"Edited employee cannot be seen on employees menu");

		tester.removesEmployee(editedEmployee);
	}

	@Test
	public void loggedInUser_canCancel_editingExistentEmployee() {

		tester.logsInWithAuthUser();

		EmployeeDto employee = tester.addsEmployee();
		tester.selectsAndEditsEmployee(employee);

		tester.cancelsEditing();
		Assert.assertTrue(tester.verifyAtEmployeesPage(), "System could not navigate to Employees Page");

		tester.removesEmployee(employee);

	}

	@Test
	public void loggedInUser_canDelete_existentEmployee() throws Exception {

		tester.logsInWithAuthUser();
		EmployeeDto employee = tester.addsEmployee();

		tester.removesEmployee(employee);

		tester.logsInWithAuthUser();
		Assert.assertTrue(tester.verifyEmployeeRemoved(employee), "Removed employee can be searched on employees menu");

	}

	@Test
	public void loggedinUser_canDelete_whileEditingEmployee() {

		tester.logsInWithAuthUser();
		EmployeeDto employee = tester.addsEmployee();
		tester.selectsAndEditsEmployee(employee);

		tester.deletesEmployeeWhileEditing();
		Assert.assertTrue(tester.verifyAtEmployeesPage(),
				"After editting employee, system could not navigate to Employees Page");
		Assert.assertTrue(tester.verifyEmployeeRemoved(employee), "Edited employee cannot be seen on employees menu");

	}

}
