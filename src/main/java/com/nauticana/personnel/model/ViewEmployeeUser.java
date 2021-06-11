package com.nauticana.personnel.model;

import java.util.Date;

public class ViewEmployeeUser {

	private int personId;
	private Date employment;
	private String position;
	private String emailAddress;
	private String workPhone;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date birthday;
	private String gender;
	private String nationality;
	private String governmentId;
	private String personelMail;
	private String cellPhone;
	private String username;
	private String status;
	private String userMail;
	private Date passdate;
	private Date lastlogon;
	
	public ViewEmployeeUser(int personId, Date employment, String position, String emailAddress, String workPhone, String firstName, String middleName, String lastName, Date birthday, String gender, String nationality, String governmentId,
			String personelMail, String cellPhone, String username, String status, String userMail, Date passdate, Date lastlogon) {
		super();
		this.personId = personId;
		this.employment = employment;
		this.position = position;
		this.emailAddress = emailAddress;
		this.workPhone = workPhone;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.gender = gender;
		this.nationality = nationality;
		this.governmentId = governmentId;
		this.personelMail = personelMail;
		this.cellPhone = cellPhone;
		this.username = username;
		this.status = status;
		this.userMail = userMail;
		this.passdate = passdate;
		this.lastlogon = lastlogon;
	}

	public int getPersonId() {
		return personId;
	}

	public Date getEmployment() {
		return employment;
	}

	public String getPosition() {
		return position;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getGender() {
		return gender;
	}

	public String getNationality() {
		return nationality;
	}

	public String getGovernmentId() {
		return governmentId;
	}

	public String getPersonelMail() {
		return personelMail;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public String getUsername() {
		return username;
	}

	public String getStatus() {
		return status;
	}

	public String getUserMail() {
		return userMail;
	}

	public Date getPassdate() {
		return passdate;
	}

	public Date getLastlogon() {
		return lastlogon;
	}
	
	
}
