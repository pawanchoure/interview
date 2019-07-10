package com.pawan.choure.AQR2019.model;

import com.pawan.choure.AQR2019.utility.BookType;
import com.pawan.choure.AQR2019.utility.OrderType;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * AskPrice : Class to hold Ask Price
 */
public class AskPrice {
    BigDecimal bestAskPrice;
    Integer bestAskQuantity;
    BookType bookType;
    OrderType orderType;
    Integer id;
    String exchange;

    /**
     * AskPrice Constructor
     * @param bestOfferPrice
     * @param bestOfferQuantity
     * @param bookType
     * @param orderType
     * @param id
     * @param exchange
     */
    public AskPrice(BigDecimal bestOfferPrice, Integer bestOfferQuantity, BookType bookType, OrderType orderType,Integer id,String exchange) {
        this.bestAskPrice=bestOfferPrice;
        this.bestAskQuantity=bestOfferQuantity;
        this.bookType =bookType;
        this.orderType=orderType;
        this.id=id;
        this.exchange=exchange;
     }

    /**
     * AskPrice : Constructor used for searching the Ask Price by ID
     * @param id
     */
    public AskPrice(Integer id) {
        this.id=id;
    }

    public BigDecimal getBestAskPrice() {
        return bestAskPrice;
    }

    public void setBestAskPrice(BigDecimal bestAskPrice) {
        this.bestAskPrice = bestAskPrice;
    }

    public Integer getBestAskQuantity() {
        return bestAskQuantity;
    }

    public void setBestAskQuantity(Integer bestAskQuantity) {
        this.bestAskQuantity = bestAskQuantity;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        return "AskPrice{" +
                "bestAskPrice=" + bestAskPrice +
                ", bestAskQuantity=" + bestAskQuantity +
                ", bookType=" + bookType +
                ", orderType=" + orderType +
                ", id=" + id +
                ", exchange='" + exchange + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AskPrice askPrice = (AskPrice) o;
        return id.equals(askPrice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
