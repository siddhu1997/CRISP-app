package menuOptions;

public class Menu {

	//root node
	public static void mainMenu() {
		System.out.println("Choose an option:");
		System.out.println("1. Continue as Administrator");
		System.out.println("2. Continue as User");
		System.out.println("3. Exit this application");
		System.out.println("Enter an option:");
	}
	
	//User Node
	public static void userMainMenu() {
		System.out.println("Choose an option:");
		System.out.println("1. Register as a new User");
		System.out.println("2. Login as an exisiting User");
		System.out.println("3. Go Back");
		System.out.println("Enter an option:");
	}
	
	//Admin Node 2
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
	
	//Admin Node 1
	public static void adminOptionMainMenu() {
		System.out.println("Enter an option:");
		System.out.println("1. Manage Donor Requests,See all the lists (Patient|Recovered|Dead).");
		System.out.println("2. Update Treatment History of a patient");
		System.out.println("3. Go Back");
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
		System.out.println("4. See Death List");
		System.out.println("5. See Donor List");
		System.out.println("6. Go Back");
		System.out.println("Enter an option:");
	}
	
	//User post login Menu
	public static void userPostLoginMenu(){
		System.out.println("Choose an option:");
		System.out.println("1. Add a new Testing Result");
		System.out.println("2. Plasma Donor Registration");
		System.out.println("3. View Satistics");
		System.out.println("4. Go Back");
		System.out.println("Enter an option:");
	}
	
	//User Donation Menu
	public static void userDonorMenu() {
		System.out.println("Choose an option:");
		System.out.println("1. Apply for Plasma Donor Registration");
		System.out.println("2. See status of previous requests");
		System.out.println("3. Go Back");
		System.out.println("Enter an option:");
	}
}
