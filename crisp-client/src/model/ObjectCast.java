package model;

//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ObjectCast {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
}
