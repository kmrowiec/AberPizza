/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Represents whole order.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 */
public class Order {
	
	private Date date;
	private String customerName;
	private ArrayList<OrderItem> items;
	private String number;
	private BigDecimal tendered;
	
	private final static Offer[] offers = {new ThreeLargePizzasOffer(),
											new PizzaSideAndDrinkOffer()};
											
	
	
	public Order(){
		date = new Date();
		items = new ArrayList<OrderItem>();
		customerName = "";
		tendered = new BigDecimal("0.00");
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public void addItem(Item item, int quantity){
		items.add(new OrderItem(item, quantity));
	}
	
	/**
	 * Updates quantity of an OrderItem that contains Item given as parameter.
	 * @param item Item to be changed
	 * @param quantity new quantity
	 */
	public void updateItemQuantity(Item item, int quantity){
		
		for(OrderItem i:items){
			if(i.getItem().getDescription().equals(item.getDescription())){
				i.setQuantity(quantity);
				break;
			}
		}
	}
	
	/**
	 * Returns sub-total, i. e. price of the order minus the discount.
	 * @return subtotal
	 */
	public BigDecimal getSubtotal(){
		BigDecimal subtotal = new BigDecimal("0.00");
		for(OrderItem i : items){
			subtotal = subtotal.add(i.getOrderItemTotal());
		}
		
		subtotal = subtotal.subtract(getDiscount());
		
		return subtotal;
	}

	/**
	 * Calculates all applicable discounts and returns the one that comes
	 * with biggest profit to the customer.
	 * @return biggest discount
	 */
	public BigDecimal getDiscount(){
		
		ArrayList<BigDecimal> discounts = new ArrayList<BigDecimal>();
		for(Offer o : offers){
			discounts.add(o.calculateDiscount(this));
		}
		
		BigDecimal biggest = new BigDecimal("0.00");
		for(BigDecimal d : discounts){
			if(d.compareTo(biggest)==1){
				biggest = d;
			}
		}
		
		return biggest;
		
	}
	
	/**
	 * Represents the order as a receipt, using HTML tags.
	 * @return String containing the receipt
	 */
	public String getReceipt(){
		
		StringBuilder sb = new StringBuilder();
		
		//first of all, creating nice-looking date string
		SimpleDateFormat df = new SimpleDateFormat("dd MMMMM yyyy,  HH:mm");
		String niceDate = df.format(getDate());
		
		//title
		sb.append("<table width=\"260\"><tr><td><center>AberPizza<br>" +
				"Sales Receipt</center></h2></td></tr></table>");
		sb.append("--------------------------------------------------");
		
		//date and number of order
		sb.append("<table width=\"260\"><tr><td>Date :</td><td>");
		sb.append(niceDate); // here goes proper date 
		sb.append("</td></tr><tr><td width=\"35%\">Order No :</td><td>");
		sb.append(getNumber());
		sb.append("</td></tr></table>");
		sb.append("--------------------------------------------------");
		
		//starting the part with items
		sb.append("<table width=\"260\">");
		
		for(int i=0;i<getItems().size();i++){
			sb.append("<tr><td>");
			sb.append(getItems().get(i).toString()); // here go quantity and name
			sb.append("</td><td width=\"30%\" align=\"right\">");
			sb.append(getItems().get(i).getOrderItemTotal().toString());
			sb.append("</td></tr>");
		}
		
		//and finishing part with items by closing table tag
		sb.append("</table>--------------------------------------------------");
		
		//subtotal
		sb.append("<table width=\"260\"><tr><td align=\"right\">Sub-Total :</td><td width=\"30%\" align=\"right\">");
		sb.append(getSubtotal().add(getDiscount()).toString()); 
		sb.append("</td></tr>");
		
		//discount
		sb.append("<tr><td align=\"right\">Discount :" +
						"</td><td width=\"30%\" align=\"right\">");
		sb.append(getDiscount().toString());
		sb.append("</td></tr>");
		
		//spacer
		sb.append("<td align=\"right\"></td><td width=\"30%\" align=\"right\">-------------</td></tr>");
		
		//to pay 
		sb.append("<tr><td align=\"right\">To Pay :" +
								"</td><td width=\"30%\" align=\"right\">");
		sb.append(getSubtotal().toString());
		sb.append("</td></tr>");
			
		//spacer
		sb.append("<td align=\"right\"></td><td width=\"30%\" align=\"right\">-------------</td></tr>");
		
		//tendered
		sb.append("<tr><td align=\"right\">Tendered :" +
								"</td><td width=\"30%\" align=\"right\">");
		sb.append(getTendered().toString());
		sb.append("</td></tr>");
		
		//change
		sb.append("<tr><td align=\"right\">Change :" +
								"</td><td width=\"30%\" align=\"right\">");
		sb.append(getChange().toString());
		sb.append("</td></tr></table>");
		
		
		return sb.toString();
	}
	

	
	/**
	 * 
	 * @return order items
	 */
	public ArrayList<OrderItem> getOrderItems(){
		return items;
	}

	/**
	 * Checking if two orders are the same; used for testing.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Order other = (Order)obj;
		
		if(other.getCustomerName().equals(customerName))
		if(other.getOrderItems().equals(items))
		return true;
		
		return false;
	}

	/**
	 * @return the items
	 */
	public ArrayList<OrderItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<OrderItem> items) {
		this.items = items;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the tendered
	 */
	public BigDecimal getTendered() {
		return tendered;
	}

	/**
	 * @param tendered the tendered to set
	 */
	public void setTendered(BigDecimal tendered) {
		this.tendered = tendered;
	}

	/**
	 * @return the change
	 */
	public BigDecimal getChange() {
		return tendered.subtract(getSubtotal());
	}

	/**
	 * Dummy method. Left for compatibility with some old .xml files.
	 * @param fakeAnyway
	 */
	public void setChange(BigDecimal fakeAnyway){
		
	}
	
	
}
