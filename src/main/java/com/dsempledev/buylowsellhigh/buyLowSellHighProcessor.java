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
		//two buy indexes incase lowest price does not give biggest difference in price
        int buyIndex = 0;
        int newBuyIndex = 0;
        Float buyPrice = currentPrices.get(buyIndex);
        int i;
		logger.debug("Looping through all share prices.");
        for (i = 1; i < currentPrices.size(); i++)
        {
            if ((currentPrices.get(i) - buyPrice) > max_diff)
            {
                max_diff = currentPrices.get(i) - buyPrice;
                sellIndex = i; //set day to sell to current day
                buyIndex = newBuyIndex; //set day to buy to lowest previous value
				logger.debug("New best trade identified.");
            }
            if (currentPrices.get(i) < buyPrice)//check if today is lowest price
            {
            	buyPrice = currentPrices.get(i); //price to buy at set to today's value
            	newBuyIndex = i; //store todays index
				logger.debug("New day to purchase shares identified.");
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
			logger.debug("Reprocessing file.  ");
			processTrades();
		}

		return theBestTrade != null ? theBestTrade : "ERROR in processing";
	}
}

