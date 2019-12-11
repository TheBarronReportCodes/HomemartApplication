package edu.westga.cs6311.homemart.model;

import java.util.ArrayList;

/**
 * manages the core variables and functions for the homeMart project on the
 * shopper facing side
 * 
 * @author Jordan Barron
 * @version December 8th, 2019
 *
 */
public class Shopper {
	private String shopperName;
	private double shopperMoney;
	private ArrayList<InventoryItem> theCart;

	/**
	 * constructor that when called, creates a new instance of a shoppersName, and
	 * the amount of money they have
	 * 
	 * @param name  (Shoppers name)
	 * @param money (amount of money shopper has)
	 */
	public Shopper(String name, double money) {
		this.shopperName = name;
		this.shopperMoney = money;
		this.theCart = new ArrayList<InventoryItem>();
	}

	/**
	 * sets default values if invalid information is passed in
	 */
	public Shopper() {
		this("", 0);
	}

	/**
	 * accesses the shopper name entered
	 * 
	 * @return the shopperName
	 */
	public String getShopperName() {
		return this.shopperName;
	}

	/**
	 * accesses the shopper money entered
	 * 
	 * @return the shopperMoney
	 */
	public double getShopperMoney() {
		return this.shopperMoney;
	}
	
	/**
	 * accesses the cart arrayList
	 * @return the theCart
	 */
	public ArrayList<InventoryItem> getTheCart() {
		return this.theCart;
	}

	/**
	 * return true or false based on if the shopper has enough money to buy the
	 * items.
	 * 
	 * @param item     (InventoryItem object)
	 * @param quantity (amount customer wants to purchase)
	 * @return true or false
	 */
	public boolean haveEnoughToBuy(InventoryItem item, int quantity) {
		double costForCustomer = item.getCost() * quantity;

		boolean haveEnoughToBuy = this.shopperMoney >= costForCustomer;

		return haveEnoughToBuy;
	}

	/**
	 * If shopper enough to buy the item and there is enough in stock, then the
	 * number of the item you want to buy gets added to you cart Once added to the
	 * cart, the inventory of the item should decrease and the shoppers purchase
	 * power should decrease
	 * 
	 * @param item             (InventoryItem Object)
	 * @param customerQuantity (amount customer wants to purchase)
	 * @precondition have enough to buy != false & in stock != false
	 */
	public void addItem(InventoryItem item, int customerQuantity) {
		if ((this.haveEnoughToBuy(item, customerQuantity)) && (item.getQuantity() >= customerQuantity)) {
			InventoryItem itemAdded = new InventoryItem(item.getItem(), item.getCost(), customerQuantity);
			this.theCart.add(itemAdded);

			item.purchaseItem(customerQuantity);
			item.setQuantity(item.getQuantity());
			this.shopperMoney = this.shopperMoney - (item.getCost() * customerQuantity);
		} else {
			return;
		}
	}

	/**
	 * Clear out all items in the shopping cart
	 */
	public void purchaseCart() {
		this.theCart.clear();
	}

}
