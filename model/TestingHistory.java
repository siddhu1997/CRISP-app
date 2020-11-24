package model;

import java.util.Date;

public class TestingHistory {

	private Integer testingId;
	private Person person;
	private String hospital;
	private Date testingDate;
	private String result;
	
	//Constructors
	public TestingHistory() {}

	//testingId is an auto increment attribute; no need to initialize
	public TestingHistory(Person person, String hospital, Date testingDate, String result) {
		super();
		this.person = person;
		this.hospital = hospital;
		this.testingDate = testingDate;
		this.result = result;
	}

	//getters and setters
	public Integer getTestingId() {
		return testingId;
	}

	public void setTestingId(Integer testingId) {
		this.testingId = testingId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public Date getTestingDate() {
		return testingDate;
	}

	public void setTestingDate(Date testingDate) {
		this.testingDate = testingDate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "TestingHistory [testingId=" + testingId + ", person=" + person + ", hospital=" + hospital
				+ ", testingDate=" + testingDate + ", result=" + result + "]";
	}
	
	
}
