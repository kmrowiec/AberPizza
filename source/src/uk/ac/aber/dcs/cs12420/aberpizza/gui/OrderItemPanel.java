/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uk.ac.aber.dcs.cs12420.aberpizza.data.OrderItem;

/**
 * A panel that contains single order item.
 * That means e. g. 3 x Small Pizza  +-   .
 * In other words, it contains text label with quantity and name of item
 * and two buttons, plus and minus, to increase/decrease quantity
 * 
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
public class OrderItemPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 500, HEIGHT = 40;
	private JLabel description;
	private OrderItem orderItem;
	private OrderPanel orderPanel;
	private OrderItemsListPanel listPanel;
	
	/**
	 * Represents a button increasing or decreasing quantity.
	 */
	private class QuantityButton extends JButton{
		private static final long serialVersionUID = 1L;
		public QuantityButton(String text){
			this.setMargin(new Insets(2,2,2,2));
			this.setText(text);
			this.setPreferredSize(new Dimension(30,30));
		}
	}
	
	public OrderItemPanel(OrderItem item, OrderPanel op, OrderItemsListPanel lp){
		orderPanel = op;
		listPanel = lp;
		orderItem = item;
		this.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		
		//setting sub-panel with two buttons
		JPanel buttons = new JPanel();
		JButton plus, minus;
		plus = new QuantityButton("+");
		minus = new QuantityButton("-");
		plus.addActionListener(this);
		minus.addActionListener(this);
		buttons.add(plus, BorderLayout.WEST);
		buttons.add(minus, BorderLayout.EAST);
		
		//setting bg colour
		Color col = Color.YELLOW;
		this.setBackground(col);
		buttons.setBackground(col);
		
		String q = Integer.toString(orderItem.getQuantity());
		description = new JLabel("  "+ q + " x " + orderItem.getItem().getDescription() +" (" +
				orderItem.getOrderItemTotal().toString() + ")");
		description.setFont(new Font("Helvetica",Font.BOLD, 17));
		
		this.setLayout(new BorderLayout());
		this.add(description, BorderLayout.WEST);
		this.add(buttons, BorderLayout.EAST);
		
		this.setVisible(true);
	}
	
	/**
	 * Changes text displayed in panel, 
	 * according to OrderItem that is being represented by the panel.
	 */
	public void updateDescription(){
		String q = Integer.toString(orderItem.getQuantity());
		description.setText("  "+ q + " x " + orderItem.getItem().getDescription() +" (" +
				orderItem.getOrderItemTotal().toString() + ")");
		description.repaint();
		
	}

	/**
	 * ActionListener for + and - buttons.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String message = e.getActionCommand();
		if(message.equals("+")){
			orderItem.setQuantity(orderItem.getQuantity()+1);
			updateDescription();
			orderPanel.updateTotalPrice();
		}else if(message.equals("-")){
			orderItem.setQuantity(orderItem.getQuantity()-1);
			updateDescription();
			if(orderItem.getQuantity()<=0){
				listPanel.removeOrderItem(this);
				
			}
			orderPanel.updateTotalPrice();
		}
		
	}
}
