package uk.ac.aber.dcs.cs12420.aberpizza.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import uk.ac.aber.dcs.cs12420.aberpizza.data.ProductType;
import uk.ac.aber.dcs.cs12420.aberpizza.data.StoreItem;

/**
 * Tests StoreItem.
 * StoreItem is not very complex, so the test class is short.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
public class TestStoreItem {

	/**
	 * Tests both constructors.
	 */
	@Test
	public void testConstructor() {
		
		// testing one constructor
		StoreItem storeItem = new StoreItem("Pizza", "6.99");
		assertEquals("incorrect description returned", "Pizza", storeItem.getDescription() );
		assertEquals("incorrect price returned", new BigDecimal("6.99"), storeItem.getPrice() );
		
		//testing second constructor
		storeItem = new StoreItem("Pizza", "6.99", ProductType.PIZZA);
		assertEquals("incorrect description returned", "Pizza", storeItem.getDescription() );
		assertEquals("incorrect price returned", new BigDecimal("6.99"), storeItem.getPrice() );
		assertEquals("ProductType incorrect", ProductType.PIZZA, storeItem.getProductType());
	
	}
	
	/**
	 * Tests setting and getting product type.
	 */
	@Test
	public void testGettingAndSettingProductType(){
		StoreItem item = new StoreItem();
		item.setProductType(ProductType.PIZZA);
		assertEquals("ProductType set incorrectly", ProductType.PIZZA, item.getProductType());
	}
	
	/**
	 * Tests setting and getting price and description.
	 */
	@Test
	public void testGettingAndSettingPriceAndDescription() {
		
		StoreItem storeItem = new StoreItem();
		
		storeItem.setDescription("Small Pizza");
		storeItem.setPrice(new BigDecimal("2.99"));
		
		assertEquals("incorrect description setter", "Small Pizza", storeItem.getDescription() );
		assertEquals("incorrect price setter", new BigDecimal("2.99"), storeItem.getPrice() );
	}
	
	/**
	 * Tests equals method.
	 */
	@Test
	public void testEquals(){
		
		StoreItem item1 = new StoreItem("Pizza", "6.99");
		StoreItem item2 = new StoreItem("Pizza", "6.99");
		StoreItem item3 = new StoreItem("Beer", "1.99");
		assertTrue("Should return true", item1.equals(item2));
		assertFalse("Should return false", item1.equals(item3));
	}

}
