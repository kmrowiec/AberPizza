package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

/**
 * Interface that represents single item for sale.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 */
public interface Item {
	/**
	 * Returns price of the item.
	 * @return price of the item
	 */
	public BigDecimal getPrice();
	/**
	 * Sets price of the item.
	 * @param price price to be set.
	 */
	public void setPrice(BigDecimal price);
	/**
	 * Returns description.
	 * @return description
	 */
	public String getDescription();
	/**
	 * Sets description.
	 * @param description to be set.
	 */
	public void setDescription(String description);
	/**
	 * Returns type of the product
	 * @return type of the product
	 */
	public ProductType getProductType();
	/**
	 * Sets type of the product
	 * @param pt ProductType to be set.
	 */
	public void setProductType(ProductType pt);

}
