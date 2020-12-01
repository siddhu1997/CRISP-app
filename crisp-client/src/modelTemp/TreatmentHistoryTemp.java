package modelTemp;

public class TreatmentHistoryTemp {
	private Integer personId;
	private String treatmentDetails;
	private Long admissionDate;
	private Long recoveredDate;
	private Long deathDate;
	
	//Constructors
	public TreatmentHistoryTemp() {}

	public TreatmentHistoryTemp(Integer personId, String treatmentDetails, Long admissionDate, Long recoveredDate,Long deathDate) {
		this.personId = personId;
		this.treatmentDetails = treatmentDetails;
		this.admissionDate = admissionDate;
		this.recoveredDate = recoveredDate;
		this.deathDate = deathDate;
	}

	//getters & setters
	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getTreatmentDetails() {
		return treatmentDetails;
	}

	public void setTreatmentDetails(String treatmentDetails) {
		this.treatmentDetails = treatmentDetails;
	}

	public Long getAdmissionDate() {
		if(admissionDate == null)
			return 1L;
		return admissionDate;
	}

	public void setAdmissionDate(Long admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Long getRecoveredDate() {
		if(recoveredDate == null)
			return 1L;
		else
			return recoveredDate;
	}

	public void setRecoveredDate(Long recoveredDate) {
		this.recoveredDate = recoveredDate;
	}

	public Long getDeathDate() {
		if(deathDate == null)
			return 1L;
		else
			return deathDate;
	}

	public void setDeathDate(Long deathDate) {
		this.deathDate = deathDate;
	}

	@Override
	public String toString() {
		return "TreatmentHistory [personId=" + personId + ", treatmentDetails=" + treatmentDetails + ", admissionDate="
				+ admissionDate + ", recoveredDate=" + recoveredDate + ", deathDate=" + deathDate + "]";
	}
	
	//Use Comparator if comparing becomes necessary
}
