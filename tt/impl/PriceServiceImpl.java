package tt.impl;

import java.util.concurrent.ConcurrentHashMap;

import tt.Price;
import tt.PriceService;

/**
 * Service to store prices and get them from memory. Also provides second
 * highest prices.
 * 
 * @author Ivan
 * 
 */
public class PriceServiceImpl implements PriceService {

	private final ConcurrentHashMap<String, PriceInfo> priceData = new ConcurrentHashMap<>();

	@Override
	/**
	 * Also used to initialise the priceData map with PriceInfo.
	 * @param Price
	 */
	public void setPrice(Price price) {

		PriceInfo info = priceData.get(price.getUnderlying());

		if (info == null) {
			info = new PriceInfo();
			priceData.putIfAbsent(price.getUnderlying(), info);
			info = priceData.get(price.getUnderlying());
		}

		info.setNewPrice(price);

	}

	@Override
	/**
	 * Get price.
	 * @param underlying
	 * @return Price can be null when not yet set
	 */
	public Price getPrice(String underlying) {
		Price price = null;

		PriceInfo info = priceData.get(underlying);

		if (info == null) {
			return null;
		}

		price = new PriceImpl(underlying, info.getLatestPrice());

		return price;
	}

	@Override
	/**
	 * Get second highest price
	 * @param underlying
	 * @return Price can be null when not yet set
	 */
	public Price getSecondHighestPrice(String underlying) {
		Price price = null;

		PriceInfo info = priceData.get(underlying);

		if (info == null) {
			return null;
		}

		price = new PriceImpl(underlying, info.getSecondHighestPrice());

		return price;

	}

	/**
	 * Wrapper to contain information about a single underlying
	 * 
	 * @author Ivan
	 * 
	 */
	class PriceInfo {
		public Double getLatestPrice() {
			return latestPrice;
		}

		/**
		 * Set latest price and also highest and second highest prices so far.
		 * 
		 * @param price
		 */
		public void setNewPrice(Price price) {

			if (price.isPriceHigher(highestPrice)) {
				double previousHighestPrice = highestPrice;
				highestPrice = price.getPrice();
				if (secondHighestPrice < previousHighestPrice) {
					secondHighestPrice = previousHighestPrice;
				}
			} else {

				if (price.isPriceHigher(secondHighestPrice)) {
					secondHighestPrice = price.getPrice();
				}
			}

			latestPrice = price.getPrice();

		}

		public void setLatestPrice(Double latestPrice) {
			this.latestPrice = latestPrice;
		}

		public Double getHighestPrice() {
			return highestPrice;
		}

		public void setHighestPrice(Double highestPrice) {
			this.highestPrice = highestPrice;
		}

		public Double getSecondHighestPrice() {
			return secondHighestPrice;
		}

		public void setSecondHighestPrice(Double secondHighestPrice) {
			this.secondHighestPrice = secondHighestPrice;
		}

		private Double latestPrice = null;
		private Double highestPrice = 0.0;
		private Double secondHighestPrice = 0.0;

		@Override
		public String toString() {
			return "PriceInfo [latestPrice=" + latestPrice + ", highestPrice="
					+ highestPrice + ", secondHighestPrice="
					+ secondHighestPrice + "]";
		}

	}

}
