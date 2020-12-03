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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.ArrayList;

import model.DonationRequest;
import model.Person;
import model.TestingHistory;
import model.TreatmentHistory;
import service.TestingHistoryClient;
import service.TreatmentHistoryClient;
import service.DonationClient;
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
		String phoneRegex = "[6789]{1}[0-9]{9}";
		Pattern numberp = Pattern.compile(phoneRegex);
		Matcher matcher;
		System.out.println("Enter your Phone Number (Provided at the time of Registration):");
		String phone;
		while(true){
			phone = sc.nextLine();
			matcher = numberp.matcher(phone);
			if(matcher.matches() == false) {
				System.out.println("Enter a valid Phone Number (10 digits, starts with with 6,7,8 or 9; Do not add +91):");	
			}
			else
				break;
		}
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
			System.out.println("*********************************************** Welcome to your Dashboard:"+personFetched.getName()+" ***********************************************");
		return personFetched;
	}

	public static void userTestingHistory(Person personFetched) throws IOException, ParseException {
		sc = new Scanner(System.in);
		ArrayList<String> resultList = new ArrayList<String>();
		resultList.add("positive");
		resultList.add("negative");
		String dateRegex = "([1-9]{1}[0-9]{3})-([0-1][0-9])-([0-3][0-9])";
		Pattern datep = Pattern.compile(dateRegex);
		Matcher matcher;
		
		String hospital = "",result="";
		int personId = personFetched.getPersonId();	

		List<TestingHistory> testList = testconsumer.readTestingHistoryByPersonId(personId);
		if(testList.size()!=0) {
			System.out.println("Previous Testing History and their Results:");
			System.out.format("%-5s %-10s %-10s %-10s\n","ID","HOSPITAL","DATE","RESULT");
			for(TestingHistory t1:testList) {
				System.out.format("%-5d %-10s %-10s %-10s\n",t1.getTestingId(),t1.getHospital(),sdf.format(t1.getTestingDate()),t1.getResult().toUpperCase());
			}
		}
		
		System.out.println("Enter Test details:");
		System.out.println("Enter the Hospital name where you conducted the Test:");
		hospital = sc.nextLine();
		System.out.println("Enter the Testing Date (Format: YYYY-MM-DD):");
		String date = "";
		while(true){
			date = sc.nextLine();
			matcher = datep.matcher(date);
			if(matcher.matches() == false) {
				System.out.println("Enter a valid Test Date (Format:YYYY-MM-DD):");	
			}
			else
				break;
		}
		Date testingDate=sdf.parse(date);
		System.out.println("Enter the Test Result:");
		result = "";
		while(true){
			result = sc.nextLine();
			if(resultList.contains(result.toLowerCase())==false) {
				System.out.println("Enter a valid Test Result (Positive|Negative):");	
			}
			else
				break;
		}
		
		TestingHistory test = new TestingHistory(personId, hospital, testingDate, result);

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
			System.out.println("Testing ID: "+conflictRecord.getTestingId()+" Testing Date: "+sdf.format(conflictRecord.getTestingDate())+"\n"
			+"Result: "+conflictRecord.getResult().toUpperCase());
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
					//if user was a donor, remove him
					DonationClient dc = new DonationClient();
					List<DonationRequest> dList1 = dc.readDonationRequest("approved");
					List<DonationRequest> dList2 = dList1.stream().filter((a)->a.getPersonId().equals(personId)).collect(Collectors.toList());
					for(DonationRequest d1:dList2) {
						//System.out.println(d1);
						dc.deleteDonationRequest(d1.getReqId());
					}
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
