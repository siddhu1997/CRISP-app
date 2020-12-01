/*
 * Developed by: Ajith M
 */

package crispBO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Person;
import model.TestingHistory;
import model.TreatmentHistory;
import service.TestingHistoryClient;
import service.TreatmentHistoryClient;
import service.PersonClient;

public class UserStoryOneFiveSix{

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static TestingHistoryClient testconsumer = new TestingHistoryClient();
	public static PersonClient personClient = new PersonClient();
	static Scanner sc = new Scanner(System.in);

	public static Boolean adminLoginValidation(String username,String password){
		if(username.equals("admin")) {
			if(password.equals("Ad@12345")) {
				return true;
			}
		}
		System.out.println("Invalid Administrator credentials! Try again!");
		return false;
	}

	public static Person userLoginValidation() throws IOException {

		sc = new Scanner(System.in);
		System.out.println("Enter your Phone Number (Provided at the time of Registration):");
		String phone=sc.nextLine();
		if(phone.equals(""))
			phone=sc.nextLine();
		System.out.println("Enter the password");
		String pwd=sc.nextLine();
		if(pwd.equals(""))
			pwd=sc.nextLine();

		Person person=new Person();
		person.setPhoneNumber(phone);
		person.setPassword(pwd);
		Person personFetched = personClient.readPersonByPhoneNumber(phone);

		//person=userclient.login_validation("PUT","validate", person);
		if(personFetched.getName() == null) {
			System.out.println("Invalid login credentials. Please check your phone number or password and try again or Register for a new account.");
		}
		//Testing for invalid password
		else if(!pwd.equals(personFetched.getPassword())){
			System.out.println("Invalid login credentials. Please check your phone number or password and try again or Register for a new account.");
			personFetched.setPersonId(null);
		}
		else
			System.out.println("\t\tWelcome to your dashboard "+personFetched.getName());
		return personFetched;
	}

	public static void userTestingHistory(Person personFetched) throws IOException, ParseException {
		sc = new Scanner(System.in);
		System.out.println("Enter Test details:");
		System.out.println("Enter the Hospital name where you conducted the Test:");
		String hospital = sc.nextLine();
		System.out.println("Enter the Testing Date (Format: YYYY-MM-DD):");
		Date testingDate=sdf.parse(sc.nextLine());
		System.out.println("Enter the Test Result:");
		String result = sc.nextLine();

		int personId = personFetched.getPersonId();	
		TestingHistory test = new TestingHistory(personId, hospital, testingDate, result);

		List<TestingHistory> testList = testconsumer.readTestingHistoryByPersonId(personId);

		//checking conflicts of results for the same date.
		Boolean conflict = false;
		TestingHistory conflictRecord = null;

		for(TestingHistory th: testList) {
			//check for result conflict for the same dates
			if(th.getTestingDate().equals(test.getTestingDate())) {
				conflict = true;
				conflictRecord = th;
				break;
			}
		}

		if(conflict) {
			System.out.println("Uh oh! The Test record you gave us has a conflict with a previous record. Please ensure to input valid data.\n");
			System.out.println("Previous Result:");
			System.out.println("Testing ID: "+conflictRecord.getTestingId()+" Testing Date: "+sdf.format(conflictRecord.getTestingDate()+"\n")
			+"Result: "+conflictRecord.getResult());
		}

		else {
			Integer res = testconsumer.createTestingHistory(test);
			if(res == 1) {
				System.out.println("Your Testing Details have been added to our Database!\n");
				//Update Treatment History based on Testing History
				TreatmentHistoryClient thc = new TreatmentHistoryClient();
				TreatmentHistory th = thc.readTreatmentHistoryByPersonId(test.getPersonId());
				//if no treatment history exists and result is positive
				if(th.getPersonId()==null && test.getResult().equalsIgnoreCase("positive")) {
					TreatmentHistory th2 = new TreatmentHistory();
					th2.setPersonId(test.getPersonId());
					th2.setAdmissionDate(test.getTestingDate());
					Integer r1 = thc.createTreatmentHistory(th2);
					if(r1 == 1) {
						System.out.println("Your Treatment History has also been updated.\n");
					}
					else
						System.out.println("Uh-oh! An unexpected error has occured while updating your treatment history.");
				}
				//if testing result is negative and recovered date is null update recovery date
				else if(th.getPersonId() != null && th.getRecoveredDate() == null && th.getDeathDate() == null && th.getAdmissionDate() != null) {
					TreatmentHistory th2  = new TreatmentHistory();
					th2.setRecoveredDate(test.getTestingDate());
					th2.setTreatmentDetails("Recovered. Updated by CRISP-app.");
					th2.setPersonId(test.getPersonId());
					Integer r2 = thc.updateRecoveredDate(th2);
					if(r2 == 1)
						System.out.println("Your Treatment History has also been updated.\n");
					else
						System.out.println("Uh-oh! An unexpected error has occured while updating your treatment history.");
				}
				//if recovered and infected again update admission date
				else if(th.getPersonId() != null && th.getDeathDate() == null && th.getRecoveredDate() != null && th.getAdmissionDate() != null && test.getResult().equalsIgnoreCase("positive")) {
					TreatmentHistory th2  = new TreatmentHistory();
					th2.setPersonId(test.getPersonId());
					th2.setAdmissionDate(test.getTestingDate());
					th2.setRecoveredDate(null);
					th2.setTreatmentDetails("Infected again. Updated by CRISP-app");
					Integer r3 = thc.updateAdmissionDate(th2);
					Integer r4 = thc.updateRecoveredDate(th2);
					if(r3 == 1 && r4 == 1)
						System.out.println("Your Treatment History has also been updated.\n");
					else
						System.out.println("Uh-oh! An unexpected error has occured while updating your treatment history.");
				}
			}				
			else
				System.out.println("Could not add your Details to our Database. Please try again later...");
			}
	}


	public static void main(String[] args) throws IOException, ParseException {
		//System.out.println("Menu:1.Login\nEnter the choice");
		//int choice=sc.nextInt();
		//userTestingHistory(userLoginValidation());

	}
}
