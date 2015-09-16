package jpmorgan.simpleStock.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jpmorgan.simpleStock.memorySimulator.StockMarketSimulator;
import jpmorgan.simpleStock.model.Stock;
import jpmorgan.simpleStock.model.StockSymbol;
import jpmorgan.simpleStock.model.Trade;

import org.apache.log4j.Logger;

/**
 * 
 * @author AELHAOUNI
 *
 */
public class SimpleStockServiceImpl implements SimpleStockService {
	
	private static final int WINDOW_15_MINUTE = 15;

	private Logger logger = Logger.getLogger(SimpleStockServiceImpl.class);
	
	private StockMarketSimulator stockMarketSimulator;

	/** {@inheritDoc} */
	@Override
	public double calculateDividendYield(StockSymbol stockSymbol) {
		double dividendYield = -1.0;

		try{
			Stock stock = stockMarketSimulator.getStockByStockSymbol(stockSymbol);
			if(stock==null){
				throw new Exception("");
			}

			if(stock.getSharePrice() <= 0.0){
				throw new Exception("");
			}
			dividendYield = stock.getDividendYield();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dividendYield;
	}
	
	/** {@inheritDoc} */
	@Override
	public double calculatePERation(StockSymbol stockSymbol) {
		double peRatio = -1.0;
		try{
			Stock stock = stockMarketSimulator.getStockByStockSymbol(stockSymbol);

			if(stock==null){
				throw new Exception("");
			}

			if(stock.getSharePrice() <= 0.0){
				throw new Exception("");
			}

			peRatio = stock.getPeRatio();


		}catch(Exception e){
			e.printStackTrace();
		}
		return peRatio;
	}

	/** {@inheritDoc} */
	@Override
	public void recordTrade(Trade trade) {
		try{
			
			if(trade==null){
				throw new Exception("");
			}

			if(trade.getStock()==null){
				throw new Exception("");
			}

			if(trade.getSharesQuantity()<=0){
				throw new Exception("");
			}

			if(trade.getTradePrice()<=0.0){
				throw new Exception("");
			}

			stockMarketSimulator.recordTrade(trade);

			trade.getStock().setSharePrice(trade.getTradePrice());


		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private  double calculateStockPriceByWindow(final StockSymbol stockSymbol, final int window) {
		double stockPriceWindow = 0.0;
		
		List<Trade> trades = stockMarketSimulator.getTradesInWindow(stockSymbol, window);

		int shareQuantityTotal = 0;
		double tradePriceTotal = 0.0;
		for(Trade trade : trades){
			int muniteNow = (new Date()).getMinutes();
			boolean isTradeInWindow = trade.getTimeStamp().getMinutes() + window >= muniteNow;
			if (isTradeInWindow) {
			tradePriceTotal += (trade.getTradePrice() * trade.getSharesQuantity());
			shareQuantityTotal += trade.getSharesQuantity();
			}
		}
		if(shareQuantityTotal > 0){
			stockPriceWindow = tradePriceTotal / shareQuantityTotal;	
		}

		return stockPriceWindow;
	}
	
	/** {@inheritDoc} */
	@Override
	public double calculateVolumeWeightedStockPrice(StockSymbol stockSymbol) {
		double stockPrice = 0.0;
		try{
			Stock stock = stockMarketSimulator.getStockByStockSymbol(stockSymbol);
			if(stock==null){
				throw new Exception("");
			}
			stockPrice = calculateStockPriceByWindow(stockSymbol, WINDOW_15_MINUTE);
			logger.debug("");
		}catch(Exception e){
			logger.error("", e);
			e.printStackTrace();
		}
		return stockPrice;
	}

	/** {@inheritDoc} */
	@Override
	public double calculateGBCEAllShareIndex() {
		double allShareIndex = 0.0;
		
		// Calculate stock price for all stock in the system
		HashMap<StockSymbol, Stock> stocks = stockMarketSimulator.getStocks();
		List<Double> stockPrices = new ArrayList<Double>();
		for(StockSymbol stockSymbol: stocks.keySet() ){
			double stockPrice = calculateStockPriceByWindow(stockSymbol, 0);
			if(stockPrice > 0){
				stockPrices.add(stockPrice);
			}
		}
		double geoMean = 1.0;
		final int stockPriceNumber = stockPrices.size();
		if(stockPriceNumber>=1){
			for(int i = 0; i<=(stockPriceNumber-1); i++){
				geoMean *= stockPrices.get(i).doubleValue();
			}
			geoMean = Math.pow(geoMean, 1/stockPriceNumber);
		}
		return allShareIndex;	
	}

}
