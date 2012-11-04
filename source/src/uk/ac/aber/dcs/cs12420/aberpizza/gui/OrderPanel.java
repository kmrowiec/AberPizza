/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Item;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

/**
 * Panel that is displayed in left part of the screen, contains all information concerning current order.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 */
public class OrderPanel extends JPanel implements ActionListener, ContainerListener, CaretListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel title; // e. g. Order No 13
	private JPanel titlePanel;
	private OrderItemsListPanel middlePanel;
	private JTextField custNameField;
	private JButton cancelButton, payButton;
	private static final String CURR = (Currency.getInstance(Locale.UK)).getSymbol(Locale.UK);
	
	//panel with subtotal, discount and to pay prices
	private JPanel pricePanel, lowerPanel;
	private PricePanel subtotal, discount, toPay;
	
	//big font used in this panel
	private final Font font = new Font("Helvetica",Font.PLAIN, 30);
	
	private Order currentOrder = new Order();
	private Till till;
	
	private class PricePanel extends JPanel{
		private static final long serialVersionUID = 1L;
		private JTextField text;
		public PricePanel(String description){
			this.setLayout(new GridLayout(1, 2, 0, 0));
			JLabel label = new JLabel(description, JLabel.LEFT);
			label.setFont(font);
			text = new JTextField(4);
			text.setFont(font);
			text.setEditable(false);
			text.setText(CURR+"0.00");
			this.add(label);
			this.add(text);
		}
	}
	
	public OrderPanel(Till till){		
		super();
		this.till = till;
		
		
		//setting upper part of the panel
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String str = df.format(currentOrder.getDate());
		String number = Integer.toString(till.getOrders().size()+1) +"/"+str;
		title = new JLabel("Order No " + number);
		title.setFont(font);
		titlePanel = new JPanel(new BorderLayout(0,3));
		//titlePanel.setLayout(new BorderLayout());
		titlePanel.add(title, BorderLayout.NORTH);
		custNameField = new JTextField();
		custNameField.setFont(font);
		custNameField.setColumns(10);
		custNameField.addCaretListener(this);
		
		JLabel forDescription = new JLabel("FOR :");
		forDescription.setFont(font);
		JPanel nameFieldPanel = new JPanel(new BorderLayout(5,10));
		nameFieldPanel.add(forDescription, BorderLayout.WEST);
		nameFieldPanel.add(custNameField, BorderLayout.EAST);
		titlePanel.add(nameFieldPanel, BorderLayout.SOUTH);
		
		//setting the part where order items are displayed
		middlePanel = new OrderItemsListPanel(this);
		middlePanel.getInsidePanel().addContainerListener(this);
		
		//setting the lower part of the panel, i. e. price panel
		pricePanel = new JPanel(new GridLayout(3, 1,0,0));
		subtotal = new PricePanel("SUBTOTAL:  ");
		discount = new PricePanel("DISCOUNT:  ");
		toPay = new PricePanel("TO PAY:  ");
		pricePanel.add(subtotal);
		pricePanel.add(discount);
		pricePanel.add(toPay);
		lowerPanel = new JPanel(new BorderLayout(0,8));
		payButton = new JButton("ORDER IS EMPTY");
		payButton.setBackground(new Color(184,75,82));
		payButton.setEnabled(false);
		payButton.setActionCommand("pay");
		payButton.addActionListener(this);
		payButton.setFont(new Font("Helvetica",Font.PLAIN, 25));
		payButton.setForeground(Color.WHITE);
		cancelButton = new JButton("CANCEL");
		cancelButton.setFont(new Font("Helvetica",Font.PLAIN, 25));
		cancelButton.setBackground(Color.RED);
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setEnabled(false);
		cancelButton.addActionListener(this);
		lowerPanel.add(pricePanel, BorderLayout.NORTH);
		lowerPanel.add(cancelButton, BorderLayout.WEST);
		lowerPanel.add(payButton, BorderLayout.EAST);
		
				
		//finally adding all sub-panels to the main panel
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(middlePanel, BorderLayout.CENTER);
		this.add(lowerPanel, BorderLayout.SOUTH);
		
		
	}
	
	/**
	 * Cleans panel after saving order.
	 */
	public void resetOrder(){
		currentOrder = new Order();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String str = df.format(currentOrder.getDate());
		String number = Integer.toString(till.getOrders().size()+1) +"/"+str;
		title.setText("Order No " + number);
		custNameField.setText("");
		middlePanel.reset();
		subtotal.text.setText(CURR+"0.00");
		discount.text.setText(CURR+"0.00");
		toPay.text.setText(CURR +"0.00");
		this.repaint();
		this.validate();
	}
	
	/**
	 * Adds current order to list of orders, shows info that order is saved,
	 * then cleans all panels so that new order can be taken.
	 */
	public void payForOrder(){
		currentOrder.setDate(new Date()); // setting date to current
		
		//Checking if tendered money is enough to pay for the order.
		for(;;){
			JOptionPane.showMessageDialog(this.getParent(), new CheckoutPanel(currentOrder), "", JOptionPane.PLAIN_MESSAGE);
			if(currentOrder.getTendered().compareTo(currentOrder.getSubtotal())==-1){
				JOptionPane.showMessageDialog(this.getParent(), "Not enough money tendered", "Error", JOptionPane.ERROR_MESSAGE);
				continue;
			}
			break;
		}
		
		//Setting order number.
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String str = df.format(currentOrder.getDate());
		String number = Integer.toString(till.getOrders().size()+1) +"/"+str;
		currentOrder.setNumber(number);
		
		//Setting customer name.
		currentOrder.setCustomerName(custNameField.getText());
		
		//Adding current order to orders list.
		till.getOrders().add(currentOrder); 
		
		//Displays the receipt.
		JOptionPane.showMessageDialog(this.getParent(), new ReceiptPanel(currentOrder), "Receipt", 
				JOptionPane.PLAIN_MESSAGE);
		
		//Resets OrderPanel.
		this.resetOrder();
	}
	
	/**
	 *Gets subtotal from order, calculates if any discounts are applicable, 
	 *and updates adequate text labels.
	 */
	public void updateTotalPrice(){
		BigDecimal discount = currentOrder.getDiscount();
		BigDecimal subtotal = currentOrder.getSubtotal();
		this.subtotal.text.setText(CURR+subtotal.add(discount).toString());
		this.discount.text.setText(CURR + discount.toString());
		this.toPay.text.setText(CURR+subtotal.toString());
	}
	
	/**
	 * Removes from currentOrder every OrderItem with Quantity equal to zero.
	 */
	public void removeEmptyItems(){
		for(int i=0; i<currentOrder.getItems().size();i++){
			if(currentOrder.getItems().get(i).getQuantity()<=0){
				currentOrder.getItems().remove(i);
				i--;
			}
		}
	}
	
	/**
	 * Called when any StoreItemButton is cliked, what triggers adding item to order.
	 * It checks if such item has already been added to the order, if so,
	 * only quantity increases, otherwise new order position is created.
	 * @param item Item to be added
	 */
	public void addItemToOrder(Item item){
		//first, checking if such item already is in the order
		//if so, only quantity is increased
		int i; 
		for(i=0; i<currentOrder.getItems().size();i++){
			if(currentOrder.getItems().get(i).getItem().equals(item)){
				//increasing quantity
				currentOrder.getItems().get(i).setQuantity(currentOrder.getItems().get(i).getQuantity()+1);
				//changing description of order item panel
				middlePanel.getItemPanels().get(i).updateDescription();
				middlePanel.repaint();
				middlePanel.validate();
				updateTotalPrice();
				return;
			}
		}
		
		currentOrder.addItem(item, 1);
		middlePanel.addItemToPanel(currentOrder.getItems().get(currentOrder.getItems().size()-1));
		updateTotalPrice();
	}
	
	/**
	 * @return Panel with OrderItems.
	 */
	public OrderItemsListPanel getMiddlePanel(){
		return middlePanel;
	}

	/**
	 * This listener method checks if "PAY" button has been clicked.
	 * If so, payForOrder() method is called.
	 * It also maintains cancellation of order.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("pay")){
			this.payForOrder();
		}else if(arg0.getActionCommand().equals("CANCEL")){
			if(currentOrder.getItems().size()==0){
				resetOrder();
				return;
			}
			int n = 0;
			n = JOptionPane.showConfirmDialog(this.getParent(), "Are you sure you want to cancel current order?"
					, "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(n==0) resetOrder();
		}
		
	}

	
	/**
	 * Method checks for changes in custNameField and middlePanel, i. e.
	 * if customer name has been filled and if the order is not empty.
	 * The "PAY" button depends of those two things - it turns green when both 
	 * requisites are fulfilled.
	 */
	public void isReadyToPay(){
		
		//Checking whether cancel button should be active.
		//(only if there is at least one item in the order OR customer name is not empty)
		if(currentOrder.getOrderItems().size()==0 && custNameField.getText().equals(""))
			cancelButton.setEnabled(false);
		else cancelButton.setEnabled(true);
		
		if(currentOrder.getOrderItems().size()==0){
			payButton.setText("ORDER IS EMPTY");
			payButton.setEnabled(false);
			payButton.setBackground(new Color(184,75,82));
			return;
		}

		
		if(custNameField.getText().equals("")){
			Dimension goodSize = payButton.getSize(); // so that size will not change
			payButton.setText("NO CUST. NAME");
			payButton.setEnabled(false);
			payButton.setBackground(new Color(184,75,82));
			payButton.setPreferredSize(goodSize);
			return;
		}
		
		Dimension goodSize = payButton.getSize(); // so that size will not change
		payButton.setText("PAY");
		payButton.setBackground(Color.GREEN);
		payButton.setEnabled(true);
		payButton.setPreferredSize(goodSize);
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.CaretListener#caretUpdate(javax.swing.event.CaretEvent)
	 */
	@Override
	public void caretUpdate(CaretEvent arg0) {
		isReadyToPay();
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ContainerListener#componentAdded(java.awt.event.ContainerEvent)
	 */
	@Override
	public void componentAdded(ContainerEvent e) {
		isReadyToPay();
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ContainerListener#componentRemoved(java.awt.event.ContainerEvent)
	 */
	@Override
	public void componentRemoved(ContainerEvent e) {
		isReadyToPay();
		
	}
	
	

}
