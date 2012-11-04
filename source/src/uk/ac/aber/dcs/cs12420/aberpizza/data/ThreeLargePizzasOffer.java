/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Example offer.
 * If the order contains 3 or more large pizzas, 
 * the cheapest pizza of those is for free. 
 * 
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
public class ThreeLargePizzasOffer implements Offer {

	/**
	 * @see uk.ac.aber.dcs.cs12420.aberpizza.data.Offer#calculateDiscount(uk.ac.aber.dcs.cs12420.aberpizza.data.Order)
	 */
	@Override
	public BigDecimal calculateDiscount(Order order) {
		
		//This will hold all large pizzas contained by order.
		ArrayList<Item> largePizzas = new ArrayList<Item>();
		
		//Searching for large pizzas in the order.
		//If found, the item is added to largePizzas.
		//If quantity is greater than 1, multiple items will be added.
		for(OrderItem i : order.getOrderItems()){
			if(i.getItem().getDescription().contains("Large")){
				for(int j = 0; j<i.getQuantity();j++){
					largePizzas.add(i.getItem());
				}
			}
		}
		
		//If there are less than 3 large pizzas, returned discount is 0.00
		if(largePizzas.size()<3) return new BigDecimal("0.00");
		
		//Finding cheapest pizza 
		Item cheapest = largePizzas.get(0);
		for(Item pizza : largePizzas){
			if(pizza.getPrice().compareTo(cheapest.getPrice())==-1){
				cheapest = pizza;
			}
		}
		
		//Value of the discount is price of the cheapest pizza.
		return cheapest.getPrice();
	}

}
