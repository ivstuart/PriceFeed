package tt.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tt.PriceService;
import tt.impl.PriceImpl;
import tt.impl.PriceServiceImpl;

public class PriceServiceTest {

	final static double DELTA = 0.000001;
	
	
	@Test
	public void setPricesThenCheckSecondHighest() {
		PriceService priceService = new PriceServiceImpl();
		priceService.setPrice(new PriceImpl(".FTSE",3.0));
	
		priceService.setPrice(new PriceImpl(".FTSE",7.0));
	
		priceService.setPrice(new PriceImpl(".FTSE",5.0));
		
		
		assertEquals("Price",5.0,priceService.getSecondHighestPrice(".FTSE").getPrice(), DELTA);
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
