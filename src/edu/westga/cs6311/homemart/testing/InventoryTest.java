package edu.westga.cs6311.homemart.testing;

import edu.westga.cs6311.homemart.model.Inventory;
import edu.westga.cs6311.homemart.model.InventoryItem;
import edu.westga.cs6311.homemart.model.Shopper;
import edu.westga.cs6311.homemart.view.ManagerTUI;
import edu.westga.cs6311.homemart.view.ShopperTUI;

/**
 * test class to check errors and expected outcomes
 * 
 * @author Jordan Barron
 * @version December 7th, 2019
 *
 */
public class InventoryTest {
	/**
	 * InventoryItem class test
	 */
	public void testItemClass() {
		InventoryItem object1 = new InventoryItem("pencil", 2, 4);
		InventoryItem object2 = new InventoryItem("windshield", .99, 100);
		
		System.out.println("^^^^Inventory Item Class Test^^^^");
	
		System.out.println("Expected item name: pencil");
		System.out.println("Actual: " + object1.getItem());

		System.out.println("Expected cost: 2.00");
		System.out.println("Actual: " + object1.getCost());

		System.out.println("Expected quantity: 4");
		System.out.println("Actual: " + object1.getQuantity());

		object1.setQuantity(89);
		System.out.println("Expected new quantity: 89");
		System.out.println("Actual: " + object1.getQuantity());

		object1.purchaseItem(95);
		System.out.println("Expected new quantity after purchased item > quantity: 89 (return)");
		System.out.println("Actual: " + object1.getQuantity());

		object1.purchaseItem(12);
		System.out.println("Expected new quantity after purchased item < quantity: 77");
		System.out.println("Actual: " + object1.getQuantity());

		System.out.println("__________ $ ______ Quantity: ___-Expected toString");
		System.out.println(object1 + "- Actual toString");

		System.out.println(object2 + "- Actual toString");
	}
	
	/**
	 * Inventory Class Test
	 */
	public void testInventoryClass() {
		System.out.println();

		System.out.println("^^^^Inventory Class Test^^^^");
		Inventory arrayListObject = new Inventory();
		arrayListObject.openStore();
		arrayListObject.addItem("pencil", 2, 4);
		arrayListObject.addItem("windshield", .99, 100);
		arrayListObject.addItem("air filter", 8.09, 30);

		System.out.println("Expected found item: pencil");
		System.out.println("Actual: " + arrayListObject.findItem("hammers"));
		
		System.out.println();
		
		System.out.println("Expected: Total Quantity: 239, Most Expensive: hammers, Least Expensive: nails, Average Cost: 4.41");
		System.out.println("Actual: \n" + arrayListObject);

	}
		
	/**
	* managerTUI Test
	*/
	public void testManagerTuiClass() {
		System.out.println();
		System.out.println("^^^^ManagerTUI Class Test^^^^");
		Inventory inventoryObject = new Inventory();
		ManagerTUI test = new ManagerTUI(inventoryObject);
			
		test.runManager();
	}
	
	/**
	 * Shopper Class Test
	 */
	public void testShopperClass() {
		System.out.println();
		System.out.println("^^^^Shopper Class Test^^^^");
		
		InventoryItem plaster = new InventoryItem("plaster", 12, 7);
		Shopper jordan = new Shopper("Jordan", 50.00);
		
		System.out.println("Expected: Shopper name is Jordan and the money they have is 50.00");
		System.out.println("Actual: Shopper name is " + jordan.getShopperName() + " and the money they have is " + jordan.getShopperMoney());
		
		System.out.println();
		
		System.out.println("Have enough to buy Expected: true");
		System.out.println("Have enough to buy: " + jordan.haveEnoughToBuy(plaster, 3));
		
		System.out.println();
		
		System.out.println("Expected: money = 2.00 & inventory quantity = 3");
		System.out.println("Actual");
		jordan.addItem(plaster, 4);
		System.out.println(jordan.getShopperMoney());
		System.out.println(plaster.getQuantity());
	}
	
	/**
	* managerTUI Test
	*/
	public void testShopperTuiClass() {
		System.out.println();
		System.out.println("^^^^ManagerTUI Class Test^^^^");
		Inventory inventoryObject = new Inventory();
		ShopperTUI test = new ShopperTUI(inventoryObject);
			
		test.runShopper();
	}

}
