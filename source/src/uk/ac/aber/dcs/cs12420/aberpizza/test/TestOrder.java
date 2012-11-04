package uk.ac.aber.dcs.cs12420.aberpizza.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Item;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.StoreItem;

/**
 * Tests Order class.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
public class TestOrder {

	/*
	 *  NOTE:
	 *  public String getReceipt() is not tested here, it would be pointless.
	 *  Moreover, getDiscount() is not tested here, 
	 *  instead it is tested explicitly in testDiscounts class.
	 *  
	 */
	
	Order order;
	
	@Before
	public void before(){
		order  = new Order();
	}
	
	/**
	 * Tests setting and getting customer name.
	 */
	@Test
	public void testGetAndSetCustomerName() {
		order.setCustomerName("client");
		assertEquals("Customer name is not set properly", "client", order.getCustomerName());
	}
	
	/**
	 * Tests setting and getting datee.
	 */
	@Test
	public void testGetAndSetDate(){
		Date testDate = new Date();
		order.setDate(testDate);
		assertEquals("Date is not set properly", testDate, order.getDate());
	}
	
	/**
	 * Tests setting and getting order number.
	 */
	@Test
	public void testGetAndSetNumber(){
		String num = "1/23456";
		order.setNumber(num);
		assertEquals("Number is not set properly", num, order.getNumber());
	}
	
	/**
	 * Tests adding item to the order and getting sub-total.
	 */
	@Test
	public void testAddItemAndSubtotal() {
		
		StoreItem storeItem1 = new StoreItem("Pizza", "2.00");
		StoreItem storeItem2 = new StoreItem("Beer", "2.50");
		
		//adding items to order
		order.addItem(storeItem1, 1);
		order.addItem(storeItem2, 2);
		
		BigDecimal subtotal = order.getSubtotal();
		BigDecimal expected = new BigDecimal("7.00");
		
		assertEquals("Subtotal incorrect", expected, subtotal);
	}
	
	/**
	 * Tests setting tendered and calculating change.
	 */
	@Test
	public void testTenderedAndChange(){
		order.addItem(new StoreItem("Pizza 1", "2.00"),2);
		order.addItem(new StoreItem("Pizza 2", "2.00"),2);
		
		order.setTendered(new BigDecimal("10.00"));
		
		assertEquals("Tendered is not set properly", new BigDecimal("10.00"), order.getTendered());
		assertEquals("change is not working properly", new BigDecimal("2.00"), order.getChange());
	}
	
	/**
	 * Tests updating quantity of an item.
	 */
	@Test
	public void testUpdateItemQuantity(){
		Item testItem = new StoreItem("Some Pizza", "2.00");
		order.addItem(testItem,1);
		order.updateItemQuantity(testItem, 5);
		assertEquals("Quantity set incorrectly", 5, order.getItems().get(0).getQuantity());
	}



}
