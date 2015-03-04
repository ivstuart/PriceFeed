package tt.impl;

import tt.Price;

/**
 * Threadsafe implementation of Price
 * @author Ivan
 * 
 */
public class PriceImpl implements Price {

	
	final private String underlying;
	final private double price;

	/**
	 * Constructor
	 * @param underlying
	 * @param price
	 */
	public PriceImpl(String underlying, double price) {
		this.underlying = underlying;
		this.price = price;
	}
	
	@Override
	public String getUnderlying() {
		return underlying;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "PriceImpl [underlying=" + underlying + ", price=" + price + "]";
	}

	@Override
	public boolean isPriceHigher(double highestPrice) {
		return price > highestPrice;
	}

}
