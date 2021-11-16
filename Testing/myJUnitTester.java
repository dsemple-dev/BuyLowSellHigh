import org.junit.Test;
import static org.junit.Assert.*;	

public class myJUnitTester {
	
	@Test(expected = Exception.class) //incorrect or missing file
	public void testFileHandler1()
	{
		pricesFileHandler fReader = new pricesFileHandler();
		fReader.getPricesFromFile("incorrect file name");
	}
	
	@Test // set 1
	public void testData1()
	{
		pricesFileHandler fReader = new pricesFileHandler();
		Float[] prices = fReader.getPricesFromFile("ChallengeSampleDataSet1.txt");
		buyLowSellHigh testSet = new buyLowSellHigh(prices);
		String testOutput = testSet.bestDays();
		assertEquals("15(15.28),21(27.39)",testOutput);
	}
	
	@Test // set 2
	public void testData2()
	{
		pricesFileHandler fReader = new pricesFileHandler();
		Float[] prices = fReader.getPricesFromFile("ChallengeSampleDataSet2.txt");
		buyLowSellHigh testSet = new buyLowSellHigh(prices);
		String testOutput = testSet.bestDays();
		assertEquals("20(15.79),21(26.19)",testOutput);
	}
	
	@Test // small data set
	public void testData3()
	{
		Float[] prices = {1.1f,2.1f,3.1f,5.1f,2.1f};
		buyLowSellHigh setTest = new buyLowSellHigh(prices);
		String testOutput = setTest.bestDays();
		assertEquals("1(1.1),4(5.1)",testOutput);
	}
	
	@Test // empty data set
	public void testData4()
	{
		Float[] prices = {};
		buyLowSellHigh setTest = new buyLowSellHigh(prices);
		String testOutput = setTest.bestDays();
		assertEquals("Empty List",testOutput);
	}
	
	@Test // data set too small
	public void testData5()
	{
		Float[] prices = {5.5f};
		buyLowSellHigh setTest = new buyLowSellHigh(prices);
		String testOutput = setTest.bestDays();
		assertEquals("Dataset too small",testOutput);
	}
	
	@Test // data set of only 2
	public void testData6()
	{
		Float[] prices = {1.1f,3.1f};
		buyLowSellHigh setTest = new buyLowSellHigh(prices);
		String testOutput = setTest.bestDays();
		assertEquals("1(1.1),2(3.1)",testOutput);
	}
	
	@Test // price always decreasing
	public void testData7()
	{
		Float[] prices = {9.0f,8.0f,7.0f,5.0f};
		buyLowSellHigh setTest = new buyLowSellHigh(prices);
		String testOutput = setTest.bestDays();
		assertEquals("Hold onto shares longer",testOutput);
	}
}
