package modelTemp;


public class TestingHistoryTemp {

	private Integer testingId;
	private Integer personId;
	private String hospital;
	private long testingDate;
	private String result;
	
	//Constructors
	public TestingHistoryTemp() {}

	//testingId is an auto increment attribute; no need to initialize
	public TestingHistoryTemp(Integer personId, String hospital, long testingDate, String result) {
		super();
		this.personId = personId;
		this.hospital = hospital;
		this.testingDate = testingDate;
		this.result = result;
	}

	public TestingHistoryTemp(Integer testingId, Integer personId, String hospital, long testingDate, String result) {
		super();
		this.testingId = testingId;
		this.personId = personId;
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

	public Integer getPersonId() {
		return personId;
	}

	public void setPerson(Integer personId) {
		this.personId = personId;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public long getTestingDate() {
		return testingDate;
	}

	public void setTestingDate(long testingDate) {
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
		return "TestingHistory [testingId=" + testingId + ", person=" + personId + ", hospital=" + hospital
				+ ", testingDate=" + testingDate + ", result=" + result + "]";
	}
	
	
}
