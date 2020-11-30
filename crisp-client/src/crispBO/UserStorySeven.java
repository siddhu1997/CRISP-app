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
import service.DonationClient;
import service.TestingHistoryClient;

public class UserStorySeven{
	
	static TestingHistoryClient tc = new TestingHistoryClient();
	static DonationClient dc = new DonationClient();
	
	public static void createDonationRequest(Person personFetched) throws ClassNotFoundException, SQLException, ParseException, IOException {
		
		Integer personId = personFetched.getPersonId();
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

		System.out.println("Enter Donor remarks:");
		String donorRemarks = sc.nextLine();
		
		
		
		int num = 0;
		
		//System.out.println("Adding Donor request....");
		List<TestingHistory> testList = tc.readTestingHistoryByPersonId(personId);
		
		for(int i=0;i<testList.size();i++)
		{ 
			if((testList.get(i).getResult()).equalsIgnoreCase("Positive"))
			num++;
		}
		
		Collections.sort(testList,(a,b)->sdf.format(b.getTestingDate()).compareTo(sdf.format(a.getTestingDate())));
		String[] results=new String[3];
		DonationRequest dnr;
		
		if((testList.size() < 3) || (num < 1))
		{ 
			dnr=new DonationRequest(personId, donorRemarks,"Not Eligible","Rejected");
			System.out.println("Not Eligible");
			int donorReq = dc.addDonationRequest(dnr);
			if(donorReq == 1)
				System.out.println("Sorry you are not eligible. Eligibility criteria: 1 Postive and 3 consecutive Negative testing history.");
		}
		
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
		sc.close();
	}
	
	public static void viewStatus() throws ClassNotFoundException, SQLException, IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Person Id:");
		Integer personId = Integer.parseInt(sc.nextLine());
		List<DonationRequest> donations = dc.readDonationRequestStatusByPerson(personId);
		System.out.println("Request History:");
		System.out.format("%-5s %-10s\n","ReqID","Status");
		for(DonationRequest d: donations)
			System.out.format("%-5d %-10s\n",d.getReqId(),d.getStatus());
		sc.close();
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, IOException {
/*	
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
			viewStatus();

		default:
			break;
		}
		sc.close();
*/
	}
}
