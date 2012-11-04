/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uk.ac.aber.dcs.cs12420.aberpizza.data.ProductType;
import uk.ac.aber.dcs.cs12420.aberpizza.data.StoreItem;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

/**
 * Main Frame of the application. 
 * Contains two panels - tillPanel and previousOrdersPanel.
 * Only one of them is displayed at the time, can be switched
 * using menu bar.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements WindowListener{
	
	Till till;
	MainMenuBar menuBar;
	JPanel previousOrdersPanel, tillPanel;
	
	private class CancelInputSignal extends Throwable{
		
	}
	
	public MainFrame(Till till){
		this.setSize(1000, 700);
		this.setTitle("AberPizza");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(new CardLayout());
		tillPanel = new JPanel(new BorderLayout());
		previousOrdersPanel = new PreviousOrdersPanel(till, this);
		
		this.till = till;
		
		
		//***************************** setting till panel
		//setting order panel
		OrderPanel orderPanel = new OrderPanel(till);
		Dimension pref = new Dimension((int)(this.getWidth()*(0.4)),this.getHeight());
		orderPanel.setPreferredSize(new Dimension(pref));
		pref = new Dimension((int)(this.getWidth()*(0.4)),(int)(this.getHeight()*(0.6)));
		orderPanel.getMiddlePanel().setPreferredSize(pref);
		
		//adding sub-panels
		tillPanel.add(orderPanel, BorderLayout.WEST);
		tillPanel.add(new StorePanel(till, orderPanel), BorderLayout.CENTER);
		//***************************** end of setting till panel
		
		
		this.getContentPane().add(tillPanel, "TILL");
		this.getContentPane().add(previousOrdersPanel, "PREVIOUS_ORDERS");
		this.addWindowListener(this);
		
		menuBar = new MainMenuBar(this);
		this.setJMenuBar(menuBar);
		this.pack();
		this.validate();
	}
	
	/**
	 * Switches displayed panel.
	 * @param view "TILL" or "PREVIOUS_ORDERS"
	 */
	public void switchView(String view){
		CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
	    cl.show(this.getContentPane(), view);
	}

	/**
	 * Provides few Dialogs that allow user to add an item to the store.
	 * Asks for description, price and type of the product,
	 * then new StoreItem is created and added to the Till.
	 * NOTE : The program needs restart to apply changes.
	 */
	public void addStoreItem(){
		
		String descr = null, price = null, prodType = null;
		
		try{
		descr = JOptionPane.showInputDialog(this, "Give a description", 
				"Add an item.", JOptionPane.PLAIN_MESSAGE);
		if(descr==null || descr.equals("")) throw new CancelInputSignal();
		
		price = JOptionPane.showInputDialog(this, "Give a price", 
				"Add an item.", JOptionPane.PLAIN_MESSAGE);
		if(price==null || price.equals("")) throw new CancelInputSignal();

		String[] choices = {"Pizza", "Side", "Drink"};
		prodType = (String)JOptionPane.showInputDialog(
                this, "Choose type of the product", "Add an item.",
                JOptionPane.PLAIN_MESSAGE, null, choices, "Pizza");	
		if(prodType==null) throw new CancelInputSignal();
		
		}catch(CancelInputSignal s){
			JOptionPane.showMessageDialog(this, "Item has not been added.", 
					"Action cancelled", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		StoreItem toAdd = new StoreItem(descr, price);
		ProductType type = prodType.equals("Pizza") ? ProductType.PIZZA :
			prodType.equals("Side") ? ProductType.SIDE :
				prodType.equals("Drink") ? ProductType.DRINK : ProductType.NOT_SPECIFIED;
		toAdd.setProductType(type);
		
		till.addStoreItem(toAdd);
		
		JOptionPane.showMessageDialog(this, 
				"An item has been added. To apply changes, please restart the program.", 
				"Success", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowActivated(WindowEvent arg0) {
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent arg0) {
		till.save();
		System.exit(0);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeiconified(WindowEvent arg0) {	
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowIconified(WindowEvent arg0) {		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowOpened(WindowEvent arg0) {
	}

	/**
	 * @return the till
	 */
	public Till getTill() {
		return till;
	}
	
	

}
