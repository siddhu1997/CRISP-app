
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import dao.TestingHistoryDAOImpl;
import model.TestingHistory;

public class MainTestingHistory {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static void create() throws ClassNotFoundException, SQLException, ParseException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Testing Create...");

		System.out.println("Enter Person Id:");
		Integer personId = Integer.parseInt(sc.nextLine());
		System.out.println("Enter Hospital:");
		String hospital = sc.nextLine();
		System.out.println("Enter Testing date in YYYY-MM-DD format:");
		Date testingDate = sdf.parse(sc.nextLine());
		System.out.println("Enter Result (Negative/Positive)");
		String result = sc.nextLine();
		int r = new TestingHistoryDAOImpl().createTestingHistory(personId, hospital, testingDate, result);
		System.out.println(r+" row(s) affected.");
		sc.close();
	}
	public static void read() throws ClassNotFoundException, SQLException, ParseException {
		Scanner sc = new Scanner(System.in);
		List<TestingHistory> testingHistoryList = new ArrayList<>();
		System.out.println("Testing readTestingHistoryByPersonId");
		System.out.println("Enter person id to get testing history");
		Integer personId = Integer.parseInt(sc.nextLine());
		System.out.println("Fetching results.....");
		testingHistoryList = new TestingHistoryDAOImpl().readTestingHistoryByPersonId(personId);
		if(testingHistoryList.size() <= 0 )
			System.out.println("No records found!");
		else
			for(TestingHistory th: testingHistoryList)
				System.out.println(th);
		sc.close();
	}
	public static void bulkRead() throws ClassNotFoundException, SQLException, ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Testing bulkRead........");
		List<TestingHistory> testingHistoryList = new TestingHistoryDAOImpl().readTestingHistory();
		if(testingHistoryList.size() <=0)
			System.out.println("Empty table");
		else
			for(TestingHistory th: testingHistoryList)
				System.out.println(th);
		System.out.println(testingHistoryList.size()+" records fetched!");
		sc.close();
	}
	public static void update() throws ClassNotFoundException, SQLException, ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Testing update....");
		System.out.println("Enter Testing Id");
		Integer testingId = Integer.parseInt(sc.nextLine());
		System.out.println("Enter Person Id");
		Integer personId = Integer.parseInt(sc.nextLine());
		System.out.println("Enter new Testing Date in YYYY-MM-DD format");
		Date testingDate = sdf.parse(sc.nextLine());
		System.out.println("Enter new Test result (Positive/Negative)");
		String result = sc.nextLine();

		//Always a good idea to fetch previous results in case user/admin doesn't want to update only 1 of the parameters
		//Use object.getter to restore value and pass this to update query.
		System.out.println("Old test result:");
		TestingHistory testingHistory = new TestingHistoryDAOImpl().readTestingHistoryByTestingId(testingId);
		System.out.println(testingHistory);
		System.out.println("Updating testing result....");
		int r = new TestingHistoryDAOImpl().updateTestingHistory(testingId, personId, testingDate, result);
		System.out.println(r+" row(s) affected.");
		System.out.println("Updated test result:");
		testingHistory = new TestingHistoryDAOImpl().readTestingHistoryByTestingId(testingId);
		System.out.println(testingHistory);
		sc.close();
	}
	public static void deleteById() throws ClassNotFoundException, SQLException, ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Testing deleteTestingHistory....");
		System.out.println("Enter testing id:");
		Integer testingId = Integer.parseInt(sc.nextLine());
		System.out.println("Deleting record...");
		int res = new TestingHistoryDAOImpl().deleteTestingHistory(testingId);
		System.out.println(res+" row(s) affected!");
		System.out.println("Testing for successful deletion...");
		TestingHistory th = new TestingHistoryDAOImpl().readTestingHistoryByTestingId(testingId);
		if(th == null)
			System.out.println("Successful!");
		else
			System.out.println("Failed!");
		sc.close();
	}
	public static void bulkDelete() throws ClassNotFoundException, SQLException, ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Testing emptying table functionality....");
		int res = new TestingHistoryDAOImpl().deleteTestingHistory();
		System.out.println(res+" row(s) affected.");
		List<TestingHistory> testingHistoryList = new TestingHistoryDAOImpl().readTestingHistory();
		if(testingHistoryList.size() <= 0) {
			System.out.println("Successfully emptied the table!");
		}
		else
			System.out.println("Failed!");
		sc.close();
	}
	public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException { 

		//create();
		//read();
		//bulkRead();
		//update();
		//deleteById();
		//bulkDelete();
	}
}
