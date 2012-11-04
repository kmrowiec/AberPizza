/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

/**
 * A thread, where previous orders are loaded to the PreviousOrdersPanel.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
public class PreviousOrdersLoader extends Thread {

	private PreviousOrdersPanel orderPanel;
	
	private Date startDate = null, endDate = null;
	private BigDecimal totalAmount = new BigDecimal("0.00");
	private String output;
	
	/**
	 * 
	 * @param op reference to OrderPanel, where loader is initialized
	 * @param start start date of the time period from which orders are displayed
	 * @param end finish date; null when only orders from one day are displayed
	 */
	public PreviousOrdersLoader(PreviousOrdersPanel op, Date start, Date end){
		orderPanel = op;
		startDate = start;
		endDate = end;
	}
	
	/**
	 * Main method of the thread.
	 * Here everything is loaded.
	 */
	public void run(){
		
		if(startDate==null) return;
		orderPanel.getSettingsPanel().getProgressBar().setVisible(true);
		ArrayList<Order> orders = new ArrayList<Order>();
		if(endDate==null) endDate = startDate;
		
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		Calendar cal = Calendar.getInstance();
		do{
			try{
			orders.addAll(Till.loadOrders(startDate));
			}catch(Exception e){}
			startDate.setTime(startDate.getTime()+86400000);
			cal.setTime(startDate);
		}while(cal.get(Calendar.YEAR)<endCal.get(Calendar.YEAR)||
				cal.get(Calendar.DAY_OF_YEAR)<=endCal.get(Calendar.DAY_OF_YEAR));
		
		
		StringBuilder sb = new StringBuilder();
		for(Order o:orders){
			sb.append(PreviousOrdersPanel.orderToHTML(o));
		}
		output = sb.toString();
	
		//Getting total amount and number of orders
		orderPanel.setTotalOrders(orders.size());
		for(Order i:orders){
			totalAmount = totalAmount.add(i.getSubtotal());
		}
				orderPanel.setTotalAmount(totalAmount);
				
		//Setting changes to editorPane in PreviousOrdersPanel
		if(output.equals("")){
			output = "<h2>Nothing to display.</h2>";
		}
		
		orderPanel.getEditorPane().setText(output);		
		orderPanel.getSettingsPanel().resetLoadedOrdersLabel();
		orderPanel.getSettingsPanel().getProgressBar().setVisible(false);
		
	}
	

	
}
