package model; 

public class DonationRequest {
	private Integer reqId;
	private Integer personId;
	private String donorRemarks;
	private String adminRemarks;
	private String status;
	
	public DonationRequest() {
		
	}

	public DonationRequest(Integer reqId, Integer personId, String donorRemarks, String adminRemarks, String status) {
		this.reqId = reqId;
		this.personId = personId;
		this.donorRemarks = donorRemarks;
		this.adminRemarks = adminRemarks;
		this.status = status;
	}
	
	public DonationRequest(Integer reqId, Integer personId, String adminRemarks, String status) {
		super();
		this.reqId = reqId;
		this.personId = personId;
		this.adminRemarks = adminRemarks;
		this.status = status;
	}

	public DonationRequest(Integer personId, String donorRemarks, String adminRemarks, String status) {
		this.personId = personId;
		this.donorRemarks = donorRemarks;
		this.adminRemarks = adminRemarks;
		this.status = status;
	}

	public Integer getReqId() {
		return reqId;
	}

	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
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
		return "DonationRequest [reqId=" + reqId + ", personId=" + personId + ", donorRemarks=" + donorRemarks
				+ ", adminRemarks=" + adminRemarks + ", status=" + status + "]";
	}

}
