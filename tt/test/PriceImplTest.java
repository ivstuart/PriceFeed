package tt.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tt.impl.PriceImpl;

/**
 * @author Ivan
 *
 */
public class PriceImplTest {

	
	@Test
	public void testLowerPrice() {
		PriceImpl price = new PriceImpl(".FTSE", 13);
		
		assertFalse("Price is lower",price.isPriceHigher(20));
		
	}
	
	@Test
	public void testPriceSame() {
		PriceImpl price = new PriceImpl(".FTSE", 13);
		
		assertFalse("Price is same",price.isPriceHigher(13));
		
	}
	
	@Test
	public void testHigherPrice() {
		PriceImpl price = new PriceImpl(".FTSE", 13);
		
		assertTrue("Price is higher",price.isPriceHigher(12));
		
	}
}
