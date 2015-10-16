package tt;

/*
 * TODO Get data from the PriceSource and put it into an implementation of PriceService
 */
public interface PriceWriter {

    void start();

    void stop();

	/**
	 * 
	 */
	boolean isRunning();
}
