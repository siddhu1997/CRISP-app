/*
 * Code developed by: Shivalekshmi
 */
package crispBO;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import model.DonationRequest;
import model.Person;
import model.TestingHistory;
import model.TreatmentHistory;
import service.DonationClient;
import service.TestingHistoryClient;
import service.TreatmentHistoryClient;

public class UserStorySeven{

	static TestingHistoryClient tc = new TestingHistoryClient();
	static TreatmentHistoryClient treat = new TreatmentHistoryClient(); 
	static DonationClient dc = new DonationClient();
	static Scanner sc = new Scanner(System.in);

	public static void createDonationRequest(Person personFetched) throws ClassNotFoundException, SQLException, ParseException, IOException {

		Integer personId = personFetched.getPersonId();


		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

		System.out.println("Enter Donor remarks:");
		String donorRemarks = sc.nextLine();

		int num = 0;

		List<TestingHistory> testList = tc.readTestingHistoryByPersonId(personId);
		TreatmentHistory treatment= treat.readTreatmentHistoryByPersonId(personId);
		List<DonationRequest> donationList=dc.readDonationRequestStatusByPerson(personId);
		for(int i=0;i<testList.size();i++)
		{ 
			if((testList.get(i).getResult()).equalsIgnoreCase("Positive"))
				num++;
		}

		Collections.sort(testList,(a,b)->sdf.format(b.getTestingDate()).compareTo(sdf.format(a.getTestingDate())));
		Collections.sort(donationList,(a,b)->b.getStatus().compareTo(a.getStatus()));
		//System.out.println(donationList.get(0).getStatus());
		String status = "";
		if(donationList.size()==0)
			status = "rejected";
		else
			status = donationList.get(0).getStatus();
		String[] results=new String[3];
		DonationRequest dnr;

		if(( ( (testList.size() < 3) || (num < 1)) || (treatment.getDeathDate()!=null)|| status.equalsIgnoreCase("Approved")))
		{ 
			dnr=new DonationRequest(personId, donorRemarks,"Not Eligible","Rejected");
			System.out.println("Not Eligible");
			int donorReq = dc.addDonationRequest(dnr);
			if(donorReq == 1)
				System.out.println("Sorry you are not eligible. Eligibility criteria: 1 Postive and 3 consecutive Negative testing history.");
		}

		else if(status.equalsIgnoreCase("Pending"))
			System.out.println("You already have a request in Pending status");

		else 
		{
			for(int i=0;i<3;i++)
			{
				//	System.out.println(testList.get(i).getResult());
				results[i]=testList.get(i).getResult();
			}

			int count = 0;
			for(int i=0;i <3;i++)
			{
				if(results[i].equalsIgnoreCase("Negative"))
					count++;
			}

			if(count == 3)
			{  
				dnr= new DonationRequest(personId,donorRemarks,"Pending","Pending");
				System.out.println("Waiting for Admin Approval"); 
			}
			else 
			{ 
				dnr=new DonationRequest(personId, donorRemarks,"Not Eligible","Rejected");
				System.out.println("Not Eligible");
			}

			int donorReq = dc.addDonationRequest(dnr);
			if(donorReq == 1) {
				System.out.println();
			}

		}
	}

	public static void viewStatus(Person personFetched) throws ClassNotFoundException, SQLException, IOException {
		Integer personId = personFetched.getPersonId();
		List<DonationRequest> donations = dc.readDonationRequestStatusByPerson(personId);
		System.out.println("Request History:");
		if(donations.size()==0) {
			System.out.println("No History found");
		}
		else
			System.out.format("%-5s %-10s %-25s\n","ReqID","Status","Admin Remarks");
		for(DonationRequest d: donations)
			System.out.format("%-5d %-10s %-25s\n",d.getReqId(),d.getStatus(),d.getAdminRemarks());
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Menu\n1.Request for Donor Registration\n2.View Status \nEnter Choice");
		int choice=sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Request for Donor Registration");
			//createDonationRequest(personFetched);
			break;
		case 2:
			System.out.println("View Status");
			//viewStatus();

		default:
			break;
		}
		sc.close();
	}
}
