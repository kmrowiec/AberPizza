/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.ProductType;
import uk.ac.aber.dcs.cs12420.aberpizza.data.StoreItem;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

/**
 * Tests Till.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 */
public class TestTill {

	Till till = new Till();
	Order order1, order2;
	StoreItem[] testItems = new StoreItem[5];

	/**
	 * Setup method, that creates two orders and add its to till,
	 * then 5 StoreItems is created in a loop, and also added to the till.
	 * In this method addOrder and addStoreItem are tested - if rest of the test 
	 * is working properly, that means those two methods are correct.
	 */
	@Before
	public void setup(){
		order1 = new Order();
		order1.addItem(new StoreItem("Pizza", "2.00"), 2);
		
		order2 = new Order();
		order2.addItem(new StoreItem("Beer", "2.00"), 3);
		
		till.addOrder(order1);
		till.addOrder(order2);
		
		//initializing store items
		//and adding them to the till
		//as pizzas, sides, and drinks
		for(int i=0;i<5;i++){
			testItems[i] = new StoreItem("Item", "2.00");
			testItems[i].setProductType(ProductType.PIZZA);
			till.addStoreItem(testItems[i]);
			testItems[i].setProductType(ProductType.SIDE);
			till.addStoreItem(testItems[i]);
			testItems[i].setProductType(ProductType.DRINK);
			till.addStoreItem(testItems[i]);
		}
	}
	/**
	 * Tests adding order to the till
	 * Test method for {@link uk.ac.aber.dcs.cs12420.aberpizza.data.Till#addOrder(uk.ac.aber.dcs.cs12420.aberpizza.data.Order)}.
	 */
	@Test
	public void testAddOrder() {
		
		assertEquals("Order 1 added incorrectly", order1, till.getOrders().get(0));
		assertEquals("Order 2 added incorrectly", order2, till.getOrders().get(1));
	}

	/**
	 * Test getting total for the day.
	 * Test method for {@link uk.ac.aber.dcs.cs12420.aberpizza.data.Till#getTotalForDay()}.
	 */
	@Test
	public void testGetTotalForDay() {
		
		BigDecimal expectedTotal = new BigDecimal("10.00");
		assertEquals("Total incorrect", expectedTotal, till.getTotalForDay());
	}

	/**
	 * Test saving and loading the till.
	 * Saves the predefined till, then loads it to new object
	 * and checks if those two are equal.
	 * 
	 * NOTE : the method removes settings.xml 
	 * 
	 * Test method for {@link uk.ac.aber.dcs.cs12420.aberpizza.data.Till#save()}.
	 * Test method for {@link uk.ac.aber.dcs.cs12420.aberpizza.data.Till#load()}.
	 */
	@Test
	public void testSaveAndLoad() throws IOException{
		
		till.save();	
		Till loadedTill = Till.load();
		assertEquals("Loading failed", till, loadedTill);
	
		File file = new File("settings.xml");
		file.delete();
	}


}
