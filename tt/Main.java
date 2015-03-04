package tt;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import tt.impl.PriceReaderImpl;
import tt.impl.PriceServiceImpl;
import tt.impl.PriceWriterImpl;
import tt.impl.SecondHighestPricesReport;

public class Main {

	private static final Map<String, Integer> UNDERLYINGS;
	static {
		UNDERLYINGS = new HashMap<String, Integer>(); // instrument name, update
														// frequency in seconds
		UNDERLYINGS.put(".FTSE", 10);
		UNDERLYINGS.put(".DJI", 5);
		UNDERLYINGS.put(".NDX", 5);
		UNDERLYINGS.put(".GSPC", 7);
	}

	public static void main(final String[] args) {

		final PriceService priceService = new PriceServiceImpl();
        
	    final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		
		// Producer(s)
		for (Entry<String, Integer> underlying : UNDERLYINGS.entrySet()) {

			PriceWriter priceWriter = new PriceWriterImpl(scheduler,priceService,
					underlying.getKey(), underlying.getValue());
			priceWriter.start();
		}

		// Consumer(s)
		PriceReaderImpl.displayReportHeader();
		for (Entry<String, Integer> underlying : UNDERLYINGS.entrySet()) {

			PriceReader priceReader = new PriceReaderImpl(scheduler,priceService,
					underlying.getKey(), 1);
			priceReader.start();
		}

		// Report
		new SecondHighestPricesReport(scheduler,priceService, UNDERLYINGS.keySet());

	}
}
