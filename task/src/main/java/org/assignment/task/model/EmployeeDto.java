package org.assignment.task.model;

public class EmployeeDto {

	private final String firstname;
	private final String lastname;
	private final String startdate;
	private final String email;

	public EmployeeDto(String firstname, String lastname, String startdate, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.startdate = startdate;
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getStartdate() {
		return startdate;
	}

	public String getEmail() {
		return email;
	}

}
