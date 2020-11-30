package menuOptions;

public class Menu {

	//root node
	public static void mainMenu() {
		System.out.println("Choose an option:");
		System.out.println("1. Continue as Administrator");
		System.out.println("2. Continue as User");
		System.out.println("Enter 1 or 2:\n");
	}
	
	//User Node
	public static void userMainMenu() {
		System.out.println("Choose an option:");
		System.out.println("1. Register as a new User");
		System.out.println("2. Login as an exisiting User");
		System.out.println("3. Go Back");
		System.out.println("Enter an option:");
	}
	
	//Admin Node
	public static void adminOptionsMenu() {
		System.out.println("Choose an option:");
		System.out.println("1. Manage Donor Requests");
		System.out.println("2. See Patient Lists");
		System.out.println("3. See Recovered List");
		System.out.println("4. See Death List");
		System.out.println("5. See Donor List");
		System.out.println("6. Go Back");
		System.out.println("Enter an option:");
	}
	
	//User Testing History Menu
	public static void testingMenu() {
		System.out.println("Choose an option:");
		System.out.println("1. Add new Test Results");
		System.out.println("2. Go Back");
		System.out.println("Enter an option:");
	}
	
	//Admin Treatment History Menu
	public static void updateTreatmentHistoryMenu() {
		System.out.println("Choose an option");
		System.out.println("1. Update Admission Date");
		System.out.println("2. Update Recovery Date");
		System.out.println("3. Update Death Date");
		System.out.println("4. Go Back");
		System.out.println("Enter an option:");

	}
	
	//User Statistics Menu
	public static void userStatisticsMenu() {
		System.out.println("Choose an option:");
		System.out.println("1. Get Count");
		System.out.println("2. See Patient List");
		System.out.println("3. See Recovered List");
		System.out.println("4. See Donor List");
		System.out.println("5. Go Back");
		System.out.println("Enter an option:");
	}
}
