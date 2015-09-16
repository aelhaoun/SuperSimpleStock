package jpmorgan.simpleStock.memorySimulator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jpmorgan.simpleStock.model.Stock;
import jpmorgan.simpleStock.model.StockSymbol;
import jpmorgan.simpleStock.model.Trade;

import org.apache.log4j.Logger;

public class StockMarketSimulatorImpl implements StockMarketSimulator {

	private Logger logger = Logger.getLogger(StockMarketSimulatorImpl.class);


	private HashMap<StockSymbol, Stock> stocks = null;


	private List<Trade> tradeList = null;
	
	
	public StockMarketSimulatorImpl(){
		setTradeList(new ArrayList<Trade>());
	}

	@Override
	public void recordTrade(Trade trade) {
		if (trade != null) {
			trade.setTimeStamp(new Date());
		}
		tradeList.add(trade);		
	}

	@Override
	public List<Trade> getTradeList() {
		// TODO Auto-generated method stub
		return tradeList;
	}
	
	@Override
	public void setTradeList(List<Trade> tradeList){
		this.tradeList = tradeList;
	}


	@Override
	public Stock getStockByStockSymbol(StockSymbol stockSymbol) {
		return stocks.get(stockSymbol);
	}


	@Override
	public HashMap<StockSymbol, Stock> getStocks() {
		return stocks;
	}
	
	@Override
	public void setStocks(HashMap<StockSymbol, Stock> stocks){
		this.stocks = stocks;
	}

	@Override
	public List<Trade> getTradesInWindow(StockSymbol stockSymbol, int window) {
		List<Trade> tradeListWindow = new ArrayList<Trade>();

		for (Trade trade : tradeList){
			tradeListWindow.add(trade);
		}
		
		return tradeListWindow;
	}
}
