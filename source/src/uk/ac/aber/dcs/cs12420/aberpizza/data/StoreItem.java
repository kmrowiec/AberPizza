/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

/** 
 * Implementation of Item interface.
 * It consists of description (e. g. "Beer"), and price (e. g. "2.99", in BigDecimal).
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 */
public class StoreItem implements Item {
	
	private BigDecimal price;
	private String description;
	private ProductType productType;

	/**
	 * Empty constructor,
	 * needed for the class to be XML-serializable.
	 */
	public StoreItem(){
		
	}
	/**
	 * Constructor for Store item. Although price is held as BigDecimal, the constructor takes String, 
	 * to improve purity of the code. ProductType, in this case, is set to NON_SPECIFIED.
	 * @param description Items description.
	 * @param price Price of the item.
	 */
	public StoreItem(String description, String price){
		this.description = description;
		this.price = new BigDecimal(price);
		productType = ProductType.NOT_SPECIFIED;
	}
	
	/**
	 * Another constructor. In addition to the previous one, it also takes ProductType.
	 * @param description Items description.
	 * @param price Price of the item.
	 * @param pt Type of the product.
	 */
	public StoreItem(String description, String price, ProductType pt){
		this(description, price);
		productType = pt;
	}
	
	/**
	 * @see uk.ac.aber.dcs.cs12420.aberpizza.data.Item#getPrice()
	 */
	@Override
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @see uk.ac.aber.dcs.cs12420.aberpizza.data.Item#setPrice(java.math.BigDecimal)
	 */
	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;

	}


	/**
	 * @see uk.ac.aber.dcs.cs12420.aberpizza.data.Item#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * @see uk.ac.aber.dcs.cs12420.aberpizza.data.Item#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;

	}
	/**
	 * Equals method that overrides method from Object class.
	 * It is mostly for test, so that Till.equals(Object obj) could work properly.
	 * However, it may also be useful later in the program.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		StoreItem other = (StoreItem) obj;
		if(other.getDescription().equals(description))
		if(other.getPrice().equals(price))
		return true;
		
		return false;
	}
	/**
	 * @return the productType
	 */
	@Override
	public ProductType getProductType() {
		return productType;
	}
	/**
	 * @param productType the productType to set
	 */
	@Override
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	
	@Override
	public String toString(){
		return description + "  " + price.toString();
	}

}
