package dao;
import java.util.Date;

public class Person {
 private String name;
 private Date dob;
 public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}
private Integer personId;
 private String phoneNumber;
 private String bloodGroup;
 private String location;
 private String password;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getPersonId() {
	return personId;
}
public void setPersonId(Integer personId) {
	this.personId = personId;
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
public Person(String name, Date dob, Integer personId, String phoneNumber, String bloodGroup, String location,
		String password) {
	super();
	this.name = name;
	this.dob = dob;
	this.personId = personId;
	this.phoneNumber = phoneNumber;
	this.bloodGroup = bloodGroup;
	this.location = location;
	this.password = password;
}
public Person() {
	super();
}

 
 
}


