package dao;

import java.util.Date;
import java.util.List;

import model.Person;
import model.TreatmentHistory;

public interface TreatmentHistoryDAO {

	//Create operation
	int createTreatmentHistory(Person person, String treatmentDetails, Date admissionDate, Date recoveredDate,Date deathDate);

	//Read operations
	
	//Read all
	List<TreatmentHistory> readTreatmentHistory();
	//Get particular TreatmentHistory
	TreatmentHistory readTreatmentHistoryByPersonId(Person person);

	//Update operations
	int updateAdmissionDate(Person person,String treatmentDetails,Date admissionDate );
	int updateRecoveredDate(Person person,String treatmentDetails,Date recoveredDate );
	int updateDeathDate(Person person,String treatmentDetails,Date deathDate);

	//Delete operations
	int deleteTreatmentHistory(Person person);
	int deleteTreatmentHistory();
}
