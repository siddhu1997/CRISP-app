/*
 * Code developed by: Siddharth. S
 */
package crispBO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
				
				if(donationList.size()==0)
					System.out.println("No pending Donations...");
				else
					System.out.format("%-3s %-20s %-10s %-100s\n","ID","Name","Location","Donor Remarks");
				
				for(DonationRequest d:donationList) {
					
					//request id,Person Name,Person Location,Donor Remarks
					Person temp = pc.readPersonById(d.getPersonId());
					System.out.format("%-3d %-20s %-10s %-100s\n",d.getReqId(),temp.getName(),temp.getLocation(),d.getDonorRemarks());
					dn = new DonationRequest();
					System.out.println("Approve (y = approve; n = reject; p = keep as pending)?");
					accept = sc.nextLine();
					
					if(accept.equalsIgnoreCase("y"))
						dn.setStatus("approved");
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
				System.out.println("Getting count of all the Lists....\n");
				List<TreatmentHistory> thList1 = thc.readTreatmentHistory();
				List<TreatmentHistory> patientList = thList1.stream().filter((a)->a.getRecoveredDate()==null&&a.getDeathDate()==null).collect(Collectors.toList());
				List<TreatmentHistory> recoveredList = thList1.stream().filter((a)->a.getDeathDate()==null&&a.getRecoveredDate()!=null).collect(Collectors.toList());
				List<TreatmentHistory> deathList = thList1.stream().filter((a)->a.getTreatmentDetails().equalsIgnoreCase("Died from COVID-19")).collect(Collectors.toList());
				System.out.println("No of Infected persons till date: "+patientList.size());
				System.out.println("No of Recovered people till date: "+recoveredList.size());
				System.out.println("No of people who died from COVID-19 till date: "+deathList.size()+"\n");
				break;
			}
			default:
				statistics(choice);
				break;
			}
		}while(choice != 6);
	}
	
	public static void statistics(Integer choice) throws IOException, ParseException {
		
		ArrayList<String> validLocations = new ArrayList<>();
		validLocations.add("bangalore");
		validLocations.add("chennai");
		validLocations.add("delhi");
		validLocations.add("kochi");
		String dateRegex = "([1-9]{1}[0-9]{3})-([0-1][0-9])-([0-3][0-9])";
		Pattern datep = Pattern.compile(dateRegex);
		Matcher matcher;
		sc = new Scanner(System.in);
		switch(choice) {
		case 2:
		{
			System.out.println("Patient Statistics:");
			String boolLoc="n",location="";
			String boolDate="n",date="";
			Date d = new Date();
			
			System.out.println("Do you want to filter by Location?(y/n) You will prompted to enter the Location in the next Step.");
			boolLoc = sc.nextLine();
			if(boolLoc.equalsIgnoreCase("y")) {
				System.out.println("Enter Location:");
				while(true){
					location = sc.nextLine();
					if(validLocations.contains(location.toLowerCase()) == false) {
						System.out.println("Enter a valid Location (Bangalore|Chennai|Delhi|Kochi):");	
					}
					else
						break;
				}
			}
			System.out.println("Do you want to filter by Date?(y/n) You will be prompted to enter the Date in the next Step.");
			boolDate = sc.nextLine();
			if(boolDate.equalsIgnoreCase("y")) {
				System.out.println("Enter Date:");
				while(true){
					date = sc.nextLine();
					matcher = datep.matcher(date);
					if(matcher.matches() == false) {
						System.out.println("Enter a valid Date (Format:YYYY-MM-DD):");	
					}
					else
						break;
				}
				d = sdf.parse(date);
			}
			
			System.out.println("Fetching data...");
			List<TreatmentHistory> thList = thc.readTreatmentHistory();
			List<Person> pList = pc.readPerson();
			Integer count = 0;
			//both
			if(boolLoc.equals("y") && boolDate.equals("y")){
				System.out.println("Location Filter: Active (Location:"+location.toUpperCase()+")\tDate Filter: Active (Date: "+date+")");
				System.out.format("\n%-3s %-20s %-10s %-10s\n","ID","Name","Location","Date");
				for(TreatmentHistory th:thList) {
					for(Person person:pList) {
						if(person.getPersonId() == th.getPersonId() && person.getLocation().equalsIgnoreCase(location) && th.getAdmissionDate().equals(d) && th.getRecoveredDate() == null && th.getDeathDate() == null) {
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
				System.out.println("Location Filter: Active (Location:"+location.toUpperCase()+")\tDate Filter: Inactive");
				System.out.format("\n%-3s %-20s %-10s %-10s\n","ID","Name","Location","Date");
				for(TreatmentHistory th:thList) {
					for(Person person:pList) {
						if(person.getPersonId() == th.getPersonId() && person.getLocation().equalsIgnoreCase(location) && th.getRecoveredDate() == null && th.getDeathDate() == null) {
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
				System.out.println("Location Filter: Inactive\tDate Filter: Active (Date: "+date+")");
				System.out.format("\n%-3s %-20s %-10s %-10s\n","ID","Name","Location","Date");
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
				System.out.println("Location Filter: Inactive\tDate Filter: Inactive");
				System.out.format("\n%-3s %-20s %-10s %-10s\n","ID","Name","Location","Date");
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
			
			System.out.println("Do you want to filter by Location?(y/n) You will prompted to enter the Location in the next Step.");
			boolLoc = sc.nextLine();
			if(boolLoc.equalsIgnoreCase("y")) {
				System.out.println("Enter Location:");
				while(true){
					location = sc.nextLine();
					if(validLocations.contains(location.toLowerCase()) == false) {
						System.out.println("Enter a valid Location (Bangalore|Chennai|Delhi|Kochi):");	
					}
					else
						break;
				}
			}
			System.out.println("Do you want to filter by Date?(y/n) You will prompted to enter a date in the next step.");
			boolDate = sc.nextLine();
			if(boolDate.equalsIgnoreCase("y")) {
				System.out.println("Enter Date:");
				while(true){
					date = sc.nextLine();
					matcher = datep.matcher(date);
					if(matcher.matches() == false) {
						System.out.println("Enter a valid Date (Format:YYYY-MM-DD):");	
					}
					else
						break;
				}
				d = sdf.parse(date);
			}
			
			System.out.println("Fetching data...");
			List<TreatmentHistory> thList = thc.readTreatmentHistory();
			List<Person> pList = pc.readPerson();
			Integer count = 0;
			//both
			if(boolLoc.equals("y") && boolDate.equals("y")){
				System.out.println("Location Filter: Active (Location:"+location.toUpperCase()+")\tDate Filter: Active (Date: "+date+")");
				System.out.format("\n%-3s %-20s %-10s %-10s\n","ID","Name","Location","Date");
				for(TreatmentHistory th:thList) {
					for(Person person:pList) {
						if(person.getPersonId() == th.getPersonId() && person.getLocation().equalsIgnoreCase(location) && th.getRecoveredDate()!= null && th.getDeathDate() == null ) {
							if(th.getRecoveredDate().equals(d)) {
								count++;
								System.out.format("%-3d %-20s %-10s %-10s\n",person.getPersonId(),person.getName(),person.getLocation(),sdf.format(th.getRecoveredDate()));
								break;
							}		
						}
					}
				}
				
			}
			//location only
			else if(boolLoc.equals("y") && boolDate.equals("n")) {
				System.out.println("Location Filter: Active (Location:"+location.toUpperCase()+")\tDate Filter: Inactive");
				System.out.format("\n%-3s %-20s %-10s %-10s\n","ID","Name","Location","Date");
				for(TreatmentHistory th:thList) {
					for(Person person:pList) {
						if(person.getPersonId() == th.getPersonId() && person.getLocation().equalsIgnoreCase(location) && th.getDeathDate() == null && th.getRecoveredDate()!=null) {
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
				System.out.println("Location Filter: Inactive\tDate Filter: Active (Date: "+date+")");
				System.out.format("\n%-3s %-20s %-10s %-10s\n","ID","Name","Location","Date");
				for(TreatmentHistory th:thList) {
					for(Person person:pList) {
						if(person.getPersonId() == th.getPersonId() && th.getDeathDate() == null && th.getRecoveredDate()!=null) {							
							if(th.getRecoveredDate().equals(d)) {
								count++;
								//id,name,location,date
								System.out.format("%-3d %-20s %-10s %-10s\n",person.getPersonId(),person.getName(),person.getLocation(),sdf.format(th.getRecoveredDate()));
								break;
							}
						}
					}
				}
			}
			//un-filtered
			else {
				System.out.println("Location Filter: Inactive\tDate Filter: Inactive");
				System.out.format("\n%-3s %-20s %-10s %-10s\n","ID","Name","Location","Date");
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
			
			System.out.println("Do you want to filter by Location?(y/n) You will prompted to enter the Location in the next Step.");
			boolLoc = sc.nextLine();
			if(boolLoc.equalsIgnoreCase("y")) {
				System.out.println("Enter Location:");
				while(true){
					location = sc.nextLine();
					if(validLocations.contains(location.toLowerCase()) == false) {
						System.out.println("Enter a valid Location (Bangalore|Chennai|Delhi|Kochi):");	
					}
					else
						break;
				}
			}
			System.out.println("Do you want to filter by Date?(y/n) You will be prompted to enter date in next step.");
			boolDate = sc.nextLine();
			if(boolDate.equalsIgnoreCase("y")) {
				System.out.println("Enter Date:");
				while(true){
					date = sc.nextLine();
					matcher = datep.matcher(date);
					if(matcher.matches() == false) {
						System.out.println("Enter a valid Date (Format:YYYY-MM-DD):");	
					}
					else
						break;
				}
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
				System.out.println("Location Filter: Active (Location:"+location.toUpperCase()+")\tDate Filter: Active (Date: "+date+")");
				System.out.format("\n%-3s %-20s %-10s %-10s\n","ID","Name","Location","Date");
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
				System.out.println("Location Filter: Active (Location:"+location.toUpperCase()+")\tDate Filter: Inactive");
				System.out.format("\n%-3s %-20s %-10s %-10s\n","ID","Name","Location","Date");
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
				System.out.println("Location Filter: Inactive\tDate Filter: Active (Date: "+date+")");
				System.out.format("\n%-3s %-20s %-10s %-10s\n","ID","Name","Location","Date");
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
				System.out.println("Location Filter: Inactive\tDate Filter: Inactive");
				System.out.format("\n%-3s %-20s %-10s %-10s\n","ID","Name","Location","Date");
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
			DonationClient dc1 = new DonationClient();
			List<DonationRequest> list = dc1.readDonationRequest("approved");
			if(list.size()==0) {
				System.out.println("No Donors available at the moment.");
				break;
			}			
			else
				System.out.format("%-5s %-20s %-20s %-6s\n","P.ID","Name","Location","Blood Group");			
			List<Person> pList = pc.readPerson();
			for(DonationRequest r:list) {
				for(Person p:pList) {
					if(p.getPersonId().equals(r.getPersonId())) {
						System.out.format("%-5d %-20s %-20s %-6s\n",r.getPersonId(),p.getName(),p.getLocation(),p.getBloodGroup());
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
