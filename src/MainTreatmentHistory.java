import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.TreatmentHistoryDAOImpl;
import model.TreatmentHistory;

public class MainTreatmentHistory {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	static TreatmentHistoryDAOImpl treat = new TreatmentHistoryDAOImpl();
	public static void bulkRead() throws ClassNotFoundException, SQLException, ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bulk Read testing....");
		System.out.println("Fetching all records...." );
		List<TreatmentHistory> list = treat.readTreatmentHistory();
		for(TreatmentHistory th:list)
			System.out.println(th);
		sc.close();
	}
	public static void read() throws ClassNotFoundException, SQLException, ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("readTreatmentHistoryByPersonId testing....");
		System.out.println("Enter person id:");
		Integer personId = Integer.parseInt(sc.nextLine());
		System.out.println("Fetching record.........");
		TreatmentHistory t = treat.readTreatmentHistoryByPersonId(personId);
		System.out.println(t);
		sc.close();
	}
	public static void update() throws ClassNotFoundException, SQLException, ParseException {
		Date d;
		int r;
		String date;
		Scanner sc = new Scanner(System.in);
		System.out.println("update functionality testing....");
		System.out.println("Enter person id:");
		Integer personId = Integer.parseInt(sc.nextLine());
		TreatmentHistory th1 = treat.readTreatmentHistoryByPersonId(personId);
		System.out.println(th1);
		System.out.println("Testing updateAdmissionDate...");
		System.out.println("Enter new admission date");
		date = sc.nextLine();
		d = sdf.parse(date);
		System.out.println(d);
		r = treat.updateAdmissionDate(personId,"Updated", d);
		System.out.println(r+" row(s) affected.");
		
		System.out.println("Testing updateRecoveredDate...");
		System.out.println("Enter new recovered date");
		date = sc.nextLine();
		d = sdf.parse(date);
		System.out.println(d);
		r = treat.updateRecoveredDate(personId, "Updated", d);
		System.out.println(r+" row(s) affected.");
		
		System.out.println("Testing updateDeathDate...");
		System.out.println("Enter new death date");
		date = sc.nextLine();
		d = sdf.parse(date);
		System.out.println(d);
		r = treat.updateDeathDate(personId, "Updated", d);
		System.out.println(r+" row(s) affected.");
		
		System.out.println("Updated record:");
		TreatmentHistory t = treat.readTreatmentHistoryByPersonId(personId);
		System.out.println(t);
		sc.close();
	}
	public static void delete() throws ClassNotFoundException, SQLException, ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("deleteTreatmentHistory functionality testing....");
		System.out.println("Enter person ID");
		Integer personId= Integer.parseInt(sc.nextLine());
		int r = treat.deleteTreatmentHistory(personId);
		System.out.println(r+" row(s) affected.");
		System.out.println("Checking for successful deletion...");
		TreatmentHistory th = treat.readTreatmentHistoryByPersonId(personId);
		if(th == null)
			System.out.println("Successful!");
		else
			System.out.println("Failed");
		sc.close();
	}
	public static void empty() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("empty table functionality testing....");
		int r = treat.deleteTreatmentHistory();
		System.out.println(r+" row(s) affected.");
		sc.close();
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {

		//bulkRead();
		//read();
		//update();
		//delete();
		//empty();
	}

}
