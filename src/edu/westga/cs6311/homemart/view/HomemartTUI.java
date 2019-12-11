package edu.westga.cs6311.homemart.view;

import java.util.Scanner;

import edu.westga.cs6311.homemart.model.Inventory;

/**
 * user interface class for HomeMart Administrators
 * 
 * @author Jordan Barron
 * @version December 8th, 2019
 */
public class HomemartTUI {
	private Inventory inventoryObject;
	private Scanner scan;
	
	/**
	 * Holds information on items from the inventory Class
	 * 
	 * @param newInventory from the Inventory Class
	 */
	public HomemartTUI(Inventory newInventory) {
		this.inventoryObject = newInventory;
		this.scan = new Scanner(System.in);
	}
	
	/**
	 * Allows user to run manager application, shopper application, or quit
	 */
	public void runHomemart() {
		System.out.println("Welcome to the Homemart Application \n");
		
		boolean keepGoing = true;
		while (keepGoing) {
			this.displayMenu();
			System.out.print("Enter your choice: ");
			String userOptionString = this.scan.nextLine();
			int userOptionInt = Integer.parseInt(userOptionString);
			
			System.out.println();
			
			switch (userOptionInt) {
				case 1:
					this.startManagerApplication();
					break;
				case 2:
					this.startShopperApplication();
					break;
				case 3:
					keepGoing = false;
					System.out.println("Thank you for using the homemart application. Have a great day");
					break;
				default:
					System.out.println("That's not a valid choice. Please try again \n");
					break;
			}
		}
	}
	
	/**
	 * Displays the different options for the user to choose and interact with
	 */
	private void displayMenu() {
		System.out.println("1 - Start manager application");
		System.out.println("2 - Start shopper application");
		System.out.println("3 - Quit");
	}
	
	/**
	 * Runs an instance of the ManagerTUI Application
	 */
	private void startManagerApplication() {
		ManagerTUI managerObject = new ManagerTUI(this.inventoryObject);
		
		managerObject.runManager();
	}
	
	/**
	 * Runs an instance of the ShopperTUI Application
	 */
	private void startShopperApplication() {
		ShopperTUI shopperObject = new ShopperTUI(this.inventoryObject);
		
		shopperObject.runShopper();
	}
}
