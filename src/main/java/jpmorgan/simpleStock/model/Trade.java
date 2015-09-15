package jpmorgan.simpleStock.model;

import java.util.Date;

public class Trade {
	
	private Date timeStamp;
	
	private int sharesQuantity;
	
	private TradeIndicator tradeIndicator;
	
	private Double tradePrice;
	
	private Stock stock;
	
	public Trade() {
		sharesQuantity = 0;
		tradeIndicator = TradeIndicator.BUY;
		tradePrice = 0.0;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}


	public TradeIndicator getTradeIndicator() {
		return tradeIndicator;
	}

	public void setTradeIndicator(TradeIndicator tradeIndicator) {
		this.tradeIndicator = tradeIndicator;
	}

	public int getSharesQuantity() {
		return sharesQuantity;
	}

	public void setSharesQuantity(int sharesQuantity) {
		this.sharesQuantity = sharesQuantity;
	}

	public Double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	

}
