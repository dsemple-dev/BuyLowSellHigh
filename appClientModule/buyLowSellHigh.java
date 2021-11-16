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
		Float max_diff = currentPrices.get(1) - currentPrices.get(0); 
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
        return dayPrice(buyIndex) +","+ dayPrice(sellIndex);
	}
	
	private String dayPrice(int dayIndex)
	{
		return String.valueOf(dayIndex+1) +"("+ currentPrices.get(dayIndex).toString()+")";
	}
}
