package tt.impl;

import static java.util.concurrent.TimeUnit.MINUTES;

import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

import tt.Price;
import tt.PriceService;
import tt.Report;

/**
 * Report the second highest price for each index over the period
 * 
 * @author Ivan
 * 
 */
public class SecondHighestPricesReport implements Runnable, Report {

	
	private final PriceService priceService;
	private final Set<String> underlyings;	
	
	public SecondHighestPricesReport(ScheduledExecutorService scheduler,PriceService priceService,Set<String> underlyings) {
		scheduler.schedule(this,	1, MINUTES);
		this.priceService = priceService;
		this.underlyings = underlyings;
	}
	/* (non-Javadoc)
	 * @see tt.Report#displayToConsole()
	 */
	@Override
	public void displayToConsole() {
		System.out.println("Second Highest Prices");
		System.out.println("Underlying Price");

		for (String underlying : underlyings) {
			Price price = priceService.getSecondHighestPrice(underlying);
			if (price != null) {
				System.out.println(String.format("%s %s",
						price.getUnderlying(), price.getPrice()));
			}
		}
		System.out.println("End of report");
	}
	
	/* (non-Javadoc)
	 * @see tt.Report#run()
	 */
	@Override
	public void run() {
		displayToConsole();
		
	}

}
