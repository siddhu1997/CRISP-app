package testing;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import model.Person;
import service.PersonClient;

public class MainPerson {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void readAll() throws IOException {
		PersonClient pc = new PersonClient();
		List<Person> personList = pc.readPerson();
		for(Person p:personList) {
			System.out.println(p);
		}
	}
	public static void readId() throws IOException {
		Scanner sc = new Scanner(System.in);
		PersonClient pc = new PersonClient();
		System.out.println("Enter ID:");
		Integer personId = Integer.parseInt(sc.nextLine());
		Person person = pc.readPersonById(personId);
		System.out.println(person);
		sc.close();
	}
	public static void readPhone() throws IOException {
		Scanner sc = new Scanner(System.in);
		PersonClient pc = new PersonClient();
		System.out.println("Enter Phone Number:");
		String phoneNumber = sc.nextLine();
		Person person = pc.readPersonByPhoneNumber(phoneNumber);
		System.out.println(person);
		sc.close();
	}
	public static void create() throws ParseException, IOException {
		Scanner sc = new Scanner(System.in);
		PersonClient pc = new PersonClient();
		//Change Parameters 
		Person person = new Person("Dhamu",sdf.parse("1997-12-26"),"9526630352","A-ve","Ernakulam","root");
		Integer output = pc.createPerson(person);
		System.out.println(output);
		sc.close();
	}
	public static void update() throws IOException {
		Scanner sc = new Scanner(System.in);
		PersonClient pc = new PersonClient();
		//Change Parameters
		Person person = new Person(16,"8907245713","Noida");
		Integer output = pc.updatePerson(person);
		System.out.println(output);
		sc.close();
	}
	public static void delete() throws IOException{
		Scanner sc = new Scanner(System.in);
		PersonClient pc = new PersonClient();
		//Change id
		Integer personId = 17;
		Integer output = pc.deletePerson(personId);
		System.out.println(output);
		sc.close();
	}
	public static void main(String[] args) throws IOException, ParseException {
		//readAll();
		//readId();
		//readPhone();
		//create();
		//update();
		//delete();		
	}
}
