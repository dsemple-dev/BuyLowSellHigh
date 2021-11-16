
public class Main {
	public static void main(String[] args) {
		
		pricesFileHandler fReader = new pricesFileHandler();
		
		//set 1
		System.out.println("SET 1");
		Float[] prices = fReader.getPricesFromFile("ChallengeSampleDataSet1.txt");
		buyLowSellHigh set1 = new buyLowSellHigh(prices);
		System.out.println(set1.bestDays());
		
		// set 2
		System.out.println("\nSET 2");
		Float[] prices2 = fReader.getPricesFromFile("ChallengeSampleDataSet2.txt");
		buyLowSellHigh set2 = new buyLowSellHigh(prices2);
		System.out.println(set2.bestDays());
	}
}