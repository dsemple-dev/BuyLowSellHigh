package com.dsempledev.buylowsellhigh;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class buyLowSellHighProcessor {

	private static final Logger logger = LogManager.getLogger(buyLowSellHighApplication.class);
	private ArrayList<Float> currentPrices;
	private String theBestTrade;
	
	public buyLowSellHighProcessor(ArrayList<Float> priceArray)
	{
		logger.debug("buyLowSellHigh Processor created");
        currentPrices = priceArray;
		processTrades();
	}

	public void matriceTest() // remove before PROD
	{
		Float[][] theMatrix = new Float[currentPrices.size()][currentPrices.size()];
		int bestX = 0;
		int bestY = 1;
		float bestTrade = 0;
		for (int x = 0; x < currentPrices.size(); x++)
		{
			for (int y = x + 1; y < currentPrices.size(); y++)
			{
				if (currentPrices.get(y) < currentPrices.get(x))
				{
					//theMatrix[x][y] = -1f;
				}
				else {
					theMatrix[x][y] = (currentPrices.get(y) - currentPrices.get(x));
					if (theMatrix[x][y] > bestTrade) {
						bestTrade = theMatrix[x][y];
						bestX = x;
						bestY = y;
					}
				}
			}
		}
		logger.info(dayPrice(bestX) + dayPrice(bestY));
	}

	private void processTrades()
	{
		logger.debug("Processing Trades");
		if (currentPrices.isEmpty())
		{
			theBestTrade = ("The price list is empty.");
			logger.error("The price list is empty.");
		}
		if (currentPrices.size() < 2)
		{
			theBestTrade = ("Dataset too small");
			logger.error("Dataset too small");
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
			theBestTrade = ("Hold onto shares longer");
			logger.error("No valid trade to make.  Hold onto shares longer.  ");
        }
		setBestTradeOutputFormat(buyIndex,sellIndex);
	}
	
	private String dayPrice(int dayIndex)
	{
		return String.valueOf(dayIndex+1) +"("+ currentPrices.get(dayIndex).toString()+")";
	}

	private void setBestTradeOutputFormat(int buyIndex, int sellIndex)
	{
		theBestTrade =  dayPrice(buyIndex) +","+ dayPrice(sellIndex);
	}

	public String getTheBestTrade()
	{
		if (theBestTrade == null) // use string utils to check this
		{
			processTrades();
		}

		return theBestTrade != null ? theBestTrade : "ERROR in processing";
	}
}

