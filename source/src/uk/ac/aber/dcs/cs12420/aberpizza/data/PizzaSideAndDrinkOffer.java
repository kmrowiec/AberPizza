/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

/**
 * Example offer.
 * If a customer buys at least one large pizza, one side and one drink,
 * 20% discount for those three items is applied.
 * 
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 */
public class PizzaSideAndDrinkOffer implements Offer {

	/**
	 * @see uk.ac.aber.dcs.cs12420.aberpizza.data.Offer#calculateDiscount(uk.ac.aber.dcs.cs12420.aberpizza.data.Order)
	 */
	@Override
	public BigDecimal calculateDiscount(Order order) {
		
		//Variables representing parts of the Offer.
		Item largePizza = null, side = null, drink = null;
		
		//Scanning for large pizza in the order.
		//First found pizza is passed to largePizza and the loop finishes.
		for(OrderItem o : order.getOrderItems()){
			if(o.getItem().getDescription().contains("Large")){
				largePizza = o.getItem();
				break;
			}
		}
		
		//The same for side.
		for(OrderItem o : order.getOrderItems()){
			if(o.getItem().getProductType()==ProductType.SIDE){
				side = o.getItem();
				break;
			}
		}
		
		//And drink.
		for(OrderItem o : order.getOrderItems()){
			if(o.getItem().getProductType()==ProductType.DRINK){
				drink = o.getItem();
				break;
			}
		}
		
		//Checks if all three items were found.
		//If not, the discount is 0.00
		if(largePizza==null||side==null||drink==null) return new BigDecimal("0.00");
		
		//If the discount should be applied, it equals 
		//20% of total price of those three items.
		BigDecimal discount = largePizza.getPrice()
				.add(side.getPrice())
				.add(drink.getPrice())
				.multiply(new BigDecimal("0.20"))
				.setScale(2, BigDecimal.ROUND_DOWN);
		
		return discount;
		
	}

}
