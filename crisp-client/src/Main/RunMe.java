package Main;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import menuOptions.Menu;
import model.Person;
import crispBO.UserStoryFour;
import crispBO.UserStoryOneFiveSix;
import crispBO.UserStorySeven;
import crispBO.UserStoryThree;
import crispBO.UserStoryTwoEight;

public class RunMe {

	public static Scanner sc = new Scanner(System.in);
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException, SQLException {

		//Assigning all choices to exit by default;
		Integer choice1 = 3; 
		Integer choice2 = 3;
		Integer choice3 = 3;
		Integer choice4 = 4;
		Integer choice5 = 3;

		do {

			//Print 1st Menu options
			Menu.mainMenu();
			choice1 = Integer.parseInt(sc.nextLine());

			switch(choice1) {
			//Admin Story- Case 1
			case 1:
				//admin
				System.out.println("Enter Administrator username:");
				String username = sc.nextLine();
				//Ad@12345
				System.out.println("Enter password:");
				String password = sc.nextLine();
				if(UserStoryOneFiveSix.adminLoginValidation(username, password)) {
					do {

						Menu.adminOptionMainMenu();
						sc = new  Scanner(System.in);
						choice2 = Integer.parseInt(sc.nextLine());
						switch(choice2) {
						case 1:
							UserStoryTwoEight.adminManage();
							break;
						case 2:
							UserStoryThree.updateTreatementHistory();
							break;
						default:
							break;
						}

					}while(choice2 != 3);					
				}				
				break;
				//User Story - Case 2
			case 2:

				Menu.userMainMenu();
				choice3 = Integer.parseInt(sc.nextLine());

				switch(choice3) {
				case 1:
					UserStoryFour.userRegistration();
					break;
				case 2:
					Person personFetched = UserStoryOneFiveSix.userLoginValidation();
					if(personFetched.getPersonId()!=null) {
						do {
							Menu.userPostLoginMenu();
							choice4 = Integer.parseInt(sc.nextLine());
							switch(choice4) {
							case 1:
								UserStoryOneFiveSix.userTestingHistory(personFetched);
								break;
							case 2:
								do {
									Menu.userDonorMenu();
									choice5 = Integer.parseInt(sc.nextLine());
									switch(choice5) {
									case 1:
										UserStorySeven.createDonationRequest(personFetched);
										break;
									case 2:
										UserStorySeven.viewStatus(personFetched);
										break;
									default:
										break;
									}
								}while(choice5 != 3);
								break;
							case 3:
								UserStoryTwoEight.userStatistics();
								break;
							default:
								break;
							}
						}while(choice4 != 4);
					}
					break;
				}
		
				break;
			default:
				break;
			}

		}while(choice1 != 3);
		
		sc.close();
	}
}
