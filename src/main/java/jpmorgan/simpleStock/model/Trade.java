package jpmorgan.simpleStock.model;

import java.util.Date;

/**
 * Trade modelisation.
 * 
 * @author AELHAOUNI
 *
 */
public class Trade {
	/**
	 * Time stamp corresponding to the trade.
	 */
	private Date timeStamp;
	/**
	 * Quantily of shares.
	 */
	private int sharesQuantity;
	
	/**
	 * The trade indicator.
	 */
	private TradeIndicator tradeIndicator;
	/**
	 * The trade price.
	 */
	private Double tradePrice;
	
	/**
	 * The stock.
	 */
	private Stock stock;
	
	/**
	 * Trade constructor.
	 */
	public Trade() {
		sharesQuantity = 0;
		tradeIndicator = TradeIndicator.BUY;
		tradePrice = 0.0;
	}

	/**
	 * Get the record time stamp.
	 * 
	 * @return The record time stamp.
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Set the time stamp.
	 * @param timeStamp New value of the time stamp.
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Get the trade indicator.
	 * @return The trade indicator.
	 */
	public TradeIndicator getTradeIndicator() {
		return tradeIndicator;
	}

	/**
	 * Set the trade indicator.
	 * @param tradeIndicator The trade indicator.
	 */
	public void setTradeIndicator(TradeIndicator tradeIndicator) {
		this.tradeIndicator = tradeIndicator;
	}

	/**
	 * Get the quantity of shares.
	 * @return Quantity of shares.
	 */
	public int getSharesQuantity() {
		return sharesQuantity;
	}

	/**
	 * Set the new quantity of shares.
	 * @param sharesQuantity the new value of the quantity of shares.
	 */
	public void setSharesQuantity(int sharesQuantity) {
		this.sharesQuantity = sharesQuantity;
	}

	/**
	 * Get the trade price.
	 * 
	 * @return The trade price.
	 */
	public Double getTradePrice() {
		return tradePrice;
	}

	/**
	 * Set the trade price.
	 * @param tradePrice The new value of the trade price.
	 */
	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	/**
	 * Get the stock.
	 * @return Stock.
	 */
	public Stock getStock() {
		return stock;
	}

	/**
	 * Attach a new stock.
	 * @param stock Stock.
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	

}
