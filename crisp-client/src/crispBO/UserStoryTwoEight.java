/*
 * Code developed by: Siddharth. S
 */
package crispBO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import menuOptions.Menu;
import model.DonationRequest;
import model.Person;
import model.TreatmentHistory;
import service.DonationClient;
import service.PersonClient;
import service.TreatmentHistoryClient;

public class UserStoryTwoEight {

	static Scanner sc;
	static PersonClient pc = new PersonClient();
	static TreatmentHistoryClient thc = new TreatmentHistoryClient();
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	
	public static void adminManage() throws IOException, ParseException {
		
		Integer option = 6;
		do {
			
			Menu.adminOptionsMenu();
			sc = new Scanner(System.in);
			option = Integer.parseInt(sc.nextLine());
			switch(option) {
			case 1:
				//allowing admin to see all the pending request first.
				System.out.println("Fetching pending requests....");
				DonationClient dc = new DonationClient();
				
				List<DonationRequest> donationList = dc.readDonationRequest("pending");
				
				String accept;
				String comment;
				DonationRequest dn = null;
				
				System.out.format("%-3s %-20s %-10s %-100s\n","ID","Name","Location","Donor Remarks");
				
				for(DonationRequest d:donationList) {
					
					//request id,Person Name,Person Location,Donor Remarks
					Person temp = pc.readPersonById(d.getPersonId());
					System.out.format("%-3d %-20s %-10s %-100s\n",d.getReqId(),temp.getName(),temp.getLocation(),d.getDonorRemarks());
					dn = new DonationRequest();
					System.out.println("Approve (y = approve; n = reject; p = keep as pending)?");
					accept = sc.nextLine();
					
					if(accept.equalsIgnoreCase("y"))
						dn.setStatus("accepted");
					else if(accept.equalsIgnoreCase("n"))
						dn.setStatus("rejected");
					else if(accept.equalsIgnoreCase("p"))
						dn.setStatus("pending");
					else {
						System.out.println("Couldn't understand. Keeping status as pending");
					}
					
					System.out.println("Enter Admin remarks:");
					comment = sc.nextLine();
										
					dn.setPersonId(temp.getPersonId());
					dn.setReqId(d.getReqId());
					dn.setAdminRemarks(comment);
					Integer r = dc.updateStatus(dn);
					if(r == 1)
						System.out.println("Record updated for Request ID: "+d.getReqId());
					else
						System.out.println("Uh-Oh! An unexpected error occured! Try again later.");
					
				}	
				break;
			default:
				statistics(option);

			}	
		
		}while(option != 6);
	}
	
	public static void userStatistics() throws IOException, ParseException {
		sc = new Scanner(System.in);
		Integer choice;
		do {
			Menu.userStatisticsMenu();
			choice = Integer.parseInt(sc.nextLine());
			switch(choice) {
			case 1:
			{
				System.out.println("Getting count of all the Lists....");
				List<TreatmentHistory> thList1 = thc.readTreatmentHistory();
				List<TreatmentHistory> patientList = thList1.stream().filter((a)->a.getRecoveredDate()==null&&a.getDeathDate()==null).collect(Collectors.toList());
				List<TreatmentHistory> recoveredList = thList1.stream().filter((a)->a.getDeathDate()==null).collect(Collectors.toList());
				List<TreatmentHistory> deathList = thList1.stream().filter((a)->a.getTreatmentDetails().equalsIgnoreCase("Died from COVID-19")).collect(Collectors.toList());
				System.out.println("No of Infected persons till date: "+patientList.size());
				System.out.println("No of Recovered people till date: "+recoveredList.size());
				System.out.println("No of people who died from COVID-19 till date: "+deathList.size()+"\n");
				break;
			}
			case 2:
				statistics(2);
				break;
			case 3:
				statistics(3);
				break;
			case 4:
				statistics(4);
				break;
			}
		}while(choice != 5);
	}
	
	public static void statistics(Integer choice) throws IOException, ParseException {
		sc = new Scanner(System.in);
		switch(choice) {
		case 2:
		{
			System.out.println("Patient Statistics:");
			String boolLoc="n",location="";
			String boolDate="n",date="";
			Date d = new Date();
			
			System.out.println("Do you want to filter by Location?(y/n)");
			boolLoc = sc.nextLine();
			if(boolLoc.equalsIgnoreCase("y")) {
				System.out.println("Enter Location:");
				location = sc.nextLine();
			}
			System.out.println("Do you want to filter by Date?(y/n)");
			boolDate = sc.nextLine();
			if(boolDate.equalsIgnoreCase("y")) {
				System.out.println("Enter Date:");
				date = sc.nextLine();
				 d = sdf.parse(date);
			}
			
			System.out.println("Fetching data...");
			List<TreatmentHistory> thList = thc.readTreatmentHistory();
			List<Person> pList = pc.readPerson();
			Integer count = 0;
			//both
			if(boolLoc.equals("y") && boolDate.equals("y")){
				for(TreatmentHistory th:thList) {
					for(Person person:pList) {
						if(person.getPersonId() == th.getPersonId() && person.getLocation().equals(location) && th.getAdmissionDate().equals(d) && th.getRecoveredDate() == null && th.getDeathDate() == null) {
							count++;
							//id,name,location,date
							System.out.format("%-3d %-20s %-10s %-10s\n",person.getPersonId(),person.getName(),person.getLocation(),sdf.format(th.getAdmissionDate()));
							break;
						}
					}
				}
				
			}
			//location only
			else if(boolLoc.equals("y") && boolDate.equals("n")) {
				for(TreatmentHistory th:thList) {
					for(Person person:pList) {
						if(person.getPersonId() == th.getPersonId() && person.getLocation().equals(location) && th.getRecoveredDate() == null && th.getDeathDate() == null) {
							count++;
							//id,name,location,date
							System.out.format("%-3d %-20s %-10s %-10s\n",person.getPersonId(),person.getName(),person.getLocation(),sdf.format(th.getAdmissionDate()));
							break;
						}
					}
				}
			}
			//date only
			else if(boolLoc.equals("n") && boolDate.equals("y")){
				for(TreatmentHistory th:thList) {
					for(Person person:pList) {
						if(person.getPersonId() == th.getPersonId() && th.getAdmissionDate().equals(d) && th.getRecoveredDate() == null && th.getDeathDate() == null) {
							count++;
							//id,name,location,date
							System.out.format("%-3d %-20s %-10s %-10s\n",person.getPersonId(),person.getName(),person.getLocation(),sdf.format(th.getAdmissionDate()));
							break;
						}
					}
				}
			}
			//un-filtered
			else {
				for(TreatmentHistory th:thList) {
					for(Person person:pList) {
						if(person.getPersonId() == th.getPersonId() && th.getRecoveredDate() == null && th.getDeathDate() == null) {
							count++;
							//id,name,location,date
							System.out.format("%-3d %-20s %-10s %-10s\n",person.getPersonId(),person.getName(),person.getLocation(),sdf.format(th.getAdmissionDate()));
							break;
						}
					}
				}
			}
			System.out.println("Total Count: "+count+"\n");
			break;
		}		
		case 3:{
			System.out.println("Recovery Statistics:");
			String boolLoc="n",location="";
			String boolDate="n",date="";
			Date d = new Date();
			
			System.out.println("Do you want to filter by Location?(y/n)");
			boolLoc = sc.nextLine();
			if(boolLoc.equalsIgnoreCase("y")) {
				System.out.println("Enter Location:");
				location = sc.nextLine();
			}
			System.out.println("Do you want to filter by Date?(y/n)");
			boolDate = sc.nextLine();
			if(boolDate.equalsIgnoreCase("y")) {
				System.out.println("Enter Date:");
				date = sc.nextLine();
				 d = sdf.parse(date);
			}
			
			System.out.println("Fetching data...");
			List<TreatmentHistory> thList = thc.readTreatmentHistory();
			List<Person> pList = pc.readPerson();
			Integer count = 0;
			//both
			if(boolLoc.equals("y") && boolDate.equals("y")){
				for(TreatmentHistory th:thList) {
					for(Person person:pList) {
						if(person.getPersonId() == th.getPersonId() && person.getLocation().equals(location) && th.getRecoveredDate().equals(d) && th.getDeathDate() == null) {
							count++;
							//id,name,location,date
							System.out.format("%-3d %-20s %-10s %-10s\n",person.getPersonId(),person.getName(),person.getLocation(),sdf.format(th.getRecoveredDate()));
							break;
						}
					}
				}
				
			}
			//location only
			else if(boolLoc.equals("y") && boolDate.equals("n")) {
				for(TreatmentHistory th:thList) {
					for(Person person:pList) {
						if(person.getPersonId() == th.getPersonId() && person.getLocation().equals(location) && th.getDeathDate() == null) {
							count++;
							//id,name,location,date
							System.out.format("%-3d %-20s %-10s %-10s\n",person.getPersonId(),person.getName(),person.getLocation(),sdf.format(th.getRecoveredDate()));
							break;
						}
					}
				}
			}
			//date only
			else if(boolLoc.equals("n") && boolDate.equals("y")){
				for(TreatmentHistory th:thList) {
					for(Person person:pList) {
						if(person.getPersonId() == th.getPersonId() && th.getRecoveredDate().equals(d)  && th.getDeathDate() == null) {
							count++;
							//id,name,location,date
							System.out.format("%-3d %-20s %-10s %-10s\n",person.getPersonId(),person.getName(),person.getLocation(),sdf.format(th.getRecoveredDate()));
							break;
						}
					}
				}
			}
			//un-filtered
			else {
				for(TreatmentHistory th:thList) {
					for(Person person:pList) {
						if(person.getPersonId() == th.getPersonId() && th.getRecoveredDate() != null && th.getDeathDate() == null) {
							count++;
							//id,name,location,date
							System.out.format("%-3d %-20s %-10s %-10s\n",person.getPersonId(),person.getName(),person.getLocation(),sdf.format(th.getRecoveredDate()));
							break;
						}
					}
				}
			}
			System.out.println("Total Count: "+count+"\n");
			break;
		}
		case 4:
		{
			System.out.println("Death Statistics:");
			String boolLoc="n",location="";
			String boolDate="n",date="";
			Date d = new Date();
			
			System.out.println("Do you want to filter by Location?(y/n)");
			boolLoc = sc.nextLine();
			if(boolLoc.equalsIgnoreCase("y")) {
				System.out.println("Enter Location:");
				location = sc.nextLine();
			}
			System.out.println("Do you want to filter by Date?(y/n)");
			boolDate = sc.nextLine();
			if(boolDate.equalsIgnoreCase("y")) {
				System.out.println("Enter Date:");
				date = sc.nextLine();
				 d = sdf.parse(date);
			}
			
			System.out.println("Fetching data...");
			List<TreatmentHistory> thList1 = thc.readTreatmentHistory();
			//Applying Stream to reduce the list.
			List<TreatmentHistory> thList = thList1.stream().filter((a)->a.getTreatmentDetails().equalsIgnoreCase("Died from COVID-19")).collect(Collectors.toList());
			List<Person> pList = pc.readPerson();
			Integer count = 0;
			//both
			if(boolLoc.equals("y") && boolDate.equals("y")){
				for(TreatmentHistory th1:thList) {
					for(Person person1: pList) {
						if(person1.getPersonId() == th1.getPersonId() && person1.getLocation().equals(location) && th1.getDeathDate().equals(d)) {
							count++;
							//id,name,location,date
							System.out.format("%-3d %-20s %-10s %-10s\n",person1.getPersonId(),person1.getName(),person1.getLocation(),sdf.format(th1.getDeathDate()));
							break;
						}
					}
				}
				
			}
			//location only
			else if(boolLoc.equals("y") && boolDate.equals("n")) {
				for(TreatmentHistory th1:thList) {
					for(Person person1:pList) {
						if(person1.getPersonId() == th1.getPersonId() && person1.getLocation().equals(location)){
							count++;
							//id,name,location,date
							System.out.format("%-3d %-20s %-10s %-10s\n",person1.getPersonId(),person1.getName(),person1.getLocation(),sdf.format(th1.getDeathDate()));
							break;
						}
					}
				}
			}
			//date only
			else if(boolLoc.equals("n") && boolDate.equals("y")){
				for(TreatmentHistory th1:thList) {
					for(Person person1:pList) {
						if(person1.getPersonId() == th1.getPersonId() && th1.getDeathDate().equals(d)) {
							count++;
							//id,name,location,date
							System.out.format("%-3d %-20s %-10s %-10s\n",person1.getPersonId(),person1.getName(),person1.getLocation(),sdf.format(th1.getDeathDate()));
							break;
						}
					}
				}
			}
			//un-filtered
			else {
				for(TreatmentHistory th1:thList) {
					for(Person person1:pList) {
						if(person1.getPersonId() == th1.getPersonId()) {
							count++;
							//id,name,location,date
							System.out.format("%-3d %-20s %-10s %-10s\n",person1.getPersonId(),person1.getName(),person1.getLocation(),sdf.format(th1.getDeathDate()));
							break;
						}
					}
				}
			}
			System.out.println("Total Count: "+count+"\n");
			break;
		}
			
		case 5:
		{
			System.out.println("Donor Statistics:");
			System.out.format("%-5s %-20s %-20s\n","P.ID","Name","Location");
			DonationClient dc1 = new DonationClient();
			List<DonationRequest> list = dc1.readDonationRequest("approved");
			List<Person> pList = pc.readPerson();
			for(DonationRequest r:list) {
				for(Person p:pList) {
					if(p.getPersonId().equals(r.getPersonId())) {
						System.out.format("%-5d %-20s %-20s\n",r.getPersonId(),p.getName(),p.getLocation());
						break;
					}
				}
				
			}
			System.out.println("Total Count: "+list.size()+"\n");
			break;
		}		
		case 6:
		default:
			break;
		}
	}
	
	public static void main(String[] args) throws IOException, ParseException {
		userStatistics();
	}

}
