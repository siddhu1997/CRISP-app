package model;

public class DonationRequest {
	
	private Integer reqId;
	private Person person;
	private String donorRemarks;
	private String adminRemarks;
	private String status;
	
	//constructor
	public DonationRequest() {}

	//reqId is an auto increment attribute; no need to initialize
	public DonationRequest(Person person, String donorRemarks, String adminRemarks, String status) {
		this.person = person;
		this.donorRemarks = donorRemarks;
		this.adminRemarks = adminRemarks;
		this.status = status;
	}

	//getters and setters
	public Integer getReqId() {
		return reqId;
	}

	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getDonorRemarks() {
		return donorRemarks;
	}

	public void setDonorRemarks(String donorRemarks) {
		this.donorRemarks = donorRemarks;
	}

	public String getAdminRemarks() {
		return adminRemarks;
	}

	public void setAdminRemarks(String adminRemarks) {
		this.adminRemarks = adminRemarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DonationRequest [reqId=" + reqId + ", person=" + person + ", donorRemarks=" + donorRemarks
				+ ", adminRemarks=" + adminRemarks + ", status=" + status + "]";
	}

}
