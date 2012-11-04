/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

/**
 * Panel displayed on the right side of Till screen.
 * It contains three tabs, for Pizzas, Sides and Drinks.
 * Every tab contains ItemButtonsPanel.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
public class StorePanel extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ItemButtonsPanel pizzasPanel, sidesPanel, drinksPanel;
	OrderPanel orderPanel;
	
	private class TabDescription extends JLabel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public TabDescription(String text){
			super();
			this.setFont(new Font("Helvetica",Font.PLAIN, 30));
			
			this.setText(text);
		}

	}
	
	public StorePanel(Till till, OrderPanel op){
		super(); 
		orderPanel = op;
		
		pizzasPanel = new ItemButtonsPanel(till.getPizzas(), op);
		sidesPanel = new ItemButtonsPanel(till.getSides(), op);
		drinksPanel = new ItemButtonsPanel(till.getDrinks(), op);
		
		pizzasPanel.setBackground(Color.GREEN);
		sidesPanel.setBackground(Color.ORANGE);
		drinksPanel.setBackground(Color.BLUE);
		
		this.add(pizzasPanel, "PIZZAS");
		this.add(sidesPanel, "SIDES");
		this.add(drinksPanel, "DRINKS");
		
		this.setTabComponentAt(0, new TabDescription("PIZZAS"));
		this.setTabComponentAt(1, new TabDescription("SIDES"));
		this.setTabComponentAt(2, new TabDescription("DRINKS"));
		
		this.setBackground(Color.PINK); // only for testing purposes :P
				
		this.setVisible(true);
	}
}
