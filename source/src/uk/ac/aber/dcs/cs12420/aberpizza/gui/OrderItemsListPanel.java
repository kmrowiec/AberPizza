/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uk.ac.aber.dcs.cs12420.aberpizza.data.OrderItem;

/**
 * Panel that contains OrderItemPanels, i. e. all OrderItems
 * from current order.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
public class OrderItemsListPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JPanel insidePanel; 
	private OrderPanel orderPanel;
	private ArrayList<OrderItemPanel> itemPanels;

	public OrderItemsListPanel(OrderPanel orderPanel){
		super();
		this.orderPanel = orderPanel;
		this.setLayout(new GridLayout(1,1));
		insidePanel = new JPanel();
		insidePanel.setLayout(new BoxLayout(insidePanel, BoxLayout.PAGE_AXIS));
		scrollPane = new JScrollPane(insidePanel);
		itemPanels = new ArrayList<OrderItemPanel>();
		
		this.add(scrollPane);
	}
	
	public void addItemToPanel(OrderItem item){
		
		itemPanels.add(new OrderItemPanel(item, orderPanel, this));
		insidePanel.add(itemPanels.get(itemPanels.size()-1));
	}


	public void removeOrderItem(Component comp) {
		orderPanel.removeEmptyItems();
		insidePanel.remove(comp);
		itemPanels.remove(comp);
		this.repaint();
		this.validate();
		
	}
	
	public void reset(){
		insidePanel.removeAll();
		itemPanels = new ArrayList<OrderItemPanel>();
	}

	/**
	 * @return the itemPanels
	 */
	public ArrayList<OrderItemPanel> getItemPanels() {
		return itemPanels;
	}

	/**
	 * @return the insidePanel
	 */
	public JPanel getInsidePanel() {
		return insidePanel;
	}
	
	
}
