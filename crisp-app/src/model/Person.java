package model;

import java.util.Date;

import com.cts.crisp.CustomDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


public class Person {

	private Integer personId;
	private String name;
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date dob;
	private String phoneNumber;
	private String bloodGroup;
	private String location;
	private String password;
	
	//Constructors
	public Person() {}
	
	public Person(Integer personId) {
		this.personId = personId;
	}

	//No need for Person id because person id is auto increment attribute in database.
	public Person(String name, Date dob, String phoneNumber, String bloodGroup, String location, String password) {
		this.name = name;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.bloodGroup = bloodGroup;
		this.location = location;
		this.password = password;
	}

	public Person(Integer personId, String name, Date dob, String phoneNumber, String bloodGroup, String location,
			String password) {
		this.personId = personId;
		this.name = name;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.bloodGroup = bloodGroup;
		this.location = location;
		this.password = password;
	}

	//getters and setters 
	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonDeserialize(using=CustomDateDeserializer.class)
	public Date getDob() {
		return dob;
	}

	@JsonDeserialize(using=CustomDateDeserializer.class)
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", name=" + name + ", dob=" + dob + ", phoneNumber=" + phoneNumber
				+ ", bloodGroup=" + bloodGroup + ", location=" + location + ", password=" + password + "]";
	}

	@Override
	public boolean equals(Object obj) {
		
		Person person = (Person) obj;
		
		//Two persons are equal if they have same name, blood group, dob and phoneNumber
		return this.getName().equals(person.getName()) &&
			   this.getBloodGroup().equals(person.getBloodGroup()) &&
			   this.getDob().equals(person.getDob()) &&
			   this.getPhoneNumber().equals(person.getPhoneNumber());
	}
	
	
}
