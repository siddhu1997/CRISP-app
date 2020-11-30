package Main;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import menuOptions.Menu;

public class Main {

	public static Scanner sc = new Scanner(System.in);
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static void main(String[] args) {
		
		/*
		 * First Menu:
		 * 1. Admin
		 * 2. User
		 */
		Menu.mainMenu();
		
	}

}
