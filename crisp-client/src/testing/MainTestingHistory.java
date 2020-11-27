package testing;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import model.TestingHistory;
import service.TestingHistoryClient;

public class MainTestingHistory {

	public static void main(String[] args) throws IOException, ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Scanner sc = new Scanner(System.in);
		TestingHistoryClient c = new TestingHistoryClient();
		System.out.println("Testing add...");
		Integer r = c.createTestingHistory(new TestingHistory(1,"Lakshore",sdf.parse("2020-11-11"),"positive"));
		System.out.println(r +" rows(s) affected.");
		System.out.println("Testing read by id....");
		TestingHistory t = c.readTestingHistoryByTestingId(2);
		System.out.println(t);
		System.out.println("Testing read all...");
		List<TestingHistory> list = c.readTestingHistory();
		for(TestingHistory ee:list)
			System.out.println(ee);
		System.out.println("Testing by read by person id");
		List<TestingHistory> list2 = c.readTestingHistoryByPersonId(1);
		for(TestingHistory e:list2)
			System.out.println(e);
		System.out.println("Testing read by test id...");
		TestingHistory test = c.readTestingHistoryByTestingId(3);
		System.out.println(test);
		System.out.println("Testing update....");
		Integer r1 = c.updateTestingHistory(new TestingHistory(10,4,sdf.parse("2020-12-16"),"negative"));
		System.out.println(r1+" row(s) affected.");
		System.out.println("Testing delete....");
		Integer r2 = c.deleteTestingHistory(11);
		System.out.println(r2+" row(s) affected.");
		sc.close();
	}

}
