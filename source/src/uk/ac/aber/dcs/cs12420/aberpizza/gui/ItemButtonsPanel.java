/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Item;
import uk.ac.aber.dcs.cs12420.aberpizza.data.StoreItem;

/**
 * Panel constisting of buttons representing each item to sell (StoreItemButton).
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
@SuppressWarnings("serial")
public class ItemButtonsPanel extends JPanel implements ActionListener{
	
	private JScrollPane scrollPane;
	private JPanel insidePanel;
	private OrderPanel orderPanel;
	
	public ItemButtonsPanel(ArrayList<StoreItem> items, OrderPanel op){
		super();
		this.setVisible(true);
		this.setLayout(new GridLayout(1, 1, 0, 0));
		
		this.setBorder(null);
		
		insidePanel = new JPanel();	
		scrollPane = new JScrollPane(insidePanel);
		orderPanel = op;
		
				
		insidePanel.setLayout(new GridLayout(7, 6, 0, 0));
		
		for(int i=0;i<items.size();i++){
			StoreItemButton butt = new StoreItemButton(items.get(i));
			butt.addActionListener(this);
			insidePanel.add(butt);
		}
		
		insidePanel.setVisible(true);
		
		this.add(scrollPane, BorderLayout.WEST);

	
	}

	/**
	 * Listens to the buttons in the panel, and triggers adding items to the order.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		StoreItemButton source = (StoreItemButton)(e.getSource());
		Item item = source.getItem();
		orderPanel.addItemToOrder(item);
		orderPanel.validate();
		
	}

}
