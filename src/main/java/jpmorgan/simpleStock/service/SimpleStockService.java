package jpmorgan.simpleStock.service;

import jpmorgan.simpleStock.model.StockSymbol;
import jpmorgan.simpleStock.model.Trade;

/**
 * Simple stock services interface.
 * 
 * @author AELHAOUNI
 *
 */
public interface SimpleStockService {
	/**
	 * Calculate the dividend yield given a market price (share price) deduced from the stock symbol.
	 * 
	 * @param stockSymbol The stock symbol.
	 * @return Dividend yield.
	 */
	public double calculateDividendYield(StockSymbol stockSymbol);
	
	/**
	 * Calculate the Price-earning ration - P/E ratio given a market price (share price) deduced from the stock symbol.
	 * 
	 * @param stockSymbol The sock symbol.
	 * @return Price-earning ration.
	 */
	public double calculatePERation(StockSymbol stockSymbol);
	
	/**
	 * Record a trade.
	 * 
	 * @param trade Trade to record.
	 */
	public void recordTrade(Trade trade);
	
	/**
	 * Calculate the volume weighted stock price given a market price (share price) deduced from the stock symbol.
	 * 
	 * @param stockSymbol The sock symbol.
	 * @return GBCE all share index.
	 */
	public double calculateVolumeWeightedStockPrice(StockSymbol stockSymbol);
	
	/**
	 * Calculate the GBCE all share index.
	 * @return GBCE all share index.
	 */
	public double calculateGBCEAllShareIndex();

}
