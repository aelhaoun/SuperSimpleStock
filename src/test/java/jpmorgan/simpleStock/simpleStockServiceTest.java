package jpmorgan.simpleStock;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import jpmorgan.simpleStock.memorySimulator.StockMarketSimulator;
import jpmorgan.simpleStock.memorySimulator.StockMarketSimulatorImpl;
import jpmorgan.simpleStock.model.Stock;
import jpmorgan.simpleStock.model.StockSymbol;
import jpmorgan.simpleStock.model.StockType;
import jpmorgan.simpleStock.model.Trade;
import jpmorgan.simpleStock.service.SimpleStockService;
import jpmorgan.simpleStock.service.SimpleStockServiceImpl;

public class simpleStockServiceTest {
	
	StockMarketSimulator stockMarket;
	SimpleStockService simpleStockService;
	@Before
	public void initialize() {
		stockMarket = new StockMarketSimulatorImpl() ;
		simpleStockService = new SimpleStockServiceImpl();
		
		HashMap<StockSymbol, Stock> stocks = new HashMap<StockSymbol, Stock>();
		
		stocks.put(StockSymbol.TEA, new Stock(StockSymbol.TEA, StockType.COMMON, 0.0, null, 100));
		stocks.put(StockSymbol.POP, new Stock(StockSymbol.POP, StockType.COMMON, 8.0,  null, 100));
		stocks.put(StockSymbol.ALE, new Stock(StockSymbol.ALE, StockType.COMMON, 23.0,  null, 60));
		stocks.put(StockSymbol.GIN, new Stock(StockSymbol.GIN, StockType.PREFERRED, 8.0,  2.0, 100));
		stocks.put(StockSymbol.JOE, new Stock(StockSymbol.JOE, StockType.COMMON, 13.0,  null, 250));
		
		stockMarket.setStocks(stocks);
		
		stockMarket.recordTrade(new Trade(10, 1, stocks.get(StockSymbol.TEA)));
		stockMarket.recordTrade(new Trade(20, 2, stocks.get(StockSymbol.POP)));
		stockMarket.recordTrade(new Trade(30, 3, stocks.get(StockSymbol.ALE)));
		stockMarket.recordTrade(new Trade(50, 4, stocks.get(StockSymbol.GIN)));
		stockMarket.recordTrade(new Trade(50, 4, stocks.get(StockSymbol.JOE)));
		
	}
	
	@Test
	public void calculateDividendYieldTest() {
		
		double dividendYieldTEA =simpleStockService.calculateDividendYield(StockSymbol.POP);
		System.out.println("Dividend yield TEA : "+ dividendYieldTEA);
		
	}

}
