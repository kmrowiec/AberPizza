/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

/**
 * Represents an offer. The interface contains only one method that calculates 
 * the amount of the discount for given offer.
 * 
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 * 
 */
public interface Offer {
	/**
	 * Calculates an amount of the discount that applies to order given as parameter.
	 * @param order Order that discount should be applied to.
	 * @return Amount of the discount.
	 */
	public BigDecimal calculateDiscount(Order order);
}
