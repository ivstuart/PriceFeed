
package tt.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.junit.Test;

import tt.PriceService;
import tt.impl.PriceImpl;
import tt.impl.PriceServiceImpl;
import tt.impl.SecondHighestPricesReport;

/**
 * @author Ivan
 *
 */
public class SecondHighestPricesReportTest {
	private static final Map<String, Integer> UNDERLYINGS;
	static {
		UNDERLYINGS = new HashMap<String, Integer>(); // instrument name, update
														// frequency in seconds
		UNDERLYINGS.put(".FTSE", 10);
		UNDERLYINGS.put(".DJI", 5);
		UNDERLYINGS.put(".NDX", 5);
		UNDERLYINGS.put(".GSPC", 7);
	}
	@Test
	public void testDisplay() {
		
		final PriceService priceService = new PriceServiceImpl();
        
	    final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		
		for (Entry<String, Integer> underlying : UNDERLYINGS.entrySet()) {

			priceService.setPrice(new PriceImpl(underlying.getKey(), underlying.getValue()));
			priceService.setPrice(new PriceImpl(underlying.getKey(), underlying.getValue())); // Set 2nd highest price.
		}
	    
		SecondHighestPricesReport report = new SecondHighestPricesReport(
				scheduler, priceService, UNDERLYINGS.keySet());
		
		report.displayToConsole();
	}
}
