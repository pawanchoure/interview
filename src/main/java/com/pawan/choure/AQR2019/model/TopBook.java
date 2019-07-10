package com.pawan.choure.AQR2019.model;

/**
 * TopBook : Class for storing the Top of the Book Messages received from different exchanges
 */

import com.pawan.choure.AQR2019.utility.Utility;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * TopBook : Class used to store TopBook Object
 */
public class TopBook extends TradeBook {
    Integer topBookId;
    BigDecimal bestBidPrice;
    Integer bestBidSize;
    BigDecimal bestOfferPrice;
    Integer bestOfferSize;

    /**
     * TopBook : TopBook Instance creation
     * @param symbol
     * @param exchange
     * @param bestBidPrice
     * @param bestBidSize
     * @param bestOfferPrice
     * @param bestOfferSize
     */
    public TopBook(String symbol,String exchange,BigDecimal bestBidPrice, Integer bestBidSize, BigDecimal bestOfferPrice, Integer bestOfferSize) {
        super(symbol,exchange);
        this.bestBidPrice = bestBidPrice;
        this.bestBidSize = bestBidSize;
        this.bestOfferPrice = bestOfferPrice;
        this.bestOfferSize = bestOfferSize;
        this.topBookId= Utility.getUniqueID();
    }

    public BigDecimal getBestBidPrice() {
        return bestBidPrice;
    }

    public void setBestBidPrice(BigDecimal bestBidPrice) {
        this.bestBidPrice = bestBidPrice;
    }

    public Integer getBestBidSize() {
        return bestBidSize;
    }

    public void setBestBidSize(Integer bestBidSize) {
        this.bestBidSize = bestBidSize;
    }

    public BigDecimal getBestOfferPrice() {
        return bestOfferPrice;
    }

    public void setBestOfferPrice(BigDecimal bestOfferPrice) {
        this.bestOfferPrice = bestOfferPrice;
    }

    public Integer getBestOfferSize() {
        return bestOfferSize;
    }

    public void setBestOfferSize(Integer bestOfferSize) {
        this.bestOfferSize = bestOfferSize;
    }

    public Integer getTopBookId() {
        return topBookId;
    }

    public void setTopBookId(Integer topBookId) {
        this.topBookId = topBookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopBook topBook = (TopBook) o;
        return topBookId.equals(topBook.topBookId) &&
                symbol.equals(topBook.symbol) &&
                exchange.equals(topBook.exchange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topBookId, symbol, exchange);
    }
}
