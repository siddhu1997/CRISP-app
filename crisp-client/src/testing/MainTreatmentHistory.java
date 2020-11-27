package testing;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import java.util.List;

import model.TreatmentHistory;
import service.TreatmentHistoryClient;

public class MainTreatmentHistory {

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static TreatmentHistoryClient c = new TreatmentHistoryClient();
	public static void add() throws ParseException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter person id");
		Integer personId = Integer.parseInt(sc.nextLine());
		System.out.println("Enter admission date:");
		Date d = sdf.parse(sc.nextLine());
		TreatmentHistory th = new TreatmentHistory(personId,d);
		Integer r = c.createTreatmentHistory(th);
		System.out.println(r+" row(s) affected.");
		sc.close();
	}
	public static void readByPersonId() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter person id");
		Integer personId = Integer.parseInt(sc.nextLine());
		TreatmentHistory r = c.readTreatmentHistoryByPersonId(personId);
		System.out.println(r);
		sc.close();
	}
	public static void readAll() throws IOException {
		List<TreatmentHistory> r = c.readTreatmentHistory();
		for(TreatmentHistory r1:r)
			System.out.println(r1);
	}
	public static void update() throws IOException, ParseException {

		Integer r = c.updateAdmissionDate(new TreatmentHistory(1,"Infected!",sdf.parse("2020-12-27")));
		System.out.println(r+" row(s) affected");
	}
	public static void delete() throws IOException {
		Integer r = c.deleteTreatmentHistory(1);
		System.out.println(r);
	}
	public static void main(String[] args) throws ParseException, IOException {

			//add();
			//readByPersonId();
			//readAll();
			//update();
			//delete();
	}

}
