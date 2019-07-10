package com.pawan.choure.AQR2019.model;

/**
 * TradeBook : Base Class to hold Symbol and Exchange used by TopBook and OrderBook
 */
public class TradeBook {
    String symbol;
    String exchange;

    /**
     * TradeBook : Constructor for creating new instance
     * @param symbol
     * @param exchange
     */
    public TradeBook(String symbol, String exchange) {
        this.symbol = symbol;
        this.exchange = exchange;
    }
    public TradeBook() {

    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
