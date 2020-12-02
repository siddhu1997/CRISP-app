/*
 * Code developed by: Keerthi
 */

package crispBO;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import model.Person;
import service.PersonClient;

public class UserStoryFour {
	
	static PersonClient p = new PersonClient();
	static Scanner sc=new Scanner(System.in);
	
	public static void userRegistration() throws ParseException, IOException {
		
		String name = "";
		Date dob =  null;
		String phoneNumber = "";
		String bloodGroup = "";
		String location = "";
		String password = "";
		
		String dateRegex = "([1-9]{1}[0-9]{3})-([0-1][0-9])-([0-3][0-9])";
		String phoneRegex = "[6789]{1}[0-9]{9}";
		String bloodRegex = "((a\\+ve)|(a-ve)|(b\\+ve)|(b-ve)|(o\\+ve)|(o-ve)|(ab\\+ve)|(ab-ve))";
		
		Pattern datep = Pattern.compile(dateRegex);
		Pattern numberp = Pattern.compile(phoneRegex);
		Pattern bloodp = Pattern.compile(bloodRegex);
		
		Matcher matcher;
		System.out.println("Enter the following details:");
		System.out.println("Enter your name:");
		while(true){
			name = sc.nextLine();
			if(name.equals("")) {
				System.out.println("Enter a valid Name:");	
			}
			else
				break;
		}
		System.out.println("Enter your phone number (10 digits, starts with with 6,7,8 or 9; Do not add +91):");
		while(true){
			phoneNumber = sc.nextLine();
			matcher = numberp.matcher(phoneNumber);
			if(matcher.matches() == false) {
				System.out.println("Enter a valid Phone Number (10 digits, starts with with 6,7,8 or 9; Do not add +91):");	
			}
			else
				break;
		}
		System.out.println("Enter your Blood group:");	
		while(true){
			bloodGroup = sc.nextLine();
			matcher = bloodp.matcher(bloodGroup.toLowerCase());
			if(bloodGroup.equals("") || matcher.matches() == false) {
				System.out.println("Enter a valid Blood Group:");	
			}
			else
				break;
		}		
		System.out.println("Enter your Date Of Birth(yyyy-MM-dd format):");
		String date = "";
		while(true){
			date = sc.nextLine();
			matcher = datep.matcher(date);
			if(matcher.matches() == false) {
				System.out.println("Enter a valid Date of Birth (Format:YYYY-MM-DD):");	
			}
			else
				break;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dob = sdf.parse(date);
		System.out.println("Enter your Location (Bangalore|Chennai|Delhi|Kochi):");
		ArrayList<String> validLocations = new ArrayList<>();
		validLocations.add("bangalore");
		validLocations.add("chennai");
		validLocations.add("delhi");
		validLocations.add("kochi");	
		while(true){
			location = sc.nextLine();
			if(location.equals("") == true || validLocations.contains(location.toLowerCase()) == false) {
				System.out.println("Enter a valid Location (Bangalore|Chennai|Delhi|Kochi):");	
			}
			else
				break;
		}

		System.out.println("Enter your Password (Min. 8 Characters long):");
		while(true){
			password = sc.nextLine();
			if(password.length() < 8 ) {
				System.out.println("Enter a password that is atleast 8 characters long:");	
			}
			else
				break;
		}
		
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
			System.out.println("Exisiting User found! Please login...");
	}

	public static void main(String args[]) throws ClassNotFoundException, SQLException, ParseException, IOException
	{
		userRegistration();
	}

}
