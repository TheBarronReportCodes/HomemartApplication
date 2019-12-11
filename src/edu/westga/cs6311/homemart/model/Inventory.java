package edu.westga.cs6311.homemart.model;

import java.util.ArrayList;

/**
 * manages the ArrayList functions for the homeMart project
 * 
 * @author Jordan Barron
 * @version December 7th, 2019
 *
 */
public class Inventory {
	private ArrayList<InventoryItem> inventoryCollections;

	/**
	 * constructor that when called, creates a new instance of an ArrayList of
	 * InventoryItem objects
	 */
	public Inventory() {
		this.inventoryCollections = new ArrayList<InventoryItem>();
	}

	/**
	 * creates to inventory item objects (hammer and nails)
	 * Adds objects to the arrayList
	 * 
	 */
	public void openStore() {
		InventoryItem hammers = new InventoryItem("Hammer", 9.99, 5);
		InventoryItem nails = new InventoryItem("Nails", .98, 100);	
		
		this.inventoryCollections.add(hammers);
		this.inventoryCollections.add(nails);
	}

	/**
	 * finds and returns the inventory item that matches the name entered in the
	 * string
	 * 
	 * @param searchItem the name to search
	 * @precondition searchItem cannot equal null
	 * @return InventoryItem object. null if not found
	 */
	public InventoryItem findItem(String searchItem) {
		if (searchItem == null) {
			return null;
		}

		for (InventoryItem current : this.inventoryCollections) {
			if (current.getItem().equalsIgnoreCase(searchItem)) {
				return current;
			}
		}

		return null;
	}

	/**
	 * Adds an InventoryItem class object to the arrayList
	 * 
	 * @param newItemName represents new item name to be added
	 * @param newItemCost represents new item cost to be added
	 * @param newItemQuantity represents new item quantity to be added
	 * @precondition item name cannot equal null
	 */
	public void addItem(String newItemName, double newItemCost, int newItemQuantity) {
		if (newItemName == null) {
			return;
		}
		
		InventoryItem newInventory = new InventoryItem(newItemName, newItemCost, newItemQuantity);
		
		this.inventoryCollections.add(newInventory);
	}

	/**
	 * finds cheapest item
	 * @return the InventoryItem object that has the lowest cost
	 */
	public InventoryItem getCheapestItem() {
		InventoryItem cheapest = this.inventoryCollections.get(0);

		for (InventoryItem current : this.inventoryCollections) {
			if (cheapest.getCost() > current.getCost()) {
				cheapest = current;
			}
		}

		return cheapest;
	}

	/**
	 * finds most expensive item
	 * @return the InventoryItem object that has the lowest cost
	 */
	public InventoryItem getMostExpensiveItem() {
		InventoryItem mostExpensive = this.inventoryCollections.get(0);

		for (InventoryItem current : this.inventoryCollections) {
			if (mostExpensive.getCost() < current.getCost()) {
				mostExpensive = current;
			}
		}

		return mostExpensive;
	}

	/**
	 * finds total of all quantities
	 * @return sum of all quantities from each inventoryItem object in the ArrayList
	 */
	public int getTotalQuantity() {
		int totalQuantity = 0;

		for (InventoryItem current : this.inventoryCollections) {
			totalQuantity += current.getQuantity();
		}

		return totalQuantity;
	}

	/**
	 * finds average cost of all items
	 * @return average cost of all inventoryItem objects in the ArrayList
	 */
	public double getAverageCost() {
		double totalCost = 0;
		int size = this.inventoryCollections.size();

		for (InventoryItem current : this.inventoryCollections) {
			totalCost += current.getCost();
		}

		double average = totalCost / size;

		return average;
	}

	/**
	 * final string to be displayed to console
	 * @override toString method for arrayList level of abstraction
	 * @precondition inventoryCollections cannot be null
	 * @return final string to be displayed to console
	 */
	public String toString() {
		String finalString = "";

		if (this.inventoryCollections == null) {
			return finalString;
		}

		finalString += String.format(
				this.arrayListToString()
						+ "\nNumber of Items: %d \nTotal quantity of all items: %d \nMost Expensive item:  "
						+ this.getMostExpensiveItem() + "\nLeast Expensive item: " + this.getCheapestItem()
						+ "\nAverage item cost: $%.2f \n",
				this.inventoryCollections.size(), this.getTotalQuantity(), this.getAverageCost());

		return finalString;
	}
	
	/**
	 * private helper method
	 * @return Inventory ArrayList in string form
	 */
	private String arrayListToString() {
		String arrayString = "";
		
		for (InventoryItem current : this.inventoryCollections) {
			arrayString += current + "\n";
		}
		
		return arrayString;
	}

}
