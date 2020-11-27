package modelTemp;

import java.util.Date;

import model.Person;
import model.TestingHistory;
import model.TreatmentHistory;

public class ObjectCast {
	
	public static Person toPerson(PersonTemp t1){
		Person person = new Person();
		person.setPersonId(t1.getPersonId());
		person.setName(t1.getName());
		person.setBloodGroup(t1.getBloodGroup());
		Date d = new Date(t1.getDob());
		person.setDob(d);
		person.setPhoneNumber(t1.getPhoneNumber());
		person.setLocation(t1.getLocation());
		person.setPassword(t1.getPassword());
		return person;
	}
	public static TestingHistory toTestingHistory(TestingHistoryTemp t1){
		TestingHistory test = new TestingHistory();
		test.setTestingId(t1.getTestingId());
		test.setPerson(t1.getPersonId());
		test.setHospital(t1.getHospital());
		Date d = new Date(t1.getTestingDate());
		test.setTestingDate(d);
		test.setResult(t1.getResult());
		return test;
	}
	
	public static TreatmentHistory toTreatmentHistory(TreatmentHistoryTemp t1) {
		TreatmentHistory th = new TreatmentHistory();
		th.setPersonId(t1.getPersonId());
		Date d = new Date(t1.getAdmissionDate());
		th.setAdmissionDate(d);
		if(t1.getRecoveredDate() == 1L) {
			th.setRecoveredDate(null);
		}
		if(t1.getRecoveredDate()!= 1L) {
			Date d1 = new Date(t1.getRecoveredDate());
			th.setRecoveredDate(d1);
		}
		if(t1.getDeathDate() == 1L)
			th.setDeathDate(null);
		if(t1.getDeathDate()!= 1L) {
			Date d2 = new Date(t1.getDeathDate());
			th.setDeathDate(d2);
		}
		th.setTreatmentDetails(t1.getTreatmentDetails());
		return th;
	}
}
