/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import javax.swing.JEditorPane;
import javax.swing.JPanel;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;

/**
 * A panel that contains the receipt. It can be placed everywhere, but in this program
 * is is displayed in messageDialog from JOptionPane.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 */
public class ReceiptPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	public ReceiptPanel(Order order){
		JEditorPane editor = new JEditorPane("text/html; charset=EUC-JP",order.getReceipt());
		this.add(editor);
	}
	
}
