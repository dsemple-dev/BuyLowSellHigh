package com.dsempledev.buylowsellhigh;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.StreamTokenizer;

import java.util.ArrayList;

public class pricesFileHandler {

	private static final Logger logger = LogManager.getLogger(buyLowSellHighApplication.class);
	private ArrayList<Float> sharePriceList;
	
	public pricesFileHandler()
	{//empty constructor
	}
	
	public ArrayList<Float> readWithStreamTokenizer(String fileName)
	{
		sharePriceList = new ArrayList<Float>();
		try (FileReader fileReader = new FileReader(fileName)){
			StreamTokenizer st = new StreamTokenizer(fileReader);
			st.whitespaceChars(',', ',');
			float token =0;
			while((token = st.nextToken()) != StreamTokenizer.TT_EOF) {
				if(st.ttype == StreamTokenizer.TT_NUMBER)
				{
					if (st.nval > 0)//check that share price is positive number
					{
						sharePriceList.add((float)st.nval);
					}
					else
					{
						logger.error("Not a valid share price: "+st.sval);
						logger.error("Must be greater than zero.");
					}
				} else if(st.ttype == StreamTokenizer.TT_EOF) {
					logger.debug("All prices read in from file.");
				}
				else if(st.ttype == StreamTokenizer.TT_WORD) {
					logger.error("Not a valid share price: "+st.sval);
				}
				else {
					logger.error("Not a valid share price: "+(char)token);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sharePriceList.size() > 0 ? sharePriceList : null;
	}
}
