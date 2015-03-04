package tt;
public interface Price {
    String getUnderlying();

    double getPrice();

	boolean isPriceHigher(double highestPrice);
    
}
