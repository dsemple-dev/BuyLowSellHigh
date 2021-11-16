import java.util.Arrays;
import java.util.List;

public class buyLowSellHigh {
	
	private List<Float> currentPrices;
	
	public buyLowSellHigh(Float[] priceArray)
	{
        currentPrices = Arrays.asList(priceArray); 
	}
	
	public String bestDays()
	{
		if (currentPrices.isEmpty())
		{
			return ("Empty List");
		}
		if (currentPrices.size() < 2)
		{
			return ("Dataset too small");
		}
		 
		Float max_diff = 0.0f;
        int sellIndex = 0;
        int buyIndex = 0;
        int newBuyIndex = 0;
        Float buyPrice = currentPrices.get(buyIndex);
        int i;
        for (i = 1; i < currentPrices.size(); i++)
        {
            if (currentPrices.get(i) - buyPrice > max_diff)
            {
                max_diff = currentPrices.get(i) - buyPrice;
                sellIndex = i;
                buyIndex = newBuyIndex;
            }
            if (currentPrices.get(i) < buyPrice)
            {
            	buyPrice = currentPrices.get(i);
            	newBuyIndex = i;
            }
        }
        if (buyIndex == sellIndex)
        {
        	return "Hold onto shares longer";
        }
        return dayPrice(buyIndex) +","+ dayPrice(sellIndex);
	}
	
	private String dayPrice(int dayIndex)
	{
		return String.valueOf(dayIndex+1) +"("+ currentPrices.get(dayIndex).toString()+")";
	}
}
