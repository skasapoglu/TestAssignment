package org.assignment.task.common;

import org.assignment.task.model.EmployeeDto;
import org.assignment.task.model.UserAuthDto;

public class EmployeesTestData {

	public static EmployeeDto getEmployeeDetailsToAdd() {
		String firstname = "testUser";
		String lastname = "test last Name";
		String startdate = "2020-02-21";
		String email = "testuser@test.com";

		return new EmployeeDto(firstname, lastname, startdate, email);
	}

	public static EmployeeDto getEmployeeDetailsToEdit() {
		String firstname = "edited User";
		String lastname = "edited *Last Name";
		String startdate = "2012-09-30";
		String email = "editedEmail@email.com";

		return new EmployeeDto(firstname, lastname, startdate, email);
	}

	public static EmployeeDto getInappropriateEmployeeDetails() {
		String firstname = "username";
		String lastname = "lastname";
		String startdate = "2012-14-30";
		String email = "edited@Emailemail.com";

		return new EmployeeDto(firstname, lastname, startdate, email);
	}

	public static UserAuthDto getAuthorizedUser() {
		Config.initilize();
		return new UserAuthDto(Config.getUsername(), Config.getPassword());
	}

	public static UserAuthDto getUnAuthorizedUser() {
		return new UserAuthDto("wrongName", "wrongPassword");
	}

}
