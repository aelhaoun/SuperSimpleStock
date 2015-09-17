package jpmorgan.simpleStock.memorySimulator;

import java.util.HashMap;
import java.util.List;

import jpmorgan.simpleStock.model.Stock;
import jpmorgan.simpleStock.model.StockSymbol;
import jpmorgan.simpleStock.model.Trade;

/**
 * Simulating the stock market (memory).
 * 
 * @author AELHAOUNI
 *
 */
public interface StockMarketSimulator {
	/**
	 * Record a trade.
	 * 
	 * @param trade Trade to recod.
	 */
	public void recordTrade(Trade trade);
	
	/**
	 * Get the trade list.
	 * @return Trade list.
	 */
	public List<Trade> getTradeList();
	
	/**
	 * Set the trade List.
	 * @param tradeList The new value of the trade list.
	 */
	public void setTradeList(List<Trade> tradeList);
	
	/**
	 * Get a stock by its symbol.
	 * @param stockSymbol Stock symbol.
	 * @return Stock.
	 */
	public Stock getStockByStockSymbol(StockSymbol stockSymbol);
	
	/**
	 * Get all stocks.
	 * 
	 * @return All stocks.
	 */
	public HashMap<StockSymbol, Stock> getStocks();
	
	/**
	 * Set new Stock.
	 * @param stocks Stocks.
	 */
	public void setStocks(HashMap<StockSymbol, Stock> stocks);
	
	/**
	 * get all trades corresponding to a stock symbol.
	 * @param stockSymbol Stock symbol.
	 * @return Trade list.
	 */
	public List<Trade> getTradesByStockSymbol(StockSymbol stockSymbol);

}
