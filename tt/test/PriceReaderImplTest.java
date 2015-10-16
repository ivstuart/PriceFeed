package tt.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.junit.Test;

import tt.PriceReader;
import tt.PriceService;
import tt.impl.PriceImpl;
import tt.impl.PriceReaderImpl;
import tt.impl.PriceServiceImpl;

/**
 * @author Ivan
 *
 */
public class PriceReaderImplTest {

	@Test
	public void testPriceReaderLaunchEmpty() {
		final ScheduledExecutorService scheduler = Executors
				.newSingleThreadScheduledExecutor();

		final PriceService priceService = new PriceServiceImpl();
		PriceReader priceReader = new PriceReaderImpl(scheduler, priceService,
				".VOD", 1);
		priceReader.start();
		priceReader.stop();
	}

	@Test
	public void testPriceReaderLaunch() {
		final ScheduledExecutorService scheduler = Executors
				.newSingleThreadScheduledExecutor();

		final PriceService priceService = new PriceServiceImpl();
		priceService.setPrice(new PriceImpl(".VOD",15));
		PriceReader priceReader = new PriceReaderImpl(scheduler, priceService,
				".VOD", 1);
		priceReader.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		priceReader.stop();
	}

}
