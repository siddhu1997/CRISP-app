package dao;

import java.util.List;

import model.DonationRequest;
import model.Person;

public interface DonationRequestDAO {

	//Create operation
	int createDonationRequest(Person person,String donorRemarks,String adminRemarks,String stats);

	//Read operations
	List<DonationRequest> readDonationRequest(); // Read requests where status = pending
	String readDonationRequestStatus(Person person);

	//Update operation
	int updateStatus(Integer personId,String status);

	//Delete operations
	int deleteDonationRequest(Integer personId);
	int deleteDonationRequest();
}
