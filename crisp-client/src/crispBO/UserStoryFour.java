/*
 * Code developed by: Keerthi
 */

package crispBO;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.Person;
import service.PersonClient;

public class UserStoryFour {
	static PersonClient p = new PersonClient();

	public static void userRegistration() throws ParseException, IOException {
		
		String name;
		Date dob;
		String phoneNumber;
		String bloodGroup;
		String location;
		String password;
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the following details:");
		System.out.println("Enter your name:");
		name = sc.nextLine();
		System.out.println("Enter your phone number:");
		phoneNumber = sc.nextLine();
		System.out.println("Enter your Blood group:");
		bloodGroup = sc.nextLine();
		System.out.println("Enter your Date Of Birth(yyyy-MM-dd format):");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dob = sdf.parse(sc.nextLine());
		System.out.println("Enter your Location:");
		location = sc.nextLine();
		System.out.println("Enter your Password");
		password = sc.nextLine();
		
		Person newPerson = new Person(name,dob,phoneNumber,bloodGroup,location,password);
		Person person = null;
		
		System.out.println("Searching for existing user....");
		person = p.readPersonByPhoneNumber(phoneNumber);
		//System.out.println("T:"+person1);
		
		Integer r = 0;
		if(person.getPersonId() == null) {
			System.out.println("No user found. Creating account with given credentials....");
			r = p.createPerson(newPerson);
			}
		
		if(r != 0)
			System.out.println("Your account has been created. Please login with your new account.");
		else
			System.out.println("Uh ho! We encountered an error while creating your account. Please try again later.");
		sc.close();
	}

	public static void main(String args[]) throws ClassNotFoundException, SQLException, ParseException
	{
		//userRegistration();
	}

}
