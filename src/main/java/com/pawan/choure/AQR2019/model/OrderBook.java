package com.pawan.choure.AQR2019.model;

import com.pawan.choure.AQR2019.utility.BUYSELL;
import com.pawan.choure.AQR2019.utility.OrderType;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * OrderBook : Class used for Storing Order Book records received from different exchanges
 */
public class OrderBook extends TradeBook {

    Integer orderId;
    OrderType orderType;

    BigDecimal limitPrice;
    BUYSELL buySell;
    Integer quantity;

    /**
     * OrderBook Instance for NEW Order Type
     * @param symbol
     * @param exchange
     * @param orderId
     * @param orderType
     * @param limitPrice
     * @param buySell
     * @param quantity
     */
    public OrderBook(String symbol, String exchange, Integer orderId, OrderType orderType, BigDecimal limitPrice, BUYSELL buySell, Integer quantity) {
        super(symbol, exchange);
        this.orderId = orderId;
        this.orderType = orderType;
        this.limitPrice = limitPrice;
        this.buySell = buySell;
        this.quantity = quantity;
    }

    /**
     * OrderBook : Constructor used for MODIFYING
     * @param symbol
     * @param exchange
     * @param orderId
     * @param orderType
     * @param buySell
     * @param quantity
     */
    public OrderBook(String symbol, String exchange, Integer orderId, OrderType orderType, BUYSELL buySell, Integer quantity) {
        super(symbol, exchange);
        this.orderId = orderId;
        this.orderType = orderType;
        this.buySell = buySell;
        this.quantity = quantity;
    }

    /**
     * OrderBook: Constructor used for CANCELLATION
     * @param symbol
     * @param exchange
     * @param orderId
     * @param orderType
     * @param buySell
     */
    public OrderBook(String symbol, String exchange, Integer orderId, OrderType orderType, BUYSELL buySell) {
        super(symbol, exchange);
        this.orderId = orderId;
        this.orderType = orderType;
        this.buySell = buySell;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }


    public BigDecimal getLimitPrice() {
        return limitPrice;
    }

    public void setLimitPrice(BigDecimal limitPrice) {
        this.limitPrice = limitPrice;
    }

    public BUYSELL getBuySell() {
        return buySell;
    }

    public void setBuySell(BUYSELL buySell) {
        this.buySell = buySell;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBook orderBook = (OrderBook) o;
        return orderId.equals(orderBook.orderId) &&
                symbol.equals(orderBook.symbol) &&
                exchange.equals(orderBook.exchange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, symbol, exchange);
    }
}
