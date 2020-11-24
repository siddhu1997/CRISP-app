package dao;

import java.util.Date;
import java.util.List;

import model.TestingHistory;

public interface TestingHistoryDAO {

	//Create operation
	int createTestingHistory(Integer personId, String hospital,Date testingDate,String result);

	//Read operation
	TestingHistory readTestingHistoryByPersonId(Integer personId);
	List<TestingHistory> readTestingHistory();

	//Update operation
	int updateTestingHistory(Integer personId,Date testingDate,String result);

	//Delete operation
	int deleteTestingHistory(Integer testingId);
	int deleteTestingHistory();
}
