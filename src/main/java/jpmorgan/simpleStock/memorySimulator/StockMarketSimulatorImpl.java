package jpmorgan.simpleStock.memorySimulator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jpmorgan.simpleStock.model.Stock;
import jpmorgan.simpleStock.model.StockSymbol;
import jpmorgan.simpleStock.model.Trade;

import org.apache.log4j.Logger;

import sun.security.jca.GetInstance;

/** {@inheritDoc} */
public class StockMarketSimulatorImpl implements StockMarketSimulator {

	private Logger logger = Logger.getLogger(StockMarketSimulatorImpl.class);


	private HashMap<StockSymbol, Stock> stocks = null;


	private List<Trade> tradeList = null;
	
	
	private StockMarketSimulatorImpl(){
		setTradeList(new ArrayList<Trade>());
	}
	
	private static StockMarketSimulatorImpl INSTANCE = new StockMarketSimulatorImpl();
	
	public static StockMarketSimulatorImpl getInstance(){
		return INSTANCE;
	}

	/** {@inheritDoc} */
	@Override
	public void recordTrade(Trade trade) {
		if (trade != null) {
			trade.setTimeStamp(new Date());
			trade.getStock().setSharePrice(trade.getTradePrice());
			tradeList.add(trade);	
		}
			
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Trade> getTradeList() {
		return tradeList;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setTradeList(List<Trade> tradeList){
		this.tradeList = tradeList;
	}

	/** {@inheritDoc} */
	@Override
	public Stock getStockByStockSymbol(StockSymbol stockSymbol) {
		return stocks.get(stockSymbol);
	}

	/** {@inheritDoc} */
	@Override
	public HashMap<StockSymbol, Stock> getStocks() {
		return stocks;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setStocks(HashMap<StockSymbol, Stock> stocks){
		this.stocks = stocks;
	}

	/** {@inheritDoc} */
	@Override
	public List<Trade> getTradesByStockSymbol(StockSymbol stockSymbol) {
		List<Trade> tradeListWindow = new ArrayList<Trade>();

		for (Trade trade : tradeList){
			if (trade.getStock().getStockSymbol().equals(stockSymbol)) {
				tradeListWindow.add(trade);
			}
		}
		
		return tradeListWindow;
	}
}
