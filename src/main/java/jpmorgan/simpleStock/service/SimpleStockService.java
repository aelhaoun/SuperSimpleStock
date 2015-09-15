package jpmorgan.simpleStock.service;

import jpmorgan.simpleStock.model.StockSymbol;
import jpmorgan.simpleStock.model.Trade;

/**
 * 
 * @author AELHAOUNI
 *
 */
public interface SimpleStockService {
	
	public double calculateDividendYield(StockSymbol stockSymbol);
	
	public double calculatePERation(StockSymbol stockSymbol);
	
	public void recordTrade(Trade trade);
	
	public double calculateVolumeWeightedStockPrice(StockSymbol stockSymbol);
	
	public double calculateGBCEAllShareIndex();

}
