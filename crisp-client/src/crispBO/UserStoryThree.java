/*
 * Code developed by: Sharath
 */
package crispBO;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import menuOptions.Menu;
import model.TreatmentHistory;
import service.TreatmentHistoryClient;

public class UserStoryThree {

	static Scanner scanner = new Scanner(System.in);
	public static void updateTreatementHistory() throws ClassNotFoundException, SQLException, ParseException, IOException {
		
		int choice = 4;
		
		TreatmentHistoryClient t = new TreatmentHistoryClient();
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");

		do {
			
			Menu.updateTreatmentHistoryMenu();
			choice = scanner.nextInt();
			
			switch (choice) {
			case 1: 
			{
				System.out.println("Enter Person ID:");
				int personId = scanner.nextInt();
				System.out.println("Enter Admission Date (format: YYYY-MM-DD):");
				String date = scanner.nextLine();
				if (date.equals(""))
					date = scanner.nextLine();
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
			
			case 2:
			{
				System.out.println("Enter Person ID:");
				int personId = scanner.nextInt();
				System.out.println("Enter Recovered Date (format: YYYY-MM-DD)");
				String date = scanner.nextLine();
				if (date.equals(""))
					date = scanner.nextLine();
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

			case 3:
			{
				scanner = new Scanner(System.in);
				System.out.println("Enter Person ID:");
				Integer personId = Integer.parseInt(scanner.nextLine());
				System.out.println("Enter Death Date (format: YYYY-MM-DD)");
				String date = scanner.nextLine();
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
				} else {
					System.out.println("Failed to save changes. Please try again later!");
				}
				
				break;
			}
			
			default:
			}

		} while (choice != 4);
		//scanner.close();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, IOException {
		//updateTreatementHistory();
	}

}
