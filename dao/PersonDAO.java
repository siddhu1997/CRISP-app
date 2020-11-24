package dao;

import java.util.List;

import model.Person;

public interface PersonDAO {
	
	//Create operation
	int createPerson(Person person);
	
	//Read operations
	Person readPersonById(Integer personId);
	List<Person> readPerson();
	Person readPersonByPhoneNumber(String phoneNumber);

	//Update operation
	int updatePerson(Integer personId, String phoneNumber, String location);

	//delete operation
	int deletePerson(Integer personId);
	int deletePerson();
}
