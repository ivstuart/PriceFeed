
package tt.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.junit.Test;

import tt.PriceService;
import tt.PriceWriter;
import tt.impl.PriceServiceImpl;
import tt.impl.PriceWriterImpl;

/**
 * @author Ivan
 *
 */
public class PriceWriterImplTest {

	
	@Test
	public void launchAndCancel() {
		
		final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		
		final PriceService priceService = new PriceServiceImpl();
		
		PriceWriter priceWriter = new PriceWriterImpl(scheduler,priceService,
				".BLK", 1);
		priceWriter.start();
		
		assertTrue(priceWriter.isRunning());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		priceWriter.stop();
		assertFalse(priceWriter.isRunning());
	}
}
