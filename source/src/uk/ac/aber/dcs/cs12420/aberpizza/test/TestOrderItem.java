package uk.ac.aber.dcs.cs12420.aberpizza.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import uk.ac.aber.dcs.cs12420.aberpizza.data.OrderItem;
import uk.ac.aber.dcs.cs12420.aberpizza.data.ProductType;
import uk.ac.aber.dcs.cs12420.aberpizza.data.StoreItem;

/**
 * Tests OrderItem.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
public class TestOrderItem {

	/**
	 * Tests all setters and getters methods.
	 */
	@Test
	public void testGettersAndSetters(){
		OrderItem item = new OrderItem();
		item.setQuantity(5);
		StoreItem storeItem = new StoreItem("Pizza", "5.00", ProductType.PIZZA);
		item.setItem(storeItem);
		assertEquals("Quantity set incorrectly", 5, item.getQuantity());
		assertEquals("Item set incorrectly", storeItem, item.getItem());
	}
	
	/**
	 * Tests the constructor.
	 */
	@Test
	public void testConstructor(){
		OrderItem item = new OrderItem(new StoreItem("Pizza", "1.99"), 3);
		assertEquals("Quantity set incorrectly", 3, item.getQuantity());
		assertEquals("Item set incorrectly", new StoreItem("Pizza", "1.99"), item.getItem());
	}

	/**
	 * Tests getting total for order item.
	 */
	@Test
	public void testGetOrderItemTotal() {
		OrderItem orderItem = new OrderItem(new StoreItem("Pizza", "1.99"), 3);
		assertEquals("Total incorrect", new BigDecimal("5.97"), orderItem.getOrderItemTotal());
	}
	
	/**
	 * Tests equals method.
	 */
	@Test
	public void testEquals(){
		OrderItem item1 = new OrderItem(new StoreItem("Pizza", "1.99"), 3);
		OrderItem item2 = new OrderItem(new StoreItem("Pizza", "1.99"), 3);
		assertTrue("Should be true", item1.equals(item2));
	}
	
	/**
	 * Tests toString method.
	 */
	@Test 
	public void testToString(){
		OrderItem item = new OrderItem(new StoreItem("Pizza", "1.99"), 3);
		assertEquals("ToString is wrong", "3 x Pizza", item.toString());
	}

}
