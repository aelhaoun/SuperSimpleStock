package jpmorgan.simpleStock.model;

import org.apache.log4j.Logger;

public class Stock {
	
	private Logger logger = Logger.getLogger(Stock.class);
	
	private StockSymbol stockSymbol = null;
	
	private StockType stockType = StockType.COMMON;
	
	private Double lastDividend;
	
	private Double fixedDividend;
	
	private Double parValue;

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public StockSymbol getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(StockSymbol stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public StockType getStockType() {
		return stockType;
	}

	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}

	public Double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public Double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public Double getParValue() {
		return parValue;
	}

	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}
	
	

}
