package model;

import java.util.Date;

public class TreatmentHistory {
	private Person person;
	private String treatmentDetails;
	private Date admissionDate;
	private Date recoveredDate;
	private Date deathDate;
	
	//Constructors
	public TreatmentHistory() {}

	public TreatmentHistory(Person person, String treatmentDetails, Date admissionDate, Date recoveredDate,
			Date deathDate) {
		this.person = person;
		this.treatmentDetails = treatmentDetails;
		this.admissionDate = admissionDate;
		this.recoveredDate = recoveredDate;
		this.deathDate = deathDate;
	}

	//getters & setters
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getTreatmentDetails() {
		return treatmentDetails;
	}

	public void setTreatmentDetails(String treatmentDetails) {
		this.treatmentDetails = treatmentDetails;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Date getRecoveredDate() {
		return recoveredDate;
	}

	public void setRecoveredDate(Date recoveredDate) {
		this.recoveredDate = recoveredDate;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	@Override
	public String toString() {
		return "TreatmentHistory [person=" + person + ", treatmentDetails=" + treatmentDetails + ", admissionDate="
				+ admissionDate + ", recoveredDate=" + recoveredDate + ", deathDate=" + deathDate + "]";
	}
	
	//Use Comparator if comparing becomes necessary
}
