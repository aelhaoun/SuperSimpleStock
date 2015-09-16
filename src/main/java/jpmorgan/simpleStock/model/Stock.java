package jpmorgan.simpleStock.model;

import org.apache.log4j.Logger;

/**
 * Stock modelisation.
 * 
 * @author AELHAOUNI
 *
 */
public class Stock {
	
	/**
	 * Logger.
	 */
	private Logger logger = Logger.getLogger(Stock.class);
	
	/**
	 * Stock Symbol.
	 */
	private StockSymbol stockSymbol;
	
	/**
	 * Stock Type.
	 */
	private StockType stockType = StockType.COMMON;
	
	/**
	 * Last dividend
	 */
	private double lastDividend;
	
	/**
	 * Fixed dividend.
	 */
	private Double fixedDividend;
	
	/**
	 * Par value
	 */
	private double parValue;
	
	/**
	 * Share price.
	 */
	private double sharePrice;

	public Stock(StockSymbol stockSymbol, StockType stockType, double lastDividend, Double fixedDividend, double parValue) {
		this.stockSymbol = stockSymbol;
		this.stockType = stockType;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	/**
	 * Get the stock symbol.
	 * @return The stock symbol.
	 */
	public StockSymbol getStockSymbol() {
		return stockSymbol;
	}

	/**
	 * Set the stock symbol.
	 * @param stockSymbol
	 */
	public void setStockSymbol(StockSymbol stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	/**
	 * Get the stock type.
	 * @return The stock type.
	 */
	public StockType getStockType() {
		return stockType;
	}

	/**
	 * Set the stock type.
	 * @param stockType Stock type.
	 */
	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}

	/**
	 * Get the last dividend.
	 * @return The last dividend.
	 */
	public Double getLastDividend() {
		return lastDividend;
	}

	/**
	 * Set the last dividend
	 * @param lastDividend new value of the last dividend.
	 */
	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}

	/**
	 * Get the fixed dividend.
	 * @return The fixed dividend.
	 */
	public Double getFixedDividend() {
		return fixedDividend;
	}

	/**
	 * Set the fixed dividend.
	 * @param fixedDividend The new valeu of the fixed dividend.
	 */
	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	/**
	 * Get the par value.
	 * @return The par value.
	 */
	public Double getParValue() {
		return parValue;
	}

	/**
	 * Set the par value.
	 * @param parValue the new value of the par value.
	 */
	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}
	
	/**
	 * Get the stock price.
	 * 
	 * @return The stock price.
	 */
	public double getSharePrice() {
		return sharePrice;
	}

	/**
	 * Set the stock price.
	 * 
	 * @param stockPrice The new value of the stock price.
	 */
	public void setSharePrice(double stockPrice) {
		this.sharePrice = stockPrice;
	}

	/**
	 * Get the dividend Yield.
	 * DIVIDEND YIELD = ANNUAL DIVIDENDS PER Share / Price per share.
	 * 
	 * If there is any problem with computation, NEGATIVE_INFINITY is returned
	 * @return The dividend yield.
	 */
	public double getDividendYield() throws ArithmeticException{
		double dividendYield = Double.NEGATIVE_INFINITY;
		if(sharePrice > 0.0){
			if( stockType==StockType.COMMON){// stockType==StockType.COMMON
				dividendYield = lastDividend / sharePrice;
			}else{
				// stockType==StockType.PREFERRED
				dividendYield = (fixedDividend * parValue ) / sharePrice;
			}
		}else {
			logger.error("The share price must be greater than 0");
			throw new ArithmeticException();
		}
		return dividendYield;
	}
	
	/**
	 * Get the Price-Earnings Ratio.
	 * The price-earnings ratio can be calculated as:
	 * 			Market Value per Share / Earnings per Share
	 *			===> sharePrice / last Dividend (I assume that the company didn't keep any proper capital).
	 * If there is any problem with computation, NEGATIVE_INFINITY is returned
	 * @return the P/E ration.
	 */
	public double getPeRatio() throws ArithmeticException{
		double peRatio = Double.NEGATIVE_INFINITY;
		
		if(lastDividend > 0.0){
			peRatio = sharePrice/lastDividend;
		}else  if (lastDividend == 0){
			logger.error("The last dividend should be greater than 0");
			throw new ArithmeticException();
		}
		
		return peRatio;
	}
	
	

}
