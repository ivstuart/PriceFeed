package tt;
public interface PriceService {

    void setPrice(Price price);

    Price getPrice(String underlying);

	Price getSecondHighestPrice(String underlying);
}
