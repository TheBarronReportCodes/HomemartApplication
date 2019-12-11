package edu.westga.cs6311.homemart.model;

/**
 * manages the core variables and functions for the homeMart project
 * 
 * @author Jordan Barron
 * @version December 7th, 2019
 *
 */
public class InventoryItem {
	private String itemName;
	private double itemCost;
	private int quantity;
	
	/**
	 * constructor that when called, creates a new instance of an item name, item cost, and quantity size
	 * @param item string parameter
	 * @param cost double parameter
	 * @param quantity integer parameter
	 * @precondition parameters cannot equal null
	 */
	public InventoryItem(String item, double cost, int quantity) {
		this.itemName = item;
		this.itemCost = cost;
		this.quantity = quantity;
	}
	
	/**
	 * sets default values if invalid information is passed in
	 */
	public InventoryItem() {
		this("", 0.00, 0);
	}

	
	/**
	 * item name entered in the Inventory Item constructor
	 * will be used in other methods
	 * @return item name entered in the Inventory Item constructor
	 * 
	 */
	public String getItem() {
		return this.itemName;
		
	}
	
	/**
	 * cost entered in the Inventory Item constructor
	 *  will be used in other methods
	 * @return cost entered in the Inventory Item constructor
	 *
	 */
	public double getCost() {
		return this.itemCost;
		
	}
	
	/**
	 * quantity entered in the Inventory Item constructor. Changes if setQuantity or purchaseItem method is used 
	 * will be used in other methods
	 * @return quantity entered in the Inventory Item constructor. Changes if setQuantity or purchaseItem method is used 
	 * 
	 */
	public int getQuantity() {
		return this.quantity;
		
	}

	/**
	 * Sets new Quantity
	 * 
	 * @param newQuantity integer parameter that is used to set new quantity
	 */
	public void setQuantity(int newQuantity) {
		if (newQuantity < 0) {
			return;
		} else {
			this.quantity = newQuantity;
		}
	}
	
	/**
	 * represents a customer purchasing an item
	 * if an item is purchased, it is no longer in inventory
	 * @param numberPurchased represents the number purchased
	 */
	public void purchaseItem(int numberPurchased) {
		if (this.quantity < 0) {
			return;
		} else if (numberPurchased > this.quantity) {
			return;
		} else {
			this.quantity = this.quantity - numberPurchased;
		}
	}
	
	/**
	 * inventoryItem information as a string
	 * @return string of inventoryItem name, cost, and quantity
	 * @override toString method
	 * 0 right padding for string (0$-) left padding for float and int
	 * 10 spaces for string, 6 spaces for float, 3 spaces for int
	 * 
	 */
	public String toString() {
		String newString = "";
		newString += String.format("%0$-10s $ %6.2f Quantity: %3d", this.itemName, this.itemCost, this.quantity);
		return newString;
	}
	
}
