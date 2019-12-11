package edu.westga.cs6311.homemart.controller;

import edu.westga.cs6311.homemart.model.Inventory;
import edu.westga.cs6311.homemart.view.HomemartTUI;


/**
 * Final execution method of application
 * 
 * @author Jordan Barron
 * @version December 7th, 2019
 */
public class HomemartDriver {

	/**
	 * Entry point into application
	 * 
	 * @param args
	 * 
	 * not used
	 */
	public static void main(String[] args) {
		Inventory inventoryObject = new Inventory();
		HomemartTUI homemartObject = new HomemartTUI(inventoryObject);
		
		homemartObject.runHomemart();

	}

}