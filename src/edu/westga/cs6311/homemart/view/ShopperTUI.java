package edu.westga.cs6311.homemart.view;

import edu.westga.cs6311.homemart.model.Inventory;
import edu.westga.cs6311.homemart.model.InventoryItem;
import edu.westga.cs6311.homemart.model.Shopper;
import java.util.Scanner;

/**
 * user interface class for shopper
 * 
 * @author Jordan Barron
 * @version December 7th, 2019
 */
public class ShopperTUI {
	private Inventory inventoryObject;
	private Shopper shopperObject;
	private Scanner scan;

	/**
	 * Holds information on items from the inventory Class
	 * 
	 * @param newInventory from the Inventory Class
	 */
	public ShopperTUI(Inventory newInventory) {
		this.inventoryObject = newInventory;
		this.scan = new Scanner(System.in);
	}

	/**
	 * Obtains name and funds available to spend from user and creates a new shopper
	 */
	private void getShopperInformation() {
		String userName;
		String userMoneyString;
		double userMoneyDouble;

		System.out.print("Please enter your name: ");
		userName = this.scan.nextLine();

		do {
			System.out.println("Please enter the amount you have to spend: ");
			userMoneyString = this.scan.nextLine();
			userMoneyDouble = Double.parseDouble(userMoneyString);
		} while (userMoneyDouble <= 0);

		this.shopperObject = new Shopper(userName, userMoneyDouble);

		System.out.printf("Welcome %s. Enjoy spending your $%.2f  \n", this.shopperObject.getShopperName(),
				this.shopperObject.getShopperMoney());
		System.out.println();
	}

	/**
	 * runs the information inside it
	 */
	public void runShopper() {
		System.out.println("Welcome to the Shopper Application \n");
		this.getShopperInformation();
		boolean keepGoing = true;
		while (keepGoing) {
			this.displayMenu();
			System.out.print("Enter your choice: ");
			String userOptionString = this.scan.nextLine();
			int userOptionInt = Integer.parseInt(userOptionString);
			switch (userOptionInt) {
				case 1:
					this.viewInventory();
					break;
				case 2:
					this.addNewCartItem();
					break;
				case 3:
					this.viewCart();
					break;
				case 4:
					this.viewMoneyRemaining();
					break;
				case 5:
					this.checkout();
					break;
				case 6:
					keepGoing = false;
					System.out.println("Thank you for using the shopper application \n");
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
		System.out.println("1 - View inventory");
		System.out.println("2 - Add item to the cart");
		System.out.println("3 - View cart");
		System.out.println("4 - View money remaining");
		System.out.println("5 - Checkout");
		System.out.println("6 - Quit the shopper application");
	}

	/**
	 * Activates the open store method from the inventory class
	 * 
	 * From a shopper point a view, they see inventory that is available when the
	 * store is open
	 * 
	 * separated by if statement to keep initial inventory from increasing
	 */
	private void viewInventory() {
		System.out.println();
		
		if (this.inventoryObject.getTotalQuantity() <= 0) {
			this.inventoryObject.openStore();
			System.out.println(this.inventoryObject);
			return;
		}
		System.out.println(this.inventoryObject);
	}

	/**
	 * adds a new item to the shoppers cart
	 * 
	 * @precondition item must be in inventory
	 * @precondition the shopper has enough money to keep buying
	 */
	private void addNewCartItem() {
		String searchItem;
		String quantityAddedCartString;
		int quantityAddedCart;
		InventoryItem foundItem;

		System.out.println("\nEnter item to be purchased: ");
		searchItem = this.scan.nextLine();

		this.inventoryObject.findItem(searchItem);

		if (this.inventoryObject.findItem(searchItem) == null) {
			System.out.println("That item does not exist \n");
			return;
		} else {
			foundItem = this.inventoryObject.findItem(searchItem);
			System.out.println("Enter the number of " + foundItem.getItem() + "(s) to buy: ");
			quantityAddedCartString = this.scan.nextLine();
			quantityAddedCart = Integer.parseInt(quantityAddedCartString);
		}

		if (this.shopperObject.haveEnoughToBuy(foundItem, quantityAddedCart)) {
			this.shopperObject.addItem(foundItem, quantityAddedCart);
			System.out.printf("You have added %d %s(s) to your cart \nYou have $%.2f remaining to spend \n \n",
					quantityAddedCart, foundItem.getItem(), this.shopperObject.getShopperMoney());
		} else {
			System.out.printf(
					"You don't have enough money to purchase %d %s(s) \n \n", quantityAddedCart, foundItem.getItem());
			return;
		}
	}

	/**
	 * Shows breakdown of items user has taken from inventory. Activates the cart
	 * array from the shopper class when 3 is pressed by the user
	 * 
	 * because the getTheCart returns an arrayList we make this into a string by using a string builder
	 * 
	 * If there are no items in the shoppers cart (size = 0), the error message will
	 * print
	 * 
	 * @precondition Size for shopper cart cannot be empty (<= 0)
	 */
	private void viewCart() {
		System.out.println();
		
		StringBuilder result =  new StringBuilder();
		String separation = "\n"; 
		
		if (this.shopperObject.getTheCart().size() <= 0) {
			System.out.println("No items in cart \n");
			return;
		}
		
		for (InventoryItem element : this.shopperObject.getTheCart()) {
			result.append(element);
			result.append(separation);
		}
		
		System.out.println(result);
		System.out.println();
	}

	/**
	 * Shows money left for the user to spend Activates the getShopperMoney from the
	 * Shopper class when 4 is press by the user
	 * 
	 * If there is no money for the shopper to spend (getShopperMoney <= 0), the
	 * error message will print
	 * 
	 * @precondition Money for shopper cannot be empty (<= 0)
	 */
	private void viewMoneyRemaining() {
		if (this.shopperObject.getShopperMoney() <= 0) {
			System.out.println("No money available \n");
			return;
		}
		System.out.println();
		System.out.printf("Amount remaining to spend $%.2f \n", this.shopperObject.getShopperMoney());
		System.out.println();
	}

	/**
	 * Clears the ArrayList (represents the shopper checking out)
	 */
	private void checkout() {
		System.out.println();
		this.shopperObject.purchaseCart();
		System.out.println("Your purchase has been completed. \n");
	}

}
