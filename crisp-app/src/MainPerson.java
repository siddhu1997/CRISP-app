
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.PersonDAOImpl;
import model.Person;

public class MainPerson {

	public static void insert() throws ClassNotFoundException, SQLException, ParseException { 
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Insertion Test:");
		String name;
		Date dob;
		String phoneNumber;
		String bloodGroup;
		String location;
		String password;
		
		System.out.println("Enter Name:");
		name = sc.nextLine();
		System.out.println("Enter Phone Number:");
		phoneNumber = sc.nextLine();
		System.out.println("Enter Blood group:");
		bloodGroup = sc.nextLine();
		System.out.println("Enter dob in (YYYY-MM-DD) format");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dob = sdf.parse(sc.nextLine());
		System.out.println("Enter Location:");
		location = sc.nextLine();
		System.out.println("Enter a password");
		password = sc.nextLine();
		
		Person person = new Person(name,dob,phoneNumber,bloodGroup,location,password);
		
		PersonDAOImpl p = new PersonDAOImpl();
		Integer r = p.createPerson(person);
		System.out.println(r+" row(s) affected.");
		sc.close();
	}
	public static void readByPhoneNumber() throws ClassNotFoundException, SQLException, ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Read by Phone Number");
		System.out.println("Enter phone number:");
		String phoneNumber = sc.nextLine();
		PersonDAOImpl p = new PersonDAOImpl();
		Person person = p.readPersonByPhoneNumber(phoneNumber);
		System.out.println(person);
		sc.close();
	}
	public static void readAll() throws ClassNotFoundException, SQLException, ParseException {
		Scanner sc = new Scanner(System.in);
		List<Person> personList=new ArrayList<>();
		System.out.println("Retrieving all persons from database:");
		personList = new PersonDAOImpl().readPerson();
		for(Person person: personList) {
			System.out.println(person);
		}
		System.out.println(personList.size()+" records fetched from DB.");
		sc.close();
	}
	public static void updatePerson() throws ClassNotFoundException, SQLException, ParseException {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Testing update person:");
		
		System.out.println("Enter person id to be updated:");
		Integer personId = Integer.parseInt(sc.nextLine());
		System.out.println("Enter new location:");
		String location = sc.nextLine();
		System.out.println("Enter new phone number:");
		String phoneNumber = sc.nextLine();
		
		System.out.println("Old information:");
		Person p1 = new PersonDAOImpl().readPersonById(personId);
		System.out.println(p1);
		
		System.out.println("New information");
		Integer r = new PersonDAOImpl().updatePerson(personId, phoneNumber, location);
		System.out.println(r+" rows(s) affected.");
		Person p2 = new PersonDAOImpl().readPersonById(personId);
		System.out.println(p2);
		
		sc.close();
	}
	public static void deleteById() throws ClassNotFoundException, SQLException, ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Deleting one person by ID:");
		System.out.println("Enter Person Id");
		Integer personId = Integer.parseInt(sc.nextLine());
		System.out.println("Old record:");
		Person p = new PersonDAOImpl().readPersonById(personId);
		System.out.println(p);
		
		System.out.println("Deleting...");
		int r = new PersonDAOImpl().deletePerson(personId);
		System.out.println(r+" row(s) affected.");
		
		System.out.println("Testing for successful deletion...");
		p = new PersonDAOImpl().readPersonById(personId);
		if(p == null)
			System.out.println("Successfull!");
		else
			System.out.println("Failed!");
		
		sc.close();
	}
	public static void deleteAll() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Emptying the table..");
		int r = new PersonDAOImpl().deletePerson();
		System.out.println(r+" rows(s) affected.");
		sc.close();
	}
	
	public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException {
		//insert();
		//readByPhoneNumber();
		//readAll();
		//updatePerson();
		//deleteById();
		//deleteAll();
	}
}
