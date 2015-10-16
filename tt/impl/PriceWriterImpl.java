package tt.impl;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import tt.Price;
import tt.PriceService;
import tt.PriceSource;
import tt.PriceWriter;

/**
 * Price writer responsible for updated price from price source to price
 * service. Also displays to console when a price have been updated.
 * 
 * @author Ivan
 * 
 */
public class PriceWriterImpl implements PriceWriter, Runnable {

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
	public PriceWriterImpl(ScheduledExecutorService scheduler,
			PriceService priceService, String underlying,
			Integer frequencyInSeconds) {
		this.scheduler = scheduler;
		this.priceService = priceService;
		this.underlying = underlying;
		this.frequencyInSeconds = frequencyInSeconds;
	}

	@Override
	/**
	 * Start schedule for this writer to run every second
	 */
	public void start() {
		priceFeedSchedule = scheduler.scheduleAtFixedRate(this, initialDelay,
				1, SECONDS);
	}

	@Override
	/**
	 * Request to stop this scheduled writer
	 */
	public void stop() {
		priceFeedSchedule.cancel(true);
	}

	@Override
	/**
	 * Gets the price from price source 
	 * and displays it has been updated console at a required frequency.
	 * Then sets that price via the price service.
	 */
	public void run() {

		if (secondsCounter % frequencyInSeconds == 0) {
			Price price = new PriceImpl(underlying, PriceSource.getPrice());
			priceService.setPrice(price);
			System.out.println(String.format("%s %s updated", secondsCounter,
					price.getUnderlying()));
		}

		secondsCounter++;

	}

	/* (non-Javadoc)
	 * @see tt.PriceWriter#isRunning()
	 */
	@Override
	public boolean isRunning() {
		return !priceFeedSchedule.isCancelled();
	}

}
