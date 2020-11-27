package testing;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import model.DonationRequest;
import service.DonationClient;

public class MainDonationRequest {

	static DonationClient dc = new DonationClient();
	public static void readStatus() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter status String");
		String status = sc.nextLine();
		List<DonationRequest> d = dc.readDonationRequest(status);
		for(DonationRequest d1:d)
			System.out.println(d1);
		sc.close();
	}
	public static void readById() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ente, NumberFormatExceptionr person Id:");
		Integer personId = Integer.parseInt(sc.nextLine());
		List<DonationRequest> d = dc.readDonationRequestStatusByPerson(personId);
		for(DonationRequest d1:d)
			System.out.println(d1);
		sc.close();
	}
	public static void readReqId() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Request Id:");
		Integer requestId = Integer.parseInt(sc.nextLine());
		DonationRequest d1 = dc.readDonationRequestById(requestId);
		System.out.println(d1);
		sc.close();
	}
	public static void update() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter person Id:");
		Integer personId = Integer.parseInt(sc.nextLine());
		System.out.println("Enter req id:");
		Integer requestId = Integer.parseInt(sc.nextLine());
		System.out.println("Enter status:");
		String status = sc.nextLine();
		System.out.println("Enter admin remarks:");
		String adminRemarks = sc.nextLine();
		DonationRequest dn = new DonationRequest(requestId,personId,adminRemarks,status);
		Integer r = dc.updateStatus(dn);
		System.out.println(r+" row(s) affected.");
		sc.close();
	}
	public static void add() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter person Id:");
		Integer personId = Integer.parseInt(sc.nextLine());
		System.out.println("enter donor remarks:");
		String donorRemarks = sc.nextLine();
		System.out.println("Enter status:");
		String status = sc.nextLine();
		System.out.println("Enter admin remarks:");
		String adminRemarks = sc.nextLine();
		DonationRequest dn = new DonationRequest(personId,donorRemarks,adminRemarks,status);
		Integer r = dc.addDonationRequest(dn);
		System.out.println(r+" row(s) affected.");
		sc.close();
	}
	public static void delete() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter request Id:");
		Integer requestId = Integer.parseInt(sc.nextLine());
		Integer r = dc.deleteDonationRequest(requestId);
		System.out.println(r+" row(s) affected.");
		sc.close();
	}
	public static void main(String[] args) throws IOException {

		//readStatus();
		//readById();
		//readReqId();
		//update();
		//add();
		//delete();
		

	}

}
