package edu.westga.cs6311.homemart.view;

import edu.westga.cs6311.homemart.model.Inventory;
import java.util.Scanner;

/**
 * user interface class for inventory
 * 
 * @author Jordan Barron
 * @version December 7th, 2019
 */
public class ManagerTUI {
	private Inventory inventoryObject;
	private Scanner scan;

	/**
	 * Holds information on items from the inventory Class
	 * 
	 * @param newInventory from the Inventory Class
	 */
	public ManagerTUI(Inventory newInventory) {
		this.inventoryObject = newInventory;
		this.scan = new Scanner(System.in);
	}

	/**
	 * runs the information inside it
	 */
	public void runManager() {
		System.out.println("Welcome to the Manager Application \n");
		
		boolean keepGoing = true;
		while (keepGoing) {
			this.displayMenu();
			System.out.print("Enter your Choice: ");
			String userOptionString = this.scan.nextLine();
			int userOptionInt = Integer.parseInt(userOptionString);
			
			System.out.println();

			switch (userOptionInt) {
				case 1:
					this.openStore();
					break;
				case 2:
					this.addNewInventory();
					break;
				case 3:
					this.viewInventory();
					break;
				case 4:
					keepGoing = false;
					System.out.println("Thank you for using the manager application \n");
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
		System.out.println("1 - Open New Store");
		System.out.println("2 - Add a new item to the inventory");
		System.out.println("3 - View the current inventory");
		System.out.println("4 - Quit the manager application");
	}

	/**
	 * Activates the openStore method from the Inventory class when 1 is pressed by
	 * the user
	 */
	private void openStore() {
		this.inventoryObject.openStore();
		System.out.println("Your store is open! \n");
	}

	/**
	 * Activates the addItem method from the Inventory class when 2 is pressed by
	 * the user
	 */
	private void addNewInventory() {
		String userItemName = "";
		double userItemCostDouble = 0;
		int userItemQuantityInt = 0;

		do {
			System.out.println("Please enter the item name: ");
			userItemName = this.scan.nextLine();
		} while (userItemName == null);

		do {
			System.out.println("Please enter the item cost: ");
			String userItemCost = this.scan.nextLine();
			userItemCostDouble = Double.parseDouble(userItemCost);
		} while (userItemCostDouble <= 0);

		do {
			System.out.println("Please enter the item quantity: ");
			String userItemQuantity = this.scan.nextLine();
			userItemQuantityInt = Integer.parseInt(userItemQuantity);
		} while (userItemQuantityInt <= 0);

		this.inventoryObject.addItem(userItemName, userItemCostDouble, userItemQuantityInt);

		System.out.printf("You added %d %s(s) at a cost of %.2f to the inventory \n", userItemQuantityInt, userItemName, userItemCostDouble);
		System.out.println();
	}

	/**
	 * Activates the toString method from the Inventory class when 3 is pressed by
	 * the user
	 * 
	 * If there are no items in stock (quantity = 0), the error message will print
	 * 
	 * @precondition Quantity cannot be empty (<= 0)
	 */
	private void viewInventory() {
		if (this.inventoryObject.getTotalQuantity() <= 0) {
			System.out.println("No items in Stock \n");
			return;
		}
		System.out.println(this.inventoryObject);
	}
}
