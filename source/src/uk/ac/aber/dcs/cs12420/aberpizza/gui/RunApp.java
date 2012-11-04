/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import javax.swing.JOptionPane;

import uk.ac.aber.dcs.cs12420.aberpizza.data.ProductType;
import uk.ac.aber.dcs.cs12420.aberpizza.data.StoreItem;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;


/**
 * Start point for the application.
 * Here Till is loaded and GUI is started.
 * 
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
public class RunApp {

	
	/**
	 * Main method, where the till is loaded and GUI is initialized.
	 * @param args Arguments passed to main method.
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Till till = null;
		
		try{
		till = Till.load();
		}catch(Exception e){
			//If till could not be loaded, the program asks wheter to load 
			//pre-defined, hard-coded till items.
			//If user click YES, they are loaded and that till will be saved to settings.xml later on,
			//if NO, the program closes. 
			int i = JOptionPane.showConfirmDialog(null, 
					"Cannot load Till from file. Load pre-defined Till?",
					"Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			switch(i){
			case 0:
				till = RunApp.setupTill();
				break;
			case 1:
				System.exit(0);
				break;
			}
		}
		if(till==null) till = new Till();
		
		MainFrame mf = new MainFrame(till);
	}

	/**
	 * If settings.xml file does not exist, this method creates new Till
	 * and adds items for sale.
	 * @return created till
	 */
	public static Till setupTill(){
		Till till = new Till();
		
		
		till.addStoreItem(new StoreItem("Small margherita", "2.99"));
		till.addStoreItem(new StoreItem("Medium margherita", "5.99"));
		till.addStoreItem(new StoreItem("Large margherita", "8.99"));
		till.addStoreItem(new StoreItem("Small pepperoni", "3.49"));
		till.addStoreItem(new StoreItem("Medium pepperoni", "6.19"));
		till.addStoreItem(new StoreItem("Large pepperoni", "9.49"));
		till.addStoreItem(new StoreItem("Small hawaiian", "3.59"));
		till.addStoreItem(new StoreItem("Medium hawaiian", "6.99"));
		till.addStoreItem(new StoreItem("Large hawaiian", "9.99"));
		
		till.addStoreItem(new StoreItem("Small vegetarian", "2.99"));
		till.addStoreItem(new StoreItem("Medium vegetarian", "5.99"));
		till.addStoreItem(new StoreItem("Large vegetarian", "8.99"));
		till.addStoreItem(new StoreItem("Small oregano", "2.99"));
		till.addStoreItem(new StoreItem("Small Funghi", "2.99"));
		till.addStoreItem(new StoreItem("Small BBQ pizza", "3.99"));
		till.addStoreItem(new StoreItem("Medium BBQ pizza", "6.99"));
		till.addStoreItem(new StoreItem("Large BBQ pizza", "9.99"));		
		
		till.addStoreItem(new StoreItem("Potato wedges", "1.99", ProductType.SIDE));
		till.addStoreItem(new StoreItem("Garlic bread", "2.99", ProductType.SIDE));
		till.addStoreItem(new StoreItem("Fries", "2.45", ProductType.SIDE));
		till.addStoreItem(new StoreItem("Coleslaw", "4.25", ProductType.SIDE));
		
		till.addStoreItem(new StoreItem("Cola", "2.29", ProductType.DRINK));
		till.addStoreItem(new StoreItem("Orange juice", "1.89", ProductType.DRINK));
		till.addStoreItem(new StoreItem("Lemonade", "1.29", ProductType.DRINK));
		
		/*
		NOTE : ProductType is used only with offers, to know wheter the item 
		is a side or a drink. So far, there is no need to specify it for pizzas,
		but it may be needed in the future. If constructor without ProductType argument is used,
		it is by default set to NOT_SPECIFIED.		
		 */
		
		return till;
	}
}
