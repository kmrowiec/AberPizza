package uk.ac.aber.dcs.cs12420.aberpizza.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Item;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.ProductType;
import uk.ac.aber.dcs.cs12420.aberpizza.data.StoreItem;

/**
 * Test all classes that implement Offer interface and their interaction with Order.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
public class TestDiscounts {

	Order testOrder;
	Item large1 = new StoreItem("Large pepperoni", "8.00");
	Item large2 = new StoreItem("Large BBQ", "9.00");
	Item large3 = new StoreItem("Large Expensive Pizza", "11.00");
	Item large4 = new StoreItem("Large Another", "9.00");
	Item side = new StoreItem("Side something", "2.00", ProductType.SIDE);
	Item drink = new StoreItem("A drink", "2.00", ProductType.DRINK);
	
	@Before
	public void clean(){
		testOrder = new Order();
	}
	
	/**
	 * Testing if bigger discount will be chosen correctly.
	 */
	@Test
	public void testBiggerDiscount(){
		testOrder.addItem(large1, 1);
		testOrder.addItem(large2, 1);
		testOrder.addItem(large3, 1);
		testOrder.addItem(side, 1);
		testOrder.addItem(drink, 1);
		
		assertEquals("Bigger discount selected incorrectly", new BigDecimal("8.00"), testOrder.getDiscount());
	}
	
	/**
	 * Testing offer with Large Pizza, Side and Drink.
	 */
	@Test
	public void testPizzaSideAndDrinkOffer() {
		testOrder.addItem(large1, 1);
		testOrder.addItem(side, 1);
		testOrder.addItem(drink, 1);
		
		assertEquals("Discount incorrect", new BigDecimal("2.40"), testOrder.getDiscount());
		assertEquals("Subtotal incorrect", new BigDecimal("9.60"), testOrder.getSubtotal());
	}
	
	/**
	 * Testing offer with three lagre pizzas.
	 */
	@Test
	public void testThreeLargePizzasOffer() {
		
		//Simpliest case, just 3 different large pizzas in the order.
		testOrder.addItem(large1, 1);
		testOrder.addItem(large2, 1);
		testOrder.addItem(large3, 1);
		assertEquals("Discount incorrect", new BigDecimal("8.00"), testOrder.getDiscount());
		assertEquals("Subtotal incorrect", new BigDecimal("20.00"), testOrder.getSubtotal());
		
		//Another case, only two different large pizzas.
		clean();
		testOrder.addItem(large1, 1);
		testOrder.addItem(large2, 1);
		assertEquals("Discount should be 0.00", new BigDecimal("0.00"), testOrder.getDiscount());
		assertEquals("Subtotal incorrect", new BigDecimal("17.00"), testOrder.getSubtotal());
		
		//Now with four different large pizzas.
		clean();
		testOrder.addItem(large1, 1);
		testOrder.addItem(large2, 1);
		testOrder.addItem(large3, 1);
		testOrder.addItem(large4, 1);
		assertEquals("Discount incorrect", new BigDecimal("8.00"), testOrder.getDiscount());
		assertEquals("Subtotal incorrect", new BigDecimal("29.00"), testOrder.getSubtotal());
		
		//Now with quantity greater than 1.
		clean();
		testOrder.addItem(large1, 3);
		testOrder.addItem(large2, 5);
		assertEquals("Discount incorrect", new BigDecimal("8.00"), testOrder.getDiscount());
		assertEquals("Subtotal incorrect", new BigDecimal("61.00"), testOrder.getSubtotal());
	}
}
