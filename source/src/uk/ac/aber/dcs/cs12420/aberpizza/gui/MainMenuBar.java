/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Represents menu bar from main frame.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
public class MainMenuBar extends JMenuBar implements ActionListener{

	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;
	
	public MainMenuBar(MainFrame mf) {
		super();
		mainFrame = mf;
		JMenu fileMenu, adminMenu;
		fileMenu = new JMenu("File");
		
		JMenuItem quit = new JMenuItem("Quit");
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		quit.addActionListener(this);
		fileMenu.add(quit);
		
		adminMenu = new JMenu("Admin");
		
		JMenuItem addShopItem = new JMenuItem("Add item to the store");
		addShopItem.addActionListener(this);
		adminMenu.add(addShopItem);
		
		JMenuItem showTill = new JMenuItem("Show till");
		showTill.addActionListener(this);
		adminMenu.add(showTill);
		
		JMenuItem showHistory = new JMenuItem("Show previous orders");
		showHistory.addActionListener(this);
		adminMenu.add(showHistory);
		
		this.add(fileMenu);
		this.add(adminMenu);
		
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String message = arg0.getActionCommand();
		if(message.equals("Show previous orders")){
			mainFrame.getTill().save();
			mainFrame.switchView("PREVIOUS_ORDERS");
		}else if(message.equals("Show till")){
			mainFrame.switchView("TILL");
		}else if(message.equals("Quit")){
			mainFrame.getTill().save();
			System.exit(0);
		}else if(message.equals("Add item to the store")){
			mainFrame.addStoreItem();
		}
		
	}

}
