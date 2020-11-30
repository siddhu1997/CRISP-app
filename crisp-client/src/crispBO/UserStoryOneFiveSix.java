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
import service.TestingHistoryClient;
import service.PersonClient;
import menuOptions.Menu;

public class UserStoryOneFiveSix{

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static TestingHistoryClient testconsumer = new TestingHistoryClient();
	public static PersonClient personClient = new PersonClient();

	public Boolean adminLoginValidation(String username,String password){
		if(username.equals("admin")) {
			if(password.equals("Ad@12345")) {
				return true;
			}
		}
		System.out.println("Invalid Administrator credentials! Try again!");
		return false;
	}
	
	public static Person userLoginValidation() throws IOException {

		Scanner sc = new Scanner(System.in);
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
			System.exit(0);
		}
		//Testing for invalid password
		else if(!pwd.equals(personFetched.getPassword())){
			System.out.println("Invalid login credentials. Please check your phone number or password and try again or Register for a new account.");
			System.exit(0);
		}

		System.out.println("\t\tWelcome to your dashboard "+personFetched.getName());
		sc.close();
		return personFetched;
	}

	public static void userTestingHistory(Person personFetched) throws IOException, ParseException {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		if(flag){
			
			//flag = true;
			//----------------Testing history menu
			while(flag) {

				//System.out.println("MENU--->1.Add Testing History.2. Exit..\n Enter the choice");
				Menu.testingMenu();		
				int choice2=sc.nextInt();
				switch(choice2) {

				case 1:

					/*
					String details=sc.nextLine();
					if(details.equals(""))
						details=sc.nextLine();
					String arr[]=details.split(",");
					 */

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
						if(res == 1)
							System.out.println("Your Testing Details have been added to our Database!\n");
						else
							System.out.println("Could not add your Details to our Database. Please try again later...");
					}	

					/*
					 * -----------This code doesn't check for conflict of dates with different results---- 
						if(testList.isEmpty()) {
							String res1=testconsumer.callDml("POST", "createTestingRecord" ,test);
							if(res1.contains("1")){
								System.out.println("insertion successfull");break;
							}
						}
						else {
							String res2=testconsumer.callDml("PUT", "addTestingRecord" ,test);
							if(res2.contains("1")){
								System.out.println("insertion successfull");break;
							}
						}
						System.out.println("insertion failed..!");
					 */
					break;
				case 2:
					System.out.println("Thank you for using this Application.");
					flag=false;
					break;
				default:
					break;
				}	
			}
			sc.close();
		}
	}
	
	
	public static void main(String[] args) throws IOException, ParseException {
		//System.out.println("Menu:1.Login\nEnter the choice");
		//int choice=sc.nextInt();
		//userTestingHistory(userLoginValidation());

	}
}
