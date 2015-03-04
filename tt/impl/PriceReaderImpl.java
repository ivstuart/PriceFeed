package tt.impl;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import tt.Price;
import tt.PriceReader;
import tt.PriceService;

/**
 * a reader that gets data from the service and stores it in an in memory via
 * the service as well as printing out to the console
 * 
 * @author Ivan
 * 
 */
public class PriceReaderImpl implements PriceReader, Runnable {

	private final ScheduledExecutorService scheduler;
	ScheduledFuture<?> priceFeedSchedule;

	private final PriceService priceService;
	private final String underlying;
	private final int frequencyInSeconds;
	private final int initialDelay = 0;
	private int secondsCounter = 0;

	/**
	 * Constructor
	 * 
	 * @param priceService
	 * @param underlying
	 * @param frequencyInSeconds
	 */
	public PriceReaderImpl(ScheduledExecutorService scheduler,PriceService priceService, String underlying,
			int frequencyInSeconds) {
		this.scheduler = scheduler;
		this.priceService = priceService;
		this.underlying = underlying;
		this.frequencyInSeconds = frequencyInSeconds;
	}

	@Override
	/**
	 * Start and schedule the reader to run every second
	 */
	public void start() {
		priceFeedSchedule = scheduler.scheduleAtFixedRate(this, initialDelay,
				frequencyInSeconds, SECONDS);
	}

	@Override
	/**
	 * Request to stop scheduled reader
	 */
	public void stop() {
		priceFeedSchedule.cancel(true);
	}

	@Override
	/**
	 * Get price and display to console.
	 */
	public void run() {

		Price price = priceService.getPrice(underlying);

		if (price != null) {
			System.out.println(String.format("%s %s %s", secondsCounter,
					price.getUnderlying(), price.getPrice()));
		}
		secondsCounter++;

	}

	/**
	 * Display the header for the report
	 */
	public static void displayReportHeader() {
		System.out.println("sec desc");
	}
}
