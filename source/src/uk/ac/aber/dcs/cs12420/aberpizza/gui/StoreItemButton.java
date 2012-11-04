/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.Currency;
import java.util.Locale;

import javax.swing.JButton;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Item;
import uk.ac.aber.dcs.cs12420.aberpizza.data.StoreItem;

/**
 * Button representing one item for sale.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
public class StoreItemButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 200;
	private final int HEIGHT = 80;
	private Item item;
	private static final String CURR = (Currency.getInstance(Locale.UK)).getSymbol(Locale.UK);
	
	public StoreItemButton(StoreItem item) {
		super();
		this.item = item;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // not working as I would expect, but helps Oo
		this.setMargin(new Insets(3, 3, 3, 3));
		this.setVisible(true);
		this.setFont(new Font("Helvetica", Font.PLAIN, 20));
		this.setText(item.getDescription() +"   "+CURR+item.getPrice().toString());
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}


	
	
	
}
