import java.util.HashMap;
import java.util.List;

import jpmorgan.simpleStock.memorySimulator.StockMarketSimulator;
import jpmorgan.simpleStock.memorySimulator.StockMarketSimulatorImpl;
import jpmorgan.simpleStock.model.Stock;
import jpmorgan.simpleStock.model.StockSymbol;
import jpmorgan.simpleStock.model.StockType;
import jpmorgan.simpleStock.model.Trade;


public class Main {
	
	public static void main(String args[]){
		
		StockMarketSimulator stockMarket = new StockMarketSimulatorImpl() ;
		
		HashMap<StockSymbol, Stock> stocks = new HashMap<StockSymbol, Stock>();
		
		stocks.put(StockSymbol.TEA, new Stock(StockSymbol.TEA, StockType.COMMON, 0.0, null, 100));
		stocks.put(StockSymbol.POP, new Stock(StockSymbol.POP, StockType.COMMON, 8.0,  null, 100));
		stocks.put(StockSymbol.ALE, new Stock(StockSymbol.ALE, StockType.COMMON, 23.0,  null, 60));
		stocks.put(StockSymbol.GIN, new Stock(StockSymbol.GIN, StockType.PREFERRED, 8.0,  2.0, 100));
		stocks.put(StockSymbol.JOE, new Stock(StockSymbol.JOE, StockType.COMMON, 13.0,  null, 250));
		
		stockMarket.setStocks(stocks);
		
	}

}
