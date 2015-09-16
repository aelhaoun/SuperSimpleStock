package jpmorgan.simpleStock.memorySimulator;

import java.util.HashMap;
import java.util.List;

import jpmorgan.simpleStock.model.Stock;
import jpmorgan.simpleStock.model.StockSymbol;
import jpmorgan.simpleStock.model.Trade;


public interface StockMarketSimulator {
	
	public void recordTrade(Trade trade);
	
	public List<Trade> getTradeList();
	
	public void setTradeList(List<Trade> tradeList);
	
	public Stock getStockByStockSymbol(StockSymbol stockSymbol);
	
	public HashMap<StockSymbol, Stock> getStocks();
	
	public void setStocks(HashMap<StockSymbol, Stock> stocks);
	
	public List<Trade> getTradesByStockSymbol(StockSymbol stockSymbol);

}
