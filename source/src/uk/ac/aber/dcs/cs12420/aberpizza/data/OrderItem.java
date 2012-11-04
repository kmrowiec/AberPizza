/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

/**
 * One item in the order, i. e. an Item and quantity.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
public class OrderItem {
	
	private Item item;
	private int quantity;
	
	public OrderItem(){
		
	}
	
	public OrderItem(Item item, int quantity){
		this.item = item;
		this.quantity = quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * 
	 * @return Quantity of the order item
	 */
	public int getQuantity(){
		return this.quantity;
	}
	
	/**
	 * 
	 * @return Total value of order item, i.e. price multiplied by quantity
	 */
	public BigDecimal getOrderItemTotal(){
		return item.getPrice().multiply(new BigDecimal(quantity));
	}

	/**
	 * Used for testing.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		OrderItem other = (OrderItem)obj;
		
		if(other.getItem().equals(this.getItem()))
		if(other.getQuantity()==this.getQuantity())
		return true;
		
		return false;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Integer.toString(quantity) + " x " + item.getDescription();
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	
	
	
	

}
