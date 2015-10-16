package tt.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import tt.PriceService;
import tt.impl.PriceImpl;
import tt.impl.PriceServiceImpl;

public class PriceServiceImplTest {

	final static double DELTA = 0.000001;

	@Test
	public void emptyPrice() {
		PriceService priceService = new PriceServiceImpl();
		
		assertNull(priceService.getPrice(".EMPTY"));
	}

	@Test
	public void emptySecondHighestPrice() {
		PriceService priceService = new PriceServiceImpl();
		
		assertNull(priceService.getSecondHighestPrice(".EMPTY"));
	}
	
	@Test
	public void setPriceThenGetLatestPrice() {
		PriceService priceService = new PriceServiceImpl();
		
		String underlying = ".FTSE";
		priceService.setPrice(new PriceImpl(underlying,3.0));
	
		priceService.setPrice(new PriceImpl(underlying,7.0));
		
		double latestPrice = priceService.getPrice(underlying).getPrice();
		
		assertEquals("Price",7.0,latestPrice, DELTA);
	}
	
	@Test
	public void setPricesThenCheckSecondHighest() {
		PriceService priceService = new PriceServiceImpl();
		
		String underlying = ".FTSE";
		priceService.setPrice(new PriceImpl(underlying,3.0));
	
		priceService.setPrice(new PriceImpl(underlying,7.0));
	
		priceService.setPrice(new PriceImpl(underlying,5.0));	
		
		assertEquals("Price",5.0,priceService.getSecondHighestPrice(underlying).getPrice(), DELTA);
	}
	
	@Test
	public void setMorePricesThenCheckSecondHighest() {
		PriceService priceService = new PriceServiceImpl();
		priceService.setPrice(new PriceImpl(".FTSE",3.0));
		priceService.setPrice(new PriceImpl(".FTSE",13.0));
		priceService.setPrice(new PriceImpl(".FTSE",5.0));
		priceService.setPrice(new PriceImpl(".FTSE",15.0));
		
		assertEquals("Price",13.0,priceService.getSecondHighestPrice(".FTSE").getPrice(),DELTA);

	}
}
