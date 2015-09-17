package jpmorgan.simpleStock;

import java.util.HashMap;
import java.util.List;

import jpmorgan.simpleStock.memorySimulator.StockMarketSimulator;
import jpmorgan.simpleStock.memorySimulator.StockMarketSimulatorImpl;
import jpmorgan.simpleStock.model.Stock;
import jpmorgan.simpleStock.model.StockSymbol;
import jpmorgan.simpleStock.model.StockType;
import jpmorgan.simpleStock.model.Trade;
import jpmorgan.simpleStock.service.SimpleStockService;
import jpmorgan.simpleStock.service.SimpleStockServiceImpl;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


public class simpleStockServiceTest {
	
	StockMarketSimulator stockMarket;
	SimpleStockService simpleStockService;
	
	/**
	 * Initialization for following tests.
	 */
	@Before
	public void initialize() {
		stockMarket = StockMarketSimulatorImpl.getInstance() ;
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
	
	/**
	 * Dividend yield calculation test.
	 */
	@Test
	public void calculateDividendYieldTest() {
		double dividendYieldTEA =simpleStockService.calculateDividendYield(StockSymbol.TEA);
		Assert.assertTrue("The divided yield should be equals to zero for TEA", dividendYieldTEA == 0);
		
		double dividendYieldPOP =simpleStockService.calculateDividendYield(StockSymbol.POP);
		Assert.assertTrue("The divided yield should be a postive value For POP", dividendYieldPOP > 0);
		
		double dividendYieldALE =simpleStockService.calculateDividendYield(StockSymbol.ALE);
		Assert.assertTrue("The divided yield should be a postive value for ALE", dividendYieldALE > 0);
		
		double dividendYieldGIN =simpleStockService.calculateDividendYield(StockSymbol.GIN);
		Assert.assertTrue("The divided yield should be a postive value for GIN", dividendYieldGIN > 0);
		
		double dividendYieldJOE =simpleStockService.calculateDividendYield(StockSymbol.JOE);
		Assert.assertTrue("The divided yield should be a postive value for JOE", dividendYieldJOE > 0);
		
	}
	
	/**
	 * P/E ration calculation test.
	 */
	@Test
	public void calculatePERationTest() {
		
		double peRatioTEA = simpleStockService.calculatePERation(StockSymbol.TEA);
		Assert.assertTrue("The P/E Ratio should be equals to zero for TEA", peRatioTEA == Double.NEGATIVE_INFINITY);
		
		double peRatioPOP =simpleStockService.calculatePERation(StockSymbol.POP);
		Assert.assertTrue("The P/E Ratio should be a postive value", peRatioPOP > 0);
		
		double peRatioALE =simpleStockService.calculatePERation(StockSymbol.ALE);
		Assert.assertTrue("The P/E Ratio should be a postive value", peRatioALE > 0);
		
		double peRatioGIN =simpleStockService.calculatePERation(StockSymbol.GIN);
		Assert.assertTrue("The P/E Ratio should be a postive value", peRatioGIN > 0);
		
		double peRatioJOE =simpleStockService.calculatePERation(StockSymbol.JOE);
		Assert.assertTrue("The P/E Ratio should be a postive value", peRatioJOE > 0);
	}
	
	@Test
	public void recordTradeTest() {
		Stock stock  = stockMarket.getStockByStockSymbol(StockSymbol.POP); 
		Trade trade = new Trade(1.0, 1, stock);
		simpleStockService.recordTrade(trade);
		
		Assert.assertEquals(stock.getSharePrice(), trade.getTradePrice());
		List<Trade> tradeList =  stockMarket.getTradeList();
		
		Assert.assertTrue(tradeList.get(tradeList.size()-1).getTradePrice() == trade.getTradePrice());
	}
	
	@Test
	public void calculateVolumeWeightedStockPrice() {
		Double cwsp = simpleStockService.calculateVolumeWeightedStockPrice(StockSymbol.ALE);
		Assert.assertTrue(cwsp > 0.0);
	}
	
	@Test
	public void calculateGBCEAllShareIndexTest() {
		Double gbce = simpleStockService.calculateGBCEAllShareIndex();
		Assert.assertTrue(gbce > 0.0);
	}

}
