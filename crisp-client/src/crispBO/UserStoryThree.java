/*
 * Code developed by: Sharath
 */
package crispBO;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import menuOptions.Menu;
import model.DonationRequest;
import model.Person;
import model.TreatmentHistory;
import service.DonationClient;
import service.PersonClient;
import service.TreatmentHistoryClient;

public class UserStoryThree {

	static Scanner scanner = new Scanner(System.in);
	public static void updateTreatementHistory() throws ClassNotFoundException, SQLException, ParseException, IOException {
		
		String choice = "4";
		
		TreatmentHistoryClient t = new TreatmentHistoryClient();
		PersonClient pc = new PersonClient();
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		DonationClient dc = new DonationClient();
		
		String dateRegex = "([1-9]{1}[0-9]{3})-([0-1][0-9])-([0-3][0-9])";
		String personRegex = "([1-9]|[0-9]{2,})";
		Pattern datep = Pattern.compile(dateRegex);
		Pattern personp = Pattern.compile(personRegex);
		Matcher matcher;
		Person personFetched = null;
		
		do {
			
			Menu.updateTreatmentHistoryMenu();
			choice = scanner.nextLine();	
			switch (choice) {
			case "1": 
			{
				System.out.println("Enter Person ID:");
				String pid = "";
				//int personId = scanner.nextInt();
				while(true){
					pid = scanner.nextLine();
					matcher = personp.matcher(pid);
					if(matcher.matches() == false) {
						System.out.println("Enter a valid Person ID:");	
					}
					else
						break;
				}
				Integer personId = Integer.parseInt(pid);
				personFetched = pc.readPersonById(personId);
				if(personFetched.getPersonId() == null) {
					System.out.println("No such person exist in Database.");
					break;
				}
				else {
					System.out.println("Do you want to modify "+personFetched.getName()+"'s record? (y/n)");
					String confirm = scanner.nextLine();
					if(confirm.equalsIgnoreCase("n"))
						break;
				}
				System.out.println("Enter Admission Date (format: YYYY-MM-DD):");
				String date = "";
				while(true){
					date = scanner.nextLine();
					matcher = datep.matcher(date);
					if(matcher.matches() == false) {
						System.out.println("Enter valid Date (format: YYYY-MM-DD):");	
					}
					else
						break;
				}
				
				System.out.println("Enter Treatment Details:");
				String details = scanner.nextLine();
				if (details.equals(""))
					details = scanner.nextLine();
				
				TreatmentHistory th = new TreatmentHistory();
		
				th.setAdmissionDate(dFormat.parse(date));
				th.setPersonId(personId);
				th.setTreatmentDetails(details);
				
				int result = t.updateAdmissionDate(th);
				
				if (result == 1) {
					System.out.println("Changes have been saved!");
				} else {
					System.out.println("Failed to save changes. Please try again later!");
				}
				break;
			}
			
			case "2":
			{
				System.out.println("Enter Person ID:");
				String pid = "";
				//int personId = scanner.nextInt();
				while(true){
					pid = scanner.nextLine();
					matcher = personp.matcher(pid);
					if(matcher.matches() == false) {
						System.out.println("Enter a valid Person ID:");	
					}
					else
						break;
				}
				Integer personId = Integer.parseInt(pid);
				personFetched = pc.readPersonById(personId);
				if(personFetched.getPersonId() == null) {
					System.out.println("No such person exist in Database.");
					break;
				}
				else {
					System.out.println("Do you want to modify "+personFetched.getName()+"'s record? (y/n)");
					String confirm = scanner.nextLine();
					if(confirm.equalsIgnoreCase("n"))
						break;
				}
				System.out.println("Enter Recovered Date (format: YYYY-MM-DD)");
				String date = "";
				while(true){
					date = scanner.nextLine();
					matcher = datep.matcher(date);
					if(matcher.matches() == false) {
						System.out.println("Enter a valid Date (format: YYYY-MM-DD:");	
					}
					else
						break;
				}
				System.out.println("Enter Treatment Details:");
				String details = scanner.nextLine();
				if (details.equals(""))
					details = scanner.nextLine();
				
				TreatmentHistory th = new TreatmentHistory();
				
				th.setRecoveredDate(dFormat.parse(date));
				th.setPersonId(personId);
				th.setTreatmentDetails(details);
				
				int result = t.updateRecoveredDate(th);
				
				if (result == 1) {
					System.out.println("Changes have been saved!");
				} else {
					System.out.println("Failed to save changes. Please try again later!");
				}
				break;
			}

			case "3":
			{
				scanner = new Scanner(System.in);
				System.out.println("Enter Person ID:");
				String pid = "";
				//int personId = scanner.nextInt();
				while(true){
					pid = scanner.nextLine();
					matcher = personp.matcher(pid);
					if(matcher.matches() == false) {
						System.out.println("Enter a valid Person ID:");	
					}
					else
						break;
				}
				Integer personId = Integer.parseInt(pid);
				personFetched = pc.readPersonById(personId);
				if(personFetched.getPersonId() == null) {
					System.out.println("No such person exist in Database.");
					break;
				}
				else {
					System.out.println("Do you want to modify "+personFetched.getName()+"'s record? (y/n)");
					String confirm = scanner.nextLine();
					if(confirm.equalsIgnoreCase("n"))
						break;
				}
				System.out.println("Enter Death Date (format: YYYY-MM-DD)");
				String date = "";
				while(true){
					date = scanner.nextLine();
					matcher = datep.matcher(date);
					if(matcher.matches() == false) {
						System.out.println("Enter a valid Date (format: YYYY-MM-DD):");	
					}
					else
						break;
				}
				System.out.println("Did patient die from COVID-19 (y/n)?");
				String causeOfDeath = scanner.nextLine();
				String details;
				if(causeOfDeath.equalsIgnoreCase("y"))
					details = "Died from COVID-19";
				
				else {
					System.out.println("Enter Treatment Details:");
					details = scanner.nextLine();
				}
						
				TreatmentHistory th = new TreatmentHistory();
				
				th.setDeathDate(dFormat.parse(date));
				th.setPersonId(personId);
				th.setTreatmentDetails(details);
				
				int result = t.updateDeathDate(th);
				
				if (result == 1) {
					System.out.println("Changes have been saved!");
					//delete all approved requests of that person.
					List<DonationRequest> dList1 = dc.readDonationRequest("approved");
					List<DonationRequest> dList2 = dList1.stream().filter((a)->a.getPersonId().equals(personId)).collect(Collectors.toList());
					for(DonationRequest d1:dList2) {
						//System.out.println(d1);
						dc.deleteDonationRequest(d1.getReqId());
					}
				} else {
					System.out.println("Failed to save changes. Please try again later!");
				}
				
				break;
			}
			
			default:
			}

		} while (choice.equals("4")==false);
		//scanner.close();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, IOException {
		updateTreatementHistory();
	}

}
