package billionCompanies;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class CompanyValue {
	
	public CompanyValue() {
	}
	
	public void test() throws IOException {
		String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
		// Can also be done with explicit from, to and Interval parameters
		Map<String, Stock> stocks = YahooFinance.get(symbols);
		Stock intel = stocks.get("INTC");
		Stock airbus = stocks.get("AIR.PA");
		BigDecimal price = intel.getQuote().getPrice();
		BigDecimal change = intel.getQuote().getChangeInPercent();
		BigDecimal peg = intel.getStats().getPeg();
		BigDecimal dividend = intel.getDividend().getAnnualYieldPercent();
		 
		intel.print();
	}
	
	public static void main() throws IOException {
		CompanyValue Test = new CompanyValue();
		Test.test();
		//System.out.println(x);
	}
}
