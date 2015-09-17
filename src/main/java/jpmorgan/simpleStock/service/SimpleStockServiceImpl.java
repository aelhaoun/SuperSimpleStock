package jpmorgan.simpleStock.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jpmorgan.simpleStock.memorySimulator.StockMarketSimulator;
import jpmorgan.simpleStock.memorySimulator.StockMarketSimulatorImpl;
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
	
	private StockMarketSimulator stockMarketSimulator = StockMarketSimulatorImpl.getInstance();

	/** {@inheritDoc} */ 
	@Override
	public double calculateDividendYield(StockSymbol stockSymbol) {
		double dividendYield = Double.NEGATIVE_INFINITY;

		try{
			logger.info("Calculate the dividend yield");
			Stock stock = stockMarketSimulator.getStockByStockSymbol(stockSymbol);
			if(stock==null){
				throw new Exception("No stock found that correspond to the stock Symbol");
			}

			if(stock.getSharePrice() <= 0.0){
				throw new Exception("The share price should have a postive value");
			}
			dividendYield = stock.getDividendYield();
		}catch(Exception e){
			logger.error("Error: calcul dividend yield");
			e.printStackTrace();
		}
		
		return dividendYield;
	}
	
	/** {@inheritDoc} */
	@Override
	public double calculatePERation(StockSymbol stockSymbol) {
		double peRatio = Double.NEGATIVE_INFINITY;
		try{
			logger.info("Calculate the P/E Ration");
			Stock stock = stockMarketSimulator.getStockByStockSymbol(stockSymbol);

			if(stock==null){
				throw new Exception("No stock found that correspond to the stock Symbol");
			}

			if(stock.getSharePrice() <= 0.0){
				throw new Exception("The share price should have a postive value");
			}

			peRatio = stock.getPeRatio();


		}catch(Exception e){
			logger.error("An error occured calculing the PE Ratio for : "+stockSymbol);
			e.printStackTrace();
		}
		return peRatio;
	}

	/** {@inheritDoc} */
	@Override
	public void recordTrade(Trade trade) {
		try{
			
			if(trade==null){
				throw new Exception("The trade is not initialised");
			}

			if(trade.getStock()==null){
				throw new Exception("No stock found that correspond to the stock Symbol");
			}

			if(trade.getSharesQuantity()<=0){
				throw new Exception("The quantity of share is a positive value");
			}

			if(trade.getTradePrice()<=0.0){
				throw new Exception("THe trade price is a positif value");
			}

			logger.info("Recode of a new trade");
			stockMarketSimulator.recordTrade(trade);

			trade.getStock().setSharePrice(trade.getTradePrice());


		}catch(Exception e){
			logger.error("An error occured recording the trade");
			e.printStackTrace();
		}
	}

	private  double calculateStockPriceByWindow(final StockSymbol stockSymbol, final int window) throws ArithmeticException{
		double stockPriceWindow = Double.NEGATIVE_INFINITY; 
		
		List<Trade> trades = stockMarketSimulator.getTradesByStockSymbol(stockSymbol);

		int shareQuantityTotal = 0;
		double tradePriceTotal = 0.0;
		for(Trade trade : trades){
			Long timeNow = (new Date()).getTime();
			boolean isTradeInWindow = trade.getTimeStamp().getTime() + window*60000 >= timeNow;
			if (isTradeInWindow) {
				tradePriceTotal += (trade.getTradePrice() * trade.getSharesQuantity());
				shareQuantityTotal += trade.getSharesQuantity();
			}
		}
		if(shareQuantityTotal > 0){
			stockPriceWindow = tradePriceTotal / shareQuantityTotal;	
		} else {
			logger.error("the Total quantity of share is null");
			throw  new ArithmeticException();
		}

		return stockPriceWindow;
	}

	
	/** {@inheritDoc} */
	@Override
	public double calculateVolumeWeightedStockPrice(StockSymbol stockSymbol) {
		double stockPrice = Double.NEGATIVE_INFINITY;
		logger.info("Calculate the Volume Weight stock price");
		try{
			Stock stock = stockMarketSimulator.getStockByStockSymbol(stockSymbol);
			if(stock==null){
				throw new Exception("No stock found that correspond to the stock Symbol");
			}
			stockPrice = calculateStockPriceByWindow(stockSymbol, WINDOW_15_MINUTE);
		}catch(Exception e){
			logger.error("Error : Volume Weight stock price calculation ", e);
			e.printStackTrace();
		}
		return stockPrice;
	}

	/** {@inheritDoc} */
	@Override
	public double calculateGBCEAllShareIndex() {
		double allShareIndex =Double.NEGATIVE_INFINITY;
		logger.info("Calculate GBCE All index");
		try {
			HashMap<StockSymbol, Stock> stocks = stockMarketSimulator.getStocks();
			
			double geoMean = 1.0;
			final int stockPriceNumber = stocks.size();
			if(stockPriceNumber>=1){
				for (StockSymbol key : stocks.keySet()){
					geoMean *= stocks.get(key).getSharePrice();
				}
				allShareIndex = Math.pow(geoMean, 1.0/stockPriceNumber);
			}
			
		}catch (Exception e) {
			logger.error("Error: GBCE calculation", e);
			e.printStackTrace();
		}
		
		
		return allShareIndex;	
	}

}
