/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

/**
 * Panel responsible for showing previous orders.
 * It contains JEditorPane that displays orders coverted to text with HTML elements;
 * as well as settingsPanel that provides controls.
 * 
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
public class PreviousOrdersPanel extends JPanel {

	private JEditorPane editorPane;
	private JScrollPane scrollPane;
	private PreviousOrdersSettingsPanel settingsPanel;
	private Till till;
	private MainFrame frame;
	
	private int totalOrders;
	private BigDecimal totalAmount;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PreviousOrdersPanel(Till till, MainFrame frame) {
		this.frame = frame;
		this.till = till;
		this.setLayout(new BorderLayout());
		
		totalAmount = new BigDecimal("0.00");
		
		editorPane = new JEditorPane("text/html; charset=EUC-JP","");
		scrollPane = new JScrollPane(editorPane);
		scrollPane.setPreferredSize(new Dimension(600, 300));
		
		
		settingsPanel = new PreviousOrdersSettingsPanel(this);
		
		
		this.add(scrollPane, BorderLayout.WEST);
		this.add(settingsPanel, BorderLayout.CENTER);

	}
	
	
	
	/**
	 * Creates HTML representation of order, to be displayed in this panel.
	 * Similar to getReceipt() from Order class, but wider and shows custormer name as well.
	 * @param order input order
	 * @return String with HTML version of the order
	 */
	public static String orderToHTML(Order order){
		if(order==null) return "";
		StringBuilder sb = new StringBuilder();
		
		//first of all, creating nice-looking date string
		SimpleDateFormat df = new SimpleDateFormat("dd MMMMM yyyy,  HH:mm");
		String niceDate = df.format(order.getDate());
		
		
		//date and number of order
		sb.append("<br>");
		sb.append("<table width=\"500\"><tr><td>Order No :</td><td>");
		sb.append(order.getNumber()); // here goes proper date 
		sb.append("</td></tr><tr><td width=\"35%\">Date :</td><td>");
		sb.append(niceDate);
		sb.append("</td></tr><tr><td width=\"35%\">Customer name :</td><td>");
		sb.append(order.getCustomerName());
		sb.append("</td></tr></table>");
		sb.append("--------------------------------------------------");
		sb.append("-------------------------------------------------");
		
		//starting the part with items
		sb.append("<table width=\"500\">");
		
		for(int i=0;i<order.getItems().size();i++){
			sb.append("<tr><td>");
			sb.append(order.getItems().get(i).toString()); // here go quantity and name
			sb.append("</td><td width=\"30%\" align=\"right\">");
			sb.append(order.getItems().get(i).getOrderItemTotal().toString());
			sb.append("</td></tr>");
		}
		
		//and finishing part with items by closing table tag
		sb.append("</table>--------------------------------------------------");
		sb.append("--------------------------------------------------");
		
		//subtotal
		sb.append("<table width=\"500\"><tr><td align=\"right\">Sub-Total :" +
				"</td><td width=\"30%\" align=\"right\">");
		sb.append(order.getSubtotal().add(order.getDiscount()));
		sb.append("</td></tr>");
		
		//discount
		sb.append("<tr><td align=\"right\">Discount :" +
						"</td><td width=\"30%\" align=\"right\">");
		sb.append(order.getDiscount().toString());
		sb.append("</td></tr>");
		
		//spacer
		sb.append("<td align=\"right\"></td><td width=\"30%\" align=\"right\">-------------</td></tr>");
		
		//to pay
		sb.append("<tr><td align=\"right\">To Pay :" +
								"</td><td width=\"30%\" align=\"right\">");
		sb.append(order.getSubtotal().toString());
		sb.append("</td></tr>");
			
		//spacer
		sb.append("<td align=\"right\"></td><td width=\"30%\" align=\"right\">-------------</td></tr>");
		
		//tendered
		sb.append("<tr><td align=\"right\">Tendered :" +
								"</td><td width=\"30%\" align=\"right\">");
		sb.append(order.getTendered().toString());
		sb.append("</td></tr>");
		
		//change
		sb.append("<tr><td align=\"right\">Change :" +
								"</td><td width=\"30%\" align=\"right\">");
		sb.append(order.getChange().toString());
		sb.append("</td></tr></table>");
		sb.append("<br><hr>");
		
		return sb.toString();
	}

	/**
	 * @return the totalOrders
	 */
	public int getTotalOrders() {
		return totalOrders;
	}

	/**
	 * @param totalOrders the totalOrders to set
	 */
	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}

	/**
	 * @return the totalAmount
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Till getTill() {
		return till;
	}

	public MainFrame getFrame() {
		return frame;
	}

	public JEditorPane getEditorPane() {
		return editorPane;
	}

	public PreviousOrdersSettingsPanel getSettingsPanel() {
		return settingsPanel;
	}

	

}
