import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.DonationRequestDAOImpl;
import model.DonationRequest;

public class MainDonorRequest{

	public static void create() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Testing addDonationRequest.....");
		System.out.println("Enter Person Id:");
		Integer personId = Integer.parseInt(sc.nextLine());
		System.out.println("Enter Donor remarks:");
		String donorRemarks = sc.nextLine();
		System.out.println("Adding Donor request....");
		DonationRequest dnr = new DonationRequest(personId,donorRemarks,"","pending");
		int r = new DonationRequestDAOImpl().addDonationRequest(dnr);
		System.out.println(r+" row(s) affected.");
		sc.close();
	}
	public static void bulkReadPending() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Testing updateStatus,readDonationRequestStatus,readDonationRequestStatusById.....");
		List<DonationRequest> donorList = new DonationRequestDAOImpl().readDonationRequest("pending");
		for(DonationRequest dr:donorList)
			System.out.println(dr);
		sc.close();
	}
	public static void update() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Testing updateStatus,readDonationRequestStatus,readDonationRequestStatusById.....");
		System.out.println("Enter Person ID: ");
		Integer personId = Integer.parseInt(sc.nextLine());
		
		System.out.println("Old requests:");
		List<DonationRequest> dr = new DonationRequestDAOImpl().readDonationRequestStatusByPerson(personId);
		for(DonationRequest d:dr)
			System.out.println(d);
		
		System.out.println("Enter request id whose status is to be updated:");
		Integer requestId = Integer.parseInt(sc.nextLine());
		
		System.out.println("Enter new status:");
		String status = sc.nextLine();
		System.out.println("Enter Admin Remarks...");
		String adminRemarks = sc.nextLine();
		
		System.out.println("Updating.....");
		int r = new DonationRequestDAOImpl().updateStatus(personId, status,adminRemarks,requestId);
		System.out.println(r+" row(s) affected.");
		
		System.out.println("Updated record");
		DonationRequest r2 = new DonationRequestDAOImpl().readDonationRequestById(requestId);
		System.out.println(r2);
		
		sc.close();
	}
	public static void delete() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Testing deleteDonationRequest.....");
		System.out.println("Enter Request ID:");
		Integer requestId = Integer.parseInt(sc.nextLine());
		int r = new DonationRequestDAOImpl().deleteDonationRequest(requestId);
		System.out.println(r +" row(s) affected.");
		System.out.println("Checking for successful deletion...");
		DonationRequest dr = new DonationRequestDAOImpl().readDonationRequestById(requestId);
		if(dr == null)
			System.out.println("Successful!");
		else
			System.out.println("Failed");
		
		sc.close();
	}
	public static void emptyTable() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Testing empty table functionality...");
		int r = new DonationRequestDAOImpl().deleteDonationRequest();
		System.out.println(r+" row(s) affected.");
		sc.close();
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//create();
		//bulkReadPending();
		//update();
		//delete();
		//emptyTable();
	}

}
